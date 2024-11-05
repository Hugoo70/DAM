package prueba;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public class VentanaTraductor {

	private JFrame frmPrueba;
	private JTextField INombre;
	private JTextField textField_1;

	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {

		UIManager.LookAndFeelInfo [] looks=UIManager.getInstalledLookAndFeels();

		for (LookAndFeelInfo look : looks) {

			System.out.println("Looks instalados: " + look.getClassName());


		}

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					VentanaTraductor window = new VentanaTraductor();
					window.frmPrueba.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaTraductor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrueba = new JFrame();
		frmPrueba.getContentPane().setBackground(new Color(255, 255, 0));
		frmPrueba.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(87, 55, 56, 14);
		frmPrueba.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Apellidos");
		lblNewLabel_1.setBounds(87, 97, 56, 14);
		frmPrueba.getContentPane().add(lblNewLabel_1);

		INombre = new JTextField();
		INombre.setBackground(new Color(0, 255, 255));
		INombre.setBounds(193, 52, 86, 20);
		frmPrueba.getContentPane().add(INombre);
		INombre.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBackground(new Color(128, 128, 255));
		textField_1.setBounds(193, 94, 86, 20);
		frmPrueba.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setBackground(new Color(0, 255, 0));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {


			}
		});
		btnNewButton.setBounds(30, 195, 89, 23);
		frmPrueba.getContentPane().add(btnNewButton);

		JButton btnSigueinte = new JButton("Siguiente");
		btnSigueinte.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				frmPrueba.getContentPane().setVisible(false);

			}
		});
		btnSigueinte.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnSigueinte.setBackground(new Color(255, 0, 0));
		btnSigueinte.setBounds(315, 195, 89, 23);
		frmPrueba.getContentPane().add(btnSigueinte);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Vivo?");
		chckbxNewCheckBox.setBackground(new Color(255, 128, 255));
		chckbxNewCheckBox.setBounds(193, 135, 86, 23);
		frmPrueba.getContentPane().add(chckbxNewCheckBox);

		JSpinner spinner = new JSpinner();
		spinner.setBackground(new Color(255, 128, 0));
		spinner.setBounds(87, 136, 62, 20);
		frmPrueba.getContentPane().add(spinner);
		frmPrueba.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaTraductor.class.getResource("/prueba/images.png")));
		frmPrueba.setBounds(400, 350, 450, 300);
		frmPrueba.setLocationRelativeTo(null);
		frmPrueba.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


	}

}
