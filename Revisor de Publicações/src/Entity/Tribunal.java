package Entity;

/**
 * Entidade do tribunal
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class Tribunal
{
    /**
     * ID do tribunal
     */
    private int ID;
    
    /**
     * Sigla do tribunal
     */
    private String Sigla;
    
    /**
     * Nome do tribunal
     */
    private String NomeTribunal;
    
    /**
     * Estado do tribunal
     */
    private Estado Estado;

    /**
     * Função para retornar o ID do tribunal
     * @return o ID
     */
    public int getID()
    {
        return ID;
    }

    /**
     * Função para determinar o ID do tribunal
     * @param ID o ID que será determinado
     */
    public void setID(int ID)
    {
        this.ID = ID;
    }

    /**
     * Função para retornar a sigla do tribunal
     * @return a Sigla
     */
    public String getSigla()
    {
        return Sigla;
    }

    /**
     * Função para determinar a sigla do tribunal
     * @param Sigla a Sigla do diário
     */
    public void setSigla(String Sigla)
    {
        this.Sigla = Sigla;
    }

    /**
     * Função para retornar a nome do tribunal
     * @return o NomeTribunal O nome do tribunal
     */
    public String getNomeTribunal()
    {
        return NomeTribunal;
    }

    /**
     * Função para determinar o nome do tribunal
     * @param NomeTribunal o NomeTribunal que será determinado
     */
    public void setNomeTribunal(String NomeTribunal)
    {
        this.NomeTribunal = NomeTribunal;
    }

    /**
     * Função para retornar o estado do tribunal
     * @return o Estado
     */
    public Estado getEstado()
    {
        return Estado;
    }

    /**
     * Função para determinar o estado do tribunal
     * @param Estado o Estado que será determinado
     */
    public void setEstado(Estado Estado)
    {
        this.Estado = Estado;
    }
}