/**
 * Filename:
 *		OrderETAComparator.java
 *
 * Version:
 *	   $Id: OrderETAComparator.java 382 2011-02-13 02:38:36Z Brian Baum $
 *
 * Revision:
 *	   $Log$
 */
package orders;

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
public class OrderETAComparator implements OrderComparator {

	/**
	 * Compares two Orders (of the food kind) for order (of the sorting kind)
	 * based on the Orders' ETAs.
	 * 
	 * @param o1 - The first Order to compare
	 * @param o2 - The second Order to compare
	 * 
	 * @return
	 * 		A negative integer, zero, or positive integer as the first Order
	 * 		is less than, equal to, or greater than the second.
	 */
	public int compare(Order o1, Order o2) {
		return o1.getETA(true) - o2.getETA(true);
	}
}
