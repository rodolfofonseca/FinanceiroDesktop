package util;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.*;

public class Model {

 public static List<Document> find(String collectionName,
                                  Map<String, Object> filtros,
                                  String orderBy,
                                  String direction,
                                  int limit) {

    MongoCollection<Document> col = DB.collection(collectionName);

    Document query = buildQuery(filtros);

    System.out.println("QUERY: " + query.toJson());

    FindIterable<Document> result = col.find(query);

    // 🔽 ORDER BY
    if (orderBy != null && !orderBy.isEmpty()) {
        int dir = direction != null && direction.equalsIgnoreCase("desc") ? -1 : 1;
        result = result.sort(new Document(orderBy, dir));
    }

    // 🔽 LIMIT
    if (limit > 0) {
        result = result.limit(limit);
    }

    List<Document> lista = new ArrayList<>();
    result.forEach(lista::add);

    return lista;
}

    // ➕ INSERT
    public static void insert(String collection, Document data) {
        DB.collection(collection).insertOne(data);
    }

    // ✏️ UPDATE
    public static void update(String collection, Map<String, Object> filtros, Document data) {
        Document query = buildQuery(filtros);
        DB.collection(collection).updateMany(query, new Document("$set", data));
    }

    // ❌ DELETE
    public static void delete(String collection, Map<String, Object> filtros) {
        Document query = buildQuery(filtros);
        DB.collection(collection).deleteMany(query);
    }

    // 🔧 REAPROVEITA FILTRO
    private static Document buildQuery(Map<String, Object> filtros) {

        List<Document> conditions = new ArrayList<>();

        for (Map.Entry<String, Object> entry : filtros.entrySet()) {

            String campo = entry.getKey();
            Object valor = entry.getValue();

            if (valor instanceof Map) {

                Map<String, Object> ops = (Map<String, Object>) valor;

                for (Map.Entry<String, Object> op : ops.entrySet()) {

                    String operador = op.getKey();
                    Object v = op.getValue();

                    switch (operador) {

                        case "=": // LIKE %valor%
                            conditions.add(new Document(campo,
                                    new Document("$regex", ".*" + v + ".*")
                            ));
                            break;

                        case "==": // IGUAL EXATO
                            conditions.add(new Document(campo, v));
                            break;

                        case ">=":
                            conditions.add(new Document(campo,
                                    new Document("$gte", v)
                            ));
                            break;

                        case "<=":
                            conditions.add(new Document(campo,
                                    new Document("$lte", v)
                            ));
                            break;

                        default:
                            throw new RuntimeException("Operador não suportado: " + operador);
                    }
                }

            } else {
                // se não vier operador, assume igualdade
                conditions.add(new Document(campo, valor));
            }
        }

        if (conditions.size() > 1) {
            return new Document("$and", conditions);
        } else if (conditions.size() == 1) {
            return conditions.get(0);
        } else {
            return new Document();
        }
    }
}
