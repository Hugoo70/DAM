package PanelPrincipal;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Paneles.Mecanografia;
import TXT.Estadisticas;
import TXT.LecturaEscritura;
import TXT.Usuario;

public class Game extends JPanel {
	private int dif;
	private ArrayList<String> textos;
	private Usuario usuarioLogin;
	private Texto textoInteractivo;
	private Teclado tecladoBotones;
	private Info infoPanel;
    private MenuBarPanel menu;
	private long tiempoInicio = 0;
	private boolean tiempoIniciado = false;
	private boolean juegoTerminado = false;
	private Image image;

	public Game(int dif, ArrayList<String> textos, ArrayList<Estadisticas> estadisticas, ArrayList<Usuario> usuarios,
			Usuario usuarioLogin) {
		this.dif = dif;
		this.textos = textos;
		this.usuarioLogin = usuarioLogin;

		setLayout(null);
		Dimension PantallaCompleta = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(PantallaCompleta);
		image = Mecanografia.requestImage("src/img/fondoGame.jpg");
		

		
        menu = new MenuBarPanel();
        menu.setBounds(0, 0, PantallaCompleta.width, 30);
        add(menu);

		textoInteractivo = new Texto();
		JScrollPane scrollLeer = new JScrollPane(textoInteractivo.getTextoLeer());
		scrollLeer.setBounds(40, 51, 1820, 275);
		add(scrollLeer);

		JScrollPane scrollEscribir = new JScrollPane(textoInteractivo.getTextoEscribir());
		scrollEscribir.setBounds(40, 351, 1820, 275);
		add(scrollEscribir);

		tecladoBotones = new Teclado();
		tecladoBotones.setBounds(40, 654, 1308, 332);
		add(tecladoBotones);

		infoPanel = new Info();
		infoPanel.setBounds(1400, 654, 460, 332);
		add(infoPanel);

		imprimirTextoDificultad();

		textoInteractivo.getTextoEscribir().getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) {
		        actualizarVista();
		    }

		    @Override
		    public void removeUpdate(DocumentEvent e) {
		        actualizarVista();
		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		        actualizarVista();
		    }

		    private void actualizarVista() {
		        textoInteractivo.getTextoEscribir().getDocument().removeDocumentListener(this); // Deshabilitar el listener temporalmente
		        textoInteractivo.actualizarVista(textos.get(dif));
		        verificarCondiciones();
		        textoInteractivo.getTextoEscribir().getDocument().addDocumentListener(this); 
		    }

		});

		textoInteractivo.getTextoEscribir().addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
		            JOptionPane.showMessageDialog(null, "¡No puedes hacer retroceso!", "Error",
		                    JOptionPane.ERROR_MESSAGE);
		            e.consume();
		        } else {
		            if (!tiempoIniciado) {
		                tiempoInicio = System.currentTimeMillis();
		                tiempoIniciado = true;
		            }

		            String keyText = KeyEvent.getKeyText(e.getKeyCode());
		            if (keyText.equalsIgnoreCase("Space")) {
		                keyText = "SPACE";
		            }
		            tecladoBotones.resaltarTecla(keyText);

		            // Verificar precisión
		            String textoUsuario = textoInteractivo.getTextoEscribir().getText();
		            String textoReferencia = textos.get(dif);

		            if (!textoUsuario.isEmpty()) {
		                boolean esCorrecta = textoUsuario.length() <= textoReferencia.length()
		                        && textoUsuario.charAt(textoUsuario.length() - 1) == textoReferencia.charAt(textoUsuario.length() - 1);
		                infoPanel.registrarPulsacion(esCorrecta);
		            }

		            // Verificar condiciones
		            verificarCondiciones();
		        }
		    }

		    @Override
		    public void keyReleased(KeyEvent e) {
		        String keyText = KeyEvent.getKeyText(e.getKeyCode());
		        if (keyText.equalsIgnoreCase("Space")) {
		            keyText = "SPACE";
		        }
		        tecladoBotones.liberarTecla(keyText);
		    }
		});
	}

	private void imprimirTextoDificultad() {
		if (textos == null || textos.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Error: Texto no disponible.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		textoInteractivo.setTextoReferencia(textos.get(dif));
	}

	private void ColorCaracter() {
		String textoReferencia = textos.get(dif);
		textoInteractivo.actualizarColores(textoReferencia);
	}

	private void verificarCondiciones() {
	    if (juegoTerminado) {
	        return; // Evitar que se ejecute si el juego ya terminó
	    }

	    String textoUsuario = textoInteractivo.getTextoEscribir().getText();
	    String textoReferencia = textoInteractivo.getTextoLeer().getText();

	    int errores = infoPanel.getErrores();
	    long tiempoTranscurrido = (System.currentTimeMillis() - tiempoInicio) / 1000; // Segundos

	    int limiteErrores = (dif == 0) ? 5 : 3; // Fácil: 5 errores, Difícil: 3 errores
	    int limiteTiempo = (dif == 0) ? 240 : 180; // Fácil: 240 segundos, Difícil: 180 segundos

	    // Condición de fallo
	    if (errores >= limiteErrores || tiempoTranscurrido >= limiteTiempo) {
	        juegoTerminado = true; // Marcar el juego como terminado
	        mostrarMensajeFallo(errores, tiempoTranscurrido);
	    }

	    // Condición de éxito
	    if ((textoUsuario.length() == textoReferencia.length()) && (errores < limiteErrores || tiempoTranscurrido < limiteTiempo)) {
	        juegoTerminado = true; // Marcar el juego como terminado
	        mostrarMensajeExito(tiempoTranscurrido, errores);
	    }
	}

	private boolean verificarMejoraEstadisticas(Estadisticas nuevaEstadistica, ArrayList<Estadisticas> estadisticasUsuario) {
	    // Filtrar estadísticas del usuario actual
	    return estadisticasUsuario.stream()
	            .filter(est -> est.getUser().equals(nuevaEstadistica.getUser()) && est.getDif() == nuevaEstadistica.getDif())
	            .allMatch(est -> 
	                nuevaEstadistica.getPpm() > est.getPpm() // Mejora en PPM
	                || nuevaEstadistica.getErrores() < est.getErrores() // Menos errores
	                || nuevaEstadistica.getTiempoTranscurrido() < est.getTiempoTranscurrido() // Menor tiempo
	            );
	}

	private void mostrarMensajeExito(long tiempoTranscurrido, int errores) {
	    String mensaje = "¡Enhorabuena, has completado el texto correctamente!\n" 
	                    + "Tiempo transcurrido: " + tiempoTranscurrido + " segundos\n" 
	                    + "Errores: " + errores;

	    JOptionPane.showMessageDialog(null, mensaje, "¡Éxito!", JOptionPane.INFORMATION_MESSAGE);

	    // Crear la estadística del usuario actual
	    int ppm = (int) ((infoPanel.getPulsaciones() / (double) tiempoTranscurrido) * 60);
	    Estadisticas nuevaEstadistica = new Estadisticas(
	            usuarioLogin.getId(), // Id usuario actual
	            dif, // Dificultad
	            (int) tiempoTranscurrido, // Tiempo en segundos
	            ppm, // Pulsaciones por minuto
	            errores // Número de errores
	    );

	    // Guardar las estadísticas en el archivo
	    LecturaEscritura le = new LecturaEscritura();
	    le.guardarEstadistica("src/TXT/estadisticas.txt", nuevaEstadistica);

	    // Verificar si mejora las estadísticas
	    ArrayList<Estadisticas> estadisticasUsuario = le.getListaEstadisticas();
	    boolean esMejora = verificarMejoraEstadisticas(nuevaEstadistica, estadisticasUsuario);

	    if (esMejora) {
	        // Enviar correo con las nuevas estadísticas
	        String email = usuarioLogin.getMail();
	        String asunto = "¡Mejoras en tus estadísticas de juego!";
	        String cuerpo = "Hola " + usuarioLogin.getName() + ",\n\n"
	                      + "¡Has mejorado tus estadísticas!\n"
	                      + "Aquí tienes los detalles de tu última partida:\n\n"
	                      + "Tiempo transcurrido: " + tiempoTranscurrido + " segundos\n"
	                      + "Errores: " + errores + "\n"
	                      + "Pulsaciones por minuto (PPM): " + ppm + "\n\n"
	                      + "¡Sigue practicando para mejorar aún más!\n\n"
	                      + "Saludos,\nEquipo del Juego";

	        Mail.enviarMail(email, asunto, cuerpo);
	    }

	    // Preguntar si el usuario quiere reiniciar o salir
	    int opcion = JOptionPane.showConfirmDialog(null, "¿Quieres intentarlo de nuevo?", "Reiniciar",
	            JOptionPane.YES_NO_OPTION);

	    if (opcion == JOptionPane.YES_OPTION) {
	        reiniciarJuego();
	    } else {
	        System.exit(0);
	    }
	}



	private void mostrarMensajeFallo(int errores, long tiempoTranscurrido) {
		
		String mensaje = "Has fallado. \nTiempo transcurrido: " + tiempoTranscurrido + " segundos\n" + "Errores: "
				+ errores + "\n" + "¿Deseas intentar de nuevo o salir?";
		ImageIcon icon = new ImageIcon("src/img/fallo.webp");
		Image scaledImage = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);

		int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Juego terminado", JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE, scaledIcon);

		if (opcion == JOptionPane.YES_OPTION) {
			reiniciarJuego();
		} else {
			System.exit(0);
		}
	}

	private void reiniciarJuego() {
	    SwingUtilities.invokeLater(() -> {
	        textoInteractivo.getTextoEscribir().setText(""); // Limpiar el texto ingresado
	        infoPanel.reiniciar(); // Reiniciar panel de información
	        tecladoBotones.reiniciarTeclas(); // Reiniciar las teclas
	        tiempoIniciado = false; // Reiniciar el temporizador
	        juegoTerminado = false; // Reiniciar el estado del juego
	    });
	}
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

	}
	
	


}
