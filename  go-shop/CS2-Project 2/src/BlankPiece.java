import java.awt.Color;


public class BlankPiece extends FrenzyPiece{
	
	public BlankPiece(int x, int y, int boardLength){
		
		super(x,y,boardLength);
		
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return "";
	}
	
	public void die(){
		
	}
	

}
