package player;

import Command.SetCode;
import system.GameState;

public class CodeMaker_Local extends CodeMaker{

	public void makeMove() {
		if (GameState.getInstance().getTurn() == 0) {
			SetCode sc = new SetCode();
			GameState.getInstance().pushCommand(sc);
		} else {
			getCodeFeedback();
		}
	}
}
