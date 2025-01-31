package javaAutomatioCourse;

public class ArrayStats {

	private int[] arr;

	public ArrayStats(int[] arr) {
		this.arr = arr;
	}

	public int max() {
		try {
			if (arr == null || arr.length == 0) {
				throw new IllegalArgumentException("Its empty");
			}
			int max = arr[0];
			for (int i : arr) {
				if (i > max) {
					max = i;
				}
			}
			return max;
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return Integer.MIN_VALUE;
		}
	}

}
