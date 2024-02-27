package hw.seven;

import hw.nine.Month;

public class Employee {
    private int age;
    private String name;
    private String gender;
    private int salaryInDay;
    public int salary;

    public Employee(int age, String name, String gender, int salaryInDay) {
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.salaryInDay = salaryInDay;
    }
    public Employee(String name,int salary) {
        this.name = name;
        this.salary = salary;
    }

    public boolean isSameName(Employee otherEmployee) {
        if (getName() == null) return otherEmployee == null;
        return (otherEmployee.getName().equals( getName()));
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public int getSalaryInDay() {
        return salaryInDay;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSalaryInDay(int salaryInDay) {
        this.salaryInDay = salaryInDay;
    }

    public double getSalary(Month[] monthArray) {
        double salaryInMonth = 0;
        for (int i = 0; i < monthArray.length; i++) {
          salaryInMonth += getSalaryInDay() * monthArray[i].numberOfWorkingDays;
        }
        return salaryInMonth;
    }
}
