package util;

import com.mongodb.client.*;
import org.bson.Document;

import java.io.FileInputStream;
import java.util.Properties;

public class DB {

    private static MongoClient client;
    private static MongoDatabase database;

    public static void connect() {
        if (client != null) {
            return;
        }

        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("configuracao.ini"));

            String dns = prop.getProperty("dns", "mongodb://127.0.0.1:27017");
            String dbName = prop.getProperty("db", "financeiro");

            client = MongoClients.create(dns);
            database = client.getDatabase(dbName);

            //System.out.println("Mongo conectado!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Classe responsável por fechar a conexão com o banco de dados
     */
    public static void close() {
        try {
            if (client != null) {
                client.close();
                client = null;
                database = null;

                //System.out.println("Mongo desconectado!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MongoCollection<Document> collection(String name) {
        connect();
        return database.getCollection(name);
    }

    public static MongoDatabase conectar() {
        if (database != null) {
            return database;
        }

        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("configuracao.ini"));

            String dns = prop.getProperty("dns");
            String dbName = prop.getProperty("db");

            client = MongoClients.create(dns);
            database = client.getDatabase(dbName);

            return database;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
