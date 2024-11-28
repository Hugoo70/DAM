package Paneles;

import javax.swing.*;
import java.awt.Font;

public class Dificultad extends JPanel {

    // Declaración de los componentes
    private JRadioButton btnFacil;  // Botón para el modo fácil
    private JRadioButton btnDificil;  // Botón para el modo difícil
    private JButton btnDificultades;  // Botón para iniciar el juego con la dificultad seleccionada

    public Dificultad() {
        setLayout(null);  // Usamos layout nulo para poder colocar los componentes manualmente

        // Etiqueta para el título "Modo de Juego"
        JLabel lblNewLabel = new JLabel("Modo de Juego");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));  // Establecemos la fuente y tamaño
        lblNewLabel.setBounds(126, 39, 189, 57);  // Establecemos la posición y tamaño de la etiqueta
        add(lblNewLabel);  // Añadimos la etiqueta al panel

        // RadioButton para el modo fácil
        btnFacil = new JRadioButton("Modo Fácil");
        btnFacil.setBounds(156, 112, 109, 23);  // Establecemos la posición y tamaño del botón
        add(btnFacil);  // Añadimos el botón de modo fácil al panel

        // RadioButton para el modo difícil
        btnDificil = new JRadioButton("Modo Difícil");
        btnDificil.setBounds(156, 168, 109, 23);  // Establecemos la posición y tamaño del botón
        add(btnDificil);  // Añadimos el botón de modo difícil al panel

        // Agrupamos los botones de opción (JRadioButton) para que solo uno pueda ser seleccionado a la vez
        ButtonGroup group = new ButtonGroup();
        group.add(btnFacil);  // Añadimos el botón de modo fácil al grupo
        group.add(btnDificil);  // Añadimos el botón de modo difícil al grupo

        // Botón "EMPEZAR!" para iniciar el juego con la dificultad seleccionada
        btnDificultades = new JButton("EMPEZAR!");
        btnDificultades.setBounds(136, 215, 136, 42);  // Establecemos la posición y tamaño del botón
        add(btnDificultades);  // Añadimos el botón al panel
    }

    // Métodos getter y setter para acceder a los botones y modificar sus valores

    public JRadioButton getBtnFacil() {
        return btnFacil;
    }

    public void setBtnFacil(JRadioButton btnFacil) {
        this.btnFacil = btnFacil;
    }

    public JRadioButton getBtnDificil() {
        return btnDificil;
    }

    public void setBtnDificil(JRadioButton btnDificil) {
        this.btnDificil = btnDificil;
    }

    public JButton getBtnDificultades() {
        return btnDificultades;
    }

    public void setBtnDificultades(JButton btnDificultades) {
        this.btnDificultades = btnDificultades;
    }
}
