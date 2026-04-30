package util;
import java.util.function.BiConsumer;
import javax.swing.JTable;

public class ActionButton {

    public String nome;
    public String icone;

    // 👉 função dinâmica (id + tabela)
    public BiConsumer<String, JTable> action;

    public ActionButton(String nome, String icone,
                        BiConsumer<String, JTable> action) {
        this.nome = nome;
        this.icone = icone;
        this.action = action;
    }
}