/**
 * Player.java
 *
 * Version:
 *    $Id: Player.java,v 1.1 2002/10/22 21:12:53 se362 Exp $
 *
 * Revisions:
 *    $Log: Player.java,v $
 *    Revision 1.1  2002/10/22 21:12:53  se362
 *    Initial creation of case study
 *
 */

import java.awt.*;
		   
/**
*  A class representation of the Player object.  This object
*  contains the methods needed when one of the users clicks
*  on one of the buttons within the GUI.
*
*  @author
*/

public abstract class Player {
    
    protected final int YES = 0;
    protected final int NO = 1;
    
    protected Driver theDriver;
    
    // Instance of the move class which will be
    // created when a user makes a move.
    protected Move   theMove;	
    
    protected int    playerNumber;
    protected String playerName;
    protected Color  playerColor;
    
    /**
     * Create a new instance of a Player object to represent
     * one of the users.
     * 
     * @param num       The number of the player.
     * @param newRules  The rules used to validate moves.
     * @param newDriver Driver which will control this.     
     */
    public Player( int num, Driver newDriver ){
    	playerName   = null;
    	playerColor  = null;
    	playerNumber = num;
    	theDriver    = newDriver;
    }
       
    /**
     * This method is used for when a user has clicked on the 
     * "Quit" button on the GUI.  It handles exiting  the game.
     * 
     * @param the player who quit
     * @pre  game is in progress
     * @post message is sent to driver to end the game
     */
    public abstract void endOfGame( String message );
    
    /**
     * When the current player clicks the draw button, this method
     * is called in the opposite player to inform them that a draw 
     * has been offered.  This method is implemented differently for 
     * localPlayer and networkPlayer. 
     * 
     * @pre a game is in progress
     * @pre a draw has been offered
     */
    public abstract void offerDraw(); 
    
    /**
     * When the current player accepts a draw, this method is called 
     * in the opposite player to inform them that the draw has been 
     * accepted.  This method is implemented differently for localPlayer 
     * and networkPlayer. 
     * 
     * @pre a game is in progress
     * @pre a draw has been accepted
     */
    public void acceptDraw() {};
    
    /**
     *  Method is invoked if the other player declines a draw.
     *  It displays the dialog box for the decline of draw
     */
    public abstract void declineDraw();
    
    /**
     * Returns the number for this player
     * 
     * @pre the player has a number
     * 
     * @return playerNumber
     */
    public int getNumber(){
	return playerNumber;
    }
    
    /**
     * Returns the players name
     * 
     * @pre the player has a name
     * 
     * @return the players name
     */
    public String getName(){
	return playerName;
    }
    
    /**
     * Sets the players name
     * 
     * @param the name to be set
     */
    public void setName( String name ){
	playerName = name;
    }
    
    /**
     * Return the color of this player
     * 
     * @return the color of this player
     */
    public Color getColor() {
	return playerColor;
    }
    
    /**
     * Set the color for this player.
     * 
     * @param newColor The new color for this player.
     */
    public void setColor( Color newColor ){
	playerColor = newColor;
    }
    
    /**
     * A string representation of this object.
     * 
     * @return a String representation of this object.
     */
    public String toString(){
        return ("Player.  name = " + playerName);
    }
    
    /**
     * A default implementation for sending moves to another location.
     */
    public void sendMove(){}
    
}//Player.java
