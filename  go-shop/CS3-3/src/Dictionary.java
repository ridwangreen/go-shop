/*
 * Dictionary.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

import java.io.*;
import java.util.*;

/**
 * This class creates objects that are capable of doing simple lookups in a
 * sorted dictionary of words. The Searchers class is used to provide the
 * searching capability.
 * 
 * @author Paul Tymann
 * 
 */

public class Dictionary {

	// Constant used to indicate search failure

	public static final int NOT_FOUND = -1;

	// The array in which the dictionary is stored

	private String[] words;

	// the number of searches done by this class

	private int numSearches;

	private int[] indexes;

	/**
	 * Create a new dictionary object that uses the word list in the specified
	 * file for lookup.
	 * 
	 * @param fileName
	 *            the file that contains the word list
	 * 
	 * @exception FileNotFoundException
	 *                if the word list file cannot be found or opened
	 * @exception IOException
	 *                if an I/O error occurs during reading
	 */

	public Dictionary(String fileName) throws FileNotFoundException,
			IOException {

		// Stream to read the word list on

		BufferedReader in = new BufferedReader(new FileReader(fileName));

		// Since we do not initially know the size of the word list,
		// it will temporarily be stored in a List. Once the list
		// has been read into memory, it will be converted into an
		// array.

		List<String> tmpDict = new ArrayList<String>();
		String nextWord;

		// Read the words from the file. Assumes one word per line
		while ((nextWord = in.readLine()) != null) {

			// Ignore case

			nextWord = nextWord.toLowerCase();

			// Add the word to the dictionary

			tmpDict.add(nextWord);
		}

		// Convert the List containing the words to an array

		words = (String[]) tmpDict.toArray(new String[0]);

		// Clean up

		in.close();

		indexes = new int[26];
		int pos = Character.getNumericValue('a');
		for (int i = 0; i < words.length; i++) {
			int current = Character.getNumericValue(words[i].charAt(0));
			if (current == pos && indexes[current - pos] == 0) {
				indexes[current - pos] = i;
			}
		}
		
	}

	/**
	 * Lookup the given string in the dictionary - the lookup is NOT
	 * case-sensitive. The constant NOT_FOUND will be returned if the word is
	 * not in the dictionary.
	 * 
	 * @param target
	 *            the string being searched for in the dictionary.
	 * 
	 * @return NOT_FOUND if the string cannot be found.
	 */

	public int lookUp(String target) {
		numSearches++;

		// Ignore case

		target = target.toLowerCase();

		// Limits for the search

		int low = 0;
		int high = words.length - 1;

		int pos = Character.getNumericValue(target.charAt(0))
				- Character.getNumericValue('a');
		return Searchers.binarySearch(words, target, pos, pos-1);
	}

	/**
	 * Return the number of words currently in the dictionary.
	 * 
	 * @return the number of words currently in the dictionary.
	 */

	public int length() {
		return words.length;
	}

	/**
	 * The number of searches performed by this dictionary object.
	 * 
	 * @return the number of searches performed by this dictionary object.
	 */

	public int numSearches() {
		return numSearches;
	}

	/**
	 * The average number of probes (searches) made by this dictionary object.
	 * 
	 * @return the average number of probes made by this dictionary object.
	 */

	public int averageProbes() {
		return Searchers.getNumCompares() / numSearches;
	}

} // Dictionary
