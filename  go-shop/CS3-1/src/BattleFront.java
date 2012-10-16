/*
 
* BattleFront.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */

/**
 * The BattleFront is a generic priority queue that orders elements of type T
 * based on their natural order comparison. It is required that T implements
 * Comparable<T>.
 * 
 * @author rcd1575: Becca Dudley
 */

public class BattleFront<T extends Comparable<T>> implements IQueue<T> {

	private Node<T> head; // The head of the queue
	private int size; // The current number of elements in the queue

	/**
	 * Constructor which initializes the head to null, and the current count to
	 * 0.
	 * 
	 */
	public BattleFront() {

		head = null;
		size = 0;

	}

	@Override
	public synchronized T dequeue() throws UnderflowException {
		// TODO Auto-generated method stub
		
		if(size == 0){
			throw new UnderflowException("There is no one on the battlefront!");
		}else{
			Node<T> oldHead = head;
			head = head.getNext();
			size--;
			return oldHead.getData();
		}
		
	}

	/**
	 * tells if the queue is empty
	 * 
	 * @return true if its empty and false if its not
	 */
	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	/**
	 * Adds an element to the queue based on the priority of the elements. The
	 * elements are ordered in ascending priority, from lowest to highest. The
	 * priority is determined by using Comparable's compareTo() method. Because
	 * this method modifies the collection, and multiple threads can invoke it,
	 * it is protected with the synchronized keyword.
	 * 
	 * @param element
	 *            The new element to insert into the queue
	 */
	@Override
	public synchronized void enqueue(T element) {

		Node<T> toAdd = new Node<T>(element);
		if (head == null) {
			head = toAdd;
			size++;
		} else {
			Node<T> current = head;
			while (current != null) {

				if (current.getData().compareTo(toAdd.getData()) < 0
						&& (current.getNext() != null && current.getNext()
								.getData().compareTo(toAdd.getData()) >= 0)) {

					toAdd.setNext(current.getNext());
					current.setNext(toAdd);
					size++;
					break;
					
				}
				if(current.getNext() == null){
					current.setNext(toAdd);
					size++;
					break;
				}
				current = current.getNext();

			}
		}

 	}

	/**
	 * tells if the queue is full or not
	 * the queue can never be full
	 * 
	 * @return false
	 */
	@Override
	public boolean full() {
		// TODO Auto-generated method stub
		return false;
	}

	
	/**
	 * Indicates how many elements are in the queue 
	 * 
	 * @return the number of elements in the queue
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

}
