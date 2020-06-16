import java.util.*;
import java.lang.*;

class reverseWord {
	public static void main(String[] args) {
		String a = "To Be Or Not To Be";
		String[] rlt = new String[20];
		System.out.println(revWord(a));
		// System.arraycopy(rlt, 0, revWord(a), 0, revWord(a).length);

		// for (String i : rlt) {
		// 	System.out.println(i);
		// }

	}

	public static String revWord(String target) {
		Scanner sc = new Scanner(target);
		String rlt = "";
		int index = 0;
		String[] temp = new String[20];

		do {
			temp[index] = sc.next();
			index++;
		} while(sc.hasNext());

		for (int i = 0; i < index; i++) {
			rlt += (reverse(temp[i]) + " ");
		}


		return rlt;
	}

	public static String reverse(String targetStr) {
		char[] char1 = targetStr.toCharArray();
		String str = "";
		for (int i = char1.length - 1; i >= 0; i--) {
			str = str + (char1[i] + "");
		}

		return str;
	}
}