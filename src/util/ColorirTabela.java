package util;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * Classe responsável por pintar a celula da tabela de acordo com o valor
 * boolean que vem
 *
 * @author RodolfoFonseca
 */
public class ColorirTabela {

    public static void colorirLinhaPorBoolean(
            JTable tabela, int colunaStatus) {

        for (int coluna = 0; coluna < tabela.getColumnCount(); coluna++) {

            final int indiceColunaStatus = colunaStatus;

            tabela.getColumnModel().getColumn(coluna).setCellRenderer(
                new DefaultTableCellRenderer() {

                    @Override
                    public Component getTableCellRendererComponent(
                            JTable tabela,
                            Object valor,
                            boolean selecionado,
                            boolean foco,
                            int linha,
                            int colunaAtual) {

                        Component componente =
                            super.getTableCellRendererComponent(
                                tabela, valor, selecionado,
                                foco, linha, colunaAtual
                            );

                        int linhaModelo =
                            tabela.convertRowIndexToModel(linha);

                        Object status = tabela.getModel().getValueAt(
                            linhaModelo, indiceColunaStatus
                        );

                        if (!selecionado) {
                            if (Boolean.TRUE.equals(status)) {
                                componente.setBackground(
                                    new Color(198, 239, 206)
                                );
                                componente.setForeground(
                                    new Color(0, 97, 0)
                                );
                            } else {
                                componente.setBackground(
                                    new Color(255, 199, 206)
                                );
                                componente.setForeground(
                                    new Color(156, 0, 6)
                                );
                            }
                        }

                        return componente;
                    }
                }
            );
        }
    }
}
