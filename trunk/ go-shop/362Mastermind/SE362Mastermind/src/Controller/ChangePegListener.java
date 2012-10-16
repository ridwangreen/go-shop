package Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import GUI.PegUI;
import system.PegColor;

/*
 * @File ChangePegListener.java
 * 
 * @Authors Becca Dudley
 * 
 * @Class Description action listener for changing peg color
 */
public class ChangePegListener implements ActionListener {

	private ArrayList<PegColor> bigColorArr = new ArrayList<PegColor>();
	//the colors for the guess and code pegs
	private ArrayList<PegColor> smallColorArr = new ArrayList<PegColor>();
	//the colors for the feedback pegs

	/**
	 * Initializes all the ArrayLists with all the colors for the peg types
	 */
	public ChangePegListener() {
		bigColorArr.add(PegColor.BLUE);
		bigColorArr.add(PegColor.GREEN);
		bigColorArr.add(PegColor.YELLOW);
		bigColorArr.add(PegColor.WHITE);
		bigColorArr.add(PegColor.BLACK);
		bigColorArr.add(PegColor.RED);

		smallColorArr.add(PegColor.WHITE);
		smallColorArr.add(PegColor.BLACK);
		smallColorArr.add(PegColor.NEUTRAL);
	}

	/**
	 * when the button is clicked the PegColor is changed to the next
	 * color on the list
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		PegUI peggy = (PegUI) ae.getSource();
		PegColor currentColor = peggy.getColor();
		PegColor newColor;
		if(peggy.getPegSize() == 50) {
			int index = bigColorArr.indexOf(currentColor);
			if (index != 5) {
				index++;
			} else {
				index = 0;
			}
			newColor = bigColorArr.get(index);
		} else {
			int index = smallColorArr.indexOf(currentColor);
			if(index != 2){
				index++;
			}else{
				index = 0;
			}
			newColor = smallColorArr.get(index);
		}
		peggy.setColor(newColor);
	}

}