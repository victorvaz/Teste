package Entity;

/**
 * Entidade estado.
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class Estado
{
    /**
     * Código do estado.
     */
    private int codEstado;
    
    /**
     * sigla do estado.
     */
    private String sigla;
    
    /**
     * nome do estado.
     */
    private String nome;
    
    /**
     * Função para retornar o código do estado.
     * @return Código do estado.
     */
    public int getCodEstado()
    {
        return this.codEstado;
    }

    /**
     * Função para definir o código do estado.
     * @param codigo Código do estado.
     */
    public void setCodEstado(int codigo)
    {
        this.codEstado = codigo;
    }
    
    /**
     * Função para retornar a sigla do estado.
     * @return A sigla do estado.
     */
    public String getSigla()
    {
        return this.sigla;
    }
    
    /**
     * Função para definir a sigla do estado.
     * @param sigla A sigla do estado que será definida.
     */
    public void setSigla(String sigla)
    {
        this.sigla = sigla;
    }
    
    /**
     * Função para retornar o nome do estado.
     * @return O nome do estado.
     */
    public String getNome()
    {
        return this.nome;
    }
    
    /**
     * Função para definir o nome do estado.
     * @param nome nome do estado que será definido.
     */
    public void setNome(String nome)
    {
        this.nome = nome;
    }
}
