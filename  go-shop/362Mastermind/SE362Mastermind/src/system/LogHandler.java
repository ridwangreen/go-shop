package system;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

import Command.Command;

/*
 * @File NameGame.java
 * 
 * @Authors Alex Kahn, John Neville, Alex Canter, Becca Dudley
 * 
 * @Class Description this is the class that will deal with the log.
 * lumberjack
 */
public class LogHandler implements Observer {
	private static LogHandler instance = null; // The Singleton instance 
	String fName = null;
	
	/**
	 * Protected, Singleton constructor.
	 * No outside Object is allowed to create a new LogHandler.
	 * THERE CAN ONLY BE ONE!!!
	 */
	protected LogHandler() {
	}
	/**
	 * Implementation of Singleton's getInstance()
	 * @return The LogHandler instance if one exists, a new
	 *         LogHandler instance otherwise.
	 */
	public static LogHandler getInstance() {
		if(instance == null) {
			instance = new LogHandler();
		}
		return instance;
	}
	
	/**
	 * Sets the logger filename. Will not overwrite existing file
	 * 
	 * @param filename the filename to use for logging
	 */
	public void setFileName(String filename){
		setFileName(filename, false);
	}
	
	
	/**
	 * Sets the logger filename
	 * 
	 * @param filename the filename to use for logging
	 * @param overwrite should the file be overwrited? true = yes
	 */
	public void setFileName(String filename, boolean overwrite){
		fName = filename;
		if(overwrite){
			try {
				FileWriter writer = new FileWriter(fName);
				writer.write("");
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean write(Command lastCommand) {	
		FileWriter writer = null;
		if(fName!=null){
			try {
				writer = new FileWriter(fName);
				writer.append(lastCommand.toString()+System.getProperty("line.separator"));
				writer.close();
				
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * Get update 
	 */
	public void update(Observable arg0, Object arg1) {
		GameState state = (GameState) arg0;
		write(state.getLastCommand());
	}
}
