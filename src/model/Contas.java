package model;
import java.sql.Timestamp;
/**
 * Classe que contém todas as informções das contas bancárias.
 * @author RodolfoFonseca
 */
public class Contas {
    private int codigo_conta;
    private Empresa empresa;
    private String nome_conta;
    private String descricao;
    private float saldo_conta;
    private boolean status;
    private Timestamp data_cadastro;

    /**
     * @return the codigo_conta
     */
    public int getCodigo_conta() {
        return codigo_conta;
    }

    /**
     * @param codigo_conta the codigo_conta to set
     */
    public void setCodigo_conta(int codigo_conta) {
        this.codigo_conta = codigo_conta;
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
    public float getSaldo_conta() {
        return saldo_conta;
    }

    /**
     * @param saldo_conta the saldo_conta to set
     */
    public void setSaldo_conta(float saldo_conta) {
        this.saldo_conta = saldo_conta;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
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
    
    @Override
    public String toString(){
    return nome_conta;
    }
    
}
