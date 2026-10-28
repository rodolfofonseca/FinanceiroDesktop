package controller.view;

import controller.ContasCtr;
import javax.swing.SwingUtilities;
import model.Contas;
import util.Sessao;
import view.JFDashboard;
import static view.JFDashboard.desktop;
import static view.JFDashboard.lUsuarioLogado;
import view.inferna_frame.pesquisa.JIFContasBancarias;
import view.inferna_frame.cadastro_edicao.JIFContasBancariasCad;

/**
 * Classe responsável por controlar todas as ações do formulário Dashboard
 *
 * @author RodolfoFonseca
 */
public class DashboardCtr {

    /**
     * Função responsável por adicionar uma imagem de plano de fundo dentro do
     * sistema
     */
    public void adicionarPlanoDeFundo(JFDashboard formulario) {
        java.net.URL url = getClass().getResource("/imagens/imagens_sistema/logo.png");

        if (url == null) {
            javax.swing.JOptionPane.showMessageDialog(formulario, "Imagem de fundo não encontrada!");
            return;
        }

        javax.swing.ImageIcon imagem = new javax.swing.ImageIcon(url);

        if (desktop instanceof util.DesktopComFundo) {
            ((util.DesktopComFundo) desktop).setImagemFundo(imagem.getImage());
        } else {
            javax.swing.JOptionPane.showMessageDialog(formulario, "O desktop precisa ser do tipo DesktopComFundo.");
        }
    }

    /**
     * Função que coloca as informações do usuário que está logado no sistema
     * nos componentes.
     */
    public void colocarDadosUsuarioLogado() {
        lUsuarioLogado.setText("Usuário Logado: " + Sessao.usuario.getString("nome"));
    }

    /**
     * Função responsável por abrir o formulário de Dasboard
     */
    public void abrirFomularioContas() {
        SwingUtilities.invokeLater(() -> {
            JIFContasBancarias contasBancarias = new JIFContasBancarias();

            contasBancarias.setSize(800, 400);

            int x = (desktop.getWidth() - contasBancarias.getWidth()) / 2;
            int y = (desktop.getHeight() - contasBancarias.getHeight()) / 2;

            contasBancarias.setLocation(x, y);

            desktop.add(contasBancarias);
            contasBancarias.setVisible(true);

            desktop.revalidate();
            desktop.repaint();

            try {
                contasBancarias.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Função resoponsável por colocar todas as informações nos componentes
     *
     * @param formulario
     */
    public void iniciarFormulario(JFDashboard formulario) {
        this.adicionarPlanoDeFundo(formulario);
        this.colocarDadosUsuarioLogado();
    }

    /**
     * Função responsável por abrir o forumlário que cadastro de contas
     * bancárias
     */
    public void abrirFormularioCadastroConta(Contas conta) {
        SwingUtilities.invokeLater(() -> {
            JIFContasBancariasCad contabancariaCad = new JIFContasBancariasCad();
            desktop.add(contabancariaCad);
            
            if(conta != null){
                contabancariaCad.preencherFormulario(conta);
            }

            int x = (desktop.getWidth() - contabancariaCad.getWidth()) / 2;
            int y = (desktop.getHeight() - contabancariaCad.getHeight()) / 2;
            contabancariaCad.setLocation(x, y);

            contabancariaCad.setVisible(true);

            desktop.revalidate();
            desktop.repaint();

            try {
                contabancariaCad.setSelected(true);
            } catch (java.beans.PropertyVetoException ex) {
                ex.printStackTrace();
            }
        });
    }
}
