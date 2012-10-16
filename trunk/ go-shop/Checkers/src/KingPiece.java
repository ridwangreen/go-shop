/**
 * KingPiece.java
 *
 * Version:
 *   $Id: KingPiece.java,v 1.1 2002/10/22 21:12:52 se362 Exp $
 *
 * Revisions:
 *   $Log: KingPiece.java,v $
 *   Revision 1.1  2002/10/22 21:12:52  se362
 *   Initial creation of case study
 *
 */
import java.awt.Color;
import java.util.TreeMap;

/**
 * This is a class representing a king piece (a piece that has been kinged)
 *
 * @author
 *
 */
public class KingPiece extends Piece {

   private final int KING = 1; // the king type
   private int type; // the type of his object
  
   /**
    * This constructor creates a king piece object
    * 
    * @param c - the color of this king piece
    * @param e - the color of the opposite player
    */
   public KingPiece( Color c, Color e ) {
   
	   super( c, e ); 
	   type = KING;
   }
   
   /**
    * This method returns the type of piece that this object is 
    * 
    * @return 1 for the king piece representation
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
	   return ((i==1) || (i==-1));
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
	   return ((i==2) || (i==-2));
   }
   
   /**
    * Check if the piece can make any legal jumps, noting that king pieces
    * can move in either direction
    * 
    * @param   the set of neighbors surrounding this piece
    * @return  true if there is at least one legal jump
    */
   public boolean checkPossibleJumps(TreeMap<String, Piece> neighbors)
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
	   
	   return false;
   }
   
}//KingPiece
