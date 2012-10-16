package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import system.GameState;

import Command.NewGame;

/*
 * @File NewGameListener.java
 * 
 * @Authors Becca Dudley
 * 
 * @Class Description this is the actionlistener for the new game button
 */
public class NewGameListener implements ActionListener{

	/**
	 * when the button is clicked inform the gamestate
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		
		GameState.getInstance().pushCommand(new NewGame());
	}
	
}