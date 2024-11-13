package ProyectoHugo;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class Carga extends JPanel {
	Timer crono;
	int i = 6;
	
	public Carga() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Bienvenido!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblNewLabel.setBounds(131, 61, 173, 69);
		add(lblNewLabel);

		JLabel contador = new JLabel("");
		contador.setFont(new Font("Tahoma", Font.PLAIN, 24));
		contador.setBounds(205, 169, 39, 33);
		add(contador);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(66, 136, 300, 22);
		add(progressBar);

		crono = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				i--;
				contador.setText(String.valueOf(i));
				progressBar.setValue(progressBar.getValue() + 20);

				if (i == 0) {
					crono.stop();		
					setVisible(false);
					Login log = new Login();
					getRootPane().add(log);
					log.setVisible(true);
					

				}
				if (i == (i-4)) {
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
