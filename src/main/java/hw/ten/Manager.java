package hw.ten;

public final class Manager extends Employee {
    private int numberOfSubordinates;
    double baseSalary = super.getBaseSalary();

    public Manager(String name, int numberOfSubordinates) {
        super(name);
        this.numberOfSubordinates = numberOfSubordinates;
    }

    public int getNumberOfSubordinates() {
        return numberOfSubordinates;
    }

    public void setNumberOfSubordinates(int numberOfSubordinates) {
        this.numberOfSubordinates = numberOfSubordinates;
    }

    @Override
    public double getSalary() {
        if (numberOfSubordinates == 0) {
            return getSalary();
        }
        return getBaseSalary() + getBaseSalary() * ((double) numberOfSubordinates / 100 * 3);
    }
}
