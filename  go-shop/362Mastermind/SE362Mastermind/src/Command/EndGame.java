package Command;

import system.GameState;

public class EndGame extends Command {
	public void execute() {
		GameState.getInstance().clearCommands();
	}
	public void undo() {
		// TODO Auto-generated method stub
	}
}
