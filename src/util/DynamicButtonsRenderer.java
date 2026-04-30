package util;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;

public class DynamicButtonsRenderer extends JPanel implements TableCellRenderer {

    public DynamicButtonsRenderer(List<ActionButton> botoes) {

        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        for (ActionButton b : botoes) {
            JButton btn = new JButton();

            btn.setIcon(new ImageIcon(getClass().getResource(b.icone)));
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);

            add(btn);
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        return this;
    }
}