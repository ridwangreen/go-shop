/**
 * TossedSalad.java
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
 * A FoodItem representing a TossedSalad, this class sets it's FoodItem values based
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
public class TossedSalad extends FoodItem {
	
	/**
	 * A Constructor of TossedSalad objects. Passes common FoodItem values to its
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
	public TossedSalad(ConstantValues cV, int orderID, int identifier) {
		super(cV, orderID, identifier);
		
		setFoodAttributes(Integer.parseInt(cV.getProperty("saladPrepTime")), 
				Integer.parseInt(cV.getProperty("saladCookTime")),
				Integer.parseInt(cV.getProperty("saladOvenSpace")),
				Double.parseDouble(cV.getProperty("saladPrice")));
	}

	/* (non-Javadoc)
	 * @see foodItems.FoodItem#compressToString()
	 */
	@Override
	public String compressToString() {
		String toString = "";
		toString = toString.concat( "T::" );
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
		vectorString.add("#" + this.identifier + " TossedSalad");
		return vectorString; 		
	}
}