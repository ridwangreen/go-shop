/*
 * Location.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */

/**
 * The location class for objects in battle ship
 *
 * @author Rebecca Dudley
 */
public class Location implements Comparable<Location>{

	public int row;
	public int col;
	
	public Location(int row, int col){
		
		this.row = row;
		this.col = col;

	}
	
	public boolean isValid(CheatBoard b){
		return row < b.size && col < b.size;
	}
	
	public int compareTo(Location o) {
		if(o.row == row && col == o.col){
			return 0;
		}
		else if(row != o.row){
			return o.row - row;
		}
		return o.col- col;
	}
}
