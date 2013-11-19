import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GraphicInterface extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JTextField text_field = new JTextField();
	private JButton btn = new JButton("Calculate");
	private JTextField text_result = new JTextField();
	
	
	public class Key implements KeyListener
	{

		
		public void keyPressed(KeyEvent E) 
		{
			if(E.getKeyCode() == KeyEvent.VK_ENTER)
			{
				Compute com = new Compute();
				double result;
				result = com.calculate(text_field.getText());
				text_result.setText(Double.toString(result));
			}
			if(E.getKeyCode() == KeyEvent.VK_DELETE)
			{
				text_field.setText("");
			}
		}

		public void keyReleased(KeyEvent E) 
		{
			
		}

		public void keyTyped(KeyEvent E) 
		{
						
		}
		
	}
	
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
	
	public GraphicInterface()
	{
		
	}
	
	public GraphicInterface(String title)
	{
		super(title);
		createGUI();
	}
	
	public void createGUI()
	{
		Color bg = new Color(108,0,108);
		Font f = new Font("Arial",Font.ITALIC,25);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		btn.setBounds(5, 55, 200, 35);
		btn.setFont(f);
		
		
		
		text_field.setBounds(5, 10, 900, 35);
		text_field.setFont(f);
		
		text_result.setBounds(5, 100, 900, 35);
		text_result.setEditable(false);
		text_result.setFont(f);
		
		panel.setBackground(bg);
		panel.add(text_result);
		panel.add(btn);
		panel.add(text_field);
		
		getContentPane().add(panel);
		
		setSize(1000, 200);
		setVisible(true);
	}
	
	public void runGUI()
	{
		btn.addActionListener(new Button());
		text_field.addKeyListener(new Key());
	}
	
	public void printErrorMessage(int pos, String error_text)
	{
		text_result.setText(error_text);
		text_field.setCaretPosition(pos);
	}
}
