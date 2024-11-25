package Admin;

import javax.swing.*;
import java.awt.*;

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
