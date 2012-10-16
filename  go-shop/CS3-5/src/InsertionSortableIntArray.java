/*
 * InsertionSortableIntArray.java
 *
 * Version:
 *     $Id: InsertionSortableIntArray.java,v 1.11 2003/01/26 19:43:15 cs2 Exp $
 *
 * Revisions:
 *     $Log: InsertionSortableIntArray.java,v $
 *     Revision 1.11  2003/01/26 19:43:15  cs2
 *     *** empty log message ***
 *
 *     Revision 1.10  2001/10/31 16:00:21  cs2
 *     Changed to count the number of operations
 *
 *     Revision 1.9  2001/04/23 18:07:27  cs2
 *     Fixed spelling errors
 *
 *     Revision 1.8  2001/01/22 21:04:39  cs2
 *     *** empty log message ***
 *
 *     Revision 1.7  2001/01/22 16:33:56  cs2
 *     Removed java.lang
 *
 *     Revision 1.6  2001/01/21 22:05:35  cs2
 *     Fixed class and file headers to match 20001 standards
 *
 */

/**
 * A subclass of SortableIntArray that sorts integers using an
 * insertion sort.

 * @author     Paul Tymann
 */

public class InsertionSortableIntArray extends SortableIntArray {

    /**
     * Create an InsertionSortableIntArray of the given size.
     *
     * @param    size    the size of the collection.
     */

    public InsertionSortableIntArray( int size ) {
	super( size );
    }

    /**
     * Sort the integers in the collection using an insertion sort.
     */

    public void sort() {

        // i marks the beginning of the unsorted portion of the array
        // (elements in postions 0..i-1 are in sorted order).

	for ( int i = 1; i < getSize(); i++) {
            // Insert the first element in the unsorted portion of the
            // array into the sorted portion.  The process works from
            // right to left in the sorted array.

	    for ( int j = i - 1;
                    j >= 0 && getElementAt( j + 1 ) < getElementAt( j ); 
                    j-- ) { 
		operations++; 

                // since we have not found the correct location for the
                // new element, move the elements in the sorted portion of
                // the array one to the right.

		int tmp = getElementAt( j );
		setElementAt( j , getElementAt( j + 1 ) );
		setElementAt( j + 1 , tmp );
	    }
        }
    }

} // InsertionSortableIntArray
