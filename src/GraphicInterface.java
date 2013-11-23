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
				
				if(isCorrect(text_field.getText()) != text_field.getText().length())
				{
					text_field.setCaretPosition(isCorrect(text_field.getText()));
					return;
				}
				try
				{
					result = com.calculate(text_field.getText());
					text_result.setText(Double.toString(result));
				}
				catch(Exception e)
				{
					text_result.setText("Unexpected error please stand by");
				}
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
			
			if(isCorrect(text_field.getText()) != text_field.getText().length())
			{
				text_field.setCaretPosition(isCorrect(text_field.getText()));
				text_field.requestFocus();
				return;
			}
			try
			{
				result = com.calculate(str);
				text_result.setText(Double.toString(result));
			}
			catch(Exception e)
			{
				text_result.setText("Unexpected error please stand by");
			}
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
	
	public int isCorrect(String str)
	{
		int brackets = 0;
		int pos;
		String symbol = new String();
		Stack<String> st = new Stack<String>();
		Compute com = new Compute();
		
		for(int i = 0; i < str.length(); i++)
		{
			pos = com.getNextPosition(str,i);
			symbol = str.substring(i, pos);
			i = pos - 1;
			
			
			switch(com.defineSymbol(symbol))
			{
			case Compute.MULT_DIV:
				if(!st.isEmpty() && com.defineSymbol(st.peek()) == Compute.OP_BRACKET)
				{
					text_result.setText("Missing operand on position " + i);
					return i;
				}
				if(st.isEmpty())
				{
					text_result.setText("Expression cannot start this way");
					return i;
				}
				break;
			case Compute.PLUS_MINUS:
				if(symbol.equals("-"))
				{
					break;
				}
				if(!st.isEmpty() && com.defineSymbol(st.peek()) == Compute.OP_BRACKET)
				{
					text_result.setText("Missing operand on position " + i);
					return i;
				}
				if(st.isEmpty())
				{
					text_result.setText("Expression cannot start this way");
					return i;
				}
				break;
			case Compute.POWER:
				if(!st.isEmpty() && com.defineSymbol(st.peek()) == Compute.OP_BRACKET)
				{
					text_result.setText("Missing operand on position " + i);
					return i;
				}
				if(st.isEmpty())
				{
					text_result.setText("Expression cannot start this way");
					return i;
				}
				break;
			case Compute.NUM:
				if(!st.isEmpty() && com.defineSymbol(st.peek()) == Compute.CL_BRACKET)
				{
					text_result.setText("Missing operator on position " + i);
					return i;
				}
				break;
			case Compute.ILLEGAL_SYMBOL:
				if(com.defineFunction(symbol) != Compute.ILLEGAL_SYMBOL)
				{
					break;
				}
				else
				{
					text_result.setText("Illegal symbol on position " + i);
				}
				return i;
			case Compute.OP_BRACKET:
				brackets++;
				if(!st.isEmpty() && com.defineSymbol(st.peek()) == Compute.NUM)
				{
					text_result.setText("Missing operator on position " + i);
					return i;
				}
				if(!st.isEmpty() && com.defineSymbol(st.peek()) == Compute.CL_BRACKET)
				{
					text_result.setText("Missing operator on position " + i);
					return i;
				}
				break;
			case Compute.CL_BRACKET:
				brackets--;
				if(brackets < 0)
				{
					text_result.setText("Missing " + Math.abs(brackets) + " opening bracket in expression");
					return i;
				}
				if(!st.isEmpty() && (com.defineSymbol(st.peek()) == Compute.PLUS_MINUS || com.defineSymbol(st.peek()) == Compute.MULT_DIV || com.defineSymbol(st.peek()) == Compute.POWER))
				{
					text_result.setText("Missing operand on position " + i);
					return i;
				}
				break;
			case Compute.LETTER:
				text_result.setText("Illegal symbol on position " + i);
				return i;
			case Compute.SPACE:
				break;
			}
			if(!symbol.equals(" "))
			{
				st.push(symbol);
			}
		}
		if(brackets != 0)
		{
			text_result.setText("Missing " + brackets + " closing brackets");
			return str.length() - 1;
		}
		return str.length();
	}// Requires minor correction, fix SPACE problem
}
