/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.HashMap;
import java.util.Map;
import org.bson.Document;
import util.Model;
import util.DB;

/**
 *
 * @author RODOLFO
 */
public class Main {

    public static void main(String[] args) {

        //Model.all("contas").forEach(System.out::println);
        MongoDatabase db = DB.conectar();
        MongoCollection<Document> clientes = db.getCollection("clientes");

        Map<String, Object> filtros = new HashMap<>();

        //Map<String, Object> nome = new HashMap<>();
        //nome.put("==", "Rodolfo");

        //filtros.put("nome", nome);

        Model.find("clientes", filtros, null, null, 2)
     .forEach(doc -> System.out.println(doc.toJson()));
    }
}
