/**
 * Timer.java
 * 
 * Version:
 *     $id$
 *     
 * Revision:
 *     $log$
 */
package updater;

import java.util.Iterator;

import components.Delivery;
import components.Oven;
import components.Preparation;
import control.SixAxisController;

import orders.Order;
import orders.OrderManager;

/**
 * 
 *
 * @author 
 *		Shaun DeVos
 * 		Brian Baum
 * 		Rebecca Dudley
 *		Jonathan Johnson
 *		Jonathan Olin
 */
public class Timer extends Thread {
	
	Preparation p;
	Oven o;
	Delivery d;
	OrderManager om;
	SixAxisController controller;
	boolean running;
	
	/**
	 * The 'accelerator' for the program.
	 * ex)
	 * 		if the multiplier is 2, the time goes by twice as fast while
	 * 		running.
	 */
	private double time_Multiplier;
	
	/**
	 * DO NOT USE THIS METHOD OR THE WORLD WILL END , tear
	 *
	 *
	 */
	public Timer(){
	}
	
	/**
	 * The Constructor for the program time simulator.  The higher the
	 * multiplier the faster the time goes.
	 * @param om 
	 * @param d 
	 * @param o 
	 * @param p 
	 * @param control
	 * @param String time_Multiplier - the time multiplier for the timer.
	 */
	public Timer(String time_Multiplier, Preparation p, Oven o, Delivery d,
			OrderManager om, SixAxisController control){
		this.running = true;
		this.p = p;
		this.o = o;
		this.d = d;
		this.om = om;
		this.controller = control;
		this.time_Multiplier = Double.parseDouble( time_Multiplier );
	}
	
	/**
	 * The run method controls the start of this Timer.
	 * 
	 * @Override
	 * 		Thread - super class.
	 * @return
	 * 		void
	 */
	public void run() {
		while( this.running ){
			
			try {
				
				sleep((int)(1000 * time_Multiplier));	
				updateAll();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * As long as there is an Order in the Order Manager updates all Components
	 * and Order arrays.
	 *
	 * @return
	 * 		void
	 */
	public void updateAll(){
		if (controller != null) {
			controller.sortingCommand();
		}
		Iterator<Integer> currOrderKey = om.getOrders().keySet().iterator();
		Order current;
		while(currOrderKey.hasNext()){
			current = om.getOrders().get( currOrderKey.next() );
			current.update();
		}
		
		d.update();
		o.update();
		p.update();		
	}
	
	/**
	 * Terminates this thread object
	 * 
	 * @param
	 * 		terminate - 
	 */
	public void setRunning( boolean terminate ) {
		this.running = terminate;		
	}
	
	/**
	 * Returns whether or not the thread is currently running
	 * 
	 * @return
	 * 		true  - if the thread is running
	 * 		false - if the thread is not running
	 */
	public boolean isRunning() {
		return this.running;
	}
}