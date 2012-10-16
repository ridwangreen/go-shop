/*
 * Ship.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */
import java.util.ArrayList;
/**
 * The ships for the battleship program
 *
 * @author Rebecca Dudley
 */

public class Ship extends BoardObject{

	public ArrayList<Location> loc;
	public int length;
	public boolean horiz;
	public int numHits;
	
	/**
	 * constructor for the ship object
	 * a ship knows its name, starting and ending locations and length
	 * @param start
	 * @param end
	 */
	public Ship(String name, Location start, Location end){
		
		numHits = 0;
		this.name = name;
		loc = new ArrayList<Location>();
		horiz = start.row == end.row;
		if(horiz){
			if(start.compareTo(end) > 0){
				length = start.compareTo(end);
				for(int c = start.col; c <= end.col; c++){
					loc.add(new Location(start.row, c));
				}
			}else{
				length = Math.abs(start.compareTo(end));
				for(int c = end.col; c <= start.col; c++){
					loc.add(new Location(end.row, c));
				}
			}
		}else{
			if(start.compareTo(end) > 0){
				length = start.compareTo(end);
				for(int c = start.row; c <= end.row; c++){
					loc.add(new Location(c, start.col));
				}
			}else{
				length = Math.abs(start.compareTo(end));
				for(int c = end.row; c <= start.row; c++){
					loc.add(new Location(c, end.col));
				}
			}
		}
		
	}
	
	/**
	 * returns the name of the object
	 * 
	 * @return the name of the object "Ship"
	 */
	public String name() {
		return name;
	}
	
	

}
