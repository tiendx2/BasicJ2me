package bai14_generic;

/*--------------------------------------------------
 * KeyCodes.java
 *
 * Canvas for processing key code and commands
 *
 * Example from the book:     Core J2ME Technology
 * Copyright John W. Muchow   http://www.CoreJ2ME.com
 * You may use/modify for any non-commercial purpose
 *-------------------------------------------------*/
import java.util.Random;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class RandomNumber extends MIDlet {
	private Display display; // The display
	private KeyCodeCanvas canvas; // Canvas

	public RandomNumber() {
		display = Display.getDisplay(this);
		canvas = new KeyCodeCanvas(this);
	}

	protected void startApp() {
		display.setCurrent(canvas);
	}

	protected void pauseApp() {
	}

	protected void destroyApp(boolean unconditional) {
	}

	public void exitMIDlet() {
		destroyApp(true);
		notifyDestroyed();
	}
}

/*--------------------------------------------------
 * Class KeyCodeCanvas
 *
 * Key codes and commands for event handling
 *-------------------------------------------------*/
class KeyCodeCanvas extends Canvas implements CommandListener {
	private Command cmExit,cmGen; // Exit midlet
	private int numRandom = 0; // Key code text
	private RandomNumber midlet;

	/*--------------------------------------------------
	 * Constructor
	 *-------------------------------------------------*/
	public KeyCodeCanvas(RandomNumber midlet) {
		this.midlet = midlet;

		// Create exit command & listen for events
		cmExit = new Command("Exit", Command.EXIT, 1);
		cmGen = new Command("Gen", Command.OK, 1);
		addCommand(cmExit);
		addCommand(cmGen);
		setCommandListener(this);
	}

	/*--------------------------------------------------
	 * Paint the text representing the key code 
	 *-------------------------------------------------*/
	protected void paint(Graphics g) {
		// Clear the background (to white)
		g.setColor(255, 255, 255);
		g.fillRect(0, 0, getWidth(), getHeight());

			// Draw with black pen
			g.setColor(0, 0, 0);
			// Close to the center
			g.drawString("Generate random number:"+numRandom, 0, 0,0);
	}

	/*--------------------------------------------------
	 * Command event handling
	 *-------------------------------------------------*/
	public void commandAction(Command c, Displayable d) {
		if (c == cmExit)
			midlet.exitMIDlet();
		else if(c==cmGen){
			Random rn = new Random();
			numRandom=rn.nextInt(100);
			repaint();
		}
	}

}
