package bai8_slideShow;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDlet;

public class CannvasAmbul extends MIDlet implements CommandListener {
	Command mStart, mStop, mExit;
	SweepCanvas sweeper;

	public void startApp() {
		try {
			sweeper = new SweepCanvas();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mStart = new Command("Start", Command.OK, 0);
		mStop = new Command("Stop", Command.STOP, 0);
		mExit = new Command("Exit", Command.EXIT, 1);
		sweeper.addCommand(mStart);
		sweeper.addCommand(mStop);
		sweeper.addCommand(mExit);
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
		} else if (arg0 == mExit) {
			notifyDestroyed();
		}

	}

}

class SweepCanvas extends Canvas implements Runnable {
	private boolean mTrucking;

	private int mDelay = 3000;
	Image imgCurrent=Image.createImage("/img5.jpg");;
	int i=0;
	Image [] slide = new Image[5];
	public SweepCanvas() throws IOException {
		slide[0]=Image.createImage("/img1.jpg");
		slide[1]=Image.createImage("/img2.jpg");
		slide[2]=Image.createImage("/img3.jpg");
		slide[3]=Image.createImage("/img4.jpg");
		slide[4]=Image.createImage("/img5.jpg");
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
		g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN,
				Font.SIZE_LARGE));
		g.setColor(255, 0, 0);
		g.drawImage(imgCurrent, 40, 40, Graphics.TOP | Graphics.LEFT);

	}

	public void run() {
		while (mTrucking) {
			i=i%5;
			imgCurrent=slide[i];
			repaint();
			try {
				Thread.sleep(mDelay);
			} catch (InterruptedException ie) {

			}
			i=i+1;
		}
	}
}