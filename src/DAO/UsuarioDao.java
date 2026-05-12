package DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.Document;
import model.Usuarios;
import util.DB;

/**
 *
 * @author RODOLFO
 */
public class UsuarioDao implements InterfaceDao {

    ModelDAO modelDao = new ModelDAO();

    public UsuarioDao() {
        this.modelDao = new ModelDAO();
    }

    public List<Document> LoginSistema(String emailUsuario) {
        MongoDatabase db = DB.conectar();
        Usuarios usuario = new Usuarios();
        MongoCollection<Document> usuarios = db.getCollection(usuario.getTabela());
        //MongoCollection<Document> usuarios = db.getCollection("contas_pagar_receber");
        Map<String, Object> filtros = new HashMap<>();

        Map<String, Object> email = new HashMap<>();
        email.put("==", emailUsuario);
        filtros.put("email_usuario", email);

        return this.modelDao.find(usuario.getTabela(), filtros, "", "asc", 0);
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