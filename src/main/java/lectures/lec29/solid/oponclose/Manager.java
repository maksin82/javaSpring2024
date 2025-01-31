package lectures.lec29.solid.oponclose;

public class Manager extends Employee {

	private final int subordinates;

	public Manager(String name, double baseSalary, int subordinates) {
		super(name, baseSalary);
		this.subordinates = subordinates;
	}

	@Override
	double getSalary() {
		return getBaseSalary() * (1 + subordinates * 3 / 100);
	}

}
