package ProyectoHugo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class Login extends JPanel{
	public Login() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Prueba");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblNewLabel.setBounds(225, 186, 725, 362);
		add(lblNewLabel);
	}
}
