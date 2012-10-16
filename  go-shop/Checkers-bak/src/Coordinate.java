/**
 * Upon creation turns an integer location of a piece into
 * x and y coordinates.
 * 
 * @author sam
 *
 */
public class Coordinate {
	private int x;
	private int y;
	
	public Coordinate(int origCoord){
		this.x = origCoord % 8;
		this.y = origCoord / 8;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
