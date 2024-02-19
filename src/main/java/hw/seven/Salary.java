package hw.seven;

public class Salary {
    public static int getSum(Employee[] employeeArray) {
        int salary = 0;
        for (int i =0; i < employeeArray.length; i ++) {
            salary += employeeArray[i].salary;
        }
        return salary;
    }
}

