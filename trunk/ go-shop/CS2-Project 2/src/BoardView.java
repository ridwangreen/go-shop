
/*
 * Board.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

/**
 * Creates a board for the frenzy game
 *
 * @author Rebecca Dudley
 */
public class BoardView  {

	private static FrenzyButton buttons[][];
	private static JFrame gameFrame;
	private static Player player;
	public FrenzyModel model;
	private JTextArea updates;
	private int amountConsump;
	private JLabel amountEaten;
	private JLabel gameStatus;
	
	/**
	 * creates a new board with given diameters
	 * 
	 * @param height   the height of the game board
	 * @param width    the width of the game board
	 */
	public BoardView(int length, FrenzyModel model) {
		
		amountConsump = 0;
		this.model = model;
		gameFrame = new JFrame();
		BoardController controller = new BoardController(this, model);
        gameFrame.addKeyListener(controller);
        long eventMask = AWTEvent.KEY_EVENT_MASK; 
        gameFrame.getToolkit().addAWTEventListener(controller, eventMask);
		gameFrame.setLayout(new BorderLayout());
		createTopPanel(gameFrame);
		buttons = new FrenzyButton[length][length];
		createButtonPanel(gameFrame, length);
		updateView();
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.pack();
		gameFrame.setVisible(true);
		new Thread(){
			public void run(){
				while(true){
				BoardView.this.updateView();
				}
			}
		}.start();
		
	}
	
	/**
	 * creates the top panel for the game board
	 * 
	 * @param frame   the frame that holds the top panel
	 */
	public void createTopPanel(JFrame frame){
		
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(1,4));
		JPanel labels = new JPanel();
		labels.setLayout(new GridLayout(2,1));
		JLabel consump = new JLabel("Consumption");
		JLabel player = new JLabel("Peice is P");
		labels.add(consump);
		labels.add(player);
		container.add(labels);
		JPanel values = new JPanel();
		values.setLayout(new GridLayout(2,1));
		String ate = "";
		amountEaten = new JLabel(ate + 0);
		amountEaten.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		gameStatus = new JLabel("Game Running!");
		gameStatus.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		values.add(amountEaten);
		values.add(gameStatus);
		container.add(values);
		JPanel ateList = new JPanel();
		ateList.setLayout(new GridLayout(1,1));
		updates = new JTextArea();
		DefaultCaret caret = (DefaultCaret) updates.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		updates.setEditable(false);
		JScrollPane updatesBar = new JScrollPane(updates);
		updatesBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		updatesBar.setPreferredSize(new Dimension(200,100));
		ateList.add(updatesBar);
		container.add(ateList);
		JPanel clicks = new JPanel();
		clicks.setLayout(new GridLayout(3,2));
		JCheckBox pause = new JCheckBox("Pause");
		pause.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				model.setPaused(!model.paused());
				
			}
			
		});
		clicks.add(pause);
		clicks.add(new JLabel());
		JButton plus = new JButton("+ +");
		clicks.add(plus);
		plus.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0){
				model.setSpeed(model.getSpeed()-100);
			}
		});
		JButton minus = new JButton("- -");
		clicks.add(minus);
		minus.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0){
				model.setSpeed(model.getSpeed()+100);
			}
		});
		JButton restart = new JButton("Restart");
		clicks.add(restart);
		restart.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0){
				model.reset();
			}
		});
		JButton quit = new JButton("Quit");
		clicks.add(quit);
		quit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameFrame.dispose();
				
			}});
		container.add(clicks);
		frame.add(container, BorderLayout.NORTH);
		
		
		
	}
	
	public static JFrame getFrame(){
		return gameFrame;
	}
	
	/**
	 * creates the button planel for the game board with given diameters
	 * 
	 * @param frame  the frame to add the buttons to
	 * @param height  the height of the panel
	 * @param width   the width of the panel
	 */
	public void createButtonPanel(JFrame frame,int length){
		JPanel bPanel = new JPanel();
		bPanel.setBorder(BorderFactory.createTitledBorder("Game Board"));
		bPanel.setLayout(new GridLayout(length,length));
		FrenzyButton clicky;
		for(int r = 0; r < length; r++){
			for(int c = 0; c < length; c++){
				clicky = new FrenzyButton(r,c);
				bPanel.add(clicky);
				clicky.addActionListener(new FrenzyListener(this));
				buttons[r][c] = clicky;
			}
		}
		frame.add(bPanel, BorderLayout.CENTER);
	}
	
	public FrenzyButton[][] getButtons(){
		return buttons;
	}
   

    
    
    /**
     * makes the player graphics
     * 
     * @param p   the player peice
     * @param x   the old x coordinate
     * @param y   the old y corrdinate
     */
    public void updateView(){
    	
    	for(int r = 0; r < buttons.length; r++){
    		for(int c = 0; c < buttons[r].length; c++){
    			String text = model.getPieces()[r][c].getText();
    			Color color = model.getPieces()[r][c].getColor();
    			if(!buttons[r][c].getText().equals(text) ||buttons[r][c].getBackground().equals(color)){
    				buttons[r][c].setText(text);
        			buttons[r][c].setBackground(color);
    			}
    			
    			
    		}
    	}
    	
    }
    
    public void update(String messege){
    
    	updates.append(messege + "\n");
    	
    }
    
    public void updateGameStatus(String text){
    	
    	gameStatus.setText(text);
    	
    }
    
    public void addConsumed(){
    	
    	String num = "";
    	amountConsump++;
    	amountEaten.setText(num + amountConsump);
    }
    
    public void resetConsumed(){
    	
    	String num = "";
    	amountConsump = 0;
    	amountEaten.setText(num + amountConsump);
    	
    }
	
}
