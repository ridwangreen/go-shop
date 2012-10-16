package system;

import java.awt.Color;

import GUI.PegUI;

/*
 * @File Peg.java
 * 
 * @Authors Alex Kahn, John Neville, Alex Canter, Becca Dudley
 * 
 * @Class Description this is the class that will deal with the logical Peg
 */
public class Peg {
	// A feedback peg which marks a guess as CORRECT
	public static final Peg CORRECT = new Peg(PegColor.BLACK);
	// A feedback peg which marks a guess as INCORRECT
	public static final Peg INCORRECT = new Peg(PegColor.WHITE);
	public PegUI myUI; //The PegUI representation of this peg
	
	private PegColor pegColor; // the color of this peg
	
	/**
	 * The default constructor for a peg.  
	 * Default color is gray
	 */
	public Peg() {
		pegColor = PegColor.NEUTRAL;
	}
	
	/**
	 * A constructor for a Peg which takes a PegColor
	 */
	public Peg(PegColor color) {
		pegColor = color;
	}
	
	/**
	 * returns the color of the peg
	 * 
	 * @return pegColor  the color of the peg
	 */
	public PegColor getPegColor() {
		return pegColor;
	}
	
	/**
	 * sets the color of the peg when not called by UI
	 * 
	 * @param newColor   the color to set the peg to
	 */
	public void setPegColor(PegColor newColor) {
		pegColor = newColor;
		if(myUI != null){
			myUI.setColor(newColor);
		}
	}
	
	/**
	 * sets the color of the peg when called by UI
	 * 
	 * @param newColor   the color to set the peg to
	 */
	public void uiSetPegColor(PegColor newColor) {
		pegColor = newColor;
	}
	
	public java.awt.Color getUIColor() {
		Color result = Color.getColor(pegColor.name());
		return result;
	}
	
	/**
	 * Checks two pegs for equality by comparing their colors.
	 * @see java.lang.Object.equals(Object)
	 * @param other The Peg to compare for equality
	 * @return true if equal, false otherwise.
	 */
	public boolean equals(Peg other) {
		return (this.pegColor.equals(other.pegColor));
	}
	
	public void setUI(PegUI ui){
		myUI = ui;
	}
	
}
