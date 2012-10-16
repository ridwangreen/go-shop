import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/*
 * ActionListener for the SecondScreen
 * Pulled from SecondScreen
 * 
 */
public class SSListener implements ActionListener, ChangeListener {

	private Facade theFacade;
	private Secondscreen sS;
	private JLabel WarningLengthLabel;
	private JSlider warningLengthField;
	private JSlider turnLengthField;

	public SSListener(Secondscreen sS, Facade theFacade) {

		this.theFacade = theFacade;
		this.sS = sS;
		warningLengthField = sS.getSlider(1);
		turnLengthField = sS.getSlider(2);
		WarningLengthLabel = sS.getLabel(1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		try {

			JTextField playerOneField = sS.getTextField(1);
			JTextField playerTwoField = sS.getTextField(2);
			JCheckBox timedGameBox = sS.getCheckBox();

			if ((e.getActionCommand()).equals("ok")) {

				// take note of all selections and go to game startup

				// Set the player names
				if (playerOneField.isEnabled()) {
					if (!((playerOneField.getText()).equalsIgnoreCase(""))) {
						theFacade.setPlayerName(1, playerOneField.getText());
					}
				}

				if (playerTwoField.isEnabled()) {
					if (!((playerTwoField.getText()).equalsIgnoreCase(""))) {
						theFacade.setPlayerName(2, playerTwoField.getText());
					}
				}

				// if a timer is desired
				if (timedGameBox.isEnabled()) {
					if (timedGameBox.isEnabled()) {

						// set the 2 timer values
						try {

							theFacade.setTimer(turnLengthField.getValue(),
									warningLengthField.getValue());

						} catch (Exception x) {

							JOptionPane.showMessageDialog(null,
									"Invalid Timer value(s)", "Error",
									JOptionPane.INFORMATION_MESSAGE);
						}
						// else set timer values to a no timer constant
					} else {
						theFacade.setTimer(-1, -1);
					}
				} else {
					theFacade.setTimer(-1, -1);
				}

				// start the game
				theFacade.startGame();

				// if they hit cancel go to the previous screen
			} else if (e.getActionCommand().equals("cancel")) {

				sS.setVisible(false);
				theFacade.startUI();

				// handle whether or not a timer is desired
			} else if (e.getSource() instanceof Checkbox) {

				if (timedGameBox.isEnabled()) {
					turnLengthField.setEnabled(true);
					warningLengthField.setEnabled(true);
				} else {
					turnLengthField.setEnabled(false);
					warningLengthField.setEnabled(false);
				}
			}

			// TODO: Super bad practice
		} catch (Exception x) {
			x.printStackTrace();
		}

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource().equals(turnLengthField)) {
			sS.getLabel(2).setText("Turn Length ( "
					+ turnLengthField.getValue() + " seconds )");
		} else if (e.getSource().equals(warningLengthField)) {
			WarningLengthLabel.setText("Warning Length ( "
					+ warningLengthField.getValue() + " seconds )");
		}

	}

}
