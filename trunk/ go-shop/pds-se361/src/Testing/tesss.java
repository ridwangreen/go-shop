/*
 * Filename:
 *		tesss.java
 *
 * Version:
 *	   $Id$
 *
 * Revision:
 *	   $Log$
 */
package Testing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import orders.Order;
import orders.OrderManager;
import persistance.ConstantValues;
import persistance.Database;
import updater.Timer;
import GUI.LoginScreen;
import GUI.MainGUIPage;
import GUI.OrderEditor;
import GUI.PastOrderView;

import components.Delivery;
import components.Oven;
import components.Preparation;

import control.SixAxisController;
import foodItems.FoodItem;
import foodItems.Pizza;
import foodItems.PizzaLog;
import foodItems.TossedSalad;


/**
 *
 * @author 
 *		Shaun DeVos
 * 		Brian Baum
 * 		Rebecca Dudley
 *		Jonathan Johnson
 *		Jonathan Olin
 */
public class tesss {
	/**
	 * The Driver class of the Pineapple program.  This class creates the values provided
	 * in the filename given by the user when the program is first executed.  The
	 * GUI is initialized and run, the OrderManager is initialized and started. 
	 *
	 * @author 
	 *		Shaun DeVos
	 * 		Brian Baum
	 * 		Rebecca Dudley
	 *		Jonathan Johnson
	 *		Jonathan Olin
	 *
	 */

		private static ConstantValues cV;
		private static Database database;
		private static SixAxisController dualShock;
		
	

		/**
		 * The Driver of the PDS program.
		 *
		 * @param
		 * 		args0 - the filename providing the information given for the day.
		 * @return
		 * 		void
		 */
		public static void main(String[] args) {
			
			// get the filename and assign all of the listed values to the
			//ConstantValues class.  Create all needed objects.
			cV = new ConstantValues();
			database = null;

			try {
				database = new Database("cusInfoString","orderIDsString","ordersString",cV);
				cV.load( new FileInputStream("configFile"));
				
			} catch (IOException IOE) {
				IOE.printStackTrace();
			}		
			
			OrderManager oM = new OrderManager(cV);
			//dualShock = new SixAxisController(oM, database);

			String[] del = new String[1];
			del[0] = cV.getProperty( "numOfDrivers" );
			Delivery d = new Delivery(del,oM);

			String[] ov = new String[1];
			ov[0] = cV.getProperty( "totalOvenSpaces" );
			Oven o = new Oven(ov,oM,d);
			
			String[] prep = new String[1];
			prep[0] = cV.getProperty( "numOfCooks" );
			Preparation p = new Preparation(prep,oM,o);
			
			
			cV.link( p, o, d, oM,database );
			
			
			//Timer time = new Timer(cV.getProperty( "timeMultiplier" ),p,o,d,oM, dualShock);
			
			//time.start();
			System.out.println("PDS READY!");
			
			Order order1 = new Order(1,"3107176912","RIT","nameorder1",cV);
			
			Vector<String> toppings = new Vector<String>();
			toppings.add("a");toppings.add("b");toppings.add("c");toppings.add("d");toppings.add("e");
			
			FoodItem[] foods = new FoodItem[100];
			

			for(int i = 0;i<100;i++){
				foods[i] = new PizzaLog(cV,3,i);
				if(i<2){
					foods[i] = new Pizza(cV, toppings, i, 1, 'S');
					order1.addFoodItem( foods[i] );
				}else if(i<5){
					foods[i] = new Pizza(cV, toppings, i, 2, 'M');
					order1.addFoodItem( foods[i] );
				}else if(i<7){
					foods[i] = new Pizza(cV, toppings, i, 3, 'L');
					order1.addFoodItem( foods[i] );
				}else if(i<9){
					foods[i] = new PizzaLog(cV,4,i);
					order1.addFoodItem( foods[i] );
				}else if(i<11){
					foods[i] = new TossedSalad(cV,5,i);
					order1.addFoodItem( foods[i] );
				}else if(i<13){
					
				}
			}
			
			
			database.addOrder( order1.getOrderID() + "", order1.OrderCompression() );
			System.out.println(order1.OrderCompression());
			String[] f = new String[1];
			f[0] = order1.getOrderID() + "";
			
			Vector<Order> t = database.getOrders( f );
			
			System.out.println(t.get( 0 ).toString());
			Vector<String> orderFoods = t.get( 0 ).editorGUIToString();
			System.out.println(orderFoods.size());
//			for(String a : orderFoods){
//				System.out.println(a);
//			}
			//database.makeOrder( database.g );
			
			Vector<String> z = oM.managerReport();
			for(String x : z){
				System.out.println(x);
			}
			
			if(database.getCustomerInfo( "1" ) == null){
				System.out.println("null");
			}else if(database.getCustomerInfo( "1" ).equals( "" )){
				System.out.println(" empty string");
			}else {
				System.out.println("nothing good");
			}
			
			
			//shutDown();
			

			
		}
		private static void shutDown() {
			
			//any other databases 
			try {
				//DOES NOT STORE configFIle!
				//cV.store(new FileOutputStream("configFile"), "");
				database.shutDown();
			} catch ( FileNotFoundException e ) {
				e.printStackTrace();
			} catch ( IOException e ) {
				e.printStackTrace();
			}
			
		}
		
}
