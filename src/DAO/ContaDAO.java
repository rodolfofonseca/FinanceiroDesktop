package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Contas;
import util.Conexao;
import util.Sessao;

/**
 * Classe que se comunica com o banco de dados buscando as informações das contas
 * @author nc
 */
public class ContaDAO{

    public void cadastrar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void alterar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object pesquisar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Contas> listar() {
        List<Contas> contas = new ArrayList<>();
        
        int codigo_empresa = Sessao.empresa.getInt("codigo_empresa");
        
        String sql = "SELECT * FROM conta WHERE codigo_empresa = '"+codigo_empresa+"' ORDER BY nome_conta ASC;";
        
        try (Connection conn = Conexao.conectar(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                Contas conta = new Contas();
                
                conta.setCodigo_conta(rs.getInt("codigo_conta"));
                conta.setNome_conta(rs.getString("nome_conta"));
                conta.setDescricao(rs.getString("descricao"));
                conta.setSaldo_conta(rs.getFloat("saldo_conta"));
                conta.setStatus(rs.getBoolean("status"));
                conta.setData_cadastro(rs.getTimestamp("data_cadastro"));
                
                contas.add(conta);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return contas;
    }
    
}