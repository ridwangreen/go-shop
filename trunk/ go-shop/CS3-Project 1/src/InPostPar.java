/*
 * InPostPar.java
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
 * using parenthesis
 * 
 * @author rcd1575: Rebecca Dudley
 */

public class InPostPar {

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
		if (!Delim.check(input)) {
			System.out.println(input + ": Parentheses are not balanced.");
			return;			
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

	public static String toPost(String input) throws CS3StackEmptyException {
		CS3LinkedStack<Character> ops = new CS3LinkedStack<Character>();
		String toReturn = "";
		boolean inParen = false;
		int numP = 0;
		String parens = "";
		for (int i = 0; i < input.length(); i++) {
			char current = input.charAt(i);
			if (current == '(') {
				inParen = true;
				numP++;
			} else if (current == ')') {
				if (numP != 1) {
					parens += current;
					numP--;
				} else {
					inParen = false;
					toReturn += toPost(parens.substring(1));
					parens = "";
					numP = 0;
				}
			}
			if (inParen) {
				parens += current;
			} else {
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
				} else if (!(current == '(' || current == ')')) {
					toReturn += (current);
				}
			}
		}
		while (!ops.isEmpty()) {

			toReturn += (ops.pop());
		}
		return toReturn;
	}

}
