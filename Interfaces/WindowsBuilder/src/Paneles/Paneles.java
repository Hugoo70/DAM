package Paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.WindowConstants;

public class Paneles {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Paneles window = new Paneles();
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
	public Paneles() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 624, 527);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 255, 128));
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 128, 0));
		frame.getContentPane().add(panel_1, BorderLayout.EAST);

		JButton btnNewButton_2 = new JButton("New button");
		panel_1.add(btnNewButton_2);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 0, 0));
		frame.getContentPane().add(panel_2, BorderLayout.WEST);

		JButton btnNewButton_3 = new JButton("New button");
		panel_2.add(btnNewButton_3);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 0));
		frame.getContentPane().add(panel_3, BorderLayout.SOUTH);

		JButton btnNewButton_4 = new JButton("New button");
		panel_3.add(btnNewButton_4);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(128, 0, 255));
		frame.getContentPane().add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		panel_4.add(chckbxNewCheckBox);

		JSlider slider = new JSlider();
		panel_4.add(slider);
	}

}
