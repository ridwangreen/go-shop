package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import player.PlayerProxy;

import system.GameState;
import system.Peg;
import Command.Guess;

/*
 * @File MakeGues.java
 * 
 * @Authors Becca Dudley
 * 
 * @Class Description this is the actionlistener for the make guess button
 */
public class MakeGuessListener implements ActionListener{

	private PlayerProxy players;
	
	public MakeGuessListener(PlayerProxy players){
		
		this.players = players;
	}
	
	/**
	 * when the button is clicked inform the playerProxy
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		players.nextBreakerTurn();
	}

}
