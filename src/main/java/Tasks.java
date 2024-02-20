import java.text.DecimalFormat;
import java.util.Locale;

public class Tasks {
    //      Написать метод, который принимает на вход десятичное число
//      (например, 10.75), и возвращает строку “10 руб 75 коп”.
    public static String returnRub(double num) {
        String amount = "";
        String fraction = String.valueOf(num);
        int a = fraction.indexOf('.') + 1;
        fraction = fraction.substring(a);

        amount = (int) num + " руб " + fraction + " коп";
        return amount;
    }

//    Написать метод, который принимает на вход десятичное число
//    и возвращает строку “10 кг 75 гр”.

    public static String returnKg(double num) {
        int fraction = (int) ((num - (int) num) * 100);
        String weitgh = String.format("%d кг %d гр", (int) num, fraction);

        return weitgh;
    }

    //    Написать метод, который распечатывает  последовательность чисел
//    в промежутке [12, 13] с шагом 0.1
    public static void printNum(int[] num) {
        for (double i = num[0]; i < num[1]; i += 0.1) {
//            System.out.printf("%.1f%n", i);
            System.out.printf(Locale.ROOT, "%.1f ", i);
        }
    }

    //    Написать метод, который распечатывает последовательность чисел,
    //    кратных 7, в промежутке от 7 исключительно до 14 исключительно.
    public static void printMulSeven() {
        for (int i = 7; i < 14; i++) {
            if (i % 7 == 0) {
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(returnRub(1.86));
        System.out.println(returnKg(11.86));
        printMulSeven();
        printNum(new int[]{12, 13});
    }

}
