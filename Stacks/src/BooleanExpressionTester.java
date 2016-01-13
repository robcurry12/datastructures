import java.util.Scanner;
import java.io.*;
public class BooleanExpressionTester 
{
	public static void main(String[] args) throws Exception
	{
		Scanner scan = new Scanner (new File ("Stack.txt"));
		while (scan.hasNext())
		{
			String exprLine = scan.nextLine();
			BooleanExpression expr = new BooleanExpression(exprLine);
			boolean result = expr.evaluate();
			System.out.println(exprLine + "\n" + result);
		}
		scan.close();
	}

}
