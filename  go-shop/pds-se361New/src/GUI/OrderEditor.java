/*
 * OrderEditor.java
 * 
 * Version:
 *     $Id$
 *     
 * Revision:
 *     $Log$
 */

package GUI;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import control.SixAxisController;

/**
 * 
 *
 * @author 
 *		Shaun DeVos
 * 		Brian Baum
 * 		Rebecca Dudley
 *		Jonathan Johnson
 *		Jonathan Olin
 */
public class OrderEditor extends  JFrame {

	//Variables
	
	/**
	 * Yeah.
	 */
	private static final long	serialVersionUID	= 1L;
	
	private JLabel address;
	private JComboBox addressDropDown;
	private JButton back;
	private JPanel bottomButtons;
	private JCheckBox box1f;
	private JCheckBox box1s;
	private JCheckBox box2f;
	private JCheckBox box2s;
	private JCheckBox box3f;
	private JCheckBox box3s;
	private JCheckBox box4f;
	private JCheckBox box4s;
	private JCheckBox box5f;
	private JCheckBox box5s;
	private JButton check;
	private JLabel currentOrder;
	private JScrollPane currentOrderList;
	private JPanel currentOrderPanel;
	private JButton deleteFromOrder;
	private JLabel deleteLabel;
	private JLabel first;
	private JLabel half;
	private JButton addToOrder;
	private JPanel checkPanel;
	private JPanel customerInfoPanel;
	private JRadioButton large;
	private SixAxisController listener;
	private JRadioButton medium;
	private JLabel mushrooms;
	private JLabel name;
	private JTextField nameTextField;
	private JLabel onions;
	private JPanel options;
	private ButtonGroup optionsButtons;
	private List orderList;
	private JLabel orderNum;
	private JButton pastOrders;
	private JLabel pepperoni;
	private JLabel peppers;
	private JLabel phone;
	private JTextField phoneTextField;
	private JRadioButton pizza;
	private JRadioButton pizzaLogs;
	private JPanel pizzaOptions;
	private JButton placeOrder;
	private JRadioButton salad;
	private JLabel sausage;
	private JLabel second;
	private JLabel size;
	private ButtonGroup sizeOptions;
	private JRadioButton small;
	private JTextField toDelete;
	private  JPanel top;
	private  JLabel toppings;
	private JLabel totalPrice;
	
	/**
	 * Creates new form order
	 */
	public OrderEditor(SixAxisController controller) {
		listener = controller;
		initComponents();
	}

	private void initComponents() {

	optionsButtons = new ButtonGroup();
	sizeOptions = new ButtonGroup();
	top = new JPanel();
	options = new JPanel();
	salad = new JRadioButton();
	pizzaLogs = new JRadioButton();
	pizza = new JRadioButton();
	pizzaOptions = new JPanel();
	size = new JLabel();
	small = new JRadioButton();
	medium = new JRadioButton();
	large = new JRadioButton();
	toppings = new JLabel();
	half = new JLabel();
	first = new JLabel();
	second = new JLabel();
	pepperoni = new JLabel();
	sausage = new JLabel();
	onions = new JLabel();
	peppers = new JLabel();
	mushrooms = new JLabel();
	checkPanel = new JPanel();
	box1f = new JCheckBox();
	box1s = new JCheckBox();
	box2f = new JCheckBox();
	box2s = new JCheckBox();
	box3f = new JCheckBox();
	box3s = new JCheckBox();
	box4f = new JCheckBox();
	box4s = new JCheckBox();
	box5f = new JCheckBox();
	box5s = new JCheckBox();
	check = new JButton();
	currentOrderPanel = new JPanel();
	currentOrderList = new JScrollPane();
	orderList = new List();
	orderNum = new JLabel();
	currentOrder = new JLabel();
	bottomButtons = new JPanel();
	back = new JButton();
	addToOrder = new JButton();
	deleteFromOrder = new JButton();
	placeOrder = new JButton();
	customerInfoPanel = new JPanel();
	name = new JLabel();
	nameTextField = new JTextField();
	phone = new JLabel();
	phoneTextField = new JTextField();
	address = new JLabel();
	addressDropDown = new JComboBox();
	pastOrders = new JButton();
	toDelete = new JTextField();
	deleteLabel = new JLabel();
	totalPrice = new JLabel();

	//Label Topping Buttons
	
	box1f.setName( "Pepperoni, f");
	box1s.setName( "Pepperoni, s" );
	box2f.setName( "Sausage, f");
	box2s.setName( "Sausage, s" );
	box3f.setName( "Onion, f" );
	box3s.setName( "Onion, s" );
	box4f.setName( "Pepper, f" );
	box4s.setName( "Pepper, s" );
	box5f.setName( "Mushrooms, f" );
	box5s.setName( "Mushrooms, s" );
	
	//Assign Mnemonics
	
	back.setMnemonic( 'B' );
	deleteFromOrder.setMnemonic( 'D' );
	addToOrder.setMnemonic( 'A' );
	pastOrders.setMnemonic( 'P' );
	placeOrder.setMnemonic( 'O' );
	
	//Assign ActionListeners
	
	salad.addActionListener( listener );
	pizzaLogs.addActionListener( listener );
	pizza.addActionListener( listener );
	small.addActionListener( listener );
	medium.addActionListener( listener );
	large.addActionListener( listener );
	box1f.addActionListener( listener );
	box1s.addActionListener( listener );
	box2f.addActionListener( listener );
	box2s.addActionListener( listener );
	box3f.addActionListener( listener );
	box3s.addActionListener( listener );
	box4f.addActionListener( listener );
	box4s.addActionListener( listener );
	box5f.addActionListener( listener );
	box5s.addActionListener( listener );
	back.addActionListener( listener );
	addToOrder.addActionListener( listener );
	deleteFromOrder.addActionListener( listener );
	placeOrder.addActionListener( listener );
	addressDropDown.addActionListener( listener );
	pastOrders.addActionListener( listener );
	check.addActionListener( listener );
	
	
	
	GroupLayout.Alignment leading = GroupLayout.Alignment.LEADING;
	GroupLayout.Alignment trailing = GroupLayout.Alignment.TRAILING;
	GroupLayout.Alignment baseline = GroupLayout.Alignment.BASELINE;
	int defaultSize = GroupLayout.DEFAULT_SIZE;
	LayoutStyle.ComponentPlacement unrelated = 
		LayoutStyle.ComponentPlacement.UNRELATED;
	LayoutStyle.ComponentPlacement related = 
		LayoutStyle.ComponentPlacement.RELATED;
	int preferredSize = GroupLayout.PREFERRED_SIZE;
	
	setResizable(false);
	
	
	optionsButtons.add(salad);
	salad.setText("Salad");
	salad.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			setEnabled(false);
		}
	});
	
	optionsButtons.add(pizzaLogs);
	pizzaLogs.setText("Pizza Logs");
	pizzaLogs.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			setEnabled(false);
		}
	});
	
	optionsButtons.add(pizza);
	pizza.setText("Pizza");
	pizza.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			setEnabled(true);
		}
	});
	
	
	GroupLayout optionsLayout = new GroupLayout(options);
	options.setLayout(optionsLayout);
	GroupLayout.ParallelGroup foodOptionGroup = 
		optionsLayout.createParallelGroup(leading);
	GroupLayout.SequentialGroup layoutGroup = 
		optionsLayout.createSequentialGroup();
	layoutGroup.addContainerGap().addGroup(foodOptionGroup);
	layoutGroup.addContainerGap(defaultSize, Short.MAX_VALUE);
	foodOptionGroup.addComponent(salad).addComponent(pizzaLogs);
	foodOptionGroup.addComponent(pizza);
	optionsLayout.setHorizontalGroup(
			optionsLayout.createParallelGroup(leading).addGroup(layoutGroup)
	);
	
	
	GroupLayout.SequentialGroup containerLayout = 
		optionsLayout.createSequentialGroup();
	containerLayout.addContainerGap();
	containerLayout.addComponent(salad).addPreferredGap(unrelated);
	containerLayout.addComponent(pizzaLogs).addPreferredGap(unrelated);
	containerLayout.addComponent(pizza).addContainerGap(
			defaultSize, Short.MAX_VALUE);
	GroupLayout.ParallelGroup verticalGroup = 
		optionsLayout.createParallelGroup(leading).addGroup(containerLayout);
	optionsLayout.setVerticalGroup(verticalGroup);
	
	
	GroupLayout topLayout = new GroupLayout(top);
	top.setLayout(topLayout);
	GroupLayout.SequentialGroup horizLayout = 
		topLayout.createSequentialGroup();
	horizLayout.addComponent(
			options, preferredSize, defaultSize, preferredSize);
	horizLayout.addContainerGap(143, Short.MAX_VALUE);
	topLayout.setHorizontalGroup(horizLayout);
	
	
	GroupLayout.ParallelGroup topVertGroup = 
		topLayout.createParallelGroup(leading);
	topVertGroup.addComponent(options,preferredSize,defaultSize,preferredSize);
	GroupLayout.SequentialGroup vertSeq = topLayout.createSequentialGroup();
	topVertGroup.addGroup(vertSeq.addContainerGap(14, Short.MAX_VALUE));
	topLayout.setVerticalGroup(topVertGroup);
	pizzaOptions.setRequestFocusEnabled(false);
	
	//The text for all the Pizza options
	
	size.setText("Size:");
	small.setText("Small");
	medium.setText("Medium");
	large.setText("Large");
	
	sizeOptions.add(small);
	sizeOptions.add(medium);
	sizeOptions.add(large);
	
	//The text for all the Topping check boxes
	
	toppings.setText("Toppings:");
	half.setText("Half:");
	first.setText("Left");
	second.setText("Right");
	pepperoni.setText("Pepperoni");
	sausage.setText("Sausage");
	onions.setText("Onions");
	peppers.setText("Peppers");
	mushrooms.setText("Mushrooms");
	
	
	 GroupLayout checkPanelLayout = new GroupLayout(checkPanel);
	checkPanel.setLayout(checkPanelLayout);
	
	GroupLayout.ParallelGroup firstGroup = 
		checkPanelLayout.createParallelGroup(leading);
	firstGroup.addComponent(box1f).addComponent(box2f);
	firstGroup.addComponent(box3f).addComponent(box4f).addComponent(box5f);
	
	GroupLayout.ParallelGroup secondGroup = 
		checkPanelLayout.createParallelGroup(leading);
	secondGroup.addComponent(box1s).addComponent(box2s);
	secondGroup.addComponent(box3s).addComponent(box4s).addComponent(box5s);
	
	GroupLayout.SequentialGroup boxsGroup = 
		checkPanelLayout.createSequentialGroup();
	boxsGroup.addGroup(firstGroup).addGap(47,47,47).addGroup(secondGroup);
	
	GroupLayout.SequentialGroup horSeqGroup = 
		checkPanelLayout.createSequentialGroup().addContainerGap();
	horSeqGroup.addGroup(boxsGroup).addContainerGap(36, Short.MAX_VALUE);
	
	GroupLayout.ParallelGroup horizBoxGroup = 
		checkPanelLayout.createParallelGroup(leading).addGroup(horSeqGroup);
	checkPanelLayout.setHorizontalGroup(horizBoxGroup);
	
	
	GroupLayout.SequentialGroup firstHalfGroup1 = 
		checkPanelLayout.createSequentialGroup();
	firstHalfGroup1.addComponent(box1f).addPreferredGap(related);
	firstHalfGroup1.addComponent(box2f).addPreferredGap(related);
	firstHalfGroup1.addComponent(box3f).addPreferredGap(related);
	
	//I'm working here
	
	checkPanelLayout.setVerticalGroup(
	checkPanelLayout.createParallelGroup(leading)
	.addGroup(checkPanelLayout.createSequentialGroup()
	.addGroup(checkPanelLayout.createParallelGroup(leading)
	.addGroup(checkPanelLayout.createSequentialGroup()
	.addComponent(box1f)
	.addPreferredGap(related)
	.addComponent(box2f)
	.addPreferredGap(related)
	.addComponent(box3f))
	.addGroup(checkPanelLayout.createSequentialGroup()
	.addComponent(box1s)
	.addPreferredGap(related)
	.addComponent(box2s)
	.addPreferredGap(related)
	.addComponent(box3s)))
	.addPreferredGap(related)
	.addGroup(checkPanelLayout.createParallelGroup(trailing)
	.addGroup(checkPanelLayout.createSequentialGroup()
	.addComponent(box4f)
	.addPreferredGap(related)
	.addComponent(box5f))
	.addGroup(checkPanelLayout.createSequentialGroup()
	.addComponent(box4s)
	.addPreferredGap(related)
	.addComponent(box5s))))
	);
	
	GroupLayout pizzaOptionsLayout = new GroupLayout(pizzaOptions);
	pizzaOptions.setLayout(pizzaOptionsLayout);
	pizzaOptionsLayout.setHorizontalGroup(
	pizzaOptionsLayout.createParallelGroup(leading)
	.addGroup(pizzaOptionsLayout.createSequentialGroup()
	.addGroup(pizzaOptionsLayout.createParallelGroup(leading)
	.addGroup(pizzaOptionsLayout.createSequentialGroup()
	.addContainerGap()
	.addGroup(pizzaOptionsLayout.createParallelGroup(leading)
	.addComponent(size)
	.addGroup(pizzaOptionsLayout.createSequentialGroup()
	.addComponent(small)
	.addPreferredGap(unrelated)
	.addGroup(pizzaOptionsLayout.createParallelGroup(trailing)
	.addGroup(pizzaOptionsLayout.createSequentialGroup()
	.addComponent(medium)
	.addPreferredGap(unrelated)
	.addComponent(large))
	.addGroup(leading, pizzaOptionsLayout.createSequentialGroup()
	.addGroup(pizzaOptionsLayout.createParallelGroup(trailing)
	.addComponent(first)
	.addComponent(toppings))
	.addGap(38, 38, 38)
	.addComponent(second))))
	.addGroup(pizzaOptionsLayout.createSequentialGroup()
	.addGroup(pizzaOptionsLayout.createParallelGroup(leading)
	.addComponent(onions)
	.addComponent(peppers)
	.addComponent(mushrooms))
	.addGap(23, 23, 23)
	.addComponent(checkPanel, defaultSize, defaultSize, Short.MAX_VALUE))))
	.addGroup(pizzaOptionsLayout.createSequentialGroup()
	.addContainerGap()
	.addComponent(pepperoni))
	.addGroup(pizzaOptionsLayout.createSequentialGroup()
	.addContainerGap()
	.addComponent(sausage))
	.addGroup(pizzaOptionsLayout.createSequentialGroup()
	.addGap(31, 31, 31)
	.addComponent(half)))
	.addContainerGap(defaultSize, Short.MAX_VALUE))
	);
	
	//
	
	pizzaOptionsLayout.setVerticalGroup(
		pizzaOptionsLayout.createParallelGroup(
				leading)
		.addGroup(trailing,
				pizzaOptionsLayout.createSequentialGroup()
			.addComponent(size)
			.addGap(9, 9, 9)
			.addGroup(pizzaOptionsLayout.createParallelGroup(
					baseline)
				.addComponent(small)
				.addComponent(medium)
				.addComponent(large))
			.addGap(30, 30, 30)
			.addGroup(pizzaOptionsLayout.createParallelGroup(
					trailing)
				.addGroup(pizzaOptionsLayout.createSequentialGroup()
				.addGap(30, 30, 30)
				.addComponent(half)
				.addPreferredGap(related,
						defaultSize, Short.MAX_VALUE))
			.addGroup(pizzaOptionsLayout.createSequentialGroup()
				.addPreferredGap(related)
				.addComponent(toppings)
				.addPreferredGap(unrelated)
				.addGroup(pizzaOptionsLayout.createParallelGroup(
						baseline)
					.addComponent(second)
					.addComponent(first))
				.addGap(18, 18, 18)))
		.addPreferredGap(related)
		.addGroup(pizzaOptionsLayout.createParallelGroup(
				leading)
			.addGroup(pizzaOptionsLayout.createSequentialGroup()
				.addComponent(pepperoni)
				.addPreferredGap(related)
				.addComponent(sausage)
				.addGap(6, 6, 6)
				.addComponent(onions)
				.addPreferredGap(related)
				.addComponent(peppers)
				.addPreferredGap(related)
				.addComponent(mushrooms))
			.addComponent(checkPanel, defaultSize, 
					defaultSize, Short.MAX_VALUE))
		.addContainerGap())
	);
	
	JPanel priceContain = new JPanel();
	totalPrice.setText( "Total Price: " );
	priceContain.add( totalPrice );
	currentOrderList.setViewportView(orderList);
	currentOrder.setText("Current Order:");
	
	GroupLayout currentOrderPanelLayout = 
		new GroupLayout(currentOrderPanel);
	currentOrderPanel.setLayout(currentOrderPanelLayout);
	currentOrderPanelLayout.setHorizontalGroup(
		currentOrderPanelLayout.createParallelGroup(
				leading)
		.addGroup(currentOrderPanelLayout.createSequentialGroup()
			.addGroup(currentOrderPanelLayout.createParallelGroup(
					leading)
				.addGroup(currentOrderPanelLayout.createSequentialGroup()
				.addContainerGap()
				.addComponent(currentOrder))
				.addComponent( priceContain )
			.addComponent(currentOrderList,
					 preferredSize, 320, preferredSize))
		.addContainerGap(defaultSize, Short.MAX_VALUE))
	);
	
	currentOrderPanelLayout.setVerticalGroup(
	currentOrderPanelLayout.createParallelGroup(leading)
	.addGroup(currentOrderPanelLayout.createSequentialGroup()
	.addComponent(currentOrder)
	.addPreferredGap(related)
	.addComponent(currentOrderList, defaultSize, 300, Short.MAX_VALUE)
	.addPreferredGap( related )
	.addComponent( priceContain )
	.addContainerGap())
	);
	
	back.setText("Back");
	addToOrder.setText("Add Item");
	deleteFromOrder.setText("Delete Item");
	placeOrder.setText("Place Order");
	deleteLabel.setText( "  Delete Number : " );
	
	bottomButtons.setLayout( new GridLayout(1,0) );
	
	bottomButtons.add( back ); 
	bottomButtons.add( addToOrder );
	bottomButtons.add( placeOrder ); 
	bottomButtons.add( deleteLabel );
	bottomButtons.add( toDelete );
	bottomButtons.add( deleteFromOrder );
	
	name.setText( "Name:" );
	phone.setText( "Phone:" );
	address.setText( "Address:" );
	pastOrders.setText( "Past Orders" );
	orderNum.setText( "Order Number:" );
	check.setText( "Check" );
	
	addressDropDown.addItem( "RIT" );
	addressDropDown.addItem( "University_of_Rochester" );
	addressDropDown.addItem( "Nazareth_College" );
	addressDropDown.addItem( "St._John_Fisher" );
	addressDropDown.addItem( "Roberts_Wesleyan_College" );
	addressDropDown.addItem( "Monroe_Community_College" );
	
	//Top panel containing the Name TextField, Phone TextField, Address DropDown
	
	 GroupLayout customerInfoPanelLayout = new  GroupLayout(customerInfoPanel);
	customerInfoPanel.setLayout(customerInfoPanelLayout);
	customerInfoPanelLayout.setHorizontalGroup(
		customerInfoPanelLayout.createParallelGroup(leading)
			.addGroup(customerInfoPanelLayout.createSequentialGroup()
			.addContainerGap()
			.addGroup(customerInfoPanelLayout.createParallelGroup(
					leading, false)
			.addGroup(customerInfoPanelLayout.createSequentialGroup()
				.addComponent(address)
				.addPreferredGap(related)
				.addComponent(addressDropDown))
			.addGroup(customerInfoPanelLayout.createSequentialGroup()
				.addComponent(name)
				.addPreferredGap(related)
				.addComponent(nameTextField, preferredSize, 110,
						preferredSize)
				.addPreferredGap(related)
				.addComponent(phone)
				.addGap(5, 5, 5)
				.addComponent(phoneTextField, preferredSize, 115,
						preferredSize)))
		.addGap(18, 18, 18)
		.addComponent(check, preferredSize, 72,
				preferredSize)
				.addGap(36)
		.addComponent(pastOrders, preferredSize, 110,
				preferredSize))
	);
	
	customerInfoPanelLayout.setVerticalGroup(
	customerInfoPanelLayout.createParallelGroup(leading)
	.addGroup(customerInfoPanelLayout.createSequentialGroup()
	.addContainerGap()
	.addGroup(customerInfoPanelLayout.createParallelGroup(leading)
	.addGroup(customerInfoPanelLayout.createSequentialGroup()
	.addComponent(pastOrders, defaultSize, 52, Short.MAX_VALUE)
	.addContainerGap())
		.addComponent(check, preferredSize, 20,
				preferredSize)
	.addGroup(customerInfoPanelLayout.createSequentialGroup()
	.addGroup(customerInfoPanelLayout.createParallelGroup(baseline)
	.addComponent(name)
	.addComponent(nameTextField, preferredSize, defaultSize, preferredSize)
	.addComponent(phone)
	.addComponent(phoneTextField, preferredSize, defaultSize, preferredSize))
	.addPreferredGap(related)
	.addGroup(customerInfoPanelLayout.createParallelGroup(baseline)
	.addComponent(address)
	.addComponent(addressDropDown, preferredSize, defaultSize, preferredSize))
	.addGap(6, 6, 6))))
	);
	
	 GroupLayout layout = new  GroupLayout(getContentPane());
	getContentPane().setLayout(layout);
	layout.setHorizontalGroup(
	layout.createParallelGroup(leading)
		.addComponent(bottomButtons, defaultSize, defaultSize, Short.MAX_VALUE)
		.addGroup(layout.createSequentialGroup()
		.addGroup(layout.createParallelGroup(trailing, false)
		.addComponent(customerInfoPanel, leading, defaultSize, defaultSize,
				Short.MAX_VALUE)
		.addGroup(leading, layout.createSequentialGroup()
		.addContainerGap()
		.addGroup(layout.createParallelGroup(leading, false)
		.addComponent(top, defaultSize, defaultSize, Short.MAX_VALUE)
		.addComponent(pizzaOptions, preferredSize, defaultSize, preferredSize))
		.addComponent(currentOrderPanel, preferredSize, defaultSize,
				preferredSize)))));
	layout.setVerticalGroup(
		layout.createParallelGroup(leading)
		.addGroup(layout.createSequentialGroup()
		.addComponent(customerInfoPanel, preferredSize, defaultSize, 
				preferredSize)
		.addPreferredGap(related)
		.addGroup(layout.createParallelGroup(leading, false)
		.addGroup(layout.createSequentialGroup()
		.addComponent(top, preferredSize, defaultSize, preferredSize)
		.addPreferredGap(related)
		.addComponent(pizzaOptions, preferredSize, defaultSize, preferredSize))
		.addComponent(currentOrderPanel, preferredSize, defaultSize,
				preferredSize))
		.addPreferredGap(related, defaultSize, Short.MAX_VALUE)
		.addComponent(bottomButtons, preferredSize, defaultSize, 
				preferredSize))
	);
	
	setEnabled(false);
	
	pack();
	}
	
	/**
	 * Sets all the components to the passed value.
	 * 
	 * @override
	 * 		setEnabled - in the Component class
	 * @param
	 * 		enabled - whether everything is enabled or not.
	 */
	public void setEnabled(boolean enabled) {
		for (Component c : pizzaOptions.getComponents()) {
			c.setEnabled(enabled);
		}
		box1f.setEnabled(enabled);
		box1s.setEnabled(enabled);
		box2f.setEnabled(enabled);
		box2s.setEnabled(enabled);
		box3f.setEnabled(enabled);
		box3s.setEnabled(enabled);
		box4f.setEnabled(enabled);
		box4s.setEnabled(enabled);
		box5f.setEnabled(enabled);
		box5s.setEnabled(enabled);
	}

	/**
	 * Find all the items that are currently selected.
	 *
	 * @return
	 * 		int - the names of all the items that are selected
	 */
	public int getSelected() {
		return orderList.getSelectedIndex();	
	}
	
	/**
	 * changes the visibility of the window and resets all of the components to
	 * not selected.
	 * 
	 * @param
	 * 		make_visible - true if the window is to be visible, false otherwise
	 * @param
	 * 		is_it_new_order - false if the button was pressed from
	 * 		PastOrderScreen
	 * @override
	 * 		setVisible() - Component
	 * @return
	 * 		void
	 */
	public void setVisible( boolean make_visible, boolean is_it_new_order) {
		super.setVisible( make_visible );
		if(is_it_new_order) {
			resetFields();
		}
	}
	
	/**
	 * The 'setter' for the address drop down selection
	 *
	 * @param
	 * 		address - the address to make selected
	 * @return
	 * 		void
	 */
	public void setAddressDropDown( String address ) {
		addressDropDown.setSelectedItem( address );
	}
	
	/**
	 * The 'getter' for the address drop down selection
	 *
	 * @return
	 * 		String - The address selected
	 */
	public String getAddressDropDown() {
		return (String) addressDropDown.getSelectedItem();
		
	}
	
	/**
	 * updates the total price label
	 *
	 * @param price   the total price
	 * void
	 */
	public void setTotalPrice(String price){
		
		totalPrice.setText( "Total Price: " + price );
	}
	
	/**
	 * The 'setter' for the text in the phone number text field
	 * 
	 * @param
	 * 		phone_num - the phone number to be set to the text field
	 */
	public void setPhoneTextField( String phone_num) {
		phoneTextField.setText( phone_num );
	}
	
	/**
	 * The 'getter' for the text in the phone number text field.
	 *
	 * @return
	 * 		String - the phone number in the text field
	 */
	public String getPhoneTextField() {
		return phoneTextField.getText();
	}
	
	/**
	 * The 'setter' for the name from the name text field
	 *
	 * @param
	 * 		cust_name - the name to be set in the text field
	 * @return
	 * 		void
	 */
	public void setNameTextField( String cust_name ) {
		nameTextField.setText( cust_name );
	}
	
	/**
	 * The 'getter' for the text in the name text field.
	 *
	 * @return
	 * 		String - the name on the order
	 */
	public String getNameTextField() {
		return nameTextField.getText();
	}

	/**
	 * A 'getter' for the selected address from the drop down menu
	 * 
	 * @return
	 * 		String - the selected address
	 */
	public String getOrderAddress() {
		return (String) addressDropDown.getSelectedItem();
	}
	
	/**
	 * The 'getter' for the last index occupied in the order list
	 *
	 * @return
	 * 		int - The last index of the order list
	 */
	public int getLastListIndex() {
		return orderList.getItemCount() - 1;
	}
	
	/**
	 * Get and return the type of food to be added
	 *
	 * @return
	 * 		String - Type of FoodItem selected.
	 */
	public String getFoodTypeSelection() {
		
		if( pizza.isSelected() ) return "Pizza";
		else if( salad.isSelected() ) return "Salad";
		else if( pizzaLogs.isSelected() ) return "Pizza Logs";
		else return null;
	//TODO this return should actually call a method that outputs an error that
	//there isn't a food item selected.
	}
	
	/**
	 * Get the size of the Pizza.
	 *
	 * @return
	 * 		pizzaSize - the char for the size of the Pizza selected
	 */
	public char getPizzaSizeSelections() {
		char pizzaSize = ' ';

		if( pizza.isSelected() ) {
			if(small.isSelected()) pizzaSize = 'S';
			else if(medium.isSelected()) pizzaSize = 'M';
			else if(large.isSelected()) pizzaSize = 'L';
		}
		return pizzaSize;
	}

	/**
	 * Get all the toppings and return them in a Vector list
	 *
	 * @return
	 * 		selectedToppings - a list of all the toppings selected
	 */
	public Vector<String> getPizzaToppingsSelections() {
		Vector<String> selectedToppings = new Vector<String>();

		//TODO create a hashmap with box#* as the key and the topping_* as the
		//value, then insert that value from the hashmap into the Vector instead
		
		if( pizza.isSelected() ) {
//			if(box1f.isSelected()) selectedToppings.add( box1f.getName() );
//			if(box1s.isSelected()) selectedToppings.add( box1s.getName() );
//			if(box2f.isSelected()) selectedToppings.add( box2f.getName() );
//			if(box2s.isSelected()) selectedToppings.add( box2s.getName() );
//			if(box3f.isSelected()) selectedToppings.add( box3f.getName() );
//			if(box3s.isSelected()) selectedToppings.add( box3s.getName() );
//			if(box4f.isSelected()) selectedToppings.add( box4f.getName() );
//			if(box4s.isSelected()) selectedToppings.add( box4s.getName() );
//			if(box5f.isSelected()) selectedToppings.add( box5f.getName() );
//			if(box5s.isSelected()) selectedToppings.add( box5s.getName() );
			
			if(box1f.isSelected()) selectedToppings.add( "Pepperoni(L)" );
			if(box1s.isSelected()) selectedToppings.add( "Pepperoni(R)" );
			if(box2f.isSelected()) selectedToppings.add( "Sausage(L)" );
			if(box2s.isSelected()) selectedToppings.add( "Sausage(R)" );
			if(box3f.isSelected()) selectedToppings.add( "Onions(L)" );
			if(box3s.isSelected()) selectedToppings.add( "Onions(R)" );
			if(box4f.isSelected()) selectedToppings.add( "Peppers(L)" );
			if(box4s.isSelected()) selectedToppings.add( "Peppers(R)" );
			if(box5f.isSelected()) selectedToppings.add( "Mushrooms(L)");
			if(box5s.isSelected()) selectedToppings.add( "Mushrooms(R)");
		}
		return selectedToppings;
	}
	
	/**
	 * Sets the list items from the given array.
	 * 
	 * @param
	 * 		foods - the vector containing all the items to be added to the list
	 * @return
	 * 		void
	 */
/*	public void setListElements( Vector<FoodItem> foods ) {
		for(Iterator<FoodItem> food_item_it = foods.iterator();
				food_item_it.hasNext();) {
			
			FoodItem the_current_item = food_item_it.next();
			addItemToList( the_current_item.gUIString() );
			
			if(the_current_item.getClass().getName().equals( "Pizza" )) {
					listener.putToppingsIntoList( the_current_item );
			}
		}
	}
*/	

	/**
	 * Adds the given item to the list in a string form. If there is a
	 * selection in the list
	 *
	 * @param
	 * 		new_item - The new item to be added to the list
	 * @return
	 * 		void
	 */
	public void addItemToList( Vector<String> foodOrder ) {
		
		orderList.removeAll();
		for(String line: foodOrder){
			orderList.add( line );
		}
	}
	
	/**
	 * Sets the text fields back to blank.  Needs to be done due to GUI class
	 * objects only changing visibility states and never being reinvoked.
	 *
	 * return
	 * 		void
	 */
	public void resetFields() {
		
		nameTextField.setText( null );
		phoneTextField.setText( null );	
		orderList.removeAll();
		
		salad.setSelected( false );
		pizzaLogs.setSelected( false );
		pizza.setSelected( false );
		small.setSelected( false );
		medium.setSelected( false );
		large.setSelected( false );
		
		box1f.setSelected( false );
		box1s.setSelected( false );
		box2f.setSelected( false );
		box2s.setSelected( false );
		box3f.setSelected( false );
		box3s.setSelected( false );
		box4f.setSelected( false );
		box4s.setSelected( false );
		box5f.setSelected( false );
		box5s.setSelected( false );
	}
	
	/**
	 * changes the drop down to show the current address
	 * when editing an order
	 *
	 * @param currentAddress   the address to be displayed
	 * void
	 */
	public void setAddress(String currentAddress){
		addressDropDown.setSelectedItem( currentAddress );
	}
	
	/**
	 * returns the item that is to be deleted
	 *
	 * @return  the number of the item to be deleted
	 */
	public String getItemToDelete(){
		return toDelete.getText();
	}
	
	/**
	 * ***For testing purposes only***
	 * The driver method for the OrderEditor class
	 * 
	 * @param args
	 *            the command line arguments
	 */
//	public static void main(String args[]) {
//		java.awt.EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				SixAxisController controller = new SixAxisController();
//				new OrderEditor(controller).setVisible(true);
//			}
//		});
//	}

}