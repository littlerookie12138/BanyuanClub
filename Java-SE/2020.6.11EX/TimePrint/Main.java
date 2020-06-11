class Main {

	public static void main(String[] args) {
		TimeShow t = new TimeShow();
		TimeShow t1 = new TimeShow();

		//自动获取系统时间
		// t.setTimeAuto();
		// System.out.println(t.timeToString());

		//手动设置时间
		t1.setTime(23, 59, 59);
		t1.nextSecond();
		System.out.println(t1.timeToString());

	}
}