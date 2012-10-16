/*
 * Filename:
 *		overrideScrre.java
 *
 * Version:
 *	   $Id$
 *
 * Revision:
 *	   $Log$
 */
package GUI;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.SixAxisController;


/**
 *
 * @author 
 *		Shaun DeVos
 * 		Brian Baum
 * 		Rebecca Dudley
 *		Jonathan Johnson
 *		Jonathan Olin
 */
public class overrideScreen extends JFrame{

	private SixAxisController listener;
	private JPanel warningPanel;
	private JPanel overWritePanel;
	private JLabel warning;
	private JButton overWrite;
	private JButton cancel;
	private JPanel cancelPanel;
	
	public overrideScreen( SixAxisController controller ) {

		listener = controller;
		initComponents();

	}
	
	private void initComponents(){
		
		setLayout( new BorderLayout() );
		warningPanel = new JPanel();
		overWritePanel = new JPanel();
		warning = new JLabel();
		warning.setText( "Warning, you are about to overwrite past order" );
		warningPanel.add( warning );
		add( warningPanel, BorderLayout.NORTH );
		overWrite = new JButton();
		overWrite.setText( "Overwrite" );
		overWrite.addActionListener( listener );
		overWrite.setMnemonic( 'O' );
		overWritePanel.add( overWrite );
		cancel = new JButton();
		cancelPanel = new JPanel();
		cancel.setText( "Cancel" );
		cancel.addActionListener( listener );
		cancel.setMnemonic( 'C' );
		cancelPanel.add( cancel );
		add(cancelPanel, BorderLayout.EAST);
		add( overWritePanel, BorderLayout.WEST );
		pack();
		setResizable( false );
		setVisible( true );

		
	}
	
}
