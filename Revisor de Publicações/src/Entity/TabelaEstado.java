package Entity;

/**
 * Entidade Tabela do Estado
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class TabelaEstado
{
    /**
     * Nome da tabela
     */
    private String nomeTabela;

    /**
     * Função para retornar o nome da tabela
     * @return o nomeTabela
     */
    public String getNomeTabela()
    {
        return nomeTabela;
    }

    /**
     * Função para definir o nome da tabela
     * @param nomeTabela o nomeTabela que será determinado
     */
    public void setNomeTabela(String nomeTabela)
    {
        this.nomeTabela = nomeTabela;
    }
}