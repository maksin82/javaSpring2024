package hw.nine;

public abstract class BaseEmployee {
    private int age;
    private String name;
    private String gender;
    private int salaryInDay;

    public BaseEmployee(int age, String name, String gender, int salaryInDay) {
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.salaryInDay = salaryInDay;
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


    public abstract double getSalary(Month[] monthArray);
}
