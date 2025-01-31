package codeJohnTutorial;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class SelectionSort {

	public static void main(String[] args) {
		int[] numbers = new int[10000];
		Random random = new Random();
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = random.nextInt(100000);
		}

		System.out.println(Arrays.toString(numbers));
		long startTime = System.currentTimeMillis();
		selectionSort(numbers);
		long endTime = System.currentTimeMillis();
		System.out.println("Took " + (endTime - startTime) + "ms");
		System.out.println(Arrays.toString(numbers));

		Object simpleCalc = new SimpleCalc();
		System.out.println(simpleCalc.getClass());

		int[] numbers2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 1 };
		Set<Integer> set = Arrays.stream(numbers2).boxed().collect(Collectors.toSet());
		System.out.println(set);

	}

	public static void selectionSort(int[] numbers) {
		int length = numbers.length;

		for (int i = 0; i < length - 1; i++) {
			int min = numbers[i];
			int indexOfMin = i;
			for (int j = i + 1; j < length; j++) {
				if (numbers[j] < min) {
					min = numbers[j];
					indexOfMin = j;
				}
			}
			swap(numbers, i, indexOfMin);
		}
	}

	private static void swap(int[] numbers, int a, int b) {
		int temp = numbers[a];
		numbers[a] = numbers[b];
		numbers[b] = temp;
	}

}
