/*
 * Filename:
 *		DriverTest.java
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
import workers.Driver;

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
 */
public class DriverTest {
	
	ConstantValues cV;
	Preparation p;
	Oven o;
	Delivery d;
	OrderManager om;
	Order order;
	FoodItem lPizza;
	Driver driver;

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
		lPizza = new Pizza(cV, new Vector<String>(), 1, 1, 'L');
		om.addNewOrder( 1, order );
		order.addFoodItem( lPizza );
		driver = new Driver();
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
	 * Test method for {@link workers.Driver#deliver(orders.Order)}.
	 */
	@Test
	public void testDeliver() {
		assertFalse(driver.onDelivery());
		driver.deliver( order );
		assertTrue(driver.onDelivery());
	}

	/**
	 * Test method for {@link workers.Driver#update()}.
	 */
	@Test
	public void testUpdate() {
		driver.deliver( order );
		int deliver1 = driver.getDeliveryTime();
		for (int i = 0; i < 6; i++) {
			driver.update();
		}
		assertEquals((deliver1 + 6), driver.getDeliveryTime());
	}

}
