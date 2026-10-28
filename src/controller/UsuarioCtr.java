package controller;

import model.Usuario;
import DAO.UsuarioDAO;
import view.JFLoginSistema;
import api.LoginApi;
import com.google.gson.JsonObject;
import javax.swing.SwingWorker;
import org.json.JSONObject;
import util.Alertas;
import util.LoadingDialog;
import util.Sessao;
import view.JFDashboard;

/**
 * Classe responsável por unir todas as partes do sistema para o controle de
 * usuários
 *
 * @author RodolfoFonseca
 */
public class UsuarioCtr implements InterfaceController {

    @Override
    public void colocarDados() {

    }

    @Override
    public void cadastro() {

    }

    @Override
    public void validaCadastro() {

    }

    @Override
    public void pesquisarDados() {

    }

    @Override
    public void selecionarDados() {

    }

    @Override
    public void LimparCampos() {

    }

    /**
     * Função responsável por fazer o login no sistema, por meio da api
     */
    public void LoginSistema(JFLoginSistema JFLoginSistema) {
        String email = JFLoginSistema.tbxEmail.getText().trim();
        String senha = new String(JFLoginSistema.tbxSenha.getPassword());

        if (email.isEmpty() || senha.isEmpty()) {
            Alertas.aviso("Preencha o e-mail e a senha!");
            return;
        }

        LoginApi api = new LoginApi();

        LoadingDialog.mostrar(JFLoginSistema, "Validando suas credenciais...");

        SwingWorker<com.google.gson.JsonObject, Void> worker = new SwingWorker<com.google.gson.JsonObject, Void>() {

            @Override
            protected com.google.gson.JsonObject doInBackground() throws Exception {
                return api.login(email, senha);
            }

            @Override
            protected void done() {

                try {
                    com.google.gson.JsonObject resposta = get();
                    JSONObject respostaJson = new JSONObject(resposta.toString());

                    boolean retorno = resposta.get("sucesso").getAsBoolean();
                    String mensagem = resposta.get("mensagem").getAsString();

                    LoadingDialog.fechar();

                    if (retorno) {
                        Sessao.carregar(respostaJson);

                        /*String versaoSistema = Sessao.sistema.getString("versao_sistema_java");

                        if ("alfa 0.0".equals(versaoSistema)) {
                            Alertas.sucesso(mensagem);
                            JFDashboard dashboard = new JFDashboard();
                            dashboard.setVisible(true);
                            JFLoginSistema.dispose();
                        } else {
                            Alertas.aviso("Sistema desatualizadao. Atualize o sistema");
                        }*/
                    } else {
                        Alertas.erro(mensagem);
                    }

                } catch (Exception e) {
                    LoadingDialog.fechar();

                    Throwable causa = e.getCause();
                    String mensagemErro = causa != null ? causa.getMessage() : e.getMessage();

                    System.err.println("Falha no login: " + mensagemErro);
                    Alertas.erro(mensagemErro != null ? mensagemErro : "Não foi possível realizar o login.");

                } finally {
                    JFLoginSistema.tbxSenha.setText("");
                }
            }
        };

        worker.execute();
    }
}
