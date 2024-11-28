package PanelPrincipal;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.text.*;

public class Texto {
    private JTextPane textoLeer;  // Componente para mostrar el texto de referencia
    private JTextArea textoEscribir;  // Componente para que el usuario escriba

    // Constructor de la clase Texto
    public Texto() {
        // Configuración del JTextPane (Texto de referencia)
        textoLeer = new JTextPane();
        textoLeer.setFont(new Font("Tahoma", Font.PLAIN, 18));  // Establece la fuente y tamaño del texto
        textoLeer.setEditable(false);  // Deshabilita la edición del texto de referencia
        textoLeer.setHighlighter(null);  // Deshabilita la selección de texto
        textoLeer.setFocusable(false);  // Deshabilita el enfoque en este componente

        // Configuración del JTextArea (Texto del usuario)
        textoEscribir = new JTextArea();
        textoEscribir.setFont(new Font("Tahoma", Font.PLAIN, 18));  // Establece la fuente y tamaño del texto
        textoEscribir.setLineWrap(true);  // Habilita el ajuste de línea automático
        textoEscribir.setWrapStyleWord(true);  // Ajusta el texto palabra por palabra
    }

    // Métodos para obtener los componentes
    public JTextPane getTextoLeer() {
        return textoLeer;  // Devuelve el componente que muestra el texto de referencia
    }

    public JTextArea getTextoEscribir() {
        return textoEscribir;  // Devuelve el componente donde el usuario escribe
    }

    // Método para establecer el texto de referencia que el usuario debe escribir
    public void setTextoReferencia(String texto) {
        textoLeer.setText(texto);  // Establece el texto en el JTextPane
    }

    // Método para actualizar los colores del texto de referencia basado en la entrada del usuario
    public void actualizarColores(String textoReferencia) {
        String textoUsuario = textoEscribir.getText();  // Obtiene el texto que el usuario ha escrito
        StyledDocument sd = textoLeer.getStyledDocument();  // Obtiene el documento estilizado del JTextPane

        // Restablecer colores y fondo a los valores predeterminados
        Style estiloDefecto = textoLeer.addStyle("default", null);
        StyleConstants.setForeground(estiloDefecto, Color.BLACK);  // Color de texto negro
        StyleConstants.setBackground(estiloDefecto, Color.WHITE);  // Fondo blanco
        sd.setCharacterAttributes(0, textoReferencia.length(), estiloDefecto, true);  // Aplica el estilo predeterminado

        // Resaltar caracteres correctos e incorrectos
        for (int i = 0; i < textoUsuario.length(); i++) {
            Style estilo = textoLeer.addStyle("color", null);
            if (i < textoReferencia.length() && textoUsuario.charAt(i) == textoReferencia.charAt(i)) {
                StyleConstants.setForeground(estilo, Color.GREEN);  // Si la letra es correcta, la marca en verde
            } else {
                StyleConstants.setForeground(estilo, Color.RED);  // Si es incorrecta, la marca en rojo
            }
            sd.setCharacterAttributes(i, 1, estilo, true);  // Aplica el color a cada caracter
        }
    }

    // Método para resaltar la posición actual en el texto de referencia con fondo gris oscuro
    public void resaltarPosicion(int posicion) {
        StyledDocument sd = textoLeer.getStyledDocument();  // Obtiene el documento estilizado

        // Resaltar la posición actual sin sobrescribir colores existentes
        if (posicion < textoLeer.getText().length()) {
            Style estiloResaltado = textoLeer.addStyle("highlight", null);

            // Mantener el color actual (verde, rojo o negro) del texto
            AttributeSet atributosActuales = sd.getCharacterElement(posicion).getAttributes();
            Color colorActual = StyleConstants.getForeground(atributosActuales);

            // Establecer el fondo gris oscuro para resaltar la posición
            StyleConstants.setBackground(estiloResaltado, Color.LIGHT_GRAY);
            // Mantener el color actual del texto
            StyleConstants.setForeground(estiloResaltado, colorActual);
            sd.setCharacterAttributes(posicion, 1, estiloResaltado, true);  // Aplica el estilo de resaltado en la posición
        }
    }

    // Método para combinar el resaltado de colores y la posición actual en el texto
    public void actualizarVista(String textoReferencia) {
        actualizarColores(textoReferencia);  // Aplica el color a las letras correctas e incorrectas
        resaltarPosicion(textoEscribir.getText().length());  // Resalta la posición actual donde el usuario escribe
    }
}
