package PanelPrincipal;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import TXT.Estadisticas;
import TXT.Usuario;

public class Game extends JPanel{
	private int dif;
	private ArrayList<String> textos;
	private ArrayList<Usuario> listaUsuarios;
	private Usuario usuarioLogin;
	
	private Dimension PantallaCompleta;
	
	private static int FACIL = 0;
	private static int DIFICL = 0;


	
	private JTextArea textArea;
	private Texto texto;
	
	public Game(int dif, ArrayList<String> textos, ArrayList<Estadisticas> estadisticas, ArrayList<Usuario> listaUsuarios, Usuario usuarioLogin) {
		
		setLayout(null);

		PantallaCompleta = Toolkit. getDefaultToolkit(). getScreenSize();
		setSize(PantallaCompleta);
		/*
		panelEstadisticas = new PanelEstadisticas();
		panelEstadisticas.setBackground(new Color(255, 255, 255));
		panelEstadisticas.setBounds(1590, 56, 241, 539);
		add(panelEstadisticas);

		panelTeclado = new PanelTeclado(mapBoton);
		panelTeclado.setBounds(254, 629, 1258, 375);
		add(panelTeclado);*/

		texto = new Texto();
		texto.setSize(1446, 238);
		texto.setLocation(115, 56);
		add(texto);

		textArea = new JTextArea();
		textArea.setBounds(115, 341, 1446, 254);
		textArea.setFocusable(true);
	}
	/*public void imprimirTextoDificultad() {

		//Si la lección es facil
		if(dif == 0) {
			Texto.getTexto().getText().setText(textos.get(FACIL));
			comprobarTexto(MODO_FACIL,TEMPORIZADOR_FACIL, true, 4);
			contadorDeInicio(MODO_FACIL, TEMPORIZADOR_FACIL);
		}

		//Si la lección es dificil
		if(dif == 1) {
			getPanelTexto().getTextArea().setText(textos.get(MODO_DIFICIL));
			comprobarTexto(MODO_DIFICIL, TEMPORIZADOR_DIFICIL, false, 2);
			contadorDeInicio(MODO_DIFICIL, TEMPORIZADOR_DIFICIL);
		}
	}*/

}
