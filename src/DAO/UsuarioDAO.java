package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Usuario;
import util.Conexao;
/**
 * Classe que faz a comunicação com o banco de dados 
 * @author RodolfoFonseca
 */
public class UsuarioDAO implements InterfaceDao{

    @Override
    public void cadastrar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void alterar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object pesquisar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Object> listar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
     * Função responsável por realizar o login od usuário no sistema.
     * @param email
     * @param senha
     * @return 
     */
    public Usuario login(String email, String senha) {

        String sql = "SELECT codigo_usuario, nome_usuairo, "
                   + "email_usuario, tipo_usuario, status_usuario "
                   + "FROM usuario "
                   + "WHERE email_usuario = ? "
                   + "AND senha_usuario = ? "
                   + "AND status_usuario = TRUE";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    Usuario usuario = new Usuario();

                    usuario.setCodigo_usuario(
                        rs.getInt("codigo_usuario")
                    );

                    usuario.setNome_usuairo(
                        rs.getString("nome_usuairo")
                    );

                    usuario.setEmail_usuario(
                        rs.getString("email_usuario")
                    );

                    usuario.setTipo_usuario(
                        rs.getString("tipo_usuario")
                    );

                    usuario.setStatus_usuario(
                        rs.getBoolean("status_usuario")
                    );

                    return usuario;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                "Erro ao realizar login.", e
            );
        }

        return null;
    }

    @Override
    public List<Object> listar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
