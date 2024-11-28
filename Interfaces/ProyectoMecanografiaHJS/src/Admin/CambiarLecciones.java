package Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import TXT.LecturaEscritura;

public class CambiarLecciones extends JPanel {
    private JTextArea textoFacil;
    private JTextArea textoDificil;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private LecturaEscritura gestorArchivos;

    public CambiarLecciones() {
        setLayout(new BorderLayout());
        gestorArchivos = new LecturaEscritura();
        gestorArchivos.FicheroTexto("src/TXT/textos.txt");
        ArrayList<String> textos = gestorArchivos.getListaTexto();

        // Panel de textos
        JPanel panelTextos = new JPanel(new GridLayout(2, 1, 10, 10));

        // Texto Fácil
        textoFacil = new JTextArea(textos.get(0));
        textoFacil.setBorder(BorderFactory.createTitledBorder("Texto Fácil"));
        textoFacil.setLineWrap(true);
        textoFacil.setWrapStyleWord(true);
        JScrollPane scrollFacil = new JScrollPane(textoFacil);
        panelTextos.add(scrollFacil);

        // Texto Difícil
        textoDificil = new JTextArea(textos.get(1));
        textoDificil.setBorder(BorderFactory.createTitledBorder("Texto Difícil"));
        textoDificil.setLineWrap(true);
        textoDificil.setWrapStyleWord(true);
        JScrollPane scrollDificil = new JScrollPane(textoDificil);
        panelTextos.add(scrollDificil);

        add(panelTextos, BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel();
        btnGuardar = new JButton("Guardar Cambios");
        btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

        configurarAcciones();
    }

    private void configurarAcciones() {
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (FileWriter fw = new FileWriter("src/TXT/textos.txt")) {
                    fw.write(textoFacil.getText() + ";" + textoDificil.getText() + ";");
                    JOptionPane.showMessageDialog(null, "Textos actualizados con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al guardar los textos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(CambiarLecciones.this);
                AdminPanel adminPanel = new AdminPanel();
                frame.getContentPane().removeAll();
                frame.add(adminPanel);
                frame.revalidate();
                frame.repaint();
            }
        });
    }
}

