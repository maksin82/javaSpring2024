package lectures.lec12;

public class Person {

	private final String name;

	public Person(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name + "!";
	}

}
