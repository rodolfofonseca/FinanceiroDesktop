package model;

import java.sql.Timestamp;

/**
 * Classe contendo todos os dados do usuário.
 *
 * @author RodolfoFonseca
 */
public class Usuario implements ModelInterface {
    private int codigo_usuario;
    private Empresa empresa;
    private String nome_usuairo;
    private String email_usuario;
    private String senha_usuario;
    private Timestamp data_cadastro;
    private Timestamp ultimo_login;
    private float salario;
    private String cargo;
    private String tipo_usuario;
    private String celular;
    private String cep;
    private String logradouro;
    private String numero;
    private String estado;
    private String cpf_cnpj;
    private boolean status_usuario;
            
    @Override
    public String getTabela() {
        return "usuario";
    }

    /**
     * @return the codigo_usuario
     */
    public int getCodigo_usuario() {
        return codigo_usuario;
    }

    /**
     * @param codigo_usuario the codigo_usuario to set
     */
    public void setCodigo_usuario(int codigo_usuario) {
        this.codigo_usuario = codigo_usuario;
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
     * @return the nome_usuairo
     */
    public String getNome_usuairo() {
        return nome_usuairo;
    }

    /**
     * @param nome_usuairo the nome_usuairo to set
     */
    public void setNome_usuairo(String nome_usuairo) {
        this.nome_usuairo = nome_usuairo;
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
    public Timestamp getData_cadastro() {
        return data_cadastro;
    }

    /**
     * @param data_cadastro the data_cadastro to set
     */
    public void setData_cadastro(Timestamp data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    /**
     * @return the ultimo_login
     */
    public Timestamp getUltimo_login() {
        return ultimo_login;
    }

    /**
     * @param ultimo_login the ultimo_login to set
     */
    public void setUltimo_login(Timestamp ultimo_login) {
        this.ultimo_login = ultimo_login;
    }

    /**
     * @return the salario
     */
    public float getSalario() {
        return salario;
    }

    /**
     * @param salario the salario to set
     */
    public void setSalario(float salario) {
        this.salario = salario;
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
     * @return the cpf_cnpj
     */
    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    /**
     * @param cpf_cnpj the cpf_cnpj to set
     */
    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    /**
     * @return the status_usuario
     */
    public boolean isStatus_usuario() {
        return status_usuario;
    }

    /**
     * @param status_usuario the status_usuario to set
     */
    public void setStatus_usuario(boolean status_usuario) {
        this.status_usuario = status_usuario;
    }

}
