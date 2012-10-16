/**
 * PizzaLog.java
 * 
 * Version:
 *     $id$
 *     
 * Revision:
 *     $log$
 */

package foodItems;

import java.util.Vector;

import persistance.ConstantValues;

/**
 * A FoodItem representing a PizzaLog, this class sets it's FoodItem values based
 * on values in it's creation.
 *
 * @author 
 *		Shaun DeVos
 * 		Brian Baum
 * 		Rebecca Dudley
 *		Jonathan Johnson
 *		Jonathan Olin
 *
 */
public class PizzaLog extends FoodItem {

	/**
	 * A Constructor of PizzaLog objects. Passes common FoodItem values to its
	 * Super class.
	 *
	 * @param
	 * 		cV - should already contain all of the common program variables.
	 * @param
	 * 		orderID - The integer of the order that this item belongs to.
	 * @param
	 * 		identifier - the integer that this separates this FoodItem from
	 * 		the rest
	 */
	public PizzaLog(ConstantValues cV, int orderID, int identifier) {
		super(cV, orderID, identifier);
		
		Integer.parseInt(cV.getProperty("pizzaLogPrepTime"));
		setFoodAttributes(Integer.parseInt(cV.getProperty("pizzaLogPrepTime")), 
				Integer.parseInt(cV.getProperty("pizzaLogCookTime")),
				Integer.parseInt(cV.getProperty("pizzaLogOvenSpace")),
				Double.parseDouble(cV.getProperty("pizzaLogPrice")));
	}

	/* (non-Javadoc)
	 * @see foodItems.FoodItem#compressToString()
	 */
	@Override
	public String compressToString() {
		String toString = "";
		toString = toString.concat( "P::" );
		toString = toString.concat( this.identifier + ":");
		toString = toString.concat( this.orderID + ":" );
		return toString;
	}

	/* (non-Javadoc)
	 * @see foodItems.FoodItem#toStringOrderEditor()
	 */
	@Override
	public Vector<String> toStringOrderEditor(){
		Vector<String> vectorString = new Vector<String>();
		vectorString.add("#" + this.identifier + " PizzaLog");
		return vectorString; 		
	}
}
