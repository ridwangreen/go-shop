public class Node<T> {
	/**
	 * The node's data.
	 */
	private T data;

	/**
	 * A reference to the next node.
	 */
	private Node<T> next;

	/**
	 * Accessor for node's data.
	 * 
	 * @return Node's data.
	 */
	public T getData() {
		return data;
	}

	/**
	 * Mutator for the node's data.
	 * 
	 * @param val The new data for the node.
	 */
	public void setData(T val) {
		data = val;
	}

	// ccessor for next nod

	/**
	 * Accessor for the next node
	 * 
	 * @return The next node.
	 */
	public Node<T> getNext() {
		return next;
	}

	/**
	 * Mutator for the next node.
	 * 
	 * @param val The new next node.
	 */
	public void setNext(Node<T> val) {
		next = val;
	}

	/**
	 * Constructs a node with a data element, and null for the next node.
	 * 
	 * @param data The data element.
	 */
	public Node(T data) {
		this(data, null);
	}

	/**
	 * 
	 * Constructs a node with both data and next element defined.
	 * 
	 * @param data The data element.
	 * @param next The next node.
	 */
	public Node(T data, Node<T> next) {
		this.data = data;
		this.next = next;
	}
}
