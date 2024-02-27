package hw.ten;

public class Director extends Manager{


    public Director(String name, int numberOfSubordinates) {
        super(name, numberOfSubordinates);
    }

    @Override
    public double getSalary() {
        if (getNumberOfSubordinates()==0) {
            return super.getSalary();
        }
        return getBaseSalary() + getBaseSalary() * ((double) getNumberOfSubordinates()/100*9);
    }
}
