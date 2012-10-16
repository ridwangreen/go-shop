/*
 * MainGUIPage.java
 * 
 * Version:
 *     $Id$
 *     
 * Revision:
 *     $Log$
 */

package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import control.SixAxisController;

/**
 * The main GUI that the user will see first in the PDS program
 *
 * @author 
 *		Shaun DeVos
 * 		Brian Baum
 * 		Rebecca Dudley
 *		Jonathan Johnson
 *		Jonathan Olin
 */
public class MainGUIPage extends JFrame {

	//Variables
	
	/**
	 * yeah.
	 */
	private static final long	serialVersionUID	= 1L;
	
	private SixAxisController listener;
	private JButton addNewOrder;
	private JPanel bottomPanel;
	private JButton cancelOrder;
	private JButton currentState;
	private JLabel editCancel;
	private JButton editOrder;
	private JButton estimatedTime;
	private JPanel gridPanel;
	private JButton managerReport;
	private JButton name;
	private JPanel orderListPanel;
	private JButton orderNumber;
	private JScrollPane orderScrollPane;
	private JButton phoneNumber;
	private JLabel pineappleLogo;
	private JButton timeElapse;
	private JTextField toEditDelete;
	private JPanel topPanel;
	private JButton quit;
	private JTextField[][] textBoxes;
	//private ShutDownController shutDownListener;
	
	/**
	 * Creates new form MainGUIPage
	 */
	public MainGUIPage(SixAxisController controller) {
		//shutDownListener = shut_down;
		listener = controller;
		
		//this.addWindowListener( shutDownListener );
		this.setName( "Main Page" );
		initComponents();
	}

	/**
	 * Initialize all components for this GUI
	 *
	 * void
	 */
	private void initComponents() {
		GridBagConstraints gridBagConstraints;

		topPanel = new JPanel();
		pineappleLogo = new JLabel();
		managerReport = new JButton();
		orderNumber = new JButton();
		name = new JButton();
		phoneNumber = new JButton();
		timeElapse = new JButton();
		currentState = new JButton();
		estimatedTime = new JButton();
		bottomPanel = new JPanel();
		editOrder = new JButton();
		addNewOrder = new JButton();
		cancelOrder = new JButton();
		orderListPanel = new JPanel();
		orderScrollPane = new JScrollPane();
		editCancel = new JLabel();
		toEditDelete = new JTextField();
		quit = new JButton();
		gridPanel = new JPanel();
		textBoxes = new JTextField[50][6];

		//Give all the components a name
		
		topPanel.setName( "Top Panel" );
		pineappleLogo.setName( "Logo" );
		managerReport.setName( "Manager Report" );
		orderNumber.setName( "Order Number" );
		name.setName( "Name" );
		phoneNumber.setName( "Phone Number" );
		timeElapse.setName( "Time Elapsed" );
		currentState.setName( "Current State" );
		estimatedTime.setName( "Estimated Time" );
		bottomPanel.setName( "Bottom Panel" );
		editOrder.setName( "Edit Order" );
		addNewOrder.setName( "Add New Order" );
		cancelOrder.setName( "Cancel Order" );
		orderListPanel.setName( "Order List Panel" );
		orderScrollPane.setName( "Order Scroll Pane" );
		
		setDefaultCloseOperation(0);
		setResizable(false);

		int defaultSize = GroupLayout.DEFAULT_SIZE;
		int preferred = GroupLayout.PREFERRED_SIZE;
		GroupLayout.Alignment leading = GroupLayout.Alignment.LEADING;
		
		topPanel.setLayout(new GridBagLayout());
		topPanel.setOpaque( true );
		topPanel.setBackground( new Color( 255, 90, 0 ) );

		//Create main Layout and add the Program name to the title frame
		
		pineappleLogo.setFont(new Font("Segoe UI", 0, 18));
		pineappleLogo.setText("Pineapple 1.0");
		pineappleLogo.setVerticalAlignment(SwingConstants.TOP);
		pineappleLogo.setOpaque( true );
		pineappleLogo.setBackground( new Color( 255, 90, 0 ) );
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.anchor =
			GridBagConstraints.FIRST_LINE_START;
		gridBagConstraints.insets = new Insets(0, 90, 0, 90);
		topPanel.add(pineappleLogo, gridBagConstraints);

		managerReport.setText("Manager Report");
		
		
		//Set all the Mnemonics for all the buttons
		

		managerReport.setMnemonic( 'M' );
		orderNumber.setMnemonic( 'O' );
		phoneNumber.setMnemonic( 'P' );
		timeElapse.setMnemonic( 'T' );
		currentState.setMnemonic( 'u' );
		estimatedTime.setMnemonic( 's' );
		editOrder.setMnemonic( 'E' );
		quit.setMnemonic( 'Q' );
		addNewOrder.setMnemonic( 'A' );
		cancelOrder.setMnemonic( 'C' );
		name.setMnemonic( 'N' );

		
		//Add all the ActionListeners to each button
		
		
		managerReport.addActionListener( listener );
		orderNumber.addActionListener( listener );
		phoneNumber.addActionListener( listener );
		timeElapse.addActionListener( listener );
		currentState.addActionListener( listener );
		estimatedTime.addActionListener( listener );
		editOrder.addActionListener( listener );
		addNewOrder.addActionListener( listener );
		cancelOrder.addActionListener( listener );
		name.addActionListener( listener );
		
		
		//Place the buttons in top panel
		
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 5;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints.ipady = 2;
		gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
		topPanel.add(managerReport, gridBagConstraints);

		
		bottomPanel.setLayout(new GridLayout(2, 3));
		bottomPanel.setOpaque( true );
		
		editOrder.setText( "Edit Order" );
		addNewOrder.setText( "Add New Order" );
		cancelOrder.setText( "Cancel Order" );
		editCancel.setText( " Edit/Cancel order number: " );
		quit.setText( "Quit" );
		quit.addActionListener( listener );
//		quit.addActionListener(new ActionListener(){
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				dispose();
//				
//			}});
		
		
		bottomPanel.add( addNewOrder );
		bottomPanel.add( editCancel );
		bottomPanel.add( toEditDelete );
		bottomPanel.add( quit );
		bottomPanel.add( editOrder );
		bottomPanel.add( cancelOrder );
		
		//create grid
		
		orderNumber.setText("Order #");
		name.setText( "Name" );
		phoneNumber.setText( "Phone #" );
		timeElapse.setText( "Time Elapsed" );
		estimatedTime.setText( "CurrentState" );
		currentState.setText( "ETA" );
		gridPanel.setLayout( new GridLayout(51,6) );
		gridPanel.add( orderNumber );
		gridPanel.add(name);
		gridPanel.add(phoneNumber);
		gridPanel.add(timeElapse);
		gridPanel.add(estimatedTime);
		gridPanel.add(currentState);
		for(int r = 0 ; r < 50; r++){
			for(int c = 0; c < 6; c++){
				textBoxes[r][c] = new JTextField();
				textBoxes[r][c].setEditable( false );
			}	
		}
		for(int r = 0 ; r < 50; r++){
			for(int c = 0; c < 6; c++){
				gridPanel.add(textBoxes[r][c]);
			}	
		}

		orderScrollPane.setViewportView( gridPanel );
		
		
		GroupLayout orderListPanelLayout = new GroupLayout(orderListPanel);
		GroupLayout.ParallelGroup horizScrollGroup = 
			orderListPanelLayout.createParallelGroup(leading);
		horizScrollGroup.addComponent(
				orderScrollPane, defaultSize,678,Short.MAX_VALUE);
		orderListPanel.setLayout(orderListPanelLayout);
		orderListPanelLayout.setHorizontalGroup(horizScrollGroup);
		GroupLayout.ParallelGroup vertScrollGroup = 
			orderListPanelLayout.createParallelGroup(leading);
		vertScrollGroup.addComponent(
				orderScrollPane,defaultSize, 381,Short.MAX_VALUE);
		orderListPanelLayout.setVerticalGroup(vertScrollGroup);

		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		GroupLayout.ParallelGroup horizMainGroup = 
			layout.createParallelGroup(leading);
		horizMainGroup.addComponent(
				bottomPanel, preferred,defaultSize,678);
		horizMainGroup.addComponent(
				topPanel,defaultSize,defaultSize, Short.MAX_VALUE);
		horizMainGroup.addComponent(
				orderListPanel,defaultSize,defaultSize, Short.MAX_VALUE);
		layout.setHorizontalGroup(horizMainGroup);
		GroupLayout.SequentialGroup inMainVertGroup = 
			layout.createSequentialGroup();
		inMainVertGroup.addComponent(topPanel,preferred,defaultSize,preferred);
		inMainVertGroup.addComponent(orderListPanel,preferred,
				defaultSize,preferred);
		inMainVertGroup.addComponent(
				bottomPanel,defaultSize,30,Short.MAX_VALUE);
		;
		layout.setVerticalGroup(inMainVertGroup);

		pack();
	}

	
	/**
	 * changes the visibility of the window and resets all of the components to
	 * not selected.
	 * 
	 * @param
	 * 		make_visible - true if the window is to be visible, false otherwise
 	 * @override
	 * 		setVisible - Component
	 * @return
	 * 		void
	 */
	public void setVisible( boolean make_visible ) {
		super.setVisible( make_visible );

	}
	
	/**
	 * Adds a list of orders to the order list
	 *
	 * @param
	 * 		vector - the list or orders to re-add to orderList
	 * @return
	 * 		void
	 */
	public void addAllOrders( Vector<Vector<String>> ordersList) {
		int currComp = 0;
		int MAXORDERS = 50;
		
		for(int i = 0; i < MAXORDERS; i++){
			for(int c = 0; c < 6; c++){
				textBoxes[i][c].setText( "" );
			}
		}
		
		for(Vector<String> line : ordersList){
			for(int i = 0; i < 6 ; i++){
				textBoxes[currComp][i].setText( line.get( i ) );
				//((JTextField)gridPanel.getComponent( currComp )).setText(line.get( i ));
			}
			
			currComp++;
			if(currComp >= MAXORDERS){
				//catches to make sure does not go out of bounds
				return;
			}
		}
		
	}

	
	/**
	 * Adds a single order to the order list
	 *
	 * @param
	 * 		newOrder - Order to be added to the order list
	 * @return
	 * 		void
	 */	
	public void addOrderToList( String newOrder ) {
		//orderList.add( newOrder );
	}
		
	/**
	 * returns the index of the order to be edited or removed,
	 *  -1 if there is nothing there
	 *
	 * @return  the index of the order to be edited or removed
	 */
	public String getIndexToEditOrRemove(){
		
		return toEditDelete.getText();
		
	}
	
//	/**
//	 * @param args the command line arguments
//	 */
//	/**
//	 * @param args the command line arguments
//	 */
//	public static void main(String args[]) {
//		java.awt.EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				new MainGUIPage(null).setVisible(true);
//			}
//		});
//	}

}