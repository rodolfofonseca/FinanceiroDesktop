package api;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
/**
 * Classe responsável por se comunicar com a api, retornando os dados de login
 * @author RodolfoFonseca
 */
public class LoginApi {
    private static final String URL =
        "https://gerenciador-de-documentos.api.hammertail.app/api/usuarios";

    private final HttpClient client;

    public LoginApi() {
        this.client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(15))
            .build();
    }

    public JsonObject login(String email, String senha)
            throws Exception {

        JsonObject corpo = new JsonObject();
        corpo.addProperty("email", email);
        corpo.addProperty("senha", senha);
        corpo.addProperty("router", "login");

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(URL))
            .timeout(Duration.ofSeconds(30))
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(
                corpo.toString(),
                StandardCharsets.UTF_8
            ))
            .build();

        HttpResponse<String> response = client.send(
            request,
            HttpResponse.BodyHandlers.ofString(
                StandardCharsets.UTF_8
            )
        );

        int status = response.statusCode();
        String resposta = response.body();

        if (status < 200 || status >= 300) {
            throw new RuntimeException(
                "Erro HTTP " + status + ": " + resposta
            );
        }

        JsonObject json = JsonParser.parseString(resposta)
            .getAsJsonObject();

        boolean sucesso = json.has("sucesso")
            && json.get("sucesso").getAsBoolean();

        if (!sucesso) {
            String mensagem = json.has("mensagem")
                ? json.get("mensagem").getAsString()
                : "Não foi possível efetuar o login.";

            throw new RuntimeException(mensagem);
        }

        return json;
    }

    public static void main(String[] args) {
        LoginApi api = new LoginApi();

        try {
            JsonObject resposta = api.login(
                "rodolfofonseca01@gmail.com",
                "123"
            );

            System.out.println(
                "Mensagem: " +
                resposta.get("mensagem").getAsString()
            );

            JsonObject dados = resposta
                .getAsJsonObject("dados");

            JsonObject usuario = dados
                .getAsJsonObject("usuario");

            System.out.println(
                "Código: " +
                usuario.get("codigo_usuario").getAsInt()
            );

            System.out.println(
                "Nome: " +
                usuario.get("nome_usuario").getAsString()
            );

            System.out.println(
                "Email: " +
                usuario.get("email_usuario").getAsString()
            );

            System.out.println(
                "Tipo: " +
                usuario.get("tipo_usuario").getAsString()
            );

            JsonArray empresas = dados.getAsJsonArray("empresa");

            if (!empresas.isEmpty()) {
                JsonObject empresa = empresas.get(0)
                    .getAsJsonObject();

                System.out.println(
                    "Empresa: " +
                    empresa.get("nome_empresa").getAsString()
                );
            }

            JsonArray sistemas = dados.getAsJsonArray("sistema");

            if (!sistemas.isEmpty()) {
                JsonObject sistema = sistemas.get(0)
                    .getAsJsonObject();

                System.out.println(
                    "Versão: " +
                    sistema.get("versao_sistema").getAsString()
                );

                System.out.println(
                    "Pedidos habilitados: " +
                    sistema.get("pedidos").getAsBoolean()
                );
            }

        } catch (Exception e) {
            System.err.println(
                "Falha no login: " + e.getMessage()
            );
        }
    }
}
