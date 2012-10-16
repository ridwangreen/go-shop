/*
 * BTNode.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */

/**
 * BTNode, a node for a binary tree
 * 
 * @author rcd1575: AUTHOR_FULL_NAME_HERE
 */

public class BTNode<T> {
	
	private T data;  //the real data stored inside the node
	private BTNode<T> left;  //the left child of this node
 	private BTNode<T> right;  //the right child of this node

	
	/**
	 * Constructor for a node with a data element
	 * 
	 * @param data   the data for the node
	 */
	public BTNode(T data) {
		
		this.data = data;
		left = null;
		right = null;	
	}
	
	
	/**
	 * Constryctor for building a node where the data element, and the
	 * left and right nodes are all specified
	 * 
	 * @param data   the data for the node
	 * @param left   the left child for the node
	 * @param right   the right child for the node
	 */
	public BTNode(T data, BTNode<T> left, BTNode<T> right){
		
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	
	/**
	 * Accessor for the data element
	 * 
	 * @return    the data
	 */
	public T getData(){
		
		return data;
	}
	
	
	/**
	 * Accessor for the left child
	 * 
	 * @return    the left child
	 */
	public BTNode<T> getLeft(){
	
		return left;
	}
	
	
	/**
	 * Accessor for the right child
	 * 
	 * @return   the right child
	 */
	public BTNode<T> getRight(){
		
		return right;
	}
	
	
	/**
	 * Mutator for the data
	 * 
	 * @param newData    the new data element
	 */
	public void setData(T newData){
		
		data = newData;
	}
	
	
	/**
	 * Mutator for the left child
	 * 
	 * @param newLeft    the new left child
	 */
	public void setLeft(BTNode<T> newLeft){
		
		left = newLeft;
	}
	
	
	/**
	 * Mutator for the right child
	 * 
	 * @param newRight    the new right child
	 */
	public void setRight(BTNode<T> newRight){
		
		right = newRight;
	}
	
	
	/**
	 * Return a string representation of the data element
	 * 
	 * @return  the data element in String form
	 */
	public String toString(){
		
		String toReturn = "";
		toReturn += data;
		return toReturn;
	}
	
} // BTNode
