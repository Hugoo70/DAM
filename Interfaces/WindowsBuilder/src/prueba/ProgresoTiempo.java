package prueba;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class ProgresoTiempo {

	private JFrame frame;
	Timer crono;
	int i = 10;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ProgresoTiempo window = new ProgresoTiempo();
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
	public ProgresoTiempo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton start = new JButton("START");
		start.setBounds(57, 115, 89, 23);
		frame.getContentPane().add(start);

		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				crono.start();

			}
		});

		JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setToolTipText("");
		progressBar.setBounds(91, 179, 253, 23);
		frame.getContentPane().add(progressBar);

		JLabel lblNewLabel = new JLabel("10");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel.setBounds(296, 91, 59, 52);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("cargando...");
		lblNewLabel_1.setVisible(false);
		lblNewLabel_1.setBounds(189, 203, 115, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 0, 0));
		panel.setVisible(false);
		panel.setBounds(0, 0, 450, 300);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Cargado!");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\CFGS.LAB37_PC\\git\\Interfaces\\WindowsBuilder\\src\\prueba\\icegif-1617.gif"));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel_2.setBounds(0, 0, 450, 300);
		panel.add(lblNewLabel_2);

		crono = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				i--;
				lblNewLabel.setText(String.valueOf(i));

				if (i == 0) {
					panel.setVisible(true);
					lblNewLabel_1.setVisible(false);
					progressBar.setVisible(false);
					start.setVisible(false);
					lblNewLabel.setVisible(false);

					crono.stop();

				}
				progressBar.setValue(progressBar.getValue()+10);

				if (i > 0) {
					lblNewLabel_1.setVisible(true);
					lblNewLabel_1.setLocation((int)lblNewLabel_1.getLocation().getX(),(int)lblNewLabel_1.getLocation().getY()-4);


				}

			}
		}) {

		};

	}
}
