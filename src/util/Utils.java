package util;

import br.com.easysoftware.Ean;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import org.bson.types.ObjectId;
import org.bson.BsonDateTime;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {

    /**
     * Função que pega a hora atual e transforma em data compatível com o banco
     * de dados
     *
     * @return
     */
    public static BsonDateTime now() {
        return new BsonDateTime(Instant.now().toEpochMilli());
    }

    /**
     * Função responsável por validar e retornar a data do tipo de objeto do
     * mongo
     *
     * @param calendar
     * @return
     */
    public static BsonDateTime calendarToBson(Calendar calendar) {
        return Utils.toDate(calendar.getTime());
    }

    public static BsonDateTime toDate(Date date) {
        return new BsonDateTime(date.getTime());
    }

    public static BsonDateTime toDate(Date date, String hora) {

        if (hora == null || hora.trim().isEmpty()) {
            return new BsonDateTime(date.getTime());
        }

        String[] partes = hora.split(":");

        int h = Integer.parseInt(partes[0]);
        int m = Integer.parseInt(partes[1]);
        int s = Integer.parseInt(partes[2]);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, h);
        calendar.set(Calendar.MINUTE, m);
        calendar.set(Calendar.SECOND, s);
        calendar.set(Calendar.MILLISECOND, 0);

        return new BsonDateTime(calendar.getTimeInMillis());
    }

    /**
     * Função responsável por converter a data que vem do mongo para a data do
     * tipo que o usuário do sistema consegue entender.
     *
     * @param date
     * @return
     */
    public static String convertData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        return sdf.format(data);
    }

    /**
     * Função que pega o _id em string e converte para o tipo do banco de dados
     *
     * @param id
     * @return
     */
    public static ObjectId toId(String id) {
        return new ObjectId(id);
    }

    /**
     * Função que converter o valor para o campo de moeda brasileiro com vírgulo
     * ao invés de ponto
     *
     * @param valor
     * @return
     */
    public static String formatarMoeda(double valor) {

        Locale brasil = new Locale("pt", "BR");

        NumberFormat formato = NumberFormat.getNumberInstance(brasil);

        formato.setMinimumFractionDigits(2);
        formato.setMaximumFractionDigits(2);

        return formato.format(valor);
    }

    /**
     * Função responsável por pegar a string e converter para data
     *
     * @param data
     * @return
     */
    public static Date stringParaData(String data) {

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            return sdf.parse(data);

        } catch (Exception e) {

            return null;
        }
    }

    /**
     * Função responsável por pegar a data em string e converter para um
     * calendar (Se passar vazio ele retorna a hora atual)
     *
     * @param data
     * @return
     */
    public static Calendar stringParaCalendar(String data) {
        try {
            Calendar calendar = Calendar.getInstance();

            if (data == null || data.trim().isEmpty()) {
                return calendar;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            Date date = sdf.parse(data);
            calendar.setTime(date);
            return calendar;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Função responsável por retornar a transacao
     *
     * @return
     */
    public static String getTransacao() {
        try {
            return Ean.ean13();
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * Responsável por retornar o primeiro dia do mês atual
     *
     * @return
     */
    public static Calendar primeiroDiaMesAtual() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal;
    }

    /**
     * Responsável por retornar o último dia do mês atual
     *
     * @return
     */
    public static Calendar ultimoDiaMesAtual() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal;
    }

    /**
     * Função responsável por pegar a data de hoje e adicionar automáticamente
     * mais 30 dias.
     *
     * @return
     */
    public static Calendar diaAtualMais30Dias() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 30);
        return cal;
    }

    /**
     * Função responsável por pegar o formato de data bruto do Mongo e converter
     * para o formato calendar, para ser utilizado no objeto Calendário
     * @param bsonDateTime
     * @return 
     */
    public static Calendar bsonDateTimeToCalendar(BsonDateTime bsonDateTime) {

        Calendar calendar = Calendar.getInstance();

        if (bsonDateTime != null) {
            calendar.setTimeInMillis(bsonDateTime.getValue());
        }

        return calendar;
    }
    
    /**
     * Função que recebe um campo de date e converte para calendar, para ser utlizado
     * no campo de data.
     * @param data
     * @return 
     */
    public static Calendar dateToCalendar(Date data) {

    Calendar calendar = Calendar.getInstance();

    if (data != null) {
        calendar.setTime(data);
    }

    return calendar;
}
}
