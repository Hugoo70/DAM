package PanelPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Teclado extends JPanel {
    private JButton[] teclas;
    private String[] caracteres = {
            "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
            "A", "S", "D", "F", "G", "H", "J", "K", "L", "Ñ",
            "Z", "X", "C", "V", "B", "N", "M", "SPACE"
    };

    public Teclado() {
        setLayout(new GridLayout(3, 10, 5, 5)); // Tres filas
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

    // Resaltar la tecla correspondiente
    public void resaltarTecla(String etiqueta) {
        for (JButton tecla : teclas) {
            if (tecla.getText().equalsIgnoreCase(etiqueta)) {
                tecla.setBackground(Color.BLUE);
            } else {
                tecla.setBackground(Color.LIGHT_GRAY);
            }
        }
    }

    // Reiniciar colores
    public void resetearTeclas() {
        for (JButton tecla : teclas) {
            tecla.setBackground(Color.LIGHT_GRAY);
        }
    }
}
