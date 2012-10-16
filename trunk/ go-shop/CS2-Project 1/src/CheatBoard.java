/*
 * CheatBoard.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */
import java.util.ArrayList;
/**
 * The game board for the battleship game that contains the ships
 *
 * @author Rebecca Dudley
 */
public class CheatBoard {
	
	public int size;
	public BoardObject[][] board;
	public ArrayList<Ship> ships;
	public ArrayList<Location> usedLocs;

	public CheatBoard(int size, ArrayList<Ship> myShips) throws CheatBoardException{
		
		usedLocs = new ArrayList<Location>();
		board = new BoardObject[size][size];
		this.size = size;
		ships = myShips;
		for(Ship s: ships){
			for(Location l: s.loc){
				boolean isin = false;
				for(Location l2: usedLocs){
					if(l.compareTo(l2)==0){
						isin =true;
					}
				}
				if(isin || !(new Location(l.row,l.col).isValid(this))){
					throw new CheatBoardException("Overlapping or out-of-bounds ships in file filename.");
				}
				usedLocs.add(l);
			}
		}
		Location current;
		for(int r = 0; r < size; r++){
			for(int c = 0; c < size; c++){
				current = new Location(r,c);
				boolean isIn = false;
				for(Location loc: usedLocs){
					if(loc.compareTo(current) == 0){
						isIn = true;
					}
				}
				if(!isIn){
					board[r][c] = new Water();
				}else{
					for(Ship s: ships){
						for(Location loc: s.loc)
						if(loc.compareTo(current) == 0){
							board[r][c] = s;
						}
					}
				}
			}
		}
		
	}
	
	public void printBoard(){
		
		for(int r = 0; r < size + 1; r++){
			for(int c = 0; c < size + 1; c++){
				if(r != 0 && c !=0){
					System.out.print(" " + board[r-1][c-1].name);
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
