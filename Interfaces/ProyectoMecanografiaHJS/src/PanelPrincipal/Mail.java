package PanelPrincipal;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class Mail {
	
	public static void enviarMail(String Email, String asunto, String body) {
		final String fromEmail = "hugojimenezsoler05@gmail.com"; // Email de salida
		final String password = "lcfj sywz crxn uctf"; // contraseña del email de salida
		final String toEmail = Email; // Email destinatario
				
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP de gmail en este caso
		props.put("mail.smtp.socketFactory.port", "465"); // Puerta SSL
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL Factory Class
		props.put("mail.smtp.auth", "true"); // Activar SMTP autentificación
		props.put("mail.smtp.port", "465"); // SMTP port
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
				
			}
		};
		Session session = Session.getDefaultInstance(props, auth); // Crea una sesión con todas las propiedades y el "Login"

		sendEmail(session, toEmail, asunto, body);
		}

	public static void sendEmail(Session session, String toEmail, String subject, String body) {
	    try {
	        MimeMessage msg = new MimeMessage(session);
	        // Configurar Cabeceras
	        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	        msg.addHeader("format", "flowed");
	        msg.addHeader("Content-Transfer-Encoding", "8bit");
	        msg.setFrom(new InternetAddress("hugojimenezsoler05@gmail.com", "Hugoo")); // Datos de ejemplo
	        msg.setReplyTo(InternetAddress.parse("hugojimenezsoler05@gmail.com", false));
	        msg.setSubject(subject, "UTF-8");
	        msg.setText(body, "UTF-8");
	        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

	        Transport.send(msg);

	        // Mostrar mensaje de éxito
	        JOptionPane.showMessageDialog(null,
	                "El correo se ha enviado correctamente a: " + toEmail,
	                "Correo enviado!",
	                JOptionPane.INFORMATION_MESSAGE);

	    } catch (javax.mail.SendFailedException e) {
	        // Manejar el error específico de correo no encontrado o inválido
	        JOptionPane.showMessageDialog(null,
	                "Error: El correo proporcionado no es válido o no existe:\n" + toEmail,
	                "Error al enviar el correo",
	                JOptionPane.ERROR_MESSAGE);
	    } catch (Exception e) {
	        // Manejar otros errores
	        JOptionPane.showMessageDialog(null,
	                "Ocurrió un error inesperado al enviar el correo:\n" + e.getMessage(),
	                "Error al enviar el correo",
	                JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
	    }
	}

}
