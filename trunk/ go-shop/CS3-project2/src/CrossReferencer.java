import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
 * CrossReferencer.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */

/**
 * CrossReferencer program for project2
 * 
 * @author rcd1575: Rebecca Dudley
 */
public class CrossReferencer {

	public static void main(String args[]) throws IOException {

		if (args.length != 1) {

			System.err.println("Usage: CrossReferencer filename");
			return;
		}

		BufferedReader inFile;
		try {
			inFile = new BufferedReader(new FileReader(args[0]));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block

			System.err.println("Error Opening File: " + args[0]);
			return;
		}

		WordTree tree = new WordTree();
		Scanner sc = new Scanner(inFile);
		int lineNum = 1;
		String line = "";
		while (sc.hasNextLine()) {

			line = sc.nextLine();
			String word = "";
			boolean end = true;
			for (int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);

				if (c != ' ' && c != '\t' && c != '\n') {
					word += line.charAt(i);
					end = false;
				}
				
				else {
					if (!word.equals("")) {
						end = true;
					} else {
						end = false;
					}
				}
				if(i == line.length() - 1){
					end = true;
				}
				if (end) {
					word = tokenize(word);
					CRObject o = new CRObject(word);
					if (tree.get(o) == null) {
						// System.out.println(word + " at this");
						o.addLineNum(lineNum);
						tree.add(o);
					} else {
						tree.get(o).addLineNum(lineNum);

					}
					word = "";
				}
			}
			lineNum++;

		}

		System.out.println();
		
		tree.print();
		
		System.out.println();
		WordList freqs = tree.makeFreqList();
		freqs.print();

		System.out.println();
		Scanner in = new Scanner(System.in);
		String toFind = "purple";
		while(!toFind.equals("") && in.hasNext()){
			toFind = in.nextLine();
			System.out.println(tree.find(toFind));
			
		}
		
	}

	/**
	 * takes away punctuation marks and converts to lower case
	 * 
	 * @param word  the word to be tokenized
	 * @return   the tokenized version of the word
	 */
	public static String tokenize(String word) {

		String toReturn = "";
		for (int i = 0; i < word.length(); i++) {

			char c = word.charAt(i);
			if (c != (char) '.' && c != (char) ',' && c != (char) '?'
					&& c != (char) '!') {
				toReturn += c;
			}
		}

		return toReturn.toLowerCase();
	}


}
