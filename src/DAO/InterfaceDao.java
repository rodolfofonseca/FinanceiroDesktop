package DAO;

import java.util.List;
import java.util.Map;
import org.bson.Document;

/**
 *
 * @author RODOLFO
 */
public interface InterfaceDao {
    public boolean inserirDados(String collection, Document data);
    public boolean alterarDados(String collection, Map<String, Object> filtros, Document data);
    public List<Document> pesquisarDados(String collection, Map<String, Object> filtros, String orderBy, String direction, int limit);
    public boolean excluirDados(String collection, Map<String, Object> filtros);
}
