package Paneles;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Login extends JPanel {
	private JTextField TextUser;
	private JPasswordField passwordField;
	private JButton BotonLog;

	public Login() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("INICIO DE SESIÓN");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(109, 72, 183, 49);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(80, 182, 73, 24);
		add(lblNewLabel_1);

		TextUser = new JTextField();
		TextUser.setColumns(10);
		TextUser.setBounds(185, 184, 107, 24);
		add(TextUser);

		JLabel lblNewLabel_2 = new JLabel("Contraseña:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(65, 284, 88, 24);
		add(lblNewLabel_2);

		passwordField = new JPasswordField();
		passwordField.setBounds(185, 288, 107, 20);
		add(passwordField);

		JCheckBox BotonPass = new JCheckBox("");
		BotonPass.setBounds(297, 287, 97, 23);
		add(BotonPass);
		
		BotonLog = new JButton("Login");
		BotonLog.setFont(new Font("Tahoma", Font.PLAIN, 18));
		BotonLog.setBounds(129, 362, 137, 43);
		add(BotonLog);

		BotonPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (BotonPass.isSelected()) {
					passwordField.setEchoChar((char) 0);
				}

				if (!BotonPass.isSelected()) {
					passwordField.setEchoChar('*');
				}
			}
		});

	}
	
	public JButton getBotonLog() {
		return BotonLog;
	}

	public void setBotonLog(JButton botonLog) {
		BotonLog = botonLog;
	}

	public JTextField getTextUser() {
		return TextUser;
	}

	public void setTextUser(JTextField textUser) {
		TextUser = textUser;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}
	
}
