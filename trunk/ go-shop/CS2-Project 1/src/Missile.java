/*
 * Missile.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */

/**
 * hit or miss objects for battleship game
 *
 * @author Rebecca Dudley
 */
public class Missile extends BoardObject{
	
	public Location loc;
	
	/**
	 * A missile can be a hot or a miss and it knows its location
	 * @param hitOrMiss
	 * @param loc
	 * @throws Exception
	 */
	public Missile(String hitOrMiss){
		
		if(hitOrMiss.equals("Hit")){
			name = "X";
		}
		else if(hitOrMiss.equals("Miss")){
			name = "O";
		}
	}

	public String name() {
		return name;
	}
	
	
}
