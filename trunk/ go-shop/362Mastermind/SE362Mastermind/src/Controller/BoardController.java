package Controller;

import player.PlayerProxy;
import player.PlayerType;

import system.Peg;
import GUI.Board;

/*
 * @File BoardController.java
 * 
 * @Authors Becca Dudley
 * 
 * @Class Description the controller for the gameboard GUI
 * 				It updates the GUI based on what is happening in the game
 */
public class BoardController {

	private Board gameBoard;// The board GUI
	private PlayerProxy players; // the playerController of the game

	/**
	 * Constructor: Takes in pegs, makes new board
	 * 
	 * @param guessPegs
	 * @param feedbackPegs
	 * @param players
	 *            The PlayerController containing the current players
	 */
	public BoardController(Peg guessPegs[][], Peg feedbackPegs[][],
			PlayerProxy pa) {

		players = pa;
		gameBoard = new Board(guessPegs, feedbackPegs, players);
		gameBoard.setVisible(true);
	}

	public void endGame() {

		gameBoard.dispose();
	}

	public void newGame(int breaker, int maker) {

		gameBoard.setEnabledItem(0, true);
		gameBoard.setEnabledItem(1, true);
		gameBoard.setEnabledItem(2, false);

		if (breaker == PlayerType.AI.ordinal()) {
			gameBoard.setEnabledAI(true);
		}else{
			gameBoard.setEnabledAI(false);
		}
		if (maker == PlayerType.LOCAL.ordinal()) {
			gameBoard.setCodeEnabled(true);
			gameBoard.setOptionEnabled(2, true);
			gameBoard.setOptionEnabled(0, false);
		}
	}

	public void feedback(int turn) {

		gameBoard.setEnabledGuessRow(turn, true);
		gameBoard.setEnabledGuessRow(turn-1, false);
		gameBoard.setOptionEnabled(0, true);
		gameBoard.setOptionEnabled(1, true);
		gameBoard.setOptionEnabled(2, false);
	}

	public void setCode() {

		gameBoard.setCodeEnabled(false);
		gameBoard.setOptionEnabled(2, false);
		if (players.getBreakerType() == PlayerType.LOCAL) {

			gameBoard.setEnabledGuessRow(0, true);
			gameBoard.setOptionEnabled(0, true);
			gameBoard.setOptionEnabled(1, true);
		}
		gameBoard.hideCode(true);

	}

}
