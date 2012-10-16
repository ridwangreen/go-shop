package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import player.PlayerProxy;

import system.Peg;
import system.PegColor;

/*
 * @File Board.java
 * 
 * @Authors Becca Dudley
 * 
 * @Class Description this is the gui
 * 			This is used to place all the components together and make it easier
 * 			for the rest of the program to interact with the GUI
 */
public class Board extends JFrame {

	private GuessPanel guessPanel; // panel containing the guesses
	private FeedbackPanel feedbackPanel; // panel containing the feedback
	private JButton hideButton; // button to hide/show the code
	private BottomPanel bottomPanel; // panel containing the player options
	private GameMenu gameMenu;// file menu containing game options
	private boolean codeIsHidden; // whether or not the code is hidden

	/**
	 * Constructor for the board creates all the panels and puts them together
	 */
	public Board(Peg gamePegs[][], Peg feedbackPegs[][],
			PlayerProxy players) {

		guessPanel = new GuessPanel(gamePegs); // contains guesses and code
		feedbackPanel = new FeedbackPanel(feedbackPegs);// contains feedback and
		// hide button
		bottomPanel = new BottomPanel(players);// contains the player option
		// buttons
		gameMenu = new GameMenu(players);// file menu containing the game
		// options
		setLayout(new BorderLayout());
		add(gameMenu, BorderLayout.NORTH);
		add(guessPanel, BorderLayout.CENTER);
		add(feedbackPanel, BorderLayout.WEST);
		add(bottomPanel, BorderLayout.SOUTH);
		codeIsHidden = false;
		hideButton = feedbackPanel.getHideButton();
		hideButton.addActionListener(new ActionListener() { // Action listener
					// for
					@Override
					// hiding and showing
					public void actionPerformed(ActionEvent arg0) {// the code
						if (codeIsHidden) {
							hideButton.setText("Hide");
						} else {
							hideButton.setText("Show");
						}
						codeIsHidden = !codeIsHidden;
						hideCode(codeIsHidden);
					}
				});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}

	/**
	 * enables/disables the editing of the code
	 * 
	 * @param isEnabled
	 *            if the code is enabled or not
	 */
	public void setCodeEnabled(boolean isEnabled) {

		guessPanel.setCodeEnabled(isEnabled);
	}

	/**
	 * Sets a specified row of guesses (from 0 - 9) as enabled/disabled
	 * 
	 * @param row
	 *            int of the the row to be enabled/disabled
	 * @param isEnabled
	 *            boolean dictating whether or not the row is enabled
	 */
	public void setEnabledGuessRow(int row, boolean isEnabled) {

		guessPanel.setEnabledRow(row, isEnabled);
	}

	/**
	 * Hides/reveals the code pegs
	 * 
	 * @param isHidden
	 *            whether or not the code is hidden
	 */
	public void hideCode(boolean isHidden) {

		guessPanel.hideCode(isHidden);
		if(isHidden){
			feedbackPanel.getHideButton().setText("Show");
		}else{
			feedbackPanel.getHideButton().setText("Hide");	
		}
		codeIsHidden = isHidden;
	}

	/**
	 * enables one of the player option buttons 0 - make guess 1 - leave
	 * feedback 2 - undo move 3 - set code
	 * 
	 * @param buttonNumber
	 *            the number of the button.
	 * @param isEnabled
	 */
	public void setOptionEnabled(int buttonNumber, boolean isEnabled) {

		bottomPanel.setOptionEnabled(buttonNumber, isEnabled);
	}

	/**
	 * enables one of the game option buttons 0 - new game 1 - end Game
	 * 
	 * @param item
	 * @param isEnabled
	 */
	public void setEnabledItem(int item, boolean isEnabled) {

		gameMenu.setEnabledItem(item, isEnabled);
	}

	/**
	 * Sets the AI options as enabled/disabled
	 * 
	 * @param isEnabled
	 */
	public void setEnabledAI(boolean isEnabled) {

		gameMenu.setEnabledAI(isEnabled);
		bottomPanel.setBarEnabled(isEnabled);
	}

	/**
	 * clears all the pegs from the board in order to start a new game
	 */
	public void reset() {

		guessPanel.reset();
		feedbackPanel.reset();
		bottomPanel.reset();
	}
}
