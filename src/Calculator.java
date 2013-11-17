import java.util.*;

public class Calculator
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		String str = new String();
		String symbol = new String();
		
		//str = sc.nextLine();
		
		Compute com = new Compute();
		
		int pos;
		
		
		GraphicInterface gi = new GraphicInterface("Calculator");
		gi.runGUI();
		
		//System.out.println(com.calculateFunction(str));
		
		/*for(int i = 0; i < str.length(); i++)
		{
			if(com.defineSymbol(str.substring(i, i+1)) == 11)
			{
				continue;
			}
			pos = com.getArgument(str,i+1);
			symbol = str.substring(i+1, pos);
			i = pos - 1;
			System.out.println(symbol);
		}*/
		
		//System.out.println(com.defineFunction(str));
		//System.out.println(com.calculate(str));
		
		sc.close();
	}
}
