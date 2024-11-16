package Paneles;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import TXT.LecturaEscritura;
import TXT.Usuario;

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
import java.util.ArrayList;

public class Mecanografia extends JFrame {
	//Arrays
	ArrayList<Usuario> listaUsuarios = new ArrayList<>();

	//Fondo
	private Image image;

	
	// Dimension de pantalla
	private Dimension pantallaCompleta;


	// Jpanels y Clases
	private Login login;
	private Carga carga;
	private LecturaEscritura lecturaEscritura;
	private Dificultad dificultad;
	private Partida partida;


	// Carga
	Timer crono;
	private int contador;
	
	//Login
	private Usuario usuarioLogin;


	public Mecanografia() {

		setIconImage(Toolkit.getDefaultToolkit().getImage("src/img/logo.png"));
		image = requestImage("src/img/fondoCarga.jpg");
		pantallaCompleta = Toolkit. getDefaultToolkit(). getScreenSize();

		lecturaEscritura = new LecturaEscritura();
		login = new Login();
		carga = new Carga(image);

		login.setVisible(false);
		carga.setVisible(true);

		setSize(750, 500);
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);

		
		Crono();
		add(carga);

		setVisible(true);
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

	public void VerificarArchivos(int contador) {
		if (contador == 4) {

			lecturaEscritura.ArchivosTXT();
			lecturaEscritura.FicheroUsuario("src/TXT/Usuarios.txt");
			listaUsuarios = lecturaEscritura.getListaUsuarios();

		}

		if (contador == 6) {
			crono.stop();
			dispose();

			carga.setVisible(false);
			setTitle("INICIO DE SESIÓN");
			login.setVisible(true);
			setUndecorated(false);

			btnLogin();

			setVisible(true);
			setSize(400, 500);
			setLocationRelativeTo(null);

			add(login);
		}
	}
	
	
	public void btnLogin() {

		/*Aqui podemos usar una expresion Lambda ya que la interfaz ActionListener define solo un metodo ActionPerfomed*/
		login.getBotonLog().addActionListener(e -> {

			//Necesitamos el valueOf ya que el getPassword te devuelve un Array
			String pass = String.valueOf( login.getPasswordField().getPassword());
			String name = login.getTextUser().getText();

			for(int i = 0; i<listaUsuarios.size();i++) {
				//Si el usuario y contraseña son correctos
				if((pass.equals(listaUsuarios.get(i).getPass()) && (name.equals(listaUsuarios.get(i).getName()) ))) {
					usuarioLogin = listaUsuarios.get(i);

					//Inicio de un nuevo Panel
					login.setVisible(false);
					setResizable(false);
					setLocationRelativeTo(null);

					setLocationRelativeTo(null);;
					setTitle("MECANOGIAFIA");

					//Añadimos un panel de dificultades
					dificultad = new Dificultad();
					add(dificultad);
					btnDificultades();

				}
			}
		});

	}
	
	public void btnDificultades() {

		dificultad.getBtnDificultades().addActionListener(e -> {
			dispose();
			setSize(pantallaCompleta);
			setLocationRelativeTo(null);
			setVisible(true);
			dificultad.setVisible(false);
			//image = requestImage("src/img/fondo_principal.jpg");

			//FACIL
			if(dificultad.getBtnFacil().isSelected()) {
				JOptionPane.showMessageDialog(null, "Antes de empezar debe saber: \n"
						+ " 1.- El modo cuenta con 200 caracteres.\n"
						+ " 2.- Tiene un maximo de 4 minutos, este empezara a contar al pulsar la primera tecla. \n"
						+ " 3.- Si fallas 5 veces el juego acabara. \n"
						+ " 4.- Si superas el límite de tiempo o llegas al total de errores, te mostrará una ventana emergente y el juego finalizará. \n"
						, "Instrucciones del juego", 1);
				//DICIL
			}else if(dificultad.getBtnDificil().isSelected()) {
				JOptionPane.showMessageDialog(null, "Bienvenido, aqui tienes las instrucciones: \n"
						+ " 1.- El modo cuenta con 1000 caracteres. \n"
						+ " 2.- Tiene un maximo de 3 minutos, este empezara a contar al pulsar la primera tecla. \n"
						+ " 3.- Si fallas 3 veces el juego acabara. \n"
						+ " 4.- Si superas el límite de tiempo o llegas al total de errores, te mostrará una ventana emergente y el juego finalizará. \n"
						, "Instrucciones del juego", 1);
			}
		});
	}
	
	public void dificultades(int dif) {
		partida = new Partida();
		add(partida);
		partida.setVisible(true);
		//menuOpciones();
		//botonBack();

		//btnEventoFallo();
		//btnEventoFelicitaciones();
		
	}

	
	private Image requestImage(String ruta) {
		BufferedImage  image = null;

		try {
			//El imageIO necesita el file para mostrar la imagen de pantalla
			image = ImageIO.read(new File(ruta));
		}catch (IOException e) {
			JOptionPane.showInternalMessageDialog(null, "Error, background no localizado", "Imagen no localizada", 0);
			System.exit(ABORT);
		}

		return image;
	}

}
