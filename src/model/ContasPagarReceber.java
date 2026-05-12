package model;

import org.bson.BsonDateTime;
import org.bson.types.ObjectId;

/**
 * Classe responsável por apresentas as contas que o usuário tem que pagar e receber
 * @author RODOLFO
 */
public class ContasPagarReceber implements ModelInterface{
    private ObjectId _id;
    private Empresa empresa;
    private Usuarios fornecedor;
    private ContasFornecedores conta_forneceres;
    private String nomeConta;
    private String descricao;
    private double valor_conta;
    private double valor_pago;
    private double valor_juro_desconto;
    private String tipo_juro_desconto;
    private String tipo_conta;
    private BsonDateTime data_cadastro;
    private BsonDateTime data_vencimento;
    private BsonDateTime data_baixa;
    private String status_conta;
    private String comprovante;
    private String boleto;
    private String transacao;
    
    @Override
    public String getTabela() {
        return "contas_pagar_receber";
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
     * @return the conta_forneceres
     */
    public ContasFornecedores getConta_forneceres() {
        return conta_forneceres;
    }

    /**
     * @param conta_forneceres the conta_forneceres to set
     */
    public void setConta_forneceres(ContasFornecedores conta_forneceres) {
        this.conta_forneceres = conta_forneceres;
    }

    /**
     * @return the nomeConta
     */
    public String getNomeConta() {
        return nomeConta;
    }

    /**
     * @param nomeConta the nomeConta to set
     */
    public void setNomeConta(String nomeConta) {
        this.nomeConta = nomeConta;
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
     * @return the valor_conta
     */
    public double getValor_conta() {
        return valor_conta;
    }

    /**
     * @param valor_conta the valor_conta to set
     */
    public void setValor_conta(double valor_conta) {
        this.valor_conta = valor_conta;
    }

    /**
     * @return the valor_pago
     */
    public double getValor_pago() {
        return valor_pago;
    }

    /**
     * @param valor_pago the valor_pago to set
     */
    public void setValor_pago(double valor_pago) {
        this.valor_pago = valor_pago;
    }

    /**
     * @return the valor_juro_desconto
     */
    public double getValor_juro_desconto() {
        return valor_juro_desconto;
    }

    /**
     * @param valor_juro_desconto the valor_juro_desconto to set
     */
    public void setValor_juro_desconto(double valor_juro_desconto) {
        this.valor_juro_desconto = valor_juro_desconto;
    }

    /**
     * @return the tipo_conta
     */
    public String getTipo_conta() {
        return tipo_conta;
    }

    /**
     * @param tipo_conta the tipo_conta to set
     */
    public void setTipo_conta(String tipo_conta) {
        this.tipo_conta = tipo_conta;
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
     * @return the data_vencimento
     */
    public BsonDateTime getData_vencimento() {
        return data_vencimento;
    }

    /**
     * @param data_vencimento the data_vencimento to set
     */
    public void setData_vencimento(BsonDateTime data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    /**
     * @return the data_baixa
     */
    public BsonDateTime getData_baixa() {
        return data_baixa;
    }

    /**
     * @param data_baixa the data_baixa to set
     */
    public void setData_baixa(BsonDateTime data_baixa) {
        this.data_baixa = data_baixa;
    }

    /**
     * @return the status_conta
     */
    public String getStatus_conta() {
        return status_conta;
    }

    /**
     * @param status_conta the status_conta to set
     */
    public void setStatus_conta(String status_conta) {
        this.status_conta = status_conta;
    }

    /**
     * @return the comprovante
     */
    public String getComprovante() {
        return comprovante;
    }

    /**
     * @param comprovante the comprovante to set
     */
    public void setComprovante(String comprovante) {
        this.comprovante = comprovante;
    }

    /**
     * @return the boleto
     */
    public String getBoleto() {
        return boleto;
    }

    /**
     * @param boleto the boleto to set
     */
    public void setBoleto(String boleto) {
        this.boleto = boleto;
    }

    /**
     * @return the transacao
     */
    public String getTransacao() {
        return transacao;
    }

    /**
     * @param transacao the transacao to set
     */
    public void setTransacao(String transacao) {
        this.transacao = transacao;
    }

    /**
     * @return the tipo_juro_desconto
     */
    public String getTipo_juro_desconto() {
        return tipo_juro_desconto;
    }

    /**
     * @param tipo_juro_desconto the tipo_juro_desconto to set
     */
    public void setTipo_juro_desconto(String tipo_juro_desconto) {
        this.tipo_juro_desconto = tipo_juro_desconto;
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
