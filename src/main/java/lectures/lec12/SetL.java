package lectures.lec12;

import java.util.HashSet;
import java.util.Set;

public class SetL {

	public static void main(String[] args) {
		Set<String> mySet = new HashSet<>();
		mySet.add("You");
		mySet.add("You");
		mySet.add("Me");
		mySet.add("They");

		System.out.println(mySet.size());
		System.out.println(mySet);

		Set<Person> people = new HashSet<>();
		Person vasya = new Person("Vasya");
		people.add(vasya);
		people.add(vasya);

		people.add(new Person("Vasya"));
		people.add(new Person("Vasya"));
		System.out.println(people.size());
	}

}
