package util;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
/**
 *
 * @author RODOLFO
 */
public class MongoConnection {
    private static final String URI = "mongodb+srv://youtube_tutorials:Crydo879.@yotube.zsvua.mongodb.net/?appName=Yotube";

    /**
     *
     * @return
     */
    public static MongoDatabase conectar() {
        MongoClient client = MongoClients.create(URI);
        return client.getDatabase("controleFinanceiroTeste");
    }
}