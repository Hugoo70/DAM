package PanelPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBarPanel extends JPanel {
    private JMenuBar menuBar;  // Barra de menú que contiene los elementos del menú
    private JMenu menuAcercaDe;  // Menú "Acerca de"
    private JMenuItem itemAcercaDeNosotros;  // Opción de menú "Sobre Nosotros"

    // Constructor de la clase MenuBarPanel
    public MenuBarPanel() {

        setLayout(new BorderLayout());  // Establece el layout del panel a BorderLayout

        // Crear la barra de menú
        menuBar = new JMenuBar();

        // Crear el menú "Acerca de" y añadir la opción "Sobre Nosotros"
        menuAcercaDe = new JMenu("Acerca de");  // Menú principal "Acerca de"
        itemAcercaDeNosotros = new JMenuItem("Sobre Nosotros");  // Opción del menú
        menuAcercaDe.add(itemAcercaDeNosotros);  // Añadir la opción al menú "Acerca de"
        menuBar.add(menuAcercaDe);  // Añadir el menú "Acerca de" a la barra de menú

        // Agregar la barra de menú al panel en la parte superior
        add(menuBar, BorderLayout.NORTH);

        // Configurar las acciones de los elementos del menú
        configurarAcciones();
    }

    // Configura las acciones para los elementos del menú
    private void configurarAcciones() {
        // Acción para la opción "Sobre Nosotros"
        itemAcercaDeNosotros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostrar un mensaje con información sobre la aplicación
                JOptionPane.showMessageDialog(null,
                        "Aplicación de mecanografía.\n"
                                + "Desarrollado por Hugo Jiménez para Desarrollo de Interfaces, 6º DAM.\n"
                                + "Versión 1.0.0 - 2024. (Por no decir Versión 9999.9999.9999 :D )",
                        "Acerca de Nosotros", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
