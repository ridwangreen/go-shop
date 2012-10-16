import java.util.ArrayList;


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
     * clipPolygon
     * 
     * Clip the polygon with vertex count in and vertices inx/iny
     * against the rectangular clipping region specified by lower-left corner
     * (x0,y0) and upper-right corner (x1,y1). The resulting vertices are
     * placed in outx/outy.  
     * 
     * The routine should return the with the vertex count of polygon
     * resultinhg from the clipping.
     *
     * @param in the number of vertices in the polygon to be clipped
     * @param inx - x coords of vertices of polygon to be clipped.
     * @param int - y coords of vertices of polygon to be clipped.
     * @param outx - x coords of vertices of polygon resulting after clipping.
     * @param outy - y coords of vertices of polygon resulting after clipping.
     * @param x0 - x coord of lower left of clipping rectangle.
     * @param y0 - y coord of lower left of clipping rectangle.
     * @param x1 - x coord of upper right of clipping rectangle.
     * @param y1 - y coord of upper right of clipping rectangle.
     *
     * @return number of vertices in the polygon resulting after clipping
     * 
     */
    int clipPolygon(int in, float inx[], float iny[], float outx[], 
                    float outy[], float x0, float y0, float x1, float y1)
    {
        // YOUR IMPLEMENTATION GOES HERE

    	ArrayList<float[]> vertices = new ArrayList<float[]>();
    	for(int i = 0; i < in; i++){
    		float[] toAdd = {inx[i],iny[i]};
    		vertices.add(toAdd);
    	}
    	float[] clipA = {x0,y0};
    	float[] clipB = {x0,y1};
    	float[] clipC = {x1,y1};
    	float[] clipD = {x1,y0};
    	
    	ArrayList<float[]> newList1 = new ArrayList<float[]>();
    	clipEdge(vertices,newList1,clipA,clipB);
    	
    	ArrayList<float[]> newList2 = new ArrayList<float[]>();
    	clipEdge(newList1,newList2,clipB,clipC);
    	
    	ArrayList<float[]> newList3 = new ArrayList<float[]>();
    	clipEdge(newList2,newList3,clipC,clipD);
    	
    	ArrayList<float[]> newList4 = new ArrayList<float[]>();
    	clipEdge(newList3,newList4,clipD,clipA);
    	
    	for(int i =0; i < newList4.size(); i++){
    		outx[i] = Math.round(newList4.get(i)[0]);
    		outy[i] = Math.round(newList4.get(i)[1]);
    	}
    	
        return newList4.size();  // should return number of verticies of poly after clip
    }
    
    float[] cIntersect(float[] s, float[] p, float[] clipA, float[] clipB){
    	
    	float dLine = p[0] - s[0];
    	float dClip = clipB[0] - clipA[0];
    	
    	if(dClip == 0){
    		float m2 = (p[1] - s[1])/dLine;
    		float b2 = s[1] - m2 * s[0];
    		float inty = m2* clipA[0] + b2;
    		return new float[]{clipA[0],inty};
    		
    	}else if(dLine ==0){
    		float m2 = (clipB[1] - clipA[1])/dClip;
    		float b2 = clipA[1] - m2 * clipA[0];
    		float inty = m2* s[0] + b2;
    		return new float[]{s[0],inty};
    		
    	}else{
    	
	    	float m1 = (clipB[1]-clipA[1])/dClip;
	    	float m2 = (p[1] - s[1])/dLine;
	    	float b1  = clipA[1] - m1 * clipA[0];
	    	float b2  = s[1] - m2 * s[0];
	    	float intx = (b2-b1)/(m1-m2);
	    	float inty = m2 * intx + b2;
	    	return new float[]{intx,inty};
    	}
    	
    }
    
    boolean cInside(float[] vertex, float[] clipA, float[] clipB){
    	boolean inside = false;
    	//right
    	if(clipA[1] > clipB[1]){
	    	if(vertex[0] <= clipA[0]){
	    		inside = true;
	    	}
    	//top
	    else if(clipB[0] > clipA[0]){
	    	}if(vertex[1] <= clipA[1]){
	    		inside = true;
	    	}
	    //bottom
    	}else if(clipA[0] > clipB[0]){
    		if(vertex[1] >= clipA[1]){
    			inside = true;
    		}
    	//left
    	}else{ 
    		if(vertex[0] >= clipA[0]){
    			inside = true;
    		}
    	}
    	return inside;
    }
    
    void clipEdge(ArrayList<float[]> in, ArrayList<float[]> out, float[] clipA, float[] clipB){
    	for(int i = 0; i < in.size(); i++){
    		float[] s = in.get(i);
    		float[] p;
    		if(i == in.size()-1){
    			p = in.get(0);
    		}else{
    			p = in.get(i+1);
    		}
    		if(cInside(p,clipA, clipB)){
    			if(cInside(s,clipA,clipB)){
    				out.add(p);
    				
    			}else{
    				float[] inter = cIntersect(s,p,clipA,clipB);
    				out.add(inter);
    				out.add(p);
    			}
    		}else if(cInside(s,clipA,clipB)){
    			float[] inter = cIntersect(s,p,clipA,clipB);
				out.add(inter);
    		}

    	}
    }
}
