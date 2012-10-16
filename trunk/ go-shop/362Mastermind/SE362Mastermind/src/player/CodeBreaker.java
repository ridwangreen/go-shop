package player;

import Command.Guess;
import system.GameState;
import system.Peg;

/*
 * @File NameGame.java
 * 
 * @Authors Alex Kahn, John Neville, Alex Canter, Becca Dudley
 * 
 * @Class Description this will be implemented by the two codebreakers depending on if they
 * are computer or not
 */
public abstract class CodeBreaker implements Players{
	
	public static void makeGuess() {
		Guess newGuess = new Guess();
		GameState.getInstance().pushCommand(newGuess);
	}
}
