package controller;

import DAO.ContaDao;
import com.mongodb.client.MongoDatabase;
import java.awt.Color;
import java.awt.Component;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Conta;
import model.Empresa;
import org.bson.BsonDateTime;
import org.bson.Document;
import util.ActionButton;
import util.ActionCellEditor;
import util.ActionCellRenderer;
import util.DB;
import util.Mensagens;
import util.TableUtil;
import view.inferna_frame.JIFConta;
import util.Utils;

/**
 *
 * @author RODOLFO
 */
public class ContaCtr implements InterfaceController {
    
    private final Conta contaObj;
    private final ContaDao contaDao;
    private MongoDatabase database;
    private Empresa empresa;
    private boolean alteracao = false;
    
    public ContaCtr() {
        this.contaObj = new Conta();
        this.contaDao = new ContaDao();
    }
    
    @Override
    public void cadastro() {
        try {            
            this.colocarDados();
            
            if("".equals(this.contaObj.getNome_conta())){
                Mensagens.Validacao("NOME DA CONTA");
                return;
            }
            
            Document data = new Document();
            
            data.append("empresa", this.contaObj.getEmpresa().getId());
            data.append("nome_conta", this.contaObj.getNome_conta());
            data.append("descricao", this.contaObj.getDescricao());
            data.append("saldo_conta", this.contaObj.getSaldo_conta());
            data.append("status", this.contaObj.getStatus());
            data.append("data_cadastro", this.contaObj.getData_cadastro());
            
            Map<String, Object> filtros = new HashMap<>();
            
            if (this.alteracao == true) {
                Map<String, Object> codigoConta = new HashMap<>();
                codigoConta.put("==", this.contaObj.getId());
                filtros.put("_id", codigoConta);
            }
            
            Map<String, Object> empresa_filtro = new HashMap<>();
            empresa_filtro.put("==", this.contaObj.getEmpresa().getId());
            filtros.put("empresa", empresa_filtro);
            
            boolean retorno = false;
            
            if (this.alteracao == false) {
                retorno = this.contaDao.inserirDados(this.contaObj.getTabela(), data);
            } else {
                retorno = this.contaDao.alterarDados(this.contaObj.getTabela(), filtros, data);
            }
            
            if (retorno == true) {
                Mensagens.Sucesso();
            } else {
                Mensagens.Erro();
            }
            
            this.pesquisarDadosContas(this.empresa);
        } catch (Exception ex) {
            Mensagens.ErroException(ex);
        }
    }
    
    @Override
    public void validaCadastro() {
        
    }
    
    @Override
    public void colocarDados() {
        this.contaObj.setEmpresa(JIFConta.empresa);
        
        if (!"".equals(JIFConta.tbxNomeConta.getText())) {
            this.contaObj.setNome_conta(JIFConta.tbxNomeConta.getText().toUpperCase().trim());
        }
        
        if (!"".equals(JIFConta.tbxDescricao.getText())) {
            this.contaObj.setDescricao(JIFConta.tbxDescricao.getText().toUpperCase().trim());
        } else {
            this.contaObj.setDescricao("");
        }
        
        if (!"".equals(JIFConta.tbxSaldo.getText())) {
            this.contaObj.setSaldo_conta(Double.parseDouble(JIFConta.tbxSaldo.getText().trim().replace(",", ".")));
        } else {
            this.contaObj.setSaldo_conta(0);
        }
        
        this.contaObj.setStatus(JIFConta.cbStatus.getSelectedItem().toString());
        
        Calendar dataCadastro = JIFConta.jcDataCadastro.getCalendar();
        this.contaObj.setData_cadastro(Utils.toDate(dataCadastro.getTime()));
        
        if (!"".equals(JIFConta.tbxID.getText().trim())) {
            this.contaObj.setId(Utils.toId(JIFConta.tbxID.getText().trim()));
            this.alteracao = true;
        }
    }
    
    public void pesquisarDadosContas(Empresa empresaObjeto) {
        this.empresa = empresaObjeto;
        this.database = DB.conectar();
        Map<String, Object> filtros = new HashMap<>();
        
        Map<String, Object> empresa_filtro = new HashMap<>();
        empresa_filtro.put("==", this.empresa.getId());
        
        filtros.put("empresa", empresa_filtro);
        List<Document> contas = this.contaDao.pesquisarDados(this.contaObj.getTabela(), filtros, "nome_conta", "asc", 0);
        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Nome", "Descrição", "Saldo", "Status", "Data"}, 0);
        
        for (Document conta : contas) {
            model.addRow(new Object[]{
                conta.getObjectId("_id").toString(),
                conta.getString("nome_conta"),
                conta.getString("descricao"),
                Utils.formatarMoeda(conta.getDouble("saldo_conta")),
                conta.getString("status"),
                Utils.convertData(conta.getDate("data_cadastro"))
            });
        }
        
        JIFConta.tabela.setModel(model);
        TableUtil.ajustarLarguraColunas(JIFConta.tabela);
        TableUtil.colorirLinhasPorStatus(JIFConta.tabela, "Status");
    }
    
    @Override
    public void pesquisarDados() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Função responsável por selecionar os dados da tabela quando o usuário
     * clica em algum campo.
     */
    public void selecionarDados() {
        int linha = JIFConta.tabela.getSelectedRow();
        JIFConta.tbxID.setText(JIFConta.tabela.getValueAt(linha, 0).toString());
        JIFConta.tbxNomeConta.setText(JIFConta.tabela.getValueAt(linha, 1).toString());
        JIFConta.tbxDescricao.setText(JIFConta.tabela.getValueAt(linha, 2).toString());
        JIFConta.tbxSaldo.setText(JIFConta.tabela.getValueAt(linha, 3).toString());
        JIFConta.cbStatus.setSelectedItem(JIFConta.tabela.getValueAt(linha, 4).toString());
        JIFConta.jcDataCadastro.setCalendar(Utils.stringParaCalendar(JIFConta.tabela.getValueAt(linha, 5).toString()));
    }

    /**
     * Função responsável por excluir os dados da conta
     */
    public void Excluir() {
        int resposta = JOptionPane.showConfirmDialog(null, "tem certeza que deseja excluir a conta? Ao excluir, as movimentações da conta não serão apagadas!", "Confirmação", JOptionPane.YES_NO_OPTION);
        
        if (resposta == JOptionPane.YES_OPTION) {
            this.empresa = JIFConta.empresa;
            this.contaObj.setId(Utils.toId(JIFConta.tbxID.getText().trim()));
            
            Map<String, Object> filtros = new HashMap<>();
            Map<String, Object> conta_filtro = new HashMap<>();
            
            conta_filtro.put("==", this.contaObj.getId());
            filtros.put("_id", conta_filtro);
            
            boolean retornoDelecao = this.contaDao.excluirDados(this.contaObj.getTabela(), filtros);
            
            if (retornoDelecao == true) {
                Mensagens.Sucesso();
            } else {
                Mensagens.Erro();
            }
            
            this.pesquisarDadosContas(this.empresa);
            this.LimparCampos();
        }
    }

    /**
     * Função responsável por limpar todos os campos
     */
    public void LimparCampos() {
        JIFConta.tbxID.setText("");
        JIFConta.tbxNomeConta.setText("");
        JIFConta.tbxDescricao.setText("");
        JIFConta.tbxSaldo.setText("");
        JIFConta.cbStatus.setSelectedIndex(0);
        JIFConta.jcDataCadastro.setCalendar(Utils.stringParaCalendar(""));
    }
}
