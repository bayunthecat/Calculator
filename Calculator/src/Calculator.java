import java.util.*;
public class Calculator 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		String str = new String();
		//String symbol = new String();
		//char[] c = {'a',' ',' ',' ',' ',' ',' '};
		str = sc.nextLine();
		//Stack<Character> st = new Stack<Character>();
		
		Compute com = new Compute();
		int pos;
		
		System.out.println(com.toReversePolishNotation(str));
		System.out.println(com.calculate(str));
		/*for(int i = 0; i < str.length(); i++)
		{
			pos = com.getNextPosition(str, i);
			System.out.println(str.substring(i,pos));
			i = pos-1;
		}*/
		//com.iterateChar();
		sc.close();
	}
}
