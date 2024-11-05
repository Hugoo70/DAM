package Mixto;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class CambioFondoMenu {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				CambioFondoMenu window = new CambioFondoMenu();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public CambioFondoMenu() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel_1_1 = new JLabel("Menu - cambio de fondo");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1_1.setBounds(54, 120, 324, 37);
		frame.getContentPane().add(lblNewLabel_1_1);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("FONDO");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Cambio");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.setContentPane(createContentPanelConFondo());

			}
		});
		mnNewMenu.add(mntmNewMenuItem);
	}

	private JPanel createContentPanelConFondo() {
		final Image imagenFondo = requestImage();

		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
			}
		};

		panel.setLayout(null);
		return panel;
	}

	private Image requestImage() {
		BufferedImage imagen = null;
		try {
			imagen = ImageIO.read(new File("src/prueba/descarga.jfif"));
		} catch (IOException e) {
			System.out.println("Error al cargar la imagen: " + e.getMessage());
		}
		return imagen;
	}
}
