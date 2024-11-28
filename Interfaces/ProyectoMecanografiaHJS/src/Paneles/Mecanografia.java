package Paneles;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import Admin.AdminPanel;
import Admin.CambiarLecciones;
import Admin.EnviarEmail;
import Admin.GestionUsuarios;
import PanelPrincipal.Game;
import TXT.Estadisticas;
import TXT.LecturaEscritura;
import TXT.Usuario;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
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
	// Arrays
	ArrayList<Usuario> usuarios = new ArrayList<>();
	private ArrayList<String> textos;
	private ArrayList<Estadisticas> estadisticas;

	// Fondo
	private Image image;

	// Dimension de pantalla
	private Dimension pantallaCompleta;

	// Jpanels y Clases
	private Login login;
	private Carga carga;
	private LecturaEscritura lecturaEscritura;
	private Dificultad dificultad;
	private Game game;

	// Carga
	Timer crono;
	private int contador;

	// Login
	private Usuario usuarioLogin;

	// Dificultad
	private int dif;

	public Mecanografia() {
		// Inicializar valores
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/img/logo.png"));
		image = requestImage("src/img/fondoCarga.jpg");
		pantallaCompleta = Toolkit.getDefaultToolkit().getScreenSize();

		lecturaEscritura = new LecturaEscritura();
		login = new Login();
		carga = new Carga(image);
		

		// Inicializar los primeros Paneles
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

	// Método para crear un temporizador para la barra de carga del inicio de 6
	// segundos, con un verificador de archivos
	public void Crono() {

		crono = new Timer(1000, e -> {
			contador += 1;
			carga.getBarraProgreso().setValue(contador);

			VerificarArchivos(contador);
		});
		crono.start();
	}

	// Método para preguntar antes de cerrar la app
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

	// Método para preguntarCerrarApp (Lo separno porque si no, no me detecta el
	// showConfirmDialog)
	private void Confirmarsalir() {
		int valor = JOptionPane.showConfirmDialog(this, "¿Seguro que desea cerrar la aplicación?", "MecanoDAM",
				JOptionPane.YES_NO_OPTION);
		if (valor == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	// Método para verificar archivos en el Panel de carga
	public void VerificarArchivos(int contador) {
		/* 
		 * Cuando lleve 4 segundos verificara si estan los archivos y si estan los leera
		 * para llenar los ARRAYLIST con la información de los .txt.
		 * Si alguno no se encontrara o no se guarda bien, saltaria un mensaje y cerraria la APP
		 * 
		 */
		if (contador == 4) {


			// Verificar .txt de usuarios
			lecturaEscritura.FicheroUsuario("src/TXT/Usuarios.txt");
			usuarios = lecturaEscritura.getListaUsuarios();

			// Verificar ,txt de textos
			lecturaEscritura.FicheroTexto("src/TXT/textos.txt");
			textos = lecturaEscritura.getListaTexto();

			// Verificar .txt de estadisticas
			lecturaEscritura.FicheroEstadistica("src/TXT/Estadisticas.txt");
			estadisticas = lecturaEscritura.getListaEstadisticas();

			// Verificar archivos en general
			if (usuarios == null || usuarios.isEmpty()) {
	            JOptionPane.showMessageDialog(null,
	            		"El archivo de usuarios no fue cargado correctamente.",
	                    "Error", JOptionPane.WARNING_MESSAGE);
			}
			if (textos == null || textos.isEmpty()) {
	            JOptionPane.showMessageDialog(null,
	            		"El archivo de textos no fue cargado correctamente.",
	                    "Error", JOptionPane.WARNING_MESSAGE);			}
			if (estadisticas == null || estadisticas.isEmpty()) {
	            JOptionPane.showMessageDialog(null,
	            		"El archivo de estadísticas está vacío.",
	                    "Error", JOptionPane.WARNING_MESSAGE);			}
			/*
			  Una comprobación rápida para ver si los metodos se guardan bien en los ARRAY's
			 
			  Comprobación de correcta lectura
			  
			  for (Usuario u : usuarios) { System.out.println(u); }
			  
			  for (String t : textos) { System.out.println(t); }
			  
			  for (Estadisticas e : estadisticas) { System.out.println(e); }
			 */

		}
		// Si todo esta bien, acabara el contador y saltara al Panel del Login
		if (contador == 6) {
			crono.stop();
			dispose();

			carga.setVisible(false);
			setTitle("LOG-IN");
			login.setVisible(true);
			setUndecorated(false);

			btnLogin();

			setVisible(true);
			setSize(400, 500);
			setLocationRelativeTo(null);

			add(login);
		}
	}

	/* 
	 * Método para validar si el usuario y la contraseña no son:
	 *  - mayores de 6 caracteres
	 *  - Tiene minusculas
	 *  - Tiene Mayusculas
	 */
	public String validarCredenciales(String pass) {
	    if (pass.length() < 6) {
	        return "La contraseña debe tener al menos 6 caracteres.";
	    }

	    boolean tieneMayuscula = false;
	    boolean tieneMinuscula = false;
	    boolean tieneNumero = false;

	    for (char c : pass.toCharArray()) {
	        if (Character.isUpperCase(c)) {
	            tieneMayuscula = true;
	        }
	        if (Character.isLowerCase(c)) {
	            tieneMinuscula = true;
	        }
	        if (Character.isDigit(c)) {
	            tieneNumero = true;
	        }
	    }

	    if (!tieneMayuscula) {
	        return "La contraseña debe contener al menos una letra mayúscula.";
	    }
	    if (!tieneMinuscula) {
	        return "La contraseña debe contener al menos una letra minúscula.";
	    }
	    if (!tieneNumero) {
	        return "La contraseña debe contener al menos un número.";
	    }

	    return "VALIDO";
	}
	

	public void btnLogin() {
	    login.getBotonLog().addActionListener(e -> {
	        String pass = String.valueOf(login.getPasswordField().getPassword());
	        String name = login.getTextUser().getText();

	        // Validación del administrador
	        if (name.equals("a") && pass.equals("a")) {
	            AdminPanel adminPanel = new AdminPanel();
	            mostrarPanel(adminPanel);
	            return; // Salir después de redirigir al administrador
	        }

	        // Validación de credenciales estándar
	        String validacion = validarCredenciales(pass);
	        if (!validacion.equals("VALIDO")) {
	            JOptionPane.showMessageDialog(null,
	                    "Error: " + validacion,
	                    "LOG-IN", JOptionPane.WARNING_MESSAGE);
	            return; // Salir si la contraseña no es válida
	        }

	        // Verificar usuarios en la lista
	        boolean usuarioEncontrado = false;
	        for (Usuario usuario : usuarios) {
	            if (name.equals(usuario.getName()) && pass.equals(usuario.getPass())) {
	                usuarioLogin = usuario;
	                usuarioEncontrado = true;

	                // Redirigir a las dificultades
	                login.setVisible(false);
	                setResizable(false);
	                setLocationRelativeTo(null);
	                setTitle("MECANOGRAFIA");

	                dificultad = new Dificultad();
	                add(dificultad);
	                btnDificultades();
	                return;
	            }
	        }

	        // Si no se encontró el usuario
	        if (!usuarioEncontrado) {
	            JOptionPane.showMessageDialog(null,
	                    "Usuario o contraseña incorrectos.",
	                    "LOG-IN", JOptionPane.WARNING_MESSAGE);
	        }
	    });
	}

	
	public void mostrarPanel(Component panel) {
	    getContentPane().removeAll(); 
	    getContentPane().add(panel);   
	    revalidate();                 
	    repaint();                
	}

	
	public void btnDificultades() {

		dificultad.getBtnDificultades().addActionListener(e -> {
			dispose();
			setSize(pantallaCompleta);
			setLocationRelativeTo(null);
			setVisible(true);
			dificultad.setVisible(false);

			// FACIL
			if (dificultad.getBtnFacil().isSelected()) {
				nivel(dif = 0);
				JOptionPane.showMessageDialog(null, "Antes de empezar debe saber: \n"
						+ " 1.- El modo cuenta con 200 caracteres.\n"
						+ " 2.- Tiene un maximo de 4 minutos, este empezara a contar al pulsar la primera tecla. \n"
						+ " 3.- Si fallas 5 veces el juego acabara. \n"
						+ " 4.- Si superas el límite de tiempo o llegas al total de errores, te mostrará una ventana emergente y el juego finalizará. \n",
						"Instrucciones del juego", 1);
				// DICIL
			} else if (dificultad.getBtnDificil().isSelected()) {
				nivel(dif = 1);
				JOptionPane.showMessageDialog(null, "Bienvenido, aqui tienes las instrucciones: \n"
						+ " 1.- El modo cuenta con 1000 caracteres. \n"
						+ " 2.- Tiene un maximo de 3 minutos, este empezara a contar al pulsar la primera tecla. \n"
						+ " 3.- Si fallas 3 veces el juego acabara. \n"
						+ " 4.- Si superas el límite de tiempo o llegas al total de errores, te mostrará una ventana emergente y el juego finalizará. \n",
						"Instrucciones del juego", 1);
			}
		});
	}

	public void nivel(int dif) {
	    // Aquí debes cargar los textos antes de crear la instancia de Game
	    LecturaEscritura lecturaEscritura = new LecturaEscritura();
	    lecturaEscritura.FicheroTexto("src/TXT/textos.txt");
	    ArrayList<String> textos = lecturaEscritura.getListaTexto();

	    if (textos == null || textos.isEmpty()) {
	        JOptionPane.showMessageDialog(null,
	                "Error: No se encontraron textos disponibles.",
	                "Error en textos",
	                JOptionPane.ERROR_MESSAGE);
	        return; // Salir si no hay textos
	    }

	    // Crear instancia de Game con los textos cargados
		game = new Game(dif, textos, estadisticas, usuarios, usuarioLogin);
		add(game);
		game.setVisible(true);
	}


	
	
	// Método para poner una imagen de fondo 
	
	public static Image requestImage(String ruta) {
		BufferedImage image = null;

		try {
			// El imageIO necesita el file para mostrar la imagen de pantalla
			image = ImageIO.read(new File(ruta));
		} catch (IOException e) {
			JOptionPane.showInternalMessageDialog(null, "Error, background no localizado", "Imagen no localizada", 0);
			System.exit(ABORT);
		}

		return image;
	}
	


}
