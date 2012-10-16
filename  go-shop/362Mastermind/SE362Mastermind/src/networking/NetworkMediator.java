package networking;

import java.util.ArrayList;
import java.util.Observable;

import player.CodeMaker;

import Command.Guess;
import Command.Undo;

import server.MMGameServer;
import server.MMServerObservable;
import system.GameState;
import system.Peg;
import system.PegColor;
import transferObjects.gameplay.MMFeedback;
import transferObjects.gameplay.MMGuess;
import transferObjects.gameplay.MMPegForFeedbackColor;
import transferObjects.gameplay.MMPegForGuessColor;
import transferObjects.networking.MMConnectAcknowledgedNotification;
import transferObjects.networking.MMConnectNotification;
import transferObjects.networking.MMDisconnectNotification;
import transferObjects.networking.exceptions.MMNetworkingException;
import client.MMGameClient;

/**
 * This class houses both the Client and the Server.
 * It acts as an intermediary, black box, and adapter between the 
 * protocol used by the Networking API, and the protocol used by
 * the main Mastermind System.
 * 
 * To receive messages, the sender must register 
 * itself as an Observer.
 * 
 * @author Alex Canter
 */
public class NetworkMediator extends Observable implements MMServerObservable {
	MMGameClient client;
	MMGameServer server;

	/**
	 * Basic constructor. Setup the networking elements.
	 */
	public NetworkMediator() {
		// Create a client
		client = new MMGameClient();
		// Pass it to the server and begin listening
		server = new MMGameServer(client);
		server.registerListener(this);
		System.out.println("Server started.");	
	}

	/************************
	 * SEND MESSAGES TO SERVER
	 ***********************/

	/**
	 * Attempts to connect to a remote game as a Codemaker.
	 * 
	 * @param remoteIp the IP of the remote game.
	 * 
	 * @return true is the connection was successful, 
	 * false if an error occurred.
	 */
	public boolean connectAsCodemaker(String remoteIp) {
		try {
			client.requestToConnectToRemoteGameAsCodemaker(remoteIp);
			return true;
		} catch (MMNetworkingException e) {
			e.printStackTrace();
			System.err.println("Error connecting to host.");
			return false;
		}
	}

	/**
	 * Attempts to connect to a remote game as a Codebreaker.
	 * 
	 * @param remoteIp the IP of the remote game.
	 * 
	 * @return true is the connection was successful, 
	 * false if an error occurred.
	 */
	public boolean addCodeBreaker(String remoteIp) {
		try {
			client.requestToConnectToRemoteGameAsCodebreaker(remoteIp);
			return true;
		} catch (MMNetworkingException e) {
			e.printStackTrace();
			System.err.println("Error connecting to host.");
			return false;
		}
	}

	/**
	 * Allows a player associated with the passed-in notification
	 * to join the game. This should be called -after- the player
	 * has been added on the system side; this method will add the
	 * player on the network side.
	 * 
	 * @param notification The notification
	 */
	public void allowPlayerToJoin(
			MMConnectNotification notification) {
		client.acceptConnectionRequest(notification);
	}

	/**
	 * Forbids a player associated with the passed-in notification
	 * to join the game. This must be called from the system-side to
	 * alert the server that the attempted connection was refused.
	 * 
	 * @param notification The notification
	 */
	public void forbidPlayerToJoin(
			MMConnectNotification notification) {
		client.rejectConnectionRequest(notification);
	}

	/**
	 * When exiting the app or just disconnecting, call this method
	 * to alert the server that a player is leaving. The server will
	 * push a notification to the other player and terminate its
	 * connection as well.
	 * 
	 * @return true is the connection was successful, 
	 * false if an error occurred.
	 */
	public boolean disconnectFromGame() {
		try {
			client.pushDisconnectNotification();
			return true;
		} catch (MMNetworkingException e) {
			e.printStackTrace();
			System.err.println("An error occured while disconnecting.");
			return false;
		}
	}

	/**
	 * Fire an Undo Command to the other Client.
	 * 
	 * @return true is the connection was successful, 
	 * false if an error occurred.
	 */
	public boolean undo() {
		try {
			client.pushUndo();
			return true;
		} catch (MMNetworkingException e) {
			e.printStackTrace();
			System.err.println("An error occured during the undo operation.");
			return false;
		}
	}

	/**
	 * Fire an Redo Command to the other Client.
	 * 
	 * @return true is the connection was successful, 
	 * false if an error occurred.
	 */
	public boolean redo() {
		try {
			client.pushRedo();
			return true;
		} catch (MMNetworkingException e) {
			e.printStackTrace();
			System.err.println("An error occured during the redo operation.");
			return false;
		}
	}

	/**
	 * Make a guess cross-server.
	 * 
	 * @return true is the connection was successful, 
	 * false if an error occurred.
	 */
	public boolean makeGuess(Peg[] pegs) {
		try {
			ArrayList<MMPegForGuessColor> pegColors = 
				new ArrayList<MMPegForGuessColor>();
			for (int i=0; i<pegs.length; i++) {
				int systemPegIndex = PegColor.values()[0].ordinal();
				MMPegForGuessColor temp = MMPegForGuessColor.values()[systemPegIndex];
				pegColors.add(temp);
			}
			MMGuess serverGuess = new MMGuess(pegColors);
			client.pushGuess(serverGuess);
			return true;
		} catch (MMNetworkingException e) {
			e.printStackTrace();
			System.err.println("An error occured during the guess operation.");
			return false;
		}
	}
	
	/**
	 * Give feedback to another player cross-server
	 * 
	 * @return true is the connection was successful, 
	 * false if an error occurred.
	 */
	public boolean sendFeedback(Peg[] pegs) {
		try {
			ArrayList<MMPegForFeedbackColor> pegColors = 
				new ArrayList<MMPegForFeedbackColor>();
			for (int i=0; i<pegs.length; i++) {
				int systemPegIndex = PegColor.values()[0].ordinal();
				MMPegForFeedbackColor temp;
				if (systemPegIndex == 0)
					temp = MMPegForFeedbackColor.FEEDBACK_BLACK;
				else
					temp = MMPegForFeedbackColor.FEEDBACK_WHITE;
				pegColors.add(temp);
			}
			MMFeedback serverFeedback = new MMFeedback(pegColors);
			client.pushFeedback(serverFeedback);
			return true;
		} catch (MMNetworkingException e) {
			e.printStackTrace();
			System.err.println("An error occured during the sendFeedback operation.");
			return false;
		}
	}
	
	

	/************************
	 * RECEIVED MESSAGES FROM SERVER
	 ***********************/

	/**
	 * Signal to stop receiving and mediating communications.
	 * Clears all registered Observers.
	 */
	public void stop() {
		System.out.println("Server stopped");
		server.stopListening();
		this.deleteObservers();
	}

	/**
	 * Handler for when the remote player joins the game.
	 * This message will be sent to Observers to notify them that
	 * a remote player has joined the game.
	 * 
	 * @param e the request-event
	 */
	@Override
	public void receiveConnectAcknowledgedNotifiction(
			MMConnectAcknowledgedNotification e) {
		this.setChanged();
		this.notifyObservers(e);
	}

	/**
	 * Handler for when the remote player attempts to connect.
	 * The Observers are responsible for handling this message when it
	 * is received (e.g. if the game is already in-progress, it should
	 * be ignored).
	 * 
	 * @param e the request-event
	 */
	@Override
	public void receiveConnectionRequest(MMConnectNotification e) {
		this.setChanged();
		this.notifyObservers(e);
	}

	/**
	 * Handler for when when the remote player leaves the game.
	 * The Observer is responsible for notifying the other player
	 * that this remote-player has left the game.
	 * 
	 * @param e the request-event
	 */
	@Override
	public void receiveDisconnectNotification(
			MMDisconnectNotification e) {
		this.setChanged();
		this.notifyObservers(e);
	}

	/**
	 * Handler for when the remote game has sent guess feedback
	 * to you. The Observer is responsible for transporting the
	 * guess data (which is transfered as Peg objects) to the
	 * appropriate handler.
	 * 
	 * @param feedback The guess-feedback created by the remote game.
	 */
	@Override
	public void receiveFeedback(MMFeedback feedback) {
		ArrayList<MMPegForFeedbackColor> serverFeedback = feedback.getColors();
		Peg[] systemFeednack = new Peg[serverFeedback.size()];
		for (int i=0; i<systemFeednack.length; i++) {
			// Transfer the Networking API enum to the enum used
			// by our Peg class. Then make a new Peg from it.
			PegColor systemColor = 
				PegColor.values()[serverFeedback.get(i).ordinal()];
			systemFeednack[i] = new Peg(systemColor);
		}
		// Send the translated Peg[] guess to the system
		this.setChanged();
		this.notifyObservers(systemFeednack);
	}

	/**
	 * Handler for when the remote game has sent a guess to you.
	 * Translates the Networking API's MMGuess to Peg[] array used
	 * by our system. The Observer is responsible for utilizing that
	 * translated data.
	 * 
	 * @param guess the Guess created by the remote player.
	 */
	@Override
	public void receiveGuess(MMGuess guess) {
		ArrayList<MMPegForGuessColor> serverGuess = guess.getColors();
		Peg[] systemGuess = new Peg[serverGuess.size()];
		for (int i=0; i<systemGuess.length; i++) {
			// Transfer the Networking API enum to the enum used
			// by our Peg class. Then make a new Peg from it.
			PegColor systemColor = 
				PegColor.values()[serverGuess.get(i).ordinal()];
			systemGuess[i] = new Peg(systemColor);
		}
		// Send the translated Peg[] guess to the system
		this.setChanged();
		this.notifyObservers(systemGuess);
	}

	/**
	 * Not currently sure what this method is for,
	 * since there is no "redo" command in the
	 * release2 requirements. 
	 * 
	 * @param e the request-event
	 */
	@Override
	public void receiveRedo() {

	}

	/**
	 * Handler for when the remote player requests an undo. 
	 * Observer is responsible for firing the passed-in Undo command.
	 * 
	 * @param e the request-event
	 */
	@Override
	public void receiveUndo() {
		this.setChanged();
		this.notifyObservers(new Undo());
	}

}
