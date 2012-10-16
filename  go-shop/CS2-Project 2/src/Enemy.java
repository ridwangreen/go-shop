import java.awt.Color;

/*
 * Enemy.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */

/**
 * Enemy peice for the frenzy game
 * 
 * @author Rebecca Dudley
 */

public class Enemy extends FrenzyPiece implements Runnable {

	public FrenzyModel model = null;
	public String name;
	private int direction;
	public boolean alive;

	/**
	 * creates a new player at position x,y
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @param boardWidth
	 *            the size of the board
	 */
	public Enemy(int x, int y, int boardWidth, String name, FrenzyModel model) {

		super(x, y, boardWidth);
		this.name = name;
		this.model = model;
		model.getPieces()[x][y] = this;
		direction = (int) (Math.random() * 4);
		alive = true;

	}

	public void move() {

		model.getPieces()[x][y] = new BlankPiece(x, y, boardWidth);
		switch (direction) {
		case (0):
			if (x >= boardWidth - 1) {
				x = 0;
			} else {
				x++;
			}
			if (y <= 0) {
				y = boardWidth - 1;
			} else {
				y--;
			}
			break;
		case (1):
			if (x <= 0) {
				x = boardWidth - 1;
			} else {
				x--;
			}
			if (y <= 0) {
				y = boardWidth - 1;
			} else {
				y--;
			}
			break;
		case (2):
			if (x <= 0) {
				x = boardWidth - 1;
			} else {
				x--;
			}
			if (y >= boardWidth - 1) {
				y = 0;
			} else {
				y++;
			}
			break;
		case (3):
			if (x >= boardWidth - 1) {
				x = 0;
			} else {
				x++;
			}
			if (y >= boardWidth - 1) {
				y = 0;
			} else {
				y++;
			}
			break;
		}
		
		if(this.eat(model.getPieces()[this.getX()][this.getY()])){
			model.getPieces()[this.getX()][this.getY()].die();
		}
		model.getPieces()[x][y] = this;

	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return Color.GREEN;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public void die(){
		
		alive = false;
	}

	public void run() {

		try {

			Thread.sleep(model.getSpeed());

			while (alive) {
				if (!model.paused()) {
					this.move();
				}

				Thread.sleep(model.getSpeed());

			}
		} catch (Exception i) {
			i.printStackTrace();
		}
		

	}

}
