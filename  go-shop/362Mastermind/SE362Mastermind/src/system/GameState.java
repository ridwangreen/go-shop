package system;

import java.util.Observable;
import java.util.Stack;

import Command.Command;
import Command.EndGame;
import Command.Feedback;
import Controller.GameController;

/*
 * @File GameState.java
 * 
 * @Authors Alex Kahn, John Neville, Alex Canter, Becca Dudley
 * 
 * @Class Description:
 * DESIGN PATTERN INFORMATION:
 * - GameState is a Singleton. 
 * - GameState keeps the Commands sent via the Command Pattern in a
 *   Stack, to supply undo functionality.
 * - GameState is an Observable. Other classes need to know when the
 *   state of the game has changed. GameState will indirectly observe
 *   the list of Command Objects for changes, and notifty all Observers.
 * 
 * OVERVIEW:
 * The concept of a "GameState is not inherently needed for just MasterMind;
 * it coincides with our philosophy of designing defensively for future
 * releases. More specifically, a GameState adds an additional level of
 * abstraction, to make the implementation of additional games and/or 
 * features more trivial in the future.
 */

public class GameState extends Observable {
	private static GameState instance = null; // The Singleton instance
	private Stack<Command> commandHistory; // A Command stack
	private int turn; // The current Turn in the GameState
	private Peg guessPegs[][]; // all the (logical)pegs used for guesses
	private Peg feedbackPegs[][]; // all the (logical)pegs used for feedback

	/**
	 * Protected, Singleton constructor. No outside Object is allowed to create
	 * a new GameState. THERE CAN ONLY BE ONE!!!
	 */
	protected GameState() {
		commandHistory = new Stack<Command>();
		addObserver(LogHandler.getInstance());

		createNewGame();
	}

	/**
	 * Implementation of Singleton's getInstance()
	 * 
	 * @return The GameState instance if one exists, a new GameState instance
	 *         otherwise.
	 */
	public static GameState getInstance() {
		if (instance == null) {
			instance = new GameState();
		}
		return instance;
	}

	/**
	 * Getter for the Command list
	 * 
	 * @return The commandHistory List
	 */
	public Stack<Command> getCommandHistory() {
		return commandHistory;
	}

	/**
	 * Returns the last Command executed
	 * 
	 * @return The last Command
	 */
	public Command getLastCommand() {
		return commandHistory.peek();
	}

	/**
	 * Clear the Command history.
	 */
	public void clearCommands() {
		commandHistory.clear();
		commandHistory.push(new EndGame());
	}

	/**
	 * Add a new command to the top of the command history
	 * 
	 * @param cmd
	 *            The Command to add.
	 */
	public void pushCommand(Command cmd) {
		commandHistory.push(cmd);
		cmd.execute();
		setChanged();
		notifyObservers();
	}

	/**
	 * Get the last Feedback Command on the stack TO-DO: Remove this method from
	 * GameState, put it in a "MasterMindGameState" which extends GameState, to
	 * coincide more with the generic game framework idea.
	 * 
	 * @return The last Feedback Command if it exists, null otherwise.
	 */
	public Feedback getLastFeedback() {
		Feedback lastFeedback = null;
		for (Command cmd : commandHistory) {
			if (cmd instanceof Feedback) {
				lastFeedback = (Feedback) cmd;
				break;
			}
		}
		return lastFeedback;
	}

	/**
	 * Checks to see if this GameState is a terminal state. A state is terminal
	 * iff: 1. All of CodeBreaker's guesses have been used and the correct
	 * answer has not been made, or 2. CodeBreaker has guessed correctly.
	 * 
	 * @return true if the state is terminal, false otherwise.
	 */
	public boolean isTerminalState() {
		boolean result = false; // Assume false, try and prove true.
		if (turn > 10) {
			result = true;
		} 
//			else if (feedback[0].equals(feedback[1])
//				&& feedback[1].equals(feedback[2])
//				&& feedback[2].equals(feedback[3])
//				&& feedback[3].equals(Peg.CORRECT)) {
//			// There are still turns left, and CodeBreaker has
//			// guessed correctly.
//			result = true;
//		}
		return result;
	}

	/**
	 * Getter for the current Turn of the GameState
	 * 
	 * @return The current Turn
	 */
	public int getTurn() {
		return turn;
	}

	/**
	 * Manually set the currentTurn of the GameState. This will likely not be
	 * needed, but will potentially be useful in the future.
	 * 
	 * @param turn
	 *            The new Turn object to set as the current Turn.
	 */
	public void setTurn(int turn) {
		this.turn = turn;
	}

	public void upTurnCount() {
		turn++;
	}

	/**
	 * Getter for the current logical representation of the Pegs on the Board
	 * used for guesses.
	 * 
	 * @return The Pegs array
	 */
	public Peg[][] getGuessPegs() {
		return guessPegs;
	}

	/**
	 * Getter for the current logical representation of the Pegs on the Board
	 * used for feedback
	 * 
	 * @return
	 */
	public Peg[][] getFeedbackPegs() {
		return feedbackPegs;
	}

	/**
	 * Starts a new game with the specified Peg layout.
	 * 
	 * @param guessPegs
	 *            The Peg layout for guesses, as a 2D array of Pegs
	 * @param feedbackPegs
	 *            The Pegs for the feedback
	 */
	public void createNewGame() {
		
		guessPegs = new Peg[11][4];
		feedbackPegs = new Peg[10][4];
		for (int r = 0; r < 11; r++) {
			for (int c = 0; c < 4; c++) {
				if (r == 10) {
					guessPegs[r][c] = new Peg();
				} else {
					feedbackPegs[r][c] = new Peg();
					guessPegs[r][c] = new Peg();
				}
			}
		}
		turn = 0;
	}

	/**
	 * @see java.lang.Object.toString() Utility method to aid in logging of
	 *      Commands in the LogHandler.
	 */
	public String toString() {
		String stateList = "";
		for (Command ele : commandHistory) {
			stateList.concat(ele.toString());
		}
		return stateList;
	}

}
