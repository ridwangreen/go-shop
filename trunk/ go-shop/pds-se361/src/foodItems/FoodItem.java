/*
 * FoodItem.java
 * 
 * Version:
 *     $Id$
 *     
 * Revision:
 *     $Log$
 */

package foodItems;

import java.util.Vector;

import orders.States;
import persistance.ConstantValues;

/**
 * an abstract class laying out what a FoodItem should be
 *
 * @author 
 *		Shaun DeVos
 * 		Brian Baum
 * 		Rebecca Dudley
 *		Jonathan Johnson
 *		Jonathan Olin
 *
 */
public abstract class FoodItem {
	
	/**
	 * The place in the order array, orderID is the id of the order
	 */
	protected int identifier;
	
	/**
	 * The Order this FoodItem belongs to
	 */
	protected int orderID;
	
	/**
	 * A value equivalent to the dollar value of the FoodItem
	 */
	private double total;
	
	/**
	 * An instance of the States class that maintains where the FoodItem is
	 */
	private States currentState;
	
	/**
	 * An instance of constant values that stores all common program variables
	 * and all of the statistics for the program.
	 */
	@SuppressWarnings ("unused")
	private ConstantValues cV;
	
	/**
	 * How long this item should remain in the Preparation Station
	 */
	private int prepTime;
	
	/**
	 * How long this item should remain in the Cooking Station
	 */
	private int cookTime;
	
	/**
	 * How many slots of the Oven this item occupies during the Cooking phase
	 */
	protected int ovenSpace;
	
	/**
	 * A Constructor for FoodItems, initializes the ConstantValues object, the
	 * orderID and the identifier to the given values.  Sets the current
	 * position of the FoodItem to the Preparation State.
	 *
	 * @param
	 * 		cV - should already contain all of the common program variables.
	 * @param
	 * 		orderID - The integer of the order that this item belongs to.
	 * @param
	 * 		identifier - the integer that this separates this FoodItem from
	 * 		the rest
	 */
	public FoodItem(ConstantValues cV, int orderID, int identifier){
		this.identifier = identifier;
		this.orderID = orderID;
		this.cV = cV;
		currentState = States.PrepState;	
	}
	
	/**
	 * Allows further information intended to be stored here, to be sent to
	 * this class from its children.
	 *
	 * @param 
	 * 		prepTime - The amount of time it takes to prepare this item.
	 * @param 
	 * 		cookTime - The amount of time it takes to cook this item.
	 * @param 
	 * 		ovenSpace - The amount of slots this item occupies in the Oven.
	 * @param 
	 * 		total - An double that represents the dollar value of this item.
	 * @return
	 * 		void
	 */
	public void setFoodAttributes(int prepTime, int cookTime, int ovenSpace,
			double total){
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.ovenSpace = ovenSpace;
		this.total = total;
	}
	
	public abstract String compressToString();
	
	/**
	 * A 'getter' for the prepare time of this item.
	 * 
	 * @return
	 * 		prepTime - An integer value representing the amount of time this
	 * 		item requires to prepare.
	 */
	public int getPrepTime() {
		return prepTime;
	}

	/**
	 * A 'setter' for the amount of time this item takes to prepare.
	 * 
	 * @param
	 * 		prepTime - An integer value representing the amount of time this
	 * 		item requires to prepare.
	 * @return
	 * 		void
	 */
	public void setPrepTime(int prepTime) {
		//System.out.println("prepTime: " + prepTime);
		this.prepTime = prepTime;
	}

	/**
	 * A 'getter' for the amount of time this item takes to cook.
	 * 
	 * @return
	 * 		cookTime - An integer value representing the amount of time this
	 * 		item requires to Cook
	 */
	public int getCookTime() {
		return cookTime;
	}

	/**
	 * A 'setter' for the amount of time this item takes to cook.
	 * 
	 * @param
	 * 		cookTime - An integer value representing the amount of time this
	 * 		item requires to cook.
	 * @return
	 * 		void
	 */
	public void setCookTime(int cookTime) {
		//System.out.println("cookTime: " + cookTime);
		this.cookTime = cookTime;
	}

	/**
	 * A 'getter' for the ID number for this item, separating if from the rest.
	 * 
	 * @return
	 * 		identifier - An integer value representing this item
	 */
	public int getIdentifier() {
		return identifier;
	}

	/**
	 * A 'getter' for the ID number of the order this item belongs to.
	 * 
	 * @return
	 * 		orderID - An integer value representing the order this item belongs
	 * 		to.
	 */
	public int getOrderID() {
		return orderID;
	}

	/**
	 * A 'getter' for the total dollar value this item costs.
	 * 
	 * @return
	 * 		total - A double representing this items dollar value.
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * A 'getter' for the amount of ovenSpace this items requires.
	 * 
	 * @return
	 * 		ovenSpace - An integer value representing the amount of space this
	 * 		item occupies in the oven.
	 */
	public int getOvenSpace() {
		return ovenSpace;
	}

	/**
	 * the 'getter' of the state that this item is in.
	 * 
	 * @return
	 * 		currentState - The state that this item is currently in.
	 */
	public States getCurrentState(){
		return currentState;
	}
	
	/**
	 * A 'setter' for the state that this item is currently in.
	 * 
	 * @param 
	 * 		newState - the state that this item has moved to.
	 * @return
	 * 		void
	 */
	public void setCurrentState(States newState){
		currentState = newState;
	}
	
	/**
	 * Returns all of the information about this item in a String format.
	 * @return
	 * 		- The string representation of this item.
	 * @Override
	 * 		toString - the object class' toString method
	 */
	public abstract Vector<String> toStringOrderEditor();
	
	/**
	 * Returns information about this item in a string neatly formatted for
	 * displaying in the GUI
	 *
	 * @return
	 * String - A string representation of this item.
	 */
	public String gUIString(){
		String s = this.identifier + ": " + this.getClass().getName();
		return s;
	}
	
}
