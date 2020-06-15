class ArrCopyTest {

	public static void main(String[] args) {
		int[] a = new int[0xFFFFFF];
		int[] b = new int[0xFFFFFFF];

		long start = System.currentTimeMillis();

		for (int i = 0; i < a.length - 1; i++) {
			b[i] = a[i];
		}

		long end = System.currentTimeMillis();
		System.out.println("for循环复制数组执行时间为： " + (end - start));
	}
}