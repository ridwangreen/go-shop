/*
 * FrenzyButton.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */

import java.awt.Button;
import javax.swing.JButton;

/**
 * The buttons to be used in the Frenzy game
 *
 * @author Rebecca Dudley
 */

public class FrenzyButton extends JButton{
	
	private int xVal;
	private int yVal;
	
	/**
	 * create a new button that knows its location
	 * 
	 * @param x   the x corrdinate
	 * @param y   the y coordinate
 	 */
	public FrenzyButton(int x, int y){
	
		super();
		xVal = x;
		yVal = y;
		
	}
	
	/**
	 * accessor for the x value
	 * 
	 * @return  the x value
	 */
	public int getXVal(){
		return xVal;
	}
	
	/**
	 * accessor for the the y value
	 * 
	 * @return the y value
	 */
	public int getYVal(){
		return yVal;
	}
	
}
