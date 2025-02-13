package codeJohnTutorial.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListColl {

	public static void main(String[] args) {
		/*
		 * Ctrl + H - покажет наследование
		 */
		List<String> list = new ArrayList<String>();
		list.add("element 1");
		list.add("element 2");

		ArrayList<Integer> listInt = new ArrayList<>(Arrays.asList(1, 2, 3));
		listInt.add(4);
		System.out.println(listInt);
	}

}
