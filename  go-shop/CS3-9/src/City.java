/*
 * City.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */

/**
 * City
 *
 * @author rcd1575: Becca Dudley
 */

public class City implements Comparable<City>{
	private int cost;
	private String name;
	private String predecessor;
	
	
	/**
	 * Construct the city with a given name
	 * By default the predecessor is null and the cost should
	 * be Integer.MAX_VALUE
	 * 
	 * @param name   the city name
	 */
	public City(String name){
		
		this.name = name;
		cost = Integer.MAX_VALUE;
		predecessor = null;
	}

	/**
	 * Get the name of this city
	 * 
	 * @return  the city name
	 */
	public String getName(){
	
		return name;
	}
	
	
	/**
	 * Set the name of this city
	 * 
	 * @param name   the new city's name
	 */
	public void setName(String name){
	
		this.name = name;
	}
	
	
	/**
	 * Get the predecessor associated with this city
	 * 
	 * @return   the predecessor city
	 */
	public String getPredecessor(){
		
		return predecessor;
	}
	
	
	/**
	 * Set the predecessor to the given city
	 * 
	 * @param predecessor  the predecessor city
	 */
	public void setPredecessor(String predecessor){
	
		this.predecessor = predecessor;
	}
	
	
	/**
	 * Get the cost associated with this city
	 * 
	 * @return  the cost to travel to this city
	 */
	public int getCost(){
		
		return cost;
	}
	
	
	/**
	 * Set the cost for a given city
	 * 
	 * @param cost   the cost to travel to this city
	 */
	public void setCost(int cost){
	
		this.cost = cost;
	}
	
	
	/**
	 * Compares two cities to see how they are ordered. 
	 * The primary quantity to compare is cost. 
	 * If costs are equal, compare the city names 
	 * (normal lexicographic ordering). 
	 * 
	 * @param other  the other city
	 * @return  <0 if this is "<" other 
	 */
	@Override
	public int compareTo(City other) {
		// TODO Auto-generated method stub
		if(cost == other.getCost()){
			return(name.compareTo(other.getName()));
		}
		return cost - other.getCost();
	}
	
	/**
	 * Compares this city to the other object. 
	 * They are equal if the both cost's and name's are equal.
	 * 
	 * @param other   the other object
	 * @return true if both objects are Cities, and they have the same cost and name
	 */
	public boolean equals(Object other){
		if(other.getClass().equals(this.getClass())){
			return cost == ((City)other).getCost() && name.equals(((City)other).getName());
		}
		return false;
	}

}
