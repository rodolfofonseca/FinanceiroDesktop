package util;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Classe que contém todas as informações de sessão do usuário que está logado no sistema.
 * @author RodolfoFonseca
 */
public class Sessao {
    public static JSONObject usuario;
    public static JSONObject empresa;
    public static JSONObject sistema;

    public static void carregar(JSONObject resposta) {

        JSONObject dados = resposta.getJSONObject("dados");

        usuario = dados.getJSONObject("usuario");
        empresa = dados.getJSONObject("empresa");
        sistema = dados.getJSONObject("sistema");
    }
}
