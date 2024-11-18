import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.JTextField;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.swing.JButton;
import javax.swing.JProgressBar;

public class Window {

	private JFrame frame;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblTo = new JLabel("To:");
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTo.setBounds(43, 27, 27, 55);
		panel.add(lblTo);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(113, 47, 194, 20);
		panel.add(textField_1);

		JLabel lblAsunto = new JLabel("Asunto:");
		lblAsunto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAsunto.setBounds(10, 58, 61, 55);
		panel.add(lblAsunto);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(113, 78, 194, 20);
		panel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(113, 109, 194, 61);
		panel.add(textField_3);

		JLabel lblCuerpo = new JLabel("Cuerpo:");
		lblCuerpo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCuerpo.setBounds(10, 93, 67, 55);
		panel.add(lblCuerpo);

		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.setBounds(113, 181, 89, 23);
		panel.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("Enviado");
		lblNewLabel_1.setBounds(212, 181, 95, 14);
		lblNewLabel_1.setVisible(false);
		panel.add(lblNewLabel_1);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JTextField To = textField_1;
				JTextField asunto = textField_2;
				JTextField Body = textField_3;
				String body = Body.getText();
				//String b = Leer.leerFichero(body);
				for (int i = 0; i<200;i++){
					EnvioEmail.enviarMail(To.getText(), asunto.getText(), body);
					
				}
				
				lblNewLabel_1.setVisible(true);
				//textField_3.setText(b);

			}
		});

	}
}
