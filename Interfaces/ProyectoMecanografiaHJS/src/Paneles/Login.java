package Paneles;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Login extends JPanel {
    // Declaración de los componentes
    private JTextField TextUser;  // Campo de texto para ingresar el nombre de usuario
    private JPasswordField passwordField;  // Campo de texto para ingresar la contraseña
    private JButton BotonLog;  // Botón para realizar el inicio de sesión

    // Constructor de la clase Login
    public Login() {
        setLayout(null);  // Establecer layout nulo para colocar los componentes manualmente

        // Etiqueta para el título "INICIO DE SESIÓN"
        JLabel lblNewLabel = new JLabel("INICIO DE SESIÓN");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));  // Definir la fuente y tamaño del título
        lblNewLabel.setBounds(109, 72, 183, 49);  // Establecer la posición y tamaño de la etiqueta
        add(lblNewLabel);  // Añadir la etiqueta al panel

        // Etiqueta para "Usuario:"
        JLabel lblNewLabel_1 = new JLabel("Usuario:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));  // Configurar la fuente
        lblNewLabel_1.setBounds(80, 182, 73, 24);  // Establecer la posición y tamaño de la etiqueta
        add(lblNewLabel_1);  // Añadir la etiqueta al panel

        // Campo de texto para ingresar el nombre de usuario
        TextUser = new JTextField();
        TextUser.setColumns(10);  // Número de columnas (ancho del campo de texto)
        TextUser.setBounds(185, 184, 107, 24);  // Establecer la posición y tamaño del campo de texto
        add(TextUser);  // Añadir el campo de texto al panel

        // Etiqueta para "Contraseña:"
        JLabel lblNewLabel_2 = new JLabel("Contraseña:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));  // Configurar la fuente
        lblNewLabel_2.setBounds(65, 284, 88, 24);  // Establecer la posición y tamaño de la etiqueta
        add(lblNewLabel_2);  // Añadir la etiqueta al panel

        // Campo de texto para ingresar la contraseña
        passwordField = new JPasswordField();
        passwordField.setBounds(185, 288, 107, 20);  // Establecer la posición y tamaño del campo de texto
        add(passwordField);  // Añadir el campo de contraseña al panel

        // Checkbox para mostrar u ocultar la contraseña
        JCheckBox BotonPass = new JCheckBox("");  // Checkbox sin texto, solo el icono de mostrar/ocultar
        BotonPass.setBounds(297, 287, 97, 23);  // Establecer la posición y tamaño del checkbox
        add(BotonPass);  // Añadir el checkbox al panel

        // Botón para iniciar sesión
        BotonLog = new JButton("Login");
        BotonLog.setFont(new Font("Tahoma", Font.PLAIN, 18));  // Configurar la fuente y tamaño del botón
        BotonLog.setBounds(129, 362, 137, 43);  // Establecer la posición y tamaño del botón
        add(BotonLog);  // Añadir el botón al panel

        // Acción del checkbox "BotonPass" (para mostrar/ocultar la contraseña)
        BotonPass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Si el checkbox está seleccionado, mostrar la contraseña sin ocultarla
                if (BotonPass.isSelected()) {
                    passwordField.setEchoChar((char) 0);  // Mostrar el texto de la contraseña
                }

                // Si el checkbox no está seleccionado, ocultar la contraseña con un asterisco
                if (!BotonPass.isSelected()) {
                    passwordField.setEchoChar('*');  // Ocultar el texto con asteriscos
                }
            }
        });

    }

    // Métodos getter y setter para los campos de usuario, contraseña y el botón de login

    public JButton getBotonLog() {
        return BotonLog;
    }

    public void setBotonLog(JButton botonLog) {
        BotonLog = botonLog;
    }

    public JTextField getTextUser() {
        return TextUser;
    }

    public void setTextUser(JTextField textUser) {
        TextUser = textUser;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }

}
