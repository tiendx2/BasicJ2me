import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class Converter extends MIDlet implements CommandListener {

	public List select;
	Form[] mform = new Form[5];
	Display mDisplay;
	TextField txtMile, txtMeter, txtUSD, txtVND, txtHour, txtSecond, txtF,
			txtC, txtGallon, txtLiter;

	Command cmExit, cmSelected, cmOk, cmBack;

	public void createCommand() {
		cmBack = new Command("Back", Command.BACK, 1);
		cmSelected = new Command("Select", Command.OK, 1);
		cmExit = new Command("Exit", Command.EXIT, 1);
		cmOk = new Command("Ok", Command.OK, 1);
	}

	public void createListForm() {
		createFormLength();
		createFormCurrency();
		createFormTime();
		createFormTemperature();
		createFormMass();
	}

	// 4
	private void createFormMass() {
		// TODO Auto-generated method stub
		mform[4] = new Form("Convert Length");
		txtGallon = new TextField("Gallons", "", 30, TextField.ANY);
		txtLiter = new TextField("Liter", "", 30, TextField.ANY);
		mform[4].append(txtGallon);
		mform[4].append(txtLiter);
		mform[4].addCommand(cmOk);
		mform[4].addCommand(cmBack);
		mform[4].setCommandListener(this);
	}

	// 2
	private void createFormTime() {
		// TODO Auto-generated method stub
		mform[2] = new Form("Convert Length");
		txtHour = new TextField("Hours", "", 30, TextField.ANY);
		txtSecond = new TextField("Second", "", 30, TextField.ANY);
		mform[2].append(txtHour);
		mform[2].append(txtSecond);
		mform[2].addCommand(cmOk);
		mform[2].addCommand(cmBack);
		mform[2].setCommandListener(this);
	}

	// 0
	private void createFormLength() {
		// TODO Auto-generated method stub
		mform[0] = new Form("Convert Length");
		txtMile = new TextField("Miles", "", 30, TextField.ANY);
		txtMeter = new TextField("Meters", "", 30, TextField.ANY);
		mform[0].append(txtMile);
		mform[0].append(txtMeter);
		mform[0].addCommand(cmOk);
		mform[0].addCommand(cmBack);
		mform[0].setCommandListener(this);

	}

	// 1
	private void createFormCurrency() {
		// TODO Auto-generated method stub
		mform[1] = new Form("Convert Currency");
		txtUSD = new TextField("USD", "", 30, TextField.ANY);
		txtVND = new TextField("VND", "", 30, TextField.ANY);
		mform[1].append(txtUSD);
		mform[1].append(txtVND);
		mform[1].addCommand(cmOk);
		mform[1].addCommand(cmBack);
		mform[1].setCommandListener(this);
	}

	// 3
	private void createFormTemperature() {
		// TODO Auto-generated method stub
		mform[3] = new Form("Convert temperature");
		txtF = new TextField("F", "", 30, TextField.ANY);
		txtC = new TextField("C", "", 30, TextField.ANY);
		mform[3].append(txtF);
		mform[3].append(txtC);
		mform[3].addCommand(cmOk);
		mform[3].addCommand(cmBack);
		mform[3].setCommandListener(this);
	}

	public void createSelect() {
		select = new List("Contact list: select List", List.IMPLICIT);

		select.addCommand(cmSelected);
		select.addCommand(cmExit);
		select.setCommandListener(this);
		select.append("1. Length: Miles->Meter", null);
		select.append("2. Currency: USD->VND", null);
		select.append("3. Time: Hour->seconds", null);
		select.append("4. Temperature: F->C", null);
		select.append("5. Mass: Gallon->Liter", null);
	}

	public Converter() {

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
		createCommand();
		createListForm();
		this.createSelect();
		mDisplay.setCurrent(select);
	}

	public void commandAction(Command com, Displayable display) {
		// TODO Auto-generated method stub
		if (display == select) {
			if (com == cmSelected) {
				System.out.println(select.getSelectedIndex());
				if (select.getSelectedIndex() == 0) {
					mDisplay.setCurrent(mform[0]);
				} else if (select.getSelectedIndex() == 1) {
					mDisplay.setCurrent(mform[1]);
				} else if (select.getSelectedIndex() == 2) {
					mDisplay.setCurrent(mform[2]);
				} else if (select.getSelectedIndex() == 3) {
					mDisplay.setCurrent(mform[3]);
				} else {
					mDisplay.setCurrent(mform[4]);
				}
			} else if (com == cmExit) {
				notifyDestroyed();
			}
		} else if (display == mform[0]) {
			if (com == cmBack) {
				mDisplay.setCurrent(select);
			} else if (com == cmOk) {
				processLength();
			}
		} else if (display == mform[1]) {
			if (com == cmBack) {
				mDisplay.setCurrent(select);
			}
			if (com == cmOk) {
				processCurrency();
			}

		} else if (display == mform[2]) {
			if (com == cmBack) {
				mDisplay.setCurrent(select);
			}
			if (com == cmOk) {
				processTime();
			}

		} else if (display == mform[3]) {
			if (com == cmBack) {
				mDisplay.setCurrent(select);
			}
			if (com == cmOk) {
				processTemperature();
			}

		} else if (display == mform[4]) {
			if (com == cmBack) {
				mDisplay.setCurrent(select);
			}
			if (com == cmOk) {
				processMass();
			}
		}
	}

	private void processMass() {
		// TODO Auto-generated method stub
		String str = txtGallon.getString();
		float f=0.0f;
		float t=0.0f;
		if(txtGallon.getString().length()>0){
			f = Float.parseFloat(str);
		}
		t=(float) (f*3.78541);
		this.createFormCurrency();
		txtGallon.setString(f+"");
		txtLiter.setString(t+"");
		mDisplay.setCurrent(mform[4]);

	}

	private void processTemperature() {
		// TODO Auto-generated method stub
		String str = txtF.getString();
		float f=0.0f;
		float t=0.0f;
		if(txtF.getString().length()>0){
			f = Float.parseFloat(str);
		}
		t=(float) (f*(-17.2222));
		this.createFormCurrency();
		txtF.setString(f+"");
		txtC.setString(t+"");
		mDisplay.setCurrent(mform[3]);
	}

	private void processTime() {
		// TODO Auto-generated method stub
		String str = txtHour.getString();
		float f=0.0f;
		float t=0.0f;
		if(txtHour.getString().length()>0){
			f = Float.parseFloat(str);
		}
		t=(float) (f*3600);
		this.createFormCurrency();
		txtHour.setString(f+"");
		txtSecond.setString(t+"");
		mDisplay.setCurrent(mform[2]);
	}

	private void processCurrency() {
		// TODO Auto-generated method stub
		String str = txtUSD.getString();
		float f=0.0f;
		float t=0.0f;
		if(txtUSD.getString().length()>0){
			f = Float.parseFloat(str);
		}
		t=(float) (f*21120);
		this.createFormCurrency();
		txtUSD.setString(f+"");
		txtVND.setString(t+"");
		mDisplay.setCurrent(mform[1]);

	}

	private void processLength() {
		String str = txtMile.getString();
		float f=0.0f;
		float t=0.0f;
		if(txtMile.getString().length()>0){
			f = Float.parseFloat(str);
		}
		t=(float) (f*1609.34);
		this.createFormLength();
		txtMile.setString(f+"");
		txtMeter.setString(t+"");
		mDisplay.setCurrent(mform[0]);
	}

}
