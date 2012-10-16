/**
 * Filename:
 *		OrderStateComparator.java
 *
 * Version:
 *	   $Id: OrderStateComparator.java 99 2011-02-02 00:38:35Z sdd7427 $
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
public class OrderStateComparator implements OrderComparator {

	/**
	 * Compares two Orders (of the food kind) for order (of the sorting kind)
	 * based on the Orders' States.
	 * 
	 * @param o1 - The first Order to compare
	 * @param o2 - The second Order to compare
	 * 
	 * @return
	 * 		A negative integer, zero, or positive integer as the first Order
	 * 		is less than, equal to, or greater than the second.
	 */
	public int compare(Order o1, Order o2) {
		return o2.getCurrentState().index() - o1.getCurrentState().index();
	}
}