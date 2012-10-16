package player;

import java.util.ArrayList;

import system.Game;
import system.GameState;
import system.Peg;
import system.PegColor;
import Command.Feedback;

/*
 * @File NameGame.java
 * 
 * @Authors Alex Kahn, John Neville, Alex Canter, Becca Dudley
 * 
 * @Class Description human player creates. 
 */
public abstract class CodeMaker implements Players {

	public void getCodeFeedback() {
		// for black pegs
		int curTurn = GameState.getInstance().getTurn() - 1;
		GameState gameStateTemp = GameState.getInstance();
		Peg guessPegs[][] = gameStateTemp.getGuessPegs();
		Peg feedbackPegs[][] = gameStateTemp.getFeedbackPegs();

		// deals with black pegs
		for (int i = 0; i < 4; i++) {
			if (guessPegs[curTurn][i].getPegColor() == guessPegs[10][i]
					.getPegColor()) {
				feedbackPegs[curTurn][i].setPegColor(PegColor.BLACK);
			}
		}
		// for white pegs
		// makes an array list that copies the code and allows me to use the
		// contains that belongs to an arraylist.
		ArrayList<Peg> temp = new ArrayList<Peg>();
		for (int i = 0; i < 4; i++) {
			temp.add(guessPegs[curTurn][i]);
		}

		for (int j = 0; j < 4; j++) {
			if (temp.contains(guessPegs[10][j])) {
				if (feedbackPegs[curTurn][j].getPegColor() != PegColor.BLACK) {
					feedbackPegs[curTurn][j].setPegColor(PegColor.WHITE);
				}
			}
		}
		Feedback fb = new Feedback();
		GameState.getInstance().pushCommand(fb);

	}

}
