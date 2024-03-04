package hw.seven;

public final class Employee extends Person {
    public int salary;

    public Employee(String name, int salary) {
        super(name);
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getSalary() {
        return salary;
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

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean isSameName(Employee otherEmployee) {
        if (getName() == null) return otherEmployee == null;
        return (otherEmployee.getName().equals( getName()));
    }


}
