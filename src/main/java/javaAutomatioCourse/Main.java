package javaAutomatioCourse;

import java.io.File;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		/*
		 * найти полный путь
		 */
		File file = new File("src/test/java/javaAutomatioCourse/ColectionYP/ReverseListTest.java");

		// Получаем абсолютный путь
		String absolutePath = file.getPath();
		System.out.println("Full Path: " + absolutePath);


		/*
		 * Ctrl + Alt + V - создать выражение
		 */
		ArrayList<Object> arrayList = new ArrayList<>();

		String s = "abba";
		var p = new StringInfo(s);
		assert p.isPalindrome();
	}

	private static void arrayExperiment() {
		int[] arr1 = { 5, 12, 85, 6 };
		// int[] arr2 = {};
		checkMax(arr1, 85);
		checkMax(new int[] {}, Integer.MIN_VALUE);
		checkMax(null, Integer.MIN_VALUE);
	}

	/*
	 * Control + Alt + M - создать метод
	 */

	private static void checkMax(int[] arr, int expValue) {
		var myArr = new ArrayStats(arr);
		int testData = myArr.max();
		assert testData == expValue : "MAX_VALUE = " + testData + " myValue = " + expValue;
		// System.out.printf("MAX_VALUE = %d%nmayValue = %s%n", testData, expValue);
	}

}
