
public class Pusher extends Thread{
	
	private IStack stack;
	
	/**
	 * Initialize a new pusher thread
	 * 
	 * @param name
	 * @param stack
	 */
	public Pusher(String name, IStack stack){
		
		super(name);
		this.stack = stack;
	}
	
	/**
	 * pushes 15 strings on the stack
	 * the string consists of the Pushers thread's name
	 * and a number from 1 to 15
	 */
	public void run(){
		
		for(int x = 1; x <= 15; x++){
			String s = Thread.currentThread().getName() + x;
			stack.push(s);
			Thread.yield();
		}
	}
}
