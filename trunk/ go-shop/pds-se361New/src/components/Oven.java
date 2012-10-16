/**
 * Oven.java
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
 * A Component that acts as the Oven station.  Puts the FoodItem into
 * a queue if there is oven space available.  After The Oven station the FoodItem
 * gets passed to the Delivery class.
 *
 * @author 
 *		Shaun DeVos
 * 		Brian Baum
 * 		Rebecca Dudley
 *		Jonathan Johnson
 *		Jonathan Olin
 *
 */
public class Oven extends Component {
	
	/**
	 * The array of FoodItems currently being cooked on in the Oven.
	 */
	private FoodItem[] ovenArray;
	
	/**
	 * A running Tally of how much Oven space is available(empty slots).
	 */
	private int totalCookingSpaceLeft;	

	/**
	 * refence to order manager
	 */
	private OrderManager om;
	
	/**
	 * reference to oven
	 */
	private Delivery del;
	
	/**
	 * Passes a ConstantValues object to the Component class, initializes the
	 * Oven array length and the number of free slots in the oven to the number
	 * of oven spaces from the constant values statistic.
	 *
	 * @param
	 * 		TODO!
	 */
	public Oven(String[] class_Vars, OrderManager om, Delivery del){
		super();
		this.om = om;
		this.del = del;
		totalCookingSpaceLeft = Integer.parseInt( class_Vars[ 0 ] );
		ovenArray = new FoodItem[totalCookingSpaceLeft];
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
		//System.out.print("oven /");
		
		for(int i = 0; i < ovenArray.length; i++){

			FoodItem currFood = ovenArray[i];
			if(currFood != null){
				if(currFood.getCookTime()>0){
					currFood.setCookTime(currFood.getCookTime()-1);
					if(currFood.getCookTime()==0){
						itemCooked(i);
					}
				}else{
					itemCooked(i);
				}
			}
		}
		
		if(this.totalCookingSpaceLeft > 0){
			addItemToCooking();
		}
		
	}

	/**
	 * Begins the cooking of the FoodItem.  Passes the FoodItem to the Delivery
	 * when finished.
	 *
	 * @param
	 * 		food - the FoodItem to be added from the Preparation to the Oven
	 * @return
	 * 		void
	 */
	public void cookFoodItem(FoodItem food) {
		//SET TO COOKING FOr FOOD AND FOR ORDER OF THE FOOD!!!!!
		if (food.getCookTime() > 0) {
			food.setCurrentState(States.CookState);
			om.tryUpdateOrderState(food.getOrderID(),States.CookState);
			queue.add(food);
		} else {
			del.deliverFoodItem(food);
		}
	}

	/* (non-Javadoc)
	 * @see components.Component#deleteFoodItem(int, int)
	 */
	@Override
	public void deleteFoodItem( int orderID, int identifier ) {
		Iterator<FoodItem> queueIter = queue.iterator();
		while(queueIter.hasNext()){
			FoodItem f = queueIter.next();
			if(f.getOrderID() == orderID && f.getIdentifier() == identifier){
				queueIter.remove();
			}
		}
		for(int i = 0; i < this.ovenArray.length; i++){
			if(ovenArray[i] != null && ovenArray[i].getOrderID() == orderID &&  ovenArray[i].getIdentifier() == identifier){
				ovenArray[i] = null;
			}
		}
	}
	
	public int findFoodItem(FoodItem food) {
		return queue.indexOf( food );
	}
	

	public int itemsInQueue() {
		return queue.size();
	}
	/**
	 * The FoodItem is now cooked, the FoodItem is passed to the Delivery class.
	 * 
	 * @param
	 * 		foodCookingLocation - An integer interpreted by the Stations enum
	 * 			that tells which worker is working on the FoodItem 
	 * @return
	 * 		void
	 */
	private void itemCooked(int foodCookingLocation){
		//System.out.print("item Cooked: " + ovenArray[foodCookingLocation].toString());//TODO for Testing!
		// swaps finished item out of array and sends to cooking also puts new
		
		del.deliverFoodItem(ovenArray[foodCookingLocation]);
		this.totalCookingSpaceLeft+=
			ovenArray[foodCookingLocation].getOvenSpace();
		ovenArray[foodCookingLocation] = null;		
	}

	/**
	 * Adds the FoodItem to the ovenArray to be cooked.
	 *
	 * @return
	 * 		void
	 */
	private void addItemToCooking(){
		while (queue.size() > 0 && totalCookingSpaceLeft > 0) {
			for (int i = 0; i < ovenArray.length; i++) {
				if (ovenArray[i] == null) {
					ovenArray[i] = queue.remove(0);
					this.totalCookingSpaceLeft-= ovenArray[i].getOvenSpace();
					break;		//breaks to while loop
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
		//System.out.println("oven statistics go here");
		return null;
	}


}
