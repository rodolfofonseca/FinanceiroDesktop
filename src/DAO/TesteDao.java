package DAO;

import com.mongodb.client.MongoCursor;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import util.MongoConnection;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

/**
 *
 * @author RODOLFO
 */
public class TesteDao {

    public void inserir(String nome, String email) {
        MongoCollection<Document> collection = MongoConnection.conectar().getCollection("clientes");

        Document doc = new Document("nome", nome).append("email", email);

        collection.insertOne(doc);
        System.out.println("Inserido!");
    }

    public void listar() {
        MongoCollection<Document> collection = MongoConnection.conectar().getCollection("clientes");

        MongoCursor<Document> cursor = collection.find().iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();
            System.out.println(doc.toJson());
        }
    }

    public void atualizar(String nomeAntigo, String novoEmail) {
        MongoCollection<Document> collection = MongoConnection.conectar().getCollection("clientes");

        collection.updateOne(
                eq("nome", nomeAntigo),
                set("email", novoEmail)
        );

        System.out.println("Atualizado!");
    }
    
    public void deletar(String nome) {
    MongoCollection<Document> collection = MongoConnection.conectar().getCollection("clientes");

    collection.deleteOne(eq("nome", nome));

    System.out.println("Deletado!");
}
}
