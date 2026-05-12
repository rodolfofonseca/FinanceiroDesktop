package controller.forms;
import controller.SistemaCtr;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import model.Empresa;
import model.Sistema;
import view.JFDashboard;
/**
 * Controller que realiza as tarefas do fomulário de dashboard do sistema
 * @author RODOLFO
 */
public class JFDashboardCtr {
    /**
     * Função responsável por pegar a data e a hora a cada segundo e adicionar ao label
     * @param labelHora 
     */
    public void iniciarRelogio(JLabel labelHora) {

        Thread thread = new Thread(() -> {

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            while (true) {

                String horaAtual = formato.format(new Date());

                SwingUtilities.invokeLater(() -> {
                    labelHora.setText(horaAtual);
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        thread.start();
    }
    
    /**
     * Função responsável por pegar os dados do sistema, retornando e adicionado nos campos corretos
     * @param empresa
     * @return 
     */
    public Sistema getDadosSistema(Empresa empresa){
        SistemaCtr controller = new SistemaCtr();
        Sistema sistema = new Sistema();
 
        sistema = controller.getSistema(empresa);
        
        JFDashboard.lVersaoSistema.setText(sistema.getVersao_sistema_java());
        
        return sistema;
    }
}