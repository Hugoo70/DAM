package traductor;

import javax.swing.JFrame;

public class ventanaTraductor extends JFrame {
	public ventanaTraductor() {
		setTitle("Ejemplos por clases");
		
		miPanel panelLienzo = new miPanel();
		add(panelLienzo);
	}
}
