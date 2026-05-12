package controller;

import DAO.ContaFornecedoresDao;
import DAO.ContasPagarReceberDao;
import DAO.UsuarioDao;
import com.mongodb.client.MongoDatabase;
import com.mongodb.internal.client.model.Util;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ContasFornecedores;
import model.ContasPagarReceber;
import model.Empresa;
import model.Usuarios;
import model.alterado.UsuariosJComboBox;
import model.alterado.ContasFornecedoresJComboBox;
import org.bson.Document;
import util.DB;
import util.Mensagens;
import util.TableUtil;
import util.Utils;
import util.Validations;
import view.inferna_frame.JIFContasPagarReceber;

/**
 * Responsável por realizar a comunicação entre as diversas partes do sistema.
 *
 * @author RODOLFO
 */
public class ContasPagarReceberCtr implements InterfaceController {

    private Empresa empresa;
    private Usuarios fornecedor;
    private ContasFornecedores contas_fornecedores;
    private ContasPagarReceber contas_pagar_receber;
    private ContasPagarReceberDao dao;
    private boolean alteracao = false;
    private int linhaTabela = 1;
    private DefaultTableModel modelTabelaContasCadastro;

    public ContasPagarReceberCtr() {
        this.empresa = new Empresa();
        this.fornecedor = new Usuarios();
        this.contas_fornecedores = new ContasFornecedores();
        this.contas_pagar_receber = new ContasPagarReceber();
        this.dao = new ContasPagarReceberDao();
    }

    @Override
    public void colocarDados() {
        try {
            DB.conectar();
            this.contas_pagar_receber.setId(Utils.toId(JIFContasPagarReceber.tabela.getValueAt(JIFContasPagarReceber.tabela.getSelectedRow(), 0).toString()));

            Map<String, Object> filtros = new HashMap<>();
            Map<String, Object> codigoConta = new HashMap<>();

            codigoConta.put("==", this.contas_pagar_receber.getId());
            filtros.put("_id", codigoConta);

            List<Document> contas = this.dao.pesquisarDados(this.contas_pagar_receber.getTabela(), filtros, "nome_conta", "asc", 0);

            if (contas.size() == 1) {
                for (Document retornoBanco : contas) {
                    this.contas_pagar_receber.setId(retornoBanco.getObjectId("_id"));
                    this.alteracao = true;
                    JIFContasPagarReceber.tbxDescricaoCadastro.setText(retornoBanco.getString("descricao"));
                    JIFContasPagarReceber.tbxValorContaCadastro.setText(Utils.formatarMoeda(retornoBanco.getDouble("valor_conta")));
                    JIFContasPagarReceber.tbxValorPagoCadastro.setText(Utils.formatarMoeda(retornoBanco.getDouble("valor_pago")));
                    JIFContasPagarReceber.tbxValorJuroDescontoCadastro.setText(Utils.formatarMoeda(retornoBanco.getDouble("valor_juro_desconto")));
                    JIFContasPagarReceber.jcbTipoJuroDescontoCadastro.setSelectedItem(retornoBanco.getString("tipo_juro_desconto"));
                    JIFContasPagarReceber.jcbTipoContaCadastro.setSelectedItem(retornoBanco.getString("tipo_conta"));
                    JIFContasPagarReceber.jcbStatusContaCadastro.setSelectedItem(retornoBanco.getString("status_conta"));

                    String transacao = retornoBanco.getString("transacao");
                    String boleto = retornoBanco.getString("boleto");
                    String comprovante = retornoBanco.getString("comprovante");

                    JIFContasPagarReceber.tbxTransacaoCadastro.setText(transacao != null ? transacao : Utils.getTransacao());
                    JIFContasPagarReceber.jcbBoletoCadastro.setSelectedItem(boleto != null ? boleto : "NAO");
                    JIFContasPagarReceber.jcbComprovanteCadastro.setSelectedItem(comprovante != null ? comprovante : "NAO");

                    JIFContasPagarReceber.jcDataCadastroCadastro.setCalendar(Utils.dateToCalendar(retornoBanco.getDate("data_cadastro")));
                    JIFContasPagarReceber.jcDataVencimentoCadastro.setCalendar(Utils.dateToCalendar(retornoBanco.getDate("data_vencimento")));
                    JIFContasPagarReceber.jcDataBaixaCadastro.setCalendar(Utils.dateToCalendar(retornoBanco.getDate("data_baixa")));
                }
            }

            JOptionPane.showMessageDialog(null, "Dados adicionados com sucesso,\n Entre nada aba de 'CADASTRO E EDIÇÃO DE CONTAS'\n Para alterar os dados que deseja", "SUCESSO", JOptionPane.QUESTION_MESSAGE);
        } catch (Exception ex) {
            Mensagens.ErroException(ex);
        } finally {
            DB.close();
        }
    }

    @Override
    public void cadastro() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void cadastro(ContasPagarReceber conta) {
        Document data = new Document();

        data.append("empresa", conta.getEmpresa().getId());
        data.append("cliente_fornecedor", conta.getFornecedor().getId());
        data.append("conta_fornecedor", conta.getConta_forneceres().getId());
        data.append("nome_conta", conta.getNomeConta().toUpperCase().trim());
        data.append("descricao", conta.getDescricao().toUpperCase().trim());
        data.append("valor_conta", conta.getValor_conta());
        data.append("valor_pago", conta.getValor_pago());
        data.append("valor_juro_desconto", conta.getValor_juro_desconto());
        data.append("tipo_juro_desconto", conta.getTipo_juro_desconto());
        data.append("tipo_conta", conta.getTipo_conta());
        data.append("data_cadastro", conta.getData_cadastro());
        data.append("data_vencimento", conta.getData_vencimento());
        data.append("data_baixa", conta.getData_baixa());
        data.append("status_conta", conta.getStatus_conta());
        data.append("comprovante", conta.getComprovante());
        data.append("boleto", conta.getBoleto());
        data.append("transacao", conta.getTransacao());

        boolean retorno = false;

        if (this.alteracao == false) {
            retorno = this.dao.inserirDados(conta.getTabela(), data);
        } else {
            Map<String, Object> filtros = new HashMap<>();
            Map<String, Object> codigo_conta = new HashMap<>();
            codigo_conta.put("==", this.contas_pagar_receber.getId());
            filtros.put("_id", codigo_conta);

            retorno = this.dao.alterarDados(this.contas_pagar_receber.getTabela(), filtros, data);
        }
    }

    @Override
    public void validaCadastro() {
        try {
            DB.conectar();
            DefaultTableModel model = (DefaultTableModel) JIFContasPagarReceber.tabelaCadastroContaRecorrente.getModel();
            this.empresa = JIFContasPagarReceber.empresa;

            for (int i = 0; i < model.getRowCount(); i++) {
                ContasPagarReceber contas = new ContasPagarReceber();

                this.fornecedor.setId(Utils.toId(model.getValueAt(i, 1).toString()));
                this.contas_fornecedores.setId(Utils.toId(model.getValueAt(i, 2).toString()));

                contas.setNomeConta(model.getValueAt(i, 3).toString());
                contas.setDescricao(model.getValueAt(i, 4).toString());
                contas.setValor_conta(Double.parseDouble(model.getValueAt(i, 5).toString().replace(",", ".")));
                contas.setValor_pago(Double.parseDouble(model.getValueAt(i, 6).toString().replace(",", ".")));
                contas.setValor_juro_desconto(Double.parseDouble(model.getValueAt(i, 7).toString().replace(",", ".")));
                contas.setTipo_juro_desconto(model.getValueAt(i, 8).toString());
                contas.setTipo_conta(model.getValueAt(i, 9).toString());
                contas.setData_cadastro(Utils.toDate(Utils.stringParaData(model.getValueAt(i, 12).toString())));
                contas.setData_vencimento(Utils.toDate(Utils.stringParaData(model.getValueAt(i, 13).toString())));
                contas.setData_baixa(Utils.toDate(Utils.stringParaData(model.getValueAt(i, 14).toString())));
                contas.setStatus_conta(model.getValueAt(i, 10).toString());
                contas.setComprovante(model.getValueAt(i, 16).toString());
                contas.setBoleto(model.getValueAt(i, 15).toString());
                contas.setTransacao(model.getValueAt(i, 11).toString());

                contas.setEmpresa(this.empresa);
                contas.setFornecedor(this.fornecedor);
                contas.setConta_forneceres(this.contas_fornecedores);
                cadastro(contas);

                //"ID = 0", "FORNECEDOR = 1", "ID CONTA = 2", "NOME DA CONTA = 3", "DESCRIÇÃO = 4", "VALOR CONTA = 5", 
                //"VALOR PAGO = 6", "VALOR JURO/DESCONTO = 7", "TIPO JURO/DESCONTO = 8", "TIPO = 9", "STATUS = 10", 
                //"TRANSAÇÃO = 11", "DATA CADASTRO = 12", "DATA VENCIMENTO = 13", "DATA BAIXA = 14", 
                //"BOLETO = 15", "COMPROVANTE = 16"
            }

            Mensagens.Sucesso();
        } catch (Exception ex) {
            Mensagens.ErroException(ex);
        } finally {
            DB.close();
        }
    }

    @Override
    public void pesquisarDados() {
        try {
            DB.conectar();

            ContasFornecedoresJComboBox contaFornecedor = null;
            Map<String, Object> filtros = new HashMap<>();

            if ((ContasFornecedoresJComboBox) JIFContasPagarReceber.jcbContasPesquisa.getSelectedItem() != null) {
                contaFornecedor = (ContasFornecedoresJComboBox) JIFContasPagarReceber.jcbContasPesquisa.getSelectedItem();
                String codigo = contaFornecedor.getId();

                this.contas_fornecedores.setId(Utils.toId(codigo));
                Map<String, Object> codigoConta = new HashMap<>();
                codigoConta.put("==", this.contas_fornecedores.getId());
                filtros.put("conta_fornecedor", codigoConta);
            }

            String descricao = JIFContasPagarReceber.tbxDescricaoPesquisa.getText();
            String tipoConta = JIFContasPagarReceber.jcbTipoContaPesquisa.getSelectedItem().toString();
            String statusConta = JIFContasPagarReceber.jcbStatusContaPesquisa.getSelectedItem().toString();
            String tipoData = JIFContasPagarReceber.jcbTipoDataPesquisa.getSelectedItem().toString();
            this.empresa = JIFContasPagarReceber.empresa;
            this.contas_pagar_receber.setEmpresa(empresa);

            Map<String, Object> empresa_filtro = new HashMap<>();
            empresa_filtro.put("==", this.empresa.getId());
            filtros.put("empresa", empresa_filtro);

            Calendar dataInicio = JIFContasPagarReceber.jcDataInicial.getCalendar();
            Calendar dataFim = JIFContasPagarReceber.jcDataFinal.getCalendar();

            if (!"".equals(descricao)) {
                this.contas_pagar_receber.setDescricao(descricao);
                Map<String, Object> descricaoConta = new HashMap<>();
                descricaoConta.put("==", this.contas_pagar_receber.getDescricao());
                filtros.put("descricao", descricaoConta);
            }

            if (!"TODAS".equals(tipoConta)) {
                this.contas_pagar_receber.setTipo_conta(tipoConta);
                Map<String, Object> tipo_conta = new HashMap<>();
                tipo_conta.put("==", tipoConta);
                filtros.put("tipo_conta", tipo_conta);
            }

            if (!"TODAS".equals(statusConta)) {
                this.contas_pagar_receber.setStatus_conta(statusConta);
                Map<String, Object> status_conta = new HashMap<>();
                status_conta.put("==", statusConta);
                filtros.put("status_conta", status_conta);
            }

            Map<String, Object> data_inicio = new HashMap<>();
            Map<String, Object> data_fim = new HashMap<>();

            Map<String, Object> data = new HashMap<>();

            data.put(">=", Utils.toDate(dataInicio.getTime(), "00:00:01"));
            data.put("<=", Utils.toDate(dataFim.getTime(), "23:59:59"));

            if ("DATA CADASTRO".equals(tipoData)) {

                filtros.put("data_cadastro", data);

            } else if ("DATA VENCIMENTO".equals(tipoData)) {

                filtros.put("data_vencimento", data);

            } else if ("DATA BAIXA".equals(tipoData)) {

                filtros.put("data_baixa", data);
            }

            List<Document> contas = this.dao.pesquisarDados(this.contas_pagar_receber.getTabela(), filtros, "data_vencimento", "asc", 0);
            DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "FORNECEDOR", "CONTA", "DESCRIÇÃO", "VALOR", "VENCIMENTO", "TIPO", "STATUS", "TRANSAÇÃO"}, 0);

            for (Document conta : contas) {
                model.addRow(new Object[]{
                    conta.getObjectId("_id").toString(),
                    conta.getObjectId("cliente_fornecedor") != null ? conta.getObjectId("cliente_fornecedor").toString() : "",
                    conta.getString("nome_conta"),
                    conta.getString("descricao"),
                    conta.getDouble("valor_conta"),
                    Utils.convertData(conta.getDate("data_vencimento")),
                    conta.getString("tipo_conta"),
                    conta.getString("status_conta"),
                    conta.getString("transacao") != null ? conta.getString("transacao") : ""
                });
            }

            JIFContasPagarReceber.tabela.setModel(model);
            TableUtil.ajustarLarguraColunas(JIFContasPagarReceber.tabela);
            TableUtil.colorirLinhasPorStatus(JIFContasPagarReceber.tabela, "TIPO", "STATUS");
        } catch (Exception ex) {
            Mensagens.ErroException(ex);
        } finally {
            DB.close();
        }
    }

    /**
     * Função responsável por pesquisar todas as contas com o status vencida que
     * o sistema possui, Idenpendentemente do mês.
     */
    public void pesquisarTodasContasVencidas() {
        this.empresa = JIFContasPagarReceber.empresa;

        Map<String, Object> filtros = new HashMap<>();
        Map<String, Object> empresa_filtro = new HashMap<>();
        Map<String, Object> status_conta = new HashMap<>();

        empresa_filtro.put("==", this.empresa.getId());
        filtros.put("empresa", empresa_filtro);

        status_conta.put("==", "VENCIDA");
        filtros.put("status_conta", status_conta);

        List<Document> contas = this.dao.pesquisarDados(this.contas_pagar_receber.getTabela(), filtros, "data_vencimento", "asc", 0);
        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "FORNECEDOR", "CONTA", "DESCRIÇÃO", "VALOR", "VENCIMENTO", "TIPO", "STATUS", "TRANSAÇÃO"}, 0);

        for (Document conta : contas) {
            model.addRow(new Object[]{
                conta.getObjectId("_id").toString(),
                conta.getObjectId("cliente_fornecedor") != null ? conta.getObjectId("cliente_fornecedor").toString() : "",
                conta.getString("nome_conta"),
                conta.getString("descricao"),
                conta.getDouble("valor_conta"),
                Utils.convertData(conta.getDate("data_vencimento")),
                conta.getString("tipo_conta"),
                conta.getString("status_conta"),
                conta.getString("transacao") != null ? conta.getString("transacao") : ""
            });
        }

        JIFContasPagarReceber.tabela.setModel(model);
        TableUtil.ajustarLarguraColunas(JIFContasPagarReceber.tabela);
        TableUtil.colorirLinhasPorStatus(JIFContasPagarReceber.tabela, "TIPO", "STATUS");
    }

    /**
     * Função responsável por retornar a quantidade de contas vencidas, caso
     * essa quantidade seja diferente de 0, ativa o botão de pesquisar, todas as
     * contas vencidas do sistema.
     */
    public void contarQuantidadeContaVencidaMesAnterior() {
        this.empresa = JIFContasPagarReceber.empresa;

        Map<String, Object> filtros = new HashMap<>();
        Map<String, Object> empresa_filtro = new HashMap<>();
        Map<String, Object> status_conta = new HashMap<>();
        Map<String, Object> primeiro_dia = new HashMap<>();

        empresa_filtro.put("==", this.empresa.getId());
        filtros.put("empresa", empresa_filtro);

        status_conta.put("==", "VENCIDA");
        filtros.put("status_conta", status_conta);

        Calendar diaUm = Utils.primeiroDiaMesAtual();
        primeiro_dia.put("<=", Utils.toDate(diaUm.getTime(), "00:00:00"));
        filtros.put("data_vencimento", primeiro_dia);

        List<Document> quantidadeContasObjeto = this.dao.pesquisarDados(this.contas_pagar_receber.getTabela(), filtros, "data_vencimento", "asc", 0);
        if (!quantidadeContasObjeto.isEmpty()) {
            JIFContasPagarReceber.btnOutrosMeses.setEnabled(true);
        }
    }

    @Override
    public void selecionarDados() {
        try{
            DB.conectar();
            
            Map<String, Object> filtros = new HashMap<>();
            Map<String, Object> codigoConta = new HashMap<>();
            
            this.contas_pagar_receber.setId(Utils.toId(JIFContasPagarReceber.tabela.getValueAt(JIFContasPagarReceber.tabela.getSelectedRow(), 0).toString()));
            String fornecedor = JIFContasPagarReceber.tabela.getValueAt(JIFContasPagarReceber.tabela.getSelectedRow(), 1).toString();
            String transacao = JIFContasPagarReceber.tabela.getValueAt(JIFContasPagarReceber.tabela.getSelectedRow(), 8).toString();
            
            if("".equals(fornecedor)){
                Mensagens.Validacao("FORNECEDOR");
                return;
            }
            
            if("".equals(transacao)){
                Mensagens.Validacao("TRANSAÇÃO");
                return;
            }
            
            codigoConta.put("==",this.contas_pagar_receber.getId());
            filtros.put("_id", codigoConta);
            
            List<Document> retornoBanco = this.dao.pesquisarDados(this.contas_pagar_receber.getTabela(), filtros, "data_cadastro", "asc", 0);
         
            if(retornoBanco.size() == 1){
                String codigo = "";
                for(Document conta: retornoBanco){
                    LimparCampos(true);
                    
                    JIFContasPagarReceber.tbxCodigoContaBaixaContas.setText(conta.getObjectId("_id").toString());
                    JIFContasPagarReceber.tbxFornecedorBaixaContas.setText(conta.getObjectId("cliente_fornecedor").toString());
                    JIFContasPagarReceber.tbxContaFornecedorBaixaContas.setText(conta.getObjectId("conta_fornecedor").toString());
                    JIFContasPagarReceber.tbxNomeContaBaixaContas.setText(conta.getString("nome_conta"));
                    JIFContasPagarReceber.tbxDescricaoBaixaContas.setText(conta.getString("descricao"));
                    
                    JIFContasPagarReceber.tbxValorContaBaixaContas.setText(String.valueOf(conta.getDouble("valor_conta")).replace(".", ","));
                    JIFContasPagarReceber.tbxValorPagoBaixaContas.setText(String.valueOf(conta.getDouble("valor_pago")).replace(".", ","));
                    JIFContasPagarReceber.tbxValorJuroDescontoBaixaContas.setText(String.valueOf(conta.getDouble("valor_juro_desconto")).replace(".", ","));
                    
                    JIFContasPagarReceber.jcDataCadastroBaixaContas.setCalendar(Utils.dateToCalendar(conta.getDate("data_cadastro")));
                    JIFContasPagarReceber.jcDataVencimentoBaixaContas.setCalendar(Utils.dateToCalendar(conta.getDate("data_vencimento")));
                    JIFContasPagarReceber.jcDataPagamentoBaixaContas.setCalendar(Utils.dateToCalendar(conta.getDate("data_baixa")));
                    
                    JIFContasPagarReceber.cbJuroDescontoBaixaContas.setSelectedItem(conta.getString("tipo_juro_desconto"));
                    JIFContasPagarReceber.cbTipoContaBaixaContas.setSelectedItem(conta.getString("tipo_conta"));
                    JIFContasPagarReceber.cbStatusContaBaixaContas.setSelectedItem(conta.getString("status_conta"));
                    JIFContasPagarReceber.cbComprovanteBaixaContas.setSelectedItem(conta.getString("comprovante"));
                    JIFContasPagarReceber.cbBoletoBaixaContas.setSelectedItem(conta.getString("boleto"));
                    JIFContasPagarReceber.tbxTransacaoBaixaContas.setText(conta.getString("transacao"));
                }   
            }else{
                Mensagens.DadosNaoEncontrados();
            }
        }catch(Exception ex){
            Mensagens.ErroException(ex);
        }finally{
            DB.close();
        }
    }

    @Override
    public void LimparCampos() {

    }

    public void LimparCampos(boolean baixaConta) {
        JIFContasPagarReceber.tbxNomeContaBaixaContas.setEnabled(baixaConta);
        JIFContasPagarReceber.tbxDescricaoBaixaContas.setEnabled(baixaConta);
        JIFContasPagarReceber.tbxValorContaBaixaContas.setEnabled(baixaConta);
        JIFContasPagarReceber.tbxValorPagoBaixaContas.setEnabled(baixaConta);
        JIFContasPagarReceber.tbxValorJuroDescontoBaixaContas.setEnabled(baixaConta);
        
        JIFContasPagarReceber.cbJuroDescontoBaixaContas.setEnabled(baixaConta);
        JIFContasPagarReceber.cbTipoContaBaixaContas.setEnabled(baixaConta);
        JIFContasPagarReceber.cbStatusContaBaixaContas.setEnabled(baixaConta);
        
        JIFContasPagarReceber.jcDataCadastroBaixaContas.setEnabled(baixaConta);
        JIFContasPagarReceber.jcDataPagamentoBaixaContas.setEnabled(baixaConta);
        JIFContasPagarReceber.jcDataVencimentoBaixaContas.setEnabled(baixaConta);
        
        JIFContasPagarReceber.tbxCodigoContaBaixaContas.setText("");
        JIFContasPagarReceber.tbxFornecedorBaixaContas.setText("");
        JIFContasPagarReceber.tbxContaFornecedorBaixaContas.setText("");
        
        JIFContasPagarReceber.tbxNomeContaBaixaContas.setText("");
        JIFContasPagarReceber.tbxDescricaoBaixaContas.setText("");
        
        JIFContasPagarReceber.tbxValorContaBaixaContas.setText("");
        JIFContasPagarReceber.tbxValorPagoBaixaContas.setText("");
        JIFContasPagarReceber.tbxValorJuroDescontoBaixaContas.setText("");
        
        JIFContasPagarReceber.cbJuroDescontoBaixaContas.setSelectedIndex(0);
        JIFContasPagarReceber.cbTipoContaBaixaContas.setSelectedIndex(0);
        JIFContasPagarReceber.cbStatusContaBaixaContas.setSelectedIndex(0);
        JIFContasPagarReceber.cbComprovanteBaixaContas.setSelectedIndex(0);
        JIFContasPagarReceber.cbBoletoBaixaContas.setSelectedIndex(0);
        JIFContasPagarReceber.tbxTransacaoBaixaContas.setText("");
        
        JIFContasPagarReceber.btnPagarBaixaContas.setEnabled(baixaConta);
    }

    /**
     * Função que é carregada quando o formulário é aberto pelo sistema
     */
    public void LoadFormulario() {
        try {
            DB.conectar();

            this.empresa = JIFContasPagarReceber.empresa;

            Map<String, Object> filtros = new HashMap<>();
            Map<String, Object> empresa_filtro = new HashMap<>();
            empresa_filtro.put("==", this.empresa.getId());
            filtros.put("empresa", empresa_filtro);

            UsuarioDao daoUsuario = new UsuarioDao();
            List<Document> fornecedores = daoUsuario.pesquisarDados(this.fornecedor.getTabela(), filtros, "nome_usuario", "asc", 0);
            DefaultComboBoxModel<UsuariosJComboBox> model = new DefaultComboBoxModel<>();

            model.addElement(new UsuariosJComboBox("", ""));

            for (Document forne : fornecedores) {
                model.addElement(new UsuariosJComboBox(forne.getObjectId("_id").toString(), forne.getString("nome_usuario")));
            }

            JIFContasPagarReceber.jcbFornecedorPesquisa.setModel(model);
            JIFContasPagarReceber.jcbFornecedorCadastro.setModel(model);

            JIFContasPagarReceber.jcDataInicial.setCalendar(Utils.primeiroDiaMesAtual());
            JIFContasPagarReceber.jcDataFinal.setCalendar(Utils.ultimoDiaMesAtual());

            JIFContasPagarReceber.jcDataVencimentoCadastro.setCalendar(Utils.diaAtualMais30Dias());
            JIFContasPagarReceber.jcDataBaixaCadastro.setCalendar(Utils.diaAtualMais30Dias());

            this.contarQuantidadeContaVencidaMesAnterior();

            JIFContasPagarReceber.tbxTransacaoCadastro.setText(Utils.getTransacao());
        } catch (Exception ex) {
            Mensagens.ErroException(ex);
        } finally {
            DB.close();
        }
    }

    public void PesquisarContaFornecedor(JComboBox localInformacao, JComboBox ondeVaiInformacao) {
        try {
            DB.conectar();

            UsuariosJComboBox usuario = (UsuariosJComboBox) localInformacao.getSelectedItem();

            String codigo = usuario.getId();

            if (!"".equals(codigo)) {

                this.fornecedor.setId(Utils.toId(usuario.getId()));
                this.contas_fornecedores.setFornecedor(this.fornecedor);

                ContaFornecedoresDao contaFornecedorDao = new ContaFornecedoresDao();

                Map<String, Object> filtros = new HashMap<>();
                Map<String, Object> codigoContaFornecedor = new HashMap<>();
                codigoContaFornecedor.put("==", this.contas_fornecedores.getFornecedor().getId());
                filtros.put("fornecedor", codigoContaFornecedor);

                List<Document> contas = contaFornecedorDao.pesquisarDados(this.contas_fornecedores.getTabela(), filtros, "nome_conta", "asc", 0);

                DefaultComboBoxModel<ContasFornecedoresJComboBox> model = new DefaultComboBoxModel<>();
                model.addElement(new ContasFornecedoresJComboBox("", ""));

                for (Document conta : contas) {
                    model.addElement(new ContasFornecedoresJComboBox(conta.getObjectId("_id").toString(), conta.getString("nome_conta")));
                }

                ondeVaiInformacao.setModel(model);
            } else {
                DefaultComboBoxModel<ContasFornecedoresJComboBox> model = new DefaultComboBoxModel<>();
                model.addElement(new ContasFornecedoresJComboBox("", ""));
                ondeVaiInformacao.setModel(model);
            }
        } catch (Exception ex) {
            Mensagens.ErroException(ex);
        } finally {
            DB.close();
        }
    }

    /**
     * Função responsável por pegar toda a informação do formulário de cadastro
     * e adicioanr a tabela de contas recorrentes, para então realizar o
     * cadastro.
     */
    public void AdicionarContaRecorrenteTabela() {
        try {
            if (this.linhaTabela == 1) {
                this.modelTabelaContasCadastro = new DefaultTableModel(new Object[]{"ID", "FORNECEDOR", "ID CONTA", "NOME DA CONTA", "DESCRIÇÃO", "VALOR CONTA", "VALOR PAGO", "VALOR JURO/DESCONTO", "TIPO JURO/DESCONTO", "TIPO", "STATUS", "TRANSAÇÃO", "DATA CADASTRO", "DATA VENCIMENTO", "DATA BAIXA", "BOLETO", "COMPROVANTE"}, 0);
            }

            UsuariosJComboBox usuario = (UsuariosJComboBox) JIFContasPagarReceber.jcbFornecedorCadastro.getSelectedItem();
            ContasFornecedoresJComboBox contas = (ContasFornecedoresJComboBox) JIFContasPagarReceber.jcbContaFornecedorCadastro.getSelectedItem();

            String codigo = usuario.getId();
            String codigoConta = contas.getId();
            String nomeConta = contas.getNomeConta();
            String descricao = JIFContasPagarReceber.tbxDescricaoCadastro.getText();
            String valorConta = JIFContasPagarReceber.tbxValorContaCadastro.getText();
            String valorPago = JIFContasPagarReceber.tbxValorPagoCadastro.getText();
            String valorJuroDesconto = JIFContasPagarReceber.tbxValorJuroDescontoCadastro.getText();
            String tipoJuroDesconto = JIFContasPagarReceber.jcbTipoJuroDescontoCadastro.getSelectedItem().toString();
            String tipoConta = JIFContasPagarReceber.jcbTipoContaCadastro.getSelectedItem().toString();
            String statusConta = JIFContasPagarReceber.jcbStatusContaCadastro.getSelectedItem().toString();
            String transacao = JIFContasPagarReceber.tbxTransacaoCadastro.getText();
            String boleto = JIFContasPagarReceber.jcbBoletoCadastro.getSelectedItem().toString();
            String comprovante = JIFContasPagarReceber.jcbComprovanteCadastro.getSelectedItem().toString();
            Calendar calendarioDataCadastro = JIFContasPagarReceber.jcDataVencimentoCadastro.getCalendar();
            Calendar calendarioDataVencimento = JIFContasPagarReceber.jcDataVencimentoCadastro.getCalendar();
            Calendar calendarioDatabaixa = JIFContasPagarReceber.jcDataBaixaCadastro.getCalendar();

            String dataCadastro = Utils.convertData(calendarioDataCadastro.getTime());
            String dataVencimento = Utils.convertData(calendarioDataVencimento.getTime());
            String dataBaixa = Utils.convertData(calendarioDatabaixa.getTime());

            String[] valores = {
                codigo,
                nomeConta,
                valorConta,
                descricao,
                transacao,
                valorPago,
                valorJuroDesconto
            };

            String[] nomes = {
                "FORNECEDOR",
                "NOME DA CONTA",
                "VALOR CONTA",
                "DESCRIÇÃO",
                "TRANSAÇÃO",
                "VALOR PAGO",
                "VALOR JURO/DESCONTO"
            };

            for (int i = 0; i < valores.length; i++) {
                if (!Validations.validationEmptyString(valores[i], nomes[i])) {
                    return;
                }
            }

            if ("SELECIONE".equals(tipoJuroDesconto)) {
                tipoJuroDesconto = "";
            }

            this.modelTabelaContasCadastro.addRow(new Object[]{
                linhaTabela,
                codigo,
                codigoConta,
                nomeConta,
                descricao.toUpperCase().trim(),
                valorConta.replace(".", ","),
                valorPago.replace(".", ","),
                valorJuroDesconto.replace(".", ","),
                tipoJuroDesconto,
                tipoConta,
                statusConta,
                transacao,
                dataCadastro,
                dataVencimento,
                dataBaixa,
                boleto,
                comprovante
            });

            JIFContasPagarReceber.tabelaCadastroContaRecorrente.setModel(this.modelTabelaContasCadastro);
            TableUtil.ajustarLarguraColunas(JIFContasPagarReceber.tabela);

            JIFContasPagarReceber.btnSalvarCadastro.setEnabled(true);

            linhaTabela++;

            if (linhaTabela > 1 && this.alteracao == true) {
                JIFContasPagarReceber.btnAdicionarRecorrencia.setEnabled(false);
            }
        } catch (Exception ex) {
            Mensagens.ErroException(ex);
        }
    }
}
