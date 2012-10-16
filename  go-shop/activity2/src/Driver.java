
public class Driver {
	
	public static void main(String args[]){
		
		TS_Stack stack = new TS_Stack();
		Pusher push1 = new Pusher("push1", stack);
		Pusher push2 = new Pusher("push2", stack);
		Popper pop1 = new Popper("pop1", stack);
		Popper pop2 = new Popper("pop2", stack);
		pop1.start();
		pop2.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		push1.start();
		push2.start();
		try {
			push1.join();
			push2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}

}
