package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Contas;
import model.Empresa;
import model.Movimentacao;
import util.Conexao;
import util.Sessao;

/**
 * Classe que faz toda interação com o banco de dados
 *
 * @author RodolfoFonseca
 */
public class MovimentacaoDAO implements InterfaceDao {
    
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
    
    @Override
    public List<Object> listar(Object objeto) {
        Movimentacao movimentacao = (Movimentacao) objeto;
        List<Object> movimentacoes = new ArrayList<>();
        
        StringBuilder sql = new StringBuilder("SELECT * FROM movimentacao WHERE codigo_empresa = ?");
        
        if (movimentacao.getConta() != null) {
            sql.append(" AND codigo_conta = ?");
        }
        
        if ("CRÉDITO".equals(movimentacao.getTipoLancamentoPesq())) {
            sql.append(" AND tipo_lancamento = ?");
        } else if ("DÉBITO".equals(movimentacao.getTipoLancamentoPesq())) {
            sql.append(" AND tipo_lancamento = ?");
        } else if ("TRANSFERÊNCIA".equals(movimentacao.getTipoLancamentoPesq())) {
            sql.append(" AND transferencia = ?");
        }
        
        if (movimentacao.getDataInicialPesq() != null) {
            sql.append(" AND data_lancamento >= ?");
        }
        
        if (movimentacao.getDataFinalPesq() != null) {
            sql.append(" AND data_lancamento <= ?");
        }
        
        sql.append(" ORDER BY data_lancamento DESC;");
        
        System.out.println(sql);
        System.out.println(Sessao.empresa.getInt("codigo_empresa"));
        System.out.println(movimentacao.getDataInicialPesq());
        System.out.println(movimentacao.getDataFinalPesq());
        
        try (Connection conn = Conexao.conectar(); PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            int parametro = 1;
            
            ps.setInt(parametro++, Sessao.empresa.getInt("codigo_empresa"));
            
            if (movimentacao.getConta() != null) {
                ps.setInt(parametro++, movimentacao.getConta().getCodigo_conta());
            }
            
            if ("CRÉDITO".equals(movimentacao.getTipoLancamentoPesq())) {
                ps.setBoolean(parametro++, true);
            } else if ("DÉBITO".equals(movimentacao.getTipoLancamentoPesq())) {
                ps.setBoolean(parametro++, false);
            } else if ("TRANSFERÊNCIA".equals(movimentacao.getTipoLancamentoPesq())) {
                ps.setBoolean(parametro++, true);
            }
            
            if(movimentacao.getDataInicialPesq() != null){
                ps.setTimestamp(parametro++, movimentacao.getDataInicialPesq());
            }
            
            if(movimentacao.getDataFinalPesq() != null){
                ps.setTimestamp(parametro++, movimentacao.getDataFinalPesq());
            }
            
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Movimentacao model = new Movimentacao();
                    Empresa empresa = new Empresa();
                    Contas conta = new Contas();
                    
                    empresa.setCodigo_empresa(rs.getInt("codigo_empresa"));
                    conta.setCodigo_conta(rs.getInt("codigo_conta"));
                    
                    model.setCodigoMovimentacao(rs.getInt("codigo_movimentacao"));
                    model.setValorLancamento(rs.getFloat("valor_lancamento"));
                    model.setTipoLancamento(rs.getBoolean("tipo_lancamento"));
                    model.setDataLancamento(rs.getTimestamp("data_lancamento"));
                    model.setDescricao(rs.getString("descricao"));
                    model.setComprovante(rs.getBoolean("comprovante"));
                    model.setTransferencia(rs.getBoolean("transferencia"));
                    
                    model.setEmpresa(empresa);
                    model.setConta(conta);
                    
                    movimentacoes.add(model);
                }
            }
        } catch (SQLException ex) {
            System.getLogger(MovimentacaoDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            return movimentacoes;
        }
    }
}
