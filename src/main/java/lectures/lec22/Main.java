package lectures.lec22;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		List<Integer> integers = List.of(2, 12, 85, 6, -45, 0);
		List<String> words = List.of("list", "of", "some", "42", "random", "words");
		List<Integer> doubleInt = new ArrayList<>();
		// for (int dInt : integers) {
		// doubleInt.add(dInt*2);
		// }
		// for (String str : words) {
		// doubleInt.add(str.length());
		// }

		// doubleInt = words.stream().mapToInt( String :: length).boxed().toList();
		doubleInt = words // *List<String>
			.stream() // *Stream<String>
			.map(new Function<String, Integer>() { // .map(str -> str.length()) - лямбда
				@Override
				public Integer apply(String str) {
					return str.length();
				}
			}) // *Stream<Integer>
			.filter(i -> i < 3) // filter-boolin, вернет в поток если true
			.distinct() // .map - это не тип данных
			.toList(); // *List<Integer>
		// .collect(Collectors.toUnmodifiableList()); старый вариант
		System.out.println(doubleInt);

		List<Character> strIs = words.stream()
			.flatMap(string -> string.chars().mapToObj(i -> (char) i)) // *Stream<Charecter>
			.filter(s -> Character.isLetter(s))
			.toList();
		System.out.println(strIs);

		boolean strOrNumIsIt = words.stream().flatMap(string -> string.chars().mapToObj(i -> (char) i)).allMatch(s -> {
			boolean isLetter = Character.isLetter(s);
			boolean isNumber = Character.isDigit(s);
			return isLetter || isNumber;
		});
		System.out.println(strOrNumIsIt);

		boolean strIsF = words.stream()
			.flatMap(string -> string.chars().mapToObj(i -> (char) i))
			.anyMatch(s -> s == 'f');
		System.out.println(strIsF);

		String longest = words.stream().reduce((a, b) -> a.length() > b.length() ? a : b).get();
		System.out.println(longest);

		System.out.println(integers.stream() // при такой записи, аккумулятор типа
												// отличного от
			.reduce(0, (acc, number) -> acc + number, (sum1, sum2) -> sum1 + sum2));
		System.out.println(integers.stream().reduce((a, b) -> a + b));

		System.out.println(integers.stream().reduce(0, Integer::sum));

		Stream<Integer> mystream = Stream.iterate(1, i -> i + 1)
			.filter(i -> i % 2 == 0)
			.peek(i -> System.out.println("Filtered value: " + i))
			.map(i -> i * 3)
			.peek(i -> System.out.println("Multiply value: " + i));

		System.out.println("===================");
		System.out.println(mystream.limit(20)
			// .collect(Collectors.toUnmodifiableList())); //toList
			.collect(Collectors.counting()));

		System.out.println(words.stream()
			.flatMap(s -> s.chars().mapToObj(i -> (char) i))
			.collect(Collectors.groupingBy(c -> c))
			.entrySet()
			.stream()
			.collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue().size())));
	}

}
