package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import player.CodeBreaker_AI;
import player.Difficulty;
import player.PlayerProxy;
import GUI.GameMenu;

public class DifficultyListener implements ActionListener{

	private GameMenu gm;
	private int index;
	private PlayerProxy players;
	
	public DifficultyListener(GameMenu gm, int index, PlayerProxy players){
		
		this.gm = gm;
		this.index = index;
		this.players = players;
	}
	
	@Override
	public void actionPerformed(ActionEvent itemPressed) {
		// TODO Auto-generated method stub
		CodeBreaker_AI breaker = (CodeBreaker_AI)players.getBreaker();
		breaker.set_AI(Difficulty.values()[index]);
		gm.setDifficulty(index);
	}

}
