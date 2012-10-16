/*
 * Filename:
 *		PizzaTest.java
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
import java.util.Arrays;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistance.ConstantValues;

import foodItems.*;


/**
 *
 * @author 
 *		Shaun DeVos
 * 		Brian Baum
 * 		Rebecca Dudley
 *		Jonathan Johnson
 *		Jonathan Olin
 */
public class PizzaTest {

ConstantValues cV;
Pizza sPizza;
Pizza mPizza;
Pizza lPizza;
Vector<String> someToppings;
Vector<String> moreToppings;

	/**
	 *
	 * @throws java.lang.Exception
	 * void
	 */
	@Before
	public void setUp() throws Exception {
		cV = new ConstantValues();

		try {
			cV.load( new FileInputStream("configFile"));
		} catch (IOException IOE) {
			IOE.printStackTrace();
		}

		
		sPizza = new Pizza(cV, new Vector<String>(), 0, 1, 'S');
		someToppings = new Vector<String>(
				Arrays.asList( "mushrooms", "peppers" ));
		mPizza = new Pizza(cV, someToppings, 1, 1, 'M');
		moreToppings = new Vector<String>(
				Arrays.asList( "sausage", "extra_cheese", "peppers", "olives" ));
		lPizza = new Pizza(cV, moreToppings, 2, 1, 'L');
		
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
	 * Test method for {@link foodItems.Pizza#compressToString()}.
	 */
	@Test
	public void testCompressToString() {
		System.out.println(lPizza);
		System.out.println(lPizza.compressToString());
	}

	/**
	 * Test method for {@link foodItems.Pizza#getToppings()}.
	 */
	@Test
	public void testGetToppings() {
		assertTrue(mPizza.getToppings().equals(someToppings));
	}

	/**
	 * Test method for {@link foodItems.Pizza#replaceToppings(java.util.Vector)}.
	 */
	@Test
	public void testReplaceToppings() {
		mPizza.replaceToppings( moreToppings );
		assertTrue(mPizza.getToppings().equals(moreToppings));
	}

}
