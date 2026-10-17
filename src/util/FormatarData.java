package util;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
/**
 * Classe responsável por formatar as datas que vem do banco de dados no formato timestamp
 * para o padrão do Brasil.
 * @author RodolfoFonseca
 */
public class FormatarData {
    public static String formatarData(Timestamp data) {
    if (data == null) {
        return "";
    }

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    return formato.format(data);
}
}
