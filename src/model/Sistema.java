package model;

import org.bson.types.ObjectId;

/**
 * Classe que contém todas as informações pertinentes ao sistema do usuário como
 * configurações e outras informações
 *
 * @author RODOLFO
 */
public class Sistema implements ModelInterface {

    private ObjectId _id;
    private Empresa empresa;
    private String versao_sistema;
    private String anexa_documentos;
    private boolean modulo_contabil;
    private boolean pedidos;
    private boolean cloudinary;
    private boolean google_agenda;
    private String endereco_json_google;
    private String conta_capital_social;
    private String conta_lucros_apropriar;
    private String conta_prejuizos_acumulados;
    private String conta_vendas_a_vista;
    private String conta_vendas_a_prazo;
    private String conta_servicos_a_prazo;
    private String conta_servicos_a_vista;
    private String conta_custo_mercadorias_vendidas;
    private String conta_custo_servicos_prestados;
    private String conta_apuracao_resultado;
    private String versao_sistema_java;

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
     * @return the versao_sistema
     */
    public String getVersao_sistema() {
        return versao_sistema;
    }

    /**
     * @param versao_sistema the versao_sistema to set
     */
    public void setVersao_sistema(String versao_sistema) {
        this.versao_sistema = versao_sistema;
    }

    /**
     * @return the anexa_documentos
     */
    public String getAnexa_documentos() {
        return anexa_documentos;
    }

    /**
     * @param anexa_documentos the anexa_documentos to set
     */
    public void setAnexa_documentos(String anexa_documentos) {
        this.anexa_documentos = anexa_documentos;
    }

    /**
     * @return the modulo_contabil
     */
    public boolean isModulo_contabil() {
        return modulo_contabil;
    }

    /**
     * @param modulo_contabil the modulo_contabil to set
     */
    public void setModulo_contabil(boolean modulo_contabil) {
        this.modulo_contabil = modulo_contabil;
    }

    /**
     * @return the pedidos
     */
    public boolean isPedidos() {
        return pedidos;
    }

    /**
     * @param pedidos the pedidos to set
     */
    public void setPedidos(boolean pedidos) {
        this.pedidos = pedidos;
    }

    /**
     * @return the cloudinary
     */
    public boolean isCloudinary() {
        return cloudinary;
    }

    /**
     * @param cloudinary the cloudinary to set
     */
    public void setCloudinary(boolean cloudinary) {
        this.cloudinary = cloudinary;
    }

    /**
     * @return the google_agenda
     */
    public boolean isGoogle_agenda() {
        return google_agenda;
    }

    /**
     * @param google_agenda the google_agenda to set
     */
    public void setGoogle_agenda(boolean google_agenda) {
        this.google_agenda = google_agenda;
    }

    /**
     * @return the endereco_json_google
     */
    public String getEndereco_json_google() {
        return endereco_json_google;
    }

    /**
     * @param endereco_json_google the endereco_json_google to set
     */
    public void setEndereco_json_google(String endereco_json_google) {
        this.endereco_json_google = endereco_json_google;
    }

    /**
     * @return the conta_capital_social
     */
    public String getConta_capital_social() {
        return conta_capital_social;
    }

    /**
     * @param conta_capital_social the conta_capital_social to set
     */
    public void setConta_capital_social(String conta_capital_social) {
        this.conta_capital_social = conta_capital_social;
    }

    /**
     * @return the conta_lucros_apropriar
     */
    public String getConta_lucros_apropriar() {
        return conta_lucros_apropriar;
    }

    /**
     * @param conta_lucros_apropriar the conta_lucros_apropriar to set
     */
    public void setConta_lucros_apropriar(String conta_lucros_apropriar) {
        this.conta_lucros_apropriar = conta_lucros_apropriar;
    }

    /**
     * @return the conta_prejuizos_acumulados
     */
    public String getConta_prejuizos_acumulados() {
        return conta_prejuizos_acumulados;
    }

    /**
     * @param conta_prejuizos_acumulados the conta_prejuizos_acumulados to set
     */
    public void setConta_prejuizos_acumulados(String conta_prejuizos_acumulados) {
        this.conta_prejuizos_acumulados = conta_prejuizos_acumulados;
    }

    /**
     * @return the conta_vendas_a_vista
     */
    public String getConta_vendas_a_vista() {
        return conta_vendas_a_vista;
    }

    /**
     * @param conta_vendas_a_vista the conta_vendas_a_vista to set
     */
    public void setConta_vendas_a_vista(String conta_vendas_a_vista) {
        this.conta_vendas_a_vista = conta_vendas_a_vista;
    }

    /**
     * @return the conta_vendas_a_prazo
     */
    public String getConta_vendas_a_prazo() {
        return conta_vendas_a_prazo;
    }

    /**
     * @param conta_vendas_a_prazo the conta_vendas_a_prazo to set
     */
    public void setConta_vendas_a_prazo(String conta_vendas_a_prazo) {
        this.conta_vendas_a_prazo = conta_vendas_a_prazo;
    }

    /**
     * @return the conta_servicos_a_prazo
     */
    public String getConta_servicos_a_prazo() {
        return conta_servicos_a_prazo;
    }

    /**
     * @param conta_servicos_a_prazo the conta_servicos_a_prazo to set
     */
    public void setConta_servicos_a_prazo(String conta_servicos_a_prazo) {
        this.conta_servicos_a_prazo = conta_servicos_a_prazo;
    }

    /**
     * @return the conta_servicos_a_vista
     */
    public String getConta_servicos_a_vista() {
        return conta_servicos_a_vista;
    }

    /**
     * @param conta_servicos_a_vista the conta_servicos_a_vista to set
     */
    public void setConta_servicos_a_vista(String conta_servicos_a_vista) {
        this.conta_servicos_a_vista = conta_servicos_a_vista;
    }

    /**
     * @return the conta_custo_mercadorias_vendidas
     */
    public String getConta_custo_mercadorias_vendidas() {
        return conta_custo_mercadorias_vendidas;
    }

    /**
     * @param conta_custo_mercadorias_vendidas the
     * conta_custo_mercadorias_vendidas to set
     */
    public void setConta_custo_mercadorias_vendidas(String conta_custo_mercadorias_vendidas) {
        this.conta_custo_mercadorias_vendidas = conta_custo_mercadorias_vendidas;
    }

    /**
     * @return the conta_apuracao_resultado
     */
    public String getConta_apuracao_resultado() {
        return conta_apuracao_resultado;
    }

    /**
     * @param conta_apuracao_resultado the conta_apuracao_resultado to set
     */
    public void setConta_apuracao_resultado(String conta_apuracao_resultado) {
        this.conta_apuracao_resultado = conta_apuracao_resultado;
    }

    /**
     * @return the versao_sistema_java
     */
    public String getVersao_sistema_java() {
        return versao_sistema_java;
    }

    /**
     * @param versao_sistema_java the versao_sistema_java to set
     */
    public void setVersao_sistema_java(String versao_sistema_java) {
        this.versao_sistema_java = versao_sistema_java;
    }

    @Override
    public String getTabela() {
        return "sistema";
    }

    /**
     * @return the conta_custo_servicos_prestados
     */
    public String getConta_custo_servicos_prestados() {
        return conta_custo_servicos_prestados;
    }

    /**
     * @param conta_custo_servicos_prestados the conta_custo_servicos_prestados to set
     */
    public void setConta_custo_servicos_prestados(String conta_custo_servicos_prestados) {
        this.conta_custo_servicos_prestados = conta_custo_servicos_prestados;
    }

}
