/**
 * Move.java
 *
 * Version:
 *    $Id: Move.java,v 1.1 2002/10/22 21:12:52 se362 Exp $
 *
 * Revisions:
 *    $Log: Move.java,v $
 *    Revision 1.1  2002/10/22 21:12:52  se362
 *    Initial creation of case study
 *
 */

import java.awt.Color;

/**
 * An object representation of a move.
 *
 * @author
 */
 public class Move {
	 
	private int startingLocation;	// the starting location
	private int endingLocation;	// the ending location

	/**
	 *  The player that this move is intended for.
	 */
	private Color theColor;

     
	/**
	 * Create a move with the starting location and 
	 * ending location passed in as paremeters.
	 *	
	 * @param startLoc The starting point of the move
	 * @param endLoc   The ending point of the move
	 * 
	 * @pre startLoc and endLoc are valid locations
	 */
	public Move( Color playerColor, int startLoc, int endLoc ) {
	
		// TODO: Use of Player, remove
		theColor = playerColor;
		startingLocation = startLoc;
		endingLocation = endLoc;
	}

     
	/**
	 * Return the player who made this move
	 * 
	 * @return the player who made this move
	 * 
	 * @post nothing has changed 
	 * 
	 */
	public Color getColor() {
		return theColor;
	}

     
	/**
	 * Return the starting location of this move.
	 *
	 * @return The starting point of the move.
	 * 
	 * @post nothing has changed
 	 */
	public int startLocation() {
	
		return startingLocation;
	}

     
	/**
	 * Return the ending location of this move.
	 *
	 * @return The ending point of this location.
	 * 
	 * @post Nothing has changed
	 */
	public int endLocation() {
		
		return endingLocation;
	}
     
} //Move.java
