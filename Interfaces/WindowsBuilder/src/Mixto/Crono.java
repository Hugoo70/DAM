package Mixto;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class Crono {

	private JFrame frame;
	Timer crono;
	int i = 5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Crono window = new Crono();
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
	public Crono() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(192, 192, 192));

		JLabel lblNewLabel = new JLabel("CUENTA ATRÁS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblNewLabel.setBounds(103, 29, 229, 89);
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("START");
		btnNewButton.setBackground(new Color(0, 255, 64));
		btnNewButton.setBounds(67, 160, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("10");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(287, 151, 55, 32);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("xxxxx");
		lblNewLabel_2.setBounds(0, 0, 434, 261);
		lblNewLabel_2.setVisible(false);
		frame.getContentPane().add(lblNewLabel_2);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				i=5;
				crono.start();
				lblNewLabel_1.setText(String.valueOf(i));
				lblNewLabel_2.setVisible(false);

			}
		});


		crono = new Timer(10, new ActionListener()  {
			@Override
			public void actionPerformed(ActionEvent e) {
				i--;
				lblNewLabel_1.setText(String.valueOf(i));

				if(i==0) {
					lblNewLabel_2.setVisible(true);
					frame.getContentPane().add(createContentPanelConFondo());
					crono.stop();

				}


			}
			private Component createContentPanelConFondo() {
				final Image imagenFondo = requestImage();

				JPanel panel = new JPanel() {

					@Override
					protected void paintComponent(Graphics g) {
						super.paintComponent(g);
						int W = frame.getWidth();
						int H = frame.getHeight();
						g.drawImage(imagenFondo, 0, 0, W, H, null);
					}
				};

				int W = frame.getWidth();
				int H = frame.getHeight();
				panel.setSize(W, H);

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
		});
	}

}
