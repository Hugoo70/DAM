package Mixto;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Random;
import java.awt.Font;

public class CazaMoscas {

	private JFrame frmCazaLaMosca;
	Random random = new Random();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CazaMoscas window = new CazaMoscas();
					window.frmCazaLaMosca.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CazaMoscas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCazaLaMosca = new JFrame();

		frmCazaLaMosca.setTitle("Caza la mosca - Hugo J");
		frmCazaLaMosca.setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\CFGS.LAB37_PC\\git\\Interfaces\\WindowsBuilder\\src\\Mixto\\mosca.jpg"));
		frmCazaLaMosca.setBounds(100, 100, 753, 561);
		frmCazaLaMosca.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCazaLaMosca.getContentPane().setLayout(null);

		JLabel lblMosca = new JLabel("");

		ImageIcon miniMosca = new ImageIcon(CazaMoscas.class.getResource("/Mixto/mosca.jpg"));
		Image miniMoscaRed = miniMosca.getImage().getScaledInstance(75, 75, Image.SCALE_FAST);
		lblMosca.setIcon(new ImageIcon(miniMoscaRed));

		lblMosca.setBounds(0, 0, 75, 75);
		frmCazaLaMosca.getContentPane().add(lblMosca);

		JLabel lblPosMosca = new JLabel("Posición de la mosca:");
		lblPosMosca.setBounds(10, 497, 129, 14);
		frmCazaLaMosca.getContentPane().add(lblPosMosca);

		JLabel lblPosRat = new JLabel("Posición del ratón:");
		lblPosRat.setBounds(525, 497, 110, 14);
		frmCazaLaMosca.getContentPane().add(lblPosRat);

		JLabel lblCoorMos = new JLabel("");
		lblCoorMos.setBounds(153, 497, 90, 14);
		frmCazaLaMosca.getContentPane().add(lblCoorMos);

		JLabel lblCoorRat = new JLabel("");
		lblCoorRat.setBounds(637, 497, 90, 14);
		frmCazaLaMosca.getContentPane().add(lblCoorRat);
		
		JLabel lblNewLabel = new JLabel("Puntos ganados: 0");
		lblNewLabel.setBounds(263, 497, 129, 14);
		frmCazaLaMosca.getContentPane().add(lblNewLabel);

		frmCazaLaMosca.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

				int Xpuntero = e.getX();
				int Ypuntero = e.getY();

				int Xlabel = lblMosca.getX();
				int Ylabel = lblMosca.getY();

				int tamX = lblMosca.getWidth();
				int tamY = lblMosca.getHeight();
				
				
				lblCoorMos.setText(Xlabel + "; " + Ylabel);
				lblCoorRat.setText(Xpuntero + "; " + Ypuntero);

				int distanX = Math.abs(Xlabel - Xpuntero);
				int distanY = Math.abs(Ylabel - Ypuntero);

				int radio = 55;

				if (distanX < radio && distanY < radio) {
					int moverX = random.nextInt(101) - radio;
					int moverY = random.nextInt(101) - radio;

					int margenX = Math.max(0, Math.min(Xlabel + moverX, frmCazaLaMosca.getWidth() - 2* tamX));
					int margenY = Math.max(0, Math.min(Ylabel + moverY, frmCazaLaMosca.getHeight() - 2* tamY));

					lblMosca.setLocation(margenX, margenY);
					
					int puntos = random.nextInt(0,200);
					lblNewLabel.setText("Puntos ganados: " + puntos );
				}
				

			}
		});
	}
}
