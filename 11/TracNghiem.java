package bai11_tracNghiem;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;

public class TracNghiem extends MIDlet implements ItemCommandListener {
	public ChoiceGroup[] arrChoose = new ChoiceGroup[5];

	private Display display;
	private Form form;

	private RecordStore rs;
	private String rsName = "RSName";

	Command cmNext, cmBack, cmFinish, cmExit;

	int radioCurrent = 0;
	int arrDapAn[] = new int[5];
	int arrAnser[] = new int[5];

	int ketqua = 0;

	public void createArrChoose() {
		String sdata[][] = this.readREC(arrDapAn);
		int i, j;

		for (i = 0; i < 5; i++) {
			arrChoose[i] = new ChoiceGroup(sdata[i][0], ChoiceGroup.EXCLUSIVE);
			for (j = 1; j < 5; j++) {
				arrChoose[i].append(sdata[i][j], null);
			}
			if (i == 0) {
				arrChoose[i].addCommand(cmExit);
				arrChoose[i].addCommand(cmNext);
			} else if (i == 4) {
				arrChoose[i].addCommand(cmBack);
				arrChoose[i].addCommand(cmFinish);
			} else {
				arrChoose[i].addCommand(cmBack);
				arrChoose[i].addCommand(cmNext);
			}
			arrChoose[i].setItemCommandListener(this);
		}
	}

	public TracNghiem() {
		cmNext = new Command("Next", Command.OK, 1);
		cmBack = new Command("Back", Command.BACK, 1);
		cmExit = new Command("Exit", Command.EXIT, 1);
		cmFinish = new Command("Finish", Command.OK, 1);
	}

	public void openRS() {
		try {
			rs = RecordStore.openRecordStore(rsName, true);
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
	}

	public void closeRS() {
		try {
			rs.closeRecordStore();
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
	}

	public void deleteRS() {
		try {
			RecordStore.deleteRecordStore(rsName);
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
	}

	// Write REC
	public void writeREC(String sdata[], int idata) {
		openRS();

		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		DataOutputStream dout = new DataOutputStream(bout);

		int i;
		try {
			for (i = 0; i < 5; i++)
				dout.writeUTF(sdata[i]);
			dout.writeInt(idata);

			byte[] bytedata = bout.toByteArray();

			rs.addRecord(bytedata, 0, bytedata.length);

			bout.close();
			dout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("So record:" + rs.getNumRecords());
		} catch (RecordStoreNotOpenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeRS();
	}

	public String[][] readREC(int idata[]) {
		openRS();
		String sdata[][] = new String[5][5];
		ByteArrayInputStream bin = null;
		DataInputStream din = null;
		int i, j;
		try {

			RecordEnumeration re = rs.enumerateRecords(null, null, true);

			byte[] bytedata = null;

			i = 0;
			while (re.hasNextElement()) {
				int id = re.nextRecordId();

				bytedata = rs.getRecord(id);

				bin = new ByteArrayInputStream(bytedata);
				din = new DataInputStream(bin);
				for (j = 0; j < 5; j++) {
					sdata[i][j] = din.readUTF();
				}
				idata[i] = din.readInt();
				i = i + 1;
			}

			bin.close();
			din.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		closeRS();
		return sdata;

	}

	public void deleteREC(int id) {
		openRS();
		try {
			rs.deleteRecord(id);
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
		closeRS();
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {

	}

	protected void pauseApp() {

	}

	public void initStore() {
		int i, j;
		openRS();
		String sData[][] = {
				{ "Cau hoi 1", "Tra loi 1", "tra loi 2", "tra loi 3",
						"tra loi 4" },
				{ "Cau hoi 2", "Tra loi 1", "tra loi 2", "tra loi 3",
						"tra loi 4" },
				{ "Cau hoi 3", "Tra loi 1", "tra loi 2", "tra loi 3",
						"tra loi 4" },
				{ "Cau hoi 4", "Tra loi 1", "tra loi 2", "tra loi 3",
						"tra loi 4" },
				{ "Cau hoi 5", "Tra loi 1", "tra loi 2", "tra loi 3",
						"tra loi 4" }, };
		int intDA[] = { 1, 0, 2, 3, 2 };
		for (i = 0; i < 5; i++) {
			this.writeREC(sData[i], intDA[i]);
		}

		closeRS();
	}

	protected void startApp() throws MIDletStateChangeException {
		display = Display.getDisplay(this);
		deleteRS();
		initStore();
		createArrChoose();
		creatForm(this.radioCurrent);
	}

	public void creatForm(int current) {
		form = new Form("RecordStore Demo");
		form.append(this.arrChoose[current]);
		display.setCurrent(form);
	}

	public void commandAction(Command arg0, Item arg1) {
		// TODO Auto-generated method stub
		if (arg0 == cmNext) {
			this.arrAnser[this.radioCurrent] = this.arrChoose[radioCurrent]
					.getSelectedIndex();
			if (arrAnser[this.radioCurrent] == arrDapAn[this.radioCurrent])
				this.ketqua = ketqua + 1;

			this.radioCurrent = this.radioCurrent + 1;
			creatForm(this.radioCurrent);
		} else if (arg0 == cmBack) {
			createArrChoose();
			this.radioCurrent = this.radioCurrent - 1;
			creatForm(radioCurrent);

		} else if (arg0 == cmFinish) {
			display.setCurrent(new Alert("Ket Qua", "Dung:" + ketqua + "/5",
					null, AlertType.CONFIRMATION));
			this.ketqua = 0;

		} else if (arg0 == cmExit) {

		}

	}

}
