package Mixto;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class CronoFinPanel {

	private JFrame frame;
	Timer crono;
	int i = 5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CronoFinPanel window = new CronoFinPanel();
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
	public CronoFinPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(106, 120, 231, 14);
		panel.add(progressBar);
		progressBar.setValue(0); 

		JLabel lblNewLabel = new JLabel("Iniciando...");
		lblNewLabel.setBounds(207, 145, 78, 14);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 434, 260);
		panel_1.setVisible(false);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\CFGS.LAB37_PC\\git\\Interfaces\\WindowsBuilder\\src\\Mixto\\icegif-1617.gif"));
		lblNewLabel_1.setBounds(0, 0, 434, 260);
		panel_1.add(lblNewLabel_1);
		
		crono = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				i--;
				lblNewLabel.setText(String.valueOf(i));
				progressBar.setValue(progressBar.getValue() + 20);

				if (i == 0) {
					crono.stop(); 
					panel_1.setVisible(true);
					panel.setVisible(false);
				}
			}
		});

		crono.start();  
	}
}
