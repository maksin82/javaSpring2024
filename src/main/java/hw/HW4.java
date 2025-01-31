package hw;

import java.util.ArrayList;

public class HW4 {

	public static void main(String[] args) {
		int[] array = { 9, 2, 6, 4, 5, 12, 7, 8, 6 };

		numbersPrint();
		System.out.println("\n_numbersPrint_____________");

		powFive();
		System.out.println("\n_powFive_____________");

		multFour1();
		System.out.println("\n_multFour1_____________");

		multFour2();
		System.out.println("\n______________");

		arrPrintOdd(array);

		System.out.println("\n______________");
		moreThenFive(array);
		System.out.println("\n______________");

		System.out.println(moreThenFive2(array));
		System.out.println("\n______________");

		addFifteen(array);
		System.out.println("\n______________");

	}

	// 4.1.1
	public static void numbersPrint() {
		// for (int i = 0; i <= 15; i++) {
		// System.out.print(i + " ");
		// }
		int i = 0;
		while (i <= 15) {
			System.out.print(i + " ");
			i++;
		}
	}

	// 4.1.2
	public static ArrayList<Integer> powFive() {
		// for (int i = 1; i < 10_000; i *= 5) {
		// System.out.println(i + " ");
		// }
		ArrayList<Integer> array = new ArrayList<>();
		int i = 1;
		do {
			array.add(i);
			i = i * 5;
		}
		while (i < 10_000);
		return array;
	}

	// 4.1.3
	public static ArrayList<Integer> multFour1() {
		ArrayList<Integer> arrayList = new ArrayList<>();
		for (int i = 40; i <= 60; i++) {
			if (i % 4 == 0) {
				arrayList.add(i);
			}
		}
		return arrayList;
	}

	public static void multFour2() {
		for (int i = 40; i <= 60; i += 4) {
			System.out.print(i + " ");
		}
	}

	// 4.2.1
	public static void arrPrintOdd(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 != 0) {
				System.out.print(arr[i] + " ");
			}
		}
	}

	public static void moreThenFive(int[] arr) {
		int i = 0;
		for (; i < arr.length;) {
			if (arr[i] > 5) {
				System.out.print(arr[i] + " ");
			}
			i++;
		}
	}

	public static String moreThenFive2(int[] arr) {
		String s = "";
		if (arr.length > 5) {
			s = "It is more then 5";
		}
		else
			throw new IllegalArgumentException("need more then 5");
		return s;
	}

	public static void addFifteen(int[] arr) {
		for (int i : arr) {
			System.out.print(i + 15 + " ");
		}
	}

}
