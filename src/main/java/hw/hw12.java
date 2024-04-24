package hw;

import java.util.*;
import java.util.stream.Collectors;

public class hw12 {
    public static void main(String[] args) {
        List<String> colors = new ArrayList<>();
        colors.add("White");
        colors.add("Tan");
        colors.add("Yellow");
        colors.add("Orange");
        colors.add("Red");
        colors.add("Pink");
        colors.add("Purple");
        colors.add("Blue");

        List<String> badColors = new ArrayList<>();
        for (String color : colors) {
            if (color.contains("l")) {
                badColors.add(color);
            }
        }
        colors.removeAll(badColors);

        System.out.println(colors);

        List<Integer> integers = new ArrayList<>();
        for (int i = 100; i <= 1000; i++) {
            integers.add(i);
        }
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()) {
            Integer number = iterator.next();
            if (number % 2 == 0) {
                iterator.remove();
            }
        }
        List<Integer> integersOdd = new ArrayList<>();
        for (Integer integer : integers) {
            if (integer % 2 != 0) {
                integersOdd.add(integer);
            }
        }

        System.out.println(integers);
        System.out.println(integersOdd);
        System.out.println(integers.stream().filter(i -> i % 2 != 0).toList());

        String text = "Lorem ipsum dolor sit amet consectetur adipiscing elit Vivamus at condimentum urna Pellentesque venenatis pharetra ullamcorper Sed pharetra nisi sed ipsum mattis ut dignissim justo ullamcorper Ut ante mi faucibus";
        List<String> thirtyText = new ArrayList<>();
        for (String s : text.split(" ")) {
            thirtyText.add(s.toLowerCase());
        }

        Map<Character, String> longestWord = new HashMap<>();
        for (String s : thirtyText) {
            char ch = s.charAt(0);
            if (!longestWord.containsKey(ch) || longestWord.get(ch).length() < s.length()) {
                longestWord.put(ch, s);
            }
        }

        System.out.println(longestWord);
    }
}
