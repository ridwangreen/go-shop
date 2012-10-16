import java.awt.Component;

/*
 * order.java
 *
 * Created on __DATE__, __TIME__
 */

/**
 * 
 * @author __USER__
 */
public class OrderEditor extends javax.swing.JFrame {

	/** Creates new form order */
	public OrderEditor() {
		initComponents();
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

optionsButtons = new javax.swing.ButtonGroup();
sizeOptions = new javax.swing.ButtonGroup();
top = new javax.swing.JPanel();
options = new javax.swing.JPanel();
salad = new javax.swing.JRadioButton();
pizzaLogs = new javax.swing.JRadioButton();
pizza = new javax.swing.JRadioButton();
pizzaOptions = new javax.swing.JPanel();
size = new javax.swing.JLabel();
small = new javax.swing.JRadioButton();
medium = new javax.swing.JRadioButton();
large = new javax.swing.JRadioButton();
toppings = new javax.swing.JLabel();
half = new javax.swing.JLabel();
first = new javax.swing.JLabel();
second = new javax.swing.JLabel();
pepperoni = new javax.swing.JLabel();
sausage = new javax.swing.JLabel();
onions = new javax.swing.JLabel();
peppers = new javax.swing.JLabel();
mushrooms = new javax.swing.JLabel();
jPanel1 = new javax.swing.JPanel();
box1s = new javax.swing.JCheckBox();
box1f = new javax.swing.JCheckBox();
box2f = new javax.swing.JCheckBox();
box2s = new javax.swing.JCheckBox();
box3f = new javax.swing.JCheckBox();
box3s = new javax.swing.JCheckBox();
box4f = new javax.swing.JCheckBox();
box4s = new javax.swing.JCheckBox();
box5f = new javax.swing.JCheckBox();
box5s = new javax.swing.JCheckBox();
currentOrderPanel = new javax.swing.JPanel();
currentOrderList = new javax.swing.JScrollPane();
orderList = new javax.swing.JList();
currentOrder = new javax.swing.JLabel();
bottomButtons = new javax.swing.JPanel();
back = new javax.swing.JButton();
addToOrder = new javax.swing.JButton();
deleteFromOrder = new javax.swing.JButton();
placeOrder = new javax.swing.JButton();
jPanel2 = new javax.swing.JPanel();
name = new javax.swing.JLabel();
nameTextField = new javax.swing.JTextField();
phone = new javax.swing.JLabel();
phoneTextField = new javax.swing.JTextField();
address = new javax.swing.JLabel();
addressTextField = new javax.swing.JTextField();
pastOrders = new javax.swing.JButton();

setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

optionsButtons.add(salad);
salad.setText("Salad");
salad.addActionListener(new java.awt.event.ActionListener() {
	public void actionPerformed(java.awt.event.ActionEvent evt) {
		setEnabled(false);
	}
});

optionsButtons.add(pizzaLogs);
pizzaLogs.setText("Pizza Logs");
pizzaLogs.addActionListener(new java.awt.event.ActionListener() {
	public void actionPerformed(java.awt.event.ActionEvent evt) {
		setEnabled(false);
	}
});

optionsButtons.add(pizza);
pizza.setText("Pizza");
pizza.addActionListener(new java.awt.event.ActionListener() {
	public void actionPerformed(java.awt.event.ActionEvent evt) {
		setEnabled(true);
	}
});


javax.swing.GroupLayout optionsLayout = new javax.swing.GroupLayout(options);
options.setLayout(optionsLayout);
optionsLayout.setHorizontalGroup(
optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(optionsLayout.createSequentialGroup()
.addContainerGap()
.addGroup(optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(salad)
.addComponent(pizzaLogs)
.addComponent(pizza))
.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
);
optionsLayout.setVerticalGroup(
optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(optionsLayout.createSequentialGroup()
.addContainerGap()
.addComponent(salad)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
.addComponent(pizzaLogs)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
.addComponent(pizza)
.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
);

javax.swing.GroupLayout topLayout = new javax.swing.GroupLayout(top);
top.setLayout(topLayout);
topLayout.setHorizontalGroup(
topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(topLayout.createSequentialGroup()
.addComponent(options, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addContainerGap(143, Short.MAX_VALUE))
);
topLayout.setVerticalGroup(
topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(topLayout.createSequentialGroup()
.addComponent(options, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addContainerGap(14, Short.MAX_VALUE))
);

pizzaOptions.setRequestFocusEnabled(false);

size.setText("Size:");

sizeOptions.add(small);
small.setText("Small");

sizeOptions.add(medium);
medium.setText("Medium");

sizeOptions.add(large);
large.setText("Large");

toppings.setText("Toppings:");

half.setText("Half:");

first.setText("First");

second.setText("Second");

pepperoni.setText("Pepperoni");

sausage.setText("Sausage");

onions.setText("Onions");

peppers.setText("Peppers");

mushrooms.setText("Mushrooms");

javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
jPanel1.setLayout(jPanel1Layout);
jPanel1Layout.setHorizontalGroup(
jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()
.addContainerGap()
.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()
.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(box1f)
.addComponent(box2f)
.addComponent(box3f)
.addComponent(box4f))
.addGap(41, 41, 41)
.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(box4s)
.addComponent(box5s)
.addComponent(box3s)
.addComponent(box2s)
.addComponent(box1s)))
.addComponent(box5f))
.addContainerGap(36, Short.MAX_VALUE))
);
jPanel1Layout.setVerticalGroup(
jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()
.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()
.addComponent(box1f)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(box2f)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(box3f))
.addGroup(jPanel1Layout.createSequentialGroup()
.addComponent(box1s)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(box2s)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(box3s)))
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
.addGroup(jPanel1Layout.createSequentialGroup()
.addComponent(box4f)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 3, Short.MAX_VALUE)
.addComponent(box5f))
.addGroup(jPanel1Layout.createSequentialGroup()
.addComponent(box4s)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 3, Short.MAX_VALUE)
.addComponent(box5s))))
);

javax.swing.GroupLayout pizzaOptionsLayout = new javax.swing.GroupLayout(pizzaOptions);
pizzaOptions.setLayout(pizzaOptionsLayout);
pizzaOptionsLayout.setHorizontalGroup(
pizzaOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(pizzaOptionsLayout.createSequentialGroup()
.addGroup(pizzaOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(pizzaOptionsLayout.createSequentialGroup()
.addContainerGap()
.addGroup(pizzaOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(size)
.addGroup(pizzaOptionsLayout.createSequentialGroup()
.addComponent(small)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
.addGroup(pizzaOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
.addGroup(pizzaOptionsLayout.createSequentialGroup()
.addComponent(medium)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
.addComponent(large))
.addGroup(javax.swing.GroupLayout.Alignment.LEADING, pizzaOptionsLayout.createSequentialGroup()
.addGroup(pizzaOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
.addComponent(first)
.addComponent(toppings))
.addGap(38, 38, 38)
.addComponent(second))))
.addGroup(pizzaOptionsLayout.createSequentialGroup()
.addGroup(pizzaOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(onions)
.addComponent(peppers)
.addComponent(mushrooms))
.addGap(23, 23, 23)
.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
.addGroup(pizzaOptionsLayout.createSequentialGroup()
.addContainerGap()
.addComponent(pepperoni))
.addGroup(pizzaOptionsLayout.createSequentialGroup()
.addContainerGap()
.addComponent(sausage))
.addGroup(pizzaOptionsLayout.createSequentialGroup()
.addGap(31, 31, 31)
.addComponent(half)))
.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
);
pizzaOptionsLayout.setVerticalGroup(
pizzaOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pizzaOptionsLayout.createSequentialGroup()
.addComponent(size)
.addGap(9, 9, 9)
.addGroup(pizzaOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(small)
.addComponent(medium)
.addComponent(large))
.addGap(30, 30, 30)
.addGroup(pizzaOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
.addGroup(pizzaOptionsLayout.createSequentialGroup()
.addGap(30, 30, 30)
.addComponent(half)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
.addGroup(pizzaOptionsLayout.createSequentialGroup()
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(toppings)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
.addGroup(pizzaOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(second)
.addComponent(first))
.addGap(18, 18, 18)))
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addGroup(pizzaOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(pizzaOptionsLayout.createSequentialGroup()
.addComponent(pepperoni)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(sausage)
.addGap(6, 6, 6)
.addComponent(onions)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(peppers)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(mushrooms))
.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
.addContainerGap())
);


currentOrderList.setViewportView(orderList);

currentOrder.setText("Current Order:");

javax.swing.GroupLayout currentOrderPanelLayout = new javax.swing.GroupLayout(currentOrderPanel);
currentOrderPanel.setLayout(currentOrderPanelLayout);
currentOrderPanelLayout.setHorizontalGroup(
currentOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(currentOrderPanelLayout.createSequentialGroup()
.addGroup(currentOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(currentOrderPanelLayout.createSequentialGroup()
.addContainerGap()
.addComponent(currentOrder))
.addComponent(currentOrderList, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
);
currentOrderPanelLayout.setVerticalGroup(
currentOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(currentOrderPanelLayout.createSequentialGroup()
.addComponent(currentOrder)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(currentOrderList, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
.addContainerGap())
);


back.setText("Back");

addToOrder.setText("Add Item");

deleteFromOrder.setText("Delete Item");

placeOrder.setText("Place Order");

javax.swing.GroupLayout bottomButtonsLayout = new javax.swing.GroupLayout(bottomButtons);
bottomButtons.setLayout(bottomButtonsLayout);
bottomButtonsLayout.setHorizontalGroup(
bottomButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(bottomButtonsLayout.createSequentialGroup()
.addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
.addGap(0, 0, 0)
.addComponent(addToOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
.addGap(0, 0, 0)
.addComponent(deleteFromOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
.addGap(0, 0, 0)
.addComponent(placeOrder, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
);
bottomButtonsLayout.setVerticalGroup(
bottomButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(bottomButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(placeOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
.addComponent(addToOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(deleteFromOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
);

name.setText("Name:");

phone.setText("Phone:");

address.setText("Address:");

pastOrders.setText("Past Orders");

javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
jPanel2.setLayout(jPanel2Layout);
jPanel2Layout.setHorizontalGroup(
jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel2Layout.createSequentialGroup()
.addContainerGap()
.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
.addGroup(jPanel2Layout.createSequentialGroup()
.addComponent(address)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(addressTextField))
.addGroup(jPanel2Layout.createSequentialGroup()
.addComponent(name)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(phone)
.addGap(5, 5, 5)
.addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
.addGap(18, 18, 18)
.addComponent(pastOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
.addContainerGap(22, Short.MAX_VALUE))
);
jPanel2Layout.setVerticalGroup(
jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel2Layout.createSequentialGroup()
.addContainerGap()
.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel2Layout.createSequentialGroup()
.addComponent(pastOrders, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
.addContainerGap())
.addGroup(jPanel2Layout.createSequentialGroup()
.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(name)
.addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(phone)
.addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(address)
.addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addGap(6, 6, 6))))
);

javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
getContentPane().setLayout(layout);
layout.setHorizontalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(bottomButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
.addGroup(layout.createSequentialGroup()
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
.addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
.addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
.addContainerGap()
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
.addComponent(top, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
.addComponent(pizzaOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(currentOrderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
);
layout.setVerticalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
.addGroup(layout.createSequentialGroup()
.addComponent(top, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(pizzaOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addComponent(currentOrderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
.addComponent(bottomButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
);

setEnabled(false);

pack();
}// </editor-fold>


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
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new OrderEditor().setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JLabel address;
	private javax.swing.JTextField addressTextField;
	private javax.swing.JButton back;
	private javax.swing.JPanel bottomButtons;
	private javax.swing.JCheckBox box1f;
	private javax.swing.JCheckBox box1s;
	private javax.swing.JCheckBox box2f;
	private javax.swing.JCheckBox box2s;
	private javax.swing.JCheckBox box3f;
	private javax.swing.JCheckBox box3s;
	private javax.swing.JCheckBox box4f;
	private javax.swing.JCheckBox box4s;
	private javax.swing.JCheckBox box5f;
	private javax.swing.JCheckBox box5s;
	private javax.swing.JLabel currentOrder;
	private javax.swing.JScrollPane currentOrderList;
	private javax.swing.JPanel currentOrderPanel;
	private javax.swing.JButton deleteFromOrder;
	private javax.swing.JLabel first;
	private javax.swing.JLabel half;
	private javax.swing.JButton addToOrder;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JRadioButton large;
	private javax.swing.JRadioButton medium;
	private javax.swing.JLabel mushrooms;
	private javax.swing.JLabel name;
	private javax.swing.JTextField nameTextField;
	private javax.swing.JLabel onions;
	private javax.swing.JPanel options;
	private javax.swing.ButtonGroup optionsButtons;
	private javax.swing.JList orderList;
	private javax.swing.JButton pastOrders;
	private javax.swing.JLabel pepperoni;
	private javax.swing.JLabel peppers;
	private javax.swing.JLabel phone;
	private javax.swing.JTextField phoneTextField;
	private javax.swing.JRadioButton pizza;
	private javax.swing.JRadioButton pizzaLogs;
	private javax.swing.JPanel pizzaOptions;
	private javax.swing.JButton placeOrder;
	private javax.swing.JRadioButton salad;
	private javax.swing.JLabel sausage;
	private javax.swing.JLabel second;
	private javax.swing.JLabel size;
	private javax.swing.ButtonGroup sizeOptions;
	private javax.swing.JRadioButton small;
	private javax.swing.JPanel top;
	private javax.swing.JLabel toppings;
	// End of variables declaration//GEN-END:variables

}