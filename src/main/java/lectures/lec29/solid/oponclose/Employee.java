package lectures.lec29.solid.oponclose;

public class Employee {

	private final String name;

	private final double baseSalary;

	public Employee(String name, double baseSalary) {
		this.name = name;
		this.baseSalary = baseSalary;
	}

	final double getBaseSalary() { // final - наследник не может переписать/изменить эту
									// имплементацию
		return baseSalary;
	}

	double getSalary() {
		return getBaseSalary();
	}

	public String getName() {
		return name;
	}

}
