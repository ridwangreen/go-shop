/*
 * Pizza.java
 * 
 * Version:
 *     $Id$
 *     
 * Revision:
 *     $Log$
 */

package foodItems;

import java.util.Iterator;
import java.util.Vector;
import persistance.ConstantValues;

/**
 * A FoodItem representing a Pizza, this class sets it's FoodItem values based
 * on which size it is and how many toppings it has.
 *
 * @author 
 *		Shaun DeVos
 * 		Brian Baum
 * 		Rebecca Dudley
 *		Jonathan Johnson
 *		Jonathan Olin
 *
 */
public class Pizza extends FoodItem {
	
	/**
	 * A Vector of strings containing all the toppings to be on this pizza.
	 */
	private Vector<String> toppings;
	
	private String size;
	
	/**
	 * A Constructor of Pizza objects. Passes common FoodItem values to its
	 * Super class.  Then sets all of the values in its parent by determining
	 * if the Pizza is a small, medium or large (S, M, L).
	 *
	 * @param
	 * 		cV - should already contain all of the common program variables.
	 * @param 
	 * 		toppings - A Vector of strings containing all the toppings on this
	 * 		pizza.
	 * @param
	 * 		identifier - the integer that this separates this FoodItem from
	 * 		the rest 
	 * @param
	 * 		orderID - The integer of the order that this item belongs to. 
	 * @param 
	 * 		pizza_Size - The size of this pizza.
	 */
	public Pizza(ConstantValues cV, Vector<String> toppings, int identifier,
			int orderID, char pizza_Size){
		super(cV, orderID, identifier);
		
		// set order information for this Pizza
		
		this.toppings = toppings;
		this.identifier = identifier;
		this.orderID = orderID;
		
		// initialize common variables
		
		int prepTime = 0;
		int cookTime = 0;
		double price = 0;
		double pricePerTopping = 0;
		
		// Determine what size this Pizza is.  Then set the attributes
		
		switch(pizza_Size) {
		case 'L': prepTime = Integer.parseInt(cV.getProperty("largePizzaPrepTime"));
				  cookTime = Integer.parseInt(cV.getProperty("largePizzaCookTime"));
				  this.ovenSpace = Integer.parseInt(cV.getProperty("largePizzaOvenSpace"));
				  price = Double.parseDouble(cV.getProperty("largePizzaPrice"));
				  pricePerTopping = Double.parseDouble(cV.getProperty("largePizzaToppingsPrice"));
				  size = "Large";
				  
				  break;
		
		case 'M': prepTime = Integer.parseInt(cV.getProperty("mediumPizzaPrepTime"));
				  cookTime = Integer.parseInt(cV.getProperty("mediumPizzaCookTime"));
				  this.ovenSpace = Integer.parseInt(cV.getProperty("mediumPizzaOvenSpace"));
				  price = Double.parseDouble(cV.getProperty("mediumPizzaPrice"));
				  pricePerTopping = Double.parseDouble(cV.getProperty("mediumPizzaToppingsPrice"));
				  size = "Medium";
		  
				  break;
		
		case 'S': prepTime = Integer.parseInt(cV.getProperty("smallPizzaPrepTime"));
		  		  cookTime = Integer.parseInt(cV.getProperty("smallPizzaCookTime"));
		  		  this.ovenSpace = Integer.parseInt(cV.getProperty("smallPizzaOvenSpace"));
		  		  price = Double.parseDouble(cV.getProperty("smallPizzaPrice"));
		  		  pricePerTopping = Double.parseDouble(cV.getProperty("smallPizzaToppingsPrice"));
		  		  size = "Small";

		  		  break;
		}
		
		// Calculate the price of this item and set the value
		
		// each topping is only a half
		pricePerTopping = pricePerTopping/2;
		
		for(String top: toppings){
			if(top.equals( "Pepperoni(L)" )){
				price = price - pricePerTopping;
			}else if(top.equals( "Pepperoni(R)" )){
				price = price - pricePerTopping;
			}
				
		}
		
		double total = price + pricePerTopping*this.toppings.size();
		
		setFoodAttributes(prepTime, cookTime, ovenSpace, total);
	}

	/**
	 * The 'getter' for the toppings on the Pizza
	 *
	 * @return
	 * 		Vector<String> - the list of toppings on the pizza
	 */
	public Vector<String> getToppings() {
		return this.toppings;
	}
	
	public void replaceToppings(Vector<String> newToppings){
		this.toppings = newToppings;
	}
	/* (non-Javadoc)
	 * @see foodItems.FoodItem#compressToString()
	 */
	@Override
	public String compressToString() {
		String toString = "";
		toString = toString.concat( size.charAt( 0 ) + ":" );
		
		//toppings
		toString = toString.concat( "!" );
		for(Iterator<String> i = toppings.iterator(); i.hasNext(); ){
			toString = toString.concat( i.next() + "!" );
		}
		
		toString = toString.concat( ":" );
		toString = toString.concat( this.identifier + ":");
		toString = toString.concat( this.orderID + ":" );
		return toString;
	}
	
	/**
	 * @see foodItems.FoodItem.gUIString()
	 */
	@Override
	public String gUIString(){
		String s = this.identifier + ": " + this.size + " " + this.getClass().getName() + " with " + toppings.toString();
		return s;
	}

	/* (non-Javadoc)
	 * @see foodItems.FoodItem#toStringOrderEditor()
	 */
	@Override
	public Vector<String> toStringOrderEditor(){
		Vector<String> vectorString = new Vector<String>();
		vectorString.add("#" + this.identifier + " " + size + " Pizza");
		for(String top : toppings){
			vectorString.add( "  "  + top);
		}
		return vectorString; 		
	}
}