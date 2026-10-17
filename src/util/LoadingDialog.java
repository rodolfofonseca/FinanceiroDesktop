package util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class LoadingDialog {

    private static JDialog dialog;
    private static JLabel label;
    private static JLabel porcentagemLabel;
    private static JProgressBar progressBar;

    private LoadingDialog() {
    }

    public static void mostrar(Window parent, String mensagem) {

        if (dialog != null && dialog.isVisible()) {

            atualizarMensagem(mensagem);

            return;
        }

        dialog = new JDialog(
                parent,
                "Aguarde",
                Dialog.ModalityType.MODELESS
        );

        dialog.setDefaultCloseOperation(
                JDialog.DO_NOTHING_ON_CLOSE
        );

        dialog.setResizable(false);

        label = new JLabel(
                mensagem,
                SwingConstants.CENTER
        );

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        label.setForeground(
                new Color(60, 60, 60)
        );

        label.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 20, 10, 20
                )
        );

        progressBar = new JProgressBar();

        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setValue(0);

        progressBar.setForeground(
                new Color(40, 167, 69)
        );

        progressBar.setBackground(
                new Color(230, 230, 230)
        );

        progressBar.setOpaque(true);

        progressBar.setUI(
                new BasicProgressBarUI()
        );

        progressBar.setPreferredSize(
                new Dimension(300, 22)
        );

        progressBar.setStringPainted(false);

        porcentagemLabel = new JLabel(
                "0%",
                SwingConstants.CENTER
        );

        porcentagemLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        porcentagemLabel.setForeground(
                new Color(40, 167, 69)
        );

        porcentagemLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        5, 0, 10, 0
                )
        );

        JPanel painelProgresso = new JPanel(
                new BorderLayout()
        );

        painelProgresso.setBackground(
                Color.WHITE
        );

        painelProgresso.setBorder(
                BorderFactory.createEmptyBorder(
                        0, 20, 0, 20
                )
        );

        painelProgresso.add(
                progressBar,
                BorderLayout.CENTER
        );

        JPanel painelPrincipal = new JPanel(
                new BorderLayout()
        );

        painelPrincipal.setBackground(
                Color.WHITE
        );

        painelPrincipal.add(
                label,
                BorderLayout.NORTH
        );

        painelPrincipal.add(
                painelProgresso,
                BorderLayout.CENTER
        );

        painelPrincipal.add(
                porcentagemLabel,
                BorderLayout.SOUTH
        );

        dialog.setContentPane(
                painelPrincipal
        );

        dialog.pack();

        dialog.setMinimumSize(
                new Dimension(360, 130)
        );

        dialog.setLocationRelativeTo(parent);

        dialog.setVisible(true);
    }

    public static void atualizarProgresso(int valor) {

        final int progresso;

        if (valor < 0) {
            progresso = 0;
        } else if (valor > 100) {
            progresso = 100;
        } else {
            progresso = valor;
        }

        SwingUtilities.invokeLater(() -> {

            if (progressBar != null) {

                progressBar.setValue(progresso);

                if (porcentagemLabel != null) {

                    porcentagemLabel.setText(
                            progresso + "%"
                    );
                }

                progressBar.revalidate();
                progressBar.repaint();

                if (porcentagemLabel != null) {
                    porcentagemLabel.repaint();
                }
            }
        });
    }

    public static void atualizarMensagem(String mensagem) {

        SwingUtilities.invokeLater(() -> {

            if (label != null) {

                label.setText(
                        mensagem
                );

                label.revalidate();
                label.repaint();
            }
        });
    }

    public static void fechar() {

        SwingUtilities.invokeLater(() -> {

            if (dialog != null) {

                dialog.dispose();

                dialog = null;
                label = null;
                porcentagemLabel = null;
                progressBar = null;
            }
        });
    }
}
