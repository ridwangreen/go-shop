/*
 * PlayerBoard.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */

/**
 * The game board for the battleship game that the player sees
 *
 * @author Rebecca Dudley
 */
public class PlayerBoard {

	public int size;
	public BoardObject[][] gameBoard;
	
	 
	/**
	  * Creates a new board dimensions size
	  * 
	  *  @param size
	  */
	public PlayerBoard(int size){
		
		gameBoard = new BoardObject[size][size];
		this.size = size;
		for(int r = 0; r < size; r++){
			for(int c = 0; c < size; c++){
				gameBoard[r][c] = new Water();
			}
		}
		
	}
	
	/**
	 * prints the game board
	 * 
	 */
	public void printBoard(){
		
		for(int r = 0; r < size + 1; r++){
			for(int c = 0; c < size + 1; c++){
				if(r != 0 && c !=0){
					System.out.print(" " + gameBoard[r-1][c-1].name);
				}
				else{
					if(r ==0 && c==0){
						System.out.print("  ");
					}
					else if(r == 0){
						System.out.print(" " + (char)(c + 64));
					}
					
					else if(c == 0){
						System.out.print(" " + (char)(r + 64));
					}
				}
			}
			System.out.println();
			
		}
		
	}
	
}
