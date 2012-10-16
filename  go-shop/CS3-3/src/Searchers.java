/*
 * Searchers.java
 *
 * Version:
 *     $Id: Searchers.java,v 1.1 2010/09/24 15:16:14 rcd1575 Exp $
 *
 * Revisions:
 *     $Log: Searchers.java,v $
 *     Revision 1.1  2010/09/24 15:16:14  rcd1575
 *     Initial revision
 *
 */

import java.lang.*;

/**
 * This class contains a number of static methods that can be used to perform
 * searches on arrays of Comparable objects. The following search methods have
 * been implemented: linear search, binary search, and ternary search.
 * 
 * @author Paul Tymann
 * 
 */

public class Searchers {

	/**
	 * A constant value indicating the search failed.
	 */

	public static final int NOT_FOUND = -1;

	/**
	 * The number of comparisons made so far by the search algorithms.
	 */

	private static int numCompares = 0;

	/**
	 * Return the number of probes (compares) made by the search methods since
	 * the last time resetCompares() was called. The number of probes is not
	 * changed by this call.
	 * 
	 * @return the number of probes made by the search methods
	 */

	public static int getNumCompares() {
		return numCompares;
	}

	/**
	 * Reset the number of probes mades by the search methods to 0.
	 */

	public static void resetNumCompares() {
		numCompares = 0;
	}

	/**
	 * Search for the Comparable target in the specified array using a linear
	 * search. This method makes no assumptions regarding the order in which the
	 * objects appear in the array being searched (i.e., the objects do not need
	 * to be sorted). The instance variable numCompares will be incremented each
	 * time a comparison is made.
	 * 
	 * @param array
	 *            array of items to be searched
	 * @param target
	 *            item being looked for in the array.
	 * 
	 * @return index in the array where the target was found or NOT_FOUND if the
	 *         target is not in the array.
	 */

	public static <T> int linearSearch(T[] array, Comparable<T> target) {
		int retval = NOT_FOUND;

		// Search for the target until it is found or the end of the
		// array is reached.

		for (int i = 0; i < array.length && retval == NOT_FOUND; i++) {

			numCompares++;
			if (target.compareTo(array[i]) == 0) {
				retval = i;
			}
		}

		return retval;
	}

	/**
	 * Search for the Comparable target in the specified array using a binary
	 * search. Note the items in the array must be sorted in ascending order.
	 * The instance variable numCompares will be incremented each time a
	 * comparison is made.
	 * 
	 * @param array
	 *            sorted array of items to be searched
	 * @param target
	 *            item being looked for in the array.
	 * 
	 * @return index in the array where the target was found or NOT_FOUND if the
	 *         target is not in the array.
	 */

	public static <T> int binarySearch(Comparable<T>[] array, T target) {

		// Call the other form of binary search with low=0 and
		// high=the length of the array - 1 to do the real work.

		return binarySearch(array, target, 0, array.length - 1);
	}

	/**
	 * Search for the Comparable target in the specified array within the range
	 * specified by low and high using a binary search. Note the items in the
	 * array must be sorted in ascending order. The instance variable
	 * numCompares will be incremented each time a comparison is made.
	 * 
	 * @param array
	 *            sorted array of items to be searched
	 * @param target
	 *            item being looked for in the array.
	 * @param low
	 *            the lower (inclusive) bound of the search.
	 * @param high
	 *            the high (inclusive) bound of the search.
	 * 
	 * @return index in the array where the target was found or NOT_FOUND if the
	 *         target is not in the array.
	 */

	public static <T> int binarySearch(Comparable<T>[] array, T target,
			int low, int high) {

		// Hold the result of the search

		int result;

		// Determine the middle of the array

		int mid = (low + high) / 2;

		// Priming test loop
		int compare = array[mid].compareTo(target);
		numCompares++;

		// Stay in loop as long as the high and low indices have
		// not crossed and we have not found the value

		while ((low <= high) && (compare != 0)) {

			if (compare < 0) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}

			mid = (low + high) / 2;

			compare = array[mid].compareTo(target);
			numCompares++;

		}

		// Test whether we found the value

		if (low > high) {
			result = NOT_FOUND;
		} else {
			result = mid;
		}

		return result;
	}

	/**
	 * Search for the Comparable target in the specified array using a ternary
	 * search. Note the items in the array must be sorted in ascending order.
	 * The instance variable numCompares will be incremented each time a
	 * comparison is made.
	 * 
	 * @param array
	 *            sorted array of items to be searched
	 * @param target
	 *            item being looked for in the array.
	 * 
	 * @return index in the array where the target was found or NOT_FOUND if the
	 *         target is not in the array.
	 */

	public static <T> int ternarySearch(T[] array, Comparable<T> target) {

		// Call the other form of ternary search with low=0 and
		// high= the length of the array - 1 to do the real work.

		return ternarySearch(array, target, 0, array.length - 1);
	}

	/**
	 * Search for the Comparable target in the specified array within the range
	 * specified by low and high using a ternary search. Note the items in the
	 * array must be sorted in ascending order. The instance variable
	 * numCompares will be incremented each time a comparison is made.
	 * 
	 * @param array
	 *            sorted array of items to be searched
	 * @param target
	 *            item being looked for in the array.
	 * @param low
	 *            the lower (inclusive) bound of the search.
	 * @param high
	 *            the high (inclusive) bound of the search.
	 * 
	 * @return index in the array where the target was found or NOT_FOUND if the
	 *         target is not in the array.
	 */

	public static <T> int ternarySearch(T[] array, Comparable<T> target,
			int low, int high) {

		// hold the result of the search

		int result;

		// Priming test loop

		int compare = NOT_FOUND;

		// Stay in loop as long as the high and low indices have
		// not crossed and we have not found the value

		int probe = 0;

		while ((low <= high) && (compare != 0)) {

			// Divide the array into thirds

			int third = (high - low) / 3;

			// The lower third runs from 0 to low + third

			probe = low + third;

			// Check if the target is in the lower third of the array

			compare = target.compareTo(array[probe]);
			numCompares++;

			// If so, limit the search to 0 - probe -1 and loop

			if (compare < 0) {
				high = probe - 1;
			} else if (compare > 0) {

				// The element is not in the lower third, so look in
				// the remaining thirds.

				low = probe + 1;

				probe = high - third;

				// Check to see if the target is in the middle or
				// top third of the array

				compare = target.compareTo(array[probe]);
				numCompares++;

				// If the element is in the middle move high down and
				// repeat. Otherwise it is in the top third, so low
				// is adjusted

				if (compare < 0) {
					high = probe - 1;
				} else {
					low = probe + 1;
				}
			}
		}

		// Test whether we found the value

		if (compare != 0) {
			result = NOT_FOUND;
		} else {
			result = probe;
		}

		return result;
	}

} // Searchers
