package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(4);
        TreeNode right = new TreeNode(7);
        TreeNode tree = new TreeNode(10, left, right);

        System.out.println(checkTree(tree));

        int[] a = {1, 4, 5, 10};
        int[] copyA = Arrays.copyOf(a, a.length);
        System.out.println(Arrays.toString(runningSum1(a)));
        System.out.println(Arrays.toString(runningSum2(a)));
        System.out.println(Arrays.toString(runningSum3(copyA)));
        System.out.println(Arrays.toString(runningSum4(a)));
        System.out.println(Arrays.toString(runningSum5(a)));
        System.out.println(Arrays.toString(runningSumRecursive(a)));

        int[][] accounts = {{1, 5}, {7, 3}, {3, 5}};
        System.out.println(maximumWealth1(accounts));
        System.out.println(maximumWealth2(accounts));
        System.out.println(maximumWealth3(accounts));
        System.out.println(maximumWealth4(accounts));

        var n = 14;
        System.out.println(fizzBuzz1(n));
        System.out.println(fizzBuzz2(n));
        System.out.println(fizzBuzz3(n));

        System.out.println(numberOfSteps(n));
    }

    //In one step, if the current number is even, you have to divide it by 2, otherwise, you have to subtract 1 from it.
    private static int numberOfSteps(int n) {
        int acc = 0;
        while (n > 0) {
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n --;
            }
            acc++;
        }
        return acc;
    }

    private static List<String> fizzBuzz3(int n) {
        List<String> answer = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            StringBuilder sb = new StringBuilder();

            if (i % 3 == 0) sb.append("Fizz");
            if (i % 5 == 0) sb.append("Buzz");
            answer.add(sb.length() == 0 ? String.valueOf(i) : sb.toString());
        }
        return answer;
    }

    private static List<String> fizzBuzz2(int n) {
        List<String> answer = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            boolean div3 = i % 3 == 0;
            boolean div5 = i % 5 == 0;
            if (div3 && div5) {
                answer.add("FizzBuzz");
            } else if (div3) {
                answer.add("Fizz");
            } else if (div5) {
                answer.add("Buzz");
            } else {
                answer.add(String.valueOf(i));
            }
        }
        return answer;
    }

    /*
    answer[i] == "FizzBuzz" if i is divisible by 3 and 5.
    answer[i] == "Fizz" if i is divisible by 3.
    answer[i] == "Buzz" if i is divisible by 5.
    answer[i] == i (as a string) if none of the above conditions are true.
*/

    /*    Многократные вычисления i % 3 и i % 5 для одного числа (до 3 раз за итерацию).
    Неинициализированная емкость ArrayList (может привести к реаллокациям при больших n).*/
    private static List<String> fizzBuzz1(int n) {
        List<String> answers = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            answers.add(i % 3 == 0 && i % 5 == 0 ? "FizzBuzz" : i % 3 == 0 ? "Fizz" : i % 5 == 0 ? "Buzz" : String.valueOf(i));
        }
        return answers;
    }


    private static int maximumWealth4(int[][] accounts) {
        return Arrays.stream(accounts)
                .mapToInt(customer -> Arrays.stream(customer).sum())
                .max()
                .orElse(0);
    }

    private static int maximumWealth3(int[][] accounts) {
        int maxWealth = 0;
        for (int[] customer : accounts) {
            int currentWealth = 0;
            for (int money : customer) {
                currentWealth += money;
            }
            maxWealth = Math.max(maxWealth, currentWealth);
        }
        return maxWealth;
    }

    private static int maximumWealth2(int[][] accounts) {
        var maxWealth = 0;

        for (int[] customer : accounts) {
            maxWealth = Math.max(maxWealth, Arrays.stream(customer).sum());
        }
        return maxWealth;
    }

    /*time complexity = O(n*m)
     * space complexity = O(1)*/
    private static int maximumWealth1(int[][] accounts) {
        int maxWealth = 0;
        for (int i = 0; i < accounts.length; i++) {
            int currentWealth = 0;
            for (int j = 0; j < accounts[i].length; j++) {
                currentWealth += accounts[i][j];
            }

            maxWealth = Math.max(maxWealth, currentWealth);
//            if (currentWealth > maxWealth) {
//                maxWealth = currentWealth;
//            }
        }
        return maxWealth;
    }

    /*time complexity = O(n^2)
     * space complexity = */
    private static int[] runningSum4(int[] arr) {
        if (arr == null || arr.length == 0) return arr;

        return IntStream.range(0, arr.length)
                .map(i -> IntStream.range(0, i + 1).map(j -> arr[j]).sum())
                .toArray();
    }

    private static int[] runningSum5(int[] arr) {
        int[] sum = {0};
        return Arrays.stream(arr)
                .map(x -> sum[0] += x)
                .toArray();
    }

    /*most effective
    time complexity = O(n)
     * space complexity = O(1)*/
    private static int[] runningSum3(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            arr[i] += arr[i - 1];
        }
        return arr;
    }

    /*time complexity = O(n)
     * space complexity = O(n)*/
    private static int[] runningSum2(int[] arr) {
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = arr[i] + sum[i - 1];
        }
        return sum;
    }

    /*time complexity = O(n)
     * space complexity = O(n)*/
    private static int[] runningSum1(int[] arr) {
        int[] sum = new int[arr.length];
        int acc = 0;

        for (int i = 0; i < arr.length; i++) {
            acc += arr[i];
            sum[i] = acc;
        }
        return sum;
    }

    private static int[] runningSumRecursive(int[] arr) {
        if (arr.length <= 1) return arr;
        runningSumRecursiveHelper(arr, 1);
        return arr;
    }

    private static void runningSumRecursiveHelper(int[] arr, int i) {
        if (i >= arr.length) return;
        arr[i] += arr[i - 1];
        runningSumRecursiveHelper(arr, i + 1);
    }

    public static boolean checkTree(TreeNode root) {
        return root.val == root.left.val + root.right.val;
    }

}
