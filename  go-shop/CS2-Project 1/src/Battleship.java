/*
 * Battleship.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * The main class for the Battleship game
 *
 * @author Rebecca Dudley
 */

public class Battleship {

	public static final int NUM_ARGS = 2;
	
	public static void printStats(int fire, int hit, int miss, int sunk){
		System.out.println("Number of missiles fired: " + fire);
		System.out.println("Number of hits: " + hit);
		System.out.println("Number of misses: " + miss);
		System.out.println("Hit ratio: " + 100 *((double)hit/(double)fire) + "%");
		System.out.println("Number of ships sunk: " + sunk);
	}
	
	public static void main(String args[]){
		
		if(args.length != NUM_ARGS){
			System.err.println("Usage: java Battleship N config-file");
		}
		else{	
	
			try{
				 Integer.parseInt(args[0]);
			}
		
			catch(NumberFormatException nfe){	
				System.err.println("Usage: java Battleship N config-file");
				return;
			}
			
			int size = Integer.parseInt(args[0]);
			
			if(size < 5){
				System.err.println("Board must be at least 5 by 5.");
			}
			else if(size > 26){
				System.err.println("Board must be at most 26 by 26.");
			}
			else{
				
				ArrayList<Ship> ships = new ArrayList<Ship>();
				int numShips;
				BufferedReader inFile;
				try{
					inFile = new BufferedReader(new FileReader(args[1]));
				}catch(FileNotFoundException fnfe){
					System.err.println("Cannot open file " + args[1] + ".");
					return;
				}
				String line="";
				try{
					numShips = Integer.parseInt(inFile.readLine());
					for(int x = 0; x < numShips; x++){
						line = inFile.readLine();
						int count = 0;
						int r1 = 0;
						int c1 = 0;
						int r2 = 0;
						int c2 = 0;
						for(int i = 0; i < line.length(); i++){
							if(line.charAt(i) != ' ' && line.charAt(i) != '\t'){
								switch(count){
								case(0):
									r1 = line.charAt(i);
									break;
								case(1):
									c1 = line.charAt(i);
									break;
								case(2):
									r2 = line.charAt(i);
									break;
								case(3):
									c2 = line.charAt(i);
									break;
								}
								count++;
							}
						}
						String shipName ="";
						ships.add(new Ship(shipName += (char)(x+65), new Location((int)r1-65,(int)c1-65), new Location((int)r2-65,(int)c2-65)));
					}
				}catch(IOException ioe){
					System.err.println("Cannot open file " + args[1] + ".");
					return;
				}
				CheatBoard cBoard;
				try{
					cBoard = new CheatBoard(size, ships);
				}
				catch(CheatBoardException cbe){
					System.err.println(cbe.getMessage());
					return;
				}
				PlayerBoard pBoard = new PlayerBoard(size);
				pBoard.printBoard();
				System.out.print("> ");
				Scanner input = new Scanner(System.in);
		        String response = input.nextLine();
		        boolean hasWon = false;
		        int numShipsSunk = 0;
		        int numFired = 0;
		        int numHit = 0;
		        int numMissed = 0;
				do{
					
					if(response.equals("board")){
						pBoard.printBoard();
					}else if(response.equals("ships")){
						cBoard.printBoard();
					}
					else if(response.equals("stats")){
						printStats(numFired, numHit, numMissed, numShipsSunk);
					}else if(response.equals("help")){
						System.out.println("Possible commands:");
						System.out.println("board - displays the user's board");
						System.out.println("ships - displays the placement of the ships");
						System.out.println("fire r c - fires a missile at the cell at [r,c]");
						System.out.println("stats - prints out the game statistics");
						System.out.println("quit - exits the game");
					}
					else if(response.contains("fire")){
						if(response.length() < 8 ){
							System.out.println("Illegal coordinates.");
						}else{
							Location aim = new Location((int)response.charAt(5)-65,(int)response.charAt(7)- 65);
							if(!aim.isValid(cBoard)){
								System.out.println("Illegal coordinates.");
							}
							else{
								numFired++;
								boolean hit = false;
								for(Ship s: ships){
									for(Location l: s.loc){
										if(aim.compareTo(l)== 0){
											System.out.println("Hit!");
											hit = true;
											s.numHits++;
											numHit++;
											if(s.numHits == s.length + 1){
												numShipsSunk++;
												System.out.println("Sunk!");
											}
										}
									}
								}
								if(hit){
									pBoard.gameBoard[aim.row][aim.col]= new Missile("Hit");
									if(numShipsSunk == ships.size()){
										System.out.println("You win!");
										printStats(numFired,numHit,numMissed,numShipsSunk);
										hasWon = true;
									}
								}else{
									pBoard.gameBoard[aim.row][aim.col]= new Missile("Miss");
									System.out.println("Miss!");
									numMissed++;
								}
							}
						}
					}
					else if(!response.equals("")){
						System.out.println("Illegal command.");
					}
					if(!response.equals("quit") && !hasWon){
						System.out.print("> ");
						response = input.nextLine();
					}
					
				}while(!response.equals("quit") && !hasWon);
			}
			
		}
	}
}


