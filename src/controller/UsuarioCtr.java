package controller;

import DAO.UsuarioDao;
import com.mongodb.client.MongoDatabase;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import model.Empresa;
import model.Usuarios;
import org.bson.Document;
import org.mindrot.jbcrypt.BCrypt;
import util.Utils;
import view.inferna_frame.JIFClientesFornecedores;
import util.DB;
import util.Mensagens;
import util.TableUtil;

/**
 * Classe responsável por ter todos os dados do usuário
 *
 * @author RODOLFO
 */
public class UsuarioCtr implements InterfaceController {

    private final Usuarios usuarios;
    private Empresa empresa;
    private final UsuarioDao dao;
    private boolean validacao;
    private boolean alteracao;

    public UsuarioCtr() {
        this.usuarios = new Usuarios();
        this.empresa = new Empresa();
        this.dao = new UsuarioDao();
        this.alteracao = false;
    }

    /**
     * Função responsável por retornar o usuário
     *
     * @return
     */
    public Usuarios getUsuario() {
        return this.usuarios;
    }

    /**
     * Funçãp responsável por realizar o login dentro do sistema.
     *
     * @param email
     * @param senha
     * @return
     */
    public boolean LogarSistema(String email, String senha) {
        UsuarioDao dao = new UsuarioDao();
        List<Document> usuario = dao.LoginSistema(email);

        if (usuario.size() == 1) {
            String senhaBancoDados = "";

            for (Document user : usuario) {
                this.usuarios.setId(user.getObjectId("_id"));
                this.empresa.setId(user.getObjectId("empresa"));
                this.usuarios.setNome_usuario(user.getString("nome_usuario"));

                senhaBancoDados = user.getString("senha_usuario");

                this.usuarios.setData_cadastro(Utils.toDate(user.getDate("data_cadastro")));
                this.usuarios.setUltimo_login(Utils.toDate(user.getDate("ultimo_login")));
                this.usuarios.setSalario(user.getDouble("salario"));
                this.usuarios.setLogin_usuario(user.getString("login_usuario"));
                this.usuarios.setTipo_usuario(user.getString("tipo_usuario"));
                this.usuarios.setCargo(user.getString("cargo"));
                this.usuarios.setCelular(user.getString("celular"));
                this.usuarios.setCep(user.getString("cep"));
                this.usuarios.setLogradouro(user.getString("logradouro"));
                this.usuarios.setNumero(user.getString("numero"));
                this.usuarios.setBairro(user.getString("bairro"));
                this.usuarios.setUf(user.getString("uf"));
                this.usuarios.setEstado(user.getString("estado"));

                this.usuarios.setEmpresa(this.empresa);
            }

            senhaBancoDados = senhaBancoDados.replaceFirst("\\$2y\\$", "\\$2a\\$");

            boolean senhaConfere = BCrypt.checkpw(senha, senhaBancoDados);

            return senhaConfere != false;
        } else {
            return false;
        }
    }

    public void Teste() {
        UsuarioDao dao = new UsuarioDao();
        List<Document> lista = dao.LoginSistema("rodolfofonseca01@outlook.com.br");

        if (lista.size() == 1) {
            for (Document user : lista) {
                System.out.println("ID: " + user.getObjectId("_id"));
                System.out.println("Nome: " + user.getString("nome_usuario"));
                System.out.println("Email: " + user.getString("email_usuario"));
                System.out.println("-----------------------");
            }
        } else {
            System.out.println("false");
        }

    }

    @Override
    public void colocarDados() {
        this.validacao = true;

        this.empresa = JIFClientesFornecedores.empresa;
        this.usuarios.setEmpresa(this.empresa);

        if (!"".equals(JIFClientesFornecedores.tbxId.getText())) {
            this.usuarios.setId(Utils.toId(JIFClientesFornecedores.tbxId.getText().trim()));
            this.alteracao = true;
        }

        if (!"".equals(JIFClientesFornecedores.tbxNome.getText())) {
            this.usuarios.setNome_usuario(JIFClientesFornecedores.tbxNome.getText().toUpperCase().trim());
        } else {
            this.validacao = false;
        }

        if (!"".equals(JIFClientesFornecedores.tbxEmail.getText())) {
            this.usuarios.setEmail_usuario(JIFClientesFornecedores.tbxEmail.getText());
        } else {
            this.validacao = false;
        }

        this.usuarios.setCelular(JIFClientesFornecedores.tbxTelefone.getText().trim());
        this.usuarios.setCep(JIFClientesFornecedores.tbxCep.getText().trim());
        this.usuarios.setLogradouro(JIFClientesFornecedores.tbxLogradouro.getText());
        this.usuarios.setNumero(JIFClientesFornecedores.tbxNumero.getText());
        this.usuarios.setBairro(JIFClientesFornecedores.tbxBairro.getText());
        this.usuarios.setUf(JIFClientesFornecedores.tbxUf.getText());
        this.usuarios.setEstado(JIFClientesFornecedores.tbxEstado.getText());
        this.usuarios.setTipo_usuario(JIFClientesFornecedores.cbTipo.getSelectedItem().toString());
    }

    @Override
    public void cadastro() {
        this.colocarDados();

        if (this.validacao == true) {
            try {
                Document data = new Document();

                data.append("empresa", this.usuarios.getEmpresa().getId());
                data.append("nome_usuario", this.usuarios.getNome_usuario());
                data.append("email_usuario", this.usuarios.getEmail_usuario());
                data.append("senha_usuario", this.usuarios.getSenha_usuario());
                data.append("data_cadastro", this.usuarios.getData_cadastro());
                data.append("ultimo_login", this.usuarios.getUltimo_login());
                data.append("salario", this.usuarios.getSalario());
                data.append("login_usuario", this.usuarios.getLogin_usuario());
                data.append("cargo", this.usuarios.getCargo());
                data.append("tipo_usuario", this.usuarios.getTipo_usuario());
                data.append("celular", this.usuarios.getCelular());
                data.append("cep", this.usuarios.getCep());
                data.append("logradouro", this.usuarios.getLogradouro());
                data.append("numero", this.usuarios.getNumero());
                data.append("bairro", this.usuarios.getBairro());
                data.append("uf", this.usuarios.getUf());
                data.append("estado", this.usuarios.getEstado());

                boolean retorno_operacao = false;
                if (this.alteracao == false) {
                    retorno_operacao = this.dao.inserirDados(this.usuarios.getTabela(), data);
                } else {
                    Map<String, Object> filtros = new HashMap<>();

                    Map<String, Object> codigoUsuario = new HashMap<>();
                    codigoUsuario.put("==", this.usuarios.getId());
                    filtros.put("_id", codigoUsuario);

                    List<Document> lista = this.dao.pesquisarDados(this.usuarios.getTabela(), filtros, "nome_usuario", "asc", 0);

                    if (lista.size() == 1) {
                        for (Document user : lista) {
                            this.usuarios.setData_cadastro(Utils.toDate(user.getDate("data_cadastro")));
                            this.usuarios.setUltimo_login(Utils.toDate(user.getDate("ultimo_login")));
                        }
                        
                        retorno_operacao = this.dao.alterarDados(this.usuarios.getTabela(), filtros, data);
                    }else{
                        Mensagens.Erro();
                    }
                }

                if (retorno_operacao == true) {
                    Mensagens.Sucesso();
                    this.pesquisarDados();
                }
            } catch (Exception ex) {
                Mensagens.ErroException(ex);
            }
        } else {
            Mensagens.Erro();
        }
    }

    @Override
    public void validaCadastro() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void pesquisarDados() {
        this.empresa = JIFClientesFornecedores.empresa;
        DB.conectar();

        Map<String, Object> filtros = new HashMap<>();

        Map<String, Object> empresa_filtro = new HashMap<>();
        empresa_filtro.put("==", this.empresa.getId());

        filtros.put("empresa", empresa_filtro);

        List<Document> usuarios_retorno = this.dao.pesquisarDados(this.usuarios.getTabela(), filtros, "nome_usuario", "asc", 0);
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

        JIFClientesFornecedores.tabela.setModel(model);
        TableUtil.ajustarLarguraColunas(JIFClientesFornecedores.tabela);
        TableUtil.colorirLinhasPorStatus(JIFClientesFornecedores.tabela, "Tipo");
    }
    
    /**
     * Função responsável por pesquisar apenas um usuário no banco de dados
     * @param filtros
     * @return 
     */
    public Usuarios pesquisaUsuario(Map<String, Object> filtros){
        List<Document> usuarios_retorno = this.dao.pesquisarDados(this.usuarios.getTabela(), filtros, "nome_usuario", "asc", 0);
        
        Usuarios retorno = new Usuarios();
        if (usuarios_retorno.size() == 1) {
            for (Document user : usuarios_retorno) {
                retorno.setId(user.getObjectId("_id"));
                retorno.setNome_usuario(user.getString("nome_usuario"));
            }
            
            return retorno;
        } else {
            return retorno;
        }
    }
    
    /**
     * Função que pesquisa o usuário e retorna a lista completa, para que os dados possam
     * ser manipulados em outro local
     * @param empresaParametro
     * @return 
     */
    public List<Document> pesquisarUsuariosLista(Empresa empresaParametro){
        Map<String, Object> filtros = new HashMap<>();

        Map<String, Object> empresa_filtro = new HashMap<>();
        empresa_filtro.put("==", empresaParametro.getId());

        filtros.put("empresa", empresa_filtro);
        return this.dao.pesquisarDados(this.usuarios.getTabela(), filtros, "nome_usuario", "asc", 0);
    }
    
    @Override
    public void LimparCampos(){
        JIFClientesFornecedores.tbxId.setText("");
        JIFClientesFornecedores.tbxNome.setText("");
        JIFClientesFornecedores.tbxEmail.setText("");
        JIFClientesFornecedores.tbxTelefone.setText("");
        JIFClientesFornecedores.tbxCep.setText("");
        JIFClientesFornecedores.tbxLogradouro.setText("");
        JIFClientesFornecedores.tbxNumero.setText("");
        JIFClientesFornecedores.tbxBairro.setText("");
        JIFClientesFornecedores.tbxUf.setText("");
        JIFClientesFornecedores.tbxEstado.setText("");
        JIFClientesFornecedores.cbTipo.setSelectedIndex(0);
    }

    @Override
    public void selecionarDados() {
        int linha = JIFClientesFornecedores.tabela.getSelectedRow();
        
        JIFClientesFornecedores.tbxId.setText(JIFClientesFornecedores.tabela.getValueAt(linha, 0).toString());
        JIFClientesFornecedores.tbxNome.setText(JIFClientesFornecedores.tabela.getValueAt(linha, 1).toString());
        JIFClientesFornecedores.tbxEmail.setText(JIFClientesFornecedores.tabela.getValueAt(linha, 2).toString());
        JIFClientesFornecedores.tbxCep.setText(JIFClientesFornecedores.tabela.getValueAt(linha, 3).toString());
        JIFClientesFornecedores.tbxNumero.setText(JIFClientesFornecedores.tabela.getValueAt(linha, 4).toString());
        JIFClientesFornecedores.tbxBairro.setText(JIFClientesFornecedores.tabela.getValueAt(linha, 5).toString());
        JIFClientesFornecedores.tbxEstado.setText(JIFClientesFornecedores.tabela.getValueAt(linha, 6).toString());
        JIFClientesFornecedores.tbxUf.setText(JIFClientesFornecedores.tabela.getValueAt(linha, 7).toString());
        JIFClientesFornecedores.cbTipo.setSelectedItem(JIFClientesFornecedores.tabela.getValueAt(linha, 8).toString());
    }
}
