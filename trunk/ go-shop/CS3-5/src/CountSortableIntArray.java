/*
 * CountSortableIntArray.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * Counting sort
 *
 * @author     Rebecca dudley
 */

public class CountSortableIntArray extends SortableIntArray{

	public CountSortableIntArray(int size) {
		super(size);
		// TODO Auto-generated constructor stub
		
	}
	
	public void sort(){
		
		int vals[] = new int[getRangeSize()];
		for(int i = 0; i < vals.length; i++){
			int count = 0;
			for(int o = 0; o < getSize(); o++){
				if(getElementAt(o) == i){
					count++;
				}
			}
			vals[i] = count;
		}
		int index = 0;
		for(int c = 0; c < vals.length; c++){
			for(int r = 0; r < vals[c]; r++){

				setElementAt(index, c);
				index++;
			}
		}
		
	}

}
