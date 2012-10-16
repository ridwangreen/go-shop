package Command;

import system.GameState;
import system.Peg;

public class Feedback extends Command {
	
	public Feedback() {
		GameState.getInstance().upTurnCount();
	}

	public void execute() {
	}
	
	public void undo() {
		// TODO Auto-generated method stub
	}
}
