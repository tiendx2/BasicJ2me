package bai1.contactManager;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.rms.RecordStoreNotOpenException;

public class ShowContact extends MIDlet implements CommandListener {

	private Display mDisplay;
	private Command mEdit, mAdd, mExit, mBack, mOk;
	private ContactList listContact;

	private List mlist;
	private Form form;
	TextField txtName;
	TextField txtTelephon;
	TextField txtEmail;

	public int flag = 1;

	public ShowContact() {
		// TODO Auto-generated constructor stub
		listContact = new ContactList("danhSach");
		listContact.openRecord();
		listContact.initList();
	}

	protected void startApp() {
		// TODO Auto-generated method stub
		mDisplay = Display.getDisplay(this);
		this.createCommand();
		try {
			this.createList();
		} catch (RecordStoreNotOpenException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.createForm();
		mDisplay.setCurrent(mlist);

	}

	// Method create screen Add
	private void createForm() {
		form = new Form("Add Information");
		txtName = new TextField("Name", "", 30, TextField.ANY);
		txtTelephon = new TextField("Telephone", "", 30, TextField.NUMERIC);
		txtEmail = new TextField("Email", "", 30, TextField.EMAILADDR);

		form.append(txtName);
		form.append(txtTelephon);
		form.append(txtEmail);

		form.addCommand(mBack);
		form.addCommand(mOk);
		form.setCommandListener(this);
	}

	// Method create screen List
	private void createList() throws RecordStoreNotOpenException {
		mlist = new List("Contact Manager", List.IMPLICIT);
		String tg;
		int i, t;
		for (i = 1; i <= this.listContact.getSizeRestore(); i += 3) {
			t = i / 3 + 1;
			tg = new String(this.listContact.getOneRecord(i));
			mlist.append(t + ". " + tg, null);
		}

		mlist.addCommand(mExit);
		mlist.addCommand(mAdd);
		mlist.addCommand(mEdit);

		mlist.setCommandListener(this);
	}

	private void createCommand() {
		this.mAdd = new Command("Add", Command.SCREEN, 0);
		this.mEdit = new Command("Edit", Command.SCREEN, 1);
		this.mExit = new Command("Exit", Command.EXIT, 0);

		this.mOk = new Command("Ok", Command.OK, 0);
		this.mBack = new Command("Back", Command.BACK, 0);

	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		this.listContact.closeRecord();
		this.listContact.deleteRecStore();

	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	private void editInformation() {
		int d = mlist.getSelectedIndex() * 3 + 1;
		this.txtName.setString(new String(this.listContact.getOneRecord(d)));
		this.txtTelephon.setString(new String(this.listContact
				.getOneRecord(d + 1)));
		this.txtEmail
				.setString(new String(this.listContact.getOneRecord(d + 2)));
	}

	public void commandAction(Command com, Displayable dis) {
		// TODO Auto-generated method stub
		if (dis == mlist) {
			if (com == mAdd) {
				this.flag = 1;
				this.createForm();
				this.mDisplay.setCurrent(form);
			} else if (com == mEdit) {
				this.flag = 2;
				this.createForm();
				this.editInformation();
				this.mDisplay.setCurrent(form);
			} else if (com == mExit) {
				notifyDestroyed();
			}
		} else if (dis == form) {
			if (com == mBack) {
				try {
					createList();
				} catch (RecordStoreNotOpenException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.mDisplay.setCurrent(mlist);
			} else if (com == mOk) {
				if (this.txtName.getString().length() > 3) {
					if (flag == 1) {
						listContact.writeRecord(this.txtName.getString());
						listContact.writeRecord(this.txtTelephon.getString());
						listContact.writeRecord(this.txtEmail.getString());

					} else if (flag == 2) {
						listContact.updateRecord(
								this.mlist.getSelectedIndex() + 1,
								this.txtName.getString());
						listContact.updateRecord(
								this.mlist.getSelectedIndex() + 2,
								this.txtTelephon.getString());
						listContact.updateRecord(
								this.mlist.getSelectedIndex() + 3,
								this.txtEmail.getString());
					}
					txtName.setString("");
					txtTelephon.setString("");
					txtEmail.setString("");
				} 
			}
		}
	}

}
