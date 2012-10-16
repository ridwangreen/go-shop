package GUI;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import player.PlayerProxy;
import Controller.DifficultyListener;
import Controller.LogListener;
import Controller.NewGameListener;

public class GameMenu extends JMenuBar{
	
	private JMenuItem menuItems[];
	private JMenuItem difficulty[];
	private JMenu AIDifficulty;
	
	public GameMenu(PlayerProxy players){
		
		menuItems = new JMenuItem[4];
		difficulty = new JMenuItem[3];
		
		JMenu file = new JMenu("File");
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener(new NewGameListener());
		menuItems[0] = newGame;
 		file.add(newGame);
		add(file);
		
		AIDifficulty = new JMenu("AI Difficulty");
		JMenuItem easy = new JMenuItem("Easy");
		easy.addActionListener(new DifficultyListener(this, 0, players));
		difficulty[0] = easy;
		JMenuItem medium = new JMenuItem("Medium");
		medium.addActionListener(new DifficultyListener(this, 1, players));
		difficulty[1] = medium;	
		JMenuItem hard = new JMenuItem("Hard");
		hard.addActionListener(new DifficultyListener(this, 2, players));
		difficulty[2] = hard;
		AIDifficulty.add(easy);
		AIDifficulty.add(medium);
		AIDifficulty.add(hard);
		add(AIDifficulty);
		
		JMenu logging = new JMenu("Logging");
		JMenuItem on = new JMenuItem("Turn Logging On");
		JMenuItem off = new JMenuItem("Turn Logging off");
		on.addActionListener(new LogListener(this, 1));
		off.addActionListener(new LogListener(this, 1));
		menuItems[1] = on;
		menuItems[2] = off;
		logging.add(on);
		logging.add(off);
		add(logging);
		
		setVisible(true);
	}
	
	public void setEnabledItem(int item, boolean isEnabled){
		
		menuItems[item].setEnabled(isEnabled);
	}
	
	public void setEnabledAI(boolean isEnabled){
		
		AIDifficulty.setEnabled(isEnabled);
	}
	
	public void setDifficulty(int index){
		
		for(int i = 0; i < 3; i++){
			if(i != index){
				difficulty[i].setEnabled(true);
			}
		}
		difficulty[index].setEnabled(false);
	}
}
