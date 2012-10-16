/*
 * BuildGraph.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */

import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

/**
 * BuildGraph it builds a graph
 * 
 * @author rcd1575: Becca Dudley
 */

public class BuildGraph {

	public static void main(String args[]) {

		Scanner sc = new Scanner(System.in);
		Boolean brian = true;
		String current = "";
		ArrayDiGraph adg = new ArrayDiGraph();
		while (brian) {

			current = sc.next();
			if (current.length() == 1) {
				adg.addVertex(current, current);
			} else if (current.length() == 3) {
				try {
					adg.addEdge(current.charAt(0), current.charAt(1), current
							.charAt(2));
				} catch (NoSuchVertexException e) {
					System.err.println(e.getMessage());
				}

			} else{
				brian = false;
			}
		}
		Collection c = adg.vertexKeys();
		Iterator itr = c.iterator();
		while (itr.hasNext()) {
			Object key = itr.next();
			try {
				System.out.println(adg.getVertexData(key) + " (in degree == "
						+ adg.inDegree(key) + ", out degree == "
						+ adg.outDegree(key) + ")");
			} catch (NoSuchVertexException e1) {
				// TODO Auto-generated catch block
			}
			Iterator inIt;
			try {
				inIt = adg.neighborKeys(key).iterator();
			} catch (NoSuchVertexException e) {
				// TODO Auto-generated catch block
				break;
			}
			while (inIt.hasNext()) {
				Object key2 = inIt.next();
				try {
					System.out.println("to " + adg.getVertexData(key2)
							+ " cost " + adg.getEdgeData(key, key2));
				} catch (NoSuchVertexException e) {
					// TODO Auto-generated catch block
				}
			}
		}
	}

} // BuildGraph
