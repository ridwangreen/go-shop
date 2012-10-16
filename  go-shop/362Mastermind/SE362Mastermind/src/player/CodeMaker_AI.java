package player;

import java.util.Observable;
import java.util.Observer;

import system.GameState;
import system.PegColor;
import Command.Feedback;
import Command.SetCode;

/*
 * @Class Name CodeMaker_AI
 * 
 * @author Alex Kahn
 * 
 * @description needs to make a computer controlled AI
 *
 * @date 10-9-2011
 */
public class CodeMaker_AI extends CodeMaker_Local implements Observer {

	// makes one AI with a code to crack
	public CodeMaker_AI() {
	}

	// makes the code to be tested against
	public void createCode() {
		for (int i = 0; i < 4; i++) {
			GameState.getInstance().getGuessPegs()[10][i]
					.setPegColor(color_selecter(guesser(1, 6)));
		}
	}

	public void makeMove() {
		if (GameState.getInstance().getTurn() == 0) {
			createCode();
			SetCode sc = new SetCode();
			GameState.getInstance().pushCommand(sc);
		} else {
			getCodeFeedback();
		}
	}

	public static int guesser(int min, int max) {
		return (int) ((max - min + 1) * Math.random() + min);
	}

	public static PegColor color_selecter(int num) {
		if (num == 1) {
			return PegColor.BLUE;
		} else if (num == 2) {
			return PegColor.YELLOW;
		} else if (num == 3)
			return PegColor.GREEN;
		else if (num == 4) {
			return PegColor.RED;
		} else if (num == 5) {
			return PegColor.WHITE;
		}
		return PegColor.BLACK;

	}

	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof Feedback)
			makeMove();
	}
}
