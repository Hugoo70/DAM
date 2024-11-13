package Paneles;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.*;

public class Mecanografia extends JFrame {
	// Carga
	Timer crono;
	private int contador;

	// Dimension de pantalla
	private Dimension pantallaCompleta;

	// Jpanels
	private Login login;
	private Carga carga;

	public Mecanografia() {

		setIconImage(Toolkit.getDefaultToolkit().getImage("src/img/logo.png"));
		login = new Login();
		carga = new Carga();

		login.setVisible(false);
		carga.setVisible(true);

		// Diseño Pagina de Carga
		setSize(750, 500);
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);

		
		Crono();
		add(carga);

		setVisible(true);
		// Funcion para confirmar el cierre de la APP
		preguntarCerrarApp();

	}

	public void Crono() {

		crono = new Timer(1000, e -> {
			contador += 1;
			carga.getBarraProgreso().setValue(contador);

			VerificarArchivos(contador);
		});
		crono.start();
	}

	// Función para confirmar el cierre de la app
	public void preguntarCerrarApp() {

		try {

			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					Confirmarsalir();
					setVisible(true);
				}
			});

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error inseperado", "Error", 0);
			System.exit(ABORT);
		}
	}

	private void Confirmarsalir() {
		int valor = JOptionPane.showConfirmDialog(this, "¿Esta seguro de cerrar la aplicacion?", "Advertencia",
				JOptionPane.YES_NO_OPTION);
		if (valor == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	void VerificarArchivos(int contador) {
		if (contador == 4) {

			//ArchivosTXT();

		}

		if (contador == 6) {
			crono.stop();
			dispose();

			carga.setVisible(false);
			setTitle("INICIO DE SESIÓN");
			login.setVisible(true);
			setUndecorated(false);

			//btnLogin();

			setVisible(true);
			setSize(400, 500);
			setLocationRelativeTo(null);

			add(login);
		}
	}

	public void ArchivosTXT() {

		File usuarioFile = new File("src/TXT/Usuarios.txt");
		File estadisticasFile = new File("src/TXT/estadisticas.txt");
		File textosFiles = new File("src/TXT/textos.txt");


		if (!(usuarioFile.exists() && estadisticasFile.exists() && textosFiles.exists())) {

			JOptionPane.showMessageDialog(null, "Error, Fichero no encontrado!", "ERROR 520", 0);
			System.exit(0);

		}
	}
	
	

}
