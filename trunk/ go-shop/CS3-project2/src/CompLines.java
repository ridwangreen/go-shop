/*
 * CompInt.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */

import java.util.Comparator;

/**
 * Object that compares the CRObject
 * 
 * @author rcd1575: Rebecca Dudley
 */
public class CompLines implements Comparator<CRObject>{

	/**
	 * Compares two CRObjects, first by line numbers, then by words
	 * 
	 * @param  first - the first CRObject
	 * @param  second - the second CRObject
	 */
	@Override
	public int compare(CRObject first, CRObject second) {
		// TODO Auto-generated method stub
		if(((Integer)first.getNumLines()).compareTo((Integer)second.getNumLines()) == 0){
			return first.getWord().compareTo(second.getWord());
		}
		
		return ((Integer)first.getNumLines()).compareTo((Integer)second.getNumLines());
	}

}
