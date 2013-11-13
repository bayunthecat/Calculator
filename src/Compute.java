import java.lang.Math;


public class Compute 
{
	int start;
	private static final int OPERATOR = 10;
	//private static final int NUMBER = 2;
	
	private static final int OP_BRACKET = 0;
	private static final int CL_BRACKET = 1;
	private static final int PLUS_MINUS = 2;
	private static final int MULT_DIV = 3;
	private static final int POWER = 4;
	private static final int SPACE = 5;
	private static final int NUM = 6;
	private static final int POINT = 7;
	private static final int ILLEGAL_SYMBOL = 8; 
	
	
	public int defineCorrectSymbol(String c)
	{
		char Array[] = c.toCharArray();
		for(int i = 0; i < c.length(); i++)
		{
			if(!c.isEmpty() && (int)Array[i] >= 48 && (int)Array[i] <= 57 || (int)Array[i] == 46)
			{
				if(i == (c.length()-1))
				{
					return NUM;
				}
			}
			else
			{
				break;
			}
		}
		switch(c)
		{
		case "+":
			return OPERATOR;
		case "-":
			return OPERATOR;
		case "*":
			return OPERATOR;
		case "/":
			return OPERATOR;
		case "^":
			return OPERATOR;
		case " ":
			return SPACE;
		case "(":
			return OP_BRACKET;
		case ")":
			return CL_BRACKET;
		default:
			return ILLEGAL_SYMBOL;
		}
	}//Function o define more general symbol class 
	
	public int getNextPosition(String str, int start)
	{
		int result = start;
		
		for(int i = start; i<str.length() ;i++)
		{
			if(defineSymbol(str.substring(i,i+1)) == NUM || defineSymbol(str.substring(i,i+1)) == POINT)
			{
				result++;
			}
			else
			{
				if(result == start)
				{
					result = result + 1;
				}
				
				break;
			}
		}
		return result;
	}
	
	public int defineSymbol(String c)
	{
		char Array[] = c.toCharArray();
		for(int i = 0; i < c.length(); i++)
		{
			if(!c.isEmpty() && (int)Array[i] >= 48 && (int)Array[i] <= 57 || (int)Array[i] == 46)
			{
				if(i == (c.length()-1))
				{
					return NUM;
				}
			}
			else
			{
				break;
			}
		}
		switch(c)
		{
		case "+":
			return PLUS_MINUS;
		case "-":
			return PLUS_MINUS;
		case "*":
			return MULT_DIV;
		case "/":
			return MULT_DIV;
		case "^":
			return POWER;
		case " ":
			return SPACE;
		case "(":
			return OP_BRACKET;
		case ")":
			return CL_BRACKET;
		case ".":
			return POINT;
		default:
			return ILLEGAL_SYMBOL;
		}
	}//Correct
	
	public boolean bracket(String str)
	{
		char[] symbol = new char[1];
		Stack<Character> st = new Stack<Character>();
		for(int i = 0; ; i++)
		{
			try
			{
				str.getChars(i, i+1, symbol, 0);
				if(symbol[0] != '(' && symbol[0] != ')')
				{
					continue;
				}
				if(symbol[0] == ')')
				{
					if(st.isEmpty())
					{
						System.out.println("No opening bracket before the closing one" + " on position " + (i+1));
						return false;
					}
					if(st.peek() == '(')
					{
						st.pop();
						continue;
					}
				}
				st.push(symbol[0]);
			}
			catch(StringIndexOutOfBoundsException e)
			{
				break;
			}
		}
		if(!st.isEmpty())
		{
			return false;
		}
		return true;
	}//Requires correction
	
	public void iterateChar()
	{
		for(char c = 0; c < 128; c++)
		{
			System.out.println("Value " + (int)c + " Symbol " + c);
		}
	}//Correct
	
	public boolean isCorrect(String str)
	{
		int pos;
		String symbol = new String();
		String previous_symbol = new String("");
		
		for(int i = 0; i < str.length(); i++)
		{
			pos = getNextPosition(str,i);
			symbol = str.substring(i, pos);
			
			switch(defineCorrectSymbol(symbol))
			{
			case OP_BRACKET:
				if(previous_symbol.equals("."))//You can't write like that if(symbol == "."). The result will be always false.
				{
					System.out.println("Point in front of the opening bracket " + (i+1) + " position");
					return false;
				}
				if(defineSymbol(previous_symbol) == NUM)
				{
					System.out.println("Missing the operator between the operand and the opening bracket " + (i+1) + " position");
					return false;
				}
			case CL_BRACKET:
				if(defineCorrectSymbol(previous_symbol) == OPERATOR)
				{
					System.out.println("Missing operand between the operator and the closing bracket " + (i+1) + " position");
					return false;
				}
				if(previous_symbol.equals("."))
				{
					System.out.println("Point in front of the closing bracket " + (i+1) + " position");
					return false;
				}
			case OPERATOR:
				if(defineCorrectSymbol(previous_symbol) == OPERATOR)
				{
					System.out.println("Illegal combination of operators " + (i+1) + " position");
					return false;
				}
				if(previous_symbol.equals("."))
				{
					System.out.println("Point in front of the operator " + (i+1) + " position");
					return false;
				}
				if(defineCorrectSymbol(previous_symbol) == OP_BRACKET)
				{
					System.out.println("No operand between the opening bracket and  " + (i+1) + " position");
					return false;
				}
			case SPACE:
				break;
			}
			
			if(symbol != " ")
			{
				previous_symbol = symbol;
			}
			i = pos - 1;
		}
		return true;
	}//Unfinished, do not forget to add redefinition of i in the end
	
	public String toReversePolishNotation(String str)
	{
		int pos;
		String symbol_next = new String();
		String symbol = new String();
		Stack<String> st = new Stack<String>();
		String space = new String(" ");
		String result = new String();
		for(int i = 0; i < str.length(); i++)
		{
			pos = getNextPosition(str,i);
			symbol = str.substring(i,pos);
			i = pos - 1;
			
			switch (defineSymbol(symbol))
			{
			case NUM:
				
				result = result + symbol + space;
				break;
				
			case PLUS_MINUS:
				
				if(st.isEmpty() || defineSymbol(st.peek()) < PLUS_MINUS)
				{
					st.push(symbol);
					break;
				}
				else
				{
					symbol_next = symbol;
					
					while(defineSymbol(st.peek()) >= PLUS_MINUS)
					{
						symbol = st.pop();
						result = result + symbol + space;
						if(st.isEmpty())
						{
							break;
						}
					}
					st.push(symbol_next);
					break;
				}
				
			case MULT_DIV:
				
				if(st.isEmpty() || defineSymbol(st.peek()) < MULT_DIV)
				{
					st.push(symbol);
					break;
				}
				else
				{
					symbol_next = symbol;
					
					while(defineSymbol(st.peek()) >= MULT_DIV)
					{
						symbol = st.pop();//Присваивая массивы друг - другу на самом дле переопределяются ссылки
						result = result + symbol + space;
						if(st.isEmpty())
						{
							break;
						}
					}
					st.push(symbol_next);
					
					break;
				}
				
			case POWER:
				
				if(st.isEmpty() || defineSymbol(st.peek()) < POWER)
				{
					st.push(symbol);
					break;
				}
				else
				{
					symbol_next = symbol;
					while(defineSymbol(st.peek()) >= POWER)
					{
						symbol = st.pop();
						result = result + symbol + space;
						if(st.isEmpty())
						{
							break;
						}
					}
					st.push(symbol_next);
					break;
				}
				
			case OP_BRACKET:
				
				st.push(symbol);
				break;
				
			case CL_BRACKET:
				
				while(defineSymbol(st.peek()) != OP_BRACKET)
				{
					symbol = st.pop();
					result = result + symbol + space;
				}
				symbol = st.pop();
				
				break;
				
			case SPACE:
				
				break;
			default:
				break;
			}
		}
		
		while(!st.isEmpty())
		{
			symbol = st.pop();
			result = result + symbol + space;
		}
		
		return result;
	}
	public double calculate(String str)
	{
		//int pointer = 0;
		double number;
		double operand = 0;
		
		int pos;
		
		String reverse_polish_notation;
		reverse_polish_notation = toReversePolishNotation(str);
		Stack<Double> st = new Stack<Double>();
		
		for(int i = 0; i < reverse_polish_notation.length(); i++)
		{
			
			pos = getNextPosition(reverse_polish_notation,i);
			if(defineSymbol(reverse_polish_notation.substring(i, pos)) != SPACE && defineSymbol(reverse_polish_notation.substring(i, pos)) == NUM)
			{
				number = Double.parseDouble(reverse_polish_notation.substring(i, pos));
				st.push(number);
			}
			else
			{
				switch(reverse_polish_notation.substring(i, pos))
				{
				case "+":
					operand = st.pop();
					number = st.pop() + operand;
					st.push(number);
					break;
				case "-":
					operand = st.pop();
					number = st.pop() - operand;
					st.push(number);
					break;
				case "*":
					number = st.pop() * st.pop();
					st.push(number);
					break;
				case "/":
					operand = st.pop();
					number = st.pop() / operand;
					st.push(number);
					break;
				case "^":
					double power = st.pop();
					number = Math.pow(st.pop(), power);
					st.push(number);
					break;
				default:
					break;
				}
			}
			i = pos -1;
		}
		return st.pop();
	}
	

}
