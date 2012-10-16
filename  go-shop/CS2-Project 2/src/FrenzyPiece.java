import java.awt.Color;

/*
 * FrenzyPiece.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */

/**
 * The super class for the pieces in the Frenzy game
 *
 * @author Rebecca Dudley
 */
public abstract class FrenzyPiece {

	protected int x;
	protected int y;
	protected int boardWidth;
	
	public FrenzyPiece(int x, int y, int boardWidth){
		
		this.x = x;
		this.y = y;
		this.boardWidth = boardWidth;
	}
	
	/**
	 * accessor method for the x value
	 * 
	 * @return the x value
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * the accessor method for the y value
	 * 
	 * @return   the y value
	 */
	public int getY(){
		return y;
	}
	
	public boolean eat(FrenzyPiece piece){
		
		return !(piece instanceof BlankPiece);
			
	}
	
	public abstract String getText();
	
	public abstract Color getColor();
	
	public abstract void die();
}
