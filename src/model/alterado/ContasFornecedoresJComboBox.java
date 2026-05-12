package model.alterado;

/**
 * Alteração da model para utilização em JComboBox
 * @author RODOLFO
 */
public class ContasFornecedoresJComboBox {
    private String id;
    private String nome_conta;
    
    public ContasFornecedoresJComboBox(String id, String nome){
        this.id = id;
        this.nome_conta = nome;
    }
    
    public String getId(){
        return this.id;
    }
    
    public String getNomeConta(){
        return this.nome_conta;
    }
    
    @Override
    public String toString(){
        return nome_conta;
    }
}
