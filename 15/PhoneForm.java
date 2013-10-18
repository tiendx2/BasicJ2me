package bai15_PhoneNumber;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class PhoneForm extends MIDlet implements CommandListener {

	private Display mDisplay;
	private Form mForm;
	private TextField txtInput;
	private StringItem strInfor;
	private Command cmExit, cmCheck;

	public PhoneForm() {
		// TODO Auto-generated constructor stub
		mForm = new Form("Check Area Code");
		txtInput = new TextField("Phone Number", "", 30, TextField.NUMERIC);
		strInfor = new StringItem("", "");
		cmExit = new Command("Exit", Command.EXIT, 1);
		cmCheck = new Command("Check", Command.OK, 1);
		mForm.append(txtInput);
		mForm.append(strInfor);
		mForm.addCommand(cmExit);
		mForm.addCommand(cmCheck);
		mForm.setCommandListener(this);

	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		mDisplay = Display.getDisplay(this);
		mDisplay.setCurrent(mForm);

	}
	// Method convert char to int
	public int convertChar(char c){
		switch (c) {
		case '0':
			return 0;
		case '1':
			return 1;
		case '2':
			return 2;
		case '3':
			return 3;
		case '4':
			return 4;
		case '5':
			return 5;
		case '6':
			return 6;
		case '7':
			return 7;
		case '8':
			return 8;
		case '9':
			return 9;
		default:
			return -1;
		}
	}
	public boolean check(String number) {
		int b = 0;
		char[] arr = number.toCharArray();
		int arrMavung[] = {convertChar(arr[0]),convertChar(arr[1])};
		System.out.println(arrMavung[0]+" va "+arrMavung[1]);
		// Kiem tra do dai
		if (arr.length == 9)
			b = b + 1;
		// Kiem tra ma vung
		if (arrMavung[0] == 0
				&& (arrMavung[1] == 3 || arrMavung[1] == 4 || arrMavung[1] == 7 || arrMavung[1] == 8)) {
			b = b + 1;
		}

		if (b == 2)
			return true;
		else
			return false;
	}

	public void commandAction(Command com, Displayable arg1) {
		// TODO Auto-generated method stub
		if (com == cmExit) {
			notifyDestroyed();
		} else if (com == cmCheck) {
			if (check(txtInput.getString()) == true) {
				strInfor.setText("Massage: Area code is correct ");
			} else {
				strInfor.setText("Massage: Area code is not correct ");
			}
		}

	}

}
