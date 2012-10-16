/*
 * DynamicTree.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */

/**
 * DynamicTree program for project2 sorts generics
 * 
 * @author rcd1575: Rebecca Dudley
 */
public class DynamicTree<E extends Comparable<E>> implements CS3Tree<E> {

	protected class DNode<E> {

		private E data;
		private DNode<E> left;
		private DNode<E> right;

		/**
		 * creates a new node that holds a specified data and sets the left and
		 * right children to null
		 * 
		 * @param data
		 *            the data of this node
		 */
		public DNode(E data) {

			this.data = data;
			left = null;
			right = null;
		}

		/**
		 * creates a new node with specified data, left child and right child
		 * 
		 * @param data
		 *            the data of this node
		 * @param left
		 *            the left child
		 * @param right
		 *            the right child
		 */
		public DNode(E data, DNode<E> left, DNode<E> right) {

			this.data = data;
			this.left = left;
			this.right = right;
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
		 * accessor for the right child
		 * 
		 * @return the right child
		 */
		public DNode<E> getRight() {

			return right;
		}

		/**
		 * accessor for the left child
		 * 
		 * @return the left child
		 */
		public DNode<E> getLeft() {

			return left;
		}

		/**
		 * Mutator for the data
		 * 
		 * @param newData
		 *            the new data element
		 */
		public void setData(E newData) {

			data = newData;
		}

		/**
		 * Mutator for the left child
		 * 
		 * @param newLeft
		 *            the new left child
		 */
		public void setLeft(DNode<E> newLeft) {

			left = newLeft;
		}

		/**
		 * Mutator for the right child
		 * 
		 * @param newRight
		 *            the new right child
		 */
		public void setRight(DNode<E> newRight) {

			right = newRight;
		}

		/**
		 * Return a string representation of the data element
		 * 
		 * @return the data element in String form
		 */
		public String toString() {

			return data.toString();
		}

	}

	protected DNode<E> root;

	/**
	 * creates a new tree and sets root to null
	 */
	public DynamicTree() {

		root = null;
	}

	/**
	 * adds a new node to the tree using specified data
	 * 
	 * @param data
	 *            the data for the new node
	 */
	@Override
	public boolean add(E data) {
		// TODO Auto-generated method stub
		DNode<E> newNode = new DNode<E>(data);
		if (root == null) {
			root = newNode;
			return true;
		} else {

			return add(root, newNode);
		}

	}

	/**
	 * adds a new node, if its less then the current node it adds to the left
	 * more than it adds to the right
	 * 
	 * @param node
	 *            the current node
	 * @param newNode
	 *            the node to be added
	 * @return true if the node is added
	 */
	private boolean add(DNode<E> node, DNode<E> newNode) {

		int result = newNode.getData().compareTo(node.getData());
		if (result < 0) {
			if (node.getLeft() == null) {
				node.setLeft(newNode);
				return true;
			} else {
				return add(node.getLeft(), newNode);
			}
		} else if (result > 0) {
			if (node.getRight() == null) {
				node.setRight(newNode);
				return true;
			} else {
				return add(node.getRight(), newNode);
			}
		}

		return false;

	}

	/**
	 * returns the data if a node is found containing the data
	 * 
	 * @param data the data to be found  null if not found
	 */
	@Override
	public E get(E data) {
		// TODO Auto-generated method stub

		DNode<E> newNode = new DNode<E>(data);

		return get(newNode, root);
	}

	/**
	 * returns the data if the node is equal to the current node
	 * 
	 * @param newNode   the node to be found
	 * @param node    the current node
	 * @return    the data of the node   null if not found
	 */
	private E get(DNode<E> newNode, DNode<E> node) {

		if (node == null) {
			return null;
		}
		if (node.getData().compareTo(newNode.getData()) == 0) {
			return node.getData();
		}
		if (newNode.getData().compareTo(node.getData()) < 0) {
			return get(newNode, node.getLeft());
		}
		return get(newNode, node.getRight());
	}

	/**
	 * prints the contents of the tree
	 */
	@Override
	public void print() {
		// TODO Auto-generated method stub

		print(root);

	}

	/**
	 * prints the contents of the tree starting at a specified node
	 * 
	 * @param node   the starting node
	 */
	private void print(DNode<E> node) {
		if (node == null) {
			return;
		}

		print(node.getLeft());
		System.out.println(node.toString());
		print(node.getRight());
	}

	/**
	 * finds a node with the specified data then returns the data
	 * 
	 * @param data    the data to be found
	 * @return     the data when found   null if not found
	 */
	public E find(E data) {
		return find(data, root);
	}

	/**
	 * finds a node with specified data then returns the data when found
	 * starting at a specified node
	 * 
	 * @param data    the data to be found
	 * @param node    the starting node
	 * @return  the data when found  null if its not found
	 */
	private E find(E data, DNode<E> node) {

		if (node == null) {
			return null;
		}
		int found = node.getData().compareTo(data);
		if (found == 0) {
			return node.getData();
		}
		if (node.getLeft() == null && node.getRight() == null && found != 0) {
			return null;
		}
		if (found > 0) {
			return find(data, node.getLeft());
		}
		return find(data, node.getRight());

	}

}
