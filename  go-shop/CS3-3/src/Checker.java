

/*
 * Checker.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Spell checks a file
 *
 * @author     Becca Dudley
 *
 */
public class Checker {

	public static void main(String args[]){
		
		if(args.length != 2){
			System.err.print("Usage: java Checker dict-file file-to-check");
			return;
		}
		File inFile = new File(args[1]);
		Scanner sc;
		try {
			sc = new Scanner(inFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			System.err.print("Checker: " + e1.getMessage());
			return;
		}
		sc.useDelimiter("[^a-zA-Z]+");
		
		Dictionary dict;
		try {
			dict = new Dictionary(args[0]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.print("Checker: " + e.getMessage());
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.print("Checker: " + e.getMessage());
			return;
		} 
		
		int numChecked = 0;
		while(sc.hasNext()){
			String word = sc.next();
			if(dict.lookUp(word) == Searchers.NOT_FOUND){
				System.out.println(word);
			}
			numChecked++;
		}

		System.out.println("Size of Dictionary: " + dict.length() + "\n" + "Words checked: " + numChecked + "\n" + "Average probes: " + dict.averageProbes());
	}
	
}
