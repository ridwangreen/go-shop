package system;

import Command.NewGame;
import Controller.GameController;

/*
 * @File Game.java
 * 
 * @Authors Alex Kahn, John Neville, Alex Canter, Becca Dudley
 * 
 * @Class Description is now our main
 */

public class Game {
	public static void main(String[] args){		
		
		// Add the observers
		GameController gameController = new GameController();
		GameState.getInstance().addObserver(gameController);
		
		// Start a new game
		NewGame newGame = new NewGame();
		GameState.getInstance().pushCommand(newGame);
	}
}