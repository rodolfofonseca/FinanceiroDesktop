package model;

import org.bson.BsonDateTime;
import org.bson.types.ObjectId;

/**
 *
 * @author RODOLFO
 */
public class Conta implements ModelInterface{
    private ObjectId _id;
    private Empresa empresa;
    private String nome_conta;
    private String descricao;
    private double saldo_conta;
    private String status;
    private BsonDateTime data_cadastro;
    
    @Override
    public String getTabela() {
        return "contas";
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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the saldo_conta
     */
    public double getSaldo_conta() {
        return saldo_conta;
    }

    /**
     * @param saldo_conta the saldo_conta to set
     */
    public void setSaldo_conta(double saldo_conta) {
        this.saldo_conta = saldo_conta;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
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
    
}
