package traductor;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class miPanel extends JPanel{
	private JTextField textField;
	private JTextField textField_1;
	public miPanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TRADUCTOR");
		lblNewLabel.setBounds(179, 33, 77, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Introducir palabra");
		lblNewLabel_1.setBounds(51, 108, 103, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Palabra traducida");
		lblNewLabel_2.setBounds(51, 170, 103, 14);
		add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(179, 105, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setBounds(179, 167, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("TRADUCIR");
		btnNewButton.setBounds(176, 224, 89, 23);
		add(btnNewButton);
	}
	
}
