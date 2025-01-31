package codeJohnTutorial.Collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.function.Predicate;

public class ListCollection {

	public static void main(String[] args) {
		LinkedList<String> namesLinkedList = new LinkedList<>();
		namesLinkedList.add("John");
		namesLinkedList.add("Paul");
		namesLinkedList.add("George");
		namesLinkedList.add("Ringo");

		System.out.println(namesLinkedList.get(2));
		namesLinkedList.add(1, "Jerry");

		String[] names = new String[4];
		ArrayList<String> namesArrayList = new ArrayList<>();
		namesArrayList.add("John");
		namesArrayList.add("Paul");
		namesArrayList.add("George");
		namesArrayList.add("Ringo");

		System.out.println(namesArrayList.get(2));
		namesArrayList.add(1, "Jerry");

		String argStr = ListCollection.Seasons.SPRING.toString();
		Seasons arg = Seasons.FALL;

		switch (arg) {
			case WINTER:
				System.out.println("It's winter! Christmas time!");
				break;
			case SUMMER:
				System.out.println("It's summer! Let's go to the beach!");
				break;
			case SPRING:
				System.out.println("It's spring! Easter is coming!");
				break;
			case FALL:
				System.out.println("It's fall! Halloween is coming!");
				break;
		}

		String color = "green";

		switch (color) {
			case "red":
				System.out.println("Color is red");
				break;
			case "green":
				System.out.println("Color is green");
				break;
			case "blue":
				System.out.println("Color is blue");
				break;
			default:
				System.out.println("Color is not red, green or blue");
				break;
		}
		System.out.println(color.intern());

		if (color == "green") {
			System.out.println("Color is true");
		}
		else
			System.out.println("false");
		if (color.equals("green")) {
			System.out.println("Color is true");
		}
		else
			System.out.println("false");
		System.out.println(color.compareTo("green"));

		Predicate<Integer> isEven = num -> num % 2 == 0;
		System.out.println(isEven.test(4));

	}

	public enum Seasons {

		WINTER, SUMMER, SPRING, FALL;

	}

}
