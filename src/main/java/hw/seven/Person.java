package hw.seven;

public class Person {

	int age;

	String name;

	String gender;

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		if (gender.equals("man")) {
			return ("Mr. " + name);
		}
		else
			return ("Msr. " + name);
	}

}
