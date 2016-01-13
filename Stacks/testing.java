import java.util.Stack;
import java.io.*;
public class BooleanExpression 
{
	String relate;
	
	public BooleanExpression(String str)
	{
		relate = str;
	}
	public String postfixProper(String str)
	{
		String result = "";
		Stack s1 = new Stack();
		int i = 0;
		while(i < str.length())
		{
			String st = str.substring(i, i+1);
			if(st == "(")
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
					if (st.equals("<") || st.equals(">")
					{
						if(str.charAt(i+1) == '=')
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
				}
				else if (st.equals("<") || st.equals(">"))
				{
					if(str.charAt(i+1) == '=')
					{
						st += "=";
						i++;
					}
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
					if(s1.peek() == "(")
						break;
					else
						result += s1.pop();
				}
			}
			i++;
		}
		while(s1.isEmpty() == false)
		{
			if(s1.peek() == "(")
				break;
			else
				result+=s1.pop();
		}
		return result;
	}