package zer.mail;



import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class MailClient
{
  private Properties props;
  private String senderEmail;
  private String senderPassword;
  private String subject;
  private String content;
  private String contentType;
  private String receiverEmail;

  {
    props = new Properties();
    props.setProperty("mail.smtp.starttls.enable", "true");
    props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
    props.setProperty("mail.smtp.auth", "true");
    props.setProperty("mail.smtp.host", "smtp.gmail.com");
    props.setProperty("mail.smtp.port", "587");
  
    contentType = "text/plain";
  } 

  public MailClient contentType(String contentType)
  {
    this.contentType = contentType;
    return this;
  }

  public MailClient message(String subject, String content)
  {
    this.subject = subject; 
    this.content = content;
    return this;
  }

  public MailClient from(String senderEmail, String senderPassword)
  {
    this.senderEmail = senderEmail;
    this.senderPassword = senderPassword;
    return this;
  }

  public MailClient to(String receiverEmail)
  {
    this.receiverEmail = receiverEmail;
    return this;
  }

  public void send()
  {
    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() { return new PasswordAuthentication(senderEmail, senderPassword); }
    });

    try
    {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(senderEmail));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));
      message.setSubject(subject);
      message.setContent(content, contentType);
      
      Transport.send(message);
    }
    catch (MessagingException e) { throw new RuntimeException(e); }
  }
}
