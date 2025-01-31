package hw.nine;

public final class Manager extends BaseEmployee {

	int numberOfSubordinates;

	public Manager(int age, String name, String gender, int salaryInDay, int numberOfSubordinates) {
		super(age, name, gender, salaryInDay);
		this.numberOfSubordinates = numberOfSubordinates;
	}

	@Override
	public double getSalary(Month[] monthArray) {
		double salaryInMonth = 0;
		for (Month month : monthArray) {
			salaryInMonth += getSalaryInDay() * month.numberOfWorkingDays;
		}
		return salaryInMonth + salaryInMonth * 0.1;
	}

}