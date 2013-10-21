import javax.microedition.rms.RecordComparator;
import javax.microedition.rms.RecordFilter;

class Comparator implements RecordComparator {
	int type;

	public Comparator(int type) {
		this.type = type;
	}

	public int compare(byte[] record1, byte[] record2) {

		String string1 = new String(record1).toLowerCase(), string2 = new String(record2).toLowerCase();
		String arr1[] = spitString(string1.trim());
		String arr2[] = spitString(string2.trim());
		System.out.println(arr1.length + " ");
		if (this.type == 1) {
			int comparison = arr1[0].compareTo(arr2[0]);
			if (comparison == 0) {
				int com = arr1[arr1.length - 1]
						.compareTo(arr2[arr2.length - 1]);
				if (com == 0)
					return RecordComparator.EQUIVALENT;
				else if (com < 0)
					return RecordComparator.PRECEDES;
				else
					return RecordComparator.FOLLOWS;
			} else if (comparison < 0)
				return RecordComparator.PRECEDES;
			else
				return RecordComparator.FOLLOWS;
		} else {
			int comparison = arr1[arr1.length - 1]
					.compareTo(arr2[arr2.length - 1]);
			if (comparison == 0) {
				int com = arr1[0].compareTo(arr2[0]);
				if (com == 0)
					return RecordComparator.EQUIVALENT;
				else if (com < 0)
					return RecordComparator.PRECEDES;
				else
					return RecordComparator.FOLLOWS;
			} else if (comparison < 0)
				return RecordComparator.PRECEDES;
			else
				return RecordComparator.FOLLOWS;
		}

	}

	public static String[] spitString(String str) {
		char arr[] = str.toCharArray();
		int i = 0, d = 0;
		for (i = 0; i < arr.length; i++) {
			if (arr[i] == ' ') {
				d = d + 1;
			}
		}
		String arrString[] = new String[d + 1];
		int start = 0;
		int end;
		i = 0;
		for (end = 0; end < arr.length; end++) {
			if (arr[end] == ' ') {
				arrString[i] = str.substring(start, end);
				i = i + 1;
				start = end;
			}
		}
		arrString[i] = str.substring(start, end);
		i = i + 1;

		return arrString;
	}
}
