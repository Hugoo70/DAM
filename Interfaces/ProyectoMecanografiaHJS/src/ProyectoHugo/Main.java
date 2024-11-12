package ProyectoHugo;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.JPanel;

public class Main {
	private JFrame frame;
	Timer crono;
	int i = 6;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Main miFrame = new Main();
					miFrame.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel Carga = new JPanel();
		Carga.setBounds(0, 0, 434, 259);
		frame.getContentPane().add(Carga);
		Carga.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bievenido!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblNewLabel.setBounds(143, 65, 230, 73);
		Carga.add(lblNewLabel);
		
		JLabel contador = new JLabel("");
		contador.setFont(new Font("Tahoma", Font.PLAIN, 24));
		contador.setBounds(200, 200, 65, 48);
		Carga.add(contador);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(80, 149, 293, 22);
		Carga.add(progressBar);
		


		crono = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				i--;
				contador.setText(String.valueOf(i));
				progressBar.setValue(progressBar.getValue() + 20);

				if (i == 0) {
					crono.stop();
					frame.setVisible(false);

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
