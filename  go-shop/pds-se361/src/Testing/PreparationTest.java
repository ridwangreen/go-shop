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

public class PreparationTest {
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

		// Prep-specific part
		order = new Order(1, "testnumber", "St._John_Fisher", "testOwner", cV);
		lPizza = new Pizza(cV, new Vector<String>(), 1, 1, 'L');
		om.addNewOrder( 1, order );
		order.addFoodItem( lPizza );
	}

	@After
	public void tearDown() throws Exception {
		cV = null;
		p = null;
		o = null;
		d = null;
		om = null;
	}

	@Test
	public void testPreparation() {
		p.PrepFoodItem(lPizza);
		//needs access to cV to use this so it has to be connected
		assertTrue(true);
	}

	@Test
	public void testUpdate() {
		p.PrepFoodItem( lPizza );
		int pizzaTime = lPizza.getPrepTime();
		for (int i = 0; i <= pizzaTime; i++) {
			p.update();
		}
		assertTrue(lPizza.getCurrentState() == States.CookState);
	}
	
	@Test
	public void testDeleteFoodItem() {
		p.PrepFoodItem( lPizza );
		int pizzaTime = lPizza.getPrepTime() / 2;
		for (int i = 0; i < pizzaTime; i++) {
			p.update();
		}
		p.deleteFoodItem( order.getOrderID(), lPizza.getIdentifier() );
		// testing if this worked is somewhat hard without
		// some sort of contains() method, but no error.
	}
	
	@Test
	public void testPrepFoodItem() {
		p.PrepFoodItem(lPizza);
		int pizzaTime = lPizza.getPrepTime();
		p.update(); // should add from queue
		p.update(); // should decrement prepTime
		assertTrue(lPizza.getPrepTime() == (pizzaTime - 1));
	}

}
