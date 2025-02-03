package streamAPI;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class lessonStream {

	/**
	 * Вместо int sum = 0; Надо писать int[] sum = new int[0] И не надо будет придумывать
	 * начальное число!
	 * @param s
	 * @return
	 */
	public static int getSum(Stream<Integer> s) {
		// int[] sum = new int[1];
		// s.forEach(i -> sum[0]+=i);
		// return sum[0];
		// forEach = плохой, надо следить надо самим следить за синхронизацией

		return s.reduce(0, (x, y) -> x + y);
	}

	/**
	 * @param s
	 * @return возвращаем Optional
	 */
	public static Optional<Integer> getSumVoid(Stream<Integer> s) {
		return s.reduce((x, y) -> x + y);
	}

	public static void main(String[] args) {
		System.out.println(getSum(Stream.of(1, 2, 3, 4, 5)));

		List<Integer> list = new ArrayList<>();
		Stream<Integer> s = IntStream.range(0, 100).limit(100).mapToObj(i -> 1).parallel();
		System.out.println(list);

		/*
		 * - Пустой стрим: Stream.empty() // Stream<String> - Стрим из List: list.stream()
		 * // Stream<String> - Стрим из Map: map.entrySet().stream() //
		 * Stream<Map.Entry<String, String>> - Стрим из массива: Arrays.stream(array) //
		 * Stream<String> - Стрим из указанных элементов: Stream.of("a", "b", "c") //
		 * Stream<String>
		 */
		System.out.println(IntStream.of(120, 410, 85, 32, 314, 12)
			.filter(x -> x < 300)
			.map(x -> x + 11)
			.limit(3)
			.mapToObj(x -> String.valueOf(x))
			.collect(Collectors.joining("  "))

		);

		String[] strings = new String[] { "a", "b", "c", "d", "e", "f", "g", "h" };
		StringJoiner joiner = new StringJoiner(", ");
		for (String string : strings) {
			joiner.add(string);
		}
		System.out.println(joiner.toString());

		System.out.println(Arrays.stream(strings).collect(Collectors.joining(", ")));

		int[] arr = { 120, 410, 85, 32, 314, 12 };
		int count = 0;
		for (int x : arr) {
			if (x >= 300)
				continue;
			x += 11;
			count++;
			if (count > 3)
				break;
			System.out.print(x);
		}

		/**
		 * Терминальные операции те что закрывают Stream
		 */

		final List<Integer> ints = new ArrayList<>();
		IntStream.range(0, 1000000).forEach(ints::add); // .forEach(i-> ints.add(i));
		System.out.println("\n" + ints.size() + " size");

		String str = Math.random() > 0.5 ? "I'm feeling lucky" : null;
		Stream.ofNullable(str).forEach(System.out::println);

		// generate(Supplier s)
		Stream.generate(() -> "I'm feeling lucky").limit(6).forEach(System.out::println);

		// iterate(T seed, UnaryOperator f)
		Stream.iterate(1, n -> n * 2).limit(6).forEach(System.out::println);

		// iterate(T seed, Predicate hasNext, UnaryOperator f)
		Stream.iterate(1, n -> n <= 32, n -> n * 2).forEach(System.out::println);

		// concat(Stream a, Stream b)
		Stream.concat(Stream.of(1, 2, 3, 3, 3), Stream.of(4, 5, 6, 7)).forEach(System.out::print);
		System.out.println("\n");

		// builder()
		// Declaring an empty Stream
		Stream.Builder<String> str_b = Stream.builder();

		// Inserting elements into the stream
		// using Stream.Builder accept(T t)
		str_b.accept("Geeks");
		str_b.accept("for");
		str_b.accept("GeeksforGeeks");
		str_b.accept("Data Structures");
		str_b.accept("Geeks Classes");

		// Creating the String Stream
		// The stream has now entered the built phase
		Stream<String> stream = str_b.build();

		// printing the elements
		System.out.println("Stream successfully built");
		s.forEach(System.out::println);

		// IntStream.range(int startInclusive, int endExclusive)
		IntStream.range(-2, 3).forEach(System.out::println);
		IntStream.rangeClosed(-2, 3).forEach(System.out::println);

		// filter
		Stream.of(2, 12, 85, 15, 74, 31).filter(x -> x % 2 != 0).forEach(System.out::println);

		IntStream.rangeClosed(2, 9).filter(x -> x % 3 == 0).forEach(System.out::println);

		// map
		Stream.of("2", "5", "64", "46", "87", "1", "685")
			// .map(Integer::parseInt)
			// .map(x-> x + x)
			// .map(x-> x + "x")
			.map(x -> x + "x" + x)
			.forEach(System.out::println);

		Stream.of("10", "11", "32").map(x -> Integer.parseInt(x, 16)).forEach(System.out::println);
		// 16, 17, 50

		Stream.of(2, 3, 0, 1, 3).flatMapToInt(x -> IntStream.range(0, x)).forEach(System.out::println);

		Stream.of(1, 2, 3, 4, 5, 6).flatMap(x -> {
			switch (x % 3) {
				case 0:
					return Stream.of(x, x * x, x * x * 2);
				case 1:
					return Stream.of(x);
				case 2:
				default:
					return Stream.empty();
			}
		}).forEach(System.out::println);
		// 1, 3, 9, 18, 4, 6, 36, 72

		// mapMulti(BiConsumer<T, Consumer<R>> mapper)
		Stream.of(1, 2, 3, 4, 5, 6).flatMap(x -> {
			if (x % 2 == 0) {
				return Stream.of(-x, x);
			}
			return Stream.empty();
		}).forEach(System.out::println);
		// -2, 2, -4, 4, -6, 6
		// А вот так можно переписать с использованием mapMulti:
		Stream.of(1, 2, 3, 4, 5, 6).mapMulti((x, consumer) -> {
			if (x % 2 == 0) {
				consumer.accept(-x);
				consumer.accept(x);
			}
		}).forEach(System.out::println);

		// skip
		Stream.of(5, 10).skip(40).forEach(System.out::println);
		Stream.of(5, 10, 120, 52, 54, 643).skip(4).forEach(System.out::println);

		IntStream.range(0, 10).skip(5).limit(3).skip(1).forEach(System.out::println);
		// 6, 7

		// sorted
		Stream.of(5, 45, 1, -4, 742, 24, 71, -20).sorted().map(x -> x + " ").forEach(System.out::print);
		System.out.println("\n");

		Stream.of(120, 410, 85, 32, 314, 12)
			.sorted(Comparator.reverseOrder())
			.map(x -> x + " ")
			.forEach(System.out::print);
		System.out.println("\n");
		// distinct
		Stream.of(5, 41, 2, 5, 74, 41, 2).distinct().peek(x -> System.out.format("%d ", x)).toList();
		// .map(x -> x + ", ")
		// .forEach(System.out::print);
		System.out.println("\n");

		// peek
		Stream.of(0, 3, 0, 0, 5)
			.peek(x -> System.out.format("before distinct: %d\n", x))
			.distinct()
			.peek(x -> System.out.format("after distinct: %d%n", x))
			.map(x -> x * x)
			.forEach(x -> System.out.format("after map: %d%n", x));

		// takeWhile(Predicate predicate)
		Stream.of(1, 2, 3, 4, 5, 6, 4, 5, 55, 5, 4, 2)
			.takeWhile(x -> x / 2 != 3)
			.forEach(x -> System.out.format("after takeWhile: %d%n", x));
		System.out.println("\n");

		// dropWhile(Predicate predicate)
		IntStream.range(2, 7).dropWhile(x -> x < 5).forEach(System.out::print);
		System.out.println("\n");

		IntStream.of(1, 3, 5, 2, 0, 5, 4).dropWhile(x -> x % 2 == 1).forEach(System.out::println);

		// boxed()
		DoubleStream.of(0.1, Math.PI)
			.boxed()
			.map(Object::getClass)
			// .map((Double aDouble) -> aDouble.getClass())
			.forEach(System.out::println);

		// void forEach(Consumer action)
		Stream.of(120, 410, 85, 32, 314, 12).forEach(x -> System.out.format("%d ", x));

		// reduse
		int sum = Stream.of(1, 2, 3, 4, 5).reduce(0, (a, b) -> a + b);
		System.out.println("\nSum: " + sum);

		Comparator<Integer> comparator = Integer::compareTo;
		Stream.of(1, 2, 3, 4, 5).reduce((a, b) -> comparator.compare(a, b) <= 0 ? a : b);

		Integer minValue = Stream.of(1, 2, 3, 4, -5).reduce(Integer.MAX_VALUE, Integer::min);
		System.out.println(minValue);

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		double average = numbers.stream()
			.mapToDouble(val -> val) // преобразуем Integer в double .average()
			.average()
			.orElse(Double.NaN);
		System.out.println("Среднее значение: " + average);
	}

}
