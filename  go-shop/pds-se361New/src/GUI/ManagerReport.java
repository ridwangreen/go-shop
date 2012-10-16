/*
 * ManagerReport.java
 * 
 * Version:
 *     $Id$
 *     
 * Revision:
 *     $Log$
 */

package GUI;

import java.awt.BorderLayout;
import java.awt.List;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import control.SixAxisController;

/**
 * Brings up a manager only access screen to view a running list of daily stats
 * 
 * @author Shaun DeVos Brian Baum Rebecca Dudley Jonathan Johnson Jonathan Olin
 */

public class ManagerReport extends JFrame {

	// Variables

	/**
	 * yeah.
	 */
	private static final long	serialVersionUID	= 1L;

	private SixAxisController	listener;
	private JButton				back;
	private JPanel				reportPanel;
	private List				reportArea;
	private JPanel				container;
	private JPanel				bottom;

	/**
	 * Creates the GUI for outputting the manager report.
	 * 
	 * @param controller
	 */
	public ManagerReport( SixAxisController controller ) {
		listener = controller;
		initComponents();
	}

	/**
	 * 
	 * 
	 * @return void
	 */
	private void initComponents() {

		setLayout( new BorderLayout() );
		setTitle( "Manager Report" );

		back = new JButton( "Back" );
		back.addActionListener( listener );
		back.setMnemonic( 'B' );

		reportPanel = new JPanel();
		reportArea = new List();
		container = new JPanel();
		bottom = new JPanel();

		GroupLayout layout = new GroupLayout( reportPanel );
		reportPanel.setLayout( layout );
		
		//align horizontally
		
		GroupLayout.ParallelGroup reportH = layout
				.createParallelGroup( GroupLayout.Alignment.LEADING );
		reportH.addComponent( reportArea, GroupLayout.DEFAULT_SIZE, 300,
				Short.MAX_VALUE );
		layout.setHorizontalGroup( reportH );
		
		//align Vertically
		
		GroupLayout.ParallelGroup reportV = layout
				.createParallelGroup( GroupLayout.Alignment.LEADING );
		reportV.addComponent( reportArea, GroupLayout.DEFAULT_SIZE, 350,
				Short.MAX_VALUE );
		layout.setVerticalGroup( reportV );

		container.add( reportPanel );
		add( container, BorderLayout.NORTH );

		bottom.add( back );
		add( bottom, BorderLayout.WEST );

		pack();
		setResizable( false );

	}

	/**
	 * 
	 */

	public void addItemToList( Vector<String> previousOrders ) {

		reportArea.removeAll();
		for ( String line : previousOrders ) {
			reportArea.add( line );
		}
	}

	/**
	 * changes the visibility of the window and resets all of the components to
	 * not selected.
	 * 
	 * @param make_visible
	 *            - true if the window is to be visible, false otherwise
	 * @override setVisible - Component
	 * @return void
	 */
	public void setVisible( boolean make_visible ) {
		super.setVisible( make_visible );
	}

	/**
	 * /**
	 * 
	 * 
	 * @param args
	 *            void
	 */
	// public static void main( String args[] ) {
	// java.awt.EventQueue.invokeLater( new Runnable() {
	//
	// public void run() {
	// SixAxisController controller = new SixAxisController();
	// new ManagerReport(controller).setVisible(true);
	// }
	// } );
	// }

}