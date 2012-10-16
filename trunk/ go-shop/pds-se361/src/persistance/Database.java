/*
 * Filename:
 *		Database.java
 *
 * Version:
 *	   $id$
 *
 * Revision:
 *	   $log$
 */
package persistance;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;

import foodItems.FoodItem;
import foodItems.Pizza;
import foodItems.PizzaLog;
import foodItems.TossedSalad;

import orders.Order;

/**
 *
 * @author 
 *		Shaun DeVos
 * 		Brian Baum
 * 		Rebecca Dudley
 *		Jonathan Johnson
 *		Jonathan Olin
 *
 */
public class Database {
	ConstantValues cV;
	Properties cusInfo, orderIDs, orders, systemFiles;
	int currentOrderID;
	
	public Database(String cusInfoString, String orderIDsString, String ordersString, ConstantValues cV) throws FileNotFoundException, IOException{

		this.cV = cV;
		
		this.cusInfo = new Properties();
		this.orderIDs = new Properties();
		this.orders = new Properties();
		this.systemFiles = new Properties();
		
		
		this.cusInfo.load(new FileInputStream(cusInfoString));
		this.orderIDs.load(new FileInputStream(orderIDsString));
		this.orders.load(new FileInputStream(ordersString));
		this.systemFiles.load( new FileInputStream("systemFiles") );
		
		this.currentOrderID = Integer.parseInt(systemFiles.getProperty( "currentOrderID" ));

	}
	
	public String getCustomerInfo(String phoneNum){
		return cusInfo.getProperty( phoneNum );
		//might want to return a customer object mebe
	}
	
	public String[] getOrderIDs(String phoneNum){
		String ordersCompressed = orderIDs.getProperty( phoneNum );
		
		return ordersCompressed.split( "," );
	}
	
	public Vector<Order> getOrders(String[] orderIDs){
		String[] ordersString = new String[orderIDs.length];
		for(int i = 0; i < orderIDs.length;i++){
			ordersString[i] = orders.getProperty( orderIDs[i] );
		}
		
		Vector<Order> orderObj = new Vector<Order>();
		for(int i = 0; i < orderIDs.length;i++){
			//System.out.println(ordersString[i] + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			orderObj.add(makeOrder(ordersString[i]));
		}
		
		return orderObj;
	}
	
	public Vector<Order> getCustomerOrders(String phoneNum){
		String[] orderIDs = getOrderIDs(phoneNum);
		
		return getOrders(orderIDs);
	}
	
	/**
	 *
	 * @param string
	 * @return
	 * Order
	 */
	public Order makeOrderID( String orderID ) {
		return makeOrder(this.orders.getProperty( orderID ));
	}
	
	public FoodItem makefoodItem(String food){
		String[] currentItem;
			//the 4 is after basic info
			currentItem = food.split( ":" );
			FoodItem newFood = null;
			
			//System.out.println(currentItem[2] + "current items1!!!$FDJFSD:LKJGSDKLFJGSKLDFGLJDFJK");
			String identifier = currentItem[2];
			int id = Integer.parseInt(identifier);
			
			String orderID = currentItem[3];
			int orderid = Integer.parseInt(orderID);
			
			char itemType = currentItem[0].charAt( 0 );
			
			switch(itemType){
			case 'S'://small pizza
			case 'M'://med pizza
			case 'L'://large pizza
				//0 is the type of item
				//1 is the toppings
				Vector<String> toppings = extractToppings(currentItem[1]);

				newFood = new Pizza(cV, toppings, id, orderid, itemType);
				break;
			case 'T':// tossed salad
				newFood = new TossedSalad(cV, orderid, id);
				break;
			case 'P'://Pizza log
				newFood = new PizzaLog(cV, orderid, id);
				break;
			default:	
				System.out.println("ERROR NO FOOD FOUND FOR ID!");
			}
			return newFood;
	}
	
	
	//converts an order string into an order object
	public Order makeOrder(String order){
		if( order == null || order.equals( "" )){
			System.err.println("ERROR MISSING IN MAKEORDER IN DATABSE?!?!?!");
			return null;
			
		}
		Vector<FoodItem> foodItems = new Vector<FoodItem>();
	
		//for an order that was stored
		String[] fullOrder = order.split( "," );
		//fullOrder[0-4] = basic order stats

		
		//FOR EACH FOOD ITEM
		String[] currentItem;
		for(int i = 4; i < fullOrder.length; i++){
			//the 4 is after basic info
			currentItem = fullOrder[i].split( ":" );
			FoodItem newFood = null;
			
			//System.out.println(currentItem[2] + "current items1!!!$FDJFSD:LKJGSDKLFJGSKLDFGLJDFJK");
			String identifier = currentItem[2];
			int id = Integer.parseInt(identifier);
			
			String orderID = currentItem[3];
			int orderid = Integer.parseInt(orderID);
			
			char itemType = currentItem[0].charAt( 0 );
			
			switch(itemType){
			case 'S'://small pizza
			case 'M'://med pizza
			case 'L'://large pizza
				//0 is the type of item
				//1 is the toppings
				Vector<String> toppings = extractToppings(currentItem[1]);

				newFood = new Pizza(cV, toppings, id, orderid, itemType);
				break;
			case 'T':// tossed salad
				newFood = new TossedSalad(cV, orderid, id);
				break;
			case 'P'://Pizza log
				newFood = new PizzaLog(cV, orderid, id);
				break;
			default:	
				System.out.println("ERROR NO FOOD FOUND FOR ID!");
			}
			foodItems.add( newFood );
		}
		
		
		//creating order now that food items are made
		int OorderID = Integer.parseInt(fullOrder[0]);
		
		
		String OPhoneNumber = fullOrder[1];
		String Olocation = fullOrder[2];
		String OnameOwner = fullOrder[3];
		
		Order newOrder = new Order(OorderID, OPhoneNumber, Olocation, OnameOwner, cV);
		newOrder.addFoods( foodItems );
		
		return newOrder;
	}
	
	private Vector<String> extractToppings(String order){
		Vector<String> toppings = new Vector<String>();
		
		String[] toppingsString = order.split( "!" );
		
		
		for(int i = 1; i < toppingsString.length;i++){
			
			//this will cut all before the toppings and after them
			//ex: P:*t1*t2*t3*:ID
			//P:  (t1 t2 t3)  :ID   cut off front and back
			toppings.add( toppingsString[i] );
		}
		
		return toppings;
	}
	
	public void addCustomerInfo(String phoneNum,String name,String location){
		//adds customer
		String info = name + "," + location + "," ;
		
		this.cusInfo.setProperty( phoneNum, info );
	}
	
	/*
	 * checks if the order is in the list if so ignore
	 * since reference exists
	 * elese add it
	 */
	public void addOrderNumberToOrderIDs(String orderID, String phoneNum){
		String orderIDArray = this.orderIDs.getProperty( phoneNum );
		
		String[] orderids = orderIDArray.split( "," );
		
		for(int i = 0; i < orderids.length;i++){
			if(orderids[i].equals(orderID)){
				return;
				//already exsists
			}
		}
		
		orderIDArray = orderIDArray.concat( orderID + ",");
		this.orderIDs.setProperty( phoneNum , orderIDArray );
	}
	
	public void addOrder(String orderID, String orderToString){
		//concats order ID onto the customer
		//adds to the orders package
		
		
		String phoneNum = this.getOrdersPhoneNum( orderToString );
		
		//gets orderarray and adds to end of it
		if(orderIDs.getProperty( phoneNum )== null){
			orderIDs.setProperty( phoneNum, "" );
		}
		
//		String orderIDArray = this.orderIDs.getProperty( phoneNum );
//		
//		orderIDArray = orderIDArray.concat( orderID + ",");
		
		addOrderNumberToOrderIDs(orderID,phoneNum);
	
		
		//adds to order
		this.orders.setProperty( orderID, orderToString );
	}
	
	public boolean removeOrder(String orderID){
		if(this.orders.containsKey( orderID )){
			String order = this.orders.getProperty( orderID );
			String phoneNum = this.getOrdersPhoneNum( order );
			
			String ordersIDArray = this.orderIDs.getProperty( phoneNum );
			ordersIDArray = ordersIDArray.replace( orderID+",", "" );
			//updateds orderIDs w/o that id
			this.orderIDs.setProperty( phoneNum, ordersIDArray );
			
			this.orders.remove( orderID );
			return true;
		}

		return false;
	}
	
	
	private String getOrdersPhoneNum(String orderb){
		//gets phone num
		int startOfPhoneNum = orderb.indexOf( ',' );
		int endofPhoneNum = orderb.indexOf( ',', startOfPhoneNum+1 );
		String phoneNum = orderb.substring( startOfPhoneNum+1, endofPhoneNum );
		
		if(phoneNum == null){
			phoneNum = "";
		}
		return phoneNum;
	}
	
	public void shutDown() throws FileNotFoundException, IOException{
			this.cusInfo.store( new FileOutputStream("cusInfoString"), "");
			this.orderIDs.store( new FileOutputStream("orderIDsString"), "");
			this.orders.store( new FileOutputStream("ordersString"), "");
			this.systemFiles.store( new FileOutputStream("systemFiles"), "" );

	}
	
	/**
	 * Returns the next incremented orderID to ensure each id is unique the id
	 * is maintained in the database
	 *
	 * @return
	 * 		int - the new orderID
	 */
	public int getNewOrderID(){
		this.currentOrderID++;
		systemFiles.setProperty( "currentOrderID", currentOrderID + "" );
		return this.currentOrderID;
	}
	
	public boolean checkPassword(String userName, char[] password){
		boolean works = false;
		if(systemFiles.containsKey( userName )){
			String pass = "";
			for(char c: password){
				pass += c;
			}
			if(systemFiles.get( userName ).equals(pass)){
				works = true;
			}
		}
		return works;
	}
}
