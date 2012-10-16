/**
 * Preparation.java
 * 
 * Version:
 *     $Id$
 *     
 * Revision:
 *     $Log$
 */

package components;

import java.util.Iterator;
import java.util.Vector;

import orders.OrderManager;
import orders.States;
import foodItems.FoodItem;

/**
 * A Component that acts as the Preparation station.  Puts the FoodItem into
 * a queue if there are workers available.  After The Prep station the FoodItem
 * gets passed to the Oven class.
 *
 * @author 
 *		Shaun DeVos
 * 		Brian Baum
 * 		Rebecca Dudley
 *		Jonathan Johnson
 *		Jonathan Olin
 *
 */
public class Preparation extends Component {
	
	/**
	 * The array of FoodItems currently being worked on in the Prep station.
	 */
	private FoodItem[] prepping;
	
	/**
	 * A running tally of currently available workers in the Prep station.
	 */
	private int freePreppers;
	
	/**
	 * refence to order manager
	 */
	private OrderManager om;
	
	/**
	 * reference to oven
	 */
	private Oven oven;

	/**
	 * Passes a ConstantValues object to the Component class, initializes the
	 * preparing array length and the number of free preparers to the number of
	 * workers for the day from the constant values statistic.
	 *
	 * @param
	 * 		//TODO!
	 */	
	public Preparation(String[] class_Vars, OrderManager om, Oven oven) {		
		super();
		this.om = om;
		this.oven = oven;
		freePreppers = Integer.parseInt( class_Vars[ 0 ] );
		prepping = new FoodItem[ freePreppers ];
	}

	/**
	 * Begins the Preparation of the FoodItem.  Passes the FoodItem to the Oven
	 * when finished.
	 *
	 * @param
	 * 		food - the FoodItem to be added from the order to the Preparation
	 * 		station.
	 * @return
	 * 		void
	 */
	public void PrepFoodItem(FoodItem food) {

		if (food.getPrepTime() > 0) {
			food.setCurrentState(States.PrepState);
			om.tryUpdateOrderState(food.getOrderID(),States.PrepState);
			queue.add(food);
		} else {
			oven.cookFoodItem(food);
		}
	}

	public void deleteFoodItem(int orderID, int identifier) {
		Iterator<FoodItem> queueIter = queue.iterator();
		while(queueIter.hasNext()){
			FoodItem f = queueIter.next();
			if(f.getOrderID() == orderID && f.getIdentifier() == identifier){
				queueIter.remove();
			}
		}
		for(int i = 0; i < this.prepping.length; i++){
			if(prepping[i] != null && prepping[i].getOrderID() == orderID &&  prepping[i].getIdentifier() == identifier){
				prepping[i] = null;
			}
		}
	}

	public int findFoodItem(FoodItem food) {
		return queue.indexOf( food );
	}
	
	/**
	 * See overridden method description.
	 * 
	 * @override
	 * 		Component - super class
	 * @return
	 * 		void
	 */
	public synchronized void update() {
		//System.out.println("prep updated");
		// increments by 1 preppingStation by 1 sec
		for (int i = 0; i < prepping.length; i++) {
			FoodItem curr = prepping[i];
			if (curr != null) {
				if (curr.getPrepTime() > 0) {
					// item not prepped
					curr.setPrepTime(curr.getPrepTime() - 1);
					if (curr.getPrepTime() == 0) {
						// item prepped
						itemPrepped(i);
					}
				} else {
					// item prepped, double check
					itemPrepped(i);
				}
			}
		}

		// attempts to add to new food to prepping
		if (this.freePreppers > 0) {
			addItemToPrepping();
		}
	}

	/**
	 * The FoodItem is now prepared, the FoodItem is passed to the Oven class.
	 * 
	 * @param
	 * 		foodPreppingLocation - An integer interpreted by the Stations enum
	 * 			that tells which worker is working on the FoodItem 
	 * @return
	 * 		void
	 */
	private void itemPrepped(int foodPreppingLocation) {
		//System.out.println("item Preped: " + prepping[foodPreppingLocation].toString());//TODO for Testing!
		// swaps finished item out of array and sends to cooking also puts new
		oven.cookFoodItem(prepping[foodPreppingLocation]);
		prepping[foodPreppingLocation] = null;
		this.freePreppers++;
		
	}

	/**
	 * Adds the FoodItem to the preparation queue to be prepared.
	 *
	 * @return
	 * 		void
	 */
	private void addItemToPrepping() {

		// tries to moves food item from queue to the prepping array
		// this will only succeed if there is a worker available in preparation
		
		while (queue.size() > 0 && freePreppers > 0) {
			outter_loop:
			for (int i = 0; i < prepping.length; i++) {
				if (prepping[i] == null) {
					prepping[i] = queue.remove(0);
					freePreppers--;
					break outter_loop;		//breaks to while loop
				}
			}
		}
	}

	/**
	 * See overridden method description.
	 * 
	 * @return
	 * 		- container of the statistics pertaining to this class.
	 * @Override
	 * 		Component - super class.
	 */
	public Vector<String> getStatistics() {
		//System.out.println("todo stats for prep");
		return null;
	}
}