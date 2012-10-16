package GUI;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

import system.Peg;
import system.PegColor;
import Controller.ChangePegListener;

/*
 * @File PegUI.java
 * 
 * @Authors Becca Dudley
 * 
 * @Class Description the GUI representation of the peg.
 * 				it has a reference to the logical peg it is associated with
 */
public class PegUI extends JButton{
	
	private ColorImage color; //the color image of the peg
	private int size; //the size of the peg (guess pegs are 50, feedback 25)
	private Peg gamePeg;//the logical peg associated with the PegUI
	private ChangePegListener cpl;//the listener for the pegs
	
	/**
	 * Constructor for the PegUI
	 * 
	 * @param color   the color of the Peg 
	 * @param size    the size of the peg (guess pegs are 50, feedback 25)
	 */
	public PegUI(Peg gamePeg, int size){

		this.gamePeg = gamePeg;
		this.size = size;
		cpl = new ChangePegListener();
		setPreferredSize(new Dimension(size, size));
		color = new ColorImage(PegColor.NEUTRAL, size);
		setIcon(color);
		super.setBackground(Color.LIGHT_GRAY);
		
	}
	
	/**
	 * Set the color of the PegUI
	 * This also sets the color of the logical peg
	 * associated with the PegUI
	 * 
	 * @param newColor the color to set the PegUI and Peg to
	 */
	public void setColor(PegColor newColor){

		color = new ColorImage(newColor, size);
		setIcon(color);
		gamePeg.uiSetPegColor(color.getColor());
	}
	
	/**
	 * Getter for the Color of the peg
	 * 
	 * @return the color of the PegUI
	 */
	public PegColor getColor(){
		
		return color.getColor();
	}

	/**
	 * Getter for the logical representation of the peg
	 * 
	 * @return the logical Peg associated with the PegUI
	 */
	public Peg getPeg(){
		
		return gamePeg;
	}
	
	/**
	 * Getter for the size of the PegUI
	 * 
	 * @return the size (of one side) of the PegUI
	 */
	public int getPegSize(){
		
		return size;
	}
	
	/**
	 * Sets the PegUI to its "default" light gray color
	 */
	public void reset(){
		
		this.setIcon(new ColorImage(PegColor.NEUTRAL, size));
	}
	
	/**
	 * overrides the JButton setEnabled by instead of the button becoming
	 * "unclickable" it has an actionlistener given or taken away based
	 * on whether or not it is enabled
	 * 
	 * When enabled guesspegs are set to red
	 *  and feedback pegs are set to Light Gray
	 */
	public void setEnabled(boolean isEnabled){
		
		if(size == 50){
			if(isEnabled){
				setIcon(new ColorImage(PegColor.RED, size));
				addActionListener(cpl);
			}else{
				removeActionListener(cpl);
			}
		}	
	}
}
