package PanelPrincipal;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Texto extends JPanel{
	private JTextArea textArea;
	public Texto() {
		setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(0, 0, 1486, 562);
		add(textArea);
	}
	
	public JTextArea getTexto() {
		return textArea;
	}
	public void setTexto(JTextArea textArea) {
		this.textArea = textArea;
	}
	

}
