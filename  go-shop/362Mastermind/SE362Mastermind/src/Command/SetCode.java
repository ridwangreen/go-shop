package Command;

import system.GameState;

public class SetCode extends Command {
	public SetCode() {
		GameState.getInstance().upTurnCount();
	}
	
	/**
	 * Does nothing for this command
	 */
	public void execute() {
	}
	
	/**
	 * Does nothing for this command
	 */
	public void undo() {
		
	}
}