
package util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class LoadingDialog {

    private static JDialog dialog;
    private static JLabel label;
    private static JProgressBar progressBar;
    private static IconeCarregamento iconeCarregamento;

    private LoadingDialog() {
    }

    public static void mostrar(Window parent, String mensagem) {
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(
                () -> mostrar(parent, mensagem)
            );
            return;
        }

        if (dialog != null && dialog.isVisible()) {
            atualizarMensagem(mensagem);
            return;
        }

        dialog = new JDialog(
            parent,
            "Aguarde",
            Dialog.ModalityType.MODELESS
        );

        dialog.setUndecorated(true);
        dialog.setDefaultCloseOperation(
            JDialog.DO_NOTHING_ON_CLOSE
        );
        dialog.setResizable(false);

        // Cartão branco com bordas arredondadas
        JPanel painel = new JPanel(new BorderLayout(0, 12)) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
                );

                g2.setColor(Color.WHITE);
                g2.fillRoundRect(
                    0, 0, getWidth(), getHeight(), 24, 24
                );

                g2.dispose();
                super.paintComponent(g);
            }
        };

        painel.setOpaque(false);
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                new Color(235, 237, 240), 1
            ),
            BorderFactory.createEmptyBorder(
                25, 30, 25, 30
            )
        ));

        // Ícone de carregamento
       /* JLabel icone = new JLabel(
            "\u27F3",
            SwingConstants.CENTER
        );

        icone.setFont(new Font("Arial", Font.PLAIN, 48));
        icone.setForeground(new Color(48, 133, 214));*/
       IconeCarregamento icone = new IconeCarregamento();
iconeCarregamento = icone;
icone.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        // Título
        JLabel titulo = new JLabel(
            "Aguarde...",
            SwingConstants.CENTER
        );

        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(new Color(84, 84, 84));

        // Mensagem
        label = new JLabel(
            mensagem,
            SwingConstants.CENTER
        );

        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(new Color(110, 110, 110));

        // Barra animada, sem porcentagem fictícia
        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setBorderPainted(false);
        progressBar.setOpaque(false);
        progressBar.setForeground(
            new Color(48, 133, 214)
        );
        progressBar.setPreferredSize(
            new Dimension(280, 6)
        );

        JPanel topo = new JPanel(new BorderLayout(0, 5));
        topo.setOpaque(false);
        topo.add(icone, BorderLayout.NORTH);
        topo.add(titulo, BorderLayout.CENTER);

        painel.add(topo, BorderLayout.NORTH);
        painel.add(label, BorderLayout.CENTER);
        painel.add(progressBar, BorderLayout.SOUTH);

        dialog.setContentPane(painel);
        dialog.pack();
        dialog.setSize(360, 205);
        dialog.setLocationRelativeTo(parent);

        dialog.setVisible(true);
        dialog.toFront();
    }

    public static void atualizarMensagem(String mensagem) {
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(
                () -> atualizarMensagem(mensagem)
            );
            return;
        }

        if (label != null) {
            label.setText(mensagem);
        }
    }

    public static void atualizarProgresso(int valor) {
        // Não há porcentagem real disponível durante a requisição.
        // A barra permanece animada até o retorno da API.
    }

   /* public static void fechar() {
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(
                LoadingDialog::fechar
            );
            return;
        }

        if (dialog != null) {
            dialog.dispose();
        }

        dialog = null;
        label = null;
        progressBar = null;
    }*/
    
public static void fechar() {
    if (!SwingUtilities.isEventDispatchThread()) {
        SwingUtilities.invokeLater(
            LoadingDialog::fechar
        );
        return;
    }

    if (iconeCarregamento != null) {
        iconeCarregamento.parar();
        iconeCarregamento = null;
    }

    if (dialog != null) {
        dialog.dispose();
    }

    dialog = null;
    label = null;
    progressBar = null;
}

}
