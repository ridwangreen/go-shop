/**
 * Filename:
 *		OrderIDComparator.java
 *
 * Version:
 *	   $Id: OrderIDComparator.java 175 2011-02-01 21:26:38Z sdd7427 $
 *
 * Revision:
 *	   $Log$
 */
package orders;

/**
 * Compares Orders by unique IDs.
 *
 * @author 
 *		Shaun DeVos
 * 		Brian Baum
 * 		Rebecca Dudley
 *		Jonathan Johnson
 *		Jonathan Olin
 *
 */
public class OrderIDComparator implements OrderComparator {

	/**
	 * Compares two Orders (of the food kind) for order (of the sorting kind)
	 * based on the Orders' IDs.
	 * 
	 * @param o1 - The first Order to compare
	 * @param o2 - The second Order to compare
	 * 
	 * @return
	 * 		A negative integer, zero, or positive integer as the first Order
	 * 		is less than, equal to, or greater than the second.
	 */
	public int compare(Order o1, Order o2) {
		return o2.getOrderID() - o1.getOrderID();
	}
}