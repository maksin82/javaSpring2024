package hw.ten;

import static hw.ten.Utils.*;

public class hw10 {
    public static void main(String[] args) {
        Worker w1 = new Worker("Max");
        Worker w2 = new Worker("Maxx");
        Worker w3 = new Worker("Maxxx");

        System.out.println(w1.getName() + " " + w1.getSalary());

        Manager m1 = new Manager("Ted", 2);
        Manager m2 = new Manager("Teddy", 1);
        Manager m3 = new Manager("Todeus", 0);

        System.out.println(m1.getName() + " " + m1.getSalary());
        System.out.println(m3.getSalary());

        Director d1 = new Director("Ron", 6);
        Director d2 = new Director("Ronn", 0);

        System.out.println(d1.getName() + " have " + d1.getSalary() + " and people: " + d1.getNumberOfSubordinates());
        System.out.println(d2.getSalary());

        Employee[] eArr = {w1, w2, w3, m1, m2, m3, d1, d2};
        Manager[] mArr = {m1, m2, m3, d1, d2};

        findName("Teld", eArr);

        findLikeName("M", eArr);

        Utils.calcSalaryBudget(eArr);
        Utils.calcSalaryMax(eArr);
        Utils.calcSalaryMin(eArr);

        findMaxSubordinates(mArr);
        findMinSubordinates(mArr);



    }

}
