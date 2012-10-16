/**
 * Upon creation turns an integer location of a piece into
 * r and c coordinates.
 * 
 * @author sam
 *
 */
public class Coordinate {
	private int r;
	private int c;
	
	public Coordinate(int origCoord){
		this.r = origCoord / 8;
		this.c = origCoord % 8;
	}
	
	public int getR(){
		return r;
	}
	
	public int getC(){
		return c;
	}
}
