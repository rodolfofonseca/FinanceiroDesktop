package util;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

/**
 *
 * @author RODOLFO
 */
public class ActionCellRenderer extends JPanel implements TableCellRenderer {

    public ActionCellRenderer(List<ActionButton> actions) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        setOpaque(true);

        for (ActionButton a : actions) {
            add(new ModernButton(a.nome, a.icone, a.cor, a.hover));
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int column) {
        setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);
        return this;
    }
}
