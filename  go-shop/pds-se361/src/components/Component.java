/**
 * Component.java
 * 
 * Version:
 *     $Id$
 *     
 * Revision:
 *     $Log$
 */

package components;

import java.util.ArrayList;
import java.util.Vector;
import foodItems.FoodItem;

/**
 * an abstract class laying out the stations a Food Item will pass through
 *
 * @author 
 * 		Shaun DeVos
 *		Brian Baum
 * 		Rebecca Dudley
 * 		Jonathan Johnson
 * 		Jonathan Olin
 */
public abstract class Component {
	
	/**
	 * An array list that stores the FoodItems currently in the component.
	 * Accessible by all children classes.
	 */
	protected ArrayList<FoodItem> queue;
	
	
	/**
	 * Constructs Component objects.
	 * Initializes the array list objects. 
	 *
	 * 
	 */	
	Component() {
		queue = new ArrayList<FoodItem>();
	}
	
	/**
	 * Updates the time and keeps on track with the
	 * progress of the food item.
	 *
	 * @return
	 * 		void
	 */
	public abstract void update();
	
	/**
	 * Retrieves the statistics from the constant values class
	 *
	 * @return
	 * 		Vector<String>
	 */
	public abstract Vector<String> getStatistics();
	
	public abstract void deleteFoodItem(int orderID, int identifier);
}