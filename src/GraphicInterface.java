import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GraphicInterface extends JFrame
{
	
	private JTextField text_field = new JTextField("Input an expression");
	private JButton btn = new JButton("Розрахувати");
	private JTextField text_result = new JTextField();
	
	public class Button implements ActionListener
	{	
		public void actionPerformed(ActionEvent E)
		{
			Compute com = new Compute();
			double result;
			String str = new String();
			str = text_field.getText();
			result = com.calculate(str);
			text_result.setText(Double.toString(result)); 
		}
	}
	
	public GraphicInterface(String title)
	{
		super(title);
		createGUI();
	}
	
	public void createGUI()
	{
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		btn.setBounds(5, 30, 200, 20);
		text_field.setBounds(5, 5, 400, 20);
		text_result.setBounds(5, 55, 200, 20);
		
		panel.add(text_result);
		panel.add(btn);
		panel.add(text_field);
		
		getContentPane().add(panel);
		
		setSize(500, 200);
		setVisible(true);
	}
	
	public void runGUI()
	{
		btn.addActionListener(new Button());
	}
}
