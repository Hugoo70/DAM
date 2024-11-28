package PanelPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class Info extends JPanel {
    // Componentes para mostrar estadísticas en pantalla
    private JLabel lblPPM;  // Label para mostrar las pulsaciones por minuto
    private JLabel lblErrores;  // Label para mostrar la cantidad de errores
    private JLabel lblTiempo;  // Label para mostrar el tiempo transcurrido

    // Variables para registrar pulsaciones, errores, y tiempo
    private int errores;  // Contador de errores cometidos
    private int pulsaciones;  // Contador de pulsaciones del usuario
    private long startTime;  // Hora en la que comenzó el juego
    private boolean timerStarted;  // Indica si el temporizador ha comenzado

    // Temporizador para actualizar el tiempo transcurrido cada segundo
    private Timer timer;

    // Constructor de la clase Info
    public Info() {
        setLayout(new GridLayout(3, 1));  // Layout en columna para las etiquetas de información

        // Inicializar las etiquetas para mostrar las estadísticas
        lblPPM = new JLabel("PPM: 0", SwingConstants.CENTER);
        lblPPM.setFont(new Font("Tahoma", Font.BOLD, 18));  // Establecer la fuente y tamaño de la etiqueta

        lblErrores = new JLabel("Errores: 0", SwingConstants.CENTER);
        lblErrores.setFont(new Font("Tahoma", Font.BOLD, 18));  // Configurar la fuente

        lblTiempo = new JLabel("Tiempo: 0s", SwingConstants.CENTER);
        lblTiempo.setFont(new Font("Tahoma", Font.BOLD, 18));  // Configurar la fuente

        // Añadir las etiquetas al panel
        add(lblPPM);
        add(lblErrores);
        add(lblTiempo);

        // Inicializar los contadores
        errores = 0;
        pulsaciones = 0;
        timerStarted = false;

        // Crear el temporizador que actualiza el tiempo transcurrido cada segundo
        timer = new Timer(1000, e -> actualizarTiempo());
    }

    // Métodos para acceder a los valores de pulsaciones y errores
    public int getPulsaciones() {
        return pulsaciones;
    }

    public void setErrores(int errores) {
        this.errores = errores;  // Actualiza el contador de errores
        lblErrores.setText("Errores: " + errores);  // Actualiza la etiqueta de errores
    }

    public int getErrores() {
        return errores;
    }

    // Método para registrar las pulsaciones y verificar si son correctas
    public void registrarPulsacion(boolean esCorrecta) {
        if (!timerStarted) {
            startTime = System.currentTimeMillis();  // Establece el tiempo de inicio
            timerStarted = true;  // Marca que el temporizador ha comenzado
            timer.start();  // Inicia el temporizador
        }

        pulsaciones++;  // Incrementa el contador de pulsaciones

        if (!esCorrecta) {
            errores++;  // Incrementa el contador de errores si la pulsación fue incorrecta
            lblErrores.setText("Errores: " + errores);  // Actualiza la etiqueta de errores
        }

        actualizarPPM();  // Actualiza las pulsaciones por minuto
    }

    // Método para actualizar las pulsaciones por minuto (PPM)
    private void actualizarPPM() {
        long tiempoTranscurrido = (System.currentTimeMillis() - startTime) / 1000;  // Calcula el tiempo transcurrido en segundos
        if (tiempoTranscurrido > 0) {
            // Calcula el PPM y lo muestra en la etiqueta
            int ppm = (int) ((pulsaciones / (double) tiempoTranscurrido) * 60);
            lblPPM.setText("PPM: " + ppm);
        }
    }

    // Método para actualizar el tiempo transcurrido
    private void actualizarTiempo() {
        long tiempoTranscurrido = (System.currentTimeMillis() - startTime) / 1000;  // Calcula el tiempo en segundos
        lblTiempo.setText("Tiempo: " + tiempoTranscurrido + "s");  // Muestra el tiempo en la etiqueta
    }

    // Método para reiniciar los contadores y el temporizador
    public void reiniciar() {
        errores = 0;  // Reinicia el contador de errores
        pulsaciones = 0;  // Reinicia el contador de pulsaciones
        timerStarted = false;  // Reinicia el estado del temporizador
        timer.stop();  // Detiene el temporizador

        // Reinicia las etiquetas de la interfaz
        lblPPM.setText("PPM: 0");
        lblErrores.setText("Errores: 0");
        lblTiempo.setText("Tiempo: 0s");
    }
}
