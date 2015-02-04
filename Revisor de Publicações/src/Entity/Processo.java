package Entity;

import java.util.Date;

/**
 * Entidade processo
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class Processo
{
    /**
     * Número do processo.
     */
    private int NumProcesso;
    
    /**
     * Data da publicação.
     */
    private Date DataPublicacao;
    
    /**
     * Escritório da publicação.
     */
    private Escritorio Escritorio;
    
    /**
     * Vara da publicação.
     */
    private String Vara;
    
    /**
     * Tribunal da publicação.
     */
    private Tribunal Tribunal;
    
    /**
     * Arquivo da publicação.
     */
    private String Arquivo;
    
    /**
     * Ordem da publicação.
     */
    private int Ordem;
    
    /**
     * Número do processo.
     */
    private String NumeroProcesso;
    
    /**
     * Corpo da publicação.
     */
    private String CorpoPublicacao;

    /**
     * Função para retornar o número do processo.
     * @return o NumProcesso
     */
    public int getNumProcesso()
    {
        return NumProcesso;
    }

    /**
     * Função para definir o número do processo.
     * @param NumProcesso o NumProcesso que será definido
     */
    public void setNumProcesso(int NumProcesso)
    {
        this.NumProcesso = NumProcesso;
    }

    /**
     * Função para retornar a data de publicação
     * @return a DataPublicacao
     */
    public Date getDataPublicacao()
    {
        return DataPublicacao;
    }

    /**
     * Função para definir a data de publicação.
     * @param DataPublicacao a DataPublicacao que será definida
     */
    public void setDataPublicacao(Date DataPublicacao)
    {
        this.DataPublicacao = DataPublicacao;
    }

    /**
     * Função para retornar o escritório
     * @return o Escritorio
     */
    public Escritorio getEscritorio()
    {
        return Escritorio;
    }

    /**
     * Função para definir o escritório
     * @param Escritorio o Escritorio que será definido.
     */
    public void setEscritorio(Escritorio Escritorio)
    {
        this.Escritorio = Escritorio;
    }

    /**
     * Função para retornar a vara
     * @return a Vara
     */
    public String getVara()
    {
        return Vara;
    }

    /**
     * Função para definir a vara
     * @param Vara a Vara que será definida.
     */
    public void setVara(String Vara)
    {
        this.Vara = Vara;
    }

    /**
     * Função para retornar o tribunal
     * @return o Tribunal
     */
    public Tribunal getTribunal()
    {
        return Tribunal;
    }

    /**
     * Função para definir o tribunal da publicação.
     * @param Tribunal o Tribunal que será definido.
     */
    public void setTribunal(Tribunal Tribunal)
    {
        this.Tribunal = Tribunal;
    }

    /**
     * Função que retorna o arquivo
     * @return o Arquivo
     */
    public String getArquivo()
    {
        return Arquivo;
    }

    /**
     * Função para definir o arquivo
     * @param Arquivo o Arquivo que será definido.
     */
    public void setArquivo(String Arquivo)
    {
        this.Arquivo = Arquivo;
    }

    /**
     * Função para retornar a ordem
     * @return a Ordem
     */
    public int getOrdem()
    {
        return Ordem;
    }

    /**
     * Função para definir a ordem
     * @param Ordem a Ordem que será definida
     */
    public void setOrdem(int Ordem) 
    {
        this.Ordem = Ordem;
    }

    /**
     * Função para retornar o número de processo.
     * @return o NumeroProcesso
     */
    public String getNumeroProcesso()
    {
        return NumeroProcesso;
    }

    /**
     * Função para definir o número de processo.
     * @param NumeroProcesso o NumeroProcesso que será definido.
     */
    public void setNumeroProcesso(String NumeroProcesso)
    {
        this.NumeroProcesso = NumeroProcesso;
    }

    /**
     * Função para retornar o corpo da publicação
     * @return o CorpoPublicacao
     */
    public String getCorpoPublicacao()
    {
        return CorpoPublicacao;
    }

    /**
     * Função para definir o corpo da publicação
     * @param CorpoPublicacao o CorpoPublicacao que será definida
     */
    public void setCorpoPublicacao(String CorpoPublicacao)
    {
        this.CorpoPublicacao = CorpoPublicacao;
    }
}