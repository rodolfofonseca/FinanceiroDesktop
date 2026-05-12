package DAO;

import java.util.List;
import java.util.Map;
import org.bson.Document;

/**
 *
 * @author RODOLFO
 */
public class SistemaDao implements InterfaceDao {

    ModelDAO modelDao = new ModelDAO();
    
    public SistemaDao(){
        this.modelDao = new ModelDAO();
    }

    @Override
    public boolean inserirDados(String collection, Document data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean alterarDados(String collection, Map<String, Object> filtros, Document data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Document> pesquisarDados(String collection, Map<String, Object> filtros, String orderBy, String direction, int limit) {
       return this.modelDao.find(collection, filtros, orderBy, direction, limit);
    }

    @Override
    public boolean excluirDados(String collection, Map<String, Object> filtros) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
