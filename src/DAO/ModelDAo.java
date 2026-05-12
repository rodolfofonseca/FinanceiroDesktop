package DAO;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DB;

public class ModelDAO {

    /**
     * Função responsável por pesquisar no banco de dados e retornar os dados
     * de acordo com os filtros passados
     */
    public List<Document> find(
            String collectionName,
            Map<String, Object> filtros,
            String orderBy,
            String direction,
            int limit
    ) {

        MongoCollection<Document> col = DB.collection(collectionName);

        Document query = buildQuery(filtros);

        //System.out.println("QUERY: " + query.toJson());

        FindIterable<Document> result = col.find(query);

        // ORDENAÇÃO
        if (orderBy != null && !orderBy.isEmpty()) {

            int dir = 1;

            if (direction != null
                    && direction.equalsIgnoreCase("desc")) {
                dir = -1;
            }

            result = result.sort(new Document(orderBy, dir));
        }

        // LIMITE
        if (limit > 0) {
            result = result.limit(limit);
        }

        List<Document> lista = new ArrayList<>();

        result.forEach(lista::add);

        return lista;
    }

    /**
     * INSERT
     */
    public boolean insert(String collection, Document data) {

        InsertOneResult resultado
                = DB.collection(collection).insertOne(data);

        return resultado.wasAcknowledged();
    }

    /**
     * UPDATE
     */
    public boolean update(
            String collection,
            Map<String, Object> filtros,
            Document data
    ) {

        Document query = buildQuery(filtros);

        UpdateResult resultado = DB.collection(collection)
                .updateMany(query, new Document("$set", data));

        return resultado.getModifiedCount() > 0;
    }

    /**
     * DELETE
     */
    public boolean delete(
            String collection,
            Map<String, Object> filtros
    ) {

        Document query = buildQuery(filtros);

        DeleteResult resultado = DB.collection(collection)
                .deleteMany(query);

        return resultado.getDeletedCount() > 0;
    }

    /**
     * MONTA QUERY DINÂMICA
     */
    private static Document buildQuery(Map<String, Object> filtros) {

        if (filtros == null || filtros.isEmpty()) {
            return new Document();
        }

        List<Document> conditions = new ArrayList<>();

        for (Map.Entry<String, Object> entry : filtros.entrySet()) {

            String campo = entry.getKey();
            Object valor = entry.getValue();

            // FILTRO COM OPERADORES
            if (valor instanceof Map) {

                Map<String, Object> ops
                        = (Map<String, Object>) valor;

                // TODOS OPERADORES DO MESMO CAMPO
                Document operadoresMongo = new Document();

                for (Map.Entry<String, Object> op : ops.entrySet()) {

                    String operador = op.getKey();
                    Object v = op.getValue();

                    switch (operador) {

                        // LIKE
                        case "=":

                            operadoresMongo.append(
                                    "$regex",
                                    ".*" + v + ".*"
                            );

                            operadoresMongo.append(
                                    "$options",
                                    "i"
                            );

                            break;

                        // IGUALDADE
                        case "==":

                            conditions.add(
                                    new Document(campo, v)
                            );

                            break;

                        // MAIOR OU IGUAL
                        case ">=":

                            operadoresMongo.append(
                                    "$gte",
                                    v
                            );

                            break;

                        // MENOR OU IGUAL
                        case "<=":

                            operadoresMongo.append(
                                    "$lte",
                                    v
                            );

                            break;

                        // DIFERENTE
                        case "!=":

                            operadoresMongo.append(
                                    "$ne",
                                    v
                            );

                            break;

                        // MAIOR
                        case ">":

                            operadoresMongo.append(
                                    "$gt",
                                    v
                            );

                            break;

                        // MENOR
                        case "<":

                            operadoresMongo.append(
                                    "$lt",
                                    v
                            );

                            break;

                        default:

                            throw new RuntimeException(
                                    "Operador não suportado: "
                                    + operador
                            );
                    }
                }

                // ADICIONA CAMPO COM TODOS OPERADORES
                if (!operadoresMongo.isEmpty()) {

                    // se já foi adicionado pelo ==
                    if (!ops.containsKey("==")) {

                        conditions.add(
                                new Document(campo, operadoresMongo)
                        );
                    }
                }

            } else {

                // SEM OPERADOR = IGUALDADE
                conditions.add(
                        new Document(campo, valor)
                );
            }
        }

        // MONTA QUERY FINAL
        if (conditions.size() == 1) {

            return conditions.get(0);

        } else if (conditions.size() > 1) {

            return new Document("$and", conditions);

        } else {

            return new Document();
        }
    }
}