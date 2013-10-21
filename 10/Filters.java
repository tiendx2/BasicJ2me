
import javax.microedition.rms.RecordFilter;

;
public class Filters implements RecordFilter {

	private String str;

	public Filters(String str) {
		this.str = str;
	}

	public boolean matches(byte[] rec) {

		String strRec = new String(rec);
		strRec=strRec.trim().toLowerCase();
		String[] arrStr = Comparator.spitString(strRec);
		String ss =arrStr[arrStr.length-1];
		ss=ss.trim();
		System.out.println(ss.trim());
		if (ss.equalsIgnoreCase(str)){
			return true;
		}
		return false;

	}

}
