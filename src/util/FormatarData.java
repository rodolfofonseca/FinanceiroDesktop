package util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Classe responsável por formatar as datas que vem do banco de dados no formato
 * timestamp para o padrão do Brasil.
 *
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

    /**
     * Função responsável por converter a data do formato texto para o formato
     * timestamp.
     *
     * @param dataTexto
     * @return
     * @throws Exception
     */
    public static Timestamp converterParaTimestamp(String dataTexto) throws Exception {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false);

        Date data = formato.parse(dataTexto);

        return new Timestamp(data.getTime());
    }

    /**
     * Função responsável por pegar o primeiro dia do mës.
     * @return 
     */
    public static String primeiroDia() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate hoje = LocalDate.now();
        LocalDate primeiroDia = hoje.withDayOfMonth(1);
        String dataInicial = primeiroDia.format(formato);
        return dataInicial;
    }
    
    /**
     * Função responsável por pegar o último dia do mês.
     * @return 
     */
    public static String ultimoDia(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate hoje = LocalDate.now();
        LocalDate ultimoDia = hoje.withDayOfMonth(hoje.lengthOfMonth());
        String dataFinal = ultimoDia.format(formato);
        return dataFinal;
    }
}
