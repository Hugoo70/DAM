
package Admin;

import javax.swing.*;

import Paneles.Login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import TXT.LecturaEscritura;

public class AdminPanel extends JPanel {
    private JButton btnGestionUsuarios;
    private JButton btnVolverLogin;
    private JButton btnCambiarLecciones;
    private JButton btnEnviarEmail;

    public AdminPanel() {
        // Diseño de 2x2 con espacios entre componentes
        setLayout(new GridLayout(2, 2, 10, 10));
        setBackground(Color.LIGHT_GRAY);

        // Inicializar botones
        btnVolverLogin = new JButton("Volver al Login");
        btnGestionUsuarios = new JButton("Gestionar Usuarios");
        btnCambiarLecciones = new JButton("Cambiar Lecciones");
        btnEnviarEmail = new JButton("Enviar Email");

        // Establecer colores de fondo
        btnGestionUsuarios.setBackground(Color.CYAN);
        btnVolverLogin.setBackground(Color.PINK);
        btnCambiarLecciones.setBackground(Color.YELLOW);
        btnEnviarEmail.setBackground(Color.GREEN);

        // Agregar botones al panel
        add(btnGestionUsuarios);
        add(btnCambiarLecciones);
        add(btnEnviarEmail);
        add(btnVolverLogin);

        // Configuración de acciones de botones
        configurarAcciones();
    }

    private void configurarAcciones() {
        btnGestionUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestionUsuarios gestionUsuarios = new GestionUsuarios();
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(AdminPanel.this);
                frame.getContentPane().removeAll();
                frame.add(gestionUsuarios);
                frame.revalidate();
                frame.repaint();
            }
        });

        btnCambiarLecciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CambiarLecciones cambiarLecciones = new CambiarLecciones();
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(AdminPanel.this);
                frame.getContentPane().removeAll();
                frame.add(cambiarLecciones);
                frame.revalidate();
                frame.repaint();
            }
        });

        btnEnviarEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnviarEmail enviarEmail = new EnviarEmail();
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(AdminPanel.this);
                frame.getContentPane().removeAll();
                frame.add(enviarEmail);
                frame.revalidate();
                frame.repaint();
            }
        });

        btnVolverLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(AdminPanel.this);
                frame.getContentPane().removeAll();
                frame.add(login);
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    // Métodos para acceder a los botones
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

