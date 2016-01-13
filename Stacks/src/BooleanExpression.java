import java.util.Stack;
public class BooleanExpression 
{
	String relate;
	
	/**
	 * BooleanExpression constructor
	 * @param str value to create the relate attribute of the BooleanExpression object
	 */
	public BooleanExpression(String str)
	{
		relate = str;
	}
	
	/**
	 * Takes an infix expression and converts it into a postfix expression using stacks
	 * @param str the infix expression to be converted
	 * @return the postfix version of the infix expression
	 */
	public String postfixProper(String str)
	{
		String result = "";
		Stack<String> s1 = new Stack<String>();
		int i = 0;
		while(i < str.length())
		{
			String st = str.substring(i, i+1);
			if(st.equals("("))
				s1.push(st);
			
			else if(Character.isDigit(str.charAt(i)))
				result += st;
			
			else if(st.equals("<") || st.equals(">") || st.equals("=") || st.equals("!") 
					|| st.equals("&") || st.equals("|") || st.equals("-"))
			{
				if(s1.isEmpty() == true)
				{
					if ((st.equals("<") || st.equals(">")) && (str.charAt(i+1) == '='))
					{
						st += "=";
						s1.push(st);
						i++;
					}
					else if (st.equals("=") || st.equals("!"))
					{
						st += "=";
						s1.push(st);
						i++;
					}
					else if (st.equals("&"))
					{
						st += "&";
						s1.push(st);
						i++;
					}
					else if (st.equals("|"))
					{
						st += "|";
						s1.push(st);
						i++;
					}
					else if (st.equals("-"))
					{
						st += ">";
						s1.push(st);
						i++;
					}
					else
					{
						s1.push(st);
					}
				}
				
				else if (s1.peek().equals("("))
				{
					if ((st.equals("<") || st.equals(">")) && (str.charAt(i+1) == '='))
					{
						st += "=";
						s1.push(st);
						i++;
					}
					else if (st.equals("=") || st.equals("!"))
					{
						st += "=";
						s1.push(st);
						i++;
					}
					else if (st.equals("&"))
					{
						st += "&";
						s1.push(st);
						i++;
					}
					else if (st.equals("|"))
					{
						st += "|";
						s1.push(st);
						i++;
					}
					else if (st.equals("-"))
					{
						st += ">";
						s1.push(st);
						i++;
					}
					else
					{
						s1.push(st);
					}
				}
				else if (st.equals("<") || st.equals(">"))
				{
					if(str.charAt(i+1) == '=')
					{
						st += "=";
						s1.push(st);
						i++;
					}
					else
						s1.push(st);
				}
				else if ((st.equals("=") || st.equals("!")) && (s1.peek().equals("&&") ||s1.peek().equals("||") || s1.peek().equals("->")))
				{
					st += "=";
					s1.push(st);
					i++;
				}
				else if ((st.equals("&")) && (s1.peek().equals("||") || s1.peek().equals("->")))
				{
					st += "&";
					s1.push(st);
					i++;
				}
				else if ((st.equals("|")) && (s1.peek().equals("->")))
				{
					st += "|";
					s1.push(st);
					i++;
				}
				else if (st.equals("-"))
				{
					st += ">";
					s1.push(st);
					i++;
				}
				else
				{					
					result += s1.pop();
					if((st.equals("<") || st.equals(">") && (str.charAt(i+1) == '=')) || 
						(st.equals("|") || st.equals("&") || st.equals("!") || st.equals("-")))
						i--;
				}
			}
			else
			{
				while(s1.isEmpty() != true)
				{
					if(s1.peek().equals("("))
						break;
					else
						result += s1.pop();
				}
			}
			i++;
		}
		while(s1.isEmpty() != true)
		{
			if(s1.peek().equals("("))
				s1.pop();
			else
				result += s1.pop();
		}
		return result;
	}
	
	/**
	 * Evaluates a postfix logical expression using stacks
	 * @return the truth value of the logical expression
	 */
	public boolean evaluate()
	{
		relate = relate.replaceAll("\\s","");
		relate = postfixProper(relate);
		Stack<Integer> numbers = new Stack<Integer>();
		Stack<Boolean> truthVal = new Stack<Boolean>();
		
		int i = 0;
		while(i < relate.length())
		{
			String st = relate.substring(i, i+1);
			if(Character.isDigit(relate.charAt(i)))
				numbers.push(Character.digit(relate.charAt(i), 10));
			else if(st.equals(">") || st.equals("<") || st.equals("=") || st.equals("!"))
			{
				int operand2 = numbers.pop();
				int operand1 = numbers.pop();
				if(st.equals(">"))
				{
					if(i == relate.length()-1)
					{
						i++;
						truthVal.push(operand1 > operand2);
					}
					else if(relate.charAt(i+1) == '=')
					{
						truthVal.push(operand1 >= operand2);
					}
					else
					{
						truthVal.push(operand1 > operand2);
					}
				}
				else if(st.equals("<"))
				{
					if(i == relate.length()-1)
					{
						i++;
						truthVal.push(operand1 < operand2);
					}
					else if(relate.charAt(i+1) == '=')
					{
						truthVal.push(operand1 <= operand2);
					}
					else
					{
						truthVal.push(operand1 < operand2);
					}
				}
				else if(st.equals("="))
				{
					truthVal.push(operand1 == operand2);
					i++;
				}
				else
				{
					truthVal.push(operand1 != operand2);
					i++;
				}
			}
			else if(st.equals("&") || st.equals("|") || st.equals("-"))
			{
				boolean operand2 = truthVal.pop();
				boolean operand1 = truthVal.pop();
				if(st.equals("&"))
				{
					truthVal.push(operand1 && operand2);
					i++;
				}
				else if(st.equals("|"))
				{
					truthVal.push(operand1 || operand2);
					i++;
				}
				else if(st.equals("-"))
				{
					if(operand1 == true && operand2 == true)
						truthVal.push(true);
					else if(operand1 == true && operand2 == false)
						truthVal.push(false);
					else if(operand1 == false && operand2 == true)
						truthVal.push(true);
					else
						truthVal.push(true);
					i++;
				}
			}
			i++;
		}
		return truthVal.peek();
	}
		
}