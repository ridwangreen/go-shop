package Testing;

/**
 * Pineapple.java
 * 
 * Version:
 *     $id$
 *     
 * Revision:
 *     $log$
 */


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;

import GUI.LoginScreen;
import GUI.MainGUIPage;
import GUI.OrderEditor;
import GUI.PastOrderView;

import orders.Order;
import orders.OrderManager;
import persistance.ConstantValues;
import persistance.Database;
import updater.Timer;

import components.Delivery;
import components.Oven;
import components.Preparation;
import control.SixAxisController;
import foodItems.FoodItem;
import foodItems.Pizza;
import foodItems.PizzaLog;
import foodItems.TossedSalad;

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
class SystemTestMain {
	private static ConstantValues cV;
	private static Database database;
	private static SixAxisController dualShock;
	//private static ShutDownController shut_down;

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
		
		cV.link( p, o, d, oM ,database);
		
		//shut_down = new ShutDownController( database );
		//TODO do we need a controller to close a program...?
		
		dualShock = new SixAxisController( oM, database, cV );
		
		
		Timer time = new Timer(cV.getProperty( "timeMultiplier" ),p,o,d,oM, dualShock);
		
		dualShock.setGUIs( time);
		

		
		Order[] orders = new Order[1000];
		
		Vector<String> toppings = new Vector<String>();
		toppings.add("MONKEYS");toppings.add("LOL");toppings.add("HAHA");toppings.add("TURTLES");toppings.add("PICKLES");
		
		for(int i = 0; i < 1000; i++){
			orders[i] = new Order(database.getNewOrderID(),"(310)555-" + i,"RIT","Person#" + i,cV);
			orders[i].addFoodItem( new TossedSalad(cV,orders[i].getOrderID(),orders[i].getNextIdentifier()));
			orders[i].addFoodItem( new PizzaLog(cV,orders[i].getOrderID(),orders[i].getNextIdentifier()));
			orders[i].addFoodItem( new Pizza(cV,toppings,orders[i].getNextIdentifier(),orders[i].getOrderID(),'S'));
			orders[i].addFoodItem( new Pizza(cV,toppings,orders[i].getNextIdentifier(),orders[i].getOrderID(),'M'));
			orders[i].addFoodItem( new Pizza(cV,toppings,orders[i].getNextIdentifier(),orders[i].getOrderID(),'L'));
			orders[i].startOrder();
			database.addCustomerInfo( "1234" + i, "name" + i, "address" + i);
			database.addOrder( orders[i].getOrderID() + "", orders[i].OrderCompression() );
			
			
		}
		

		
		time.start();

		
		
		
		
	
		System.out.println("1000 orders started");
		
		
		
		//shut_down.setTimer( time );
		
		
	}
}