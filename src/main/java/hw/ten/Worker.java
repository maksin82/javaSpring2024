package hw.ten;

public class Worker extends Employee {

	public Worker(String name) {
		super(name);
	}

	@Override
	public double getSalary() {
		return baseSalary;
	}

}
