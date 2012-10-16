/**
 * Filename:
 *		OrderTest.java
 *
 * Version:
 *	   $id$
 *
 * Revision:
 *	   $log$
 */
package Testing;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;

import orders.Order;
import orders.OrderManager;
import orders.States;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistance.ConstantValues;
import persistance.Database;

import components.Delivery;
import components.Oven;
import components.Preparation;

import foodItems.FoodItem;
import foodItems.Pizza;


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
public class OrderTest {
	ConstantValues cV;
	Preparation p;
	Oven o;
	Delivery d;
	OrderManager om;
	Order order;
	FoodItem lPizza;
	
	@Before
	public void setUp() throws Exception {
		// General part
		
		cV = new ConstantValues();

		try {
			cV.load( new FileInputStream("configFile"));
		} catch (IOException IOE) {
			IOE.printStackTrace();
		}
		
		om = new OrderManager(cV);

		String[] del = new String[1];
		del[0] = cV.getProperty( "numOfDrivers" );
		d = new Delivery(del,om);
		//System.out.println(d + " created");

		String[] ov = new String[1];
		ov[0] = cV.getProperty( "totalOvenSpaces" );
		o = new Oven(ov,om,d);
		//System.out.println(o + " created");
		
		String[] prep = new String[1];
		prep[0] = cV.getProperty( "numOfCooks" );
		p = new Preparation(prep,om,o);
		//System.out.println(p + " created");
		
		Database database = new Database(null, null, null, cV);
		cV.link( p, o, d, om,database );
		
		order = new Order(1, "testnumber", "RIT", "testOwner", cV);
		lPizza = new Pizza(cV, new Vector<String>(), 1, 1, 'L');
		om.addNewOrder( 1, order );

	}
	
	
	/**
	 *
	 * @throws java.lang.Exception
	 * void
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link orders.Order#tryUpdateOrderState(orders.States)}.
	 */
	@Test
	public void testTryUpdateOrderState(){
		lPizza.setCurrentState( States.CookState );
		order.tryUpdateOrderState( States.CookState );
		
		assertTrue(States.CookState == order.getCurrentState());
	}

	/**
	 * Test method for {@link orders.Order#addFoodItem(foodItems.FoodItem)}.
	 */
	@Test
	public void testAddFoodItem() {
		assertTrue(order.addFoodItem( lPizza ));
	}

	/**
	 * Test method for {@link orders.Order#removeFoodItem(foodItems.FoodItem)}.
	 */
	@Test
	public void testRemoveFoodItem() {
		order.addFoodItem( lPizza );
		Pizza mPizza = new Pizza(cV, new Vector<String>(), 0, 1, 'M');
		order.addFoodItem( mPizza );
		order.removeFoodItem( lPizza.getIdentifier() );
	}

	/**
	 * Test method for {@link orders.Order#startOrder()}.
	 */
	@Test
	public void testStartOrder() {
		Order testOrder = new Order(2, "testnumber2", "University_of_Rochester", "testOwner2", cV);
		testOrder.addFoodItem( lPizza );
		
		testOrder.startOrder();
		System.out.println("TEST" + testOrder);
		System.out.println("TEST2" + om.getOrder( 2 ));
		assertTrue(om.getOrder( 2 ) == testOrder);
		assertTrue(testOrder.getCurrentState() == States.PrepState);
		
	}

	/**
	 * Test method for {@link orders.Order#update()}.
	 */
	@Test
	public void testUpdate() {
		order.addFoodItem( lPizza );
		order.startOrder();
		
	}
	/**
	 * Test method for {@link orders.Order#OrderCompression()}.
	 */
	@Test
	public void testCompress() {
		order.addFoodItem( lPizza );
		System.out.println(order.OrderCompression());
	}

}
