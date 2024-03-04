package hw.seven;

public class hw7 {
    public static void main(String[] args) {
        Person one = new Person("Mark");
        one.gender = "man";
        one.getName();
        Employee first = new Employee("Mark", 350);
        Employee second = new Employee("Marks", 600);

        System.out.println(second.isSameName(first));

        Employee[] employeeArray = {first, second};
        System.out.println(Salary.getSum(employeeArray));
    }
}
