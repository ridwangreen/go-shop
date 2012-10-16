package Controller;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import player.CodeBreaker_AI;
import player.PlayerProxy;

public class ResponseListener implements ChangeListener{

	PlayerProxy players;
	
	public ResponseListener(PlayerProxy players){
		
		this.players = players;
	}
	
	@Override
	public void stateChanged(ChangeEvent bar) {
		// TODO Auto-generated method stub
		
		JSlider response = (JSlider)bar.getSource();
		int value = response.getValue() * 1000;
		CodeBreaker_AI breaker = (CodeBreaker_AI)players.getBreaker();
		breaker.setResponseTime(value);
		
	}
}
