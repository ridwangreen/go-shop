/*
 * DynamicList.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */
import java.util.Comparator;

/**
 * DynamicList program for project2 holds generics
 * 
 * @author rcd1575: Rebecca Dudley
 */
public class DynamicList<E> implements CS3List<E> {

	protected LNode<E> front;

	protected class LNode<E> {

		private E data;
		private LNode<E> next;

		/**
		 * creates a new node holding specified data
		 * 
		 * @param data  the data for this node
		 */
		public LNode(E data) {

			this.data = data;
		}

		/**
		 * creates a new node with specified data and next node
		 *  
		 * @param data   the data for this node
		 * @param next   the next node
		 */
		public LNode(E data, LNode<E> next) {

			this.data = data;
			this.next = next;
		}

		/**
		 * accessor for the next node
		 * 
		 * @return the next node
		 */
		public LNode<E> getNext() {
			return next;
		}

		/**
		 * accessor for the data
		 * 
		 * @return the data for this node
		 */
		public E getData() {
			return data;
		}

		/**
		 * mutator for the data
		 * 
		 * @param newData   the new data for this node
		 */
		public void setData(E newData) {
			data = newData;
		}

		/**
		 * mutator for the next node
		 * 
		 * @param newNext  the new next node
		 */
		public void setNext(LNode<E> newNext) {
			next = newNext;
		}

	}

	/**
	 * creates a new list with front equal to null
	 */
	public DynamicList() {

		front = null;
	}

	/**
	 * adds a new node to the end of the list containing specified data
	 * 
	 * @param data   the new data
	 */
	@Override
	public void add(E data) {
		// TODO Auto-generated method stub

		if (front == null) {
			front = new LNode<E>(data);
			// System.out.println(data.toString()+"a");
		} else {
			LNode<E> current = front;

			while (current.getNext() != null) {
				current = current.getNext();
			}

			current.setNext(new LNode<E>(data));

		}

	}

	/**
	 * determines if the list is empty
	 * 
	 * @return true if the list is empty
	 */
	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		if (front == null) {
			return true;
		}
		return false;
	}

	/**
	 * inserts a new node in a sorted manner
	 * 
	 * @param data   the data to be added
	 * @param comp   the comparator that determines how the list is sorted
	 */
	@Override
	public void insert(E data, Comparator<E> comp) {
		// TODO Auto-generated method stub
		if (front == null) {
			front = new LNode<E>(data);
		} else {
			LNode<E> toAdd = new LNode<E>(data);
			if (comp.compare(data, front.getData()) < 0) {
				toAdd.setNext(front);
				front = toAdd;
			} else {
				LNode<E> current = front;
				boolean added = false;
				while (current.getNext() != null) {

					if (comp.compare(data, current.getData()) > 0
							&& comp.compare(data, current.getNext().getData()) < 0) {
						toAdd.setNext(current.getNext());
						current.setNext(toAdd);
						added = true;
						break;
					}

					// System.out.println(current.getData().toString());
					current = current.getNext();
				}
				if (!added) {
					current.setNext(toAdd);
				}
			}

		}
	}
	
	/**
	 * accessor for the last node in the list
	 * 
	 * @return  the last node in the list
	 */
	public E getLast() {

		if (front != null) {
			LNode<E> current = front;
			while (current.getNext() != null) {
				current = current.getNext();
			}
			return current.getData();
		} else {
			return null;
		}
	}

	/**
	 * returns the length of the list
	 * 
	 * @return the length of the list
	 */
	@Override
	public int length() {
		// TODO Auto-generated method stub
		if (front == null) {
			return 0;
		} else {
			int l = 1;
			LNode<E> current = front;
			while (current.getNext() != null) {
				l++;
				current = current.getNext();
			}
			return l;
		}
	}

	/**
	 * prints the contents of the list
	 */
	@Override
	public void print() {

		// TODO Auto-generated method stub
		if (!empty()) {
			LNode<E> current = front;
			while (current.getNext() != null) {
				System.out.println(current.getData());
				current = current.getNext();
			}
			System.out.println(current.getData());
		}
	}

	/**
	 * creates a string with the contents of the list
	 * 
	 * @return the string representation of the list
	 */
	public String toString() {

		if (front == null) {
			return "";
		}
		String toReturn = "";
		LNode<E> current = front;
		while (current.getNext() != null) {

			toReturn += " " + current.getData().toString() + ",";
			current = current.getNext();
		}
		toReturn += " " + current.getData().toString();
		return toReturn;
	}

}
