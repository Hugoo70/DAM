package PanelPrincipal;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.text.*;

public class Texto {
    private JTextPane textoLeer;
    private JTextArea textoEscribir;

    public Texto() {
        // Configuración del JTextPane (Texto de referencia)
        textoLeer = new JTextPane();
        textoLeer.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textoLeer.setEditable(false);

        // Configuración del JTextArea (Texto del usuario)
        textoEscribir = new JTextArea();
        textoEscribir.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textoEscribir.setLineWrap(true);
        textoEscribir.setWrapStyleWord(true);
    }

    // Obtener componentes
    public JTextPane getTextoLeer() {
        return textoLeer;
    }

    public JTextArea getTextoEscribir() {
        return textoEscribir;
    }

    // Mostrar el texto de referencia en el JTextPane
    public void setTextoReferencia(String texto) {
        textoLeer.setText(texto);
    }

    // Actualizar colores del texto de referencia basado en la entrada del usuario
    public void actualizarColores(String textoReferencia) {
        String textoUsuario = textoEscribir.getText();
        StyledDocument sd = textoLeer.getStyledDocument();
        Style estiloDefecto = textoLeer.addStyle("default", null);
        StyleConstants.setForeground(estiloDefecto, Color.BLACK);
        sd.setCharacterAttributes(0, textoReferencia.length(), estiloDefecto, true);

        for (int i = 0; i < textoUsuario.length(); i++) {
            Style estilo = textoLeer.addStyle("color", null);
            if (i < textoReferencia.length() && textoUsuario.charAt(i) == textoReferencia.charAt(i)) {
                StyleConstants.setForeground(estilo, Color.GREEN);
            } else {
                StyleConstants.setForeground(estilo, Color.RED);
            }
            sd.setCharacterAttributes(i, 1, estilo, true);
        }
    }
}
