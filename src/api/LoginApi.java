package api;
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
    private static final String URL = "https://financeirodev.api.hammertail.app/api/usuarios";
    private final HttpClient client;

    public LoginApi() {
        this.client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(15)).build();
    }

    public JsonObject login(String email, String senha) throws Exception {
        JsonObject corpo = new JsonObject();
        corpo.addProperty("email", email);
        corpo.addProperty("senha", senha);
        corpo.addProperty("router", "login");

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL)).timeout(Duration.ofSeconds(30)).header("Content-Type", "application/json").header("Accept", "application/json").POST(HttpRequest.BodyPublishers.ofString(corpo.toString(), StandardCharsets.UTF_8)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

        int status = response.statusCode();
        String resposta = response.body();

        if (status < 200 || status >= 300) {
            throw new RuntimeException("Erro HTTP " + status + ": " + resposta);
        }

        JsonObject json = JsonParser.parseString(resposta).getAsJsonObject();

        boolean sucesso = json.has("sucesso") && json.get("sucesso").getAsBoolean();

        if (!sucesso) {
            String mensagem = json.has("mensagem") ? json.get("mensagem").getAsString() : "Não foi possível efetuar o login.";
            throw new RuntimeException(mensagem);
        }

        return json;
    }
}
