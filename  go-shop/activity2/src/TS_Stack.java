import java.util.ArrayList;

/*
 * Implements the IStack interface
 * uses ArrayList.  Use proper synchronization
 * 
 * Becca Dudley
 */
public class TS_Stack implements IStack {

	private ArrayList<String> stack;
	
	public TS_Stack(){
		stack = new ArrayList<String>();
	}

	@Override
	public synchronized void push(String element) {
		// TODO Auto-generated method stub

		String name = Thread.currentThread().getName();
		while(stack.size() >= 5){
			System.out.println("Thread " + name + " is waiting");
			try{
				wait();
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}
		System.out.println("Thread " + name + " is pushing");
		notifyAll();
		stack.add(element);
	}

	@Override
	public synchronized String pop() {
		// TODO Auto-generated method stub

		String name = Thread.currentThread().getName();
		while (stack.isEmpty()) {
			System.out.println("Thread " + name + " is waiting");
			try {
				wait();
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
		System.out.println("Thread " + name + " is popping");
		notifyAll();
		return stack.remove(stack.size() - 1);

	}

}
