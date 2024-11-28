package Paneles;

import javax.swing.*;
import java.awt.Font;

public class Dificultad extends JPanel {
    
    private JRadioButton btnFacil;
    private JRadioButton btnDificil;
    private JButton btnDificultades;
    
    public Dificultad() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Modo de Juego");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel.setBounds(126, 39, 189, 57);
        add(lblNewLabel);
        
        btnFacil = new JRadioButton("Modo Fácil");
        btnFacil.setBounds(156, 112, 109, 23);
        add(btnFacil);
        
        btnDificil = new JRadioButton("Modo Difícil");
        btnDificil.setBounds(156, 168, 109, 23);
        add(btnDificil);
        
        // Agrupamos los radio buttons para que sean mutuamente excluyentes
        ButtonGroup group = new ButtonGroup();
        group.add(btnFacil);
        group.add(btnDificil);
        
        btnDificultades = new JButton("EMPEZAR!");
        btnDificultades.setBounds(136, 215, 136, 42);
        add(btnDificultades);
    }

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

