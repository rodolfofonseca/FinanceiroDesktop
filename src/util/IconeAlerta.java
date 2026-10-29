package util;
import javax.swing.*;
import java.awt.*;
/**
 * Classe que desenha os icones de alertas
 * @author RodolfoFonseca
 */
public class IconeAlerta extends JPanel{
    
    public enum Tipo {
        SUCESSO, ERRO, AVISO
    }

    private final Tipo tipo;
    private final Color cor;

    public IconeAlerta(Tipo tipo, Color cor) {
        this.tipo = tipo;
        this.cor = cor;

        setOpaque(false);
        setPreferredSize(new Dimension(60, 60));
        setMinimumSize(new Dimension(60, 60));
        setMaximumSize(new Dimension(60, 60));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        int largura = getWidth();
        int altura = getHeight();

        g2.setColor(cor);
        g2.setStroke(new BasicStroke(
            4f,
            BasicStroke.CAP_ROUND,
            BasicStroke.JOIN_ROUND
        ));

        int x = 5;
        int y = 5;
        int tamanho = Math.min(largura, altura) - 10;

        switch (tipo) {
            case SUCESSO:
                g2.drawOval(x, y, tamanho, tamanho);
                g2.drawLine(18, 31, 26, 39);
                g2.drawLine(26, 39, 43, 21);
                break;

            case ERRO:
                g2.drawOval(x, y, tamanho, tamanho);
                g2.drawLine(21, 21, 39, 39);
                g2.drawLine(39, 21, 21, 39);
                break;

            case AVISO:
                Polygon triangulo = new Polygon();
                triangulo.addPoint(largura / 2, 5);
                triangulo.addPoint(largura - 5, altura - 5);
                triangulo.addPoint(5, altura - 5);

                g2.drawPolygon(triangulo);
                g2.drawLine(largura / 2, 20, largura / 2, 34);
                g2.fillOval(largura / 2 - 2, 40, 4, 4);
                break;
        }

        g2.dispose();
    }
}
