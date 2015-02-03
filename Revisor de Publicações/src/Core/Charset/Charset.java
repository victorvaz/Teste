package Core.Charset;

/**
 * CLasse com funções referentes a charsets
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class Charset
{
    /**
     * Remove os acentos da string
     * @param texto
     * @return String O texto consertado
     */
    public static String removeAcentos(String texto)
    {
        texto = texto.replace("Á", "A");
        texto = texto.replace("É", "E");
        texto = texto.replace("Í", "I");
        texto = texto.replace("Ó", "O");
        texto = texto.replace("Ú", "U");
        texto = texto.replace("Ã", "A");
        texto = texto.replace("Õ", "O");
        texto = texto.replace("Â", "Â");
        texto = texto.replace("Ô", "O");
        texto = texto.replace("á", "a");
        texto = texto.replace("é", "e");
        texto = texto.replace("í", "i");
        texto = texto.replace("ó", "o");
        texto = texto.replace("ú", "u");
        texto = texto.replace("ã", "a");
        texto = texto.replace("õ", "o");
        texto = texto.replace("â", "a");
        texto = texto.replace("ô", "o");
        
        return texto;
    }

    private Charset()
    {
    }
}
