/*
 * LoginScreen.java
 * 
 * Version:
 *     $Id$
 *     
 * Revision:
 *     $Log$
 */

package GUI;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import control.*;

/**
 * A User interface class that prompts the user for a login name and password.
 * The password text field has the characters hidden for added security.
 * 
 * @author Shaun DeVos Brian Baum Rebecca Dudley Jonathan Johnson Jonathan Olin
 * 
 */
public class LoginScreen extends JFrame {

	// Variables

	/**
	 * yeah.
	 */
	private static final long			serialVersionUID	= 1L;

	private JButton						back;
	private JButton						login;
	private JPanel						loginButtons;
	private JPanel						loginFields;
	private JLabel						password;
	private JPasswordField				passwordTextField;
	private JLabel						userName;
	private JTextField					userNameField;

	/**
	 * ActionListener object to be passed to action oriented buttons
	 */
	private static SixAxisController	listener;

	/**
	 * Constructor for a new form 'Login'
	 */
	public LoginScreen( SixAxisController controller ) {

		listener = controller;
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 */
	private void initComponents() {

		loginFields = new JPanel();
		userName = new JLabel();
		password = new JLabel();
		userNameField = new JTextField();
		passwordTextField = new JPasswordField();

		loginButtons = new JPanel();
		back = new JButton();
		login = new JButton();

		// setUndecorated( true );
		setResizable( false );

		userName.setText( "User Name:" );
		password.setText( "Password:" );
		back.setText( "Back" );
		login.setText( "Login" );
		back.setMnemonic( 'B' );
		login.setMnemonic( 'L' );

		// Send Commands to the controller for respective actions

		userNameField.addActionListener( listener );
		passwordTextField.addActionListener( listener );
		back.addActionListener( listener );
		login.addActionListener( listener );

		GroupLayout loginButtonsLayout = new GroupLayout( loginButtons );
		loginButtons.setLayout( loginButtonsLayout );

		int preferredSize = GroupLayout.PREFERRED_SIZE;
		int defaultSize = GroupLayout.DEFAULT_SIZE;
		GroupLayout.Alignment leading = GroupLayout.Alignment.LEADING;
		LayoutStyle.ComponentPlacement related = LayoutStyle.ComponentPlacement.RELATED;
		GroupLayout.Alignment baseLine = GroupLayout.Alignment.BASELINE;
		GroupLayout.Alignment trailing = GroupLayout.Alignment.TRAILING;

		// Align the Buttons horizontally

		GroupLayout.ParallelGroup loginHOuter = loginButtonsLayout
				.createParallelGroup( leading );
		GroupLayout.SequentialGroup loginHInner = loginButtonsLayout
				.createSequentialGroup();
		loginHInner.addContainerGap().addComponent( back, preferredSize,
				99, preferredSize );
		loginHInner.addPreferredGap( related, 31, Short.MAX_VALUE );
		loginHInner.addComponent( login, preferredSize, 99, preferredSize );
		loginHOuter.addGroup( loginHInner );
		loginButtonsLayout.setHorizontalGroup( loginHOuter );

		// Align the Buttons Vertically

		GroupLayout.SequentialGroup outerVButtons = loginButtonsLayout
				.createSequentialGroup();
		GroupLayout.ParallelGroup innerVButtons = loginButtonsLayout
				.createParallelGroup( baseLine );
		innerVButtons.addComponent( back, defaultSize, 32, Short.MAX_VALUE );
		innerVButtons.addComponent( login, defaultSize, 32, Short.MAX_VALUE );
		outerVButtons.addGroup( innerVButtons );
		loginButtonsLayout.setVerticalGroup( outerVButtons );

		// align labels and textfields horizontally

		GroupLayout loginFieldsLayout = new GroupLayout( loginFields );
		loginFields.setLayout( loginFieldsLayout );
		GroupLayout.ParallelGroup loginHFOuter = loginFieldsLayout
				.createParallelGroup( leading );
		loginHFOuter.addComponent( loginButtons, leading, defaultSize,
				defaultSize, Short.MAX_VALUE );
		GroupLayout.SequentialGroup loginHFInner = loginFieldsLayout
				.createSequentialGroup().addContainerGap();
		GroupLayout.ParallelGroup usePassHLabel = loginFieldsLayout
				.createParallelGroup( leading );
		usePassHLabel.addComponent( userName ).addComponent( password );
		loginHFInner.addGap( 22 );
		loginHFInner.addGroup( loginFieldsLayout.createSequentialGroup() );
		loginHFInner.addGroup( usePassHLabel.addGap( 80 ) );
		GroupLayout.ParallelGroup usePassHField = loginFieldsLayout
				.createParallelGroup( leading, false );
		usePassHField.addComponent( passwordTextField ).addComponent(
				userNameField, defaultSize, 187, Short.MAX_VALUE );
		loginHFInner.addGroup( usePassHField );
		loginHFInner.addContainerGap( defaultSize, Short.MAX_VALUE );
		loginFieldsLayout.setHorizontalGroup( loginHFOuter.addGroup( leading,
				loginHFInner ) );

		// align labels and textfields vertically

		GroupLayout.ParallelGroup loginVFOuter = loginFieldsLayout
				.createParallelGroup( leading );
		GroupLayout.SequentialGroup loginVFInner = loginFieldsLayout
				.createSequentialGroup();
		GroupLayout.ParallelGroup user = loginFieldsLayout
				.createParallelGroup( baseLine );
		user.addComponent( userName ).addComponent( userNameField,
				preferredSize, defaultSize, preferredSize );
		loginVFInner.addContainerGap().addGroup( user );
		loginVFInner.addPreferredGap( related );
		GroupLayout.ParallelGroup pass = loginFieldsLayout
				.createParallelGroup( baseLine );
		pass.addComponent( password ).addComponent( passwordTextField,
				preferredSize, defaultSize, preferredSize );
		loginVFInner.addGroup( pass );
		loginVFInner.addPreferredGap( LayoutStyle.ComponentPlacement.UNRELATED );
		loginVFInner.addComponent( loginButtons, preferredSize, 47,
				preferredSize );
		loginVFOuter.addGroup( loginVFInner );
		loginFieldsLayout.setVerticalGroup( loginVFOuter );

		// align all components horizontally

		GroupLayout layout = new GroupLayout( getContentPane() );
		getContentPane().setLayout( layout );
		GroupLayout.SequentialGroup horizComps = layout.createSequentialGroup();
		horizComps.addComponent( loginFields, preferredSize, defaultSize,
				preferredSize );
		horizComps.addContainerGap( defaultSize, Short.MAX_VALUE );
		layout.setHorizontalGroup( horizComps );

		// align all components vertically

		GroupLayout.ParallelGroup vertComps = layout
				.createParallelGroup( leading );
		vertComps.addComponent( loginFields, preferredSize, defaultSize,
				preferredSize );
		layout.setVerticalGroup( vertComps );

		pack();
	}

	/**
	 * Returns the user name entered
	 * 
	 * @return The entered user name
	 */
	public String getUserName() {
		return userNameField.getText();
	}

	/**
	 * Returns the password of the user after entered.
	 * 
	 * @return The contents of the text field
	 */
	public char[] getPassword() {
		char[] password = passwordTextField.getPassword();
		return password;
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
		resetTextFields();
	}

	/**
	 * Sets the text fields back to blank. Needs to be done due to GUI class
	 * objects only changing visibility states and never being reinvoked.
	 * 
	 * return void
	 */
	public void resetTextFields() {
		userNameField.setText( null );
		passwordTextField.setText( null );
	}

	// /**
	// * @param args
	// * the command line arguments
	// */
	// public static void main( String args[] ) {
	// java.awt.EventQueue.invokeLater( new Runnable() {
	//
	// public void run() {
	// SixAxisController controller = new SixAxisController();
	// MainGUIPage main_page = new MainGUIPage( controller );
	// LoginScreen login_screen = new LoginScreen( controller );
	// controller.setGUIs( main_page, login_screen, null, null );
	// login_screen.setVisible( true );
	// }
	// } );
	// }
}