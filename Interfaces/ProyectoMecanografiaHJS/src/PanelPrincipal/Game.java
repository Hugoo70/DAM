package PanelPrincipal;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import TXT.Estadisticas;
import TXT.Usuario;

public class Game extends JPanel {
    private int dif;
    private ArrayList<String> textos;
    private Texto textoInteractivo;
    private Teclado tecladoBotones;

    public Game(int dif, ArrayList<String> textos, ArrayList<Estadisticas> estadisticas, ArrayList<Usuario> usuarios, Usuario usuarioLogin) {
        this.dif = dif;
        this.textos = textos;

        setLayout(null);
        Dimension PantallaCompleta = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(PantallaCompleta);

        textoInteractivo = new Texto();
        JScrollPane scrollLeer = new JScrollPane(textoInteractivo.getTextoLeer());
        scrollLeer.setBounds(40, 10, 1820, 275);
        add(scrollLeer);

<<<<<<< HEAD
        JScrollPane scrollEscribir = new JScrollPane(textoInteractivo.getTextoEscribir());
        scrollEscribir.setBounds(40, 327, 1820, 275);
        add(scrollEscribir);

        tecladoBotones = new Teclado();
        tecladoBotones.setBounds(40, 654, 1308, 332);
        add(tecladoBotones);
=======
        // Campo para el texto que el usuario escribirá
        TextoEscribir = new JTextArea();
        TextoEscribir.setFont(new Font("Tahoma", Font.PLAIN, 18));
        TextoEscribir.setLineWrap(true); // Envolver texto en varias líneas
        TextoEscribir.setWrapStyleWord(true); // Ajustar envoltura por palabra
>>>>>>> cd2da3f51e0c655c2c976b32bf2c5511345eb526

        imprimirTextoDificultad();

        // Agregar DocumentListener
        textoInteractivo.getTextoEscribir().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	ColorCaracter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	ColorCaracter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	ColorCaracter();
            }
        });

        // Bloquear retroceso
        textoInteractivo.getTextoEscribir().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    JOptionPane.showMessageDialog(null, "¡No puedes hacer retroceso!", "Error", JOptionPane.ERROR_MESSAGE);
                    e.consume();
                }
            }
        });
    }

    private void imprimirTextoDificultad() {
        if (textos == null || textos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: Texto no disponible.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        textoInteractivo.setTextoReferencia(textos.get(dif));
    }

    private void ColorCaracter() {
        String textoReferencia = textos.get(dif);
        textoInteractivo.actualizarColores(textoReferencia);
        String textoUsuario = textoInteractivo.getTextoEscribir().getText();
        if (!textoUsuario.isEmpty()) {
            tecladoBotones.resaltarTecla(String.valueOf(textoUsuario.charAt(textoUsuario.length() - 1)));
        } else {
            tecladoBotones.resetearTeclas();
        }
    }
}
