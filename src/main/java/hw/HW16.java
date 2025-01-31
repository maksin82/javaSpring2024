package hw;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HW16 {

	public static void main(String[] args) {
		List<Integer> integers = List.of(2, 12, 85, 6, -45, 0);
		List<String> words = List.of("list", "of", "some", "random", "words");

		System.out.println(integers.stream().reduce(new Counters(), (counters, integer) -> {
			counters.addSum(integer);
			counters.addCount(1);
			return counters;
		}, (c1, c2) -> {
			c1.addSum(c2.sum);
			c1.addCount(c1.count);
			return c1;
		}).toAverage());

		System.out.println(integers.stream()
			.mapToInt(Integer::intValue)
			.average()
			.orElseThrow(() -> new IllegalArgumentException("error")));

		System.out.println(words.stream().map(String::toUpperCase).toList());

		System.out.println(integers.stream().filter(a -> a % 2 != 0).reduce((a, b) -> a + b).get());

		System.out.println(words.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toUnmodifiableList()));

		System.out.println((integers.stream().sorted(Integer::compare).toList()).get(integers.size() - 2));

		limits(integers);
	}

	public static void limits(List<Integer> list) {
		Integer minNum = list.stream().min(Integer::compare).get();
		Integer maxNum = list.stream().max(Integer::compare).get();
		Integer sumNum = list.stream().reduce(0, (a, b) -> a + b);

		Integer averNum = (list.stream().reduce(0, (a, b) -> a + b)) / list.size();
		System.out.println("Min: " + minNum + " Max: " + maxNum + " Sum: " + sumNum + " Avg: " + averNum);
	}

	static class Counters {

		private int sum = 0;

		private int count = 0;

		private void addSum(int number) {
			sum += number;
		}

		private void addCount(int countNum) {
			count += countNum;
		}

		public int toAverage() {
			return sum / count;
		}

	}

}
