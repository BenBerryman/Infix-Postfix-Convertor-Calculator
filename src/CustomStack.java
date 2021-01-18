/*
INFIX-POSTFIX CONVERTOR/CALCULATOR - CustomStack.java
Benjamin Berryman

A simple program that uses a stack to either convert from infix notation to
postfix notation, or to calculate a postfix expression.
 */

import java.util.ArrayList;
public class CustomStack<E> {

	private ArrayList<E> data;
	private E lastVal;
	
	public CustomStack() //Constructor
	{
		data = new ArrayList<>();
	}
	
	public int size() //Check size of the stack.
	{
		return data.size();
	}

	public boolean isEmpty() //Check if the stack is empty.
	{
		return data.size() == 0;
	}

	@SuppressWarnings("unchecked")
	public E top() //Returns the top value on the stack. If the stack is empty, return 
	{
		if (isEmpty())
			return (E)"";
		return lastVal;
		
	}

	public void add(E element) //Add an element to the top of the stack.
	{
		data.add(element);
		lastVal = element;
	}

	public E pop() //Removes the top element from the stack and returns it.
	{
		E temp = lastVal;
		data.remove(lastVal);
		if (isEmpty())
			lastVal = null;
		else
			lastVal = data.get(data.size()-1);
		return temp;
	}
	
	@SuppressWarnings("unchecked")
	public String convertToPostfix(String in) // Given an infix notation expression in the form of a String, output in postfix notation.
	{
		String input = in;
		String output = "";
		while (!input.equals(""))
		{
			String current = input.substring(0,1);
			
	/* 
	 *  Check next term. If the operator on top of the stack has greater
	 *  precedence than the new one, pop until it doesn't, then add.
	 */

			switch (current)
			{
				case "(":
					add((E)current);
					break;

				case ")":
					while (!top().equals("("))
					{
						output += pop();
					}
					pop();
					break;

				case "^":
					add((E)current);
					break;

				case "/":
					while (top().equals("^") || top().equals("*"))
					{
						output += pop();
					}
					add((E)current);
					break;

				case "*":
					while (top().equals("^") || top().equals("/"))
					{
						output += pop();
					}
					add((E)current);
					break;

				case "+":
					while (top().equals("^") || top().equals("/") || top().equals("*") || top().equals("-"))
					{
						output += pop();
					}
					add((E)current);
					break;

				case "-":
					while (top().equals("^") || top().equals("/") || top().equals("*") || top().equals("+"))
					{
						output += pop();
					}
					add((E)current);
					break;

				default:	 //The next term is a number.
					output += current;
					break;
			}
			input = input.substring(1);
		}
		while (!isEmpty()) //Print out any remaining operators.
		{
			output += pop();
		}
		return output;
	}
	
	@SuppressWarnings("unchecked")
	public String evaluate(String input) //Given a postfix expression, evaluate if possible. If not, print nv (not valid).
	{
		String line = input;
		String output;
		String current = "";
		float op1;
		float op2;
		
	/*
	 * Try to evaluate the given expression.
	 */
		try
		{
			while (!line.equals(""))
			{
				current = line.substring(0,1);
			/*
			 * Try to parse the current term to an integer.
			 * If it works, it's a number, so add it to the stack.
			 */
				try 
				{
					Integer.parseInt(current);
					add((E)(current));
				}
			/*
			 * If the parse didn't work, the current term is an operator.
			 * Pop the top two operands and perform the proper operation,
			 * push the result back to the stack.
			 */
				catch (NumberFormatException e) 
				{
					op2 = Float.parseFloat((String)pop());
					op1 = Float.parseFloat((String)pop());
					switch (current)
					{
					
					case "+":
						add((E)Float.toString(op1+op2));
						break;
					case "-":
						add((E)Float.toString(op1-op2));
						break;
					case "/":
						add((E)Float.toString(op1/op2));
						break;
					case "*":
						add((E)Float.toString(op1*op2));
						break;
					case "^":
						add((E)Float.toString((float) Math.pow(op1, op2)));
						break;
					}
					
				}
				line = line.substring(1);
			}
			output = (String)pop();
			if (!isEmpty()) //If the stack had more than one operand left, there were not enough operators to fully implement the expression. Therefore, it's not valid.
				return "nv";
			else if (output.equals("Infinity")) //If at some point there was a divide by 0 operation, return nv.
				return "nv";
			return output;
		}
	/*
	 * If any exceptions with the stack itself occur during evaluation,
	 * the given expression is not valid. In this case, just print nv to the file.
	 */
		catch (NullPointerException e)
		{
			return "nv";
		}
	}

}
