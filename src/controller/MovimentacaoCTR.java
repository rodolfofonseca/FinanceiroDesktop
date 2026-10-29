package controller;

import javax.swing.JComboBox;
import javax.swing.SwingWorker;
import controller.ContasCtr;
import DAO.MovimentacaoDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Contas;
import model.Movimentacao;
import util.Alertas;
import util.FormatarData;
import util.LoadingDialog;
import view.inferna_frame.pesquisa.MovimentacaoPesq;

/**
 * Classe que contém todas as informações pertinentes a classe movimentacao
 * @author RodolfoFonseca
 */
public class MovimentacaoCTR implements InterfaceController{
    private ContasCtr controllerContas;
    private MovimentacaoDAO dao;
    
    public MovimentacaoCTR(){
        this.controllerContas = new ContasCtr();
        this.dao = new MovimentacaoDAO();
    }
    
    @Override
    public void colocarDados() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void cadastro() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void validaCadastro() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void pesquisarDados(Object formulario) {
        java.awt.Component componente = (java.awt.Component) formulario;
        LoadingDialog.mostrar(javax.swing.SwingUtilities.getWindowAncestor(componente), "Pesquisando Movimentações...");
        
        SwingWorker<List<Object>, Void> worker = new SwingWorker<List<Object>, Void>(){
            @Override
            protected List<Object> doInBackground() throws Exception {
                Movimentacao movimentacao = new Movimentacao();
                
                movimentacao.setTipoLancamentoPesq(MovimentacaoPesq.cbxTipoLancamento.getSelectedItem().toString());
                movimentacao.setDataInicialPesq(FormatarData.converterParaTimestamp(MovimentacaoPesq.tbxDataInicial.getText().trim()));
                movimentacao.setDataFinalPesq(FormatarData.converterParaTimestamp(MovimentacaoPesq.tbxDataFinal.getText().trim()));
                
                Object contaSelecionada = MovimentacaoPesq.cbxConta.getSelectedItem();
                
                if(contaSelecionada instanceof Contas){
                    Contas contaSelecionadaObjeto = (Contas) contaSelecionada;
                    movimentacao.setConta(contaSelecionadaObjeto);
                }else{
                    movimentacao.setConta(null);
                }
                
                return dao.listar(movimentacao);
            }
            
            @Override
            protected void done(){
                try{
                    List<Object> lista = get();
                    DefaultTableModel modelo = new DefaultTableModel(new Object[][]{}, new String[]{"Conta", "Descrição", "Valor", "Data", "Tipo"});
                    
                    for(Object objeto:lista){
                      Movimentacao movimentacao = (Movimentacao) objeto;
                      modelo.addRow(new Object[] {movimentacao.getConta().getCodigo_conta(), movimentacao.getDescricao(), movimentacao.getValorLancamento(), FormatarData.formatarData(movimentacao.getDataLancamento()), movimentacao.isTipoLancamento()});
                    }
                    
                    MovimentacaoPesq.tabela.setModel(modelo);
                }catch (Exception e) {
                    Throwable causa = e.getCause();
                    String mensagem = causa != null ? causa.getMessage() : e.getMessage();
                    System.err.println("Erro ao pesquisar contas: " + mensagem);
                    Alertas.erro(mensagem != null ? mensagem : "Não foi possível carregar as contas.");
                } finally {
                    LoadingDialog.fechar();
                }
            }
        };
        
        worker.execute();
    }

    @Override
    public void selecionarDados() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void LimparCampos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
     * Função responável por pesquisar e colocar as contas no jComboBox correspondente.
     * @param comboBox 
     * @param todasAsContas
     */
    public void pesquisarContas(JComboBox comboBox, boolean todasAsContas){
        comboBox.setModel(this.controllerContas.PesquisaContasCBX(todasAsContas));
    }
    
}
