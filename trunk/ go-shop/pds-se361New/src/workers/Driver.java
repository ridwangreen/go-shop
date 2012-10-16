package workers;

import orders.Order;
import orders.States;

public class Driver {
	/**
	 * Whether or not a driver is currently delivering an order
	 */
	private boolean onDelivery;
	
	/**
	 * The amount of time that this driver has been on the current delivery
	 */
	private int deliveryTime;
	
	/**
	 * The amount of time that the given order takes to deliver.
	 */
	private int orderTime;
	
	/**
	 * The order being delivered.
	 */
	private Order myOrder;
	
	/**
	 * The amount of time that the driver spends completing a transaction.
	 */
	private final int TRANSACTION_TIME = 2;
	
	public Driver() {
		this.onDelivery = false;
	}

	public boolean onDelivery() {
		return onDelivery;
	}
	
	public int getDeliveryTime() {
		return deliveryTime;
	}
	public void deliver(Order order) {
		onDelivery = true;
		myOrder = order;
		deliveryTime = 0;
		orderTime = myOrder.getDeliveryTime();
		order.setCurrentState( States.OmwState );
		//System.out.println("Leaving with " + order);
	}
	
	public void update() {
		if (onDelivery) {
			deliveryTime++;
			if (myOrder != null && myOrder.getDeliveryTime() > 0) {
				myOrder.setDeliveryTime( myOrder.getDeliveryTime() - 1 );
			}
			if (deliveryTime == orderTime + TRANSACTION_TIME) {
				myOrder.tryUpdateOrderState( States.DeliveryState );
				//System.out.println(myOrder + " delivered!");
				myOrder = null;
			} else if (deliveryTime == 2 * orderTime + TRANSACTION_TIME) {
				onDelivery = false;
				//System.out.println(this + " returned");
			}
		} else {
			// Do nothing
		}
		
	}
}