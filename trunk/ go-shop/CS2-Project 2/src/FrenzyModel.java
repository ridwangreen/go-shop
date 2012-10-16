

  
/*
 * FrenzyModle.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */
import java.util.Random;

/**
 * Hold all game data
 *
 * @author Rebecca Dudley
 */
public class FrenzyModel {

	private FrenzyPiece[][] pieces;
	private Player p;
	private int eSpeed;
	private boolean isPaused;
	private BoardView view;
	private boolean resetEnabled;
	
	/**
	 * The model componant of the model view controller pattern
	 * 
	 * @param length
	 */
	public FrenzyModel(int length){
		
		eSpeed = 800;
		isPaused = false;
		resetEnabled = false;
		pieces = new FrenzyPiece[length][length];
		for(int r = 0; r < pieces.length; r++){
			for(int c =0 ; c < pieces[r].length; c++){
				pieces[r][c] = new BlankPiece(r,c,length);
			}
		}
		p = new Player(length/2, length/2, length, this);
		pieces[p.getY()][p.getX()] = p;
		new Thread(){
			public void run(){
				while(true){
					try{
						this.sleep(6000);
					}catch(Exception i){
						i.printStackTrace();
					}
					if(!isPaused){
						Random rand = new Random();
						int xVal = rand.nextInt(pieces.length);
						int yVal = rand.nextInt(pieces.length);
						spawnEnemy(xVal, yVal);
					}
				}
			}
		}.start();
		
	}
	
	public void setView(BoardView bView){
		view = bView;
	}
	
	public BoardView getView(){
		return view;
	}
	
	public void playerKilled(){
		
	}
	
	public FrenzyPiece[][] getPieces(){
		
		return pieces;
		
	}
	
	public int getSpeed(){
		return eSpeed;
	}
	
	public void setSpeed(int newSpeed){
		if(newSpeed > 0){
			eSpeed = newSpeed;
		}
	}
	
	public void movePlayer(int direction){
		
		int x = p.getX();
		int y = p.getY();
		pieces[y][x] = new BlankPiece(x,y,pieces.length);
		switch(direction){
		
		case 1:
			p.moveUp();
			break;
		case 2:
			p.moveDown();
			break;
		case 3:
			p.moveRight();
			break;
		case 4:
			p.moveLeft();
			break;
		
		}
		
		if(p.eat(pieces[p.getY()][p.getX()])){
			pieces[p.getY()][p.getX()].die();
			view.update("P ate " + pieces[p.getY()][p.getX()].getText());
			view.addConsumed();
		}
		pieces[p.getY()][p.getX()] = p;
		
	}
	
	public boolean paused(){
		return isPaused;
	}
	
	public synchronized void setPaused(boolean paused){
		
		isPaused = paused;
	}
	
	public void spawnEnemy(int x, int y){
		
		Random r = new Random();
		String name = "";
		name += (char)(r.nextInt(94) + 33);
		Enemy e = new Enemy(x, y, this.getPieces().length, name, this);
		new Thread(e).start();
		
	}
	
	public void setResetEnabled(){
		resetEnabled = true;
	}
	
	public synchronized void reset(){
		
		if(resetEnabled){
			
			isPaused = false;
			p = new Player(pieces.length/2, pieces.length/2, pieces.length, this);
			pieces[p.getX()][p.getY()] = p;
			view.updateView();
			view.updateGameStatus("Game Running!");
			view.resetConsumed();
			
		}resetEnabled = false;
		
	}

}
