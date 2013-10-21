import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;

public class ShowContact extends MIDlet implements CommandListener {

	private Display mDisplay;
	private Command mSort, mExit, mBack, mQuery;
	private ContactList listContact;

	Form fmSort;
	ChoiceGroup cgSort, cgFilt;
	TextField txtFiler;
	private List mlist;

	int type = 0;
	String strFilter = "";

	public int flag = 1;

	private RecordEnumeration recordEnumeration = null;

	private Comparator comparator = null;
	private Filters fil = null;

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
		if (type == 0) {
			try {
				this.createList(null, null);
			} catch (RecordStoreNotOpenException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			comparator = new Comparator(type);
			try {
				this.createList(comparator, fil);
			} catch (RecordStoreNotOpenException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.createForm();
		mDisplay.setCurrent(mlist);

	}

	// Method create screen Add
	private void createForm() {
		fmSort = new Form("Add Information");
		this.cgSort = new ChoiceGroup("Type Sort", ChoiceGroup.EXCLUSIVE);
		cgSort.append("None", null);
		cgSort.append("Last,First", null);
		cgSort.append("First,Last", null);

		this.cgFilt = new ChoiceGroup("Type Filter", ChoiceGroup.EXCLUSIVE);
		cgFilt.append("None", null);
		cgFilt.append("Start with", null);
		cgFilt.append("Contains", null);

		txtFiler = new TextField(null, "", 30, TextField.ANY);

		fmSort.append(cgSort);
		fmSort.append(cgFilt);
		fmSort.append(txtFiler);

		fmSort.addCommand(mBack);
		fmSort.addCommand(mQuery);
		fmSort.setCommandListener(this);
	}

	// Method create screen List
	private void createList(Comparator comparator, Filters fil)
			throws RecordStoreNotOpenException {
		mlist = new List("Contact Manager", List.IMPLICIT);
		mlist.deleteAll();
		String tg = "";
		recordEnumeration = this.listContact.listRecord.enumerateRecords(fil,
				comparator, false);
		while (recordEnumeration.hasNextElement()) {
			try {
				tg = new String(recordEnumeration.nextRecord());
			} catch (InvalidRecordIDException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RecordStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mlist.append(tg, null);
		}

		mlist.addCommand(mExit);
		mlist.addCommand(mSort);

		mlist.setCommandListener(this);
	}

	private void createCommand() {
		this.mSort = new Command("Sort", Command.SCREEN, 0);
		this.mExit = new Command("Exit", Command.EXIT, 0);

		this.mQuery = new Command("Ok", Command.OK, 0);
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

	public void commandAction(Command com, Displayable dis) {
		if (dis == mlist) {
			if (com == mSort) {
				mDisplay.setCurrent(fmSort);
			}
		} else if (dis == fmSort) {
			if (com == mQuery) {
				this.type = cgSort.getSelectedIndex();
				this.strFilter = txtFiler.getString();
			} else if (com == mBack) {
				comparator = new Comparator(type);
				if (strFilter.length() < 2)
					this.fil = null;
				else
					this.fil = new Filters(strFilter);
				try {
					this.createList(comparator, fil);
				} catch (RecordStoreNotOpenException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mDisplay.setCurrent(mlist);
			}
		}

	}

}
