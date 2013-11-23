import java.lang.Math;


public class Compute 
{
	public static final int MAX = 15;
	public static final int MIN = 14;
	public static final int SQRT = 13;
	public static final int SUM = 12;
	public static final int LETTER = 11;
	//private static final int OPERATOR = 10;
	
	public static final int OP_BRACKET = 0;
	public static final int CL_BRACKET = 1;
	public static final int PLUS_MINUS = 2;
	public static final int MULT_DIV = 3;
	public static final int POWER = 4;
	public static final int SPACE = 5;
	public static final int NUM = 6;
	public static final int POINT = 7;
	public static final int ILLEGAL_SYMBOL = 8; 
	
	
	
	
	public int defineFunction(String str)
	{
		String symbol = new String();
		String result = new String();
		
		for(int i = 0; i < str.length(); i++)
		{
			symbol = str.substring(i, i+1);
			if(defineSymbol(symbol) == LETTER)
			{
				result = result + symbol;
			}
			else
			{
				break;
			}
		}
		switch(result)
		{
		case "sum":
			
			return SUM;
			
		case "sqrt":
			
			return SQRT;
			
		case "min":
			
			return MIN;
			
		case "max":
			
			return MAX;
		}
		return ILLEGAL_SYMBOL;
	}//new
	
	public int getNextPosition(String str, int start)
	{
		int result = start;
		String symbol = new String();
		String previous_symbol = new String();
		
		
		for(int i = start; i < str.length(); i++)
		{
			symbol = str.substring(i, i+1);
			switch(defineSymbol(symbol))
			{
			case NUM:
				
				if(defineSymbol(previous_symbol) == LETTER)
				{
					return result;
				}
				result++;
				break;
				
			case POINT:
				
				if(defineSymbol(previous_symbol) == LETTER)
				{
					return result;
				}
				result++;
				break;
				
			case LETTER:
				
				if(defineSymbol(previous_symbol) == NUM || defineSymbol(previous_symbol) == POINT)
				{
					return result;
				}
				result++;
				break;
				
			case OP_BRACKET:
				
				if(defineSymbol(previous_symbol) == LETTER)
				{
					result = getFunctionPosition(str, result+1);
					return result;
				}
				
			case SPACE://Add support of "func (args...)" such note
				
				/*if(defineSymbol(previous_symbol) == LETTER)
				{
					
				}*/
				
			default:
				
				if(result == start)//Check
				{
					return result+1;
				}
				return result;
			}
			
			previous_symbol = symbol;
		}
		return result;
	}
	
	public int getFunctionPosition(String str, int start)
	{
		int bracket = 1;
		int result = start;
		String symbol = new String();
		
		for(int i = start; i < str.length(); i++)
		{
			symbol = str.substring(i, i+1);
			if(bracket == 0)
			{
				break;
			}
			if(symbol.equals("("))
			{
				bracket++;
			}
			if(symbol.equals(")"))
			{
				bracket--;
			}
			result++;
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
		for(int i = 0; i < c.length(); i++)
		{
			if(!c.isEmpty() && (int)Array[i] >= 97 && (int)Array[i] <= 122)
			{
				if(i == (c.length()-1))
				{
					return LETTER;
				}
			}
			else
			{
				break;
			}
		}//new
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
	
	public void iterateChar()
	{
		for(char c = 0; c < 128; c++)
		{
			System.out.println("Value " + (int)c + " Symbol " + c);
		}
	}//Correct
	
//Unfinished, do not forget to add redefinition of i in the end, incorrect
	
	public Stack<String> toReversePolishNotation(String str)
	{
		int operands = 0;
		int operators = 0;
		int pos;
		String symbol_next = new String();
		String symbol = new String();
		Stack<String> st = new Stack<String>();
		Stack<String> st_result = new Stack<String>();
		
		for(int i = 0; i < str.length(); i++)
		{
			pos = getNextPosition(str,i);
			symbol = str.substring(i,pos);
			i = pos - 1;
			
			switch (defineSymbol(symbol))
			{
			case NUM:
				
				st_result.push(symbol);//new

				operands++;
				break;
				
			case PLUS_MINUS:
				
				operators++;
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
						st_result.push(symbol);//new
						
						if(st.isEmpty())
						{
							break;
						}
					}
					st.push(symbol_next);
					break;
				}
				
			case MULT_DIV:
				
				operators++;
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
						st_result.push(symbol);//new
						
						if(st.isEmpty())
						{
							break;
						}
					}
					st.push(symbol_next);
					
					break;
				}
				
			case POWER:
				
				operators++;
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
						st_result.push(symbol);
						
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
				
				if(st.peek().equals("-") && operators >= operands)
				{
					symbol = st_result.pop();
					st_result.push(Double.toString(-Double.parseDouble(symbol)));
					
					
					operators--;
					st.pop();
					symbol = st.pop();
					break;
				}//New segment
				while(defineSymbol(st.peek()) != OP_BRACKET)
				{
					symbol = st.pop();
					st_result.push(symbol);
					
				}
				symbol = st.pop();
				
				break;
				
			case SPACE:
				
				break;
				
			default:
				break;
			}
			
			switch(defineFunction(symbol))
			{
			case SUM:
				st_result.push(Double.toString(calculateFunction(symbol)));
				break;
			case SQRT:
				st_result.push(Double.toString(calculateFunction(symbol)));
				break;
			case MIN:
				st_result.push(Double.toString(calculateFunction(symbol)));
				break;
			case MAX:
				st_result.push(Double.toString(calculateFunction(symbol)));
				break;
			}
			
		}
		
		while(!st.isEmpty())
		{
			symbol = st.pop();
			st_result.push(symbol);
		}
		
		while(!st_result.isEmpty())
		{
			st.push(st_result.pop());
		}
		

		return st;
	}
	
	public String getRevPolNot(Stack<String> st)
	{
		String result = new String();
		
		while(!st.isEmpty())
		{
			result = result + st.pop() + " "; 
		}
		return result;
	}
	
	public double calculate(String str)
	{
		double number;
		double operand = 0;
		
		Stack<String> st_result = toReversePolishNotation(str); 
		
		Stack<Double> st = new Stack<Double>();
		
		while(!st_result.isEmpty())
		{
			try
			{
				number = Double.parseDouble(st_result.peek());
				st.push(number);
				st_result.pop();
				continue;
			}
			catch(NumberFormatException E)
			{
				switch(st_result.pop())
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
		}
		return st.pop();
	}
	
	public double calculateFunction(String str)
	{
		int pos;
		double result = 0;
		String symbol = new String();
		
		for(int i = 0; i< str.length(); i++)
		{
			pos = getNextPosition(str, i);
			symbol = str.substring(i, pos);
			i = pos - 1;
			
			switch(defineFunction(symbol))
			{
			case SUM:
				
				for(int j = 0; j < str.length(); j++)
				{
					if(defineSymbol(str.substring(j, j+1)) == LETTER)
					{
						continue;
					}
					pos = getArgument(str,j+1);
					symbol = str.substring(j+1, pos);
					if(symbol.equals(""))
					{
						break;
					}
					result = result + calculate(symbol);
					j = pos - 1;
				}
				break;
				
			case SQRT:
				
				for(int j = 0; j < str.length(); j++)
				{
					if(defineSymbol(str.substring(j, j+1)) == LETTER)
					{
						continue;
					}
					pos = getArgument(str,j+1);
					symbol = str.substring(j+1, pos);
					result = Math.pow(calculate(symbol), 0.5);
					break;
				}
				break;
				
			case MIN:
				
				double min = Double.MAX_VALUE;
				for(int j = 0; j < str.length(); j++)
				{
					if(defineSymbol(str.substring(j, j+1)) == LETTER)
					{
						continue;
					}
					pos = getArgument(str, j+1);
					symbol = str.substring(j+1, pos);
					j = pos - 1;
					if(symbol.equals(""))
					{
						break;
					}
					result = calculate(symbol);
					
					if(result < min)
					{
						min = result;
					}
				}
				
				result = min;
				
				break;
			case MAX:
				
				double max = Double.MIN_VALUE;
				for(int j = 0; j < str.length(); j++)
				{
					if(defineSymbol(str.substring(j, j+1)) == LETTER)
					{
						continue;
					}
					pos = getArgument(str, j+1);
					symbol = str.substring(j+1, pos);
					j = pos - 1;
					if(symbol.equals(""))
					{
						break;
					}
					result = calculate(symbol);
					
					if(result > max)
					{
						max = result;
					}
				}
				
				result = max;
				break;
			}
		}
		return result;
	}
	
	public int getArgument(String str, int start)
	{
		int bracket = 1;
		int pos;
		int result = start;
		String symbol = new String();
		
		for(int i = start; i < str.length(); i++)
		{
			symbol = str.substring(i, i+1);
			
			pos = getNextPosition(str, i);
			result = pos;
			symbol = str.substring(i, i+1);
			i = pos - 1;
			if(symbol.equals("("))
			{
				bracket++;
			}
			if(symbol.equals(")"))
			{
				bracket--;
			}
			if(symbol.equals(",") || (symbol.equals(")") && bracket == 0))
			{
				result--;
				break;
			}
		}
		return result;
	}
}
