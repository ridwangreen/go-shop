
/*
 * FrenzyListener.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * Tells when a FrenzyButton is clicked
 *
 * @author Rebecca Dudley
 */

public class FrenzyListener implements ActionListener{
	
	private BoardView gameView;
	
	public FrenzyListener(BoardView gameView){
		this.gameView = gameView;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		FrenzyButton fButton = (FrenzyButton)ae.getSource();
		gameView.model.spawnEnemy(fButton.getXVal(), fButton.getYVal());
		gameView.updateView();
	}
	
	
}
