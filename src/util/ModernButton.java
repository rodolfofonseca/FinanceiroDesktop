/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author RODOLFO
 */


public class ModernButton extends JButton {

    private Color bgColor;
    private Color hoverColor;

    public ModernButton(String text, String iconPath, Color bgColor, Color hoverColor) {
        super(text);
        this.bgColor = bgColor;
        this.hoverColor = hoverColor;

        setIcon(new ImageIcon(getClass().getResource(iconPath)));
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setForeground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setMargin(new Insets(5, 10, 5, 10));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setColor(getModel().isRollover() ? hoverColor : bgColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);

        super.paintComponent(g);
        g2.dispose();
    }
}