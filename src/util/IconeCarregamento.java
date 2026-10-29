package util;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 * Classe que monta o icone de carregando
 * @author RodolfoFonseca
 */
public class IconeCarregamento extends JPanel{
    private int angulo = 0;
    private final Timer timer;

    public IconeCarregamento() {
        setOpaque(false);
        setPreferredSize(new Dimension(60, 60));
        setMinimumSize(new Dimension(60, 60));
        setMaximumSize(new Dimension(60, 60));

        timer = new Timer(40, (ActionEvent e) -> {
            angulo = (angulo + 12) % 360;
            repaint();
        });

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(new Color(48, 133, 214));

        g2.setStroke(new BasicStroke(
            5f,
            BasicStroke.CAP_ROUND,
            BasicStroke.JOIN_ROUND
        ));

        int tamanho = 38;
        int x = (getWidth() - tamanho) / 2;
        int y = (getHeight() - tamanho) / 2;

        g2.rotate(
            Math.toRadians(angulo),
            getWidth() / 2.0,
            getHeight() / 2.0
        );

        // Arco circular, sem caracteres especiais
        g2.drawArc(x, y, tamanho, tamanho, 45, 285);

        g2.dispose();
    }

    public void parar() {
        timer.stop();
    }
}
