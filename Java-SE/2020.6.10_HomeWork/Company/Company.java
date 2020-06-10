class Company {
	Employee[] employeeArr = new Employee[100];
	int count = 0;
	int index = -1;

	//添加
	void add(Employee employee) {
		employeeArr[count++] = employee;
	}

	//通过员工名字删除员工
	void deleteEmployeeByName(String name) {
		for (int i = 0; i < count; i++) {
			if (employeeArr[i].name == name) {
				//找到需要删除的位置记录其下标
				index = i;
			}
		}

		if (index == -1) {
			//说明没有该员工
			System.out.println("没有该员工!");
			return;
		}

		for (int j = index; j < count; j++) {
			employeeArr[j] = employeeArr[j + 1];
		}

		
	}

	//通过员工名字显示他的工资
	void showSalary(String name) {
		for (int i = 0; i < count; i++) {
			if (employeeArr[i].name == name) {
				//找到需要显示工资的位置记录其下标
				index = i;
			}
		}

		if (index == -1) {
			System.out.println("没有该员工!");
			return;
		}

		System.out.println("该" + employeeArr[index].getName() +"员工的工资为:" + employeeArr[index].getSalary());
	}

	//计算员工本月输出的工资总和
	int countSalaryTotal() {
		int totalSalary = 0;
		for (int i = 0; i < count; i++) {
			totalSalary += employeeArr[i].getSalary();
		}

		return totalSalary;
	}

	//展示员工
	void printArr() {
		for (int i = 0; i < count - 1; i++) {
			System.out.println(employeeArr[i].getName() + ":" + employeeArr[i].getSalary());
		}
	}
}