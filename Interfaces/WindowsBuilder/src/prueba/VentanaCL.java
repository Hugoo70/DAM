package prueba;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class VentanaCL {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					VentanaCL window = new VentanaCL();
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
	public VentanaCL() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaCL.class.getResource("/prueba/images.png")));
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 0, 0));
		frame.getContentPane().add(panel, "name_74927979844200");
		panel.setLayout(null);

		JButton btnNewButton = new JButton("SIGUIENTE");
		btnNewButton.setBounds(176, 112, 102, 23);
		panel.add(btnNewButton);


		JLabel lblNewLabel = new JLabel("0");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(208, 59, 70, 42);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setVisible(false);
		panel_1.setBackground(new Color(255, 255, 0));
		frame.getContentPane().add(panel_1, "name_74927987762700");
		panel_1.setLayout(null);

		JButton btnNewButton_1 = new JButton("SIGUIENTE");
		btnNewButton_1.setBounds(175, 110, 99, 23);
		panel_1.add(btnNewButton_1);

		JLabel lblNewLabel_1 = new JLabel("1");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(96, 92, 70, 42);
		panel_1.add(lblNewLabel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setVisible(false);
		panel_2.setBackground(new Color(255, 0, 0));
		frame.getContentPane().add(panel_2, "name_74927995515700");
		panel_2.setLayout(null);

		JButton btnNewButton_2 = new JButton("SIGUIENTE");
		btnNewButton_2.setBounds(176, 111, 104, 23);
		panel_2.add(btnNewButton_2);

		JLabel lblNewLabel_2 = new JLabel("2");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(207, 145, 70, 42);
		panel_2.add(lblNewLabel_2);

		JPanel panel_3 = new JPanel();
		panel_3.setVisible(false);
		panel_3.setBackground(new Color(255, 255, 0));
		frame.getContentPane().add(panel_3, "name_75016640032800");
		panel_3.setLayout(null);

		JButton btnNewButton_3 = new JButton("SIGUIENTE");
		btnNewButton_3.setBounds(166, 105, 106, 23);
		panel_3.add(btnNewButton_3);

		JLabel lblNewLabel_3 = new JLabel("3");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_3.setBounds(295, 87, 70, 42);
		panel_3.add(lblNewLabel_3);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				panel_1.setVisible(true);
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(false);
				panel_2.setVisible(true);

			}
		});

		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(false);
				panel_3.setVisible(true);

			}
		});

		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel_3.setVisible(false);
				panel.setVisible(true);

			}
		});
	}
}
