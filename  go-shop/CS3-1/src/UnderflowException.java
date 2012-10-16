public class UnderflowException extends Exception {
	// required for serialization
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs the exception.
	 * 
	 * @param msg The message associated with the exception.
	 */
	public UnderflowException(String msg) {
		super(msg);
	}
}
