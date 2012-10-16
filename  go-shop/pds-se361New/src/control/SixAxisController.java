/*
 * Filename:
 *	   SixAxisController.java
 *
 * Version:
 *	   $Id$
 *
 * Revision:
 *	   $Log$
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Vector;

import foodItems.FoodItem;
import foodItems.Pizza;
import foodItems.PizzaLog;
import foodItems.TossedSalad;

import orders.*;
import persistance.ConstantValues;
import persistance.Database;
import updater.Timer;
import GUI.*;

/**
 * The controller program for the Pizza Delivery System.
 * 
 * @author Shaun DeVos Brian Baum Rebecca Dudley Jonathan Johnson Jonathan Olin
 */
public class SixAxisController implements ActionListener {

	/**
	 * Holds the orders in a hash table
	 */
	OrderManager	oM;
		
	String currentFocus;
	
	String phone_num;
	
	/**
	 * All past orders and startup/closing information passes through this
	 * object
	 */
	Database		database		= null;

	boolean checkForPastOrders = false;
	
	overrideScreen overrideGui = null;
	
	/**
	 * The Main User interface page
	 */
	MainGUIPage		mainGUI			= null;

	/**
	 * The Login Screen
	 */
	LoginScreen		login_screen	= null;

	/**
	 * A Manager Report object to allow to manipulation of the statistics
	 */
	ManagerReport	report_screen	= null;

	popUpScreen pop = null;
	
	/**
	 * Menu that allows the viewing, changing, and creating of order specifics
	 * and change them.
	 */
	OrderEditor		orderEditorGUI	= null;

	/**
	 * The User Interface for viewing all the passed orders and allowing access
	 * to them
	 */
	PastOrderView	PastOrderGUI	= null;

	/**
	 * The temporary order object to add items to and then add to the
	 * OrderManager
	 */
	Order			currentOrder	= null;

	/**
	 * An instance of constant values that stores all common program variables
	 * and all of the statistics for the program.
	 */
	ConstantValues	cV;

	/**
	 * Default Sorting Command value (used by timer to update without noticing
	 * any changes)
	 */
	int				sortValue		= -1;
	private Timer	timmmer;

	/**
	 * This class should know how to print numbers as dollars
	 */
	
	private NumberFormat dollars = NumberFormat.getCurrencyInstance();
	
	/**
	 * The 'Controller' of the Model View Controller in the PDS program.
	 * Communicates with the User Interface
	 * 
	 * @param table_of_orders
	 *            - the table that contains all current order information
	 * @param info_base
	 *            - object containing all the static values and past orders.
	 */
	public SixAxisController( OrderManager table_of_orders, Database info_base,
			ConstantValues all_Cons ) {

		database = info_base;
		oM = table_of_orders;
		cV = all_Cons;

		mainGUI = new MainGUIPage( this );
		login_screen = new LoginScreen( this );
		orderEditorGUI = new OrderEditor( this );
		PastOrderGUI = new PastOrderView( this );
		mainGUI.setVisible(true);
		this.currentFocus = mainGUI.getName();
	}

	/**
	 * The setter for all the main GUI pages.
	 * 
	 * @param time 
	 * 
	 * @param main
	 *            - the main GUI for current order display and user options
	 * @param login
	 *            - the login prompt in the GUI for a manager to access company
	 *            information
	 * @param edit
	 *            - the order edit screen in the GUI
	 * @return void
	 */
	public void setGUIs( Timer time ) {

		this.timmmer = time;
	}

	/**
	 * Sets all the visibilities of every GUI window to which view is intended
	 * 
	 * @param main
	 *            - MainGUIPage visibility
	 * @param login
	 *            - LoginScreen visibility
	 * @param report
	 *            - ManagerReport visibility
	 * @param edit
	 *            - OrderEditor visibility
	 * @param oldOrder
	 *            - PastOrderView visibility
	 * @return void
	 */
	public void setAllGUIVisibilities( boolean main, boolean login,
			boolean report, boolean edit, boolean oldOrder ) {

		mainGUI.setVisible( main );
		login_screen.setVisible( login );
		if ( report_screen != null ) {
			report_screen.setVisible( report );
		}

		orderEditorGUI.setVisible( edit );
		if ( PastOrderGUI != null ) {
			PastOrderGUI.setVisible( oldOrder );

		}
	}

	/**
	 * Reacts when an event is triggered by an action event in the GUI. Extracts
	 * the string identifier of the acting object and decides accordingly.
	 * 
	 * @param new_event
	 *            - The event triggered by the GUI
	 * @return void
	 */
	public void actionPerformed( ActionEvent event ) {

		// TODO	
		// MAke this all a gigantic switch!!! (via enum perhaps? *scratches
		// chin*)

		String command = event.getActionCommand();

		// BACK on LOGINSCREEN, MANAGERREPORT, and ORDEREDITOR

		if ( command.equals( "Back" ) )
			backCommand();

		// LOGIN on the LOGINSCREEN

		else if ( command.equals( "Login" ) )
			loginCommand();

		// -----------------MAINGUIPAGE COMMANDS---------------------

		// TODO
		// Please give me a design pattern for the love of god!(or some deity)

		else if ( command.equals( "Manager Report" ) )
			managerReportCommand();
		else if ( command.equals( "Add New Order" ) )
			newOrderCommand();
		else if ( command.equals( "Edit Order" ) )
			editOrderCommand();
		else if ( command.equals( "Cancel Order" ) )
			cancelOrderCommand();
		else if ( command.equals( "Order Number" ) )
			sortingCommand( 0 );
		else if ( command.equals( "Name" ) )
			sortingCommand( 1 );
		else if ( command.equals( "Phone #" ) )
			sortingCommand( 2 );
		else if ( command.equals( "Time Elapsed" ) )
			sortingCommand( 3 );
		else if ( command.equals( "ETA" ) )
			sortingCommand( 4 );
		else if ( command.equals( "Current State" ) )
			sortingCommand( 5 );
		else if ( command.equals( "Quit" ) )
			quitCommand();
		else if (command.equals( "Copy" ))
			copyCommand();
		else if (command.equals( "Ok" ))
			okCommand();
		else if (command.equals( "Check" ))
			checkCommand();
		else if (command.equals( "Overwrite" ))
			overWriteCommand();
			if (command.equals( "Cancel"))
			cancelCommand();
		else

		// -------------------ORDEREDITOR COMMANDS--------------------

		if ( command.equals( "Past Orders" ) )
			pastOrdersCommand();
			

		else if ( command.equals( "Add Item" ) )
			addItemCommand();
		else if ( command.equals( "Delete Item" ) )
			deleteItemCommand();
		else if ( command.equals( "Place Order" ) )
			placeOrderCommand();
	}

	/**
	 *
	 * void
	 */
	private void cancelCommand() {
		this.overrideGui.setVisible( false );
	}

	/**
	 *
	 * void
	 */
	private void pastOrdersCommand() {
		if(checkForPastOrders){
			setAllGUIVisibilities( true, false, false, true, true );
			
			
			Vector<Order> pastOrders = database.getCustomerOrders( phone_num  );
			
			Vector<String> orderList = new Vector<String>();
			
			for(Order order : pastOrders){
				orderList.add(order.toString());
				
				for(String line : order.editorGUIToString()){
					orderList.add( line );
				}
				orderList.add( "" );
			}
			
			
			
			
			this.PastOrderGUI.addItemToList( orderList );
		}else{
			popUp("No past orders for this Number, Make sure to press Check!");
		}
	}

	/**
	 *
	 * void
	 */
	private void overWriteCommand() {
		int orderID = safeParseInt(PastOrderGUI.getOrderIDText());
		
			String[] orderIDarray = database.getOrderIDs( phone_num );
			
			boolean ownsThisOrder = false;
			
			for(int i = 0; i < orderIDarray.length; i++){
				if(orderIDarray[i].equals( orderID + "" )){
					ownsThisOrder = true;
				}
			}
			
			if(!ownsThisOrder){
				popUp("error you do not own this order! " +
						"\n On top of each order is the order ID " +
						"\n you need to type it into the box ex: 1337");
				this.overrideGui.setVisible( false );
				return;
			}
		
		Order oldOrder = database.makeOrderID( orderID + "" );
		
		String phone_num = orderEditorGUI.getPhoneTextField();
		String address = orderEditorGUI.getOrderAddress();
		String name = orderEditorGUI.getNameTextField();
		
		currentOrder = new Order(orderID, phone_num, address, name, cV);
		currentOrder.addFoods( oldOrder.getFoods() );
		this.overrideGui.setVisible( false );
		this.PastOrderGUI.setVisible( false );
		orderEditorGUI.addItemToList( currentOrder.editorGUIToString() );
		orderEditorGUI.setTotalPrice( dollars.format( currentOrder.getTotal()));
	}

	/**
	 *
	 * void
	 */
	private void checkCommand() {
		//checkForPastOrders 
		
		phone_num = orderEditorGUI.getPhoneTextField();
		
		if(database.getCustomerInfo( phone_num ) == null){
			checkForPastOrders = false;
			return;
		}
		
		String cusInfo = database.getCustomerInfo( phone_num );
		
		String[] info = cusInfo.split( "," );
		
		orderEditorGUI.setNameTextField( info[0] );
		orderEditorGUI.setAddressDropDown( info[1] );
		
		checkForPastOrders = true;
		//address = orderEditorGUI.
		//String address = orderEditorGUI.getOrderAddress();
		//String name = orderEditorGUI.getNameTextField();
	}

	public void popUp(String text){
		pop = new popUpScreen( 
				this, text );
	}
	
	private void okCommand(){
		pop.setVisible( false );
	}
	/**
	 * 
	 * quits the program
	 */
	private void quitCommand() {
		shutDown();
		timmmer.setRunning( false );
		mainGUI.dispose();
		
		this.login_screen.dispose();
		this.orderEditorGUI.dispose();
		this.PastOrderGUI.dispose();
		
		if(overrideGui != null){
			this.overrideGui.dispose();
		}
		
		if(pop != null){
			this.pop.dispose();
		}
		
		if(report_screen != null){
			this.report_screen.dispose();
		}
		// TODO need to remove all guis??
		// TODO quit the system

	}
	
	private void copyCommand(){
		this.overrideGui = new overrideScreen(this);
	}

	private void shutDown() {

		// any other databases
		try {

			database.shutDown();
		} catch ( FileNotFoundException e ) {
			e.printStackTrace();
		} catch ( IOException e ) {
			e.printStackTrace();
		}

	}

	// -----------------------ALL COMMAND CODING-----------------------

	/**
	 * Carry's out the Back Button action command
	 * 
	 * @return void
	 */
	private void backCommand() {
		if ( login_screen.isVisible()
				|| ( report_screen != null && report_screen.isVisible() )
				|| !PastOrderGUI.isVisible() ) {

			setAllGUIVisibilities( true, false, false, false, false );
		} else

		// BACK on PASTORDERVIEW

		if ( PastOrderGUI.isVisible() ) {
			PastOrderGUI.setVisible( false );
			orderEditorGUI.setVisible( true, false );
		}
		
		
	}

	/**
	 * Carry's out the login action
	 * 
	 * @return void
	 */
	private void loginCommand() {
		
		String userName = login_screen.getUserName();
		char[] password = login_screen.getPassword();

		if ( database.checkPassword( userName, password ) ) {
			report_screen = new ManagerReport( this );
			report_screen.addItemToList( oM.managerReport() );
			setAllGUIVisibilities( true, false, true, false, false );
		} else {
			popUp("Invalid Password");
			// System.err.println( "INVALID PASSWORD" );
		}
	}

	/**
	 * Carry's out the manager Reoprt action
	 * 
	 * @return void
	 */
	private void managerReportCommand() {
		setAllGUIVisibilities( true, true, false, false, false );
	}

	/**
	 * Carry's out the edit order action
	 * 
	 * @return void
	 */
	private void editOrderCommand() {

		if ( PastOrderGUI.isVisible() || orderEditorGUI.isVisible() ) {

			popUp("Error: currently editing order");
			return;
		}
		
		
		// TODO GET the order from the text field
		String sOrderID = mainGUI.getIndexToEditOrRemove();
		int orderID = this.safeParseInt( sOrderID );
		if ( orderID == -1 ) {
			popUp("Error: No such order" );
			// System.out.println( "NO SUCH ORDER !!!!! OMGOGMOGOMGMOGOMGOMG" );
			return;
		}else if(oM.getOrder( orderID ) == null){
			popUp("Error no such order");
			return;
		}
		setAllGUIVisibilities( true, false, false, true, false );

		//currentOrder = oM.getOrder( orderID );
		currentOrder = oM.getOrder( orderID ).clone();
		//System.out.println(currentOrder.getCookTime());
		
		orderEditorGUI.setNameTextField( currentOrder.getNameOwner() );
		orderEditorGUI.setPhoneTextField( currentOrder.getPhoneNumber() );
		orderEditorGUI.setAddress( currentOrder.getLocation() );

		orderEditorGUI.addItemToList( currentOrder.editorGUIToString() );
		orderEditorGUI.setTotalPrice( dollars.format( currentOrder.getTotal()));
	}

	/**
	 * Carry's out the new order command
	 * 
	 * @return void
	 */
	private void newOrderCommand() {
		setAllGUIVisibilities( true, false, false, true, false );
		orderEditorGUI.resetFields();
		this.currentOrder = new Order( database.getNewOrderID(), cV );
		orderEditorGUI.setTotalPrice( dollars.format( currentOrder.getTotal()));
	}

	/**
	 * Carry's out the cancellation of an order
	 * 
	 * @return void
	 */
	private void cancelOrderCommand() {
		
		if ( PastOrderGUI.isVisible() || orderEditorGUI.isVisible() ) {

			popUp("Error: currently editing order");
			return;
		}
		
		String orderString = mainGUI.getIndexToEditOrRemove();
		
		int orderID = this.safeParseInt( orderString );
		if ( orderID == -1 ) {

			popUp("Error: Invalid Number" );
			// System.err.println( "invalid number" );
			return;
		}

		if ( oM.getOrder( orderID ) != null ) {
			oM.removeOrder( orderID );
		} else {
			popUp("Error: ORDER does not Exist");
			// System.err.println( "ORDER does not exsit" );
		}
	}

	public void sortingCommand() {
		OrderComparator comparator;

		// figure out which comparator is required

		switch ( sortValue ) {
		case 0:
			comparator = new OrderIDComparator();
			;
			break;
		case 1:
			comparator = new OrderNameComparator();
			break;
		case 2:
			comparator = new OrderPhoneComparator();
			break;
		case 3:
			comparator = new OrderDurationComparator();
			break;
		case 4:
			comparator = new OrderETAComparator();
			break;
		case 5:
			comparator = new OrderStateComparator();
			break;
		default:
			comparator = new OrderIDComparator();
			break;
		}

		mainGUI.addAllOrders( oM.getSortedOrderList( comparator ) );
		//		
		// if( currentOrdersVector != null && currentOrdersVector.size() > 0 ) {
		//					
		// //loop through the table of current orders and add them to the GUI
		//			
		//			
		// Iterator<Order> traverse_orders = currentOrdersVector.iterator();
		// Vector<String> all_order_strings = new Vector<String>();
		//			
		// while( traverse_orders.hasNext() ) {
		// all_order_strings.add( traverse_orders.next().mainGUIToString() );
		// }
		// mainGUI.addAllOrders( all_order_strings );
		// } else {
		// System.out.println("No Current Orders to sort >.<");
		// }
	}

	/**
	 * Carry's out the order number action and updates the GUI (timer class uses
	 * -1 to update GUI)
	 * 
	 * 0 = Order Number 1 = Name 2 = Phone # 3 = Time Elapsed 4 = ETA 5 =
	 * Current State Default = Order Number
	 * 
	 * @return void
	 */
	public void sortingCommand( int which_sort ) {
		this.sortValue = which_sort;
		sortingCommand();
	}

	/**
	 * Carry's out the place order action
	 * 
	 * @return void
	 */
	private void placeOrderCommand() {

		// TODO before the command is called needs to check to see if data is
		// valid

		String phone_num = orderEditorGUI.getPhoneTextField();
		String address = orderEditorGUI.getOrderAddress();
		String name = orderEditorGUI.getNameTextField();

		// Only proceed if we have all the information required for an Order

		if ( !this.currentOrder.getFoods().isEmpty() && !phone_num.equals( "" )
				&& !address.equals( "" ) && !name.equals( "" ))  {
			String test = "";
			test += phone_num;
			test +=name;
			
			
			if(test.contains( "," ) || test.contains( ";" ) || 
					test.contains( "\\" ) || test.contains( "!" ) || 
					test.contains( "=" ) || test.contains( "_" )){
				
				popUp("Info cannot contain , : \\ _ = * ! ");
				return;
			}
			this.currentOrder.addInfo( phone_num, address, name );

			//System.out.println( "Created: " + currentOrder.editorGUIToString() );

			// //Add the order to the OrderManager and the GUI
			if(oM.getOrder( currentOrder.getOrderID())== null){
				currentOrder.startOrder();
				database.addCustomerInfo( phone_num, name, address );
				database.addOrder( currentOrder.getOrderID() + "", currentOrder.OrderCompression() );
			}else{
				if(oM.getOrder( currentOrder.getOrderID()).getCurrentState() == 
					States.OmwState || oM.getOrder( currentOrder.getOrderID()).getCurrentState() 
					== States.DeliveryState){
					//order is past the state
					popUp("The order is already being delivered!!");
					return;
				}else{
					oM.getOrder( currentOrder.getOrderID() ).FixOrder(currentOrder);
				}
			}
			
			// mainGUI.addOrderToList( new_order.mainGUIToString() );
			setAllGUIVisibilities( true, false, false, false, false );

			// Wiping old data
			this.currentOrder = null;

		} else {

			// entry order ignored from system
			// TODO ERROR SCREEN STATING THE ERROR in this case no order info
			// yet
			// or could have a missing info screen stating what fields are empty

			popUp("Error: order not created properly" );
			// System.err.println( "Error: order not created properly" );
		}
	}

	/**
	 * Carry's out the delete item action
	 * 
	 * @return void
	 */
	private synchronized void deleteItemCommand() {
		String numString = orderEditorGUI.getItemToDelete();
		int number = this.safeParseInt( numString );
		if ( number == -1 ) {
			popUp("Error: Invalid Number");
			// System.err.println("invalid number");
			return;
		}
		this.currentOrder.removeFoodItem( number );
		orderEditorGUI.addItemToList( currentOrder.editorGUIToString() );
		orderEditorGUI.setTotalPrice( dollars.format( currentOrder.getTotal()));
	}

	/**
	 * Carry's out the add item action
	 * 
	 * @return void
	 */
	private void addItemCommand() {

		String itemType = orderEditorGUI.getFoodTypeSelection();
		int food_identifier_in_list = this.currentOrder.getNextIdentifier();
		FoodItem newFood = null;

		if ( itemType != null ) {

			// A FoodItem needs to know its type

			int new_orderID = this.currentOrder.getOrderID();

			// A Pizza cannot be made without knowing everything about it

			if ( itemType.equals( "Salad" ) ) {
				newFood = new TossedSalad( cV, new_orderID,
						food_identifier_in_list );
				//System.out.println( newFood );
			} else if ( itemType.equals( "Pizza Logs" ) ) {
				newFood = new PizzaLog( cV, new_orderID,
						food_identifier_in_list );
				//System.out.println( newFood );
			} else if ( itemType.equals( "Pizza" ) ) {

				char size = orderEditorGUI.getPizzaSizeSelections();
				Vector<String> toppings = orderEditorGUI
						.getPizzaToppingsSelections();

				if ( size != ' ' ) {
					newFood = new Pizza( cV, toppings, food_identifier_in_list,
							new_orderID, size );

				} else {
					popUp("Error: Pizza missing size");
					// System.out.println( "Error: Pizza missing size" );
					return;
				}
			}
			this.currentOrder.addFoodItem( newFood );
			// TODO UPDATE GUI HERE
			orderEditorGUI.addItemToList( currentOrder.editorGUIToString() );
			orderEditorGUI.setTotalPrice( dollars.format( currentOrder.getTotal()));
			// currentOrder.editorGUIToString()

			// use addItemToList()
		}
		;
	}

	public int safeParseInt( String integerStirng ) {
		int result = -1;
		try {
			result = Integer.parseInt( integerStirng );
		} catch ( NumberFormatException e ) {
			// number was wrong so the function will return -1 and the function
			// who called it must deal with that
		}
		return result;
	}
}