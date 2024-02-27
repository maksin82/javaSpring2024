package hw.ten;

public class Worker extends Employee{

    public Worker(String name) {
        setName(name);
    }

    @Override
    public double getSalary() {
        return getBaseSalary();
    }
}
