package prueba;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class cuatroPaneles {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					cuatroPaneles window = new cuatroPaneles();
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
	public cuatroPaneles() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.getContentPane().setLayout(new CardLayout(0, 0));


		JPanel panel = new JPanel();
		panel.setAutoscrolls(true);
		panel.setBackground(new Color(0, 128, 192));
		panel.setLayout(null);
		frame.getContentPane().add(panel, "name_71599825922800");

		JButton btnNewButton = new JButton("Mostrar el panel 1");
		btnNewButton.setBounds(47, 135, 119, 23);
		panel.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("0");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setMaximumSize(new Dimension(6, 20));
		lblNewLabel.setBounds(67, 43, 71, 43);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 0, 0));
		panel_1.setLayout(null);
		frame.getContentPane().add(panel_1, "name_71599840208900");

		JButton btnMostrarElPanel = new JButton("Mostrar el panel 2");
		btnMostrarElPanel.setBounds(51, 135, 119, 23);
		panel_1.add(btnMostrarElPanel);

		JLabel lblNewLabel_1 = new JLabel("1");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(78, 47, 75, 37);
		panel_1.add(lblNewLabel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 128, 0));
		panel_2.setLayout(null);
		frame.getContentPane().add(panel_2, "name_71599851781400");

		JButton btnMostrarElPanel_1 = new JButton("Mostrar el panel 3");
		btnMostrarElPanel_1.setBounds(47, 140, 119, 23);
		panel_2.add(btnMostrarElPanel_1);

		JLabel lblNewLabel_1_1 = new JLabel("2");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1_1.setBounds(85, 44, 65, 58);
		panel_2.add(lblNewLabel_1_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 0));
		panel_3.setLayout(null);
		frame.getContentPane().add(panel_3, "name_71599862825900");

		JButton btnMostrarElPanel_2 = new JButton("Ocultar todo");
		btnMostrarElPanel_2.setBounds(46, 140, 119, 23);
		panel_3.add(btnMostrarElPanel_2);

		JLabel lblNewLabel_1_2 = new JLabel("3");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1_2.setBounds(81, 51, 69, 43);
		panel_3.add(lblNewLabel_1_2);
		frame.setBounds(100, 100, 439, 387);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(true);
			}
		});


		btnMostrarElPanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(true);
			}
		});


		btnMostrarElPanel_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel_3.setVisible(true);
			}
		});


		btnMostrarElPanel_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(false);
				panel_2.setVisible(false);
				panel_3.setVisible(false);
			}
		});

	}
}
