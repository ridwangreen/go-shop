/*
 * Woolie.java
 *
 * $Id: Woolie.java,v 1.2 2010/09/10 15:28:01 rcd1575 Exp rcd1575 $
 *
 * Revision:
 *    $Logs$
 */

/**
 * The Woolie class.  Woolies want to fight!!!!!  They are given rankings based 
 * on seniority.  Older = mo' better
 *
 * @@author Becca Dudley
 */


public class Woolie implements Comparable<Woolie>{


    private Woolie.Ranking rank; //the woolies rank
    public static final int NUM_RANKS = 8;  // the total number of rankings
    private static int id = 1;  //a counter to keep track of the # of woolies
    private int myId;  //the unique id for the woolie


    /**
     * Creates a new woolie and brands it with rank
     *
     * @@param Woolie.Ranking rank    the Woolies rank
     */
    public Woolie(Woolie.Ranking rank){

        myId = id;
        this.rank = rank;
        id++;

    }

    public enum Ranking {
        
	PRIVATE, CORPORAL, SERGEANT, LIEUTENANT, CAPTAIN, MAJOR,
        COLONEL, GENERAL
    };

    /**
     * accesor for the woolies rank
     *
     * @@return the woolies rank
     */
    public Woolie.Ranking getRank(){

        return rank;

    }

    /**
     * accessor for the woolies id
     *
     * @@return this woolies id
     */
    public int getId(){

        return myId;

    }

    /**
     * returns a string in the form {MILITARY_RANK} Woolie({id})
     *
     * @@return the string representation of the woolie
     */
    public String toString(){

        return rank + " Woolie(" + myId + ")";

    }

    /**
     * Compares two woolies firs by rank and then by id(lowest to highest)
     *
     * @@returns less than 0 if if this woolie is less than the other, greater
     * than 0 if this woolie is greater than the other and 0 if they are the
     * same woolie
     */
    public int compareTo(Woolie other){

        if(!(rank.compareTo(other.getRank()) == 0)){

            return rank.compareTo(other.getRank());

        }
        return other.getId() - myId;

    }
    
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Woolie w1 = new Woolie(Woolie.Ranking.PRIVATE);
		Woolie w2 = new Woolie(Woolie.Ranking.CORPORAL);
		Woolie w3 = new Woolie(Woolie.Ranking.GENERAL);
		Woolie w4 = new Woolie(Woolie.Ranking.CAPTAIN);
		Woolie w5 = new Woolie(Woolie.Ranking.PRIVATE);
		
		System.out.println(w1);
		System.out.println(w2);
		System.out.println(w3);
		System.out.println(w4);
		
		if(w1.compareTo(w2) < 0){
			System.out.println("test1 " + w2);
		}else{
			System.out.println("Test1 " + w1);
		}

		if(w1.compareTo(w5) < 0){
			System.out.println("test2 " + w5);
		}else{
			System.out.println("test2 " + w1);
		}
		
		if(w3.compareTo(w4) < 0){
			System.out.println("test2 " + w4);
		}else{
			System.out.println("test2 " + w3);
		}

	}


}