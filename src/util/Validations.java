package util;

/**
 * Classe responsável por realizar as validações dos campos, aprensentando as
 * mensagens quando necessário.
 *
 * @author RODOLFO
 */
public class Validations {

    public static boolean validationEmptyString(String value, String objectName) {
        if (value == null || value.trim().isEmpty()) {
            Mensagens.Validacao(objectName);
            return false;
        }

        return true;
    }
}
