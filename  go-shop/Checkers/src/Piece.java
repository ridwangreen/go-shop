/**
 * Piece.java
 *
 * Version:
 *   $Id: Piece.java,v 1.1 2002/10/22 21:12:53 se362 Exp $
 *
 * Revisions:
 *   $Log: Piece.java,v $
 *   Revision 1.1  2002/10/22 21:12:53  se362
 *   Initial creation of case study
 *
 */

/**
 * This is an abstract class representing any piece that
 * know about it's color and possible moves and captures
 *
 * @author
 *
 */

import java.awt.*;
import java.util.TreeMap;

public abstract class Piece {
	
   private Color myColor; // the color of the piece
   private Color enemyColor; // the color of the enemy piece

      
   /**
    * The constructor for this piece
    * 
    * @param c - the color for this piece
    * @param e - the color for the enemy piece
    */
   public Piece( Color c, Color e ) {

	   // set the color
	   myColor = c;
	   enemyColor = e;
   }

   /**
    * The method which is abstract
    * 
    * @return the type of the piece
    */
   abstract int getType();
   
   /**
    * This method returns the color of this piece
    * 
    * @return the color for this piece
    */
   public Color getColor() {
  
	   return myColor;
   }
   
   /**
    * This method returns the color of this piece's enemy
    * 
    * @return the color for this piece's enemy
    */
   public Color getEnemyColor()
   {
	   return enemyColor;
   }

   /**
    * Returns true if the given offset is allowed when this piece is moved to
    * an adjacent square
    * 
    * @param i   the offset in the vertical direction when the piece is moved
    * @return    true if the offset makes a legal move
    * 
    */
   public abstract boolean validSingleMove(int i);
   
   /**
    * Returns true if the given offset is allowed when this piece is moved
    * for a jump
    * 
    * @param i   the offset in the vertical direction when the piece is moved
    * @return    true if the offset makes a legal jump
    */
   public abstract boolean validJump(int i);
   
   /**
    * Check if the piece can make any legal jumps
    * 
    * @param   the set of neighbors surrounding this piece
    * @return  true if there is at least one legal jump
    */
   public abstract boolean checkPossibleJumps(TreeMap<String, Piece> neighbors);
   
}// Piece
