package controller;

import DAO.ContaFornecedoresDao;
import DAO.UsuarioDao;
import com.mongodb.client.MongoDatabase;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import model.ContasFornecedores;
import model.Empresa;
import model.Usuarios;
import org.bson.Document;
import util.DB;
import util.Mensagens;
import util.TableUtil;
import util.Utils;
import view.inferna_frame.JIFContaFornecedor;

/**
 * Classe que faz a comunicação entre todos os componentes do sistema
 *
 * @author RODOLFO
 */
public class ContaFornecedorCtr implements InterfaceController {

    private ContaFornecedoresDao dao;
    private ContasFornecedores model;
    private Empresa empresa;
    private MongoDatabase database;
    private boolean alteracao;
    private boolean validacao;
    private Usuarios fornecedor;

    public ContaFornecedorCtr() {
        this.dao = new ContaFornecedoresDao();
        this.model = new ContasFornecedores();
        this.empresa = new Empresa();
        this.fornecedor = new Usuarios();
        this.alteracao = false;
        this.validacao = true;
    }

    @Override
    public void colocarDados() {
        this.empresa = JIFContaFornecedor.empresa;

        if (!"".equals(JIFContaFornecedor.tbxIdConta.getText())) {
            this.model.setId(Utils.toId(JIFContaFornecedor.tbxIdConta.getText().trim()));
            this.alteracao = true;
        }

        if (!"".equals(JIFContaFornecedor.tbxIDFornecedor.getText())) {
            this.fornecedor.setId(Utils.toId(JIFContaFornecedor.tbxIDFornecedor.getText().trim()));
            this.model.setFornecedor(this.fornecedor);
        } else {
            this.validacao = false;
            Mensagens.Validacao("FORNECEDOR");
        }

        if (!"".equals(JIFContaFornecedor.tbxNomeConta.getText())) {
            this.model.setNome_conta(JIFContaFornecedor.tbxNomeConta.getText().trim().toUpperCase());
        }

        if (!"".equals(JIFContaFornecedor.tbxDescricao.getText())) {
            this.model.setDescricao_conta(JIFContaFornecedor.tbxDescricao.getText().trim().toUpperCase());
        }

        String status = JIFContaFornecedor.cbStatusConta.getSelectedItem().toString();

        if ("ATIVO".equals(status)) {
            this.model.setStatus_conta(true);
        } else {
            this.model.setStatus_conta(false);
        }

        this.model.setData_cadastro(Utils.calendarToBson(JIFContaFornecedor.jcDataCadastro.getCalendar()));
    }

    @Override
    public void cadastro() {
        this.colocarDados();
        
        if(this.validacao == true){
            this.validaCadastro();
            
            if(validacao == true){
                try{
                    DB.conectar();
                    boolean retorno = false;
                    Document data = new Document();
                    
                    data.append("empresa", this.empresa.getId());
                    data.append("fornecedor", this.fornecedor.getId());
                    data.append("nome_conta", this.model.getNome_conta());
                    data.append("descricao_conta", this.model.getDescricao_conta());
                    data.append("status_conta", this.model.isStatus_conta());
                    data.append("data_cadastro", this.model.getData_cadastro());
                    
                    if(this.alteracao == false){
                        retorno = this.dao.inserirDados(this.model.getTabela(), data);
                    }else{
                        Map<String, Object> codigoContaFornecedor = new HashMap<>();
                        Map<String, Object> filtros = new HashMap<>();
                        codigoContaFornecedor.put("==", this.model.getId());
                        filtros.put("_id", codigoContaFornecedor);
                        
                        retorno = this.dao.alterarDados(this.model.getTabela(), filtros, data);
                    }
                    
                    if(retorno == true){
                        Mensagens.Sucesso();
                        this.pesquisarDados();
                    }
                }catch(Exception ex){
                    Mensagens.ErroException(ex);
                }finally{
                    DB.close();
                }
            }
        }
    }

    @Override
    public void validaCadastro() {
        if ("".equals(this.model.getNome_conta())) {
            Mensagens.Validacao("NOME DA CONTA");
            this.validacao = false;
        }

        if ("".equals(this.model.getDescricao_conta())) {
            Mensagens.Validacao("DESCRIÇÃO");
            this.validacao = false;
        }
    }

    @Override
    public void pesquisarDados() {
        try {

            this.database = DB.conectar();
            this.empresa = JIFContaFornecedor.empresa;
            Map<String, Object> filtros = new HashMap<>();

            Map<String, Object> empresa_filtro = new HashMap<>();
            empresa_filtro.put("==", this.empresa.getId());

            filtros.put("empresa", empresa_filtro);

            List<Document> contas = this.dao.pesquisarDados(this.model.getTabela(), filtros, "nome_conta", "asc", 0);
            DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Nome Forn.", "Nome Conta", "Descrição", "Status", "Data"}, 0);

            for (Document conta : contas) {
                String status = conta.getBoolean("status_conta") ? "ATIVO" : "INATIVO";

                Map<String, Object> filtros_fornecedor = new HashMap<>();
                filtros_fornecedor.put("empresa", empresa_filtro);

                Map<String, Object> codigoFornecedor = new HashMap<>();
                codigoFornecedor.put("==", conta.getObjectId("fornecedor"));
                filtros_fornecedor.put("_id", codigoFornecedor);

                UsuarioCtr controllerUsuario = new UsuarioCtr();
                Usuarios usuario_pesquisa = new Usuarios();
                usuario_pesquisa = controllerUsuario.pesquisaUsuario(filtros_fornecedor);

                model.addRow(new Object[]{
                    conta.getObjectId("_id").toString(),
                    usuario_pesquisa.getNome_usuario(),
                    conta.getString("nome_conta"),
                    conta.getString("descricao_conta"),
                    status,
                    Utils.convertData(conta.getDate("data_cadastro"))
                });
            }

            JIFContaFornecedor.tabela.setModel(model);
            TableUtil.ajustarLarguraColunas(JIFContaFornecedor.tabela);
            TableUtil.colorirLinhasPorStatus(JIFContaFornecedor.tabela, "Status");
        } catch (Exception ex) {
            Mensagens.ErroException(ex);
        } finally {
            DB.close();
        }
    }

    @Override
    public void selecionarDados() {
        try {
            int linha = JIFContaFornecedor.tabela.getSelectedRow();

            if (JIFContaFornecedor.fornecedor_conta == true) {
                JIFContaFornecedor.tbxIDFornecedor.setText(JIFContaFornecedor.tabela.getValueAt(linha, 0).toString());
                JIFContaFornecedor.tbxNomeFornecedor.setText(JIFContaFornecedor.tabela.getValueAt(linha, 1).toString());
            } else {
                JIFContaFornecedor.tbxNomeFornecedor.setText(JIFContaFornecedor.tabela.getValueAt(linha, 1).toString());
                this.model.setId(Utils.toId(JIFContaFornecedor.tabela.getValueAt(linha, 0).toString()));

                Map<String, Object> filtros = new HashMap<>();
                Map<String, Object> contaFornecedor = new HashMap<>();
                contaFornecedor.put("==", this.model.getId());
                filtros.put("_id", contaFornecedor);

                DB.connect();
                List<Document> retornoConta = this.dao.pesquisarDados(this.model.getTabela(), filtros, "data_cadastro", "asc", linha);
                DB.close();

                if (retornoConta.size() == 1) {
                    for (Document conta : retornoConta) {
                        JIFContaFornecedor.tbxIdConta.setText(conta.getObjectId("_id").toString());
                        JIFContaFornecedor.tbxNomeConta.setText(conta.getString("nome_conta"));
                        JIFContaFornecedor.tbxIDFornecedor.setText(conta.getObjectId("fornecedor").toString());
                        JIFContaFornecedor.tbxDescricao.setText(conta.getString("descricao_conta"));

                        String dataCadastro = Utils.convertData(conta.getDate("data_cadastro"));
                        JIFContaFornecedor.jcDataCadastro.setCalendar(Utils.stringParaCalendar(dataCadastro));

                        boolean status = conta.getBoolean("status_conta");
                        String statusString = "ATIVO";

                        if (status == false) {
                            statusString = "INATIVO";
                        }

                        JIFContaFornecedor.cbStatusConta.setSelectedItem(statusString);
                    }
                } else {
                    Mensagens.Erro();
                }
            }
        } catch (Exception ex) {
            Mensagens.ErroException(ex);
        }
    }

    @Override
    public void LimparCampos() {
        JIFContaFornecedor.tbxIdConta.setText("");
        JIFContaFornecedor.tbxIDFornecedor.setText("");
        JIFContaFornecedor.tbxNomeConta.setText("");
        JIFContaFornecedor.tbxNomeFornecedor.setText("");
        JIFContaFornecedor.tbxDescricao.setText("");
        JIFContaFornecedor.cbStatusConta.setSelectedIndex(0);
        JIFContaFornecedor.jcDataCadastro.setCalendar(Utils.stringParaCalendar(""));
    }

    public void pesquisarFornecedores() {
        try {
            DB.conectar();

            this.empresa = JIFContaFornecedor.empresa;

            UsuarioCtr usuarioController = new UsuarioCtr();
            List<Document> usuarios_retorno = usuarioController.pesquisarUsuariosLista(this.empresa);

            DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Nome", "Email", "Cep", "Rua", "Número", "Bairro", "Estado", "UF", "Tipo", "Data"}, 0);

            for (Document retorno : usuarios_retorno) {
                model.addRow(new Object[]{
                    retorno.getObjectId("_id").toString(),
                    retorno.getString("nome_usuario"),
                    retorno.getString("email_usuario"),
                    retorno.getString("cep"),
                    retorno.getString("logradouro"),
                    retorno.getString("numero"),
                    retorno.getString("bairro"),
                    retorno.getString("uf"),
                    retorno.getString("estado"),
                    retorno.getString("tipo_usuario"),
                    Utils.convertData(retorno.getDate("data_cadastro"))
                });
            }

            JIFContaFornecedor.tabela.setModel(model);
            TableUtil.ajustarLarguraColunas(JIFContaFornecedor.tabela);
            TableUtil.colorirLinhasPorStatus(JIFContaFornecedor.tabela, "Tipo");
        } catch (Exception ex) {
            Mensagens.ErroException(ex);
        } finally {
            DB.close();
        }
    }
}
