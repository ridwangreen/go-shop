/*
 * WordList.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */

/**
 * WordIist program for project2 holds words specifically
 * 
 * @author rcd1575: Rebecca Dudley
 */
public class WordList extends DynamicList<CRObject>{

	/**
	 * creates a new list
	 */
	public WordList(){
		
		super();
	}
	
	/**
	 * prints the contents of the list
	 */
	public void print(){
		
		print(front);
	}
	
	/**
	 * prints the contents of the list
	 * 
	 * @param node  the current node
	 */
	private void print(LNode<CRObject> node){
		
		if (!empty()) {
			LNode<CRObject> current = front;
			while (current.getNext() != null) {
				System.out.print(current.getData().getNumLines() + " ");
				System.out.println(current.getData().getWord());
				current = current.getNext();
			}
			System.out.print(current.getData().getNumLines() + " ");
			System.out.println(current.getData().getWord());
		}
	}
	
}
