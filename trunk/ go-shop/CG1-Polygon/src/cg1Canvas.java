import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//
//  cg1Canvas.java
//  
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

/**
 * This is a simple canvas class for adding functionality for the
 * 2D portion of Computer Graphics I.
 *
 */

public class cg1Canvas extends simpleCanvas {
    
    /**
     * Constructor
     *
     * @param w width of canvas
     * @param h height of canvas
     */
    cg1Canvas (int w, int h)
    {
        super (w, h);
    }
    
    /**
	 * Draw a line from vertex (x0,y0) to vertex (x1,y1)
     *
     * You are to add the implementation here using only calls
	 * to setPixel();
	 */
	public void drawLine (int x0, int y0, int x1, int y1)
	{
		int width = x1 - x0 ;
	    int hieght = y1 - y0 ;
	    int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0 ;
	    if (width < 0){
	    	dx1 = -1 ; 
	    }else if (width > 0){
	    	dx1 = 1 ;
	    }
	    if (hieght < 0){
	    	dy1 = -1 ;
	    }
	    else if (hieght > 0){
	    	dy1 = 1 ;
	    }
	    if (width < 0){
	    	dx2 = -1 ; 
	    }else if(width > 0){
	    	dx2 = 1 ;
	    }
	    int longest = Math.abs(width) ;
	    int shortest = Math.abs(hieght) ;
	    if (!(longest>shortest)) {
	        longest = Math.abs(hieght) ;
	        shortest = Math.abs(width) ;
	        if (hieght < 0){ 
	        	dy2 = -1 ; 
	        }else if (hieght > 0){
	        	dy2 = 1 ;
	        }
	       
	        dx2 = 0 ;            
	    }
	    int numerator = (int)(longest * .5);
	    for (int i = 0; i <= longest; i++) {
	        setPixel(x0, y0) ;
	        numerator += shortest ;
	        if (!(numerator < longest)) {
	            numerator -= longest ;
	            x0 += dx1 ;
	            y0 += dy1 ;
	        } else {
	            x0 += dx2 ;
	            y0 += dy2 ;
	        }
	    }
	}
    
    
    /**
     * Draw a filled polygon. The polygon has n distinct vertices. The 
     * coordinates of the vertices making up the polygon are stored in the 
     * x and y arrays.  The ith vertex will have coordinate  (x[i], y[i])
     *
     * You are to add the implementation here using only calls
	 * to setPixel()
     */
    void drawPolygon(int n, int x[], int y[])
    {
    	ArrayList edges = new ArrayList();
        for(int i = 0; i < n-1; i++){
        	drawLine(x[i], y[i], x[i+1], y[i+1]);
        	int line[][] = new int[2][2];
        	line[0][0] = x[i];
        	line[0][1] = y[i];
        	line[1][0] = x[i+1];
        	line[1][1] = y[i+1];
        	edges.add(line);
        }
        int line[][] = new int[2][2];
    	line[0][0] = x[n-1];
    	line[0][1] = y[n-1];
    	line[1][0] = x[0];
    	line[1][1] = y[0];
    	edges.add(line);
        drawLine(x[n-1], y[n-1], x[0], y[0]);

        int max = biggestY(edges);
        ArrayList edgeTable = new ArrayList();
        for(int i = 0; i < max; i++){
        	ArrayList lists = new ArrayList();
        	for(int c = 0; c < n; c++){
        		int[][] cur = (int[][])edges.get(c);
        		if(findYMin(cur) == i && cur[0][1] != cur[1][1]){
        			lists.add(cur);
        		}
        	}
        	if(lists.size() > 0){
        		edgeTable.add(lists);

        	}else{
        		edgeTable.add(null);
        	}
        }
        Map AETMap = new HashMap();
        for(int scanLine = 0; scanLine <= max; scanLine++){
        	for(int index = 0; index < edgeTable.size(); index++){
        		if(edgeTable.get(index) != null && index == scanLine){
        			addEdge(AETMap,(ArrayList)edgeTable.get(index));
        		}
        	}
        	Set<Map.Entry> AETSet = AETMap.entrySet();
        	ArrayList<Map.Entry<int[][],Integer>> AET = new ArrayList<Map.Entry<int[][],Integer>>();
        	int index = 0;
        	for(Map.Entry s: AETSet){
        		AET.add(s);
        		index++;
        	}
        	Collections.sort(AET,new Comparator<Map.Entry<int[][],Integer>>(){
        	       public int compare(Map.Entry<int[][],Integer> a, Map.Entry<int[][],Integer> b){
        	           return (a.getValue()).compareTo(b.getValue());
        	        }
        	   }
        	);
        	for(int c = 0; c < AET.size() - 1; c += 2){
        		Map.Entry e1 = AET.get(c);
        		Map.Entry e2 = AET.get(c+1);
        		int p1 = (Integer) e1.getValue();
        		int p2 = (Integer) e2.getValue();
        		drawLine(p1,scanLine,p2-1,scanLine);
        	}
        	ArrayList<Map.Entry<int[][],Integer>> newAET = new ArrayList<Map.Entry<int[][],Integer>>();
        	for(Map.Entry<int[][],Integer> a:AET){
        		if(findYMax(a.getKey()) >= scanLine){
        			newAET.add(a);
        		}
        	}
        	AET = newAET;
        	for(Map.Entry<int[][],Integer> a:AET){
        		int curVal = a.getValue();
        		curVal += getSlope(a.getKey());
        		a.setValue(curVal);
        	}
        	AETMap = new HashMap();
        	for(Map.Entry<int[][],Integer> a:AET){
        		AETMap.put(a.getKey(),a.getValue());
        	}
        }
    }
    
    public int xInit(int[][] edge){
    	int yInit = findYMin(edge);
    	if(edge[0][1] == yInit){
    		return edge[0][0];
    	}
    	return edge[1][0];
    }
    public int getSlope(int[][] edge){
    	return (edge[0][0]-edge[1][0])/(edge[0][1]-edge[1][1]);
    }
    public Map addEdge(Map AET, ArrayList listToAdd){
    	for(int i = 0; i < listToAdd.size(); i++){
    		AET.put(listToAdd.get(i), xInit((int[][])listToAdd.get(i)));
    	}
    	return AET;
    }
    
    public int findYMax(int[][] edge){
    	if(edge[0][1] > edge[1][1]){
    		return edge[0][1];
    	}
    	return edge[1][0];
    }
    public int findYMin(int[][] edge){
    	if(edge[0][1] < edge[1][1]){
    		return edge[0][1];
    	}
    	return edge[1][0];
    }
    
    public int biggestY(ArrayList edges){
    	int n = edges.size();
    	int max = ((int[][])(edges.get(0)))[0][1];
    	for(int i = 0; i < n; i++){
    		int cur = ((int[][])(edges.get(i)))[0][1];
    		if(cur > max){
    			max = cur;
    		}
        }
    	return max;
    }

}
