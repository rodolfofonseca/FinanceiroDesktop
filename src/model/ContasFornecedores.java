package model;

import org.bson.BsonDateTime;
import org.bson.types.ObjectId;

/**
 * Classe que vincula os fornecedores as suas contas cadastradas
 * @author RODOLFO
 */
public class ContasFornecedores implements ModelInterface{
    private ObjectId _id;
    private Empresa empresa;
    private Usuarios fornecedor;
    private String nome_conta;
    private String descricao_conta;
    private boolean status_conta;
    private BsonDateTime data_cadastro;

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
     * @return the fornecedor
     */
    public Usuarios getFornecedor() {
        return fornecedor;
    }

    /**
     * @param fornecedor the fornecedor to set
     */
    public void setFornecedor(Usuarios fornecedor) {
        this.fornecedor = fornecedor;
    }

    /**
     * @return the nome_conta
     */
    public String getNome_conta() {
        return nome_conta;
    }

    /**
     * @param nome_conta the nome_conta to set
     */
    public void setNome_conta(String nome_conta) {
        this.nome_conta = nome_conta;
    }

    /**
     * @return the descricao_conta
     */
    public String getDescricao_conta() {
        return descricao_conta;
    }

    /**
     * @param descricao_conta the descricao_conta to set
     */
    public void setDescricao_conta(String descricao_conta) {
        this.descricao_conta = descricao_conta;
    }

    /**
     * @return the status_conta
     */
    public boolean isStatus_conta() {
        return status_conta;
    }

    /**
     * @param status_conta the status_conta to set
     */
    public void setStatus_conta(boolean status_conta) {
        this.status_conta = status_conta;
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

    @Override
    public String getTabela() {
    return "contas_fornecedores";    
    }

    /**
     * @return the _id
     */
    public ObjectId getId() {
        return _id;
    }

    /**
     * @param _id the _id to set
     */
    public void setId(ObjectId _id) {
        this._id = _id;
    }    
}
