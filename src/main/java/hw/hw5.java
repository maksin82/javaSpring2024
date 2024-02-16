package hw;

import java.util.Arrays;

public class hw5 {
    public static void main(String[] args) {
        int[] array = {9, 2, 6, 4, 5, 12, 7, 8, 6};
        int[][] arr = {{1, 2, 3, 4, 5}, {6, 7, 8, 9}, {-1, -2, -3, -4}, {-5, -6}};

        System.out.println("Задача №1: " + printSum(array));

        System.out.println("Задача №2: " + maxInt(array));

        System.out.println("Задача №3: " + minInt(array));

        System.out.println("Задача №4: " + avg(array));

        System.out.println("Задача №5: " + sumArrays(arr));

        System.out.println("Задача №6: " + maxArrays(arr));

        System.out.println("Задача №7: " + countArrays(arr));

        pyramidLeft();
        pyramidRight();
        pyramid();

    }


    //5.1.1
    public static int printSum(int[] arr) {
        int sumArr = 0;
        for (int i : arr) sumArr += i;
        return sumArr;
    }

    //5.1.2
    public static int maxInt(int[] arr) {
        int maxInt = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (maxInt < arr[i]) {
                maxInt = arr[i];
            }
        }
        return maxInt;
    }

    //5.1.3
    public static int minInt(int[] arr) {
        int minInt = arr[0];
        for (int i : arr) {
            if (minInt > i) {
                minInt = i;
            }
        }
        return minInt;
    }

    //5.1.4
    public static int avg(int[] arr) {
        int avg = 0;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            avg += arr[i];
            count++;
        }
        return avg / count;
    }

    //5.1.5
    public static int sumArrays(int[][] arr) {
        int sumArr = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                sumArr += arr[i][j];
            }
        }
//        for (int[] a: arr) {
//            for (int b: a) {
//                sumArr += b;
//            }
//        }
        return sumArr;
    }

    //5.1.6
    public static int maxArrays(int[][] arr) {
        int maxArr = arr[0][0];
//        for (int[] a : arr) {
//            for (int b : a) {
//                if (maxArr < b) {
//                    maxArr = b;
//                }
//            }
//        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (maxArr < arr[i][j]) {
                    maxArr = arr[i][j];
                }
            }
        }
        return maxArr;
    }

    //5.1.7
    public static int countArrays(int[][] arr) {
        int count = 0;
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr[i].length; j++) {
//                count++;
//            }
//        }
//    }

//        for (int[] a: arr) {
//            for (int b: a) {
//                count++;
//            }
//        }
        for (int[] a : arr) {
            count += (int) Arrays.stream(a).count();
        }
        return count;
    }

    //5.2.1
    public static void pyramidLeft() {
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println("");
        }
    }

    //5.2.2
    public static void pyramidRight() {
        int count = 9;
        for (int i = 9; i >= 0; i--) {
            count = 9 - i;
            System.out.print("  ".repeat(count));
            for (int j = 0; j <= i; j++) {
                System.out.print(j + " ");

            }
            System.out.println("");
        }
    }

    //5.2.3
    public static void pyramid() {
        
    }
}
