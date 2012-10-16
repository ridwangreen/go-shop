/**
 * Driver.java
 *
 * Version
 *    $Id: Driver.java,v 1.1 2002/10/22 21:12:52 se362 Exp $ 
 *
 * Revisions:
 *    $Log: Driver.java,v $
 *    Revision 1.1  2002/10/22 21:12:52  se362
 *    Initial creation of case study
 *
 */

import java.awt.Color;
import java.net.URL;

/**
 * 
 * This class is a part of the main functionality of the checkers game. This
 * class contains the main method to start the game, it creates all necessary
 * classes as informaton is provided. Its functions include knowing whose turn
 * it is, remembering multiple jumps, relaying end of game conditions and ending
 * the game.
 * 
 * @author
 * 
 */

public class Driver {

	// The players, represented as playerOne and playerTwo
	// At all times one of those players is the activePlayer and the
	// other is the passivePlayer, depending on turn
	private Player playerOne;
	private Player playerTwo;
	private Player activePlayer;
	private Player passivePlayer;

	private GameType type;
	private boolean runningTimer;
	private Timer theTimer;

	// Used to talk to front-end components
	private Facade theFacade;
	
	private Board theBoard;

	/**
	 * Constructor
	 * 
	 * Create the driver, which mediates the system
	 */
	public Driver() {
	}

	/**
	 * Initiate a new game of Checkers
	 */
	public void playGame() {
		// Create the board
		theBoard = new Board(this);

		// Create the facade and GUI
		theFacade = new Facade(this);
		theFacade.startUI();
	}

	/**
	 * This method is called after a move has been checked. Changes active
	 * player when a final succesful jump has been made, resets the timer when
	 * appropriate, and tells the appropriate player whos turn it is to make a
	 * move.
	 * 
	 * @param player
	 *            The player whose turn it will now be
	 * @param space
	 *            The space on the board from which a multiple jump has to be
	 *            made
	 * 
	 * @pre a players has made a move
	 * @post a player has been told to make a move
	 */
	// TODO: This should really only be called when it's definitely the
	// end of a turn
	public void endTurn(/*Player player, int space*/) {

			// If game is networked, tell networked player to send move
			activePlayer.sendMove();

			// Inform the other player to make a move and
			// tell facade to update any listining GUIs and
			// reset the timer

			Player tempHold = activePlayer;
			activePlayer = passivePlayer;
			passivePlayer = tempHold;

			theFacade.setPlayerModes(activePlayer);

	}

	/**
	 * This method ends the checkers game due to whatever reason neccessary ie.
	 * a draw, someone quitting, or a victory.
	 * 
	 * @param message
	 *            the message to send to all players regarding the reason for
	 *            ending the game
	 * 
	 * @pre the criteria for ending a game has been met, depending on why the
	 *      game ended
	 * @post the game has been ended for both players and the game is ready to
	 *       exit
	 */
	public void endGame(String message) {

		// Call endOfGame on both players with the given message
		playerOne.endOfGame(message);
		playerTwo.endOfGame(message);

		// When players have acknowledged the end of game
		// call System.exit()
		System.exit(0);
	}

	/**
	 * Set the name for the player using the passed in values.
	 * 
	 * @param num
	 *            The player's number (1 or 2)
	 * @param name
	 *            The name to assign to the player.
	 */
	public void setPlayerName(int num, String name) {
		if (num == 1) {
			playerOne.setName(name);
		} else {
			playerTwo.setName(name);
		}
	}

	/**
	 * Set the color for a player using the passed in value.
	 * 
	 * @param num
	 *            The player's number (1 or 2)
	 * @param color
	 *            The color to assign to the player.
	 */
	// TODO: Sort out the fact that only NetworkPlayer calls this...
	public void setPlayerColor(int num, Color color) {
		if (num == 1) {
			playerOne.setColor(color);
		} else {
			playerTwo.setColor(color);
		}
	}

	/**
	 * Return the name of the requested player
	 */
	public String getPlayerName(int num) {
		String retVal = "";

		switch (num) {
		case 1:
			retVal = playerOne.getName();
			break;

		case 2:
			retVal = playerTwo.getName();
			break;

		default:
			break;
		}

		return retVal;
	}

	/**
	 * This method ends the game in a draw, alerting both players that the draw
	 * has taken place
	 * 
	 * @pre both players have agreed to a draw
	 * @post the game has ended and both players have been notified of the draw
	 */
	public void endInDraw() {
		// Calls endOfGame with a message that game ended in a draw.
		endGame("Game ended in a draw.");
	}

	/**
	 * The offer for a draw has been made. This method declines that offer,
	 * meaning the game will continue.
	 * 
	 * @param player
	 *            The player declining the draw.
	 */
	public void declineDraw() {
		if (type == GameType.LOCALGAME) {
			activePlayer.declineDraw();
		}
	}

	/**
	 * Ends the game as a result of a player quitting, notifying each player
	 * 
	 */
	public void endInQuit() {
		playerOne.endOfGame(activePlayer.getName() + " quit the game");
		playerTwo.endOfGame(activePlayer.getName() + " quit the game");
	}

	/**
	 * Called when one player offers to end the game in a draw. Pass the message
	 * along to the other player.
	 */
	public void offerDraw() {
		activePlayer.offerDraw();
	}

	/**
	 * This method creates the timer to be used, if one is desired to be used.
	 * It will also set the number of seconds for each turn.
	 * 
	 * @param time
	 *            : the number of seconds for each turn
	 * @param warning
	 *            : whether or not a player will be warned that their turn is
	 *            going to end
	 * 
	 * @pre It has been selected to use a timer in the game setup
	 * @post The timer has been created and the appropriate time restraints are
	 *       in place
	 */
	public void setTimer(int time, int warning) {
		// If values are negative, set runningTimer to false
		// If they are positive values, create the Timer and
		// notifier with the times

		if (time < 0) {
			runningTimer = false;
		} else {
			runningTimer = true;
			theTimer = new Timer();
		}

	}

	/**
	 * This method will start the game play. Letting the first person move their
	 * piece and so on
	 * 
	 * @pre There are 2 players to play, and all pregame conditions are in place
	 * @post The first person is able to make their first move
	 */
	public void startGame() {

		if (type == GameType.HOSTGAME) {
			((NetworkPlayer) playerTwo).waitForConnect();
			// ( (NetworkPlayer)playerTwo).waitForConnect();
		} else if (type == GameType.CLIENTGAME) {
			// ( (NetworkPlayer)playerOne).connectToHost();
			((NetworkPlayer) playerOne).connectToHost();
		}

		// Player one is always blue
		playerOne.setColor(Color.white);
		playerTwo.setColor(Color.blue);

		// Set the active and passive player
		activePlayer = playerOne;
		passivePlayer = playerTwo;

		theFacade.setPlayerModes(activePlayer);
	}

	/**
	 * Take the starting and ending location for a move and pass it along to the
	 * board for validation
	 * 
	 * @param startSpace
	 *            starting location of the move
	 * @param endSpace
	 *            ending location of the move
	 * 
	 */
	public void makeMove(int startSpace, int endSpace) {
		makeMove(new Move(activePlayer.getColor(), startSpace, endSpace));
	}
	
	/**
	 * Take an existing move and pass it along to the board for validation
	 * 
	 * @param move   the existing move that must be validated and applied
	 */
	public void makeMove(Move move)
	{
		// Have the board check the move and perform it if legal

		MoveStatus status = theBoard.validateMove(move);
		if (theBoard.checkEndCondition()) {
			endGame("Game Over!");
		}

		switch (status) {

			case NONE:
				theFacade.displayMessage("Illegal Move", "Please select another move.");
				break;
		
			case MOVE:
				endTurn();
				break;
		}
	}
	

	/**
	 * This method sets the host the player will play against in case of a game
	 * over a network.
	 * 
	 * @param host
	 *            the host of the game to be played
	 * 
	 * @pre There is a person to host the game, both players are
	 *      networkedPlayers
	 * 
	 * @post The players are connected to play
	 */
	public void setHost(URL host) {
		// Call connectToHost in player two with the URL
		((NetworkPlayer) playerOne).setHost(host);
		((NetworkPlayer) playerTwo).setHost(host);
	}

	/**
	 * Return the player whos turn it is not
	 * 
	 * @return the player whose turn it is not
	 * 
	 * @pre there are 2 valid players and the game has started
	 * @post this method has not altered anything
	 */
	// TODO: Remove once Rules gets removed
	public Player getOppositePlayer() {
		// Returns the player whos getTurnStatus is false
		return passivePlayer;
	}

	/**
	 * Whether the current game uses a timer
	 * 
	 * @return true if a timer is being sed in the game, otherwise false
	 * 
	 * @pre the game has started
	 * @post this method has not altered anything
	 */
	public boolean timerRunning() {
		return runningTimer;
	}

	/**
	 * Select the type of game
	 * 
	 * @param mode
	 *            the mode (0 local, 1 host, 2 client) of the game
	 * 
	 * @pre Players have not been created
	 * @post Mode is set
	 */
	public void setGameMode(GameType newMode) {
		// Set the value of mode
		type = newMode;
	}

	/**
	 * Set up a local game and create the players
	 */
	public void setLocalGame() {
		type = GameType.LOCALGAME;

		// Create two local players
		playerOne = new LocalPlayer(1, this);
		playerOne.setName("player1");
		playerTwo = new LocalPlayer(2, this);
		playerTwo.setName("player2");
	}

	/**
	 * Set up a hosted network game and create the players
	 */
	public void setHostGame() {
		type = GameType.HOSTGAME;

		// Create two network players
		// TODO: Remove Rules
		playerOne = new NetworkPlayer(1, this);
		playerOne.setName("player1");
		playerTwo = new NetworkPlayer(2, this);
		playerTwo.setName("player2");
	}

	/**
	 * Set up a client network game and create the players
	 */
	public void setClientGame() {
		type = GameType.CLIENTGAME;

		// Create two network players with default names
		// TODO: Remove Rules
		playerOne = new NetworkPlayer(1, this);
		playerOne.setName("player1");
		playerTwo = new NetworkPlayer(2, this);
		playerTwo.setName("player2");
	}

	/**
	 * Return the integer representing the type of game
	 * 
	 * @return the type of game
	 * 
	 * @pre Game has started
	 * @post This method has changed nothing
	 */
	public GameType getGameMode() {
		return type;
	}

	/**
	 * Return the notifier of the Timer
	 * 
	 * @return the notifier for the Timer
	 * 
	 * @pre The game is running
	 * @post This method has changed nothing
	 */
	public Notifier getTimerNotifier() {
		// Return the timers notifier, by asking the timer
		// for its notifier
		Notifier timer = null;

		if (theTimer != null) {
			timer = theTimer.getNotifier();
		}

		return timer;
	}

	/**
	 * Return a representation of the current state of the Board
	 * 
	 * @return the current state of the Board
	 * 
	 */
	// TODO: don't like this...
	public Board getBoardState() {
		return theBoard;
	}

	/**
	 * Pass a message to be displayed to the user when a game event occurs, no
	 * response.
	 * 
	 * @param title
	 *            title for the game event
	 * @param msg
	 *            the message to give the user
	 */
	public void displayMessage(String title, String msg) {
		theFacade.displayMessage(title, msg);
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
		return theFacade.displayMessageWithResult(title, msg);
	}
        
        
    /**
     * Converts an integer location of a piece into x and y 
     */
    
   
}//Driver.java
