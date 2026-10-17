package controller;
import DAO.ContaDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Contas;
import util.ColorirTabela;
import util.FormatarData;
import view.inferna_frame.JIFContasBancarias;
/**
 * Classe que faz a comunicação entre todas as partes do sistema, relacionado as contas bancárias.
 * @author RodolfoFonseca
 */
public class ContasCtr {
    private ContaDAO dao;
    private Contas model;
    
    public ContasCtr(){
        this.dao = new ContaDAO();
        this.model = new Contas();
    }
    
    public void pesquisarContas() {

    List<Contas> contas = this.dao.listar();

    JIFContasBancarias.tabela.setModel(
        new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{
                "Código",
                "Nome",
                "Descrição",
                "Saldo",
                "Data Cadastro",
                "Status"
            }
        )
    );

    DefaultTableModel modelo =
        (DefaultTableModel) JIFContasBancarias.tabela.getModel();

    modelo.setRowCount(0);

    for (Contas conta : contas) {
        modelo.addRow(new Object[]{
            conta.getCodigo_conta(),
            conta.getNome_conta(),
            conta.getDescricao(),
            conta.getSaldo_conta(),
            FormatarData.formatarData(conta.getData_cadastro()),
            conta.isStatus()
        });
    }
    
    ColorirTabela.colorirLinhaPorBoolean(
        JIFContasBancarias.tabela, 5
    );
}
}
