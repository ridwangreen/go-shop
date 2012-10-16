/*
 * HeapSort.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */
import java.util.ArrayList;
import java.util.Collections;

/**
 * using max heap to sort Hi bill
 * 
 * @author rcd1575: Rebecca Dudley
 */

public class HeapSort {

	private static int last;

	/**
	 * main method
	 * 
	 * @param args
	 *            command line arguments
	 */

	public static void main(String args[]) {

		ArrayList<Integer> nums = new ArrayList<Integer>();
		nums.add(1);
		nums.add(6);
		nums.add(2);
		nums.add(9);
		nums.add(8);
		nums.add(4);
		last = nums.size()-1;
		for(int i = 0; i < 6; i++){
			System.out.println(nums.get(i));
		}
		System.out.println("__");
		sort(nums);
		for(int i = 0; i < 6; i++){
			System.out.println(nums.get(i));
		}
		
	}

	public static <E extends Comparable<E>> void sort(ArrayList<E> data) {

		if(data.size() <= 1);
		if (last > 0) {
			heapify(data);
			Collections.swap(data, 0, last);
			last--;
			sort(data);
		}else{
			if(data.get(0).compareTo(data.get(1)) > 0){
				Collections.swap(data, 0, 1);
			}
		}
		

	}

	public static <E extends Comparable<E>> void heapify(ArrayList<E> data) {

		boolean isHeap = true;

		for (int i = 0; (i * 2) + 2 <= last; i += 3) {
			int leftI = (i * 2) + 1;
			int rightI = (i * 2) + 2;
			E left = data.get(leftI);
			if (rightI != last) {
				E right = data.get(rightI);
				if (left.compareTo(data.get(i)) > 0
						|| right.compareTo(data.get(i)) > 0) {
					isHeap = false;
					siftDown(data, i, last);
				}
			} else {
				if (left.compareTo(data.get(i)) > 0) {
					isHeap = false;
					siftDown(data, i, last);
				}
			}

		}
		if (!isHeap) {
			heapify(data);
		}
	}

	private static <E extends Comparable<E>> void siftDown(ArrayList<E> data,
			int start, int end) {
	
		if (start != end && start*2+1 <end) {
			int leftI = (start * 2) + 1;
			int rightI = (start * 2) + 2;
			if (rightI != end) {
				if (data.get(leftI).compareTo(data.get(rightI)) > 0) {

					Collections.swap(data, start, leftI);
					siftDown(data, leftI, end);
				} else {
					Collections.swap(data, start, rightI);
					siftDown(data, rightI, end);
				}

			} else {
				Collections.swap(data, start, leftI);
				siftDown(data, leftI, end);
			}
		}

	}

} // HeapSort
