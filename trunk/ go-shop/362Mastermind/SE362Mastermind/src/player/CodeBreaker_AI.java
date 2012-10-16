package player;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import Command.Guess;
import system.GameState;
import system.Peg;
import system.PegColor;

/*
 * @File NameGame.java
 * 
 * @Authors Alex Kahn, John Neville, Alex Canter, Becca Dudley
 * 
 * @Class Description for the AI have fun Canter or however gets this which now might be me
 */
public class CodeBreaker_AI extends CodeBreaker {
	static Difficulty skill;
	int responseTime;
	PegColor pcolor;
	private static ArrayList<Peg[]> guessList = new ArrayList<Peg[]>();
	private static Peg[] guess = new Peg[4];

	// takes in a difficulty either easy/medium/hard
	public CodeBreaker_AI(Difficulty difficulty, int response) {
		skill = difficulty;
		responseTime = response;
	}

	public CodeBreaker_AI() {
		skill = Difficulty.easy;
		responseTime = 30000;
	}

	public int getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(int responseTime) {
		this.responseTime = responseTime;
	}

	/*
	 * sets the code breaker A_I if it needs to be changed for any reason
	 */
	public void set_AI(Difficulty diff) {
		if (diff == Difficulty.easy) {
			skill = Difficulty.easy;
		} else if (diff == Difficulty.medium) {
			skill = Difficulty.medium;
		} else {
			skill = Difficulty.hard;
		}
		
	}

	/*
	 * will always generate a random number between a min and a max
	 */
	public static int guesser(int min, int max) {
		return (int) ((max - min + 1) * Math.random() + min);
	}

	// easy mode is always a series of 4 random guess
	public void easyGuess() {
		int curturn = GameState.getInstance().getTurn() - 1;

		for (int i = 0; i < 4; i++) {
			pcolor = color_selecter(guesser(1, 6));
			GameState.getInstance().getGuessPegs()[curturn][i]
					.setPegColor(pcolor);
			Peg peg = new Peg(pcolor);
			guess[i] = peg;
		}
		guessList.add(guess);
	}

	/*
	 * medium skill level defines that the AI will never have the same guess
	 * twice
	 */
	public void mediumGuess() {
		int curturn = GameState.getInstance().getTurn() - 1;
		for (int i = 0; i < 4; i++) {
			pcolor = color_selecter(guesser(1, 6));
			GameState.getInstance().getGuessPegs()[curturn][i]
					.setPegColor(pcolor);
			Peg peg = new Peg(pcolor);
			guess[i] = peg;
		}
		// checks to see if the guess has been made before
		while (guessList.contains(guess)) {
			for (int i = 0; i < 4; i++) {
				pcolor = color_selecter(guesser(1, 6));
				GameState.getInstance().getGuessPegs()[curturn][i]
						.setPegColor(pcolor);
				Peg peg = new Peg(pcolor);
				guess[i] = peg;
			}
			if (!guessList.contains(guess)) {
				break;
			}
		}
		guessList.add(guess);
	}

	/*
	 * hard level is defined by simply looking at the feedback to an extent
	 * 
	 * special case for if this is the first time a guess is made by a hard AI
	 */
	public void hardGuess() {
		int curturn = GameState.getInstance().getTurn() - 1;
		if (GameState.getInstance().getLastFeedback() == null) {
			for (int i = 0; i < 4; i++) {
				pcolor = color_selecter(guesser(1, 6));
				GameState.getInstance().getGuessPegs()[curturn][i]
						.setPegColor(pcolor);
				Peg peg = new Peg(pcolor);
				guess[i] = peg;
			}
		} else {
			// decides whether or not there is a black peg in the
			// feedback
			boolean check = false;
			for (Peg p : GameState.getInstance().getLastFeedback().pegs) {
				if (p != null && p.getPegColor() == PegColor.BLACK) {
					check = true;
					break;
				}
			}
			// if we had no black pegs in the last feedback
			if (check) {
				for (int i = 0; i < 4; i++) {
					pcolor = color_selecter(guesser(1, 6));
					GameState.getInstance().getGuessPegs()[curturn][i]
							.setPegColor(pcolor);
					Peg peg = new Peg(pcolor);
					guess[i] = peg;
				}
				// we did have black pegs in the last feedback
			} else {
				// fills in the guess with 3 randoms and the last one
				// being
				// the last
				// element from the last guess
				for (int i = 0; i < 3; i++) {
					pcolor = color_selecter(guesser(1, 6));
					GameState.getInstance().getGuessPegs()[curturn][i]
							.setPegColor(pcolor);
					Peg peg = new Peg(pcolor);
					guess[i] = peg;
				}
				Peg[] temp = guessList.get(guessList.size() - 1);
				PegColor goodPeg = temp[3].getPegColor();
				GameState.getInstance().getGuessPegs()[curturn][3]
						.setPegColor(pcolor);
				Peg gPeg = new Peg(goodPeg);
				guess[3] = gPeg;
			}
		}
		guessList.add(guess);
	}

	public void makeMove() {
		// ignores the feedback constant random guess
		if (skill.equals(Difficulty.easy))
			easyGuess();
		if (skill.equals(Difficulty.medium)) {
			mediumGuess();
		} else {
			hardGuess();
		}
		try {
			Thread.currentThread();
			Thread.sleep(responseTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CodeBreaker.makeGuess();
	}

	/*
	 * takes in a number and returns a color
	 */
	public static PegColor color_selecter(int num) {
		if (num == 1) {
			return PegColor.BLUE;
		} else if (num == 2) {
			return PegColor.YELLOW;
		} else if (num == 3)
			return PegColor.RED;
		 else if (num == 3)
				return PegColor.GREEN;
		else if (num == 4)
		{
			return PegColor.WHITE;
		}
		return PegColor.BLACK;

	}
}
