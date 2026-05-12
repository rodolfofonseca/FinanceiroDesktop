package controller;

import DAO.SistemaDao;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Empresa;
import model.Sistema;
import org.bson.Document;
import util.DB;

/**
 *
 * @author RODOLFO
 */
public class SistemaCtr implements InterfaceController {

    private Empresa empresa;
    private Sistema sistema;
    private SistemaDao dao;
    private MongoDatabase database;

    public SistemaCtr() {
        this.empresa = new Empresa();
        this.sistema = new Sistema();
        this.dao = new SistemaDao();
    }

    @Override
    public void colocarDados() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void cadastro() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void validaCadastro() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void pesquisarDados() {
        this.database = DB.conectar();
        Map<String, Object> filtros = new HashMap<>();

        Map<String, Object> empresa_filtro = new HashMap<>();
        empresa_filtro.put("==", this.empresa.getId());
        
        filtros.put("empresa", empresa_filtro);
        
        List<Document> sistemas = this.dao.pesquisarDados(this.sistema.getTabela(), filtros, "", "asc", 0);
        
        if(sistemas.size() == 1){
            for(Document sist: sistemas){
                this.sistema.setId(sist.getObjectId("_id"));
                this.sistema.setVersao_sistema(sist.getString("versao_sistema"));
                this.sistema.setAnexa_documentos(sist.getString("anexa_documentos"));
                this.sistema.setModulo_contabil(sist.getBoolean("modulo_contabil"));
                this.sistema.setPedidos(sist.getBoolean("pedidos"));
                this.sistema.setCloudinary(sist.getBoolean("cloudinary"));
                this.sistema.setGoogle_agenda(sist.getBoolean("google_agenda"));
                this.sistema.setEndereco_json_google(sist.getString("endereco_json_google"));
                this.sistema.setConta_capital_social(sist.getString("conta_capital_social"));
                this.sistema.setConta_lucros_apropriar(sist.getString("conta_lucros_apropriar"));
                this.sistema.setConta_vendas_a_vista(sist.getString("conta_vendas_a_vista"));
                this.sistema.setConta_vendas_a_prazo(sist.getString("conta_vendas_a_prazo"));
                this.sistema.setConta_servicos_a_vista(sist.getString("conta_servicos_a_vista"));
                this.sistema.setConta_servicos_a_prazo(sist.getString("conta_servicos_a_prazo"));
                this.sistema.setConta_custo_mercadorias_vendidas(sist.getString("conta_custo_mercadorias_vencidas"));
                this.sistema.setConta_custo_servicos_prestados(sist.getString("conta_custo_servicos_prestados"));
                this.sistema.setConta_apuracao_resultado(sist.getString("conta_apuracao_resultado"));
                this.sistema.setVersao_sistema_java(sist.getString("versao_sistema_java"));
            }
        }
    }
    
    /**
     * Função responsável por pesquisar a empresa e retornar os dados para colocar
     * no formulário correspondente
     * @param empresa
     * @return 
     */
    public Sistema getSistema(Empresa empresa){
        this.empresa = empresa;
        this.pesquisarDados();
        return this.sistema;
    }

    @Override
    public void selecionarDados() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void LimparCampos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
