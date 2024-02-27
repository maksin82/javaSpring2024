package hw.nine;

import hw.seven.Employee;

public class hw9 {
    public static void main(String[] args) {
        Employee employee = new Employee(18, "Natan", "man", 2);
        Month[] a = {new Month(1), new Month(2), new Month(3)};

        System.out.println(employee.getSalary(a));

        Manager manager = new Manager(23, "Bill", "man", 2, 1);

        System.out.println(manager.getSalary(a));

        LicensePlate first = new LicensePlate();
        LicensePlate second = new LicensePlate();
        LicensePlate secondd = new LicensePlate();

        System.out.println(first.plate);
        System.out.println(second.plate);
        System.out.println(secondd.plate);
    }
}
