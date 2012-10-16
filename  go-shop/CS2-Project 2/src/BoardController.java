import java.awt.AWTEvent;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class BoardController implements KeyListener, AWTEventListener {

	private BoardView gameFrame;
	private FrenzyModel model;

	public BoardController(BoardView view, FrenzyModel model) {

		gameFrame = view;
		this.model = model;

	}

	/**
	 * Invoked when an event is dispatched in the AWT.
	 * 
	 * @param event
	 *            - the dispatched event
	 */
	public void eventDispatched(AWTEvent event) {

		if (event instanceof KeyEvent) {
			KeyEvent kEvent = (KeyEvent) event;
			// relay key event to all registered key listeners
			for (KeyListener kl : gameFrame.getFrame().getKeyListeners()) {
				switch (kEvent.getID()) {
				case KeyEvent.KEY_PRESSED:
					kl.keyPressed(kEvent);
					break;
				case KeyEvent.KEY_RELEASED:
					kl.keyReleased(kEvent);
					break;
				case KeyEvent.KEY_TYPED:
					kl.keyTyped(kEvent);
					break;
				}
			}
		}
	}

	/**
	 * Invoked when a key has been pressed.
	 * 
	 * @param e
	 *            - the event object
	 */
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:

			model.movePlayer(1);
			break;

		case KeyEvent.VK_DOWN:
			model.movePlayer(2);
			break;

		case KeyEvent.VK_LEFT:
			model.movePlayer(4);
			break;
			
		case KeyEvent.VK_RIGHT:
			model.movePlayer(3);
			break;
			
		default:
			System.out.println("other key: " + e.getKeyChar());
		}
		gameFrame.updateView();

	}

	/**
	 * Invoked when a key has been released.
	 * 
	 * @param e
	 *            - the event object
	 */
	public void keyReleased(KeyEvent e) {

	}

	/**
	 * Invoked when a key has been typed.
	 * 
	 * @param e
	 *            - the event object
	 */
	public void keyTyped(KeyEvent e) {

	}

}
