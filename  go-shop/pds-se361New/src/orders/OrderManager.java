/**
 * OrderManager.java
 * 
 * Version:
 *     $id$
 *     
 * Revision:
 *     $log$
 */

package orders;

import java.util.TreeMap;
import java.util.Collections;
import java.util.Vector;

import foodItems.FoodItem;

import persistance.ConstantValues;

/**
 * This class Contains all of the orders currently in progress of being made.
 * Orders may be added and removed, or returned and checked on from this class.
 *
 * @author 
 *		Shaun DeVos
 * 		Brian Baum
 * 		Rebecca Dudley
 *		Jonathan Johnson
 *		Jonathan Olin
 *
 */
public class OrderManager {
	
	/**
	 * A table that contains all the orders at specific locations.
	 */
	protected TreeMap<Integer,Order> ordersMap;
	
	/**
	 * An instance of constant values that stores all common program variables
	 * and all of the statistics for the program.
	 */
	private ConstantValues cV;
	
	private int totalOrders;
	
	private double totalCost;
	
	private int totalPrepTime;
	
	private int maxPrepTime;
	
	private int maxPrepOrder;
	
	private int totalCookTime;
	
	private int maxCookTime;
	
	private int maxCookOrder;
	
	private int totalWaitDelTime;
	
	private int maxWaitDelTime;
	
	private int maxWaitDelOrder;
	
	private int totalOrderLifeTime;
	
	private int maxOrderLifeTime;
	
	private int longestLivedOrder;
	
	/**
	 * A Constructor for the OrderManager to control the given order.
	 * Adds the first order to the HashMap containing all the orders.
	 * 
	 * @param
	 * 		cV - should already contain all of the common program variables.
	 */
	public OrderManager(ConstantValues cV){
		this.ordersMap = new TreeMap<Integer, Order>();
		this.cV = cV;
	}
	
	/**
	 * Returns the amount of order objects in the OrderManager
	 *
	 * @return
	 * 		size - the number of current orders
	 */
	public int getOrdersInOMATM(){
		return this.ordersMap.size();
	}
	
	/**
	 * Adds a new order to the ordering process by adding it to the order
	 * table.
	 *
	 * @param
	 * 		orderID - The integer of the order that is to be added.
	 * @param
	 * 		newOrder - The order to be added to the order table.
	 * @return
	 * 		void
	 */
	public synchronized void addNewOrder(int orderID, Order newOrder){
		ordersMap.put(orderID,newOrder);
	}
	
	/**
	 * Stores completed order and removes the order from hashmap, and stores in
	 * the global statistics.
	 *
	 * @param
	 * 		orderID - The integer of the order that is to be removed.
	 * @return
	 * 		void
	 */
	public synchronized void completeOrder(int orderID){
		//puts order info into global stats
		//TODO
		Order o = ordersMap.get( orderID );
		totalOrders++;
		for (FoodItem fi : o.getFoods()) {
			totalCost += fi.getTotal();
		}
		totalPrepTime += o.getPrepTime();
		if ( o.getPrepTime() > maxPrepTime ) {
			maxPrepTime = o.getPrepTime();
			maxPrepOrder = orderID;
		}
		totalCookTime += o.getCookTime();
		if ( o.getCookTime() > maxCookTime ) {
			maxCookTime = o.getCookTime();
			maxCookOrder = orderID;
		}
		totalWaitDelTime += o.getWaitDelTime();
		if ( o.getWaitDelTime() > maxWaitDelTime ) {
			maxWaitDelTime = o.getWaitDelTime();
			maxWaitDelOrder = orderID;
		}
		totalOrderLifeTime += o.getTotalTimeSinceStart();
		if ( o.getTotalTimeSinceStart() > maxOrderLifeTime ) {
			maxOrderLifeTime = o.getTotalTimeSinceStart();
			longestLivedOrder = orderID;
		}
		this.removeOrder( orderID );
		//deletes order
		
	}
	
	/**
	 * goes though all components checking for item in array or queue form prep
	 * to oven then calls deleteItem on the order that holds the item
	 * 
	 * @param
	 * 		orderID
	 * @param
	 * 		identifier
	 * @return
	 * 		void
	 */
	public void deleteItemReferences(int orderID, int identifier){
		this.cV.getPreparation().deleteFoodItem( orderID, identifier );
		this.cV.getOven().deleteFoodItem( orderID, identifier );
		//go though all compents
	}
	
	/**
	 * deletes all the fooditems and then deletes the order from the tree
	 *
	 * @param
	 * 		orderID
	 * @return
	 * 		void
	 */
	private synchronized void deleteOrdersFoodItems(int orderID){
		for(FoodItem p :ordersMap.get(orderID).getFoods()){
			this.deleteItemReferences( orderID, p.getIdentifier() );
		}
	}
	
	/**
	 * Removes the order completely as well as all of the FoodItems. Is not
	 * recorded in total statistics.
	 *
	 * @param
	 * 		orderID - The integer of the order that is to be removed.
	 * @return
	 * 		void
	 */
	public synchronized void removeOrder(int orderID){
		
		//TODO removes the order completly as well as all of the food items,
		//also ignored in total stats
		
		this.deleteOrdersFoodItems( orderID);
		ordersMap.remove( orderID );
	}
	
	/**
	 * The 'getter' for all of the orders currently being worked on.
	 *
	 * @return
	 * 		order - the HashMap containing all of the orders
	 */
	public TreeMap<Integer,Order> getOrders(){
		return ordersMap;
	}
	
	/**
	 * Creates and sorts an ArrayList of the current orders according to the
	 * provided OrderComparator.
	 * 
	 * Telephone num, orderID, name, timeelapsed, current State, eta, and 
	 * food items tostring here
	 *
	 * @param
	 * 		c - The OrderComparator to use to sort the ArrayList
	 * @return
	 * 		ArrayList<Order> sortedOrders - an ArrayList of the current orders, 
	 * sorted according to the given OrderComparator
	 */
	public synchronized Vector<Vector<String>> getSortedOrderList(OrderComparator c) {
		
		Vector<Order> sortedOrders = new Vector<Order>(ordersMap.values());
		Collections.sort( sortedOrders, c );
		
		Vector<Vector<String>> ordersInGrid = new Vector<Vector<String>>();
		
		for(Order current : sortedOrders){
			Vector<String>currentOrderStats = new Vector<String>();
			
			currentOrderStats.add( current.getOrderID() +"" );
			currentOrderStats.add( current.getNameOwner() );
			currentOrderStats.add( current.getPhoneNumber() );
			currentOrderStats.add( current.getTotalTimeSinceStart() + "" );
			currentOrderStats.add( current.getCurrentState() +"" );
			currentOrderStats.add( current.getETA(false) + "" );
				
			ordersInGrid.add( currentOrderStats );
			//ordersInGrid.add( current.editorGUIToString() );
			
		}

		return ordersInGrid;
	}
	
	/**
	 * Updates the state of all the items in the order
	 *
	 * @param
	 * 		orderID - the order to be updated
	 * @param
	 * 		state - the new state of the items
	 * @return
	 * 		void
	 */
	public synchronized void tryUpdateOrderState(int orderID, States state){
		
		if(ordersMap.get( orderID ) != null){
			ordersMap.get(orderID).tryUpdateOrderState(state);
		}else{

			System.out.println(orderID + " current order that it crashed on fish");
			
		}
	}
	
	/**
	 * The 'getter' for an order being worked on.  The order is retrieved based
	 * on the orderID provided.
	 *
	 * @param
	 * 		orderID - The phone number of the order being requested.
	 * @return
	 * 		order - The order requested containing the specified phone number.
	 * 		null - If the order is not found.
	 */
	public Order getOrder(int orderID){

		for (Order o : ordersMap.values()) {
			if (o.getOrderID() == orderID) {
				return o;
			}
		}
		
		return null;
	}

	/**
	 * Generates a manager report based on all orders that have been completed since opening the program
	 *
	 * @return
	 * 		mr - The manager report as a string, neatly formatted for display in a GUI or CLI
	 */
	public Vector<String> managerReport() {
		
		int totalOrders = this.totalOrders;
		double totalCost = this.totalCost;
		int totalPrepTime = this.totalPrepTime;
		int maxPrepTime = this.maxPrepTime;
		int maxPrepOrder = this.maxPrepOrder;
		int totalCookTime = this.totalCookTime;
		int maxCookTime = this.maxCookTime;
		int maxCookOrder = this.maxCookOrder;
		int totalWaitDelTime = this.totalWaitDelTime;
		int maxWaitDelTime = this.maxWaitDelTime;
		int maxWaitDelOrder = this.maxWaitDelOrder;
		int totalOrderLifeTime = this.totalOrderLifeTime;
		int maxOrderLifeTime = this.maxOrderLifeTime;
		int longestLivedOrder = this.longestLivedOrder;
		
		for(Integer orderID: ordersMap.keySet()){
			Order o = ordersMap.get( orderID );
			
			totalOrders++;
			for (FoodItem fi : o.getFoods()) {
				totalCost += fi.getTotal();
			}
			totalPrepTime += o.getPrepTime();
			if ( o.getPrepTime() > maxPrepTime ) {
				maxPrepTime = o.getPrepTime();
				maxPrepOrder = orderID;
			}
			totalCookTime += o.getCookTime();
			if ( o.getCookTime() > maxCookTime ) {
				maxCookTime = o.getCookTime();
				maxCookOrder = orderID;
			}
			totalWaitDelTime += o.getWaitDelTime();
			if ( o.getWaitDelTime() > maxWaitDelTime ) {
				maxWaitDelTime = o.getWaitDelTime();
				maxWaitDelOrder = orderID;
			}
			totalOrderLifeTime += o.getTotalTimeSinceStart();
			if ( o.getTotalTimeSinceStart() > maxOrderLifeTime ) {
				maxOrderLifeTime = o.getTotalTimeSinceStart();
				longestLivedOrder = orderID;
			}
		}
		
		
		Vector<String> mr = new Vector<String>();
		if(totalOrders == 0){
			mr.add( "Manager Report" );
			mr.add("");
			mr.add( "Total Orders Today: " + totalOrders );
			mr.add( "Average Cost per Order: N/A" );
			mr.add("");
			mr.add( "" );
			mr.add( "Average Preparation Time: N/A") ;
			mr.add( "Maximum Preparation Time: " + maxPrepTime );
			mr.add( "Maximum Preparation OrderID: " + maxPrepOrder );
			mr.add( "" );
			mr.add( "Average Cooking Time: N/A" );
			mr.add( "Maximum Cooking Time: " + maxCookTime );
			mr.add( "Maximum Cooking OrderID: " + maxCookOrder );
			mr.add( "" );
			mr.add( "Average Time Waiting for Delivery: N/A" );				
			mr.add( "Maximum Time Waiting for Delivery: " + maxWaitDelTime );
			mr.add( "Maximum Time Waiting for Delivery OrderID: " + maxWaitDelOrder );
			mr.add( "" );
			mr.add( "Average Order Lifetime: N/A" );
		    mr.add( "Maximum Order Lifetime: " + maxOrderLifeTime );
			mr.add( "Longest Lived OrderID: " + longestLivedOrder );
			
		}else{
			mr.add( "Manager Report" );
			mr.add("");
			mr.add( "	Total Orders Today: " + totalOrders );
			mr.add( "	Average Cost per Order: " + (totalCost/totalOrders) );
			mr.add("");
			mr.add( "" );
			mr.add( "Average Preparation Time: " + (totalPrepTime/totalOrders)) ;
			mr.add( "Maximum Preparation Time: " + maxPrepTime );
			mr.add( "Maximum Preparation OrderID: " + maxPrepOrder );
			mr.add( "" );
			mr.add( "Average Cooking Time: " + (totalCookTime/totalOrders) );
			mr.add( "Maximum Cooking Time: " + maxCookTime );
			mr.add( "Maximum Cooking OrderID: " + maxCookOrder );
			mr.add( "" );
			mr.add( "Average Time Waiting for Delivery: " + (totalWaitDelTime/totalOrders) );				
			mr.add( "Maximum Time Waiting for Delivery: " + maxWaitDelTime );
			mr.add( "Maximum Time Waiting for Delivery OrderID: " + maxWaitDelOrder );
			mr.add( "" );
			mr.add( "Average Order Lifetime: " + (totalOrderLifeTime/totalOrders) );
		    mr.add( "Maximum Order Lifetime: " + maxOrderLifeTime );
			mr.add( "Longest Lived OrderID: " + longestLivedOrder );
		}
			
			
		return mr;
	}

}