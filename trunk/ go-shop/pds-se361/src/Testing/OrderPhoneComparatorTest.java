/*
 * Filename:
 *		OrderPhoneComparatorTest.java
 *
 * Version:
 *	   $Id: OrderPhoneComparatorTest.java 406 2011-02-14 03:23:22Z Brian Baum $
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
public class OrderPhoneComparatorTest {
	
	private OrderPhoneComparator opc;
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
		
		opc = new OrderPhoneComparator();
		
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
		
		o1 = new Order(1, "1234567890", "RIT", "Bob", cV);
		FoodItem o1f1 = new PizzaLog(cV, 1, 1);
		o1.addFoodItem( o1f1 );
		for (int i=0; i<5; i++) {
			o1.update();
		}
		
		o2 = new Order(2, "9876543210", "St._John_Fisher", "Fred", cV);
		FoodItem o2f1 = new TossedSalad(cV, 2, 1);
		FoodItem o2f2 = new Pizza(cV, new Vector<String>(), 2, 2, 'M');
		o2.addFoodItem( o2f1 );
		o2.addFoodItem( o2f2 );
		
		o3 = new Order(3, "1234567890", "RIT", "George", cV);
		FoodItem o3f1 = new TossedSalad(cV, 3, 1);
		FoodItem o3f2 = new PizzaLog(cV, 3, 2);
		FoodItem o3f3 = new Pizza(cV, new Vector<String>(), 3, 3, 'L');
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
	public void testOrderPhoneGreater() {
		assertTrue(opc.compare(o2, o1) > 0);
	}
	
	@Test
	public void testOrderPhoneLess() {
		assertTrue(opc.compare( o1, o2 ) < 0);
	}
	
	@Test
	public void testOrderPhoneEqual() {
		assertTrue(opc.compare( o1, o3 ) == 0);
	}

}
