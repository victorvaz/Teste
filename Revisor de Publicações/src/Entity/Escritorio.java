package Entity;

/**
 * Entidade Escritório
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class Escritorio
{
    /**
     * Código do Escritório
     */
    private int Codigo;
    
    /**
     * Nome do Escritório
     */
    private String Nome;
    
    /**
     * Cliente do escritório
     */
    private Cliente Cliente;

    /**
     * Função para retornar o código do escritório
     * @return o Codigo
     */
    public int getCodigo()
    {
        return Codigo;
    }

    /**
     * Função para definir o código do escritório
     * @param Codigo o Código do escritório
     */
    public void setCodigo(int Codigo)
    {
        this.Codigo = Codigo;
    }

    /**
     * Função para retornar o nome do escritório
     * @return o Nome
     */
    public String getNome()
    {
        return Nome;
    }

    /**
     * Função para definir o nome do escritório
     * @param Nome o Nome que será definido.
     */
    public void setNome(String Nome)
    {
        this.Nome = Nome;
    }

    /**
     * Função para retornar o cliente do escritório.
     * @return o Cliente
     */
    public Cliente getCliente()
    {
        return Cliente;
    }

    /**
     * Função para definir o cliente do escritório.
     * @param Cliente o Cliente que será definido.
     */
    public void setCliente(Cliente Cliente)
    {
        this.Cliente = Cliente;
    }
}