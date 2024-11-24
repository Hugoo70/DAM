package PanelPrincipal;

import javax.swing.*;
import java.awt.*;

public class Teclado extends JPanel {
    private JButton[] teclas;
    private String[] caracteres = {
            "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
            "A", "S", "D", "F", "G", "H", "J", "K", "L", "Ñ",
            "Z", "X", "C", "V", "B", "N", "M", "SPACE"
    };

    public Teclado() {
        // Configurar un diseño de cuadrícula adaptable para las teclas
        setLayout(new GridLayout(3, 10, 10, 10)); // 3 filas, 10 columnas, con margen de 10 píxeles

        teclas = new JButton[caracteres.length];

        for (int i = 0; i < caracteres.length; i++) {
            JButton key = new JButton(caracteres[i]);
            key.setFont(new Font("Tahoma", Font.BOLD, 18));
            key.setFocusable(false);
            key.setBackground(Color.LIGHT_GRAY);

            teclas[i] = key;
            add(key);
        }
    }

    // Resaltar la tecla correspondiente al presionar
    public void resaltarTecla(String etiqueta) {
        for (JButton tecla : teclas) {
            if (tecla.getText().equalsIgnoreCase(etiqueta) || 
                (etiqueta.equals(" ") && tecla.getText().equalsIgnoreCase("SPACE"))) {
                tecla.setBackground(Color.BLUE);
            }
        }
    }

    // Reiniciar colores al soltar
    public void liberarTecla(String etiqueta) {
        for (JButton tecla : teclas) {
            if (tecla.getText().equalsIgnoreCase(etiqueta) || 
                (etiqueta.equals(" ") && tecla.getText().equalsIgnoreCase("SPACE"))) {
                tecla.setBackground(Color.LIGHT_GRAY);
            }
        }
    }
}
