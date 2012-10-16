package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import player.PlayerProxy;

import system.GameState;
import Command.SetCode;

/*
 * @File SetCodeListener.java
 * 
 * @Authors Becca Dudley
 * 
 * @Class Description this is the actionlistener for the set code button
 */
public class SetCodeListener implements ActionListener{

	private PlayerProxy players;
	
	public SetCodeListener(PlayerProxy players){
		this.players = players;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		players.nextMakerTurn();
	}
}
