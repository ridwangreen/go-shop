import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
 * Action listener for the FirstScreen
 * Pulled from Firstscreem
 */
public class FSListener implements ActionListener {

	private Facade theFacade;
	private Firstscreen fS;

	public FSListener(Firstscreen fS, Facade theFacade) {
		this.theFacade = theFacade;
		this.fS = fS;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		try {
			JTextField IPField = fS.getField();
			// this code handles disabling the IP field unless
			// the join game radio button is selected
			if ((e.getActionCommand()).equals("join")) {
				IPField.setEnabled(true);
			} else if ((e.getActionCommand()).equals("local")) {
				IPField.setEnabled(false);
			} else if ((e.getActionCommand()).equals("host")) {
				IPField.setEnabled(false);

				// this next if statement takes care of when the
				// OK button is selected and goes to the second
				// screen settign the desired options

			} else if ((e.getActionCommand()).equals("ok")) {

				ButtonGroup gameModes = fS.getGroup();
				// a temporary button to use for determining the game type
				ButtonModel tempButton = gameModes.getSelection();

				// if check to see of the local radio button is selected
				if (tempButton.getActionCommand().equals("local")) {

					// set up a local game
					theFacade.startLocalGame();

					// if the host game button is selected
				} else if (tempButton.getActionCommand().equals("host")) {

					// set up to host a game
					// TODO: Call to Facade, possibly change
					theFacade.startHostGame();

					// if the join game button is selected
				} else if (tempButton.getActionCommand().equals("join")) {

					// try to connect
					try {

						// create a URL from the IP address in the IPfield
						URL address = new URL("http://" + IPField.getText());
						// set the host
						theFacade.setHost(address);

						// set up to join a game
						theFacade.startClientGame();

						// catch any exceptions
					} catch (MalformedURLException x) {
						JOptionPane.showMessageDialog(null,
								"Invalid host address", "Error",
								JOptionPane.INFORMATION_MESSAGE);
					}// end of networking catch statement

					// set up to connect to another person
				}

				// if they hit cancel exit the game
			} else if (e.getActionCommand().equals("cancel")) {
				System.exit(0);
			}

		} catch (Exception x) {
			System.err.println(x.getMessage());
		}// end of general catch statement

	}// end of actionPerformed

}
