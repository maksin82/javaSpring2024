package hw.ten;

public final class Director extends Employee{


    private int numberOfSubordinates;


    public Director(String name, int numberOfSubordinates) {
        super(name);
        this.numberOfSubordinates = numberOfSubordinates;
    }

    @Override
    public double getSalary() {
        if (getNumberOfSubordinates()==0) {
            return getSalary();
        }
        return getBaseSalary() + getBaseSalary() * ((double) getNumberOfSubordinates()/100*9);
    }

    public int getNumberOfSubordinates() {
        return numberOfSubordinates;
    }
}
