
public class TestSorts {

	public static void main(String args[]){
		CountSortableIntArray cs = new CountSortableIntArray(5);
		cs.fill(8);
		cs.print();
		System.out.println("________");
		cs.sort();
		cs.print();
	}
	
}
