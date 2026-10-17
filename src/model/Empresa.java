package model;

import java.sql.Timestamp;

/**
 * Classe contendo os dados da empresa
 * @author RodolfoFonseca
 */
public class Empresa implements ModelInterface{
    private int codigo_empresa;
    private String nome_empresa;
    private String cpf_cnpj;
    private Timestamp data_cadastro;
    private String ciade;
    private String logo;
    private String telefone;
    private boolean status_empresa;
    
    @Override
    public String getTabela() {
        return "empresa";
    }

    /**
     * @return the codigo_empresa
     */
    public int getCodigo_empresa() {
        return codigo_empresa;
    }

    /**
     * @param codigo_empresa the codigo_empresa to set
     */
    public void setCodigo_empresa(int codigo_empresa) {
        this.codigo_empresa = codigo_empresa;
    }

    /**
     * @return the nome_empresa
     */
    public String getNome_empresa() {
        return nome_empresa;
    }

    /**
     * @param nome_empresa the nome_empresa to set
     */
    public void setNome_empresa(String nome_empresa) {
        this.nome_empresa = nome_empresa;
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
     * @return the ciade
     */
    public String getCiade() {
        return ciade;
    }

    /**
     * @param ciade the ciade to set
     */
    public void setCiade(String ciade) {
        this.ciade = ciade;
    }

    /**
     * @return the logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     * @param logo the logo to set
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the status_empresa
     */
    public boolean isStatus_empresa() {
        return status_empresa;
    }

    /**
     * @param status_empresa the status_empresa to set
     */
    public void setStatus_empresa(boolean status_empresa) {
        this.status_empresa = status_empresa;
    }
    
}
