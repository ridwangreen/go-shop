/**
 * States.java
 * 
 * Version:
 *     $id$
 *     
 * Revision:
 *     $log$
 */

package orders;

/**
 * An Enumerator to maintain the value of a FoodItem's state through meaningful
 * identifiers.
 *
 * @author 
 *		Shaun DeVos
 * 		Brian Baum
 * 		Rebecca Dudley
 *		Jonathan Johnson
 *		Jonathan Olin
 *
 */
public enum States {
	PrepState(0),
	CookState(1),
	WaitDelState(2),
	OmwState(3),
	DeliveryState(4);

	private final int index;
	
	States(int index){
		this.index = index;
	}
	
	public int index(){
		return index;
	}
	
	public States changeState(int newState){
		switch(newState){
		case 0:
			return States.PrepState;
		case 1:
			return States.CookState;
		case 2:
			return States.WaitDelState;
		case 3:
			return States.OmwState;
		case 4:
			return States.DeliveryState;
		default:
			System.out.println("bad state");
			return States.OmwState;
		}
	}
}
