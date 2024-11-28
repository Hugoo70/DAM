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
	// Arrays para almacenar los datos de los usuarios, textos y estadísticas.
	ArrayList<Usuario> usuarios = new ArrayList<>();
	private ArrayList<String> textos;
	private ArrayList<Estadisticas> estadisticas;

	// Componentes para manejar la interfaz gráfica
	private Image image;  // Imagen de fondo para la pantalla de carga
	private Dimension pantallaCompleta;  // Dimensiones de la pantalla completa

	// Paneles y clases
	private Login login;  // Panel de login
	private Carga carga;  // Panel de carga
	private LecturaEscritura lecturaEscritura;  // Clase que lee archivos
	private Dificultad dificultad;  // Panel para elegir dificultad
	private Game game;  // El juego que se ejecutará

	// Variables para controlar el temporizador de carga y el contador
	Timer crono;
	private int contador;

	// Variables para el login y la dificultad del juego
	private Usuario usuarioLogin;
	private int dif;


	public Mecanografia() {
	    // Inicialización de la interfaz gráfica, incluyendo la imagen de fondo y tamaño de la pantalla.
	    setIconImage(Toolkit.getDefaultToolkit().getImage("src/img/logo.png"));
	    image = requestImage("src/img/fondoCarga.jpg");
	    pantallaCompleta = Toolkit.getDefaultToolkit().getScreenSize();

	    // Inicializar los paneles
	    lecturaEscritura = new LecturaEscritura();
	    login = new Login();
	    carga = new Carga(image);

	    // Configurar el primer panel a mostrar (pantalla de carga)
	    login.setVisible(false);
	    carga.setVisible(true);

	    // Configuración de la ventana
	    setSize(750, 500);
	    setResizable(false);
	    setUndecorated(true);
	    setLocationRelativeTo(null);

	    // Iniciar temporizador para carga de archivos
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
	    if (contador == 4) {
	        // Verificar si los archivos existen y cargarlos en sus respectivas listas
	        lecturaEscritura.FicheroUsuario("src/TXT/Usuarios.txt");
	        usuarios = lecturaEscritura.getListaUsuarios();
	        lecturaEscritura.FicheroTexto("src/TXT/textos.txt");
	        textos = lecturaEscritura.getListaTexto();
	        lecturaEscritura.FicheroEstadistica("src/TXT/Estadisticas.txt");
	        estadisticas = lecturaEscritura.getListaEstadisticas();

	        // Mostrar mensaje de error si algún archivo no se carga correctamente
	        if (usuarios == null || usuarios.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "El archivo de usuarios no fue cargado correctamente.", "Error", JOptionPane.WARNING_MESSAGE);
	        }
	        if (textos == null || textos.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "El archivo de textos no fue cargado correctamente.", "Error", JOptionPane.WARNING_MESSAGE);
	        }
	        if (estadisticas == null || estadisticas.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "El archivo de estadísticas está vacío.", "Error", JOptionPane.WARNING_MESSAGE);
	        }
	    }

	    // Después de verificar, si todo está bien, pasar al login
	    if (contador == 6) {
	        crono.stop();
	        dispose();  // Cerrar la pantalla de carga
	        carga.setVisible(false);
	        setTitle("LOG-IN");
	        login.setVisible(true);
	        setUndecorated(false);
	        btnLogin();  // Preparar el botón de login
	        setVisible(true);
	        setSize(400, 500);
	        setLocationRelativeTo(null);
	        add(login);
	    }
	}


	public String validarCredenciales(String pass) {
	    if (pass.length() < 6) {
	        return "La contraseña debe tener al menos 6 caracteres.";
	    }

	    boolean tieneMayuscula = false;
	    boolean tieneMinuscula = false;
	    boolean tieneNumero = false;

	    // Validación de la contraseña (mayúsculas, minúsculas y números)
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

	    // Comprobaciones adicionales
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

	
	public void mostrarPanelLogin() {
	    getContentPane().removeAll(); // Limpia el contenido actual
	    login.setVisible(true);       // Asegúrate de que el login esté visible
	    add(login);                   // Agrega el panel de login nuevamente
	    revalidate();                 // Actualiza el contenedor
	    repaint();                    // Vuelve a dibujar
	}

	public void btnLogin() {
	    login.getBotonLog().addActionListener(e -> {
	        String pass = String.valueOf(login.getPasswordField().getPassword());
	        String name = login.getTextUser().getText();

	        // Validación de credenciales del administrador
	        if (name.equals("a") && pass.equals("a")) {
	            AdminPanel adminPanel = new AdminPanel();
	            mostrarPanel(adminPanel);
	            return;
	        }

	        // Validación de credenciales del usuario
	        String validacion = validarCredenciales(pass);
	        if (!validacion.equals("VALIDO")) {
	            JOptionPane.showMessageDialog(null, "Error: " + validacion, "LOG-IN", JOptionPane.WARNING_MESSAGE);
	            return;
	        }

	        // Verificar que el usuario exista en la lista de usuarios
	        boolean usuarioEncontrado = false;
	        for (Usuario usuario : usuarios) {
	            if (name.equals(usuario.getName()) && pass.equals(usuario.getPass())) {
	                usuarioLogin = usuario;
	                usuarioEncontrado = true;
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

	        // Si el usuario no fue encontrado
	        if (!usuarioEncontrado) {
	            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "LOG-IN", JOptionPane.WARNING_MESSAGE);
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
	        // Verificar si se seleccionó alguna dificultad
	        if (!dificultad.getBtnFacil().isSelected() && !dificultad.getBtnDificil().isSelected()) {
	            JOptionPane.showMessageDialog(
	                null,
	                "Por favor, seleccione un nivel de dificultad antes de continuar.",
	                "Error de selección",
	                JOptionPane.WARNING_MESSAGE
	            );
	            return; // Salir si no se seleccionó ninguna opción
	        }

	        dispose();
	        setSize(pantallaCompleta);
	        setLocationRelativeTo(null);
	        setVisible(true);
	        dificultad.setVisible(false);

	        // Configurar las instrucciones según la dificultad seleccionada
	        if (dificultad.getBtnFacil().isSelected()) {
	            nivel(dif = 0);
	            JOptionPane.showMessageDialog(
	                null,
	                "Antes de empezar debe saber: \n"
	                + " 1.- El modo cuenta con 200 caracteres.\n"
	                + " 2.- Tiene un máximo de 4 minutos, este empezará a contar al pulsar la primera tecla. \n"
	                + " 3.- Si fallas 5 veces el juego acabará. \n"
	                + " 4.- Si superas el límite de tiempo o llegas al total de errores, te mostrará una ventana emergente y el juego finalizará. \n",
	                "Instrucciones del juego",
	                JOptionPane.INFORMATION_MESSAGE
	            );
	        } else if (dificultad.getBtnDificil().isSelected()) {
	            nivel(dif = 1);
	            JOptionPane.showMessageDialog(
	                null,
	                "Bienvenido, aquí tienes las instrucciones: \n"
	                + " 1.- El modo cuenta con 1000 caracteres. \n"
	                + " 2.- Tiene un máximo de 3 minutos, este empezará a contar al pulsar la primera tecla. \n"
	                + " 3.- Si fallas 3 veces el juego acabará. \n"
	                + " 4.- Si superas el límite de tiempo o llegas al total de errores, te mostrará una ventana emergente y el juego finalizará. \n",
	                "Instrucciones del juego",
	                JOptionPane.INFORMATION_MESSAGE
	            );
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
