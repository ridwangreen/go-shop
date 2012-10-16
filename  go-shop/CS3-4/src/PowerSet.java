/*
 * PowerSet.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */
import java.util.HashSet;
import java.util.Set;

/**
 * Uses recursion to calculate power sets!
 * 
 * @author rcd1575: Becca Dudley
 */

public class PowerSet {

	public static void main(String args[]) {

		Set<String> pSet = new HashSet<String>();
		String set[] = new String[args.length];
		for (int i = 0; i < args.length; i++) {
			set[i] = args[i];
		}
		toPSet(set, pSet);
		System.out.print("{");
		for (String s : pSet) {
			System.out.print(" " + s + ",");
		}
		System.out.println("}");
	}

	public static Set<String> toPSet(String[] set, Set<String> pSet) {

		if (set.length == 0) {
			if (!pSet.contains("\"\"")) {
				pSet.add("\"\"");
			}
			return pSet;
		} else if (set.length == 1) {
			if (!pSet.contains(set[0])) {
				pSet.add(set[0]);
			}
			if (!pSet.contains("\"\"")) {
				pSet.add("\"\"");
			}
			return pSet;
		}
		String sSet = "";
		for (int i = 0; i < set.length; i++) {
			sSet += (set[i]);
		}
		if (!pSet.contains(sSet)) {
			pSet.add(sSet);
		}
		for (int c = 0; c < set.length; c++) {
			String a[] = { set[c] };
			if (!pSet.contains(a[0])) {
				pSet.add(a[0]);
			}
			String[] nArr = newArray(set, c);
			if (pSet.add(nArr[0])) {
				pSet.add(nArr[0]);
			}
			toPSet(nArr, pSet);
		}
		return pSet;
	}

	public static String[] newArray(String[] oldArr, int index) {

		String newArr[] = new String[oldArr.length - 1];
		int i = 0;
		for (int x = 0; x < oldArr.length; x++) {

			if (x != index) {
				
				newArr[i] = oldArr[x];
				i++;
			}
		}
		return newArr;
	}
	
	
}
