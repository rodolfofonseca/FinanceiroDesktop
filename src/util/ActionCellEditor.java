/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author RODOLFO
 */
public class ActionCellEditor extends AbstractCellEditor implements TableCellEditor {

    private JPanel panel;

    public ActionCellEditor(List<ActionButton> actions, JTable table) {
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));

        for (ActionButton a : actions) {
            ModernButton btn = new ModernButton(a.nome, a.icone, a.cor, a.hover);

            btn.addActionListener(e -> {
                int row = table.getSelectedRow();
                String id = table.getValueAt(row, 0).toString();
                a.action.accept(id, table);
            });

            panel.add(btn);
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }
}
