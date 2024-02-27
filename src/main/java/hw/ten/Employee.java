package hw.ten;

public class Employee {
    private String name;
    double baseSalary = 100.0;

    public Employee(String name) {
        this.name = name;
    }


    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return getBaseSalary();
    }
}
