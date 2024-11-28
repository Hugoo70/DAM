package Paneles;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class Carga extends JPanel {

    // Declaración de los componentes: barra de progreso y contador
	private JProgressBar progressBar;
	private int contador = 0;
	private Image imagenFondo;  // Imagen de fondo que se utilizará en el panel

    // Constructor de la clase Carga
	public Carga(Image fondo) {
        // Recibimos la imagen de fondo y la asignamos a la variable
		imagenFondo = fondo;

		setLayout(null); // Usamos un layout nulo para poder posicionar los componentes manualmente

        // Inicialización de la barra de progreso
		progressBar = new JProgressBar(0, 6);  // Establecemos el rango de la barra de 0 a 6
		progressBar.setVisible(true);  // Hacemos visible la barra de progreso
		progressBar.setBounds(50, 245, 619, 34);  // Definimos la posición y tamaño de la barra
		add(progressBar);  // Añadimos la barra de progreso al panel

	}

    // Método para obtener el contador actual
	public int getContador() {
		return contador;
	}

    // Método para obtener la barra de progreso
	public JProgressBar getBarraProgreso() {
		return progressBar;
	}

    // Método para colocar la imagen como fondo del panel
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);  // Llamamos al método de la clase base para asegurar que se dibuje correctamente
		g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), null);  // Dibuja la imagen de fondo en el panel, ajustándola al tamaño del mismo
	}
}
