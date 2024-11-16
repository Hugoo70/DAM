package Paneles;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;

public class Partida extends JPanel{
	public Partida() {
		setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 11, 1223, 22);
		add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Opciones");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Cambiar usuario");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("Sobre Nosotros");
		menuBar.add(mnNewMenu_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(198, 135, 46, 14);
		add(lblNewLabel);
	}


}
