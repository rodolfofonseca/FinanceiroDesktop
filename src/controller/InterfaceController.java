package controller;

/**
 *
 * @author RODOLFO
 */
public interface InterfaceController {
    /**
     * Função responsável por colocar os dados do formulário no objeto
     */
    public void colocarDados();
    
    /**
     * Função responsável por cadastrar um novo item no banco de dados
     */
    public void cadastro();
    
    /**
     * Função responsável por verificar se é um novo cadastro ou uma alteração em um cadastro que já existe
     */
    public void validaCadastro();
    
    /**
     * Funçãoa responsável por pesquisar as informações no banco de dados, de acordo
     * com os filtros passados
     */
    public void pesquisarDados();
    
    /**
     * Função responsável por selecionar os dados da tabela e colocar nos campos correspondentes
     */
    public void selecionarDados();
    
    /**
     * Função responsável por limpar os campos da tabela
     */
    public void LimparCampos();
}
