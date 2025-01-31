package lectures.itrator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<String> strings = List.of("one", "two", "three");
		Iterator<String> iterator = strings.iterator();

		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		// одинаково
		for (Iterator<String> itr = strings.iterator(); itr.hasNext();) {
			System.out.println(itr.next());
		}
		// это и есть итератор
		for (String str : strings) {
			System.out.println(str);
		}

		List<Integer> integers = new ArrayList<>(List.of(1, 2, 3, 4));
		for (Iterator<Integer> i = integers.iterator(); i.hasNext();) {
			int nextInt = i.next();
			if (nextInt == 2) {
				i.remove(); // Используем итератор для удаления элемента
				// integers.add(123); - Здесь есть ошибка, связанная с модификацией списка
				// во время итерации.
				// Ты используешь итератор для перебора элементов списка, но внутри
				// итерации изменяешь сам список методом remove без использования
				// итератора.
				// Это может вызвать ConcurrentModificationException.
			}
			else {
				System.out.println(i);
			}
		}

		RandomInteger randomInteger = new RandomInteger(12);
		System.out.println(randomInteger.neXtInt());

	}

}
