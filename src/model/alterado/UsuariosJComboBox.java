package model.alterado;

/**
 * Alteração de model para utilização em objetos específicos
 * @author RODOLFO
 */
public class UsuariosJComboBox {
    private String id;
    private String nome;
    
    public UsuariosJComboBox(String id, String nome){
        this.id = id;
        this.nome = nome;
    }
    
    public String getId(){
        return this.id;
    }
    
     @Override
    public String toString() {
        return nome;
    }
}
