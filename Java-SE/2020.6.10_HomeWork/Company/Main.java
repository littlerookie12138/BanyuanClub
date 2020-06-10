class Main {

	public static void main(String[] args) {
		Company company = new Company();

		//员工1
		Employee employee1 = new Employee();
		employee1.setName("张三");
		employee1.setWorkTime(205);
		employee1.setBaseSalary(2000);
		employee1.getSalary();
		company.add(employee1);

		//员工2
		Employee employee2 = new Employee();
		employee2.setName("李四");
		employee2.setWorkTime(220);
		employee2.setBaseSalary(2000);
		employee2.getSalary();
		company.add(employee2);

		//员工3
		Employee employee3 = new Employee();
		employee3.setName("王五");
		employee3.setWorkTime(180);
		employee3.setBaseSalary(2000);
		employee3.getSalary();
		company.add(employee3);

		//员工4
		Employee employee4 = new Employee();
		employee4.setName("赵四");
		employee4.setWorkTime(196);
		employee4.setBaseSalary(2000);
		employee4.getSalary();
		company.add(employee4);

		System.out.println("本月应发的薪水总数为" + company.countSalaryTotal());

		company.deleteEmployeeByName("王五");
		company.printArr();
	}
}