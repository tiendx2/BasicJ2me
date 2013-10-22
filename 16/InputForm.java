package bai16_ungDungTraTien;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class InputForm extends MIDlet implements CommandListener {

	Display mDis;
	private Form inForm, outForm;
	private ChoiceGroup cSex;
	private TextField txtAge, txtPay;
	private Command cmExit, cmNext, cmBack, cmFinish;

	private int result = 0;

	public void createInForm() {
		inForm = new Form("In Form");
		cSex = new ChoiceGroup("", ChoiceGroup.EXCLUSIVE);
		cSex.append("Male", null);
		cSex.append("Femail", null);
		cSex.append("Child", null);
		txtAge = new TextField("Age", "", 10, TextField.NUMERIC);
		cmExit = new Command("Exit", Command.EXIT, 1);
		cmNext = new Command("Next", Command.OK, 1);

		inForm.append(cSex);
		inForm.append(txtAge);
		inForm.addCommand(cmExit);
		inForm.addCommand(cmNext);

		inForm.setCommandListener(this);
	}

	public void createOutForm() {
		outForm = new Form("Out Form");
		txtPay = new TextField("Pay $", "", 10, TextField.NUMERIC);
		cmBack = new Command("Back", Command.BACK, 1);
		cmFinish = new Command("Finish", Command.OK, 1);

		outForm.append(txtPay);
		outForm.addCommand(cmBack);
		outForm.addCommand(cmFinish);

		outForm.setCommandListener(this);
	}

	public InputForm() {
		// TODO Auto-generated constructor stub
		this.createInForm();
		this.createOutForm();
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		mDis = Display.getDisplay(this);
		mDis.setCurrent(inForm);
	}

	private boolean checkAge(int age) {
		if (age > 0 && age < 199) {
			return true;
		} else
			return false;
	}

	public void process(int age) {
		int sex1 = 0, sex2 = 0, sex3 = 0;

		if (cSex.getSelectedIndex() == 0) {
			sex1 = 100;
			sex2 = 150;
			sex3 = 125;
		} else if (cSex.getSelectedIndex() == 1) {
			sex1 = 170;
			sex2 = 150;
			sex3 = 105;
		} else {
			sex3 = 50;
		}
		if (age < 18) {
			this.result = 50;
		} else if (age >= 18 && age <= 25) {
			this.result = sex1;
		} else if (age > 25 && age < 40) {
			this.result = sex2;
		} else {
			this.result = sex3;
		}
	}

	public void commandAction(Command com, Displayable mdisplay) {
		// TODO Auto-generated method stub
		if (mdisplay == inForm) {
			if (com == cmExit)
				notifyDestroyed();
			else if (com == cmNext) {
				int intAge = -1;
				if (txtAge.getString().length() > 0) {
					intAge = Integer.parseInt(txtAge.getString());
				}
				if (checkAge(intAge)) {
					this.process(intAge);
					this.mDis.setCurrent(outForm);
				}
			}
		} else if (mdisplay == outForm) {
			if (com == cmBack) {
				this.mDis.setCurrent(inForm);
			} else if (com == cmFinish) {
				int intPay = -1;
				if (txtPay.getString().length() > 0) {
					intPay = Integer.parseInt(txtPay.getString());
				}
				if (intPay > 0) {
					String massage = "";
					if(intPay>result){
						massage="So tien tra du "+(intPay-result);
					}
					else if(intPay<result){
						massage="So tien tra thieu "+(result-intPay);
					}
					else {
						massage="So tien tra vua du";
					}
					this.mDis.setCurrent(new Alert("Thong tin", massage, null,
							AlertType.CONFIRMATION));
				}
			}
		}
	}

}
