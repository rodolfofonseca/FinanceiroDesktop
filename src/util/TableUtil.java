package util;

import java.awt.Color;
import java.awt.Component;
import java.util.LinkedHashSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;

import java.util.List;
import java.util.Set;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class TableUtil {

    /**
     * Função responsável por colocar os campos de acordo com a lista que é
     * passada
     *
     * @param tabela
     * @param dados
     */
    public static void preencherTabela(JTable tabela, List<Document> dados) {

        DefaultTableModel model = new DefaultTableModel();

        if (dados == null || dados.isEmpty()) {
            tabela.setModel(model);
            return;
        }

        Set<String> colunas = new LinkedHashSet<>(dados.get(0).keySet());
        colunas.remove("_id");
        model.addColumn("_id");

        for (String col : colunas) {
            model.addColumn(col);
        }

        for (Document doc : dados) {

            Object[] linha = new Object[colunas.size() + 1];
            int i = 0;

            linha[i++] = doc.get("_id").toString();
            for (String col : colunas) {
                linha[i++] = doc.get(col);
            }

            model.addRow(linha);
        }

        tabela.setModel(model);
        tabela.getColumnModel().getColumn(0).setMinWidth(0);
        tabela.getColumnModel().getColumn(0).setMaxWidth(0);
        tabela.getColumnModel().getColumn(0).setWidth(0);
    }

    /**
     * Função responsável por redimensionar as colunas de acordo com o tamanho
     * do texto que está dentro dela
     *
     * @param tabela
     */
    public static void ajustarLarguraColunas(JTable tabela) {

        for (int coluna = 0; coluna < tabela.getColumnCount(); coluna++) {
            TableColumn tableColumn = tabela.getColumnModel().getColumn(coluna);
            int largura = 50;

            for (int linha = 0; linha < tabela.getRowCount(); linha++) {

                DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabela.getCellRenderer(linha, coluna);

                Object valor = tabela.getValueAt(linha, coluna);

                int larguraCelula = renderer
                        .getTableCellRendererComponent(tabela, valor, false, false, linha, coluna).getPreferredSize().width;

                largura = Math.max(largura, larguraCelula + 10);
            }

            tableColumn.setPreferredWidth(largura);
        }
    }

    /**
     * Função responsável por pintar a linha da tabela de acordo com o status
     * que é passado
     *
     * @param tabela
     * @param nomeColunaStatus
     */
    public static void colorirLinhasPorStatus(JTable tabela, String nomeColunaStatus) {
        tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (isSelected) {
                    return c;
                }

                try {
                    int colunaStatus = table.getColumnModel().getColumnIndex(nomeColunaStatus);

                    Object valorStatus = table.getValueAt(row, colunaStatus);

                    if (valorStatus != null) {
                        String status = valorStatus.toString();
                        if (status.equalsIgnoreCase("ATIVO") || status.equalsIgnoreCase("true")) {
                            c.setBackground(Color.decode("#CCFFCC"));
                        } else if (status.equalsIgnoreCase("INATIVO") || status.equalsIgnoreCase("false")) {
                            c.setBackground(Color.decode("#FA8072"));
                        } else if (status.equalsIgnoreCase("Administrador")) {
                            c.setBackground(Color.decode("#E2B93B"));
                        } else if (status.equalsIgnoreCase("CLIENTE")) {
                            c.setBackground(Color.decode("#768A9C"));
                        } else if (status.equalsIgnoreCase("FORNECEDOR")) {
                            c.setBackground(Color.decode("#DDD1FF"));
                        } else {
                            c.setBackground(Color.WHITE);
                        }
                    } else {
                        c.setBackground(Color.WHITE);
                    }
                } catch (Exception e) {
                    c.setBackground(Color.WHITE);
                }
                return c;
            }
        });
    }

    /**
     * Função responsável por analisar a tabela e pintar de acordo com as
     * colunas
     *
     * @param tabela
     * @param nomeColunaTipo
     * @param nomeColunaStatus
     */
    public static void colorirLinhasPorStatus(JTable tabela, String nomeColunaTipo, String nomeColunaStatus) {
        tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (isSelected) {
                    return c;
                }

                try {
                    int colunaTipo = table.getColumnModel().getColumnIndex(nomeColunaTipo);
                    int colunaStatus = table.getColumnModel().getColumnIndex(nomeColunaStatus);

                    Object valorTipo = table.getValueAt(row, colunaTipo);
                    Object valorStatus = table.getValueAt(row, colunaStatus);

                    c.setBackground(Color.WHITE);

                    if (valorTipo != null && valorStatus != null) {

                        String tipo = valorTipo.toString().toUpperCase();
                        String status = valorStatus.toString().toUpperCase();

                        if (tipo.equals("PAGAR")) {
                            if (status.equals("AGUARDANDO")) {
                                c.setBackground(Color.decode("#DDD1FF"));
                            } else if (status.equals("PAGO")) {
                                c.setBackground(Color.decode("#CCFFCC"));
                            } else if (status.equals("CANCELADO")) {
                                c.setBackground(Color.decode("#E2B93B"));
                            } else if (status.equals("VENCIDO") || status.equals("VENCIDA")) {
                                c.setBackground(Color.decode("#FA8072"));
                            }

                        } else if (tipo.equals("RECEBER")) {

                            if (status.equals("AGUARDANDO")) {
                                c.setBackground(Color.decode("#DDD1FF"));
                            } else if (status.equals("PAGO")) {
                                c.setBackground(Color.decode("#CCFFCC"));
                            } else if (status.equals("CANCELADO")) {
                                c.setBackground(Color.decode("#E2B93B"));
                            } else if (status.equals("VENCIDO") || status.equals("VENCIDA")) {
                                c.setBackground(Color.decode("#FA8072"));
                            }
                        }
                    }
                } catch (Exception e) {
                    c.setBackground(Color.WHITE);
                }
                return c;
            }
        });
    }
}
