import java.awt.Color;

/*
 * Player.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */


/**
 * Model portion of the Frenzy game
 *
 * @author Rebecca Dudley
 */

public class Player extends FrenzyPiece {

	private FrenzyModel model;
	
	/**
	 * creates a new player at position x,y
	 * 
	 * @param x   the x coordinate
	 * @param y   the y coordinate
	 * @param boardWidth   the size of the board
	 */
	public Player(int x, int y, int boardWidth, FrenzyModel model){
		
		super(x, y, boardWidth);
		this.model = model;
		
	}
	
	/**
	 * moves the player up
	 */
	public void moveUp(){
		
		if(y <= 0){
			y = boardWidth - 1;
		}else{
			y--;
		}
	}
	
	/**
	 * move player down
	 */
	public void moveDown(){
		
		if(y >= boardWidth - 1){
			y = 0;
		}else{
			y++;
		}
	}
	
	/**
	 * move the player right
	 */
	public void moveRight(){
		
		if(x >= boardWidth - 1){
			x = 0;
		}else{
			x++;
		}
	}
	
	/**
	 * move the player left
	 */
	public void moveLeft(){
		
		if(x <=0){
			x = boardWidth - 1;
		}else{
			x--;
		}
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return Color.YELLOW;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return "P";
	}
	
	public void die(){
		
		model.getView().updateGameStatus("GAME IS OVER!");
		model.setPaused(true);
		model.setResetEnabled();
		
	}
	
}
