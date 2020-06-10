class Fridge {

	int heightInCM;
	Elephant storageE;
	Lion storageL;

	void store(Elephant elephant) {
		storageE = elephant;
	}

	void store(Lion lion) {
		storageL = lion;
	}

	//取出冰箱内的大象
	public Elephant removeE() {
		if (storageE == null) {
			System.out.println("冰箱里没有大象！");
			return null;
		}

		Elephant temp = storageE;
		storageE = null;

		return temp;
	}
}