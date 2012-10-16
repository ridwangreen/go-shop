/*
 * Filename:
 *		OrderIDComparatorTest.java
 *
 * Version:
 *	   $Id: OrderIDComparatorTest.java 406 2011-02-14 03:23:22Z Brian Baum $
 *
 * Revision:
 *	   $Log$
 */
package Testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import components.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;

import orders.*;

import foodItems.*;

import persistance.ConstantValues;
import persistance.Database;

/**
 *
 * @author 
 *		Shaun DeVos
 * 		Brian Baum
 * 		Rebecca Dudley
 *		Jonathan Johnson
 *		Jonathan Olin
 */
public class OrderIDComparatorTest {
	
	private OrderIDComparator oic;
	private OrderManager om;
	private Preparation p;
	private Oven o;
	private Delivery d;
	
	private static ConstantValues cV;
	
	private Order o1;
	private Order o2;
	private Order o3;
	
	/**
	 *
	 * @throws java.lang.Exception
	 * void
	 */
	@Before
	public void setUp() throws Exception {
		
		oic = new OrderIDComparator();
		
		cV = new ConstantValues();
		try {
			cV.load( new FileInputStream("configFile") );
		} catch (IOException e) {
			e.printStackTrace();
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
		
		o1 = new Order(1, "5551234567", "RIT", "Bob", cV);
		FoodItem o1f1 = new PizzaLog(cV, 1, 1);
		o1.addFoodItem( o1f1 );
		for (int i=0; i<5; i++) {
			o1.update();
		}
		
		o2 = new Order(2, "5559874444", "St._John_Fisher", "Fred", cV);
		FoodItem o2f1 = new TossedSalad(cV, 2, 2);
		o2.addFoodItem( o2f1 );
		
		o3 = new Order(2, "5553338282", "RIT", "Bob", cV);
		FoodItem o3f1 = new TossedSalad(cV, 2, 1);
		FoodItem o3f2 = new PizzaLog(cV, 2, 2);
		FoodItem o3f3 = new Pizza(cV, new Vector<String>(), 3, 2, 'L');
		o3.addFoodItem( o3f1 );
		o3.addFoodItem( o3f2 );
		o3.addFoodItem( o3f3 );
		
		for (int i=0; i<4; i++) {
			o1.update();
			o2.update();
			o3.update();
		}
	}

	/**
	 *
	 * @throws java.lang.Exception
	 * void
	 */
	@After
	public void tearDown() throws Exception {
		om = null;
		p = null;
		o = null;
		d = null;
		cV = null;
	}
	
	@Test
	public void testOrderIDGreater() {
		assertTrue(oic.compare(o2, o1) < 0);
	}
	
	@Test
	public void testOrderIDLess() {
		assertTrue(oic.compare( o1, o3 ) > 0);
	}
	
	@Test
	public void testOrderIDEqual() {
		assertTrue(oic.compare( o2, o3 ) == 0);
	}

}
