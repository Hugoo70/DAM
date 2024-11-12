package ProyectoHugo;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Carga extends JPanel {

	Timer crono;
	int i = 6;

	public Carga() {
		setLayout(null);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(70, 182, 348, 22);
		add(progressBar);

		JLabel lblNewLabel = new JLabel("Bienvenido!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblNewLabel.setBounds(86, 57, 348, 114);
		add(lblNewLabel);

		JLabel contador = new JLabel("");
		contador.setFont(new Font("Tahoma", Font.PLAIN, 39));
		contador.setBounds(220, 215, 120, 77);
		add(contador);

		crono = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				i--;
				contador.setText(String.valueOf(i));
				progressBar.setValue(progressBar.getValue() + 20);

				if (i == 0) {
					crono.stop();
					setVisible(false);
					Login login = new Login();
					add(login);
				}
				if (i == 4) {
					String users = "Usuarios.txt";
					String text = "Textos.txt";
					String estat = "Estadisticas.txt";
					String[] ficheros = { users, text, estat };
					String ruta = "C:\\Users\\CFGS.LAB37_PC\\Desktop\\DAM\\Interfaces\\ProyectoMecanografiaHJS\\txt\\";

					try {
						VerificarFicheros(ficheros, ruta);


					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}

		});

		crono.start();

	}


	public static void VerificarFicheros(String[] name, String ruta) throws IOException {

		for (int i = 0; i < name.length; i++) {
			File File = new File(ruta + name[i]);
			if (!File.exists()) {
				JOptionPane.showMessageDialog(null, "Error falta archivo: " + name[i], "MecanoDAM", 0);
				System.exit(1);

			}
		}

	}
}
