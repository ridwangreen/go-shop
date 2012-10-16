/**
 * Filename:
 *		OrderNameComparator.java
 *
 * Version:
 *	   $Id: OrderNameComparator.java 99 2011-01-24 16:56:35Z jao3030 $
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
public class OrderNameComparator implements OrderComparator {

	/**
	 * Compares two Orders (of the food kind) for order (of the sorting kind)
	 * based on the names associated with the Orders.
	 * 
	 * @param o1 - The first Order to compare
	 * @param o2 - The second Order to compare
	 * 
	 * @return
	 * 		A negative integer, zero, or positive integer as the first Order
	 * 		is less than, equal to, or greater than the second.
	 */
	public int compare(Order o1, Order o2) {
		return o1.getNameOwner().compareTo( o2.getNameOwner() );
	}
}
