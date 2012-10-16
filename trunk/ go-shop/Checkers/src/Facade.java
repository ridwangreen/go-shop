/**
 * Facade.java
 *
 * Version	
 *   $Id: Facade.java,v 1.1 2002/10/22 21:12:52 se362 Exp $
 *
 * Revisions:
 *   $Log: Facade.java,v $
 *   Revision 1.1  2002/10/22 21:12:52  se362
 *   Initial creation of case study
 *
 */

import java.awt.AWTEventMulticaster;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JOptionPane;

/**
 * An interface between the GUI and the kernel classes in a checkers game.
 * 
 * @author
 */

public class Facade extends Component {

	public static String update = "update";
	public static String playerSwitch = "switch";
	public static String ID = "facade";

	// Used to communicate with the game backend
	public Driver theDriver;

	// TODO: Use of LocalPlayer, Board, Player, remove all
	public Player activePlayer;

	private int startSpace = 99; // Starting space for current move
	private int endSpace = 99; // Ending space for current move

	// The numbers associated with the timer
	private int timer = 999;
	private int warningTime = 999;

	private ActionListener aListener;

	// The different GUI components
	private Firstscreen first;
	private Secondscreen second;
	private CheckerGUI boardGUI;

	/**
	 * Constructor for the facade. Initializes the data members.
	 * 
	 * @param newBoard
	 *            Board object Facade will manipulate.
	 * @param newDriver
	 *            Driver object that will communicate with the Facade.
	 */
	public Facade(Driver newDriver) {

		theDriver = newDriver;

	}

	/**
	 * Launch the initial UI to set up the game
	 */
	public void startUI() {
		first = new Firstscreen(this);

		first.setVisible(true);
	}

	/**
	 * Return an int indicating which player's turn it is. ( e.g. 1 for player 1
	 * )
	 * 
	 * @return int The number of the player whose turn it is.
	 * 
	 * @pre game is in progress
	 */
	public int whosTurn() {

		// Return the integer value of the activePlayer object
		int turn;
		turn = activePlayer.getNumber();

		return turn;
	}

	/**
	 * Set which players turn it is.
	 * 
	 * @param active
	 *            The active player
	 * @param passive
	 *            The passive player
	 */
	public void setPlayerModes(Player active) {

		activePlayer = active;

		// Tell GUI to update
		generateActionPerformed(update);
	}

	/**
	 * 
	 * This method should be called to select a space on the board, either as
	 * the starting point or the ending point for a move. The Facade will
	 * interpret this selection and send a move on to the kernel when two spaces
	 * have been selected.
	 * 
	 * @param space
	 *            an int indicating which space to move to, according to the
	 *            standard checkers numbering scheme, left to right and top to
	 *            bottom.
	 */
	public void selectSpace(int space) {

		// When button is click, take info from the GUI
		if (startSpace == 99) {

			// Set startSpace to space
			startSpace = space;

		} else if (startSpace != 99 && endSpace == 99) {
			if (space == startSpace) {

				// Viewed as un-selecting the space selected
				// Set startSpace to predetermined unselected value
				startSpace = 99;

			} else {
				// The endSpace will be set to space
				endSpace = space;
				makeLocalMove();
			}
		}

		generateActionPerformed("update");

	}

	/**
	 * Send a move on to the kernel, i.e. call makeMove() in the LocalPlayer and
	 * inform it whose turn it is.
	 * 
	 * @pre startSpace is defined
	 * @pre endSpace is defined
	 */
	private void makeLocalMove() {

		// make sure startSpace and endSpace are defined
		if (startSpace != 99 && endSpace != 99) {

			// Takes the information of a move and calls makeMove()
			// in a localPlayer
			theDriver.makeMove(startSpace, endSpace);
		}

		// Reset startSpace and endSpace to 99
		startSpace = 99;
		endSpace = 99;

	}

	/**
	 * Tell the kernel that the user has quit/resigned the game or quit the
	 * program
	 */
	public void pressQuit() {

		// Alert players and the kernel that one person
		// has quit calls quitGame() for both players
		theDriver.endInQuit();

	}

	/**
	 * Tell the kernel that the user has requested a draw.
	 */
	public void pressDraw() {

		// Alerts both players and the kernel that one person
		// has offered a draw calls offerDraw() on both players
		theDriver.offerDraw();

	}

	/**
	 * Given a player number, returns the name associated with that number.
	 * 
	 * @param playerNum
	 *            the number of a player
	 * @return string the name associated with playerNum
	 * 
	 * @pre playerNum is a valid player number
	 */
	public String getPlayerName(int playerNum) {
		return theDriver.getPlayerName(playerNum);
	}

	/**
	 * Tell the kernel to associate the given name with the given player number.
	 * 
	 * @param playerNum
	 *            the number of a player
	 * @param name
	 *            the name that player should be given
	 * 
	 * @pre playerNum is a valid player number
	 */
	public void setPlayerName(int playerNum, String name) {
		theDriver.setPlayerName(playerNum, name);
	}

	/**
	 * Tell the kernel to set a time limit for each turn. The time limit, i.e.
	 * the amount of time a player has during his turn before he is given a time
	 * warning, is specified by the parameter called time, in minutes.
	 * 
	 * Tell the kernel to set a time limit for each turn. The warning time, i.e.
	 * the amount of time a player has during his turn after he is given a time
	 * warning, is specified by the parameter called time, in minutes.
	 * 
	 * @param time
	 *            the time limit for each turn, in seconds.
	 * 
	 * @pre 10 <= time <= 300.
	 */
	public void setTimer(int time, int warning) throws Exception {
		// Checks to see that time is in between the necessary frame
		// Sets time(class variable) to time(param value)
		if ((time == -1)
				|| ((time >= 10 || time <= 300) && (warning >= 10 || warning <= 300))) {

			timer = time;
			warningTime = warning;
			theDriver.setTimer(time, warning);

		} else {
			throw new Exception("Invalid timer settings");
		}
	}

	/**
	 * Tell the kernel to connect to the specified host to start a network game.
	 * 
	 * @param host
	 * 
	 * @pre host != null
	 */
	public void setHost(URL host) {
		// Makes sure host isnt null
		// Calls setHost() in driver
		if (host != null) {
			theDriver.setHost(host);
		}
	}

	/**
	 * Display to local players that the game has ended with the message
	 * provided.
	 * 
	 * @param message
	 * 
	 * @post the game ends
	 */
	// TODO: This shouldn't exist
	public void showEndGame(String message) {
		// make sure game is over
		// calls endGame in driver object
		theDriver.endGame(message);
	}

	/**
	 * Tell the driver to set up a local game and display options to the user
	 */
	public void startLocalGame() {
		theDriver.setLocalGame();

		// Hide the initial option screen and display the next one
		first.setVisible(false);
		second = new Secondscreen(this, GameType.LOCALGAME);
		second.setVisible(true);

	}

	/**
	 * Tell the driver to set up a host game and display options to the user
	 */
	public void startHostGame() {
		theDriver.setHostGame();

		// Hide the initial option screen and display the next one
		first.setVisible(false);
		second = new Secondscreen(this, GameType.HOSTGAME);
		second.setVisible(true);
	}

	/**
	 * Tell the driver to set up a client game and display options to the user
	 */
	public void startClientGame() {
		theDriver.setClientGame();

		// Hide the initial option screen and display the next one
		first.setVisible(false);
		second = new Secondscreen(this, GameType.CLIENTGAME);
		second.setVisible(true);
	}

	/**
	 * Returns the timer value, how long each player get to take a turn
	 * 
	 * @return the amount of time each player has for a turn
	 * 
	 * @pre there has been a timer set for the current game
	 * 
	 */
	public int getTimer() {
		int retval = -1;

		// Makes sure there is a timer for this game
		if (timer != 999) {
			retval = timer;
		}

		// Returns the timer value (clas variable: time )
		return retval;
	}

	/**
	 * Returns the amount of time chosen for a warning that a player is near the
	 * end of his/her turn.
	 * 
	 * @return the amount of warning time a player has
	 * 
	 * @pre there has been a timer set for the current game
	 */
	public int getTimerWarning() {
		int retval = -1;

		// Makes sure there is a timer for this game
		if (warningTime != 999) {
			retval = warningTime;
		}

		// Returns the timer value (clas variable: warningTime )
		return retval;
	}

	/**
	 * Adds an action listener to the facade
	 */
	public void addActionListener(ActionListener a) {
		aListener = AWTEventMulticaster.add(aListener, a);
		// Adds an action listener to the facade
	}

	/**
	 * Notifies everything of the sta eof the board
	 * 
	 * @return a Board object which is the state of the board
	 * 
	 */
	// TODO: Implement some kind of observer relationship with the Driver/UI
	public Board stateOfBoard() {
		// Return the board so GUI can go through and update itself
		return theDriver.getBoardState();
	}

	/**
	 * Call the driver and begin the game.
	 */
	public void startGame() {
		theDriver.startGame();

		// Hide the second options screen, make and show the board UI
		second.setVisible(false);

		boardGUI = new CheckerGUI(this, getPlayerName(1), getPlayerName(2));
		boardGUI.setVisible(true);
	}

	/**
	 * Generates an action. This is inhereted from Component
	 * 
	 */
	public void generateActionPerformed() {

		if (aListener != null) {
			aListener.actionPerformed(new ActionEvent(this,
					ActionEvent.ACTION_PERFORMED, ID));
			// Fires event associated with timer, or a move made on GUI
		}

	}

	/**
	 * Generates an action. This is inhereted from Componen
	 */
	private void generateActionPerformed(String command) {

		if (aListener != null) {
			aListener.actionPerformed(new ActionEvent(this,
					ActionEvent.ACTION_PERFORMED, command));
			// Fires an event associated with timer, or move made on GUI
		}
	}

	/**
	 * Display a message to the user based on game play. No response is
	 * necessary.
	 * 
	 * @param title
	 *            title for the game event
	 * @param the
	 *            message to be displayed
	 */
	public void displayMessage(String title, String msg) {
		JOptionPane.showMessageDialog(null, msg, title,
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Pass a message to be displayed to the user when a game event occurs,
	 * return the response
	 * 
	 * @param title
	 *            title for the game event
	 * @param msg
	 *            the message to give to the user
	 * 
	 * @return the users's response
	 */
	public int displayMessageWithResult(String title, String msg) {
		return JOptionPane.showConfirmDialog(null, msg, title,
				JOptionPane.YES_NO_OPTION);
	}

}// Facade.java