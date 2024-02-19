package hw.seven;

public class Employee {
    int age;
    String name;
    String gender;
    int salary;

    public boolean isSameName(Employee otherEmployee) {
        if (name == null) return otherEmployee == null;
        return (otherEmployee.name.equals(name));
    }

    public String getName() {
        return name;
    }
}
