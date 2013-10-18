import java.io.IOException;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class KeyCodes extends MIDlet {
	private Display display; // The display
	private KeyCodeCanvas canvas; // Canvas

	public KeyCodes() {
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
	private Command cmExit; // Exit midlet
	private String keyText = null; // Key code text
	private KeyCodes midlet;

	Image image = null;
	private int mX = this.getWidth() / 2, mY = this.getHeight() / 2;

	/*--------------------------------------------------
	 * Constructor
	 *-------------------------------------------------*/
	public KeyCodeCanvas(KeyCodes midlet) {
		this.midlet = midlet;

		// Create exit command & listen for events
		cmExit = new Command("Exit", Command.EXIT, 1);
		addCommand(cmExit);
		try {
			image = Image.createImage("/nosebleed.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setCommandListener(this);
	}

	/*--------------------------------------------------
	 * Paint the text representing the key code 
	 *-------------------------------------------------*/
	protected void paint(Graphics g) {
		// Clear the background (to white)
		g.setColor(255, 255, 255);
		g.fillRect(0, 0, getWidth(), getHeight());

		// Set color and draw text
		// Draw with black pen
		g.setColor(0, 0, 0);
		// Close to the center
		g.drawImage(image, mX, mY, Graphics.HCENTER | Graphics.VCENTER);
	}

	/*--------------------------------------------------
	 * Command event handling
	 *-------------------------------------------------*/
	public void commandAction(Command c, Displayable d) {
		if (c == cmExit)
			midlet.exitMIDlet();
	}

	/*--------------------------------------------------
	 * Key code event handling
	 *-------------------------------------------------*/
	protected void keyPressed(int keyCode) {
		int h=image.getHeight()/2;
		int w=image.getWidth()/2;
		if (keyCode == -1) {
			if (this.mY > h)
				this.mY = this.mY - 5;
		} else if (keyCode == -2) {
			if (this.mY < getHeight()-h)
				this.mY = this.mY + 5;
		} else if (keyCode == -3) {
			if (this.mX > w)
				this.mX = this.mX - 5;
		} else if (keyCode == -4) {
			if (this.mX < getWidth()-w)
				this.mX = this.mX + 5;
		}
		repaint();
	}
}