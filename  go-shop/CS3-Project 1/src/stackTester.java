
public class stackTester {

	public static void main(String args[]){
		
		CS3LinkedStack ls = new CS3LinkedStack();
		ls.push(1);
		ls.push(2);
		ls.push(3);
		ls.push(4);
		ls.push(5);
		try {
			System.out.print(ls.peek());
		} catch (CS3StackEmptyException e) {
		}
		System.out.println(" ls size :" + ls.size());
		for(int x = 0; x < 5; x++){
			try {
				System.out.print(ls.pop());
			} catch (CS3StackEmptyException e) {
			}
			System.out.println(" ls size: " + ls.size());
		}
		System.out.println("is Empty: " + ls.isEmpty());
		ls.push(1);
		ls.push(2);
		ls.push(3);
		System.out.println("is Empty: " + ls.isEmpty());
		ls.clear();
		System.out.println("is Empty: " + ls.isEmpty());
		
	}
}
