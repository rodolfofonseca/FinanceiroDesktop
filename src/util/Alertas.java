package util;

import javax.swing.*;
import java.awt.*;

/**
 * Classe responsável por modernizar os alertas do sistema.
 *
 * @author Rodolfo Fonseca
 */
public class Alertas {

    /**
     * Mensagem: Operação realizada com sucesso!
     *
     * Apresentada sempre que de sucesso de forma genérica.
     */
    public static void operacaoSucesso() {
        mostrar("Sucesso!", "Operação realizada com sucesso!", new Color(40, 167, 69));
    }

    /**
     * Mensagem: Falha durante o processo!
     *
     * Aprensentada sempre que uma operção apresentar erro
     */
    public static void OperacaoErro() {
        mostrar("Ops!", "Falha durante o processo!", new Color(220, 53, 69));
    }

    public static void sucesso(String mensagem) {
        mostrar("Sucesso!", mensagem, new Color(40, 167, 69));
    }

    public static void erro(String mensagem) {
        mostrar("Ops!", mensagem, new Color(220, 53, 69));
    }

    public static void aviso(String mensagem) {
        mostrar("Atenção!", mensagem, new Color(255, 193, 7));
    }

    private static void mostrar(String titulo, String mensagem, Color cor) {
        JDialog dialog = new JDialog((Frame) null, titulo, true);

        dialog.setUndecorated(true);
        dialog.setSize(380, 250);
        dialog.setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createLineBorder(cor, 2));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        /*JLabel icone = new JLabel(titulo.equals("Sucesso!") ? "✓" : titulo.equals("Ops!") ? "✕" : "!", SwingConstants.CENTER);
        icone.setFont(new Font("Arial", Font.BOLD, 42));
        icone.setForeground(cor);
        icone.setAlignmentX(Component.CENTER_ALIGNMENT);*/
        IconeAlerta.Tipo tipo;

        if (titulo.equals("Sucesso!")) {
            tipo = IconeAlerta.Tipo.SUCESSO;
        } else if (titulo.equals("Ops!")) {
            tipo = IconeAlerta.Tipo.ERRO;
        } else {
            tipo = IconeAlerta.Tipo.AVISO;
        }

        IconeAlerta icone = new IconeAlerta(tipo, cor);
        icone.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 23));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblMensagem = new JLabel("<html><div style='text-align:center;width:290px;'>" + mensagem + "</div></html>");
        lblMensagem.setFont(new Font("Arial", Font.PLAIN, 14));
        lblMensagem.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton botao = new JButton("OK");
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setForeground(Color.WHITE);
        botao.setBackground(cor);
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setPreferredSize(new Dimension(100, 36));
        botao.setMaximumSize(new Dimension(100, 36));
        botao.addActionListener(e -> dialog.dispose());

        painel.add(Box.createVerticalStrut(15));
        painel.add(icone);
        painel.add(Box.createVerticalStrut(5));
        painel.add(lblTitulo);
        painel.add(Box.createVerticalStrut(10));
        painel.add(lblMensagem);
        painel.add(Box.createVerticalStrut(18));
        painel.add(botao);
        painel.add(Box.createVerticalStrut(15));

        dialog.setContentPane(painel);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }
}
