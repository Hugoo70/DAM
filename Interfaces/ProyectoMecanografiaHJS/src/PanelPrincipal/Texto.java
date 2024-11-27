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

        // Restablecer colores y fondo a los valores predeterminados
        Style estiloDefecto = textoLeer.addStyle("default", null);
        StyleConstants.setForeground(estiloDefecto, Color.BLACK);
        StyleConstants.setBackground(estiloDefecto, Color.WHITE);
        sd.setCharacterAttributes(0, textoReferencia.length(), estiloDefecto, true);

        // Resaltar caracteres correctos e incorrectos
        for (int i = 0; i < textoUsuario.length(); i++) {
            Style estilo = textoLeer.addStyle("color", null);
            if (i < textoReferencia.length() && textoUsuario.charAt(i) == textoReferencia.charAt(i)) {
                StyleConstants.setForeground(estilo, Color.GREEN); // Correcto
            } else {
                StyleConstants.setForeground(estilo, Color.RED); // Incorrecto
            }
            sd.setCharacterAttributes(i, 1, estilo, true);
        }
    }

    // Resaltar la posición actual en el texto de referencia con fondo gris oscuro
    public void resaltarPosicion(int posicion) {
        StyledDocument sd = textoLeer.getStyledDocument();

        // Resaltar la posición actual sin sobrescribir colores existentes
        if (posicion < textoLeer.getText().length()) {
            Style estiloResaltado = textoLeer.addStyle("highlight", null);

            // Mantener el color actual (verde, rojo o negro)
            AttributeSet atributosActuales = sd.getCharacterElement(posicion).getAttributes();
            Color colorActual = StyleConstants.getForeground(atributosActuales);

            StyleConstants.setBackground(estiloResaltado, Color.LIGHT_GRAY); // Fondo gris oscuro
            StyleConstants.setForeground(estiloResaltado, colorActual);    // Mantener color del texto
            sd.setCharacterAttributes(posicion, 1, estiloResaltado, true);
        }
    }

    // Combinar resaltado de colores y posición actual
    public void actualizarVista(String textoReferencia) {
        actualizarColores(textoReferencia); // Aplicar colores
        resaltarPosicion(textoEscribir.getText().length()); // Resaltar posición actual
    }
}
