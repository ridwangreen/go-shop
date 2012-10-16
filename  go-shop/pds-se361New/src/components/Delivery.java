/**
 * Delivery.java
 * 
 * Version:
 *     $Id$
 *     
 * Revision:
 *     $Log$
 */
package components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import orders.Order;
import orders.OrderManager;
import orders.States;

import foodItems.FoodItem;
import workers.Driver;

/**
 * A Component that acts as the Delivery station.  sets the FoodItem out for
 * delivery.
 *
 * @author 
 *		Shaun DeVos
 * 		Brian Baum
 * 		Rebecca Dudley
 *		Jonathan Johnson
 *		Jonathan Olin
 *
 */
public class Delivery extends Component {

	ArrayList<Order> readyOrders = new ArrayList<Order>();
	
	Driver[] drivers;
	
	/**
	 * reference to order manager
	 */
	private OrderManager oM;
	
	/**
	 * Creates a Delivery station object that manages the Delivery of the order
	 *
	 * @param class_Vars - the variable needed in this class
	 * TODO
	 */
	public Delivery(String[] class_Vars, OrderManager oM){
		this.oM = oM;
		drivers = new Driver[Integer.parseInt(class_Vars[0])];
		for (int i = 0; i < drivers.length; i++) {
			drivers[i] = new Driver();
		}
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
		// send ready orders to drivers
		for (Order o : readyOrders) {
			if (o.getCurrentState() == States.WaitDelState) {
				for (int i = 0; i < drivers.length; i++) {
					if (drivers[i].onDelivery()) {
						// skip to next driver
					} else {
						drivers[i].deliver( o );
						break;
					}
				}
			}
		}
		// update all drivers
		for (int i = 0; i < drivers.length; i++) {
			drivers[i].update();
		}
		// remove items from this array cleanly.
		Iterator<Order> iter = readyOrders.iterator();
		while (iter.hasNext()) {
			Order o = iter.next();
			if (o.getCurrentState() == States.DeliveryState) {
				iter.remove();
			}
		}
	}
	
	/**
	 * Sets The FoodItem out for delivery.  Indicates that it was successful.
	 *
	 * @param
	 * 		food - The FoodItem currently ready for delivery.
	 * @return
	 * 		void
	 */
	public void deliverFoodItem(FoodItem food){
		//System.out.println("item recieved: " + food);//TODO for Testing!
		food.setCurrentState(States.WaitDelState);
		oM.tryUpdateOrderState(food.getOrderID(),States.WaitDelState);
		// Unlike other components, delivery does not
		// need to keep track of individual foodItems
	}
	
	/**
	 * the order will call this on itself when it is in the delivery state
	 * order is send out
	 *	
	 * @param order
	 * void
	 */
	public void deliverOrder(Order order){
		//System.out.println(order + "is READY for DELIVERY!");
		this.readyOrders.add( order );
		
	}

	/**
	 * Removes foodItems from this class, but does nothing
	 * since Delivery doesn't keep foodItems.
	 */
	public void deleteFoodItem(int orderID, int identifier) {
		// does nothing in this class
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
		//System.out.println("Delivery statistics go here");
		return null;
	}
}