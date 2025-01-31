package hw.nine;

public final class Employee extends BaseEmployee {

	public Employee(int age, String name, String gender, int salaryInDay) {
		super(age, name, gender, salaryInDay);
	}

	@Override
	public double getSalary(Month[] monthArray) {
		double salaryInMonth = 0;
		for (Month month : monthArray) {
			salaryInMonth += getSalaryInDay() * month.numberOfWorkingDays;
		}
		return salaryInMonth;
	}

}
