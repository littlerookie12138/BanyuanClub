class Fraction {
	//molecule 分子
	//denominator 分母

	int molecule = 0;
	int denominator = 0;

	void setMolecule(int m) {
		this.molecule = m;
	}

	void setDenominator(int d) {
		if (d == 0) {
			System.out.println("分母不能为0");
			return;
		}
		this.denominator = d;
	}

	double toDouble() {
		return (double)molecule / denominator;
	}

	Fraction plus(Fraction r) {
		//产生一个新的Fraction的对象
		Fraction newFra = new Fraction();

		if (this.denominator == r.denominator) {
			//分母相同的情况下
			newFra.molecule = this.molecule + r.molecule;
			newFra.denominator = this.denominator;
			return newFra;
		} else {
			newFra.molecule = this.molecule * r.denominator + r.molecule * this.denominator;
			newFra.denominator = this.denominator * r.denominator;
			return newFra;
		}
	}

	Fraction multiply(Fraction r) {
		//产生一个新的Fraction的对象
		Fraction newFra = new Fraction();
		newFra.molecule = this.molecule * r.molecule;
		newFra.denominator = this.denominator * r.denominator;

		return newFra;
	}

	void printFra(Fraction fra) {
		if (fra.molecule == fra.denominator) {
			//二者相同输出为1
			System.out.println(1);
		} else {
			//二者不相同判断能否约分
			int temp = 0;
			int a = fra.molecule;
			int b = fra.denominator;

			do {
				temp = a % b;
				a = b;
				b = temp;
			} while (temp != 0);

			if (a == 1) {
				System.out.println(fra.molecule + " / " + fra.denominator);

			} else {
				fra.molecule = fra.molecule / a;
				fra.denominator = fra.denominator / a;
				System.out.println(fra.molecule + " / " + fra.denominator);
			}
		}
	}


}