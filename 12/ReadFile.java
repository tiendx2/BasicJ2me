package bai12_Find;

import java.io.InputStream;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDlet;

public class ReadFile extends MIDlet implements CommandListener {
	private Display display;
	private Form form;
	private Command read, exit;
	private Alert alert;

	public ReadFile() {
		display = Display.getDisplay(this);
		read = new Command("Read", Command.SCREEN, 1);
		exit = new Command("Exit", Command.EXIT, 1);
		form = new Form("Read File");
		form.addCommand(exit);
		form.addCommand(read);
		form.setCommandListener(this);
	}

	public void startApp() {
		display.setCurrent(form);
	}

	public void pauseApp() {
	}

	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	public void commandAction(Command c, Displayable s) {
		String label = c.getLabel();
		if (label.equals("Read")) {
			String string = file();
			if (string != null) {
				alert = new Alert("Reading", string, null, null);
				alert.setTimeout(Alert.FOREVER);
				display.setCurrent(alert, form);
			}
		} else if (label.equals("Exit")) {
			destroyApp(false);
		}
	}

	public static String[] spitString(String str) {
		char arr[] = str.toCharArray();
		int i = 0, d = 0;
		for (i = 0; i < arr.length; i++) {
			if (arr[i] == ',') {
				d = d + 1;
			}
		}
		String arrString[] = new String[d + 1];
		int start = 0;
		int end;
		i = 0;
		for (end = 0; end < arr.length; end++) {
			if (arr[end] == ',') {
				arrString[i] = str.substring(start, end);
				i = i + 1;
				start = end+1;
			}
		}
		arrString[i] = str.substring(start, end);
		i = i + 1;

		return arrString;
	}

	private String file() {
		InputStream is = getClass().getResourceAsStream("/help.csv");
		StringBuffer sb = new StringBuffer();
		int i = 0;
		try {
			int chars;
			while ((chars = is.read()) != -1) {
					sb.append((char) chars);
			}
			return sb.toString();
		} catch (Exception e) {
		}

		return null;
	}
}