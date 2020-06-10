class Main {

	public static void main(String[] args) {
		//初始化第一个分数
		Fraction f = new Fraction();
		f.setMolecule(2);
		f.setDenominator(3);

		//初始化第二个分数
		Fraction r = new Fraction();
		r.setMolecule(3);
		r.setDenominator(4);

		Fraction newFra = new Fraction();
		newFra = f.multiply(r);

		Fraction newFra1 = new Fraction();
		newFra1 = f.plus(r);

		System.out.println(f.toDouble());
		newFra.printFra(newFra);
		newFra.printFra(newFra1);
	}
}