/*
 * Filename:
 *		popUpScreen.java
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
 * @author Shaun DeVos Brian Baum Rebecca Dudley Jonathan Johnson Jonathan Olin
 */
public class popUpScreen extends JFrame {

	private SixAxisController	listener;
	private JLabel				message;
	private JButton				ok;
	private String				messageString;
	private JPanel				okPanel;
	private JPanel				messagePanel;

	public popUpScreen( SixAxisController controller, String messageString ) {

		listener = controller;
		this.messageString = messageString;
		initComponents();
	}

	private void initComponents() {

		setLayout( new BorderLayout() );
		messagePanel = new JPanel();
		okPanel = new JPanel();
		message = new JLabel();
		message.setText( messageString );
		messagePanel.add( message );
		add( messagePanel, BorderLayout.NORTH );
		ok = new JButton();
		ok.setText( "Ok" );
		ok.setMnemonic( 'O' );
		ok.addActionListener( listener );
		okPanel.add( ok );
		add( okPanel, BorderLayout.WEST );
		pack();
		setResizable( false );
		setVisible( true );

	}

}
