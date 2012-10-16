/*
 * Calc.java
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
import java.util.ArrayList;
import java.util.Scanner;

/**
 * calculator using stacks
 * 
 * @author rcd1575: Rebecca Dudley
 */
public class Calc {

	public static void main(String args[]) throws CS3StackEmptyException,
			IOException {
		String input = "";
		if (args[0].equals("-")) {
			System.out.println();
			Scanner in = new Scanner(System.in);
			input = in.next();

		} else {
			BufferedReader inFile = new BufferedReader(new FileReader(args[0]));
			input = inFile.readLine();
		}
		String bad = "";
		String toAdd = "";
		int numOps = 0;
		ArrayList<Double> nums = new ArrayList<Double>();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (!((int) c <= 57 && (int) c >= 40 && (int) c != 44)) {
				bad += c;
				break;
			} else {
				if (c != '(' && c != ')' || i == input.length() - 1) {
					if (i == input.length() - 1
							|| !((int) c <= 57 && (int) c >= 48 || (int) c == 46)) {
						if (c != ')' && c !='(' && i == input.length() - 1) {
							toAdd += c;
						}
						nums.add(Double.parseDouble(toAdd));
						toAdd = "";
						numOps++;
					} else {
						toAdd += c;
					}
				}
			}
		}
		if (numOps == 0) {
			nums.add(Double.parseDouble(toAdd));
		}
		if (bad.length() != 0) {
			System.err.print(input + ": Invalid token: ");
			for (int i = 0; i < bad.length(); i++) {
				System.err.print(bad.charAt(i) + " ");
			}
			System.out.println();
			return;
		}
		if (!Delim.check(input)) {
			System.err.println(input + ": Parentheses are not balanced.");
			return;
		}
		String post = InPostPar.toPost(input);
		CS3LinkedStack<Character> op = new CS3LinkedStack<Character>();
		CS3LinkedStack<Double> num = new CS3LinkedStack<Double>();
		CS3LinkedStack<Character> nOrO = new CS3LinkedStack<Character>();
		int x = 0;
		for (int c = post.length() - 1; c >= 0; c--) {
			char o = post.charAt(c);
			if (o == '+' || o == '-' || o == '/' || o == '*') {
				op.push(o);
				nOrO.push('o');
				x= 0;
			}else if(x < 2){
				nOrO.push('n');
				x++;
			}
		}
		double ans = nums.remove(nums.size()-1);
		while (!nOrO.isEmpty()) {
			char current = nOrO.pop();
			if (current == 'n' && nums.size() != 0) {
				num.push(nums.remove(nums.size() - 1));
			} else {
				while (!op.isEmpty()) {
					char o = op.pop();
					if (o == '-') {
						ans *= -1;
						ans += num.pop();
					} else if (o == '+') {
						ans += num.pop();
					}else if(o == '/'){
						ans /= num.pop();
					}else{
						ans *= num.pop();
					}
					
				}
			}

		}

		if (args[1].equals("-")) {
			System.out.println("Infix expression: " + input);
			System.out.println("Postfix expression: " + post);
			System.out.println("Result is " + ans);
		} else {
			PrintWriter postOut = new PrintWriter(args[1]);
			PrintWriter out = new PrintWriter(new BufferedWriter(postOut));
			out.write(input + "Infix expression: " + input);
			out.write("Postfix expression: " + post);
			out.write("Result is " + ans);
		}
	}
}
