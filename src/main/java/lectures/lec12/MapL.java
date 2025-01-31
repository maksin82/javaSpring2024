package lectures.lec12;

import java.util.HashMap;
import java.util.Map;

public class MapL {

	public static void main(String[] args) {
		Map<Integer, Person> people = new HashMap<>();
		people.put(100, new Person("Vasya"));
		people.put(22, new Person("Nikolaj"));
		people.put(23, new Person("Nikolaj"));
		people.put(22, new Person("Petr"));

		System.out.println(people);

		System.out.println(people.get(100));
		System.out.println(people.get(1000));
		System.out.println(people.getOrDefault(100, new Person("Who")));
		System.out.println(people.getOrDefault(1000, new Person("Who")));

		for (Integer key : people.keySet()) {
			System.out.println("Key = " + key + ", value = " + people.get(key));
		}

		String string = "khgkdfhgkhgkhgssfslasdfghjqwerttrewqzxcvmnbvcxzclfiojghbdtnsdsi;x";
		Map<Character, Integer> charCounts = new HashMap<>();
		for (char c : string.toCharArray()) {
			int count = charCounts.getOrDefault(c, 0);
			charCounts.put(c, count + 1);
		}

		System.out.println(charCounts);

		var test = charCounts.entrySet();
		System.out.println(test.toArray()[0]);
	}

}
