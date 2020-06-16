import java.util.*;
class guessNum {
	/*
	猜一猜4个不重复的数字，请输入4个数字：
	1234
	包含了1个正确的数字
	但是这些数字位置错误

	请输入4个数字：
	2314
	包含了1个正确的数字
	1个数字的位置正确

	请输入4个数字：
	5367
	包含了3个正确的数字
	2个数字位置正确

	请输入4个数字：
	5396
	回答正确
	*/

	public static void main(String[] args) {
		Random ran = new Random();
		int result = randomInt(1000, 10000, ran);
		boolean isRight = false;
		String a = String.valueOf(result);
		if (checkRepeat(a)) {
			System.out.println("该数内含有重复!");
			return;
		}

		do {
			Scanner sc = new Scanner(System.in);
			isRight = countRightNum(sc.nextInt(), result);
		} while (!isRight);
	}

	public static int randomInt(int from, int where, Random ran) {
		int n = where - from;
		if (n > 0) {
			return ran.nextInt(n) + from;
		} else {
			int r = 0;
			do {
				r = ran.nextInt();
			} while(r < from || r >= where);

			return r;
		}
	}

	public static boolean checkRepeat(String target) {
		boolean isRepeat = false;
		char[] char1 = target.toCharArray();

		for (int i = 0; i < char1.length; i++) {
			for (int j = i + 1; j < char1.length; j++) {
				if (char1[i] == char1[j]) {
					isRepeat = true;
					return isRepeat;
				}
			}
		}
		return isRepeat;
	}

	public static boolean countRightNum(int target, int src) {
		int count = 0;
		int index = 0;
		boolean isRight = false;
		//遍历然后判断是否其中有该位
		char[] src1 = String.valueOf(src).toCharArray();
		char[] target1 = String.valueOf(target).toCharArray();
		for (int i = 0; i < target1.length; i++) {
			for (int j = 0; j < src1.length; j++) {
				if (target1[i] == src1[j]) {
					//说明有这个数字
					count++;
					if (i == j) {
						//说明位置也相同
						index++;
					}
				}
			}
		}
		if (index == 0) {
			System.out.println("包含了" + count + "个正确数字" + "\n" + "他们的位置都不正确");
			return isRight;
		} else if (index == 4 && target == src) {
			System.out.println("回答正确");
			return isRight = true;
		} else {
			System.out.println("包含了" + count + "个正确数字" + "\n" + index + "个位置正确");
			return isRight;
		}
	}
}