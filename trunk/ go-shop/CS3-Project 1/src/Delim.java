/*
 * Delim.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Checks to make sure the input has balanced delimiters
 * 
 * @author rcd1575: Rebecca Dudley
 */

public class Delim {

	private static CS3LinkedStack<Character> stack = new CS3LinkedStack<Character>();

	public static void main(String args[]) throws IOException {

		if (args.length != 2) {
			System.err.println("Usage: java Delim file1.in file1.out");
		}
		String input = "";
		if (args[0].equals("-")) {
			System.out.println();
			Scanner in = new Scanner(System.in);
			input = in.next();

		}else{
			BufferedReader inFile = new BufferedReader(new FileReader(args[0]));
			input = inFile.readLine();
		}
		if (args[1].equals("-")){
			if(check(input)){
				System.out.println("Delimiters are balanced.");
			}else{
				System.out.println("Delimiters are not balanced.");
			}
		}else{
			PrintWriter postOut = new PrintWriter(args[1]);
			PrintWriter out = new PrintWriter(new BufferedWriter(postOut));
			if(check(input)){
				out.write("Delimiters are balanced.");
			}else{
				out.write("Delimiters are not balanced.");
			}
		} 
	}

	/**
	 * tells if the input value is balanced
	 * 
	 * @param toCheck
	 *            the value to be tested
	 * @return true if its balanced, false if its not
	 */
	public static boolean check(String toCheck) {
		boolean isBalanced = true;
		for (int x = 0; x < toCheck.length(); x++) {
			char c = toCheck.charAt(x);
			if (c == '(' || c == '{' || c == '[') {
				stack.push(c);
			} else if (c == ')' || c == '}' || c == ']') {
				try {
					if (getOpp(c) != stack.pop()) {
						isBalanced = false;
					}
				} catch (CS3StackEmptyException e) {
				}
			}
		}
		if (stack.size() % 2 != 0) {
			isBalanced = false;
		}
		return isBalanced;
	}

	/**
	 * given the open delimiters, returns the close delimiter
	 * 
	 * @param x
	 *            the open delimiter
	 * @return the close delimiter
	 */
	private static char getOpp(char x) {
		if (x == ')') {
			return '(';
		} else if (x == '}') {
			return '{';
		} else
			return '[';
	}

} // Delim
