package DAO;

import java.util.List;
import java.util.Map;
import org.bson.Document;

/**
 *
 * @author RODOLFO
 */
public class ContaDao implements InterfaceDao{
    ModelDAO modelDao = new ModelDAO();
    
    public ContaDao(){
        this.modelDao = new ModelDAO();
    }
    
    @Override
    public boolean inserirDados(String collection, Document data) {
        return this.modelDao.insert(collection, data);
    }

    @Override
    public boolean alterarDados(String collection, Map<String, Object> filtros, Document data) {
        return this.modelDao.update(collection, filtros, data);
    }

    @Override
    public List<Document> pesquisarDados(String collection, Map<String, Object> filtros, String orderBy, String direction, int limit) {
        return this.modelDao.find(collection, filtros, orderBy, direction, limit);
    }

    @Override
    public boolean excluirDados(String collection, Map<String, Object> filtros) {
        return this.modelDao.delete(collection, filtros);
    }
}
