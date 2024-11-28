package Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import PanelPrincipal.Mail;

public class EnviarEmail extends JPanel {

    private JTextField textFieldTo;   // Campo para ingresar el destinatario
    private JTextField textFieldSubject;  // Campo para ingresar el asunto
    private JTextArea textAreaBody;   // Área de texto para el cuerpo del mensaje
    private JButton btnCancelar;  // Botón para cancelar la operación

    public EnviarEmail() {
        setLayout(null); // Establecer el layout a null para colocar los componentes manualmente

        // Etiqueta "To"
        JLabel lblTo = new JLabel("To:");  
        lblTo.setFont(new Font("Tahoma", Font.PLAIN, 18));  // Definir el estilo de la fuente
        lblTo.setBounds(20, 20, 100, 25);  // Establecer la posición y tamaño de la etiqueta
        add(lblTo);  // Añadir la etiqueta al panel

        // Campo para ingresar el destinatario (To)
        textFieldTo = new JTextField();
        textFieldTo.setBounds(120, 20, 250, 25);  // Posición y tamaño del campo de texto
        add(textFieldTo);  // Añadir el campo de texto al panel
        textFieldTo.setColumns(10);  // Establecer el número de columnas (ancho del campo de texto)

        // Etiqueta "Asunto"
        JLabel lblSubject = new JLabel("Asunto:");
        lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblSubject.setBounds(20, 60, 100, 25);
        add(lblSubject);

        // Campo para ingresar el asunto
        textFieldSubject = new JTextField();
        textFieldSubject.setBounds(120, 60, 250, 25);
        add(textFieldSubject);
        textFieldSubject.setColumns(10);

        // Etiqueta "Cuerpo"
        JLabel lblBody = new JLabel("Cuerpo:");
        lblBody.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblBody.setBounds(20, 100, 100, 25);
        add(lblBody);

        // Área de texto para el cuerpo del mensaje
        textAreaBody = new JTextArea();
        textAreaBody.setLineWrap(true);  // Activar el ajuste automático de líneas
        textAreaBody.setWrapStyleWord(true);  // Ajustar las palabras en lugar de cortar palabras a la mitad

        // Scroll para el área de texto (para que el texto sea desplazable)
        JScrollPane scrollPane = new JScrollPane(textAreaBody);
        scrollPane.setBounds(120, 100, 250, 100);  // Establecer la posición y tamaño del área de texto con su scroll
        add(scrollPane);

        // Botón "Enviar"
        JButton btnSend = new JButton("Enviar");
        btnSend.setBounds(120, 220, 100, 30);  // Establecer la posición y tamaño del botón
        add(btnSend);  // Añadir el botón al panel

        // Etiqueta "Correo enviado"
        JLabel lblSent = new JLabel("Correo enviado");
        lblSent.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSent.setBounds(240, 220, 150, 30);
        lblSent.setVisible(false);  // Esta etiqueta está oculta por defecto
        add(lblSent);

        // Botón "Cancelar"
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(160, 270, 100, 30);  // Centrar el botón en la parte inferior
        add(btnCancelar);

        // Acción del botón "Enviar"
        btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores ingresados por el usuario
                String to = textFieldTo.getText().trim();
                String subject = textFieldSubject.getText().trim();
                String body = textAreaBody.getText().trim();

                // Validación de campos vacíos
                if (to.isEmpty() || subject.isEmpty() || body.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.",
                            "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                    return;  // Salir si algún campo está vacío
                }

                try {
                    // Intentar enviar el correo utilizando la clase Mail
                    Mail.enviarMail(to, subject, body);
                    lblSent.setVisible(true);  // Mostrar el mensaje de éxito
                } catch (Exception ex) {
                    // Mostrar un mensaje de error si algo falla al enviar el correo
                    JOptionPane.showMessageDialog(null, "Error al enviar el correo:\n" + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción del botón "Cancelar"
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Regresar al panel de administración (AdminPanel)
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(EnviarEmail.this);
                AdminPanel adminPanel = new AdminPanel();  // Crear una instancia del panel de administración
                frame.getContentPane().removeAll();  // Limpiar el contenido actual
                frame.add(adminPanel);  // Añadir el panel de administración
                frame.revalidate();  // Volver a validar el layout
                frame.repaint();  // Volver a dibujar el frame
            }
        });
    }
}
