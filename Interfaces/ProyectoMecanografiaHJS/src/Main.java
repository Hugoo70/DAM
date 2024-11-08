import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.*;

import java.awt.BorderLayout;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

public class Main {

	private JFrame frmMecanodamSl;
	Timer crono;
	int i = 6;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmMecanodamSl.setVisible(true);
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

		int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

		frmMecanodamSl = new JFrame();
		frmMecanodamSl.setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\Users\\CFGS.LAB37_PC\\Desktop\\DAM\\Interfaces\\ProyectoMecanografiaHJS\\img\\logo.png"));
		frmMecanodamSl.setTitle("MecanoDAM S.L.");
		frmMecanodamSl.setBounds(0, 0, ancho, alto);
		frmMecanodamSl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMecanodamSl.getContentPane().setLayout(null);
				
						JPanel Carga = new JPanel();
						Carga.setBounds(0, 0, 1904, 1041);
						frmMecanodamSl.getContentPane().add(Carga);
						Carga.setLayout(null);
						
								JProgressBar progressBar = new JProgressBar();
								progressBar.setBounds(640, 489, 709, 26);
								Carga.add(progressBar);
								
										JLabel lblNewLabel = new JLabel("Bienvenido!");
										lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 60));
										lblNewLabel.setBounds(821, 327, 386, 114);
										Carga.add(lblNewLabel);
										
												JLabel contador = new JLabel("");
												contador.setFont(new Font("Tahoma", Font.PLAIN, 39));
												contador.setBounds(954, 526, 120, 77);
												Carga.add(contador);
		
				JPanel panel = new JPanel();
				panel.setVisible(false);
				panel.setBackground(new Color(128, 64, 0));
				panel.setBounds(0, 0, 1904, 1041);
				frmMecanodamSl.getContentPane().add(panel);
				panel.setLayout(null);
				
				textField = new JTextField();
				textField.setEditable(false);
				textField.setBounds(31, 47, 1845, 509);
				panel.add(textField);
				textField.setColumns(10);

		crono = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				i--;
				contador.setText(String.valueOf(i));
				progressBar.setValue(progressBar.getValue() + 20);

				if (i == 0) {
					crono.stop();
					panel.setVisible(true);
					Carga.setVisible(false);
				}
				if (i == 4) {
					String users = "Usuarios.txt";
					String text = "Textos.txt";
					String estat = "Estadisticas.txt";
					String[] ficheros = {users,text,estat};
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
		
		for(int i = 0;i<name.length;i++) {
			File File = new File(ruta+name[i]);
			if (!File.exists()) {
				JOptionPane.showMessageDialog(null, "Error falta archivo: " + name[i], "MecanoDAM", 0);
				System.exit(1);
				
			}
		}
		
		

	}
}
