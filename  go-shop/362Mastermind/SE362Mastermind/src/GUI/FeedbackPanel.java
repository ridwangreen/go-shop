package GUI;

import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

import system.Peg;
import system.PegColor;

/*
 * @File FeedbackPanel.java
 * 
 * @Authors Becca Dudley
 * 
 * @Class Description the panel for containging the feedback pegs
 */
public class FeedbackPanel extends JPanel{
	
	private PegUI feedbackUIPegs[][];//the feedback UIPegs taken from the board
	private Peg feedbackPegs[][];//the logical feedback pegs from the board
	private JButton hide;//button to hide the code pegs
	private final int SMALL_PEG = 25;
	private final int ROW_SIZE = 10;
	private final int COL_SIZE = 4;
	
	/**
	 * Creates PegUI's from the feedback pegs given
	 * Aligns the pegUI's in 10 rows of 4X4 blocks
	 * 
	 * @param feedbackPegs  the array feedbackPegs from the board
	 */
	public FeedbackPanel(Peg feedbackPegs[][]){
		
		feedbackUIPegs = new PegUI[ROW_SIZE][COL_SIZE];
		this.feedbackPegs = feedbackPegs;
		setLayout(new GridLayout(11,1));
		for(int x = 0; x < ROW_SIZE; x++){
			JPanel square = new JPanel();
			square.setLayout(new GridLayout(2,2));
			for(int y = 0; y < COL_SIZE; y++){
				PegUI peggy = new PegUI(this.feedbackPegs[x][y], SMALL_PEG);
				feedbackPegs[x][y].setUI(peggy);
				peggy.setEnabled(false);
				feedbackUIPegs[x][y] = peggy;
				square.add(peggy,y);
				
			}
			add(square, x, 0);
		}
		hide = new JButton("Hide");
		hide.setMargin(new Insets(1, 1, 1, 1) );
		add(hide, ROW_SIZE, 0);
		setVisible(true);
	}
	
	/**
	 * getter for the PegUIs
	 * 
	 * @return the pegUI's created for this panel
	 */
	public PegUI[][] getButtons(){
		
		return feedbackUIPegs;
	}
	
	/**
	 * sets all the PegUI's to their "default" gray color
	 */
	public void reset(){
		for(int r = 0; r < ROW_SIZE; r++){
			for(int c = 0; c < COL_SIZE; c++){
				feedbackUIPegs[r][c].reset();
			}
		}
	}
	
	/**
	 * getter for the hide button so the board can give it an
	 * actionlistener
	 * 
	 * @return the hide button
	 */
	public JButton getHideButton(){
		return hide;
	}
}
