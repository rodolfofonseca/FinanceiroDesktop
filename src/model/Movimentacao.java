package model;
import java.sql.Timestamp;
/**
 * Classe que contém o modelo do objeto movimentação no banco de dados
 * @author RodolfoFonseca
 */
public class Movimentacao {
    private int codigoMovimentacao;
    private Empresa empresa;
    private Contas conta;
    private String descricao;
    private float valorLancamento;
    private Timestamp dataLancamento;
    private boolean tipoLancamento;
    private boolean comprovante;
    private boolean transferencia;
    
    private String tipoLancamentoPesq;
    private Timestamp dataInicialPesq;
    private Timestamp dataFinalPesq;
    /**
     * @return the codigoMovimentacao
     */
    public int getCodigoMovimentacao() {
        return codigoMovimentacao;
    }

    /**
     * @param codigoMovimentacao the codigoMovimentacao to set
     */
    public void setCodigoMovimentacao(int codigoMovimentacao) {
        this.codigoMovimentacao = codigoMovimentacao;
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
     * @return the conta
     */
    public Contas getConta() {
        return conta;
    }

    /**
     * @param conta the conta to set
     */
    public void setConta(Contas conta) {
        this.conta = conta;
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
     * @return the valorLancamento
     */
    public float getValorLancamento() {
        return valorLancamento;
    }

    /**
     * @param valorLancamento the valorLancamento to set
     */
    public void setValorLancamento(float valorLancamento) {
        this.valorLancamento = valorLancamento;
    }

    /**
     * @return the dataLancamento
     */
    public Timestamp getDataLancamento() {
        return dataLancamento;
    }

    /**
     * @param dataLancamento the dataLancamento to set
     */
    public void setDataLancamento(Timestamp dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    /**
     * @return the tipoLancamento
     */
    public boolean isTipoLancamento() {
        return tipoLancamento;
    }

    /**
     * @param tipoLancamento the tipoLancamento to set
     */
    public void setTipoLancamento(boolean tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }

    /**
     * @return the comprovante
     */
    public boolean isComprovante() {
        return comprovante;
    }

    /**
     * @param comprovante the comprovante to set
     */
    public void setComprovante(boolean comprovante) {
        this.comprovante = comprovante;
    }

    /**
     * @return the transferencia
     */
    public boolean isTransferencia() {
        return transferencia;
    }

    /**
     * @param transferencia the transferencia to set
     */
    public void setTransferencia(boolean transferencia) {
        this.transferencia = transferencia;
    }

    /**
     * @return the tipoLancamentoPesq
     */
    public String getTipoLancamentoPesq() {
        return tipoLancamentoPesq;
    }

    /**
     * @param tipoLancamentoPesq the tipoLancamentoPesq to set
     */
    public void setTipoLancamentoPesq(String tipoLancamentoPesq) {
        this.tipoLancamentoPesq = tipoLancamentoPesq;
    }

    /**
     * @return the dataInicialPesq
     */
    public Timestamp getDataInicialPesq() {
        return dataInicialPesq;
    }

    /**
     * @param dataInicialPesq the dataInicialPesq to set
     */
    public void setDataInicialPesq(Timestamp dataInicialPesq) {
        this.dataInicialPesq = dataInicialPesq;
    }

    /**
     * @return the dataFinalPesq
     */
    public Timestamp getDataFinalPesq() {
        return dataFinalPesq;
    }

    /**
     * @param dataFinalPesq the dataFinalPesq to set
     */
    public void setDataFinalPesq(Timestamp dataFinalPesq) {
        this.dataFinalPesq = dataFinalPesq;
    }
    
}
