package PanelPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class Info extends JPanel {
    private JLabel lblPPM;
    private JLabel lblErrores;
    private JLabel lblTiempo;

    private int errores;
    private int pulsaciones;
    private long startTime;
    private boolean timerStarted;

    private Timer timer;

    public Info() {
        setLayout(new GridLayout(3, 1));

        lblPPM = new JLabel("PPM: 0", SwingConstants.CENTER);
        lblPPM.setFont(new Font("Tahoma", Font.BOLD, 18));

        lblErrores = new JLabel("Errores: 0", SwingConstants.CENTER);
        lblErrores.setFont(new Font("Tahoma", Font.BOLD, 18));

        lblTiempo = new JLabel("Tiempo: 0s", SwingConstants.CENTER);
        lblTiempo.setFont(new Font("Tahoma", Font.BOLD, 18));

        add(lblPPM);
        add(lblErrores);
        add(lblTiempo);

        errores = 0;
        pulsaciones = 0;
        timerStarted = false;

        // Crear el temporizador que actualiza el tiempo transcurrido cada segundo
        timer = new Timer(1000, e -> actualizarTiempo());
        
    }
    public int getPulsaciones() {
        return pulsaciones;
    }
    public int getErrores() {
		return errores;
	}

	public void registrarPulsacion(boolean esCorrecta) {
        if (!timerStarted) {
            startTime = System.currentTimeMillis();
            timerStarted = true;
            timer.start();
        }

        pulsaciones++;

        if (!esCorrecta) {
            errores++;
            lblErrores.setText("Errores: " + errores);
        }

        actualizarPPM();
    }

    private void actualizarPPM() {
        long tiempoTranscurrido = (System.currentTimeMillis() - startTime) / 1000; // en segundos
        if (tiempoTranscurrido > 0) {
            int ppm = (int) ((pulsaciones / (double) tiempoTranscurrido) * 60);
            lblPPM.setText("PPM: " + ppm);
        }
    }

    private void actualizarTiempo() {
        long tiempoTranscurrido = (System.currentTimeMillis() - startTime) / 1000; // en segundos
        lblTiempo.setText("Tiempo: " + tiempoTranscurrido + "s");
    }

    public void reiniciar() {
        errores = 0;
        pulsaciones = 0;
        timerStarted = false;
        timer.stop();

        lblPPM.setText("PPM: 0");
        lblErrores.setText("Errores: 0");
        lblTiempo.setText("Tiempo: 0s");
    }
}