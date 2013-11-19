


public class Calculator
{
	public static void main(String[] args) 
	{
		
		GraphicInterface gi = new GraphicInterface("Calculator");
		gi.runGUI();
		
		//System.out.println(com.calculateFunction(str));
		
		/*
		
		Scanner sc = new Scanner(System.in);
		String str = new String();
		String symbol = new String();
		
		//str = sc.nextLine();
		
		Compute com = new Compute();
		
		int pos;
		
		for(int i = 0; i < str.length(); i++)
		{
			if(com.defineSymbol(str.substring(i, i+1)) == 11)
			{
				continue;
			}
			pos = com.getArgument(str,i+1);
			symbol = str.substring(i+1, pos);
			i = pos - 1;
			System.out.println(symbol);
		}
		sc.close();
		*/
		
		//System.out.println(com.defineFunction(str));
		//System.out.println(com.calculate(str));
		
		
	}
}
