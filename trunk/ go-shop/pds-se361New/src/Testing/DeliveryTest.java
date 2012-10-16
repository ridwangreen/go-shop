/*
 * Filename:
 *		DeliveryTest.java
 *
 * Version:
 *	   $Id$
 *
 * Revision:
 *	   $Log$
 */
package Testing;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;

import orders.Order;
import orders.OrderManager;

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
import foodItems.PizzaLog;


/**
 *
 * @author 
 *		Shaun DeVos
 * 		Brian Baum
 * 		Rebecca Dudley
 *		Jonathan Johnson
 *		Jonathan Olin
 */
public class DeliveryTest {
	
	ConstantValues cV;
	Preparation p;
	Oven o;
	Delivery d;
	OrderManager om;
	Order order;
	FoodItem lPizza;
	FoodItem pizzaLog;

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

		//specific part
		order = new Order(1, "testnumber", "RIT", "testOwner", cV);
		lPizza = new Pizza(cV, new Vector<String>(), 0, 1, 'L');
		pizzaLog = new PizzaLog(cV, 1, 1);
		om.addNewOrder( 1, order );
		order.addFoodItem( lPizza );
		order.addFoodItem( pizzaLog );
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
	 * Test method for {@link components.Delivery#update()}.
	 */
	@Test
	public void testUpdate() {
		fail( "Not yet implemented" );
	}

	/**
	 * Test method for {@link components.Delivery#deliverFoodItem(foodItems.FoodItem)}.
	 */
	@Test
	public void testDeliverFoodItem() {
		fail( "Not yet implemented" );
	}

	/**
	 * Test method for {@link components.Delivery#deliverOrder(orders.Order)}.
	 */
	@Test
	public void testDeliverOrder() {
		fail( "Not yet implemented" );
	}

}
