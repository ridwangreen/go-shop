//
//  Test.java
//  
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

import java.awt.*;

public class fillTest {
	
	public fillTest () {}	
    
	static public void main(String[] args){
		
		cg1Canvas T = new cg1Canvas(300, 300);
        
        T.setColor (0.0f, 0.0f, 0.0f);
		T.clear();
		T.setColor (1.0f, 1.0f, 1.0f);
        
        int x[] = new int[7];
		int y[] = new int[7];
        
        /* 
		 * Use Student's drawPolygon 
		 */
		x[0] = 10; y[0] = 10;
		x[1] = 20; y[1] = 10;
		x[2] = 20; y[2] = 20;
		x[3] = 10; y[3] = 20;
		
		/* 
		 * LL => Lower Left Start, CCW => Vertices entered in 
		 * counter clockwise progression 
		 */
		T.drawPolygon( 4, x, y );	/* Square, LL, CCW */
		
		x[0] = 40; y[0] = 30;
		x[1] = 40; y[1] = 50;
		x[2] = 30; y[2] = 50;
		x[3] = 30; y[3] = 30;
		
		T.drawPolygon( 4, x, y );	/* Rectangle, LR, CCW */
		
		x[0] = 40; y[0] = 90;
		x[1] = 40; y[1] = 70;
		x[2] = 10; y[2] = 70;
		x[3] = 10; y[3] = 90;
		
		T.drawPolygon( 4, x, y );	/* Rectangle, UR, CW */
		
		x[0] = 10; y[0] = 230;
		x[1] = 40; y[1] = 230;
		x[2] = 40; y[2] = 210;
		x[3] = 10; y[3] = 210;
		
		T.drawPolygon( 4, x, y );	/* Rectangle, UL, CW */
		
		x[0] = 100; y[0] = 10;
		x[1] = 150; y[1] = 10;
		x[2] = 125; y[2] = 20;
		
		T.drawPolygon( 3, x, y );	/* Isosceles, flat bottom */
		
		x[0] = 100; y[0] = 30;
		x[1] = 140; y[1] = 50;
		x[2] = 175; y[2] = 50;
		
		T.drawPolygon( 3, x, y );	/* flat top - tail to left */
		
		x[0] = 120; y[0] = 40;
		x[1] = 80;  y[1] = 60;
		x[2] = 45;  y[2] = 60;
		
		T.drawPolygon( 3, x, y );	/* flat top - tail to right */
		
		x[0] = 10; y[0] = 100;
		x[1] = 10; y[1] = 120;
		x[2] = 25; y[2] = 100;
		
		T.drawPolygon( 3, x, y );	/* Right */
		
		x[0] = 10; y[0] = 130;
		x[1] = 20; y[1] = 130;
		x[2] = 20; y[2] = 140;
		
		T.drawPolygon( 3, x, y );	/* Right */
		
		x[0] = 10; y[0] = 170;
		x[1] = 20; y[1] = 170;
		x[2] = 10; y[2] = 150;
		
		T.drawPolygon( 3, x, y );	/* Right */
		
		x[0] = 100; y[0] = 70;
		x[1] = 150; y[1] = 70;
		x[2] = 75;  y[2] = 90;
		
		T.drawPolygon( 3, x, y );	/* flat bottom - top left */
		
		x[0] = 100; y[0] = 100;
		x[1] = 150; y[1] = 100;
		x[2] = 195; y[2] = 120;
		
		T.drawPolygon( 3, x, y );	/* flat bottom - top right */
		
		x[0] = 100; y[0] = 170;
		x[1] = 150; y[1] = 150;
		x[2] = 175; y[2] = 130;
		
		T.drawPolygon( 3, x, y );	/* scalene */
		
		x[0] = 200; y[0] =  50;
		x[1] = 225; y[1] =  90;
		x[2] = 250; y[2] =  50;
		x[3] = 225; y[3] =  10;
		
		T.drawPolygon ( 4, x, y );     /* diamond */
		
		x[0] = 200; y[0] = 125;
		x[1] = 210; y[1] = 150;
		x[2] = 260; y[2] = 145;
		x[3] = 275; y[3] = 120;
		x[4] = 250; y[4] = 100;
		x[5] = 225; y[5] = 100;
		
		T.drawPolygon ( 6, x, y );     /* hexagon */
		
		x[0] = 215; y[0] = 225;
		x[1] = 200; y[1] = 250;
		x[2] = 215; y[2] = 250;
		x[3] = 225; y[3] = 275;
		x[4] = 235; y[4] = 250;
		x[5] = 250; y[5] = 250;
		x[6] = 235; y[6] = 225;
		
		T.drawPolygon ( 7, x, y );     /* star top */
        
        Frame f = new Frame( "polyfill Test" );
        f.add("Center", T);
		f.pack();
		f.setResizable (false);
        f.setVisible(true);
		
	}
        
}
