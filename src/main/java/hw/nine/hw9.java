package hw.nine;

public class hw9 {

	public static void main(String[] args) {

		Employee employee = new Employee(28, "Linda", "woman", 5);

		Month[] a = { new Month(3), new Month(4), new Month(5) };

		System.out.println(employee.getSalary(a));

		Manager manager = new Manager(23, "Bill", "man", 2, 1);

		System.out.println(manager.getSalary(a));

		System.out.println(manager.getSalary(MonthUtils.yearArr()));
		System.out.println(manager.getSalary(MonthUtils.halfYearArr()));

		LicensePlateMaker nyMaker = new LicensePlateMaker("NY");
		LicensePlateMaker caMaker = new LicensePlateMaker("CA");

		LicensePlate plate1 = nyMaker.getCarNum();
		LicensePlate plate2 = nyMaker.getCarNum();
		LicensePlate plate3 = caMaker.getCarNum();
		LicensePlate plate4 = nyMaker.getCarNum();
		LicensePlate plate5 = caMaker.getCarNum();

		System.out.println(plate1.getPlate());
		System.out.println(plate2.getPlate());
		System.out.println(plate3.getPlate());
		System.out.println(plate4.getPlate());
		System.out.println(plate5.getPlate());

		System.out.println(nyMaker.getCarNum().getPlate());
		System.out.println(nyMaker.getCarNum().getPlate());
		System.out.println(nyMaker.getCarNum().getPlate());
		System.out.println(nyMaker.getCarNum().getPlate());
		System.out.println(nyMaker.getCarNum().getPlate());
	}

}
