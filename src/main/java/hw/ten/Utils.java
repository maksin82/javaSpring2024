package hw.ten;

public final class Utils {
    static void findName(String name, Employee[] arr) {
        String result = "";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getName().equals(name)) {
                result = String.valueOf(arr[i].getClass());
            }
        }
        if (result.isEmpty()) {
            result = "не работает";
        }
        System.out.println("он тут " + result);
    }

    static void findLikeName(String name, Employee[] arr) {
        String result = "";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getName().contains(name)) {
                result += String.valueOf(arr[i].getClass());
            }
        }
        if (result.isEmpty()) {
            result = "не работает";
        }
        System.out.println("он тут: " + result);
    }

    static void calcSalaryBudget(Employee[] arr) {
        double salaryBudget = 0;
        for (Employee emp : arr) {
            salaryBudget += emp.getSalary();
        }
        System.out.println(salaryBudget);
    }

    static void calcSalaryMax(Employee[] arr) {
        double salaryMin = arr[0].getSalary();
        for (Employee emp : arr) {
            if (salaryMin < emp.getSalary()) {
                salaryMin = emp.getSalary();
            }
        }
        System.out.println(salaryMin);
    }

    static void calcSalaryMin(Employee[] arr) {
        double salaryMin = arr[0].getSalary();
        for (Employee emp : arr) {
            if (salaryMin > emp.getSalary()) {
                salaryMin = emp.getSalary();
            }
        }
        System.out.println(salaryMin);
    }

    static void findMinSubordinates(Manager[] arr) {
        int minSubordinates = arr[0].getNumberOfSubordinates();
        for (Manager m : arr) {
            if (minSubordinates > m.getNumberOfSubordinates()) {
                minSubordinates = m.getNumberOfSubordinates();
            }
        }
        System.out.println(minSubordinates);
    }
    static void findMaxSubordinates(Manager[] arr) {
        int maxSubordinates = arr[0].getNumberOfSubordinates();
        for (Manager m : arr) {
            if (maxSubordinates < m.getNumberOfSubordinates()) {
                maxSubordinates = m.getNumberOfSubordinates();
            }
        }
        System.out.println(maxSubordinates);
    }

//static void searchHighest
}
