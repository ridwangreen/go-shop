/*
 * PowerComparator.java
 *
 * Version:
 *     $Id: PowerComparator.java,v 1.1 2007/04/03 17:39:47 vcss233 Exp $
 *
 * Revisions:
 *     $Log: PowerComparator.java,v $
 *     Revision 1.1  2007/04/03 17:39:47  vcss233
 *     Initial revision
 *
 *     Revision 1.2  2007/03/23 14:20:51  vcss233
 *     removed tab chars
 *
 *     Revision 1.1  2007/03/23 14:10:20  vcss233
 *     Initial revision
 *
 */

import java.util.*;

/**
 * This comparator sorts subsets in a powerset by size (in ascending order).
 * If two subsets have equal size, then they are sorted lexicographically.
 *
 * @author    Minseok Kwon
 */ 

public class PowerComparator implements Comparator<String> {

    /**
     * Compare two subsets based on their length (primary) and
     * alphabetical order (secondary).
     *
     * @param    first    first subset
     * @param    second   second subset
     *
     * @return  returns -1 if the length of first is less than
     *          the length of second; returns 1 if the length
     *          of first is greater than the length of second;
     *          if two subsets have the same length, compare
     *          alphabetically using the compareTo of String;
     *          that is, a negative integer, zero, or a positive
     *          integer as the string of first is greater than,
     *          equal to, or less than the string of second.
     */
    public int compare( String first, String second ) {
        if ( first.length() < second.length() ) {
            return -1;
        } else if ( first.length() > second.length() ) {
            return 1;
        } else {
            return first.compareTo(second);
        }
    }
}
