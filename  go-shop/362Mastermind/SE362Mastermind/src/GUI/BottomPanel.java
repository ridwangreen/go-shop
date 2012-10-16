package GUI;

import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import player.PlayerProxy;
import Controller.MakeGuessListener;
import Controller.ResponseListener;
import Controller.SetCodeListener;

/*
 * @File TopPanel.java
 * 
 * @Authors Becca Dudley
 * 
 * @Class Description the panel containing the player option buttons: 
 *  		makeguess, leavefeedback, undo and setcode
 */
public class BottomPanel extends JPanel {
	
	private JButton makeGuess;
	private JButton undo;
	private JButton setCode;
	private JSlider responseTime;
	private JButton topButtons[];//the array containing all the buttons

	/**
	 * Creates the play option buttons
	 */
	public BottomPanel(PlayerProxy players) {
		
		setLayout(new GridLayout(2,1));
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1,3));
		topButtons = new JButton[3];
		
		String makeGuessString = "<html>" + "Make" + "<br>" + "Guess" + "</html>";
		String undoString = "<html>" + "Undo" + "<br>" + "Move" + "</html>";
		String setCodeString = "<html>" + "Set" + "<br>" + "Code" + "</html>";
		
		makeGuess = new JButton(makeGuessString);
		makeGuess.addActionListener(new MakeGuessListener(players));
		makeGuess.setMargin(new Insets(1, 1, 1, 1) );
		makeGuess.setEnabled(false);
		topButtons[0] = makeGuess;
		
		undo = new JButton(undoString);
		undo.setMargin(new Insets(1, 1, 1, 1) );
		undo.setEnabled(false);
		topButtons[1] = undo;
		
		setCode = new JButton(setCodeString);
		setCode.addActionListener(new SetCodeListener(players));
		setCode.setMargin(new Insets(1, 1, 1, 1) );
		setCode.setEnabled(false);
		topButtons[2] = setCode;
		
		buttons.add(makeGuess);
		buttons.add(undo);
		buttons.add(setCode);
		
		JPanel AIPanel = new JPanel();
		AIPanel.setLayout(new GridLayout(2,1));
		responseTime = new JSlider();
		responseTime.setMaximum(30);
		responseTime.addChangeListener(new ResponseListener(players));
		responseTime.setEnabled(false);
		AIPanel.add(new JLabel("   Respose Time:"));
		AIPanel.add(responseTime);
		
		add(buttons);
		add(AIPanel);
		setVisible(true);
	}
	
	/**
	 * enabled/disables a button depending on who's turn it is
	 * 
	 * @param buttonNumber  the button to be enabled/disabled
	 * @param isEnabled  whether or not the button is enabled
	 */
	public void setOptionEnabled(int buttonNumber, boolean isEnabled){
		
		topButtons[buttonNumber].setEnabled(isEnabled);
	}
	
	/**
	 * sets the response time scroll bar as enabled/disabled
	 * 
	 * @param isEnabled
	 */
	public void setBarEnabled(boolean isEnabled){
		
		responseTime.setEnabled(isEnabled);
	}
	
	/**
	 * sets all the buttons back 
	 */
	public void reset(){
		
		makeGuess.setEnabled(false);
		undo.setEnabled(false);
		setCode.setEnabled(false);
		responseTime.setEnabled(false);
	}
}