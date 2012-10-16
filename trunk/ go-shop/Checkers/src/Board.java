/**
 * Board.java
 *
 * Version:
 *     $Id: Board.java,v 1.1 2002/10/22 21:12:52 se362 Exp $
 *
 * Revisions:
 *     $Log: Board.java,v $
 *     Revision 1.1  2002/10/22 21:12:52  se362
 *     Initial creation of case study
 *
 */
import java.awt.Color;
import java.util.TreeMap;
import java.util.Vector;

/**
 * This class represents the board on which checkers is being played. The board
 * holds a collection of pieces.
 * 
 * @invariant all variables have valid values
 * 
 * @author
 */
public class Board {

	public static final int CHECKERBOARD_R = 8;
	public static final int CHECKERBOARD_C = 8;
	public static final int HOME_ROWS_PER_PLAYER = 3;
	public static final int PIECES_PER_PLAYER = 12;
	public static final int SINGLE = 0;
	public static final int KING = 1;
	private Driver theDriver;
	private Piece[][] spaces;
	private int whitePieceCount;
	private int bluePieceCount;

	/**
	 * This constructor creates a new board at the beginning of the game
	 */

	public Board(Driver driver) {

		// Save the driver for communication throughout game
		theDriver = driver;

		whitePieceCount = PIECES_PER_PLAYER;
		bluePieceCount = PIECES_PER_PLAYER;
		spaces = new Piece[CHECKERBOARD_R][CHECKERBOARD_C];

		// create pieces
		for (int r = 0; r < HOME_ROWS_PER_PLAYER; r++) {
			for (int c = 0; c < CHECKERBOARD_C; c++) {
				if (r % 2 == 1) {
					if (c % 2 == 0) {
						spaces[r][c] = new SinglePiece(Color.white, Color.blue);
					} else {
						spaces[r + (CHECKERBOARD_C - HOME_ROWS_PER_PLAYER)][c] = new SinglePiece(
								Color.blue, Color.white);
					}
				} else {
					if (c % 2 == 1) {
						spaces[r][c] = new SinglePiece(Color.white, Color.blue);
					} else {
						spaces[r + (CHECKERBOARD_C - HOME_ROWS_PER_PLAYER)][c] = new SinglePiece(
								Color.blue, Color.white);
					}
				}
			}
		}

	}

	/**
	 * Validates the move of a piece from one location to the next for a given
	 * color piece. Returns NONE if the move wasn't valid, MOVE if a single move
	 * or jump could be made, and JUMP if a jump was made and another is available.
	 * 
	 * @param move to validate
	 * @return the type of move that was made
	 * 
	 * @author Samantha Shandrow
	 */
	public MoveStatus validateMove(Move currMove) {

		Piece pieceToMove;

		Coordinate start = new Coordinate(currMove.startLocation());
		Coordinate end = new Coordinate(currMove.endLocation());

		// check if there is a piece to move
		if (!occupied(currMove.startLocation())) {
			return MoveStatus.NONE;
		}

		// check if there is a piece in the way of the move
		if (occupied(currMove.endLocation())) {
			return MoveStatus.NONE;
		}

		pieceToMove = getPieceAt(currMove.startLocation());

		// check if you are trying to move your own piece
		Color piece = pieceToMove.getColor();
		Color player = currMove.getColor();
		if (pieceToMove.getColor() != currMove.getColor()) {
			return MoveStatus.NONE;
		}

		// Calculate the offset between the start and end coordinates
		int cOffset = start.getR() - end.getR();
		int rOffset = start.getC() - end.getC();

		// Check if the move is a valid jump
		if (rOffset == 2 || rOffset == -2) {
			if (pieceToMove.validJump(cOffset)) {
				Piece jumped = getPieceAt(start.getR() - (cOffset / 2),
						start.getC() - (rOffset / 2));

				if (jumped != null && jumped.getColor() != currMove.getColor()) {
					removePiece(start.getR() - (cOffset / 2), start.getC()
							- (rOffset / 2));
					movePiece(currMove.startLocation(), currMove.endLocation());
					if (pieceToMove.checkPossibleJumps(getNeighbors(end.getR(),
							end.getC()))) {
						return MoveStatus.JUMP;
					} else {
						return MoveStatus.MOVE;
					}
				}
			}
		}

		// Check if the move is a valid single move
		if (rOffset == 1 || rOffset == -1) {
			if (pieceToMove.validSingleMove(cOffset)) {
				// If there are any legal jumps, must make those
				if (checkPossibleJumps(currMove.getColor())) {
					System.out.println("There are possible jumps");
					return MoveStatus.NONE;
				} else {
					movePiece(currMove.startLocation(), currMove.endLocation());
					return MoveStatus.MOVE;
				}
			}
		}

		// If we reach this point, the move was not legal
		return MoveStatus.NONE;
	}

	/**
	 * Determine if there are any possible jumps for the current player
	 * 
	 * @param playerColor the color of the player to check for jumps
	 * @return whether jumps are available.
	 * 
	 */
	public boolean checkPossibleJumps(Color playerColor) {

		// Loop over the board
		for (int r = 0; r < CHECKERBOARD_R; r++) {
			for (int c = 0; c < CHECKERBOARD_C; c++) {
				Piece curr = getPieceAt(r, c);

				if (curr != null) {
					// If you find a piece of the correct color, check if it has
					// any possible jumps
					if (curr.getColor() == playerColor) {
						boolean jumpFound = curr
								.checkPossibleJumps(getNeighbors(r, c));

						if (jumpFound) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	/**
	 * Used after a move has been completed to check to see if the conditions
	 * have been met for ending the game. a boolean is returned indicating
	 * whether or not those conditions have been met.
	 * 
	 * @return true if there are no more of a certain piece color
	 * 
	 * @pre a capture was successful
	 */
	public boolean checkEndCondition() {
		return (whitePieceCount == 0 || bluePieceCount == 0);
	}

	/**
	 * Move the piece at the start position to the end position
	 * 
	 * @param start
	 *            - current location of the piece
	 * @param end
	 *            - the position where piece is moved
	 * 
	 * @return true if the operation succeeded
	 */
	public boolean movePiece(int start, int end) {

		Coordinate startCoord = new Coordinate(start);
		Coordinate endCoord = new Coordinate(end);

		boolean successful = false;
		// check if the end position of the piece is occupied
		if (!occupied(endCoord.getR(), endCoord.getC())) {
			spaces[endCoord.getR()][endCoord.getC()] = spaces[startCoord.getR()][startCoord
					.getC()];
			spaces[startCoord.getR()][startCoord.getC()] = null;

			// Check to king the piece
			if (endCoord.getR() == 0
					&& spaces[endCoord.getR()][endCoord.getC()].getColor() == Color.blue) {

				spaces[endCoord.getR()][endCoord.getC()] = new KingPiece(
						Color.blue, Color.white);
			}

			// Check to king the piece
			else if (endCoord.getR() == 7
					&& spaces[endCoord.getR()][endCoord.getC()].getColor() == Color.white) {

				spaces[endCoord.getR()][endCoord.getC()] = new KingPiece(
						Color.white, Color.blue);
			}

			successful = true;
		}

		return successful;

	}

	/**
	 * This method checks if the space on the board contains a piece
	 * 
	 * @param space
	 *            - the space that needs to be checked
	 * 
	 * @return true or false depending on the situation
	 */
	public boolean occupied(int space) {

		Coordinate coord = new Coordinate(space);

		return !(spaces[coord.getR()][coord.getC()] == null);
	}

	public boolean occupied(int r, int c) {

		return !(spaces[r][c] == null);
	}

	/**
	 * This method removes piece at the position space
	 * 
	 * @param space
	 *            - the positon of the piece to be removed
	 */
	public boolean removePiece(int space) {

		Coordinate coord = new Coordinate(space);

		boolean successful = false;

		if (occupied(space)) {
			if (spaces[coord.getR()][coord.getC()].getColor() == Color.white) {
				whitePieceCount--;
			} else {
				bluePieceCount--;
			}
			spaces[coord.getR()][coord.getC()] = null;
			successful = true;
		}

		return successful;
	}

	/**
	 * This method removes piece at the position space
	 * 
	 * @param r
	 *            -the row of the piece
	 * @param c
	 *            -the column of the piece
	 * @return -whether or not the piece was successfull removed
	 */
	private boolean removePiece(int r, int c) {
		boolean successful = false;

		if (occupied(r, c)) {
			if (spaces[r][c].getColor() == Color.white) {
				whitePieceCount--;
			} else {
				bluePieceCount--;
			}
			spaces[r][c] = null;
			successful = true;
		}

		return successful;
	}

	/**
	 * 
	 * This method returns the color of the piece at a certain space
	 * 
	 * @param space
	 *            - the position of the piece on the board
	 * 
	 * @return the color of this piece
	 * 
	 */
	public Color colorAt(int space) {

		Coordinate coord = new Coordinate(space);
		Color color = null;

		if (occupied(space)) {
			color = spaces[coord.getR()][coord.getC()].getColor();
		} else {
			color = null;
		}

		return color;

	}

	/**
	 * 
	 * This method returns the piece at the certain position
	 * 
	 * @param space
	 *            - the space of the piece
	 * 
	 * @return the piece at that space
	 * 
	 */
	public Piece getPieceAt(int space) {

		Coordinate coord = new Coordinate(space);

		Piece piece;
		if (occupied(space)) {
			piece = spaces[coord.getR()][coord.getC()];
		} else {
			piece = null;
		}

		return piece;

	}

	/**
	 * 
	 * This method returns the piece at the certain position
	 * 
	 * @param r
	 *            - the row of that piece
	 * @param c
	 *            - the column of that piece
	 * 
	 * @return the piece at that space
	 */
	public Piece getPieceAt(int r, int c) {
		Piece piece;

		if (occupied(r, c)) {
			piece = spaces[r][c];
		} else {
			piece = null;
		}

		return piece;
	}

	/**
	 * This method returns if there is a piece of color on the board
	 * 
	 * @param color
	 *            - the color of the piece
	 * 
	 * @return true if there is a piece of color left on the board else return
	 *         false
	 */
	public boolean hasPieceOf(Color color) {

		boolean successful = false;
		if (color == Color.white && whitePieceCount > 0) {
			successful = true;
		} else if (color == Color.blue && bluePieceCount > 0) {
			successful = true;
		}

		return successful;

	}

	/**
	 * This method returns a vector containing all blue Pieces
	 * 
	 * @return blue pieces on the board
	 */
	public Vector<Piece> bluePieces() {

		Vector<Piece> bluePieces = new Vector<Piece>();

		for (int r = 0; r < CHECKERBOARD_C; r++) {
			for (int c = 0; c < CHECKERBOARD_R; r++) {
				if (occupied(r, c)) {
					if (spaces[r][c].getColor() == Color.blue) {
						bluePieces.addElement(spaces[r][c]);
					}
				}
			}
		}
		return bluePieces;
	}

	/**
	 * This method returns a vector containing all white Pieces
	 * 
	 * @return blue pieces on the board
	 */
	public Vector<Piece> whitePieces() {

		Vector<Piece> whitePieces = new Vector<Piece>();

		for (int r = 0; r < CHECKERBOARD_C; r++) {
			for (int c = 0; c < CHECKERBOARD_R; r++) {
				if (occupied(r, c)) {
					if (spaces[r][c].getColor() == Color.white) {
						whitePieces.addElement(spaces[r][c]);
					}
				}
			}
		}
		return whitePieces;
	}

	/**
	 * Return the size of the checkerboard.
	 * 
	 * @return size of checkerboard
	 */
	public int sizeOf() {
		return CHECKERBOARD_R * CHECKERBOARD_C;
	}

	/**
	 * Generate a map of the eight squares legally adjacent to the given
	 * location These spaces are the two squares diagnolly adjacent in all four
	 * directions
	 * 
	 * @param r
	 *            the starting row
	 * @param c
	 *            the starting column
	 * 
	 * @return a collection of the neighbors for the piece, as few as two, as
	 *         many as eight
	 */
	private TreeMap<String, Piece> getNeighbors(int r, int c) {
		TreeMap<String, Piece> neighbors = new TreeMap<String, Piece>();

		// Add the left neighbors
		if (c - 1 >= 0) {
			// Add the lower left neighbor
			if (r + 1 <= 7) {
				neighbors.put("lowerLeft", getPieceAt(r + 1, c - 1));
			}

			// Add the upper left neighbor
			if (r - 1 >= 0) {
				neighbors.put("upperLeft", getPieceAt(r - 1, c - 1));
			}
		}

		// Add the far left neighbors
		if (c - 2 >= 0) {
			// Add the far upper left neighbor
			if (r - 2 >= 0) {
				neighbors.put("farUpperLeft", getPieceAt(r - 2, c - 2));
			}

			// Add the far lower left neighbor
			if (r + 2 <= 7) {
				neighbors.put("farLowerLeft", getPieceAt(r + 2, c - 2));
			}
		}

		// Add the right neighbors
		if (c + 1 <= 7) {
			// Add the lower right neighbor
			if (r + 1 <= 7) {
				neighbors.put("lowerRight", getPieceAt(r + 1, c + 1));
			}

			// Add the upper right neighbor
			if (r - 1 >= 0) {
				neighbors.put("upperRight", getPieceAt(r - 1, c + 1));
			}
		}

		// Add the far right neighbors
		if (c + 2 <= 7) {
			// Add the far lower right neighbor
			if (r + 2 <= 7) {
				neighbors.put("farLowerRight", getPieceAt(r + 2, c + 2));
			}

			// Add the far upper right neighbor
			if (r - 2 >= 0) {
				neighbors.put("farUpperRight", getPieceAt(r - 2, c + 2));
			}
		}

		return neighbors;
	}

}// Board

