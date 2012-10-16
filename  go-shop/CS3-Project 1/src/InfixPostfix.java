/*
 * InfixPostfix.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Converts from a given input string from infix notation to postfix notation
 * 
 * @author rcd1575: Rebecca Dudley
 */

public class InfixPostfix {

	private static CS3LinkedStack<Character> nums = new CS3LinkedStack<Character>();
	private static CS3LinkedStack<Character> operators = new CS3LinkedStack<Character>();

	public static void main(String args[]) throws IOException,
			CS3StackEmptyException {

		String input = "";
		if (args[0].equals("-")) {
			System.out.println();
			Scanner in = new Scanner(System.in);
			input = in.next();

		}else{
			BufferedReader inFile = new BufferedReader(new FileReader(args[0]));
			input = inFile.readLine();
		}
		if (args[1].equals("-")) {
			System.out.println("Infix expression: " + input);
			System.out.println("Postfix expression: " + toPost(input));
		}else{
			PrintWriter postOut = new PrintWriter(args[1]);
			PrintWriter out = new PrintWriter(new BufferedWriter(postOut));
			out.write(input + "Infix expression: " + input);
			out.write("Postfix expression: " + toPost(input));
		} 
	}

	/**
	 * converts from infix to postfix
	 * 
	 * @param input
	 *            the infix string
	 * @return the postfix string
	 */
	private static String toPost(String input) throws CS3StackEmptyException {
		CS3LinkedStack<Character> ops = new CS3LinkedStack<Character>();
		String toReturn = "";
		for (int i = 0; i < input.length(); i++) {
			char current = input.charAt(i);
			if (current == '*' || current == '/') {
				if (ops.isEmpty() || ops.peek() == '+' || ops.peek() == '-') {
					ops.push(current);
				} else {
					toReturn += ops.pop();
					ops.push(current);
				}
			} else if (current == '+' || current == '-') {
				if (ops.isEmpty()) {
					ops.push(current);
				} else {
					while (!ops.isEmpty()) {
						toReturn += (ops.pop());
					}
					ops.push(current);
				}
			} else {
				toReturn += (current);
			}
		}
		while (!ops.isEmpty()) {

			toReturn += (ops.pop());
		}
		return toReturn;
	}

}
