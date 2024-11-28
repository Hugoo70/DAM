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
    private int dif;  // Dificultad seleccionada
    private ArrayList<String> textos;  // Textos a mostrar para la mecanografía
    private Usuario usuarioLogin;  // Usuario que está jugando
    private Texto textoInteractivo;  // Componente de texto que muestra lo que el usuario debe escribir
    private Teclado tecladoBotones;  // Teclado visual para que el usuario haga clic en las teclas
    private Info infoPanel;  // Panel de información para mostrar errores y otras métricas
    private MenuBarPanel menu;  // Barra de menú del juego
    private long tiempoInicio = 0;  // Tiempo de inicio para el conteo de la partida
    private boolean tiempoIniciado = false;  // Indica si el temporizador ha comenzado
    private boolean juegoTerminado = false;  // Indica si el juego ha terminado

    // Constructor de la clase Game
    public Game(int dif, ArrayList<String> textos, ArrayList<Estadisticas> estadisticas, ArrayList<Usuario> usuarios,
            Usuario usuarioLogin) {
        this.dif = dif;  // Establece la dificultad
        this.textos = textos;  // Establece los textos disponibles
        this.usuarioLogin = usuarioLogin;  // Establece el usuario actual

        setLayout(null);  // Usamos layout nulo para posicionar los componentes manualmente
        Dimension PantallaCompleta = Toolkit.getDefaultToolkit().getScreenSize();  // Obtiene las dimensiones de la pantalla completa
        setSize(PantallaCompleta);  // Establece el tamaño del panel

        // Inicialización de los paneles y componentes
        menu = new MenuBarPanel();  // Barra de menú
        menu.setBounds(0, 0, PantallaCompleta.width, 30);  // Posiciona la barra de menú
        add(menu);  // Añade la barra de menú al panel

        textoInteractivo = new Texto();  // Componente de texto para escribir
        JScrollPane scrollLeer = new JScrollPane(textoInteractivo.getTextoLeer());  // Área de texto donde se muestra lo que debe escribir el usuario
        scrollLeer.setBounds(40, 51, 1820, 275);  // Posiciona el área de texto
        add(scrollLeer);

        JScrollPane scrollEscribir = new JScrollPane(textoInteractivo.getTextoEscribir());  // Área de texto donde el usuario escribe
        scrollEscribir.setBounds(40, 351, 1820, 275);  // Posiciona el área de texto
        add(scrollEscribir);

        tecladoBotones = new Teclado();  // Componente del teclado virtual
        tecladoBotones.setBounds(40, 654, 1308, 332);  // Posiciona el teclado
        add(tecladoBotones);

        infoPanel = new Info();  // Panel que muestra la información del juego (errores, tiempo, etc.)
        infoPanel.setBounds(1400, 654, 460, 332);  // Posiciona el panel de información
        add(infoPanel);

        imprimirTextoDificultad();  // Muestra el texto correspondiente a la dificultad seleccionada

        // Agrega un listener al campo de texto para actualizar la vista y verificar condiciones
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
                // Actualiza la vista de acuerdo al texto que se ha escrito
                textoInteractivo.getTextoEscribir().getDocument().removeDocumentListener(this);  // Deshabilita temporalmente el listener
                textoInteractivo.actualizarVista(textos.get(dif));  // Actualiza la vista con el texto correspondiente
                verificarCondiciones();  // Verifica si las condiciones del juego han cambiado
                textoInteractivo.getTextoEscribir().getDocument().addDocumentListener(this);  // Vuelve a habilitar el listener
            }
        });

        // Agrega un listener al teclado para registrar las teclas presionadas
        textoInteractivo.getTextoEscribir().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Verifica que el usuario no haga retroceso (backspace)
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    JOptionPane.showMessageDialog(null, "¡No puedes hacer retroceso!", "Error", JOptionPane.ERROR_MESSAGE);
                    e.consume();  // Consume el evento para evitar el retroceso
                } else {
                    // Inicia el temporizador si no ha sido iniciado
                    if (!tiempoIniciado) {
                        tiempoInicio = System.currentTimeMillis();  // Inicia el tiempo
                        tiempoIniciado = true;  // Marca que el tiempo ha comenzado
                    }

                    String keyText = KeyEvent.getKeyText(e.getKeyCode());  // Obtiene el nombre de la tecla presionada
                    if (keyText.equalsIgnoreCase("Space")) {
                        keyText = "SPACE";  // Modifica el texto para que sea "SPACE"
                    }
                    tecladoBotones.resaltarTecla(keyText);  // Resalta la tecla presionada en el teclado virtual

                    // Verifica la precisión del texto ingresado por el usuario
                    String textoUsuario = textoInteractivo.getTextoEscribir().getText();
                    String textoReferencia = textos.get(dif);

                    if (!textoUsuario.isEmpty()) {
                        boolean esCorrecta = textoUsuario.length() <= textoReferencia.length()
                                && textoUsuario.charAt(textoUsuario.length() - 1) == textoReferencia.charAt(textoUsuario.length() - 1);
                        infoPanel.registrarPulsacion(esCorrecta);  // Registra si la pulsación fue correcta
                    }

                    // Verifica si el juego ha terminado
                    verificarCondiciones();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String keyText = KeyEvent.getKeyText(e.getKeyCode());
                if (keyText.equalsIgnoreCase("Space")) {
                    keyText = "SPACE";  // Modifica el texto para que sea "SPACE"
                }
                tecladoBotones.liberarTecla(keyText);  // Libera la tecla resaltada en el teclado virtual
            }
        });
    }

    // Método para mostrar el texto correspondiente a la dificultad seleccionada
    private void imprimirTextoDificultad() {
        if (textos == null || textos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: Texto no disponible.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        textoInteractivo.setTextoReferencia(textos.get(dif));  // Establece el texto según la dificultad
    }

    // Método para actualizar el color de los caracteres en el texto
    private void ColorCaracter() {
        String textoReferencia = textos.get(dif);
        textoInteractivo.actualizarColores(textoReferencia);  // Actualiza los colores en el texto
    }

    // Método para verificar si las condiciones del juego han cambiado
    private void verificarCondiciones() {
        if (juegoTerminado) {
            return;  // Si el juego ya ha terminado, no hacer nada
        }

        String textoUsuario = textoInteractivo.getTextoEscribir().getText();
        String textoReferencia = textoInteractivo.getTextoLeer().getText();

        int errores = infoPanel.getErrores();  // Obtiene el número de errores cometidos
        long tiempoTranscurrido = (System.currentTimeMillis() - tiempoInicio) / 1000;  // Calcula el tiempo transcurrido en segundos

        // Define los límites de errores y tiempo dependiendo de la dificultad
        int limiteErrores = (dif == 0) ? 5 : 3;  // Fácil: 5 errores, Difícil: 3 errores
        int limiteTiempo = (dif == 0) ? 240 : 180;  // Fácil: 240 segundos, Difícil: 180 segundos

        // Verifica si el jugador ha fallado
        if (errores >= limiteErrores || tiempoTranscurrido >= limiteTiempo) {
            juegoTerminado = true;  // Marca el juego como terminado
            mostrarMensajeFallo(errores, tiempoTranscurrido);  // Muestra el mensaje de fallo
        }

        // Verifica si el jugador ha completado el texto correctamente
        if ((textoUsuario.length() == textoReferencia.length()) && (errores < limiteErrores || tiempoTranscurrido < limiteTiempo)) {
            juegoTerminado = true;  // Marca el juego como terminado
            mostrarMensajeExito(tiempoTranscurrido, errores);  // Muestra el mensaje de éxito
        }
    }

    // Método para verificar si se ha mejorado en las estadísticas
    private boolean verificarMejoraEstadisticas(Estadisticas nuevaEstadistica, ArrayList<Estadisticas> estadisticasUsuario) {
        // Filtra las estadísticas del usuario actual y verifica si ha mejorado
        return estadisticasUsuario.stream()
                .filter(est -> est.getUser().equals(nuevaEstadistica.getUser()) && est.getDif() == nuevaEstadistica.getDif())
                .allMatch(est ->
                    nuevaEstadistica.getPpm() > est.getPpm()  // Mejora en PPM
                    || nuevaEstadistica.getErrores() < est.getErrores()  // Menos errores
                    || nuevaEstadistica.getTiempoTranscurrido() < est.getTiempoTranscurrido()  // Menor tiempo
                );
    }

    // Método para mostrar el mensaje de éxito cuando se completa el texto correctamente
    private void mostrarMensajeExito(long tiempoTranscurrido, int errores) {
        String mensaje = "¡Enhorabuena, has completado el texto correctamente!\n" 
                        + "Tiempo transcurrido: " + tiempoTranscurrido + " segundos\n" 
                        + "Errores: " + errores;

        JOptionPane.showMessageDialog(null, mensaje, "¡Éxito!", JOptionPane.INFORMATION_MESSAGE);

        // Crear la estadística del usuario actual
        int ppm = (int) ((infoPanel.getPulsaciones() / (double) tiempoTranscurrido) * 60);  // Calcula las pulsaciones por minuto
        Estadisticas nuevaEstadistica = new Estadisticas(
                usuarioLogin.getId(),  // Id del usuario
                dif,  // Dificultad
                (int) tiempoTranscurrido,  // Tiempo en segundos
                ppm,  // Pulsaciones por minuto
                errores  // Número de errores
        );

        // Guardar las estadísticas en el archivo
        LecturaEscritura le = new LecturaEscritura();
        le.guardarEstadistica("src/TXT/estadisticas.txt", nuevaEstadistica);

        // Verificar si hay mejora en las estadísticas
        ArrayList<Estadisticas> estadisticasUsuario = le.getListaEstadisticas();
        boolean esMejora = verificarMejoraEstadisticas(nuevaEstadistica, estadisticasUsuario);

        if (esMejora) {
            // Enviar correo con las nuevas estadísticas si hay mejora
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

            Mail.enviarMail(email, asunto, cuerpo);  // Envia un correo con las estadísticas
        }

        // Preguntar si el usuario quiere reiniciar o salir
        int opcion = JOptionPane.showConfirmDialog(null, "¿Quieres intentarlo de nuevo?", "Reiniciar",
                JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            reiniciarJuego();  // Reinicia el juego si el usuario lo desea
        } else {
            System.exit(0);  // Sale de la aplicación si el usuario no quiere reiniciar
        }
    }

    // Método para mostrar el mensaje de fallo cuando el usuario no completa el texto correctamente
    private void mostrarMensajeFallo(int errores, long tiempoTranscurrido) {
        String mensaje = "Has fallado. \nTiempo transcurrido: " + tiempoTranscurrido + " segundos\n" + "Errores: "
                + errores + "\n" + "¿Deseas intentar de nuevo o salir?";
        ImageIcon icon = new ImageIcon("src/img/fallo.webp");
        Image scaledImage = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Juego terminado", JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE, scaledIcon);

        if (opcion == JOptionPane.YES_OPTION) {
            reiniciarJuego();  // Reinicia el juego si el usuario lo desea
        } else {
            System.exit(0);  // Sale de la aplicación si el usuario no quiere reiniciar
        }
    }

    // Método para reiniciar el juego
    private void reiniciarJuego() {
        SwingUtilities.invokeLater(() -> {
            textoInteractivo.getTextoEscribir().setText("");  // Limpia el texto ingresado por el usuario
            infoPanel.reiniciar();  // Reinicia la información mostrada
            tecladoBotones.reiniciarTeclas();  // Reinicia el teclado visual
            tiempoIniciado = false;  // Reinicia el temporizador
            juegoTerminado = false;  // Reinicia el estado del juego
        });
    }
}
