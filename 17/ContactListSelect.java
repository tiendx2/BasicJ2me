package bai17_contactList;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;
import javax.microedition.rms.RecordStoreNotOpenException;

public class ContactListSelect extends MIDlet implements CommandListener {

	public List select, personal, business;
	Display mDisplay;

	RecordStore recordPersonal, recordBusiness;

	Command cmExit, cmList, cmOk, cmBack;

	public void createRecordPersonal() throws RecordStoreFullException,
			RecordStoreNotFoundException, RecordStoreException {
		recordPersonal = RecordStore.openRecordStore("Personal", true);
		byte[] arrByte = new byte[50];
		String arrString[] = { "Dang Xuan Tien", "Nguyen Thi Nhung", "Nguyen Thi Phuong" };
		for (int i = 0; i < arrString.length; i++) {
			arrByte = arrString[i].getBytes();
			recordPersonal.addRecord(arrByte, 0, arrByte.length);
		}
		recordPersonal.closeRecordStore();
	}

	public void createRecordBussiness() throws RecordStoreFullException,
			RecordStoreNotFoundException, RecordStoreException {
		recordBusiness = RecordStore.openRecordStore("Business", true);
		byte[] arrByte = new byte[50];
		String arrString[] = { "VietTinBank", "InComBank", "ACB" };
		for (int i = 0; i <arrString.length; i++) {
			arrByte = arrString[i].getBytes();
			recordBusiness.addRecord(arrByte, 0, arrByte.length);
		}
		recordBusiness.closeRecordStore();
	}

	public void createCommand() {
		cmBack = new Command("Back", Command.BACK, 1);
		cmList = new Command("List", Command.OK, 1);
		cmExit = new Command("Exit", Command.EXIT, 1);
		cmOk = new Command("Ok", Command.OK, 1);
	}

	public void createSelect() {
		select = new List("Contact list: select List", List.IMPLICIT);

		select.addCommand(cmList);
		select.addCommand(cmExit);
		select.setCommandListener(this);
		select.append("Personal", null);
		select.append("Business", null);
	}

	public void createPersonal() throws InvalidRecordIDException,
			RecordStoreException {
		personal = new List("Peron", List.IMPLICIT);
		personal.deleteAll();
		recordPersonal = RecordStore.openRecordStore("Personal", true);
		byte[] arrByte = new byte[50];
		for (int i = 1; i <=recordPersonal.getNumRecords(); i++) {
			personal.append(i+". "+new String(recordPersonal.getRecord(i)), null);
		}
		recordPersonal.closeRecordStore();
		
		personal.addCommand(cmBack);
		personal.addCommand(cmOk);
		personal.setCommandListener(this);
	}

	public void createBusiness() throws InvalidRecordIDException,
			RecordStoreException {
		business = new List("Busines", List.IMPLICIT);
		business.deleteAll();
		recordBusiness = RecordStore.openRecordStore("Business", true);
		byte[] arrByte = new byte[50];
		for (int i = 1; i <=recordBusiness.getNumRecords(); i++) {
			business.append(i+". "+new String(recordBusiness.getRecord(i)), null);
		}
		recordBusiness.closeRecordStore();
		
		business.addCommand(cmBack);
		business.addCommand(cmOk);
		business.setCommandListener(this);
	}

	public ContactListSelect() {
		// TODO Auto-generated constructor stub
		try {
			createRecordPersonal();
			createRecordBussiness();
		} catch (RecordStoreFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RecordStoreNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RecordStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		try {
			this.recordBusiness.deleteRecordStore("Personal");
			this.recordPersonal.deleteRecordStore("Business");
		} catch (RecordStoreNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RecordStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		mDisplay = Display.getDisplay(this);
		createCommand();
		this.createSelect();
		mDisplay.setCurrent(select);
	}

	public void commandAction(Command com, Displayable display) {
		// TODO Auto-generated method stub
		if (display == select) {
			if (com == cmList) {
				if (select.getSelectedIndex() == 0) {
					try {
						createPersonal();
					} catch (InvalidRecordIDException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (RecordStoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					mDisplay.setCurrent(personal);
				} else if(select.getSelectedIndex()==1) {
					try {
						createBusiness();
					} catch (InvalidRecordIDException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (RecordStoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					mDisplay.setCurrent(business);
				}
			}
		} else if (display == personal) {
			if (com == cmBack) {
//				createSelect();
				mDisplay.setCurrent(select);
			}

		} else if (display == business) {
			if (com == cmBack) {
//				createSelect();
				mDisplay.setCurrent(select);
			}
		}
	}

}
