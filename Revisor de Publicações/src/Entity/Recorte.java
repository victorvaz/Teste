package Entity;

/**
 * Entidade recorte
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class Recorte
{
    /**
     * Nome do recorte
     */
    private String NomeRecorte;

    /**
     * Função para retornar um recorte
     * @return o NomeRecorte
     */
    public String getNomeRecorte()
    {
        return NomeRecorte;
    }

    /**
     * Função para definir um recorte
     * @param NomeRecorte o NomeRecorte que será definido
     */
    public void setNomeRecorte(String NomeRecorte)
    {
        this.NomeRecorte = NomeRecorte;
    }
}