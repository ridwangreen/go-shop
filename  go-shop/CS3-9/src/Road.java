/*
 * Road.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */

/**
 * Road
 *
 * @author rcd1575: Becca Dudley
 */
public class Road {
	
	private int length;
	private String road;
	
	
	/**
	 * Constructs a road
	 * 
	 * @param length  the road length
	 * @param road   the road name
	 */
	public Road(int length, String road){
		
		this.length = length;
		this.road = road;
	}
	
	
	/**
	 * get the road length
	 * 
	 * @return  road length
	 */
	public int getLength(){
		
		return length;
	}
	
	
	/**
	 * set the raod length
	 * 
	 * @param length   the new length for the road
	 */
	public void setLength(int length){
		
		this.length = length;
	}
	
	
	/**
	 * Get the road name
	 * 
	 * @return  road name
	 */
	public String getRoad(){
	
		return road;
	}

	
	/**
	 * Set the road name
	 * 
	 * @param road   the new name for the road
	 */
	public void setRoad(String road){
		
		this.road = road;
	}
	
}
