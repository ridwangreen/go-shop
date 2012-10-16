package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import system.Peg;

/*
 * @File GuessPanel.java
 * 
 * @Authors Becca Dudley
 * 
 * @Class Description Panel for adding all the guess PegUIs
 */
public class GuessPanel extends JPanel{
	
	private PegUI codePegs[];//the pegUIs belonging to the code
	private PegUI guessUIPegs[][];//the pegUI's used for guesses
	private final int BIG_PEG = 50;
	private final int ROW_SIZE = 11;
	private final int COL_SIZE = 4;
	private Peg guessPegs[][];//all the logical pegs on this panel
	
	/**
	 * Creates a new guesspanel using the pegs given by the board
	 * They are arranged in 11 rows of 4 the top(row 10) row contains the
	 * code pegs
	 * 
	 * @param guessPegs
	 */
	public GuessPanel(Peg guessPegs[][]){
		
		this.guessPegs = guessPegs;
		codePegs = new PegUI[COL_SIZE];
		guessUIPegs = new PegUI[ROW_SIZE - 1][COL_SIZE];
		setLayout(new GridLayout(ROW_SIZE, COL_SIZE));
		for(int r = 0; r < ROW_SIZE; r++){
			for(int c = 0; c < COL_SIZE; c++){
				PegUI peggy = new PegUI(this.guessPegs[r][c],BIG_PEG);
				guessPegs[r][c].setUI(peggy);
				peggy.setEnabled(false);
				if(r == 10){
					codePegs[c] = peggy;
				}else{
					guessUIPegs[r][c] = peggy;
				}
				add(peggy, r, c);
			}
		}
		setVisible(true);
	}
	
	/**
	 * getter for the PegUIs in the code
	 * 
	 * @return the code PegUIs
	 */
	public PegUI[] getCodePegs(){
		
		return codePegs;
	}
	
	/**
	 * The getter for the pegUI's used for guesses
	 * 
	 * @return the guess PegUIs
	 */
	public PegUI[][] getGuessPegs(){
		
		return guessUIPegs;
	}
	
	/**
	 * Enabled/disables the code row of PegUI's
	 * 
	 * @param isEnabled whether or not the row is enabled
	 */
	public void setCodeEnabled(boolean isEnabled){
		
		for(int x = 0; x < COL_SIZE; x++){
			codePegs[x].setEnabled(isEnabled);
		}
	}
	
	/**
	 * Enables/disables a row of guess pegUI's
	 * 
	 * @param row the row to be disabled/enabled
	 * @param isEnabled whether or not the row is enabled
	 */
	public void setEnabledRow(int row, boolean isEnabled){
		
		for(int x = 0; x < COL_SIZE; x++){
			guessUIPegs[row][x].setEnabled(isEnabled);
		}
	}
	
	/**
	 * Hides or shows the code PegUI's
	 * 
	 * @param isHidden whether or not the code is hidden
	 */
	public void hideCode(boolean isHidden){
		
		for(int x = 0; x < COL_SIZE; x++){
			codePegs[x].setVisible(!isHidden);
		}
	}
	
	/**
	 * Sets all the PegUI's back to their "default" gray color
	 */
	public void reset(){
		
		for(int r = 0; r < ROW_SIZE - 1; r++){
			for(int c = 0; c < COL_SIZE; c++){
				guessUIPegs[r][c].reset();
			}		
		}
		for(PegUI peggy: codePegs){
			peggy.reset();
		}
	}
}
