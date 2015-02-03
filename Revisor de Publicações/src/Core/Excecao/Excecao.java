package Core.Excecao;

import Core.Email.Email;
import Core.Template.Template;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * Classe que implementa as medidas a serem tomadas em um caso de exceção.
 * <p>
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class Excecao
{

    /**
     * Array com os destinatários que receberão os e-mails quando ocorrer uma
     * exceção.
     */
    private static final String[] DESTINATARIOS_EMAIL =
    {
        "victor.vaz@vistaes.com.br"
    };
    /**
     * Nome do erro que aconteceu.
     */
    private final String NOME_DO_ERRO;
    /**
     * Nome da classe na qual aconteceu o erro.
     */
    private final String CLASSE_RESPONSAVEL;
    /**
     * Descrição do erro que aconteceu.
     */
    private final String DESCRICAO;

    /**
     * Classe responsável por gerenciar as exceções do sistema.
     * <p>
     * @param nomeErro
     * @param classeResponsavel
     * @param descricao
     */
    public Excecao(String nomeErro, String classeResponsavel, String descricao)
    {
        // Guarda o parâmetro "nomeErro" no atributo da classe "NOME_DO_ERRO":
        this.NOME_DO_ERRO = nomeErro;
        // Guarda o parâmetro "classeResponsavel" no atributo da classe "CLASSE_RESPONSAVEL":
        this.CLASSE_RESPONSAVEL = classeResponsavel;
        // Guarda o parâmetro "descricao" no atributo da classe "DESCRICAO":
        this.DESCRICAO = descricao;

        // Mostra na tela utilizando o método System.err.println:
        JOptionPane.showMessageDialog(null, "Aconteceu um erro: " + NOME_DO_ERRO + " na " + CLASSE_RESPONSAVEL + ": " + DESCRICAO);
        System.out.println("Aconteceu um erro: " + NOME_DO_ERRO + " na " + CLASSE_RESPONSAVEL + ": " + DESCRICAO);

        // Efetua a notificação por e-mail:
        this.notificarViaEmail();
    }

    /**
     * Função enviar a notificação de exceção por e-mail.
     */
    private void notificarViaEmail()
    {
        // Captura a data e hora atual:
        String dataHoraAtual = new SimpleDateFormat("dd/MM/yyyy H:m:s").format(new Date());

        // Captura a string do template para ser editado posteriormente:
        String textoTemplate = new Template().carregar(Template.TEMPLATE_EMAIL_EXCECAO);
        // Faz a substituição das variáveis do template:
        textoTemplate = textoTemplate.replace("{%__NOME_DO_ERRO__%}", NOME_DO_ERRO);
        textoTemplate = textoTemplate.replace("{%__CLASSE_RESPONSAVEL__%}", CLASSE_RESPONSAVEL);
        textoTemplate = textoTemplate.replace("{%__DATA_HORA_ATUAL__%}", dataHoraAtual);
        textoTemplate = textoTemplate.replace("{%__DESCRICAO__%}", DESCRICAO);

        // Inicia a variável que irá ser concatenada para criar uma string de
        // destinatários separados por vírgula:
        String destinatario = "";
        // Concatena os destinatários de recebimento de e-mail para criar uma string
        // de destinatários separados por vírgula:
        for (String destinatarioAtual : DESTINATARIOS_EMAIL)
        {
            destinatario += "," + destinatarioAtual;
        }
        // Retira a última vírgula da variável de destinatários:
        destinatario = destinatario.substring(1);

        // Instancia a classe Email que será responsável por enviar o e-mail de exceção:
        Email cEmail = new Email();
        // Define o assunto do e-mail:
        cEmail.setAssunto("L.O.D.O. - Aconteceu um problema");
        // Define o corpo do e-mail:
        cEmail.setCorpo(textoTemplate);
        // Define os destinatários do e-mail:
        cEmail.setDestinatario(destinatario);
        // Efetua o envio do e-mail:
        cEmail.enviaEmail();
    }
}
