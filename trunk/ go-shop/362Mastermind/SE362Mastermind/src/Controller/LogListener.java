package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import system.LogHandler;

import GUI.GameMenu;

public class LogListener implements ActionListener{

	private GameMenu gm;
	private int index;
	
	public LogListener(GameMenu gm, int index){
	
		this.gm = gm;
		this.index = index;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		gm.setEnabledItem(index, false);
		if(index == 1){
			gm.setEnabledItem(2, true);
			String filename = JOptionPane.showInputDialog(null, "Enter Log Name:");
			LogHandler.getInstance().setFileName(filename);
		}else{
			gm.setEnabledItem(1, false);
			LogHandler.getInstance().setFileName(null);
		}
	}
}
