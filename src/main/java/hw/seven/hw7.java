package hw.seven;

public class hw7 {
    public static void main(String[] args) {
        Person one = new Person();
        one.name = "Mark";
        one.gender = "man";
        one.getName();

        Employee first = new Employee("Mark", 753);

        Employee second = new Employee("Marks", 159);

        System.out.println(second.isSameName(first));

        Employee[] employeeArray = {first, second};
        System.out.println(Salary.getSum(employeeArray));
    }
}
