import java.util.*;

class randomInt {

	public static void main(String[] args) {
		Random r = new Random();
		System.out.println(randomInt(-1000, 1078, r));
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
}