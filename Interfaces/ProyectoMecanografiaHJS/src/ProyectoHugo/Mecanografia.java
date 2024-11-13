package ProyectoHugo;

import javax.swing.JFrame;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mecanografia extends JFrame{
	
	public Mecanografia() {
		setVisible(true);
		
		setSize(800,400);
		setLocationRelativeTo(null);
		setTitle("MecanoGrafia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Carga carga = new Carga();
		add(carga);
		
	}
	

}
