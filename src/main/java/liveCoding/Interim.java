package liveCoding;

import java.util.*;
import java.util.stream.Collectors;

public class Interim {
    public static void main(String[] args) {
        String data = "Hnskdfk Ysualsefo oevasd";

        int[] num = new int[20];
        Arrays.setAll(num, i -> (int) (Math.random() * 255));

        List<Integer> integerList = List.of(2, 12, 2, 5, 4, 8, 4, 7, 7, 8, 6, 3, 2, 54, 12);
        String[] numStr = {"1", "2", "2", "3", "16", "1"};
        List<String> list = List.of("alisa:login", "nikita:login", "alisa:logout");
//I love JAVA
        String s = "I love JAVA";
        String s1 = "ВРаботать нужно не 12 часов, а головой!"; //посчитать 'В'
        System.out.println(countLettersMap(s1));

        String[] emailList = {"etst@mail.ru", "123-kind@mail.com", "1234568@jfl.", "@mail.ru"};
//        countLetterV(s1, 'в');
        System.out.println(isValidEmail(emailList));
//        System.out.println(s1.toLowerCase().split("в").length - 1);
    }

    private static Map<String, Boolean> isValidEmail(String[] emailList) {
        Map<String, Boolean> map = new HashMap<>();
        for (String s : emailList) {
         map.put(s, s.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,7}$"));
        }
        return map;
    }

    //проверить соответствие элементов массива шаблону e-mail


    private static Map<String, Integer> countLettersMap(String string) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < string.split("\\s").length; i++) {
            map.put(string.trim().split("\\s+")[i], string.trim().split("\\s+")[i].length());
        }
        return map;
    }

    private static String countLetters(String str) {
        String[] arr = str.trim().split("\\s+");
        int[] counted = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            counted[i] += arr[i].length();
        }
        return Arrays.toString(counted);
    }


    private static void countLetterV(String str, char target) {
        if (str == null || str.isEmpty()) {
            System.out.println(0);
            return;
        }

        long count = 0;

//        char[] word = str.toLowerCase().toCharArray(); //не эффективно
//        for (char c:word) {
//            if (c == 'в') {
//                count++;
//            }
//        }

//        for (int i = 0; i < str.length(); i++) {
//            if (Character.toLowerCase(str.charAt(i)) == 'в') count++;
//        }
//
//        for (char c: str.toLowerCase().toCharArray()) {
//         if (c=='в') {
//             count++;
//         }
//        }

//        long count = Optional.ofNullable(str)//с проверками
//                .orElse("")
//                .toLowerCase()
//                .chars()
//                .filter(c -> c == 'в')
//                .count();

        count = str.toLowerCase().chars()
                .filter(a -> a == target)
                .count();

        System.out.println(count);
    }

    private static String newLine(String s) {
        String[] words = s.trim().split("\\s+"); //
        return String.join("\n", words);

//        String[] arr = s.split(" "); плохой вариант
//        String news = "";
//        for (String one:arr) {
//            news += one + "\n";
//        }
//        return news;

//        String[] arr = s.split("\\s+"); //StringBuilder лучше конкотинации
//        StringBuilder news = new StringBuilder();
//        for (String one : arr) {
//            news.append(one).append("\n");
//        }
//        return news.toString();
    }

    private static int secondNumber(int[] num) {
        return Arrays.stream(num)
                .distinct()
                .boxed()
//                .sorted(Comparator.reverseOrder())
//                .sorted((a,b) -> b-a)
                .sorted((a, b) -> Integer.compare(b, a))
                .skip(1)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("empty"));
    }

    private static List<Integer> itegerStream(List<Integer> integerList) {
        return integerList.stream()
                .filter(i -> i % 2 == 0)
                .map(i -> i * i)
                .sorted((a, b) -> Integer.compare(b, a))
                .toList();
    }

    //взять list<Integer> убрать все не четные, возвести в квадрат и отсортировать по убыванию

    private static String[] deleteDoublStrVer(String[] numStr) {
        int[] numbersInt = Arrays.stream(numStr)
                .mapToInt(Integer::parseInt)
                .sorted()
                .distinct()
                .toArray();

        for (int i = 0; i < numStr.length; i++) {
            if (i < numbersInt.length) {
                numStr[i] = String.valueOf(numbersInt[i]);
            } else {
                numStr[i] = "_";
            }
        }
        return numStr;
    }

    private static String[] deleteDoublStr(String[] numStr) {
        Set<String> set = new HashSet<>();
        String[] data = new String[numStr.length];

        for (int i = 0; i < numStr.length; i++) {
            if (set.contains(numStr[i])) {
                data[i] = "_";
            } else {
                set.add(numStr[i]);
                data[i] = numStr[i];
            }
        }
        return data;
    }

    private static String[] deleteDoublStrCopy(String[] numStr) {

//        for (int i = 0; i < numStr.length; i++) {
//            set.add(numStr[i]);
//        }
//        или
//        set.addAll(Arrays.asList(numStr));
//        или
        Set<String> set = new HashSet<>(Arrays.asList(numStr));

        int v = numStr.length - set.size();

        numStr = set.toArray(new String[numStr.length]);

        for (int i = 0; i < v; i++) {
            numStr[set.size() + i] = "_";
        }

        return numStr;
    }


    private static String deleteDoubl(int[] num) {
        String[] data = new String[num.length];
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < num.length; i++) {
            if (set.contains(num[i])) {
                data[i] = "_";
            } else {
                set.add(num[i]);
                data[i] = String.valueOf(num[i]);
            }
        }

        return Arrays.toString(data);
    }
//удалить дубликать из массива чисел и заменить их подчеркиванием


    private static Map<String, Integer> countByKey(List<String> data) {
        Map<String, Integer> map = new HashMap<>();
        for (String str : data) {
            map.compute(str.split(":")[0], (k, v) -> v == null ? 1 : v + 1);
        }
        return map;
    }

    private static Map<Character, Integer> charCounts(String data) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : data.toCharArray()) {
            int count = map.getOrDefault(c, 0);
            map.put(c, count + 1);
        }
        return map;
    }

    private static Map<String, Integer> mapCount(List<String> list) {
        Map<String, Integer> map = new HashMap<>();
        for (String str : list) {
            String name = str.split(":")[0];
            int count = map.getOrDefault(name, 0);

            map.put(name, count + 1);
        }
        return map;
    }

    private static String stringReversLoop(String data) {
        String result = "";

        for (int i = data.length() - 1; i >= 0; i--) {
            char upChar = Character.toUpperCase(data.charAt(i));

            if ("EYUIOA".indexOf(upChar) == -1) {
                result += upChar;
            }
        }
        return result;
    }

    private static String stringReversApi(String data) {
        String result =
//                data.toUpperCase().chars()
//                .filter(c -> "EYUIOA".indexOf(c) == -1)
//                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//                .reverse()
//                .toString();

//                data.chars()
//                        .map(Character::toUpperCase)
//                        .filter(c -> "EYUIOA".indexOf(c) == -1)
//                        .collect(StringBuilder::new,
//                                (sb, c) -> sb.insert(0, (char) c),
//                                StringBuilder::append
//                        )
//                        .toString();

                data.chars() // 1. Получаем поток символов как IntStream
                        .map(codePoint -> Character.toUpperCase(codePoint))
//                .mapToObj(codePoint -> (char) Character.toUpperCase(codePoint))
                        .mapToObj(c -> String.valueOf((char) c))// int -> String (символ)
                        .filter(c -> "EYUIOA".indexOf(c) == -1) // 2. Фильтруем гласные
                        .collect(Collectors.collectingAndThen(
                                Collectors.toList(),  // 5. Собираем в list
                                list -> {
                                    Collections.reverse(list);// 6. Переворачиваем список
                                    return list.stream(); // 7. Возвращаем поток
                                }
                        ))
                        .map(Object::toString) // Преобразуем каждый `Character` обратно в `String`
                        .collect(Collectors.joining());

        return result;
    }

    private static String stringReversS(String data) {
        String letters = "[EYUIOA]";

        return new StringBuilder(data).reverse().toString().toUpperCase().replaceAll(letters, "");
    }
}
