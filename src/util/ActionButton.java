package util;

import java.awt.Color;
import java.util.function.BiConsumer;
import javax.swing.JTable;

public class ActionButton {

    public String nome;
    public String icone;
    public Color cor;
    public Color hover;

    // 👉 função dinâmica (id + tabela)
    public BiConsumer<String, JTable> action;

    public ActionButton(String nome, String icone, Color cor, Color hover, BiConsumer<String, JTable> action) {
        this.nome = nome;
        this.icone = icone;
        this.cor = cor;
        this.hover = hover;
        this.action = action;
    }
}
