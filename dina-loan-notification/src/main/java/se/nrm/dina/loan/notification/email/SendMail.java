package se.nrm.dina.loan.notification.email;
  
import java.util.Properties;    
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;   
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress; 
import javax.mail.internet.MimeMessage; 

/**
 *
 * @author idali
 */
@Stateless
public class SendMail {
    
//    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final int SMTP_HOST_PORT = 587;
    private static final String SMTP_AUTH_USER = "dina@mail.dina-web.net";
    private static final String SMTP_AUTH_PWD = "D-I-N-A";
    
    
    private final static String SMTP_HOST_NAME = "mail.dina-web.net";
    private final static String HOST = "mail.dina-web.net";  
    
    public static void main(String[] args) {

        try {
            Properties props = new Properties();

            props.put(SMTP_HOST_NAME, HOST);
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtps.host", SMTP_HOST_NAME);
            props.put("mail.smtps.auth", "true");
            props.put("mail.smtp.port", SMTP_HOST_PORT);
            props.put("mail.smtp.starttls.enable", "true");

            Session mailSession = Session.getInstance(props, null);
            mailSession.setDebug(true);
 
            Message message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress("dina@mail.dina-web.net"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("ida.li@nrm.se"));
            message.setSubject("test mail");
            message.setText("test");

            InternetAddress[] toaddress = new InternetAddress[]{new InternetAddress("ida.li@nrm.se")};
 
            Transport transport = mailSession.getTransport();
            transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD); 
            transport.sendMessage(message, toaddress); 
            transport.close(); 
        } catch (MessagingException ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }
}
