class Employee {

	int workTime = 0;
	String name;
	int baseSalary = 0;
	int salary = 0;

	//设置工时
	void setWorkTime(int time) {
		this.workTime = time;
	}

	//设置员工姓名
	void setName(String aName) {
		this.name = aName;
	}

	//设置基本工资
	void setBaseSalary(int baseSalary) {
		this.baseSalary = baseSalary;
	}

	//获取工时
	int getWorkTime(){
		return workTime;
	}

	//获取员工姓名
	String getName() {
		return name;
	}

	//计算薪水
	int countSalary() {
		if (workTime > 196) {
			salary = baseSalary + (workTime - 196) * 200;
			return salary;
		} else {
			salary = baseSalary;
			return salary;
		}
	}

	//获取员工薪水
	int getSalary() {
		salary = countSalary();
		return salary;
	}

	
}