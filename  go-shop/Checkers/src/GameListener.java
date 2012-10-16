import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * GameListener class
 * replacement actinlistener pulled from GUI
 */
public class GameListener implements ActionListener {

	private Facade theFacade;
	private CheckerGUI cGUI;
	
	public GameListener(CheckerGUI cGUI, Facade theFacade){
		
		this.cGUI = cGUI;
		this.theFacade = theFacade;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		try {
			// if a square gets clicked
			String action = e.getActionCommand();
			if (action.equals("1") || action.equals("3") || action.equals("5")
					|| action.equals("7") || action.equals("8")
					|| action.equals("10") || action.equals("12")
					|| action.equals("14") || action.equals("17")
					|| action.equals("19") || action.equals("21")
					|| action.equals("23") || action.equals("24")
					|| action.equals("26") || action.equals("28")
					|| action.equals("30") || action.equals("33")
					|| action.equals("35") || action.equals("37")
					|| action.equals("39") || action.equals("40")
					|| action.equals("42") || action.equals("44")
					|| action.equals("46") || action.equals("49")
					|| action.equals("51") || action.equals("53")
					|| action.equals("55") || action.equals("56")
					|| action.equals("58") || action.equals("60")
					|| action.equals("62")) {

				// call selectSpace with the button pressed
				// TODO: Use of Facade, possibly change
				theFacade.selectSpace(Integer.parseInt(action));

				// if draw is pressed
			} else if (action.equals("draw")) {
				// does sequence of events for a draw
				// TODO: Use of Facade, possibly change
				theFacade.pressDraw();

				// if resign is pressed
			} else if (action.equals("resign")) {
				// does sequence of events for a resign
				// TODO: Use of Facade, possibly change
				theFacade.pressQuit();

				// if the source came from the facade
			} else if (e.getSource().equals(theFacade)) {

				// if its a player switch event
				if ((action).equals(Facade.playerSwitch)) {
					// set a new time
					cGUI.setTimeRemaining(theFacade.getTimer());
					// if it is an update event
				} else if ((action).equals(Facade.update)) {
					// update the GUI
					cGUI.update();
				} else {
					throw new Exception("unknown message from facade");
				}
			}
			// catch various Exceptions
		} catch (NumberFormatException excep) {
			System.err
					.println("GUI exception: Error converting a string to a number");
		} catch (NullPointerException exception) {
			System.err.println("GUI exception: Null pointerException "
					+ exception.getMessage());
			exception.printStackTrace();
		} catch (Exception except) {
			System.err.println("GUI exception: other: " + except.getMessage());
			except.printStackTrace();
		}
		
	}

}