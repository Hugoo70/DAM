

import java.util.ArrayList;

import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;

public class EmailUtil {

	
	public static void sendEmail(Session session, String toEmail, String subject, String body) {
		try {
			MimeMessage msg = new MimeMessage(session);
			// Configurar Cabeceras
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Cotent-TRansfer-Encoding", "8bit");
			msg.setFrom(new InternetAddress("hugojimenezsoler05@gmail.com", "Hugoo")); // Datos de ejemplo
			msg.setReplyTo(InternetAddress.parse("hugojimenezsoler05@gmail.com", false));
			msg.setSubject(subject, "UTF-8");
			msg.setText(body, "UTF-8");
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			System.out.println("Mesaje creado");
			Transport.send(msg);
			System.out.println("Email enviado!"); // Si no da error
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
