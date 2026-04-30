package util;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.*;

public class ButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private String id;
    private boolean clicked;
    private JTable table;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);

        button = new JButton();
        button.setOpaque(true);

        button.addActionListener(e -> fireEditingStopped());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {

        this.table = table;

        // pega o ID escondido (coluna 0)
        id = table.getValueAt(row, 0).toString();

        button.setText("Excluir");

        clicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {

        if (clicked) {
            System.out.println("Deletar ID: " + id);

            // 👉 aqui você chama seu delete
            // Model.deletePorId("clientes", id);

            JOptionPane.showMessageDialog(button, "Deletado ID: " + id);
        }

        clicked = false;
        return "Excluir";
    }

    @Override
    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }
}