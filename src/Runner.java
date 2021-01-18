/*
INFIX-POSTFIX CONVERTOR/CALCULATOR - main.java
Benjamin Berryman

A simple program that uses a stack to either convert from infix notation to
postfix notation, or to calculate a postfix expression.
 */

import java.io.*;
public class Runner {

	public static void main(String[] args) throws IOException {
		CustomStack<String> stack = new CustomStack<>();

		FileWriter fw = new FileWriter(args[2]);
		BufferedWriter bw = new BufferedWriter(fw);
		String output;
		
		if (Integer.parseInt(args[0]) == 1) // Converter; simply output the postfix notation
		{
			output = stack.convertToPostfix(args[1]);
			bw.write(output);
		}
		else if (Integer.parseInt(args[0]) == 2) //Calculator; check that input truly is in postfix notation. If so, evaluate
		{
			output = stack.evaluate(args[1]);
			bw.write(output);
		}
		bw.close();
	}
}
