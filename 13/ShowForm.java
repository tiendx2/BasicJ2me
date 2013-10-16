

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


public class ShowForm extends MIDlet implements CommandListener {

	Display mDisplay;
	Form mForm, fDetail;
	TextField txtID, txtName, txtAddress, txtClass;
	Command  mUpdate, mDelete, mSearch, mExit, mBack, mDetail,mEdit,mAdd,mOk;
	List mlist;
	int selected=-1;
	public ContactList sdList;

	public ShowForm() {
		// TODO Auto-generated constructor stub
		sdList = new ContactList("DanhSachSV");
		sdList.openRecord();
		sdList.initList();
	}

	public void createCommand() {
		mUpdate = new Command("Update", Command.SCREEN, 2);
		mDelete = new Command("Delete", Command.SCREEN, 3);
		mSearch = new Command("Search", Command.SCREEN, 4);
		mBack = new Command("Back", Command.BACK, 0);
		mDetail = new Command("Detail", Command.SCREEN, 0);
		mExit = new Command("Exit", Command.EXIT, 0);
		mEdit = new Command("Edit",Command.SCREEN, 0);
		mAdd = new Command("Add",Command.SCREEN, 0);
		mOk = new Command("Add",Command.OK, 0);
	}

	public void addCommand() {
		mForm.addCommand(mDelete);
		mForm.addCommand(mSearch);
		mForm.addCommand(mExit);
	}

	public void createForm() {
		txtID = new TextField("ID", "", 30, TextField.ANY);
		txtName = new TextField("Name", "", 30, TextField.ANY);
		txtAddress = new TextField("Address", "", 30, TextField.ANY);
		txtClass = new TextField("Class", "", 30, TextField.ANY);

		mForm = new Form("Student Manager");
		mForm.append(txtID);
		mForm.append(txtName);
		mForm.append(txtAddress);
		mForm.append(txtClass);
		mForm.setCommandListener(this);

	}

	public void createDetailForm() {
		fDetail = new Form("Thong tin chi tiet");
		fDetail.addCommand(mBack);
		fDetail.setCommandListener(this);
	}

	public void createList() throws RecordStoreNotOpenException {
		mlist = new List("Danh sach sv", List.IMPLICIT);
		mlist.deleteAll();
		int size = sdList.getSizeRestore() / 4;
		String chuoi = "";
		for (int i = 0; i < size; i++) {
			chuoi = new String(sdList.getOneRecord(i * 4 + 1)) + "-"
					+ new String(sdList.getOneRecord(i * 4 + 2));
			mlist.append(chuoi, null);
		}
		mlist.addCommand(mDetail);
		mlist.addCommand(mAdd);
		mlist.addCommand(mEdit);
		mlist.addCommand(mSearch);
		mlist.setCommandListener(this);

	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		this.sdList.closeRecord();
		this.sdList.deleteRecStore();
	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		mDisplay = Display.getDisplay(this);
		createCommand();
		createForm();
		addCommand();
		try {
			createList();
		} catch (RecordStoreNotOpenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mDisplay.setCurrent(mlist);

	}

	public void actionMDetail(){
		createDetailForm();
		int p = -1;
		p = this.mlist.getSelectedIndex() ;
		selected=p*4+1;
		String detain = "";
		if (p >= 0) {
			detain = "\nID:" + new String(sdList.getOneRecord(selected))
					+ "\nName:" + new String(sdList.getOneRecord(selected + 1))
					+ "\nAddress:"+ new String(sdList.getOneRecord(selected + 2))
					+ "\nName:"+ new String(sdList.getOneRecord(selected + 3));
			System.out.println("Select=" + detain);
		}
		StringItem sItem = new StringItem("", detain);
		fDetail.append(sItem);
		mDisplay.setCurrent(fDetail);
	}
	public void actionMEdit(){
		createForm() ;
		mForm.addCommand(mUpdate);
		mForm.addCommand(mBack);
		int p = -1;
		p = this.mlist.getSelectedIndex();
		selected=p*4+1;
		txtID.setString(new String(sdList.getOneRecord(selected)));
		txtName.setString(new String(sdList.getOneRecord(selected + 1)));
		txtAddress.setString(new String(sdList.getOneRecord(selected+ 2)));
		txtClass.setString(new String(sdList.getOneRecord(selected+ 3)));
		mDisplay.setCurrent(mForm);
		
	}
	public void actionMUpdate(){
		System.out.println("Selected="+selected);
		this.sdList.updateRecord(selected,txtID.getString());
		this.sdList.updateRecord(selected+1,txtName.getString());
		this.sdList.updateRecord(selected+2,txtAddress.getString());
		this.sdList.updateRecord(selected+3,txtClass.getString());
	}
	public void actionMOkAdd(){
		this.sdList.writeRecord(txtID.getString());
		this.sdList.writeRecord(txtName.getString());
		this.sdList.writeRecord(txtAddress.getString());
		this.sdList.writeRecord(txtClass.getString());
	}
	public void commandAction(Command com, Displayable dis) {
		// TODO Auto-generated method stub
		if (dis == mlist) {
			if (com == mDetail) {
				actionMDetail();
			}
			else if(com==mEdit){
				actionMEdit();
			}
			else if(com==mAdd){
				createForm() ;
				mForm.addCommand(mOk);
				mForm.addCommand(mBack);
				mDisplay.setCurrent(mForm);
			}
			else if(com==mSearch){
				createForm() ;
				mDisplay.setCurrent(mForm);
			}
		} else if (dis == mForm) {
			if(com==mBack){
				this.mlist.deleteAll();
				try {
					createList();
				} catch (RecordStoreNotOpenException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mDisplay.setCurrent(mlist);
			}
			else if(com==mUpdate){
				actionMUpdate();
			}
			else if(com==mOk){
				actionMOkAdd();
			}

		} else if (dis == fDetail) {
			if(com==mBack){
				mDisplay.setCurrent(mlist);
			}

		}

	}

}
