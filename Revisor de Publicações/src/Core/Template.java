package Core;

import java.io.IOException;
import java.io.InputStream;
import javax.mail.MessagingException;

/**
 * Classe responsável por gerenciar templates para a aplicação.
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class Template
{
    /**
     * Diretório do template de email de excecao.
     */
    public static final String TEMPLATE_EMAIL_EXCECAO = "Template/template_email_excecao.html";

    /**
     * Buffer para a leitura do arquivo template.
     */
    private final byte[] BUFFER = new byte[4096];

    /**
     * Função para carregar um template e retornar o seu conteúdo em uma String.
     * @param template
     * @return String
     * @throws javax.mail.MessagingException
     * @throws java.io.IOException
     */
    public String carregar(String template) throws MessagingException, IOException
    {
        StringBuilder html;

        try (InputStream cInputStream = getClass().getResourceAsStream(template))
        {
            html = new StringBuilder();
            int b;
            while ((b = cInputStream.read(BUFFER, 0, BUFFER.length)) != -1)
            {
                html.append(new String(BUFFER));
            }
        }

        return html.toString();
    }
}
