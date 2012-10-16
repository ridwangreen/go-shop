/*
 * MToTheNth.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */

/**
 * MToTheNth uses recursion to calculate POWERS!!!
 * 
 * @author rcd1575: Becca Dudley
 */

public class MToTheNth {

	/**
	 * main method
	 * 
	 * @param args
	 *            command line arguments, args[0] is m, args [1] is n
	 */
	public static void main(String args[]) {

		if (args.length != 2) {
			System.err.println("Usage: java MToTheNth m n");
			return;
		}
		int m;
		int n;
		try {
			m = Integer.parseInt(args[0]);
			n = Integer.parseInt(args[1]);
		} catch (NumberFormatException nfe) {
			System.err.println("Invalid argument " + nfe.getMessage());
			return;
		}
		if (n < 0 || m <= 0) {
			System.out.print("Arguments may not be negative: ");
			if (n < 0 && m <= 0) {
				System.out.println(m + " and " + n + " are incorrect.");
				return;
			} else if (n < 0) {
				System.out.println(n + " is incorrect.");
				return;
			}
			System.out.println(m + " is incorrect.");
			return;
		}

		System.out.print(print(m,n));
		System.out.println("= " + mToTheNth(m, n));
	}

	private static int mToTheNth(int m, int n) {

		if (n == 0) {
			return 1;
		} else if (n == 1) {
			return m;
		} else {
			return m * mToTheNth(m, n - 1);
		}
	}

	public static String print(int m, int n) {

		if (n == 0) {
			return "1";
		} else if (n == 1) {
			return m + " ";
		} else {
			return (m + " * " + print(m, n - 1));
		}
	}

} // MToTheNth
