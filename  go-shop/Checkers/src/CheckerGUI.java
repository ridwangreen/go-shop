/*
 * CheckerGUI.java
 * 
 * The actual board.
 *
 * Created on January 25, 2002, 2:34 PM
 * 
 * Version
 * $Id: CheckerGUI.java,v 1.1 2002/10/22 21:12:52 se362 Exp $
 * 
 * Revisions
 * $Log: CheckerGUI.java,v $
 * Revision 1.1  2002/10/22 21:12:52  se362
 * Initial creation of case study
 *
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author
 * @version
 */

public class CheckerGUI extends JFrame {

	// the facade for the game

	private static Facade theFacade; // the facade
	private Vector<JButton> possibleSquares = new Vector<JButton>();
	JPanel grid;
	private int timeRemaining;// the time remaining
	private JLabel playerOneLabel;
	private JLabel playerTwoLabel;
	private JLabel timeRemainingLabel;
	private JLabel secondsLeftLabel;
	private JButton ResignButton;
	private JButton DrawButton;
	private JLabel warningLabel, whosTurnLabel;
	private GameListener gListener;

	// the names and time left
	private static String playerOnesName = "", playerTwosName = "",
			timeLeft = "";

	/**
	 * 
	 * Constructor, creates the GUI and all its components
	 * 
	 * @param facade
	 *            the facade for the GUI to interact with
	 * @param name1
	 *            the first players name
	 * @param name2
	 *            the second players name
	 * 
	 */

	public CheckerGUI(Facade facade, String name1, String name2) {

		super("Checkers");

		// long names mess up the way the GUI displays
		// this code shortens the name if it is too long
		if (name1.length() > 7) {
			playerOnesName = name1.substring(0, 7);
		} else {
			playerOnesName = name1;
		}
		if (name2.length() > 7) {
			playerTwosName = name2.substring(0, 7);
		} else {
			playerTwosName = name2;
		}


		theFacade = facade;
		gListener = new GameListener(this, theFacade);
		register();

		initComponents();
		pack();
		update();
		// updateTime();
	}

	/**
	 * This method handles setting up the timer
	 */

	private void register() {

		try {
			theFacade.addActionListener(gListener);

		} catch (Exception e) {

			System.err.println(e.getMessage());
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * It initializes the components adds the buttons to the Vecotr of squares
	 * and adds an action listener to the components
	 * 
	 */
	private void initComponents() {

		this.setResizable(false);

		playerOneLabel = new JLabel();
		playerTwoLabel = new JLabel();
		whosTurnLabel = new JLabel();

		warningLabel = new JLabel();
		timeRemainingLabel = new JLabel();
		secondsLeftLabel = new JLabel();

		ResignButton = new JButton();
		ResignButton.addActionListener(gListener);

		DrawButton = new JButton();
		DrawButton.addActionListener(gListener);

		// sets the layout and adds listener for closing window
		BorderLayout bl = new BorderLayout();
		bl.setHgap(15);
		getContentPane().setLayout(bl);
		grid = new JPanel();
		grid.setLayout(new GridLayout(8, 8));

		// add window listener
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				exitForm(evt);
			}
		});

		int index = 0;
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {

				JButton b = new JButton();
				b.setPreferredSize(new Dimension(80,80));
				

				if ((r % 2 == 0 && c % 2 == 0) || (r % 2 != 0 && c % 2 != 0)) {
					b.setBackground(Color.black);

				} else {
					b.setBackground(
							new Color(204, 204, 153));
					b.addActionListener(gListener);
					b.setActionCommand("" + index);

				}

				grid.add(b);
				index++;
			}
		}

		getContentPane().add(grid, BorderLayout.CENTER);
		playerOneLabel.setText("Player 1:     " + playerOnesName);
		playerOneLabel.setForeground(Color.black);

		getContentPane().add(playerOneLabel, BorderLayout.NORTH);
		;

		playerTwoLabel.setText("Player 2:     " + playerTwosName);
		playerTwoLabel.setForeground(Color.black);

		getContentPane().add(playerTwoLabel, BorderLayout.SOUTH);

		JPanel rightSide = new JPanel();
		GridLayout gl = new GridLayout(6,1);
		gl.setVgap(40);
		rightSide.setLayout(gl);
		
		whosTurnLabel.setText("");
		whosTurnLabel.setForeground(new Color(0, 100, 0));

		rightSide.add(whosTurnLabel);

		warningLabel.setText("");
		warningLabel.setForeground(Color.red);

		rightSide.add(warningLabel);

		timeRemainingLabel.setText("Time Remaining:");
		timeRemainingLabel.setForeground(Color.black);

		rightSide.add(timeRemainingLabel);

		secondsLeftLabel.setText(timeLeft + " sec.");
		secondsLeftLabel.setForeground(Color.black);

		rightSide.add(secondsLeftLabel);

		
		ResignButton.setActionCommand("resign");
		ResignButton.setText("Resign");

		rightSide.add(ResignButton);

		DrawButton.setActionCommand("draw");
		DrawButton.setText("Draw");

		rightSide.add(DrawButton);

		getContentPane().add(rightSide, BorderLayout.EAST);
	}

	/**
	 * 
	 * Exit the Application
	 * 
	 * @param the
	 *            window event
	 * 
	 */
	private void exitForm(java.awt.event.WindowEvent evt) {
		// TODO: Use of Facade, possibly change
		theFacade.pressQuit();
	}

	/**
	 * Updates the GUI reading the pieces in the board Puts pieces in correct
	 * spaces, updates whos turn it is
	 * 
	 * @param the
	 *            board
	 */

	public void update() {

		if (checkEndConditions()) {

			// TOOD: The GUI checks end conditions...Board should do this
			theFacade.showEndGame(" ");
		}
		// the board to read information from
		// TODO: Creation of Board, remove
		// TODO: Use of Facade, possibly change
		Board board = theFacade.stateOfBoard();
		// a temp button to work with
		JButton temp = new JButton();

		// go through the board
		for (int i = 1; i < board.sizeOf(); i++) {

			// if there is a piece there
			if (board.occupied(i)) {

				// check to see if color is blue
				if (board.colorAt(i) == Color.blue) {

					// if there is a single piece there
					if ((board.getPieceAt(i)).getType() == Board.SINGLE) {

						// show a blue single piece in that spot board
						temp = (JButton) grid.getComponent(i);

						// get the picture from the web
						try {
							temp.setIcon(new ImageIcon(new URL(
									"file:BlueSingle.gif")));
						} catch (MalformedURLException e) {
							System.out.println(e.getMessage());
						}
						// if there is a kinged piece there
						// TODO: Use of Board, remove
					} else if ((board.getPieceAt(i)).getType() == Board.KING) {

						// show a blue king piece in that spot board
						temp = (JButton) grid.getComponent(i);

						// get the picture formt the web
						try {
							temp.setIcon(new ImageIcon(new URL(
									"file:BlueKing.gif")));
						} catch (Exception e) {
						}
					}
					// check to see if the color is white
					// TODO: Use of Board, remove
				} else if (board.colorAt(i) == Color.white) {

					// if there is a single piece there
					// TODO: Use of Board, remove
					if ((board.getPieceAt(i)).getType() == Board.SINGLE) {

						// show a blue single piece in that spot board
						temp = (JButton) grid.getComponent(i);

						// get the picture from the web
						try {
							temp.setIcon(new ImageIcon(new URL(
									"file:WhiteSingle.gif")));
						} catch (Exception e) {
						}

						// if there is a kinged piece there
						// TODO: Use of Board, remove
					} else if ((board.getPieceAt(i)).getType() == Board.KING) {

						// show a blue king piece in that spot board
						temp = (JButton) grid.getComponent(i);

						// get the picture from the web
						try {
							temp.setIcon(new ImageIcon(new URL(
									"file:WhiteKing.gif")));
						} catch (Exception e) {
						}
					}
					// if there isnt a piece there
				}
			} else {
				// show no picture
				temp = (JButton) grid.getComponent(i);
				temp.setIcon(null);
			}
		}

		// this code updates whos turn it is
		// TODO: Use of Facade, possibly change
		if (theFacade.whosTurn() == 2) {
			playerTwoLabel.setForeground(Color.red);
			playerOneLabel.setForeground(Color.black);
			whosTurnLabel.setText(playerTwosName + "'s turn ");
			// TODO: Use of Facade, possibly change
		} else if (theFacade.whosTurn() == 1) {
			playerOneLabel.setForeground(Color.red);
			playerTwoLabel.setForeground(Color.black);
			whosTurnLabel.setText(playerOnesName + "'s turn");
		}
	}

	/**
	 * 
	 * Update the timer
	 * 
	 */

	public void updateTime() {

		// TODO: Use of Facade, possibly change
		if (theFacade.getTimer() > 0) {

			// if the time has run out but not in warning time yet
			// display warning and count warning time
			if (timeRemaining <= 0 && (warningLabel.getText()).equals("")) {
				timeRemaining = theFacade.getTimerWarning();
				warningLabel.setText("Time is running out!!!");

				// if the time has run out and it was in warning time quit game
			} else if (timeRemaining <= 0
					&& !(warningLabel.getText()).equals("")) {

				// TODO: Use of Facade, possibly change
				theFacade.pressQuit();

			} else {

				timeRemaining--;
			}

			secondsLeftLabel.setText(timeRemaining + " sec.");

		} else {
			secondsLeftLabel.setText("*****");
		}
	}

	/**
	 * Checks the ending condotions for the game see if there a no pieces left
	 * 
	 * @return the return value for the method true if the game should end false
	 *         if game needs to continue
	 */

	public boolean checkEndConditions() {

		// the return value
		boolean retVal = false;
		try {
			// the number of each piece left
			int whitesGone = 0, bluesGone = 0;

			// the board to work with
			// TODO: Use of Board, remove
			// TODO: Use of Facade, possibly change
			Board temp = theFacade.stateOfBoard();

			// go through all the spots on the board
			for (int i = 1; i < temp.sizeOf(); i++) {
				// if there is a piece there
				if (temp.occupied(i)) {
					// if its a blue piece there
					if ((temp.getPieceAt(i)).getColor() == Color.blue) {
						// increment number of blues
						bluesGone++;
						// if the piece is white
					} else if ((temp.getPieceAt(i)).getColor() == Color.white) {
						// increment number of whites
						whitesGone++;
					}
				}
			}// end of for loop

			// if either of the number are 0
			if (whitesGone == 0 || bluesGone == 0) {
				retVal = true;
			}

		} catch (Exception e) {

			System.err.println(e.getMessage());
		}
		return retVal;

	}// checkEndConditions

	public void setTimeRemaining(int time) {

		timeRemaining = time;
	}

}// checkerGUI.java
