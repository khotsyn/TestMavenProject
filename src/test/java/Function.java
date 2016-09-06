import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.openqa.selenium.WebDriver;

public class Function {

	static final String ENCODING = "UTF-8";

	public static void sendEmail(WebDriver driver, String password, String from, String to, String subject)
			throws MessagingException, UnsupportedEncodingException {

		Authenticator auth = new MyAuthenticator(from, password);
		Properties props = System.getProperties();
		String content = "Test";

		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.host", "smtp.yandex.ru");
		props.put("mail.smtp.auth", "true");
		props.put("mail.mime.charset", ENCODING);
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		Session session = Session.getDefaultInstance(props, auth);
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(from));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		msg.setSubject(subject);
		msg.setText(content);		
		Transport.send(msg);
	}
	
}
