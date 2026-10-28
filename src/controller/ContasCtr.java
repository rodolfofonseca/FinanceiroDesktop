package controller;

import DAO.ContaDAO;
import controller.view.DashboardCtr;
import java.nio.charset.CodingErrorAction;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.table.DefaultTableModel;
import model.Contas;
import util.Alertas;
import util.ColorirTabela;
import util.FormatarData;
import view.inferna_frame.pesquisa.JIFContasBancarias;
import javax.swing.SwingWorker;
import model.Empresa;
import util.LoadingDialog;
import util.Sessao;
import view.JFDashboard;
import view.inferna_frame.cadastro_edicao.JIFContasBancariasCad;

/**
 * Classe que faz a comunicação entre todas as partes do sistema, relacionado as
 * contas bancárias.
 *
 * @author RodolfoFonseca
 */
public class ContasCtr {

    private ContaDAO dao;
    private Contas model;

    public ContasCtr() {
        this.dao = new ContaDAO();
        this.model = new Contas();
    }

    /**
     * Função responsável por pesquisar as contas bancárias do usuário que está
     * cadastrado no banco de dados.
     *
     * @param formulario
     */
    public void pesquisarContas(JIFContasBancarias formulario) {
        LoadingDialog.mostrar(javax.swing.SwingUtilities.getWindowAncestor(formulario), "Pesquisando contas bancárias...");
        SwingWorker<List<Contas>, Void> worker
                = new SwingWorker<List<Contas>, Void>() {

            @Override
            protected List<Contas> doInBackground() throws Exception {
                Contas conta = new Contas();

                conta.setNome_conta(JIFContasBancarias.tbxNomeConta.getText().toUpperCase().trim());
                conta.setDescricao(JIFContasBancarias.tbxDescricaoConta.getText().toUpperCase().trim());
                String status_conta = JIFContasBancarias.cbxStatusConta.getSelectedItem().toString();

                if ("ATIVO".equals(status_conta)) {
                    conta.setStatus(true);
                } else {
                    conta.setStatus(false);
                }

                return dao.listar(conta);
            }

            @Override
            protected void done() {
                try {
                    List<Contas> contas = get();
                    DefaultTableModel modelo = new DefaultTableModel(new Object[][]{}, new String[]{"Código", "Nome", "Descrição", "Saldo", "Data Cadastro", "Status"});

                    for (Contas conta : contas) {
                        modelo.addRow(new Object[]{conta.getCodigo_conta(), conta.getNome_conta(), conta.getDescricao(), conta.getSaldo_conta(), FormatarData.formatarData(conta.getData_cadastro()), conta.isStatus()});
                    }

                    JIFContasBancarias.tabela.setModel(modelo);
                    ColorirTabela.colorirLinhaPorBoolean(JIFContasBancarias.tabela, 5);

                } catch (Exception e) {
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

    /**
     * Função responsável por unir todas as partes do sistema que é responsável
     * pelo cadastro ou atualização de contas no sistema.
     *
     * @param formulario
     */
    public void salvarDadosConta(JIFContasBancariasCad formulario) {
        try {
            Empresa empresa = new Empresa();
            empresa.setCodigo_empresa(Sessao.empresa.getInt("codigo_empresa"));

            String saldoTexto = formulario.tbxSaldoConta.getText().trim().replace(",", ".");
            String status = formulario.cbxStatusConta.getSelectedItem().toString();

            this.model.setCodigo_conta(formulario.codigoConta);
            this.model.setNome_conta(formulario.tbxNomeConta.getText().toUpperCase().trim());
            this.model.setDescricao(formulario.tbxDescricaoConta.getText().toUpperCase().trim());
            this.model.setSaldo_conta(Float.parseFloat(saldoTexto));
            this.model.setStatus("ATIVO".equals(status));
            this.model.setEmpresa(empresa);
        } catch (NumberFormatException e) {
            Alertas.erro("Informe um saldo válido.");
            return;
        }

        final boolean alteracao = this.model.getCodigo_conta() != 0;

        LoadingDialog.mostrar(javax.swing.SwingUtilities.getWindowAncestor(formulario), alteracao ? "Alterando conta bancária..." : "Cadastrando conta bancária...");

        SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                if (alteracao) {
                    return dao.alterar(model);
                } else {
                    return dao.cadastrar(model);
                }
            }

            @Override
            protected void done() {
                try {
                    boolean retornoOperacao = get();

                    LoadingDialog.fechar();

                    if (retornoOperacao) {
                        Alertas.operacaoSucesso();
                    } else {
                        Alertas.OperacaoErro();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    Alertas.erro("A operação foi interrompida.");
                } catch (java.util.concurrent.ExecutionException e) {
                    Throwable causa = e.getCause();

                    String mensagem = causa != null ? causa.getMessage() : e.getMessage();
                    Alertas.erro(mensagem != null ? mensagem : "Não foi possível salvar a conta.");
                } finally {
                    LoadingDialog.fechar();
                }
            }
        };

        worker.execute();
    }

    /**
     * Função responsável por pegar qual conta o usuário selecionou com o clique
     * duplo
     *
     * @param evento
     */
    public void selecaoTabela(java.awt.event.MouseEvent evento, JIFContasBancarias formulario) {
        int linhaSelecionada = JIFContasBancarias.tabela.getSelectedRow();

        /*if (evento.getClickCount() != 2 || linhaSelecionada == -1) {
            return;
        }*/
        
        System.out.println(linhaSelecionada);

        int linhaModelo = JIFContasBancarias.tabela.convertRowIndexToModel(linhaSelecionada);
        Object valorCodigo = JIFContasBancarias.tabela.getModel().getValueAt(linhaModelo, 0);

        if (valorCodigo == null) {
            Alertas.erro("Não foi possível indentificar a conta selecionada!");
            return;
        }

        int codigo = Integer.parseInt(valorCodigo.toString());
        
        abrirEdicao(codigo, formulario);
    }

    /**
     * Função responsável por pesquisar os dados da conta selecionada e abrir o
     * formulário de edição de informações carregados
     *
     * @param codigo_conta
     * @param formulario
     */
    public void abrirEdicao(int codigo_conta, JIFContasBancarias formulario) {
        LoadingDialog.mostrar(javax.swing.SwingUtilities.getWindowAncestor(formulario), "Pesquisando conta bancária...");
        SwingWorker<Contas, Void> worker = new SwingWorker<Contas, Void>() {
            @Override
            protected Contas doInBackground() throws Exception {
                return dao.pesquisar(codigo_conta);
            }

            @Override
            protected void done() {
                try{
                    Contas conta = get();
                    
                    if(conta == null){
                        Alertas.erro("Conta bancária não encontrada!");
                        return;
                    }
                    
                    DashboardCtr dashboardController = new DashboardCtr();
                    dashboardController.abrirFormularioCadastroConta(conta);                 
                }catch(ExecutionException ex){
                    Throwable causa = ex.getCause();
                    Alertas.erro("Erro ao consultar a conta: "+(causa!=null?causa.getMessage():ex.getMessage()));
                } catch (InterruptedException ex) {
                    Alertas.erro("Erro ao consultar a conta: "+ex.getMessage());
                }finally{
                    LoadingDialog.fechar();
                }
            }
        };
                
        worker.execute();
    }
    
    /**
     * Função responsável por preencher o formulário de cadastro de contas com as informações
     * que vem do banco de dados.
     * @param conta
     * @param formulario 
     */
    public void preencherFormulario(Contas conta, JIFContasBancariasCad formulario){
        formulario.codigoConta = conta.getCodigo_conta();
        formulario.tbxNomeConta.setText(conta.getNome_conta());
        formulario.tbxDescricaoConta.setText(conta.getDescricao());
        formulario.tbxSaldoConta.setText(String.valueOf(conta.getSaldo_conta()).replace(".", ","));
        
        if(conta.isStatus() == true){
            formulario.cbxStatusConta.setSelectedItem("ATIVO");
        }else{
            formulario.cbxStatusConta.setSelectedItem("INATIVO");
        }
    }
}
