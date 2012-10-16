/*
 * Frenzy.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */

/**
 * The main class for the Frenzy game
 *
 * @author Rebecca Dudley
 */
public class Frenzy {

	/**
	 * the main method.  Runs the program, creates a game with a board the size 
	 * specified by the command line arguments
	 * 
	 * @param args
	 */
	public static void main(String args[]){
		
		int size = 0;
		if(args.length < 1){
			size = 15;
		}else{
			try{
				size = Integer.parseInt(args[0]);
			}catch(NumberFormatException nfe){
				return;
			}
		}
		if(size < 7 || size > 30){
			System.err.println("Usage: board size N must be between 7 and 30");
			return;
		}
		FrenzyModel gModel = new FrenzyModel(size);
		BoardView gBoard = new BoardView(size, gModel);
		gModel.setView(gBoard);
	}
}
