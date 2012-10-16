/*
 * TestSearchers.java
 *
 * Version:
 *     $Id: TestSearchers.java,v 1.1 2010/09/24 15:37:30 rcd1575 Exp rcd1575 $
 *
 * Revisions:
 *     $Log: TestSearchers.java,v $
 *     Revision 1.1  2010/09/24 15:37:30  rcd1575
 *     Initial revision
 *
 */

/**
 * this program tests the searchers program
 *
 * @author   Becca Dudley
 *
 */

public class TestSearchers{

    public static void main (String args[]){


        for(int x = 8; x < 65537; x *= 2){

            Integer ints[] = new Integer[x];
            for(int i = 0; i < ints.length; i++){

                ints[i] = 2 * (Integer)x;

            }


            Searchers.linearSearch(ints, 3);
            int lin = Searchers.getNumCompares();
            Searchers.resetNumCompares();
            Searchers.binarySearch(ints, 3);
            int bin = Searchers.getNumCompares();
            Searchers.resetNumCompares();
            Searchers.ternarySearch(ints, 3);
            int tern = Searchers.getNumCompares();
            Searchers.resetNumCompares();

            System.out.println(" N = " + x + ", Linear == " + lin +
                               ", Binary == " + bin + ", Ternary == " + tern);

        }

    }
}