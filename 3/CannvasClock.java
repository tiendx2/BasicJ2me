package bai3_clock;

import java.util.Calendar;
import java.util.Date;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;

public class CannvasClock extends MIDlet implements CommandListener {
	Command mStart, mStop;
	SweepCanvas sweeper;

	public void startApp() {
		sweeper = new SweepCanvas();
		mStart = new Command("Start", Command.OK, 0);
		mStop = new Command("Stop", Command.STOP, 0);
		sweeper.addCommand(mStart);
		sweeper.addCommand(mStop);
		sweeper.setCommandListener(this);
		sweeper.start();
		Display.getDisplay(this).setCurrent(sweeper);
	}

	public void pauseApp() {
	}

	public void destroyApp(boolean unconditional) {
	}

	public void commandAction(Command arg0, Displayable arg1) {
		// TODO Auto-generated method stub
		if (arg0 == mStart) {
			sweeper.start();
		} else if (arg0 == mStop) {
			sweeper.stop();
		}

	}

}

class SweepCanvas extends Canvas implements Runnable {
	private boolean mTrucking;

	private int mDelay = 1000;
	String timer="abcde";
	

	
	public SweepCanvas(){
		Date d = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		timer = c.get(Calendar.HOUR_OF_DAY) + ":" + 
			c.get(Calendar.MINUTE) + ":" + 
			c.get(Calendar.SECOND);
	}
	public void start() {
		mTrucking = true;
		Thread t = new Thread(this);
		t.start();
	}

	public void stop() {
		mTrucking = false;
	}

	public void paint(Graphics g) {
		int width = getWidth();
		int height = getHeight();

		g.setGrayScale(255);
		g.fillRect(0, 0, width - 1, height - 1);
		g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE));
		g.setColor(255, 0, 0);
		g.drawString(timer, width/2, height/2,
                Graphics.TOP|Graphics.HCENTER);

	}

	public void run() {
		while (mTrucking) {
			Date d = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			timer = c.get(Calendar.HOUR_OF_DAY) + ":" + 
				c.get(Calendar.MINUTE) + ":" + 
				c.get(Calendar.SECOND);
			
			repaint();
			try {
				Thread.sleep(mDelay);
			} catch (InterruptedException ie) {
			}
		}
	}
}