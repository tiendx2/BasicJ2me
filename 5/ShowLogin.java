package bai5.login;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.rms.RecordStoreNotOpenException;

public class ShowLogin extends MIDlet implements CommandListener {

	private Display mDisplay;
	private Command mExit, mBack, mOk;
	private ContactList listContact;
	
	private Alert aThongTin;
	private Form form;
	TextField txtName;
	TextField txtPassword;

	public int flag = 1;

	public ShowLogin() {
		// TODO Auto-generated constructor stub
		listContact = new ContactList("danhSach");
		listContact.openRecord();
		listContact.initList();
	}

	protected void startApp() {
		// TODO Auto-generated method stub
		mDisplay = Display.getDisplay(this);
		this.createCommand();
		this.createForm();
		mDisplay.setCurrent(form);

	}

	// Method create screen Add
	private void createForm() {
		form = new Form("Login");
		txtName = new TextField("Name","", 30, TextField.ANY);
		txtPassword = new TextField("Telephone","", 30, TextField.ANY);

		aThongTin = new Alert("Thong Tin","",null,AlertType.INFO);
		aThongTin.setTimeout(Alert.FOREVER);
		
		aThongTin.addCommand(mBack);
		
		form.append(txtName);
		form.append(txtPassword);

		form.addCommand(mExit);
		form.addCommand(mOk);
		form.setCommandListener(this);
	}

	private void createCommand() {
		this.mExit = new Command("Exit", Command.EXIT, 0);
		this.mOk = new Command("Ok", Command.OK, 0);
		this.mBack = new Command("Back", Command.BACK, 0);
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		this.listContact.closeRecord();
	}

	public boolean checkLogin() {
		int i;
		int size = 0;
		String name = "", pass = "";
		try {
			size = listContact.getSizeRestore();
		} catch (RecordStoreNotOpenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(i=0;i<=size/2;i++){
			name=new String(listContact.getOneRecord(i*2+1));
			pass=new String(listContact.getOneRecord(i*2+2));
			if (name.equalsIgnoreCase(txtName.getString())
					&& pass.equalsIgnoreCase(txtPassword.getString()))
				return true;
		}

		return false;

	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	public void commandAction(Command com, Displayable dis) {
		if(dis==form){
			if(com==mOk){
				if(checkLogin()) {
					// Hien len thong tin
					aThongTin.setString("Name:"+txtName.getString()+"\nPass:"+txtPassword.getString());
					 mDisplay.setCurrent(aThongTin);
				}
				else {
					 Alert alert2 = new Alert("Login error","Ten hoac mat khau khong dung", null, AlertType.WARNING);
					 mDisplay.setCurrent(alert2);
				}
			}
			else if(com==mExit){
				notifyDestroyed();
			}
		}
		else if(dis==aThongTin){
			if(com==mBack){
				txtName.setString("");
				txtPassword.setString("");
				mDisplay.setCurrent(form);
			}
		}
	}

}
