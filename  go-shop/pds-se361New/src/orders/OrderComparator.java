/**
 * Filename:
 *		OrderComparator.java
 *
 * Version:
 *	   $Id: OrderComparator.java 174 2011-02-01 21:26:25Z sdd7427 $
 *
 * Revision:
 *	   $Log$
 */
package orders;

import java.util.Comparator;


/**
 * Compares two Orders (of the food kind) for order (of the sorting kind)
 *
 * @author 
 *		Shaun DeVos
 * 		Brian Baum
 * 		Rebecca Dudley
 *		Jonathan Johnson
 *		Jonathan Olin
 *
 */
public interface OrderComparator extends Comparator<Order> {

	/**
	 * Compares two Orders (of the food kind) for order (of the sorting kind).
	 * 
	 * @param
	 * 		o1 - The first Order to compare
	 * @param
	 * 		o2 - The second Order to compare
	 * 
	 * @return
	 * 		A negative integer, zero, or positive integer as the first Order
	 * 		is less than, equal to, or greater than the second.
	 */
	public int compare(Order o1, Order o2);
}