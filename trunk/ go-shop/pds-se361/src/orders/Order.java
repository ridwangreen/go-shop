/**
 * Order.java
 * 
 * Version:
 *     $id$
 *     
 * Revision:
 *     $log$
 */

package orders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import foodItems.FoodItem;
import persistance.ConstantValues;

/**
 * Takes all of the FoodItems, creates a List of FoodItems and begins the
 * process to create the FoodItems by starting the order.
 *
 * @author 
 *		Shaun DeVos
 * 		Brian Baum
 * 		Rebecca Dudley
 *		Jonathan Johnson
 *		Jonathan Olin
 *
 */
public class Order {
	
	/**
	 * location order is heading to
	 */
	String location;
	
	/**
	 * the name of the person who ordered it
	 */
	String nameOwner;
	
	/**
	 * The Order this FoodItem belongs to.
	 */
	int orderID;
	
	int ItemsindentifierNum;
	/**
	 * The phone number to be stored with this order.
	 */
	String phoneNumber;
	
	/**
	 * How long this item has remained in the Preparation Station.
	 */
	int prepTime;
	
	/**
	 * How long this item has remained in the Oven Station.
	 */
	int cookTime;
	
	/**
	 * how long item is ready and waiting for car
	 */
	int waitDelTime;
	
	/**
	 * how long the order has been out for delivery
	 */
	int omwTime;
	
	/**
	 * How long this order takes to deliver.
	 */
	int deliveryTime;
	
	/**
	 * the total time of the order since start
	 */
	int totalTimeSinceStart;
	
	/**
	 * the estimated time remaining
	 */
	int estimatedETA;
		
	/**
	 *  An instance of the States class that maintains where the Order is.
	 *  This is based on the state of the slowest foodItem in the order.
	 */
	States currentState;
	
	/**
	 * A container of all the FoodItems in this order
	 */
	Vector<FoodItem> foodItems;
	
	/**
	 * An instance of constant values that stores all common program variables
	 * and all of the statistics for the program.
	 */
	ConstantValues cV;
	
	/**
	 * A Constructor for an order.  Sets the Order information to respective
	 * values pertaining to this order.
	 *
	 * @param
	 * 		orderID - The integer of the order that this item belongs to. 
	 * @param
	 * 		PhoneNumber - The phone number to be stored with this order.
	 * @param
	 * 		cV - should already contain all of the common program variables.
	 */
	
	public Order(int orderID, ConstantValues cV){
		this.orderID = orderID;
		this.cV = cV;
		this.currentState = States.PrepState;
		this.foodItems = new Vector<FoodItem>();
		this.prepTime = 0;
		this.cookTime = 0;
		ItemsindentifierNum = 0;
		totalTimeSinceStart = 0;
	}
	
	public Order(int orderID,String PhoneNumber,String location,String nameOwner, ConstantValues cV){
		this(orderID,cV);
		this.phoneNumber = PhoneNumber;
		this.location = location;
		this.nameOwner = nameOwner;
		this.deliveryTime = Integer.parseInt(cV.getProperty( location ));
	}
	
	public void addInfo(String PhoneNumber,String location,String nameOwner){
		this.phoneNumber = PhoneNumber;
		this.location = location;
		this.nameOwner = nameOwner;	
		this.deliveryTime = Integer.parseInt(cV.getProperty( location ));
	}
	
	public int getNextIdentifier(){
		int newIden = ItemsindentifierNum;
		ItemsindentifierNum++;
		return newIden;
	}
	public synchronized String OrderCompression(){
		String order = "";

		//basic stats
		order = order.concat( this.orderID + "," );
		order = order.concat( this.phoneNumber + "," );
		order = order.concat( this.location + ",");
		order = order.concat( this.nameOwner + ",");
		
		//food items
		for(Iterator<FoodItem> i = foodItems.iterator(); i.hasNext(); ){
			order = order.concat( i.next().compressToString() + ",");
		}
		
		return order;
	}
	
	public synchronized Vector<FoodItem> getFoods(){
		return foodItems;
	}
	
	/**
	 * adds food items into the order, used for order decompression
	 *
	 * @param
	 * 		vector - array of foods 
	 * @return
	 * 		void
	 */
	public synchronized void addFoods(Vector<FoodItem> vector){
		for(Iterator<FoodItem> i = vector.iterator(); i.hasNext(); ){
			this.addFoodItem( i.next() );
		}
	}
	
	
	/**
	 * attempts to update the orders state if all the food items are updated.
	 *
	 * @param
	 * 		state - The state that this item is currently in.
	 * @return
	 * 		void
	 */
	public synchronized void tryUpdateOrderState(States state){
		
		if(state.index() < 3){
			for(FoodItem f: foodItems){
				if(f.getCurrentState().index() < state.index()){
					return;
				}
			}
		}
		
		this.currentState = state;
		if (this.currentState == States.WaitDelState) {
			cV.getDeliver().deliverOrder( this );
		} else if (this.currentState == States.DeliveryState) {
			cV.getOrderManager().completeOrder( this.orderID );
		}
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
	 * returns the current state of the program
	 *
	 * @return 
	 * 		States - the current State
	 */
	public States getCurrentState(){
		return this.currentState;
	}
	
	/**
	 * Adds a FoodItem to the this order.
	 *
	 * @param
	 * 		newFood - The FoodItem to be added to the order.
	 * @return
	 * 		void
	 */
	public synchronized boolean addFoodItem(FoodItem newFood){
		if(this.currentState == States.DeliveryState || currentState == States.OmwState){
			//order is too far down the line to add an item
			return false;
		}

		foodItems.add(newFood);
		this.setCurrentState( States.PrepState );
		calculateETA();
		return true;
	}
	
	/**
	 * Removes the requested FoodItem from this Order.
	 *
	 * @param
	 * 		food - The FoodItem to be removed.
	 * @return
	 * 		boolean - true if the FoodItem was successfully removed.
	 * 				- false otherwise.
	 */
	public synchronized void removeFoodItem(int identifier){
		Iterator<FoodItem> iter = foodItems.iterator();
		while (iter.hasNext()) {
			FoodItem current = iter.next();
			if (current.getIdentifier() == identifier) {
				iter.remove();
			}
		}
		States tempState = States.DeliveryState;
		for(FoodItem f: foodItems) {
			if (f.getCurrentState().compareTo(tempState) < 0) {
				tempState = f.getCurrentState();
			}
		}
		tryUpdateOrderState(tempState);
	}

	/**
	 * submits the order to the current order array and passes the FoodItems
	 * to the Preparation station.
	 *
	 * @return
	 * 		void
	 */
	public synchronized void startOrder(){
		java.util.Iterator<FoodItem> i = foodItems.iterator();

		cV.getOrderManager().addNewOrder(orderID, this);
		
		while(i.hasNext()){
			FoodItem thisItem = i.next();
			cV.getPreparation().PrepFoodItem(thisItem);
		}
	}
	
	
	/**
	 * Updates the time and keeps on track with the
	 * progress of the food item.
	 *
	 * @return
	 * 		void
	 */
	public  void update(){
		this.totalTimeSinceStart++;
		if (this.estimatedETA > 1) {
			this.estimatedETA--;
		}
		switch (currentState) {
		case PrepState:
			prepTime++;
			break;
		case CookState:
			cookTime++;
			break;
		case WaitDelState:
			waitDelTime++;
			break;
		case OmwState:
			omwTime++;
			break;
		default:
			// shouldn't get here, order is finished.
		}
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
	 * The 'getter' for the String representation of this Orders phone number.
	 * 
	 * @return
	 * 		phoneNumber - The phone number associated with this Order object.
	 */
	public String getPhoneNumber(){
		return this.phoneNumber;
	}
	
	/**
	 * The 'getter' for the name of the person who ordered this order
	 *
	 * @return
	 * 		nameOwner - The name of the person who ordered this order.
	 */
	public String getNameOwner() {
		return this.nameOwner;
	}
	
	public String getLocation(){
		return this.location;
	}
	
	public int getDeliveryTime() {
		return this.deliveryTime; 
	}
	
	public void setDeliveryTime(int time) {
		this.deliveryTime = time; 
	}
	
	
	/**
	 * The 'getter' for the ETA of the order
	 *
	 * @return
	 * 		int - The estimated number of minutes remaining.
	 */
	public int getETA(boolean recalculate) {
		if (recalculate || (totalTimeSinceStart % 10) == 0) {
			calculateETA();
		}
		return estimatedETA;
	}
	
	private void calculateETA() {
		int wait = 0;
		//TODO: Make these real variables
		final int AVERAGE_PREP_TIME = 5;
		final int AVERAGE_COOK_TIME = 8;
		for (FoodItem food : foodItems) {
			int location;
			int waitTime;
			location = cV.getPreparation().findFoodItem( food );
			if (location >= 0 && food.getCurrentState().equals( States.PrepState )) {
				// Item is waiting to be prepped
				waitTime = location * AVERAGE_PREP_TIME;
				waitTime += food.getPrepTime();
				if (food.getCookTime() > 0) {
					waitTime += (cV.getOven().itemsInQueue() * AVERAGE_COOK_TIME);
					waitTime += food.getCookTime();
				}
				wait = Math.max(wait, waitTime);
			} else if (location < 0 && food.getCurrentState().equals( States.PrepState )) {
				// Item is prepping
				waitTime = food.getPrepTime();
				if (food.getCookTime() > 0) {
					waitTime += (cV.getOven().itemsInQueue() * AVERAGE_COOK_TIME);
					waitTime += food.getCookTime();
				}
				wait = Math.max(wait, waitTime);
			} else {
				//item is not in prep
				location = cV.getOven().findFoodItem( food );
				if (location >= 0 && food.getCurrentState().equals( States.CookState )) {
					//Item is waiting to cook
					waitTime = location * AVERAGE_COOK_TIME;
					waitTime += food.getCookTime();
					wait = Math.max(wait, waitTime);
				} else if (location < 0 && food.getCurrentState().equals( States.CookState )) {
					//Item is cooking
					waitTime = food.getCookTime();
					wait = Math.max(wait, waitTime);
				}
			}
		}
		//TODO: Add method to delivery to estimate wait time.
		estimatedETA = (wait + deliveryTime);
	}
	
	
	/**
	 * The 'getter' for the total time the order has been running
	 * 
	 * @return
	 * 		totalTimeSinceStart - The total time the order has been running
	 */
	public int getTotalTimeSinceStart() {
		return this.totalTimeSinceStart;
	}
	
	/**
	 * The 'getter' for the total time the order spent in preparation
	 *
	 * @return
	 * 		prepTime - The total time spent in preparation
	 */
	public int getPrepTime() {
		return prepTime;
	}
	
	/**
	 * The 'getter' for the total time the order spent cooking
	 *
	 * @return
	 * 		cookTime - The total time spent cooking
	 */
	public int getCookTime() {
		return cookTime;
	}
	
	/**
	 * The 'getter' for the total time the order spent waiting for a car for delivery
	 *
	 * @return
	 * 		waitDelTime - The total time the order spent waiting to be delivered
	 */
	public int getWaitDelTime() {
		return waitDelTime;
	}
	
	/**
	 * The 'getter' for the total price of the order.
	 *
	 * @return
	 * 		double - The sum of the prices on each of the FoodItems in this order.
	 */
	public double getTotal() {
		double t = 0.0;
		for (FoodItem fi : foodItems) {
			t += fi.getTotal();
		}
		return t;
	}
	
	/**
	 * Creates a string consisting of the order id and the current state.
	 * 
	 * @return	
	 * 		orderString - The order id and current state as a string.
	 * @Override
	 * 		toString - the Object class's toString method.
	 */
	public String toString() {
		String orderString = "     Order #" + this.orderID;
		return orderString;
	}
	
	/**
	 * Returns information about this order in a string neatly formatted for
	 * displaying in the GUI
	 *
	 * @return
	 * 		String - A string representation of this order.
	 */
	public String mainGUIToString() {
		String s = "#" + this.orderID + "   " + this.nameOwner + " " + this.phoneNumber
			+ " " + this.totalTimeSinceStart + " " + this.getETA(false) + " "
			+ this.currentState;
		return s;
	}
	
	/**
	 * Returns information about this order in a string neatly formatted for
	 * displaying in the GUI
	 *
	 * @return
	 * 		String - A string representation of this order.
	 */
	public Vector<String> editorGUIToString() {
		Vector<String> foodsStringVector = new Vector<String>();
		for (FoodItem currentfood : foodItems) {
			for(String lines : currentfood.toStringOrderEditor()){
				foodsStringVector.add( lines );
			}
		}
		return foodsStringVector;
	}
	
	public void FixOrder(Order clonedOrder) {
		Vector<Integer> toBeDeleted = new Vector<Integer>();
		
		for(FoodItem clonedFood: clonedOrder.foodItems){
			boolean found = false;
			for(FoodItem food: this.foodItems) {
				if (food.getIdentifier() == clonedFood.getIdentifier()) {
					found = true;
				}
			}
			if (!found) {
				this.addFoodItem( clonedFood );
				cV.getPreparation().PrepFoodItem( clonedFood );
				currentState = States.PrepState;
			}
		}
		
		for(FoodItem food: this.foodItems) {
			boolean found = false;
			for(FoodItem clonedFood: clonedOrder.foodItems) {
				if (food.getIdentifier() == clonedFood.getIdentifier()) {
					found = true;
				}
			}
			if (!found) {
				toBeDeleted.add( food.getIdentifier() );
			}
		}
		
		for (int i: toBeDeleted) {
			cV.getOrderManager().deleteItemReferences( orderID, i );
			removeFoodItem(i);
		}
	}
	
	public Order clone() {
		Order newOrder = new Order(this.orderID, cV);
		newOrder.location = this.location;
		newOrder.nameOwner = this.nameOwner;
		newOrder.ItemsindentifierNum = this.ItemsindentifierNum;
		newOrder.phoneNumber = this.phoneNumber;
		newOrder.prepTime = this.prepTime;
		newOrder.cookTime = this.cookTime;
		newOrder.waitDelTime = this.waitDelTime;
		newOrder.omwTime = this.omwTime;
		newOrder.deliveryTime = this.deliveryTime;
		newOrder.totalTimeSinceStart = this.totalTimeSinceStart;
		newOrder.estimatedETA = this.estimatedETA;
		newOrder.currentState = this.currentState;
		newOrder.foodItems = new Vector<FoodItem>();
		for (FoodItem f : this.foodItems) {
			newOrder.foodItems.add(cV.getDatabase().makefoodItem(f.compressToString()));
		}
		return newOrder;
	}
}