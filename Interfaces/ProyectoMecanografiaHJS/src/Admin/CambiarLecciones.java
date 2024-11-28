package Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import TXT.LecturaEscritura;

public class CambiarLecciones extends JPanel {
    // TextArea
    private JTextArea textoFacil;
    private JTextArea textoDificil;
    
    // Botones
    private JButton btnGuardar;
    private JButton btnCancelar;
    
    // Clases
    private LecturaEscritura gestorArchivos;

    public CambiarLecciones() {
        setLayout(new BorderLayout());

        gestorArchivos = new LecturaEscritura();
        gestorArchivos.FicheroTexto("src/TXT/textos.txt");
        ArrayList<String> textos = gestorArchivos.getListaTexto();
        JPanel panelTextos = new JPanel(new GridLayout(2, 1, 10, 10));

        // Texto Fácil
        textoFacil = new JTextArea(!textos.isEmpty() ? textos.get(0) : ""); // Cargar texto si existe
        textoFacil.setBorder(BorderFactory.createTitledBorder("Texto Fácil"));
        textoFacil.setLineWrap(true); // Ajustar las líneas automáticamente
        textoFacil.setWrapStyleWord(true); // Ajustar palabras completas
        JScrollPane scrollFacil = new JScrollPane(textoFacil); // Agregar barra Scroll
        panelTextos.add(scrollFacil);

        // Texto Difícil
        textoDificil = new JTextArea(textos.size() > 1 ? textos.get(1) : ""); // Cargar texto si existe
        textoDificil.setBorder(BorderFactory.createTitledBorder("Texto Difícil"));
        textoDificil.setLineWrap(true);
        textoDificil.setWrapStyleWord(true);
        JScrollPane scrollDificil = new JScrollPane(textoDificil); // Barra de Scroll
        panelTextos.add(scrollDificil);

        add(panelTextos, BorderLayout.CENTER);

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        btnGuardar = new JButton("Guardar Cambios"); // Guardar cambios
        btnCancelar = new JButton("Cancelar"); // Cancelar
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar); 
        add(panelBotones, BorderLayout.SOUTH); // Agregar los botones en la parte inferior

        // Configurar las acciones de los botones
        configurarAcciones();
    }

    // Configuración de las acciones para los botones
    private void configurarAcciones() {
        // Acción para el botón "Guardar Cambios"
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/TXT/textos.txt"))) {
                    // Guardar el texto fácil en el archivo
                    bw.write(textoFacil.getText().trim());
                    bw.newLine(); // Separar los textos con una nueva línea
                    // Guardar el texto difícil en el archivo
                    bw.write(textoDificil.getText().trim());
                    bw.newLine(); // Separar los textos con una nueva línea
                    // Mostrar mensaje de éxito
                    JOptionPane.showMessageDialog(null, "Textos actualizados con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    // Mostrar mensaje de error si hay un problema al guardar
                    JOptionPane.showMessageDialog(null, "Error al guardar los textos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción para el botón "Cancelar"
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la ventana principal y cambiarla al panel de administración
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(CambiarLecciones.this);
                AdminPanel adminPanel = new AdminPanel(); // Crear un nuevo panel de administración
                frame.getContentPane().removeAll(); // Limpiar el contenido actual
                frame.add(adminPanel); // Agregar el panel de administración
                frame.revalidate(); // Volver a validar el layout
                frame.repaint(); // Volver a dibujar la ventana
            }
        });
    }
}
