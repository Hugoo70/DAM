package PanelPrincipal;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Teclado extends JPanel {
    private Map<String, JButton> teclas; // Mapa para almacenar las teclas con su etiqueta como clave

    // Constructor del panel Teclado
    public Teclado() {
        setLayout(new GridLayout(3, 10, 10, 10)); // Diseño de 3 filas y 10 columnas para el teclado
        teclas = new HashMap<>(); // Mapa para almacenar las teclas dinámicamente

        // Filas del teclado (QWERTY, ASDFG, ZXCV)
        crearFila("QWERTYUIOP");
        crearFila("ASDFGHJKLÑ");
        crearFila("ZXCVBNM ─");
    }

    // Método para crear una fila de teclas
    private void crearFila(String caracteres) {
        // Recorre cada caracter de la fila y lo convierte en un botón
        for (char c : caracteres.toCharArray()) {
            String etiqueta = String.valueOf(c);  // Convertir el caracter en un String
            JButton key = new JButton(etiqueta);  // Crear el botón para la tecla
            
            // Diseño estético para los botones
            key.setFont(new Font("Tahoma", Font.BOLD, 18));  // Establecer la fuente en negrita y tamaño 18
            key.setFocusable(false);  // Desactivar el foco para que no resalte al hacer clic
            key.setBackground(new Color(240, 248, 255));  // Color de fondo azul claro
            key.setForeground(new Color(60, 63, 65));  // Color de texto gris oscuro
            key.setBorder(BorderFactory.createLineBorder(new Color(173, 216, 230), 2));  // Borde azul claro
            key.setOpaque(true);  // Establecer opacidad
            key.setFocusPainted(false);  // Eliminar el borde de enfoque

            // Agregar efectos de interacción (cuando se pasa el mouse o se presiona la tecla)
            key.getModel().addChangeListener(e -> {
                // Cambiar el color de fondo al presionar la tecla
                if (key.getModel().isPressed()) {
                    key.setBackground(new Color(173, 216, 230)); // Azul claro cuando se presiona
                } else if (key.getModel().isRollover()) {
                    key.setBackground(new Color(224, 255, 255)); // Azul más claro cuando el mouse pasa por encima
                } else {
                    key.setBackground(new Color(240, 248, 255)); // Vuelve al azul muy claro cuando no se está presionando ni pasando el mouse
                }
            });

            // Almacenar el botón de la tecla en el mapa con su etiqueta como clave
            teclas.put(etiqueta, key);
            add(key);  // Añadir el botón al panel
        }
    }

    // Método para resaltar la tecla correspondiente al presionar
    public void resaltarTecla(String etiqueta) {
        JButton tecla = teclas.get(etiqueta.toUpperCase());  // Obtiene el botón de la tecla a resaltar
        if (tecla != null) {
            tecla.setBackground(new Color(100, 149, 237));  // Cambia el fondo a un azul más intenso
        }
    }

    // Método para restaurar el color de la tecla cuando se suelta
    public void liberarTecla(String etiqueta) {
        JButton tecla = teclas.get(etiqueta.toUpperCase());  // Obtiene el botón de la tecla
        if (tecla != null) {
            tecla.setBackground(new Color(240, 248, 255));  // Restaura el fondo azul claro
        }
    }

    // Método para reiniciar el estado de todas las teclas
    public void reiniciarTeclas() {
        for (JButton tecla : teclas.values()) {
            tecla.setBackground(new Color(240, 248, 255));  // Restaura el fondo azul muy claro de todas las teclas
        }
    }
}
