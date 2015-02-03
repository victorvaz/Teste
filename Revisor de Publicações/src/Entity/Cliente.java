package Entity;

/**
 * Entidade Cliente.
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class Cliente
{
    /**
     * Num do cliente
     */
    private int Num;
    
    /**
     * Nome do cliente
     */
    private String Nome;

    /**
     * Função para retornar o num do cliente
     * @return o Num
     */
    public int getNum()
    {
        return Num;
    }

    /**
     * Função para definir o num do cliente
     * @param Num the Num to set
     */
    public void setNum(int Num)
    {
        this.Num = Num;
    }

    /**
     * Função para retornar o nome do cliente
     * @return o Nome
     */
    public String getNome()
    {
        return Nome;
    }

    /**
     * Função para definir o nome do cliente
     * @param Nome o Nome do cliente
     */
    public void setNome(String Nome)
    {
        this.Nome = Nome;
    }
}