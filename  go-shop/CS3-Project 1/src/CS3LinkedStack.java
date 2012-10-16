/*
 * CS3LinkedStack.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */

/**
 * a generic linked stack for use in calculator project
 *
 * @author rcd1575: Rebecca Dudley
 */

public class CS3LinkedStack<E> implements CS3Stack<E>{

	private StackNode<E> front;//the top node
	private int size;//the size of the stack
	
	public CS3LinkedStack(){
		
		front = null;
		size = 0;
		
	}
	
	/**
	 * The class for the nodes used in CS3LinkedStack.java
	 */
	public class StackNode<E>{
		
		private StackNode<E> next;//The node after this one
		private E value;//the value of this node
		
		/**
		 * constructor, assigns the nodes value to the parameter and sets next to null
		 * 
		 * @param value   the value of this node
		 */
		public StackNode(E value){
			this.value = value;
			next = null;
		}
		
		/**
		 * Sets this nodes next to other
		 * 
		 * @param other   the other node
		 */
		public void setNext(StackNode<E> other){	
			next = other;
		}
		
		/**
		 * returns the next node
		 * 
		 * @return    the next node
		 */
		public StackNode<E> getNext(){
			return next;
		}
		
		/**
		 * returns the value of this node
		 * 
		 * @return   this nodes value
 		 */
		public E getValue(){
			return value;
		}
		
		/**
		 * returns true if this node has a next, false otherwise
		 * 
		 * @return   if this node has a next
		 */
		public boolean hasNext(){
			return next != null;
		}
		
	}
	
	/**
	 * clears the stack
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
		StackNode<E> current = front;
		while(current.hasNext()){	
			try{
				this.pop();
			}catch(CS3StackEmptyException see){
			}
			current = current.getNext();
		}
		size = 0;
	}

	/**
	 * tells if the stack is empty
	 * 
	 * @return   true if the stack is empty
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	/**
	 * gives the value of the first item in the stack
	 * 
	 * @return the first item of the stack
	 */
	@Override
	public E peek() throws CS3StackEmptyException {
		// TODO Auto-generated method stub
		
			if(this.isEmpty()){
				throw new CS3StackEmptyException("Stack is empty");
			}else{
				return front.getValue();
			}
	}

	/**
	 * removes the first item of the stack, returns its value
	 * 
	 * @return   the value of the first item of the stack
	 */
	@Override
	public E pop() throws CS3StackEmptyException {
		if(this.isEmpty()){
			throw new CS3StackEmptyException("Stack is empty");
		}else{
			StackNode<E> temp = front;
			front = front.getNext();
			size--;
			return temp.getValue();
		}
	}

	/**
	 * adds an item to the front of the stack
	 * 
	 * @param    the item to be added
	 */
	@Override
	public void push(Object element) {
		// TODO Auto-generated method stub
		
		StackNode<E> toAdd = new StackNode<E>((E)element);
		toAdd.setNext(front);
		front = toAdd;
		size++;
	}

	/**
	 * gives the size of the stack
	 * 
	 * @return  the stacks size
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	

} // CS3LinkedStack
