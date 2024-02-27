package hw.nine;

import static hw.nine.MonthUtils.*;

public class Month {
    String monthName;
    int numberOfDays;
    public int numberOfWorkingDays;


    public Month(String monthName, int numberOfDays, int numberOfWorkingDays) {
        this.monthName = monthName;
        this.numberOfDays = numberOfDays;
        this.numberOfWorkingDays = numberOfWorkingDays;
    }
    public Month(String monthName) {
        this.monthName = getMonth(monthName);;
        this.numberOfDays = getNumberOfDays();
        this.numberOfWorkingDays = getNumberOfWorkingDays();
    }
    public Month(int monthName) {
        this.monthName = getMonth(monthName);
        numberOfDays = getNumberOfDays();
        this.numberOfWorkingDays = getNumberOfWorkingDays();
    }


    @Override
    public String toString() {
        return "In " + monthName  +
                " numberOfDays = " + numberOfDays +
                " and numberOfWorkingDays = " + numberOfWorkingDays;
    }

    public static void main(String[] args) {

        Month m = new Month("feb");
        System.out.println(m.toString());

        Month m1 = new Month(1);
        System.out.println(m1.toString());

        Month m2 = new Month("September", 30, 21);
        System.out.println(m2);

        Month m3 = new Month("October");
        System.out.println(m3);
    }
}
