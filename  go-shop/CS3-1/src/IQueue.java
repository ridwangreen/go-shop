public interface IQueue<T> {
	/**
	 * Adds an element to the back of the queue.
	 * 
	 * @param data The element to be added.
	 */
	public void enqueue(T data);

	/**
	 * Removes the front element from the queue.
	 * 
	 * @return The front element
	 * @throws UnderflowException If the queue is empty when invoked.
	 */
	public T dequeue() throws UnderflowException;

	/**
	 * Indicates how many elements are in the queue.
	 * 
	 * @return The number of elements currently in the queue.
	 */
	public int size();

	/**
	 * Indicates if the queue is empty or not.
	 * 
	 * @return True if the queue is empty, false otherwise.
	 */
	public boolean empty();

	/**
	 * Indicates if the queue is full or not.
	 * 
	 * @return True if the queue is full, false otherwise.
	 */
	public boolean full();
}
