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

	private JProgressBar progressBar;
	private int contador = 0;
	private Image imagenFondo = requestImage();

	public Carga() {
		setLayout(null);
		progressBar = new JProgressBar(0, 6);
		progressBar.setVisible(true);
		progressBar.setBounds(50, 245, 619, 34);
		progressBar.setBorderPainted(false);
		progressBar.setStringPainted(true);
		add(progressBar);

	}

	public int getContador() {
		return contador;
	}

	public JProgressBar getBarraProgreso() {
		return progressBar;
	}

	private Image requestImage() {
		BufferedImage imagen = null;
		try {
			imagen = ImageIO.read(new File("src/img/fondoCarga.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imagen;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), null);

	}
}
