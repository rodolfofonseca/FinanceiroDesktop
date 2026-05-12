package model;
import org.bson.BsonDateTime;
import org.mindrot.jbcrypt.BCrypt;
import model.ModelInterface;
import org.bson.types.ObjectId;
import util.Utils;
/**
 * Formulário que contém todos os atributos dos usuário do sistema
 * @author RODOLFO
 */
public class Usuarios implements ModelInterface{
    private ObjectId id;
    private Empresa empresa;
    private String nome_usuario;
    private String email_usuario;
    private String senha_usuario;
    private BsonDateTime data_cadastro;
    private BsonDateTime ultimo_login;
    private double salario;
    private String login_usuario;
    private String cargo;
    private String tipo_usuario;
    private String celular;
    private String cep;
    private String logradouro;
    private String numero;
    private String bairro;
    private String uf;
    private String estado;
    
    public Usuarios(){
        this.setData_cadastro(Utils.now());
        this.setUltimo_login(Utils.now());
        this.setSalario(0);
        this.setCargo("");
        this.setSenha_usuario("");
        this.setLogin_usuario("");
    }
    
    /**
     * Método que retorna o nome da tabela de usuários
     * @return string nome da tabela
    */
    @Override
    public String getTabela(){
        return "usuarios";
    }
    
    /**
     * Função responsável por fazer o hash da senha igual o PHP
     * @param senha
     * @return 
     */
    public static String hashSenha(String senha) {
        int cost = 10; // igual seu PHP ['cost' => 8]
        return BCrypt.hashpw(senha, BCrypt.gensalt(cost));
    }

    /**
     * @return the empresa
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the nome_usuario
     */
    public String getNome_usuario() {
        return nome_usuario;
    }

    /**
     * @param nome_usuario the nome_usuario to set
     */
    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    /**
     * @return the email_usuario
     */
    public String getEmail_usuario() {
        return email_usuario;
    }

    /**
     * @param email_usuario the email_usuario to set
     */
    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    /**
     * @return the senha_usuario
     */
    public String getSenha_usuario() {
        return senha_usuario;
    }

    /**
     * @param senha_usuario the senha_usuario to set
     */
    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }

    /**
     * @return the data_cadastro
     */
    public BsonDateTime getData_cadastro() {
        return data_cadastro;
    }

    /**
     * @param data_cadastro the data_cadastro to set
     */
    public void setData_cadastro(BsonDateTime data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    /**
     * @return the ultimo_login
     */
    public BsonDateTime getUltimo_login() {
        return ultimo_login;
    }

    /**
     * @param ultimo_login the ultimo_login to set
     */
    public void setUltimo_login(BsonDateTime ultimo_login) {
        this.ultimo_login = ultimo_login;
    }

    /**
     * @return the salario
     */
    public double getSalario() {
        return salario;
    }

    /**
     * @param salario the salario to set
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }

    /**
     * @return the login_usuario
     */
    public String getLogin_usuario() {
        return login_usuario;
    }

    /**
     * @param login_usuario the login_usuario to set
     */
    public void setLogin_usuario(String login_usuario) {
        this.login_usuario = login_usuario;
    }

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * @return the tipo_usuario
     */
    public String getTipo_usuario() {
        return tipo_usuario;
    }

    /**
     * @param tipo_usuario the tipo_usuario to set
     */
    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return the logradouro
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * @param logradouro the logradouro to set
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the id
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(ObjectId id) {
        this.id = id;
    }
}
