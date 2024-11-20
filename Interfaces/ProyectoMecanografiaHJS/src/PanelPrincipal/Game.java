package PanelPrincipal;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

import TXT.Estadisticas;
import TXT.Usuario;

public class Game extends JPanel {
    private int dif;
    private ArrayList<String> textos;
    private JTextArea TextoLeer; // Cambiado a JTextArea
    private JTextArea TextoEscribir; // Cambiado a JTextArea

    private static final int FACIL = 0;
    private static final int DIFICIL = 1;

    private static final int TEMPORIZADORF = 3;
    private static final int TEMPORIZADORD = 5;
    private static final int MAX_ERRORESF = 5;
    private static final int MAX_ERRORESD = 3;

    public Game(int dif, ArrayList<String> textos, ArrayList<Estadisticas> estadisticas, ArrayList<Usuario> usuarios, Usuario usuarioLogin) {
        this.dif = dif;
        this.textos = textos;

        setLayout(null);
        Dimension PantallaCompleta = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(PantallaCompleta);

        // Campo para mostrar el texto a escribir
        TextoLeer = new JTextArea();
        TextoLeer.setFont(new Font("Tahoma", Font.PLAIN, 18));
        TextoLeer.setEditable(false);
        TextoLeer.setLineWrap(true); // Envolver texto en varias líneas
        TextoLeer.setWrapStyleWord(true); // Ajustar envoltura por palabra

        JScrollPane scrollLeer = new JScrollPane(TextoLeer);
        scrollLeer.setBounds(40, 10, 1820, 275);
        add(scrollLeer);

        // Campo para el texto que el usuario escribirá
        TextoEscribir = new JTextArea();
        TextoEscribir.setFont(new Font("Tahoma", Font.PLAIN, 18));
        TextoEscribir.setLineWrap(true); // Envolver texto en varias líneas
        TextoEscribir.setWrapStyleWord(true); // Ajustar envoltura por palabra

        imprimirTextoDificultad();
    }

    public void imprimirTextoDificultad() {
        if (textos == null) {
            JOptionPane.showMessageDialog(null, "Error: Texto no disponible .", "Error", JOptionPane.ERROR_MESSAGE);
        }

        TextoLeer.setText(textos.get(dif)); // Muestra el texto según la dificultad

        if (dif == FACIL) {
            comprobarTexto(FACIL, TEMPORIZADORF, MAX_ERRORESF);
            contadorDeInicio(FACIL, TEMPORIZADORF);
        } else if (dif == DIFICIL) {
            comprobarTexto(DIFICIL, TEMPORIZADORD, MAX_ERRORESD);
            contadorDeInicio(DIFICIL, TEMPORIZADORD);
        }
    }

    private void comprobarTexto(int nivel, int temporizador, int maxErrores) {
        // Lógica para comprobar texto según dificultad
    }

    private void contadorDeInicio(int nivel, int temporizador) {
        // Lógica para iniciar el temporizador
    }
}
