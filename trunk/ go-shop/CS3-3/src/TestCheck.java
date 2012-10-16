import java.io.FileNotFoundException;
import java.io.IOException;


public class TestCheck {

	public static void main(String args[]){
		
		Dictionary d;
		try {
			d = new Dictionary("words.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("boo");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("boo");
			return;
		}
		
		if(d.lookUp("dog") != Searchers.NOT_FOUND){
			System.out.println("yay");
		}
		
	}
	
}
