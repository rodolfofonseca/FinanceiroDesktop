package util;

import java.util.LinkedHashSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;

import java.util.List;
import java.util.Set;

public class TableUtil {

    public static void preencherTabela(JTable tabela, List<Document> dados) {

        DefaultTableModel model = new DefaultTableModel();

        if (dados == null || dados.isEmpty()) {
            tabela.setModel(model);
            return;
        }

        // 🔹 pega as colunas do primeiro documento
        Set<String> colunas = new LinkedHashSet<>(dados.get(0).keySet());
        colunas.remove("_id");
        model.addColumn("_id");

        // adiciona colunas
        for (String col : colunas) {
            model.addColumn(col);
        }

        // adiciona linhas
        for (Document doc : dados) {

            Object[] linha = new Object[colunas.size() + 1];
            int i = 0;

            linha[i++] = doc.get("_id").toString();
            for (String col : colunas) {
                linha[i++] = doc.get(col);
            }

            model.addRow(linha);
        }

        tabela.setModel(model);
        tabela.getColumnModel().getColumn(0).setMinWidth(0);
        tabela.getColumnModel().getColumn(0).setMaxWidth(0);
        tabela.getColumnModel().getColumn(0).setWidth(0);
    }
}
