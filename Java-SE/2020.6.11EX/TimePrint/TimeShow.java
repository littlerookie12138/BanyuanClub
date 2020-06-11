class TimeShow {
	int hour;
	int minute;
	int second;


	void setTime(int ahour, int aminute, int asecond) {
		this.hour = ahour;
		this.minute = aminute;
		this.second = asecond;
	}

	String timeToString() {
		boolean hourIsSingle = false;
		boolean minuteIsSingle = false;
		boolean secondIsSingle = false;

		String time = "0";
		if (hour < 10) {
			hourIsSingle = true;
		}
		if (minute < 10) {
			minuteIsSingle = true;
		}
		if (second < 10) {
			secondIsSingle = true;
		}

		if (hourIsSingle) {
			time = "0" + hour;
		}

		if (minuteIsSingle) {
			time = time + ":" + "0" + minute;
		}

		if (secondIsSingle) {
			time = time + ":" + "0" + second;
		}
		
		return time;
	}

	void nextSecond () {
		second++;
		if (second == 60) {
			minute++;
			second = 0;
			if (minute == 60) {
				minute = 0;
				hour++;
				if (hour == 24) {
					hour = 0;
					System.out.println("时间到了第二天。");
				}
			}
		}
	}
}