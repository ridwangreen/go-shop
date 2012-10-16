package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import control.SixAxisController;

public class PastOrderView extends JFrame {

	/**
	 * Yeah.
	 */
	private static final long	serialVersionUID	= 1L;

	private JButton				back;
	private JPanel				bottom;
	private JButton				copy;
	private SixAxisController	listener;
	private JPanel				listPanel;
	private List				previousOrdersList;
	private JScrollPane			previousOrderPane;
	private JLabel				copyNumber;
	private JTextField			toCopy;

	public PastOrderView( SixAxisController controller ) {
		listener = controller;
		initComponents();
	}

	private void initComponents() {

		setTitle( "Past Orders" );
		setLayout(new BorderLayout());

		back = new JButton( "Back" );
		copy = new JButton( "Copy" );

		copy.setName( "Copy" );

		back.addActionListener( listener );
		copy.addActionListener( listener );
		
		back.setMnemonic( 'B' );
		copy.setMnemonic( 'C' );

		listPanel = new JPanel();
		previousOrderPane = new JScrollPane();
		previousOrdersList = new List();
		previousOrderPane.setViewportView( previousOrdersList );
		listPanel.add( previousOrderPane );

		GroupLayout layout = new GroupLayout(listPanel);
		listPanel.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(
				previousOrderPane, GroupLayout.DEFAULT_SIZE, 200,
				GroupLayout.DEFAULT_SIZE));
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(
				previousOrderPane, GroupLayout.DEFAULT_SIZE, 250,
				Short.MAX_VALUE));
		add( listPanel , BorderLayout.CENTER);

		copyNumber = new JLabel("Copy Number:  ");
		toCopy = new JTextField();
		
		bottom = new JPanel();
		bottom.setLayout( new GridLayout( 1, 0 ) );
		JPanel backPanel = new JPanel();
		JPanel copyPanel = new JPanel();
		backPanel.add( back );
		copyPanel.add( copy );

		bottom.add( backPanel );
		bottom.add( copyNumber );
		bottom.add( toCopy );
		bottom.add( copyPanel );
		
		add( bottom, BorderLayout.SOUTH);

		setResizable( false );
		pack();
	}

	public void addItemToList( Vector<String> previousOrders ) {
		
		previousOrdersList.removeAll();
		for(String line: previousOrders){
			previousOrdersList.add( line );
		}
	}
	
	public String getOrderIDText(){
		//System.out.println(toCopy.getText() + " <<000");
		return toCopy.getText();
	}
	
//	public static void main( String args[] ) {
//		java.awt.EventQueue.invokeLater( new Runnable() {
//
//			public void run() {
//				new PastOrderView( null ).setVisible( true );
//			}
//		} );
//	}

}
