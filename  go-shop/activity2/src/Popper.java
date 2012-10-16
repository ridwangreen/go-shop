
public class Popper extends Thread{
	
	private IStack stack;
	
	/**
	 * Initializes a new popper
	 * 
	 * @param name
	 * @param stack
	 */
	public Popper(String name, IStack stack){
		super(name);
		this.stack = stack;
	}
	
	/**
	 * continuously pops strings from the stack
	 */
	public void run(){
		while(true){
			stack.pop();
			Thread.yield();
		}
	}
	
}
