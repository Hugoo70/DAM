package PanelPrincipal;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Teclado extends JPanel {
    private Map<String, JButton> teclas; // Map para almacenar las teclas con su etiqueta como clave

    public Teclado() {
        setLayout(new GridLayout(3, 10, 10, 10)); // Diseño de teclado (3 filas, 10 columnas)
        teclas = new HashMap<>(); // Almacenar teclas dinámicamente

        // Filas del teclado
        crearFila("QWERTYUIOP");
        crearFila("ASDFGHJKLÑ");
        crearFila("ZXCVBNM ─");
    }

    private void crearFila(String caracteres) {
        for (char c : caracteres.toCharArray()) {
            String etiqueta = String.valueOf(c);
            JButton key = new JButton(etiqueta);
            
            // Diseño estético para los botones
            key.setFont(new Font("Tahoma", Font.BOLD, 18));
            key.setFocusable(false);
            key.setBackground(new Color(240, 248, 255)); // Azul muy claro
            key.setForeground(new Color(60, 63, 65)); // Gris oscuro para el texto
            key.setBorder(BorderFactory.createLineBorder(new Color(173, 216, 230), 2)); // Borde azul claro
            key.setOpaque(true);
            key.setFocusPainted(false);

            // Efectos de interacción
            key.getModel().addChangeListener(e -> {
                if (key.getModel().isPressed()) {
                    key.setBackground(new Color(173, 216, 230)); // Azul claro al presionar
                } else if (key.getModel().isRollover()) {
                    key.setBackground(new Color(224, 255, 255)); // Azul más claro al pasar el mouse
                } else {
                    key.setBackground(new Color(240, 248, 255)); // Volver al azul muy claro
                }
            });

            teclas.put(etiqueta, key); // Guardar la tecla en el Map
            add(key); // Agregar al panel
        }
    }

    // Resaltar la tecla correspondiente al presionar
    public void resaltarTecla(String etiqueta) {
        JButton tecla = teclas.get(etiqueta.toUpperCase());
        if (tecla != null) {
            tecla.setBackground(new Color(100, 149, 237)); // Azul más intenso para resaltar
        }
    }

    // Reiniciar colores al soltar
    public void liberarTecla(String etiqueta) {
        JButton tecla = teclas.get(etiqueta.toUpperCase());
        if (tecla != null) {
            tecla.setBackground(new Color(240, 248, 255)); // Azul muy claro
        }
    }

    // Reiniciar el estado de todas las teclas
    public void reiniciarTeclas() {
        for (JButton tecla : teclas.values()) {
            tecla.setBackground(new Color(240, 248, 255)); // Azul muy claro
        }
    }
}
