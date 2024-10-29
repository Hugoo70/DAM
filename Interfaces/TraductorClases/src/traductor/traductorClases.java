package traductor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.TextArea;

public class traductorClases {

	private JFrame frmTraductorEspaol;
	private JTextField sEspanol;
	private JTextField sResultado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					traductorClases window = new traductorClases();
					window.frmTraductorEspaol.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public traductorClases() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTraductorEspaol = new JFrame();
		frmTraductorEspaol.setIconImage(Toolkit.getDefaultToolkit().getImage(traductorClases.class.getResource("/PruebaWindowBuilder/Google_Translate_logo.svg.png")));
		frmTraductorEspaol.setTitle("Traductor Español - Inglés");
		frmTraductorEspaol.setBounds(100, 100, 450, 300);
		frmTraductorEspaol.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTraductorEspaol.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("TRADUCTOR DAM");
		lblNewLabel.setBackground(new Color(0, 128, 192));
		lblNewLabel.setForeground(new Color(0, 128, 192));
		lblNewLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 13));
		lblNewLabel.setBounds(155, 24, 118, 30);
		frmTraductorEspaol.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Introduce palabra a traducir");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(48, 72, 239, 30);
		frmTraductorEspaol.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Palabra traducida");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(48, 135, 148, 14);
		frmTraductorEspaol.getContentPane().add(lblNewLabel_2);

		sEspanol = new JTextField();
		sEspanol.setBounds(226, 78, 125, 20);
		frmTraductorEspaol.getContentPane().add(sEspanol);
		sEspanol.setColumns(10);

		sResultado = new JTextField();
		sResultado.setSelectionColor(new Color(0, 0, 0));
		sResultado.setFont(new Font("Tahoma", Font.BOLD, 11));
		sResultado.setEnabled(false);
		sResultado.setBounds(226, 133, 125, 20);
		frmTraductorEspaol.getContentPane().add(sResultado);
		sResultado.setColumns(10);

		JButton btnNewButton = new JButton("Traducir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (sEspanol.getText().isBlank() != true) {
					String palabraESP = sEspanol.getText();
					sResultado.setText(traducirING(palabraESP));// Escribir el resultado en la caja de texto "gris"
				} else {
					JOptionPane.showInternalMessageDialog(null, "No hay palabra a traducir", "Error: ", 0);
				}

			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 128, 192));
		btnNewButton.setBounds(262, 192, 89, 23);
		frmTraductorEspaol.getContentPane().add(btnNewButton);
	}

	protected String traducirING(String palabraESP) {

		String cadena = null;
		Document document = null;

		String webPage = "https://www.ingles.com/traductor/" + palabraESP;

		try {
			document = Jsoup.connect(webPage).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		cadena = document.getElementById("quickdef1-es").getElementsByClass("tCur1iYH").html();

		return cadena;

	}
}