package PanelPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBarPanel extends JPanel {
	private JMenuBar menuBar;
	private JMenu menuAcercaDe;
	private JMenuItem itemAcercaDeNosotros;


	public MenuBarPanel() {

		setLayout(new BorderLayout());

		// Crear el menú bar
		menuBar = new JMenuBar();

		// Crear el menú "Acerca de"
		menuAcercaDe = new JMenu("Acerca de");
		itemAcercaDeNosotros = new JMenuItem("Sobre Nosotros");
		menuAcercaDe.add(itemAcercaDeNosotros);
		menuBar.add(menuAcercaDe);

		// Agregar el menú bar al panel
		add(menuBar, BorderLayout.NORTH);

		// Configurar acciones
		configurarAcciones();
	}

	private void configurarAcciones() {
		// Acción para "Sobre Nosotros"
		itemAcercaDeNosotros.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Aplicación de mecanografía.\n"
								+ "Desarrollado por Hugo Jiménez para Desarrollo de Interfaces, 6º DAM.\n"
								+ "Versión 1.0.0 - 2024. (Por no decir Versión 9999.9999.9999 :D )",
						"Acerca de Nosotros", JOptionPane.INFORMATION_MESSAGE);
			}
		});

	}
}
