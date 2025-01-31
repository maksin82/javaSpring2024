package liveCoding;

import java.util.*;

/**
 * Написать метод, который принимает на вход строку и ищет в ней самую длинную подстроку,
 * состоящую из одинаковых символов. Если таких подстрок несколько, то искомая подстрока -
 * самая левая. Метод возвращает найденную подстроку. input:
 * 155559999977441111330000000000009
 */
public class Main {

	public static void main(String[] args) {
		var str = "155559999977441111330000000000009";
		System.out.println(getLongSubstring(str));

		List<String> strings = Arrays.asList("a", "b", "c");
		strings.forEach(s -> System.out.println("Hello, World!"));

		Map<Character, Integer> frequencyMap = new LinkedHashMap<>();
		frequencyMap.put('a', 5);
		frequencyMap.put('b', 2);
		frequencyMap.put('r', 2);
		frequencyMap.put('c', 3);
		frequencyMap.put('d', 1);

		Optional<Map.Entry<Character, Integer>> maxEntry = frequencyMap.entrySet()
			.stream()
			.max(Comparator.comparingInt(Map.Entry::getValue));

		maxEntry.ifPresent(entry -> System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue()));

		var test = frequencyMap.entrySet();
		System.out.println(test.stream().min(Comparator.comparingInt(Map.Entry::getValue)));
	}

	private static String getLongSubstring(String string) {
		if ((string == null || string.isEmpty())) {
			throw new IllegalArgumentException("String can't be null");
		}
		if (string.length() == 1) {
			return string;
		}
		var arr = string.toCharArray();
		var arr1 = new LinkedHashMap<Character, Integer>();
		// for (char c : arr) {
		// arr1.compute(c, (_, value) -> value == null ? 1 : value + 1);
		// }

		for (char c : string.toCharArray()) {
			int count = arr1.getOrDefault(c, 0);
			arr1.put(c, count + 1);
		}
		return arr1.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).map(entr -> {
			var stringBuilder = new StringBuilder();
			var temp = entr.getKey();
			stringBuilder.append(String.valueOf(temp).repeat(Math.max(0, entr.getValue())));
			return stringBuilder.toString();
		}).orElseThrow(IllegalArgumentException::new);
	}

}
