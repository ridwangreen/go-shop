/*
 * CRObject.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */

/**
 * Object that hold the word and the page numbers it appears on
 * 
 * @author rcd1575: Rebecca Dudley
 */

public class CRObject implements Comparable<CRObject> {

	private String word;  //the word associated with the CRObject
	private DynamicList<Integer> numLine;  //a list of line numbers the word appears on

	/**
	 * constructor  takes in a word and creates an empty list of line numbers
	 * 
	 * @param word    the word to be associated with the CRObject
	 */
	public CRObject(String word) {

		this.word = word;
		numLine = new DynamicList<Integer>();
	}

	/**
	 * returns the word associated with the CRObject
	 * 
	 * @return   the word associate with the CRObject
	 */
	public String getWord() {
		return word;
	}

	/**
	 *  returns the list of line numbers
	 *  
	 * @return   the dynamicList of line numbers
	 */
	public DynamicList<Integer> getLineNumbers() {
		return numLine;
	}

	/**
	 * adds a new line number to the list if the the line number isn't
	 * already in the list
	 * 
	 * @param lineNum  the line number to be added
	 */
	public void addLineNum(int lineNum) {

		if(numLine.empty()){
			numLine.add(lineNum);
		}
		else if (!numLine.getLast().equals(lineNum)) {
			numLine.add(lineNum);
		}

	}
	
	/**
	 * returns the number of lines the word appears on (frequency)
	 * 
	 * @return  the length of the list containing line numbers
	 */
	public int getNumLines(){
		return numLine.length();
	}

	/**
	 * returns the CRObject in String form
	 * 
	 * @return  the String representaion of the CRObject
	 */
	public String toString() {

		return word + numLine.toString();
	}

	/**
	 * compares the words associated with the CRObjects
	 * 
	 * @return  the compare to value after comparing this with other
	 */
	@Override
	public int compareTo(CRObject o) {
		// TODO Auto-generated method stub
		return word.compareTo(o.getWord());
	}

}
