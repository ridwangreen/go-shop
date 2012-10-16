/**
 * SinglePiece.java
 *
 * Version:
 *   $Id: SinglePiece.java,v 1.1 2002/10/22 21:12:53 se362 Exp $
 *
 * Revisions:
 *   $Log: SinglePiece.java,v $
 *   Revision 1.1  2002/10/22 21:12:53  se362
 *   Initial creation of case study
 *
 */

import java.awt.*;
import java.util.TreeMap;

/**
 * This is a class representing a single piece (a piece that has not been
 * kinged yet)
 *
 * @author
 *
 */
public class SinglePiece extends Piece {
	
	
   private final int SINGLE = 0; // this is a single type
   private int type; // the type of the piece
   
   /**
    * This constructor creates a single piece checker object
    * 
    * @param c - the color of this single piece
    * @param e - the color of the opposite player
    */
   public SinglePiece( Color c, Color e  ) {
 
	    super( c, e );
		type = SINGLE;
   }
   
   /**
    * This method returns that the type of the checker is single
    * 
    * @return type which is 0 for single
    */
   public int getType() {
 
	   return type;
   }
   
   /**
    * Returns true if the given offset is allowed in the when this piece is moved to
    * an adjacent square
    * 
    * @param i   the offset in the vertical direction when the piece is moved
    * @return    true if the offset makes a legal move
    * 
    */
   public boolean validSingleMove(int i)
   {
	   boolean retval = false;
	   
	   // Single white pieces can only move down
	   if(getColor() == Color.white)
	   {
		   if(i == -1)
		   {
			   retval = true;
		   }
	   }
	   // Single blue pieces can only move up
	   else if(getColor() == Color.blue)
	   {
		   if(i == 1)
		   {
			   retval = true;
		   }
	   }
	   
	   return retval;
   }
   
   /**
    * Returns true if the given offset is allowed when this piece is moved
    * for a jump
    * 
    * @param i   the offset in the vertical direction when the piece is moved
    * @return    true if the offset makes a legal jump
    */
   public boolean validJump(int i)
   {
   boolean retval = false;
	   
	   // Single white pieces can only move down
	   if(getColor() == Color.white)
	   {
		   if(i == -2)
		   {
			   retval = true;
		   }
	   }
	   // Single blue pieces can only move up
	   else if(getColor() == Color.blue)
	   {
		   if(i == 2)
		   {
			   retval = true;
		   }
	   }
	   
	   return retval;
   }
   
   /**
    * Check if the piece can make any legal jumps, noting that single pieces
    * can only move in one direction
    * 
    * @param   the set of neighbors surrounding this piece
    * @return  true if there is at least one legal jump
    */
   public boolean checkPossibleJumps(TreeMap<String, Piece> neighbors)
   {
	   // If the color of the piece is white, move down and
	   // check for jumping blue pieces
	   if(getColor() == Color.WHITE)
	   {
		   // Check that there is a space in that direction, check that there is a piece there, check that the piece is the enemy color
		   if((neighbors.containsKey("lowerLeft")) && (neighbors.get("lowerLeft") != null) && (neighbors.get("lowerLeft").getColor() == getEnemyColor()))
		   {
			   // Check that there is a space in that direction, check that it is empty
			   if(neighbors.containsKey("farLowerLeft") && neighbors.get("farLowerLeft") == null)
			   {
				   return true;
			   }
		   }
		   
		   if((neighbors.containsKey("lowerRight")) && (neighbors.get("lowerRight") != null) && (neighbors.get("lowerRight").getColor() == getEnemyColor()))
		   {
			   if(neighbors.containsKey("farLowerRight") && neighbors.get("farLowerRight") == null)
			   {
				   return true;
			   }
		   }
	   }
	   // Otherwise, if the color is blue, move up and 
	   // check for jumping white pieces
	   else
	   {
		   if((neighbors.containsKey("upperLeft")) && (neighbors.get("upperLeft") != null) && (neighbors.get("upperLeft").getColor() == getEnemyColor()))
		   {
			   if(neighbors.containsKey("farUpperLeft") && neighbors.get("farUpperLeft") == null)
			   {
				   return true;
			   }
		   }
		   
		   if((neighbors.containsKey("upperRight")) && (neighbors.get("upperRight") != null) && (neighbors.get("upperRight").getColor() == getEnemyColor()))
		   {
			   if(neighbors.containsKey("farUpperRight") && neighbors.get("farUpperRight") == null)
			   {
				   return true;
			   }
		   }
	   }
	   
	   // Return false if no direction had a jump
	   return false;
   }
   
}// SinglePiece
