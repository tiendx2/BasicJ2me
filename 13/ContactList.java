

import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;

public class ContactList {

	private String REC_STORE;
	public RecordStore listRecord = null;

	public RecordStore getListRecord() {
		return this.listRecord;
	}

	public ContactList(String name) {
		this.REC_STORE = name;
	}

	// open Record Store
	public void openRecord() {
		try {
			listRecord = RecordStore.openRecordStore(REC_STORE, true);
		} catch (Exception e) {

		}
	}

	// write a record
	public void writeRecord(String str) {
		byte[] rec = str.getBytes();
		try {
			listRecord.addRecord(rec, 0, rec.length);
		} catch (Exception e) {

		}
	}

	// Update a record
	public void updateRecord(int vt, String str) {
		try {
			listRecord.setRecord(vt, str.getBytes(), 0, str.length());
		} catch (Exception e) {

		}
	}

	// Delte a record in key
	public void deleteRecord(int key) {
		try {
			listRecord.deleteRecord(key);
		} catch (Exception e) {

		}
	}

	// init record store
	public void initList() {
		String[] ds = { "001", "Dang Xuan Tien", "Hung Yen","A2",
				"002", "Nguyen Thi Nhung", "Hai Duong","A3" };
		int i = 0;
		for (i = 0; i < ds.length; i++) {
			this.writeRecord(ds[i]);
		}
	}

	public byte[] getOneRecord(int key) {
		byte byteRecord[] = new byte[100];
		try {
			byteRecord = this.listRecord.getRecord(key);
		} catch (RecordStoreNotOpenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidRecordIDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RecordStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return byteRecord;
	}

	public int getSizeRestore() throws RecordStoreNotOpenException {
		return this.listRecord.getNumRecords();
	}

	// close a record store
	public void closeRecord() {
		try {
			listRecord.closeRecordStore();
		} catch (Exception e) {

		}
	}

	public void deleteRecStore() {
		if (RecordStore.listRecordStores() != null) {
			try {
				RecordStore.deleteRecordStore(REC_STORE);
			} catch (Exception e) {
			}
		}
	}
}
