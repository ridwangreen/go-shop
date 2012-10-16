/*
 * SelectionSortableIntArray.java
 *
 * Version:
 *     $Id: SelectionSortableIntArray.java,v 1.8 2001/01/22 16:33:56 cs2 Exp $
 *
 * Revisions:
 *     $Log: SelectionSortableIntArray.java,v $
 *     Revision 1.8  2001/01/22 16:33:56  cs2
 *     Removed java.lang
 *
 *     Revision 1.7  2001/01/22 16:28:19  cs2
 *     Fixed spacing.
 *
 *     Revision 1.6  2001/01/21 22:05:35  cs2
 *     Fixed class and file headers to match 20001 standards
 *
 */

/**
 * A subclass of SortableIntArray that sorts integers using a
 * selection sort.

 * @author     Paul Tymann
 */

public class SelectionSortableIntArray extends SortableIntArray {

    /**
     * Create a SelectionSortableIntArray of the given size.
     *
     * @param    size    the size of the collection.
     */

    public SelectionSortableIntArray( int size ) {
	super( size );
    }

    /**
     * Sort the integers in the collection using a selection sort.
     */

    public void sort() {
	int min;

        // Selection sort

	for ( int i = 0; i < getSize() - 1; i++ ) {
	    min = i;

            // Look for the smallest element after the element at
            // position i

	    for ( int j = i + 1; j <= getSize() - 1; j++ ) {
		if ( getElementAt( j ) < getElementAt( min ) ) {
                    min = j;
                }
            }

            // Put the next smallest element in the correct spot
            // (i.e. swap it with the element at position i)

	    int tmp = getElementAt( min );
	    setElementAt( min, getElementAt( i ) );
	    setElementAt( i, tmp );
	}
    }

} // SelectionSortableIntArray
