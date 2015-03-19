package Core;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Classe responsável por disparar os e-mails da aplicação.
 * @author Victor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class Email
{

    /**
     * Endereço de e-mail do usuário que está disponibilizado para ser utilizado
     * para os envios de e-mail da aplicação.
     */
    private static final String EMAIL = "victor.vaz@vistaes.com.br";
    /**
     * Senha do endereço de e-mail do usuário que está disponibilizado para ser
     * utilizado para os envios de e-mail da aplicação.
     */
    private static final String SENHA = "Vistaabc123";
    /**
     * URL do Host responsável pelos envios de e-mail.
     */
    private static final String SMTP_HOST = "mail.vistaes.com.br";
    /**
     * Posta do Host responsável pelos envios de e-mail.
     */
    private static final int SMTP_PORT = 587;
    /**
     * Informa se o Host responsável pelos envios de e-mail utiliza
     * autenticação.
     */
    private static final boolean SMTP_AUTH = true;
    /**
     * Destinatário do e-mail
     */
    private String destinatario;
    /**
     * corpo do e-mail
     */
    private String corpo;
    /**
     * assunto do e-mail
     */
    private String assunto;

    /**
     * @return o destinatario
     */
    private String getDestinatario()
    {
        return destinatario;
    }

    /**
     * Função para definir o destinatário do e-mail.
     * @param Destinatario o destinatário que será definido.
     */
    public void setDestinatario(String Destinatario)
    {
        this.destinatario = Destinatario;
    }

    /**
     * @return o corpo
     */
    private String getCorpo()
    {
        return corpo;
    }

    /**
     * Função para definir o corpo do e-mail.
     * @param Corpo o corpo que será definido.
     */
    public void setCorpo(String Corpo)
    {
        this.corpo = Corpo;
    }

    /**
     * Função para retornar o assunto.
     * @return o assunto
     */
    private String getAssunto()
    {
        return assunto;
    }

    /**
     * Função para definir um assunto para o e-mail.
     * @param Assunto o assunto que será definido.
     */
    public void setAssunto(String Assunto)
    {
        this.assunto = Assunto;
    }

    /**
     * Função para enviar o e-mail de acordo com as configurações.
     * @throws javax.mail.internet.AddressException
     * @throws javax.mail.MessagingException
     */
    public void enviaEmail() throws AddressException, MessagingException
    {
        Properties props = new Properties();

        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.auth", SMTP_AUTH);
        props.put("mail.smtp.port", SMTP_PORT);

        Session session;
        session = Session.getDefaultInstance(props, new javax.mail.Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(Email.EMAIL, Email.SENHA);
            }
        });

        // Ativa Debug para sessão:
        session.setDebug(false);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(Email.EMAIL));

        Address[] toUser = InternetAddress.parse(this.getDestinatario());

        message.setRecipients(Message.RecipientType.TO, toUser);
        message.setSubject(this.getAssunto());
        message.setContent(this.getCorpo(), "text/html; charset=utf-8");

        Transport.send(message);
    }
}
