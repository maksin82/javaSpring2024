package hw.seven;

public class hw7 {
    public static void main(String[] args) {
        Person one = new Person();
        one.name = "Mark";
        one.gender = "man";
        one.getName();

        Employee first = new Employee();
        first.name = "Mark";
        first.salary = 753;

        Employee second = new Employee();
        second.name = "Marks";
        second.salary = 159;

        System.out.println(second.isSameName(first));

        Employee[] employeeArray = {first, second};
        System.out.println(Salary.getSum(employeeArray));
    }
}
