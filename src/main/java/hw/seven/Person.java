package hw.seven;

public class Person {
    int age;
    String name;
    String gender;

    public void getName() {
        if (gender.equals("man")) {
            System.out.println("Mr. " + name);
        } else System.out.println("Msr. " + name);
    }
}


