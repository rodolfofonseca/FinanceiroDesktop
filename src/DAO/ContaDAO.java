package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Contas;
import model.Empresa;
import util.Conexao;
import util.Sessao;

/**
 * Classe que se comunica com o banco de dados buscando as informações das
 * contas
 *
 * @author RodolfoFonseca
 */
public class ContaDAO {

    /**
     * Função responsável por cadastrar novas contas no banco de dados.
     * @param conta
     * @return 
     */
    public boolean cadastrar(Contas conta) {
        String sql = "INSERT INTO conta (codigo_empresa, nome_conta, descricao, saldo_conta, status) VALUES(?, ?, ?, ?, ?);";
        
        try(Connection connection = Conexao.conectar(); PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, conta.getEmpresa().getCodigo_empresa());
            stmt.setString(2, conta.getNome_conta());
            stmt.setString(3, conta.getDescricao());
            stmt.setFloat(4, conta.getSaldo_conta());
            stmt.setBoolean(5, conta.isStatus());
            
            return stmt.executeUpdate() > 0;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Função responsável por alterar as informações das contas, cadastradas no banco de dados.
     * @param conta
     * @return 
     */
    public boolean alterar(Contas conta) {
        String sql = "UPDATE conta SET nome_conta =?, descricao = ?, saldo_conta = ?, status = ? WHERE codigo_conta = ?;";
        
        try(Connection connection = Conexao.conectar(); PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, conta.getNome_conta());
            stmt.setString(2, conta.getDescricao());
            stmt.setFloat(3, conta.getSaldo_conta());
            stmt.setBoolean(4, conta.isStatus());
            stmt.setInt(5, conta.getCodigo_conta());
            
            return stmt.executeUpdate() > 0;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public Contas pesquisar(int id) {
        String sql = "SELECT * FROM conta WHERE codigo_conta = ?;";
        Contas conta = new Contas();
        
        try(Connection connection = Conexao.conectar(); PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    Empresa empresa = new Empresa();
                    
                    empresa.setCodigo_empresa(rs.getInt("codigo_empresa"));
                    
                    conta.setCodigo_conta(id);
                    conta.setEmpresa(empresa);
                    conta.setNome_conta(rs.getString("nome_conta"));
                    conta.setDescricao(rs.getString("descricao"));
                    conta.setSaldo_conta(rs.getFloat("saldo_conta"));
                    conta.setStatus(rs.getBoolean("status"));
                    conta.setData_cadastro(rs.getTimestamp("data_cadastro"));
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            return conta;
        }
    }

    public List<Contas> listar(Contas objetoConta) {
        List<Contas> contas = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT * FROM conta WHERE codigo_empresa = ?");

        if (objetoConta.getNome_conta() != null && !objetoConta.getNome_conta().isEmpty()) {
            sql.append(" AND nome_conta LIKE ?");
        }

        if (objetoConta.getDescricao() != null && !objetoConta.getDescricao().isEmpty()) {
            sql.append(" AND descricao LIKE ?");
        }

        sql.append(" AND status = ?");
        sql.append(" ORDER BY nome_conta ASC");

        try (Connection conn = Conexao.conectar(); PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            int parametro = 1;

            ps.setInt(parametro++, Sessao.empresa.getInt("codigo_empresa"));

            if (objetoConta.getNome_conta() != null && !objetoConta.getNome_conta().isEmpty()) {
                ps.setString(parametro++, "%"+objetoConta.getNome_conta()+"%");
            }

            if (objetoConta.getDescricao() != null && !objetoConta.getDescricao().isEmpty()) {
                ps.setString(parametro++, "%"+objetoConta.getDescricao()+"%");
            }

            ps.setBoolean(parametro++, objetoConta.isStatus());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Contas conta = new Contas();

                    conta.setCodigo_conta(rs.getInt("codigo_conta"));
                    conta.setNome_conta(rs.getString("nome_conta"));
                    conta.setDescricao(rs.getString("descricao"));
                    conta.setSaldo_conta(rs.getFloat("saldo_conta"));
                    conta.setStatus(rs.getBoolean("status"));
                    conta.setData_cadastro(rs.getTimestamp("data_cadastro"));

                    contas.add(conta);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contas;
    }

}
