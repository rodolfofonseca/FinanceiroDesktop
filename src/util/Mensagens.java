package util;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author RODOLFO
 */
public class Mensagens {
    public static Component parentComponent = null;
    
    /**
     * Mensagem de sucesso, apresentada sempre que alguma operação retorna sucesso.
     */
    public static void Sucesso(){
        JOptionPane.showMessageDialog(parentComponent, "Operação realizada com sucesso!", "Operação realizada com sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Mensagem genérica de erro.
     */
    public static void Erro(){
        JOptionPane.showMessageDialog(parentComponent, "Falha durante o processo! Por favor tente mais tarde", "Erro durante o processo", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Mensagem de erro que é aprensetada ao usuário
     * @param ex 
     */
    public static void ErroException(Exception ex){
        JOptionPane.showMessageDialog(parentComponent, ex.getMessage(), "Erro durante o processo", JOptionPane.ERROR_MESSAGE);
        System.err.println(ex.getMessage());
    }
    
    /**
     * Mensagem que é apresentada quando é necessário apresentar mensagens de validação de sistema.
     * @param nome 
     */
    public static void Validacao(String nome){
        JOptionPane.showMessageDialog(parentComponent, "O campo "+nome+" não pode ser vazio!", "Atenção", JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * Mensagem que é apresentada quando não encontra nenhuma informação com o filtro informado.
     */
    public static void DadosNaoEncontrados(){
        JOptionPane.showMessageDialog(parentComponent, "Não foram encontrado informações com os filtros passados!", "Selecione outro filtro", JOptionPane.WARNING_MESSAGE);
    }
}
