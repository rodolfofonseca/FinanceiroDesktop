package DAO;

import java.util.List;

/**
 *
 * @author RODOLFO
 */
public interface InterfaceDao {
    void cadastrar(Object objeto);

    void alterar(Object objeto);

    Object pesquisar(int id);

    List<Object> listar();
}
