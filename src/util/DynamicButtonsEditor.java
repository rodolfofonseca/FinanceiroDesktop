package util;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.List;

public class DynamicButtonsEditor extends AbstractCellEditor implements TableCellEditor {

    private JPanel panel = new JPanel();
    private JTable table;

    // 👉 CONSTRUTOR CORRETO
    public DynamicButtonsEditor(List<ActionButton> botoes) {

        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        for (ActionButton b : botoes) {

            JButton btn = new JButton();
            btn.setIcon(new ImageIcon(getClass().getResource(b.icone)));
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);

            // ✔ versão sem lambda (evita erro)
            btn.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {

                    int row = table.getSelectedRow();
                    String id = table.getValueAt(row, 0).toString();

                    if (b.action != null) {
                        b.action.accept(id, table);
                    }

                    fireEditingStopped();
                }
            });

            panel.add(btn);
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        this.table = table;
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return "";
    }
}