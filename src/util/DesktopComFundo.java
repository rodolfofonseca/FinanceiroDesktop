package util;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JDesktopPane;
/**
 * Classe que coloca o fundo no desktop
 * @author RodolfoFonseca
 */
public class DesktopComFundo extends JDesktopPane{
    private Image imagemFundo;

    public void setImagemFundo(Image imagemFundo) {
        this.imagemFundo = imagemFundo;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (imagemFundo != null) {
            g.drawImage(
                    imagemFundo,
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    this
            );
        }
    }
}
