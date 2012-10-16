/**
 * ConstantValues.java
 * 
 * Version:
 *     $id$
 *     
 * Revision:
 *     $log$
 */

package persistance;

import java.util.Properties;
import orders.OrderManager;
import components.Delivery;
import components.Oven;
import components.Preparation;

/**
 * This class contains all of the static values and statistics to be contained
 * within the PDS.
 *
 * @author 
 *		Shaun DeVos
 * 		Brian Baum
 * 		Rebecca Dudley
 *		Jonathan Johnson
 *		Jonathan Olin
 */
public class ConstantValues extends Properties{
	//extra stuff here if needed
	
	
	
	//Components
	
	/**
	 * 
	 */
	protected Preparation preparation;
	
	/**
	 *   
	 */
	protected Oven oven;
	
	/**
	 * 
	 */
	protected Delivery deliver;
	
	/**
	 *  
	 */
	OrderManager orders;
	
	Database database;
	/**
	 * 
	 *
	 * @param p
	 * @param o
	 * @param d
	 * @param om
	 * void
	 */
	public void link(Preparation p,Oven o,Delivery d, OrderManager om,Database database){
		this.preparation = p;
		this.oven = o;
		this.deliver = d;
		this.orders = om;
		this.database = database;
	}
	
	public Database getDatabase(){
		return database;
	}
	
	/**
	 * 
	 *
	 * @return
	 * 		Preparation
	 */
	public Preparation getPreparation() {
		return preparation;
	}

	/**
	 * 
	 *
	 * @return
	 * 		Oven
	 */
	public Oven getOven() {
		return oven;
	}


	/**
	 * 
	 *
	 * @return
	 * 		Delivery
	 */
	public Delivery getDeliver() {
		return deliver;
	}
	
	/**
	 * 
	 *
	 * @return
	 * 		OrderManager
	 */
	public OrderManager getOrderManager(){
		return orders;
	}
	
	
	
}