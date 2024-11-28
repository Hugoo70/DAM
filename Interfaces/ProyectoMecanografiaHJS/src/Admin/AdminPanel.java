package Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Paneles.Mecanografia;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import TXT.LecturaEscritura;

public class AdminPanel extends JPanel {
    // Botones
    private JButton btnGestionUsuarios;
    private JButton btnVolverLogin;
    private JButton btnCambiarLecciones;
    private JButton btnEnviarEmail;


    public AdminPanel() {
        setLayout(new GridLayout(2, 2, 10, 10)); // 2 filas, 2 columnas con espacio de 10 píxeles entre los botones

        // Inicialización de los botones
        btnVolverLogin = new JButton("Volver al Login");
        btnGestionUsuarios = new JButton("Gestionar Usuarios");
        btnCambiarLecciones = new JButton("Cambiar Lecciones");
        btnEnviarEmail = new JButton("Enviar Email");

        // Establecer colores de fondo para cada botón
        btnGestionUsuarios.setBackground(Color.CYAN);
        btnVolverLogin.setBackground(Color.PINK);
        btnCambiarLecciones.setBackground(Color.YELLOW);
        btnEnviarEmail.setBackground(Color.GREEN);

        // Agregar los botones al panel
        add(btnGestionUsuarios);
        add(btnCambiarLecciones);
        add(btnEnviarEmail);
        add(btnVolverLogin);

        // Configurar las acciones de los botones
        configurarAcciones();
    }

    // Método para configurar las acciones de cada botón
    private void configurarAcciones() {
        // Acción para el botón "Gestionar Usuarios"
        btnGestionUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestionUsuarios gestionUsuarios = new GestionUsuarios();
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(AdminPanel.this);
                // Limpiar el contenido actual del frame y agregar el nuevo panel
                frame.getContentPane().removeAll();
                frame.add(gestionUsuarios);
                frame.revalidate();
                frame.repaint();
            }
        });

        // Acción para el botón "Cambiar Lecciones"
        btnCambiarLecciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CambiarLecciones cambiarLecciones = new CambiarLecciones();
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(AdminPanel.this);
                // Limpiar el contenido actual del frame y agregar el nuevo panel
                frame.getContentPane().removeAll();
                frame.add(cambiarLecciones);
                frame.revalidate();
                frame.repaint();
            }
        });

        // Acción para el botón "Enviar Email"
        btnEnviarEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(AdminPanel.this);
                frame.getContentPane().removeAll();
                frame.add(new EnviarEmail());
                frame.revalidate();
                frame.repaint();
            }
        });

        // Acción para el botón "Volver al Login"
        btnVolverLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(AdminPanel.this);
                if (frame instanceof Mecanografia) {
                    // Reutiliza el método mostrarPanelLogin para regresar al login
                    ((Mecanografia) frame).mostrarPanelLogin();
                }
            }
        });
    }

    // Métodos para acceder a los botones desde fuera de la clase, si es necesario
    public JButton getBtnGestionUsuarios() {
        return btnGestionUsuarios;
    }

    public JButton getBtnVolverLogin() {
        return btnVolverLogin;
    }

    public JButton getBtnCambiarLecciones() {
        return btnCambiarLecciones;
    }

    public JButton getBtnEnviarEmail() {
        return btnEnviarEmail;
    }
}
