package prueba;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class ImagenFondo {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ImagenFondo window = new ImagenFondo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ImagenFondo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 211);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().add(createContentPanelConFondo());
		/*
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(85, 267, 402, 484);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\CFGS.LAB37_PC\\Interfaces\\WindowBuilder\\src\\prueba\\icegif-1617.gif"));
*/
	}

	private Component createContentPanelConFondo() {
		final Image imagenFondo = requestImage();

		JPanel panel = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				int W = getWidth();
				int H = getHeight();
				g.drawImage(imagenFondo, 0, 0,W,H, null);
			}
		};

		panel.setSize(272, 161);

		return panel;
	}

	private Image requestImage() {
		BufferedImage imagen = null;
		try {
			imagen = ImageIO.read(new File("src/prueba/descarga.jfif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imagen;
	}

}
