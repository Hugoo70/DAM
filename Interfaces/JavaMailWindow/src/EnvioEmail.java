import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;


public class EnvioEmail {

	/*
	 * Outgoing Mail (SMTP) Server
	 * requires TLS or SSL: smtp.gmail.com (SSL)
	 * Use Authentication
	 * Port for SSL: 465
	 */
	
	public static void enviarMail(String Email, String asunto, String body) {
		final String fromEmail = "hugojimenezsoler05@gmail.com"; // Email de salida
		final String password = "lcfj sywz crxn uctf"; // contraseña del email de salida
		final String toEmail = Email; // Email destinatario
		
		System.out.println("Configurando datos conexión SSL");
		
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
		System.out.println("Sesión creada");
		
		/*
		 * Llamada al método sendEmail con todos los datos configurados
		 * session
		 * toEmail
		 * subject
		 * body
		 */
		EmailUtil.sendEmail(session, toEmail, asunto, body);
		}
}
