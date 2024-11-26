package PanelPrincipal;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
	private long tiempoInicio = 0;
	private boolean tiempoIniciado = false;

	public Game(int dif, ArrayList<String> textos, ArrayList<Estadisticas> estadisticas, ArrayList<Usuario> usuarios,
			Usuario usuarioLogin) {
		this.dif = dif;
		this.textos = textos;
		this.usuarioLogin = usuarioLogin;

		setLayout(null);
		Dimension PantallaCompleta = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(PantallaCompleta);

		textoInteractivo = new Texto();
		JScrollPane scrollLeer = new JScrollPane(textoInteractivo.getTextoLeer());
		scrollLeer.setBounds(40, 10, 1820, 275);
		add(scrollLeer);

		JScrollPane scrollEscribir = new JScrollPane(textoInteractivo.getTextoEscribir());
		scrollEscribir.setBounds(40, 327, 1820, 275);
		add(scrollEscribir);

		tecladoBotones = new Teclado();
		tecladoBotones.setBounds(40, 654, 1308, 332);
		add(tecladoBotones);

		infoPanel = new Info();
		infoPanel.setBounds(1400, 654, 460, 332);
		add(infoPanel);

		imprimirTextoDificultad();

		// Agregar DocumentListener
		textoInteractivo.getTextoEscribir().getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				ColorCaracter();
				verificarCondiciones();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				ColorCaracter();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				ColorCaracter();
			}
		});

		// Bloquear retroceso y agregar resaltado en tiempo real
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

					// Verificar la precisión de la tecla presionada
					String textoUsuario = textoInteractivo.getTextoEscribir().getText();
					String textoReferencia = textos.get(dif);

					if (!textoUsuario.isEmpty()) {
						boolean esCorrecta = textoUsuario.length() <= textoReferencia.length() && textoUsuario
								.charAt(textoUsuario.length() - 1) == textoReferencia.charAt(textoUsuario.length() - 1);
						infoPanel.registrarPulsacion(esCorrecta);
					}
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
		String textoUsuario = textoInteractivo.getTextoEscribir().getText();
		String textoReferencia = textos.get(dif);
		int errores = infoPanel.getErrores();
		long tiempoTranscurrido = (System.currentTimeMillis() - tiempoInicio) / 1000; // Segundos

		int limiteErrores;
		if (dif == 0) {
			limiteErrores = 5; // Fácil: 5 errores
		} else {
			limiteErrores = 3; // Difícil: 3 errores
		}

		int limiteTiempo;
		if (dif == 0) {
			limiteTiempo = 240; // Fácil: 240 segundos
		} else {
			limiteTiempo = 180; // Difícil: 180 segundos
		}

		// Condición de fallo
		if (errores >= limiteErrores || tiempoTranscurrido >= limiteTiempo) {
			mostrarMensajeFallo(errores, tiempoTranscurrido);
			return;
		}

		// Condición de éxito
		if (textoUsuario.equals(textoReferencia)) {
			mostrarMensajeExito(tiempoTranscurrido, errores);
		}
	}

	private void mostrarMensajeExito(long tiempoTranscurrido, int errores) {
		String mensaje = "¡Enhorabuena, has completado el texto correctamente!\n"
				+ "Tiempo transcurrido: " + tiempoTranscurrido + " segundos\n"
				+ "Errores: " + errores;

		JOptionPane.showMessageDialog(null, mensaje, "¡Éxito!", JOptionPane.INFORMATION_MESSAGE);

		int ppm = (int) ((infoPanel.getPulsaciones() / (double) tiempoTranscurrido) * 60);
		Estadisticas nuevaEstadistica = new Estadisticas(usuarioLogin.getName(), dif, (int) tiempoTranscurrido, ppm,
				errores);

		LecturaEscritura le = new LecturaEscritura();
		le.guardarEstadistica("src/TXT/estadisticas.txt", nuevaEstadistica);

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

		int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Juego terminado", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE);

		if (opcion == JOptionPane.YES_OPTION) {
			reiniciarJuego();
		} else {
			System.exit(0);
		}
	}

	private void reiniciarJuego() {
		SwingUtilities.invokeLater(() -> {
			textoInteractivo.getTextoEscribir().setText("");
			infoPanel.reiniciar();
			tiempoIniciado = false;
		});
	}

}
