import java.util.*;

class StringBuffer {
	public String aString;

	public static void main(String[] args) {
		StringBuffer s = new StringBuffer();

		//判断输入的是字符串还是字符
		s.append("14215");
		s.append('a');
		s.append('d');
		s.append("1481965");
		System.out.println(s.toStr());

		//输出翻转后的字符串
		System.out.println(s.reverse(s.toStr()));

		//输出按指定位置输出的字符串
		System.out.println(s.reverse(2, 6));

		//清除先前输入
		s.clear();
	}

	//接收字符串
	public void append(String str) {
		if (aString == null) {
			aString = str;
		} else {
			aString += str;
		}
	}

	/**
	接收字符
	*/
	public void append(char str) {
		if (aString == null) {
			aString = str + "";
		} else {
			aString += str;
		}
	}

	/**
	将之前接收到的字符拼接到一起返回
	*/
	public String toStr() {
		return aString;
	}

	/**
	清除之前的输入内容
	*/
	public void clear() {
		aString = null;
	}

	/**
	将之前的输入内容反转
	*/
	public String reverse(String targetStr) {
		char[] char1 = targetStr.toCharArray();
		String str = "";
		for (int i = char1.length - 1; i >= 0; i--) {
			str = str + (char1[i] + "");
		}

		return str;
	}

	/**
	将指定位置的字符串反转
	from 从0起始
	to 不包含在内
	即[from,to)
	例如 abcdefg => reverse(1,3) => acbdefg
	*/
	String reverse(int from, int to) {
		char[] char2 = this.aString.toCharArray();
		String str = "";
		String temp = "";

		for (int i = 0; i < from; i++) {
			str += (char2[i] + "");
		}

		for (int j = to; j >= from; j--) {
			temp += char2[j] + "";
		}
		str += temp;
		
		for (int i = to + 1; i < char2.length - 1; i++) {
			str += (char2[i] + "");
		}

		return str;
	}
}