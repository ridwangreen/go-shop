/**
 * LocalPlayer.java
 *
 * Version:
 *   $Id: LocalPlayer.java,v 1.1 2002/10/22 21:12:52 se362 Exp $
 *
 * Revisions:
 *   $Log: LocalPlayer.java,v $
 *   Revision 1.1  2002/10/22 21:12:52  se362
 *   Initial creation of case study
 *
 */


/**
 *  This class inherits from the player. 
 *  This class identifies that the local player 
 *  is the second player in the game.
 *
 *  @author
 */

public class LocalPlayer extends Player {
    
    /**
     * This is a default constructor for this object
     */
    public LocalPlayer( int num, Driver theDriver ){
    	super( num, theDriver );
    }
    
    /**
     * When the current player clicks the draw button, this method 
     * is called in the opposite player to inform them that a draw 
     * has been offered. An actionEvent is generated to let the GUI 
     * know.
     */
    public void offerDraw(){
    	
    	int selected = theDriver.displayMessageWithResult("Draw offer", getName() + " has requested a draw."
    			+ "\n\nWill you agree to a draw?");
	
    	if ( selected == YES ) {
    		theDriver.endInDraw();
    	} else if ( selected == NO ) {
    		theDriver.declineDraw();
    	} else {
    		theDriver.declineDraw();
    	}
    }
    

    
    /**
     *  Method is invoked if the other player declines a draw.
     */
    public void declineDraw(){
	
    	theDriver.displayMessage("Draw Declined", "The draw offer was declined."
    			+ "\n\nClick OK to continue the game.");
    	
    }
    
    /**
     * Method that is invoked when the end of game conditions have 
     * been met.  Fire off an action event to tell the GUI to display 
     * endMessage in a dialogue box.  When the user clicks OK, call 
     * endGame in theDriver.
     *
     * @param endMessage  Message indicating the end of the game.
     */
    public void endOfGame( String endMessage ){
	
    	theDriver.displayMessage("Game Over", endMessage);
	
    	// TODO: Don't system.exit...
    	System.exit( 0 );
	
    }
    
}//LocalPlayer.java

