package Controller;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import player.PlayerProxy;
import player.PlayerType;

import system.GameState;
import system.LogHandler;
import system.Peg;
import system.PegColor;
import Command.Command;
import Command.EndGame;
import Command.Feedback;
import Command.Guess;
import Command.NewGame;
import Command.SetCode;

/*
 * @File GameController.java
 * 
 * @Authors Becca Dudley
 * 
 * @Class Description the controller for the game.  It obseves the gamestate
 * 		and communicates the changes to the classes that need to know
 */
public class GameController implements Observer {

	private BoardController bc;
	private Peg guessPegs[][];
	private Peg feedbackPegs[][];
	private PlayerProxy players;

	public GameController() {

		guessPegs = GameState.getInstance().getGuessPegs();
		feedbackPegs = GameState.getInstance().getFeedbackPegs();
	}

	@Override
	public void update(Observable gameState, Object arg1) {
		// TODO Auto-generated method stub

		GameState gameStateTemp = (GameState) gameState;
		Command last = gameStateTemp.getLastCommand();
		if (last instanceof NewGame) {
			if (bc != null) {
				bc.endGame();
				gameStateTemp.clearCommands();
				gameStateTemp.createNewGame();
			}
			LogHandler.getInstance().setFileName(null);
			Object[] options = { "Local Player", "Remote Player", "AI" };
			int maker = JOptionPane.showOptionDialog(null,
					"What will the CodeMaker be?", null,
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			int breaker = JOptionPane.showOptionDialog(null,
					"What will the CodeBreaker be?", null,
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

			players = new PlayerProxy(PlayerType.values()[maker], PlayerType
					.values()[breaker]);
			bc = new BoardController(guessPegs, feedbackPegs, players);
			bc.newGame(breaker, maker);
			players.nextMakerTurnAI();

		} else if (last instanceof Feedback) {

			if (gameStateTemp.isTerminalState()) {
				JOptionPane.showMessageDialog(null, "Game Over!");

			} else {
				players.nextBreakerTurnAI();
				if (players.getBreakerType().equals(PlayerType.LOCAL)) {

					bc.feedback(GameState.getInstance().getTurn() - 1);
				}
			}

		} else if (last instanceof Guess) {

			players.nextMakerTurn();

		} else if (last instanceof SetCode) {

			players.nextBreakerTurnAI();
			System.out.println("ASDASD");
			bc.setCode();
		}
	}
}
