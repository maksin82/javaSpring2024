package liveCoding;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
//прервернуть строку
//посчитать скобки
//написать синглтон на enum и статический

public class RunUp {
    public static void main(String[] args) {
        //массив чисел вернуть второе
        //"A man, a plan, a canal: Panama" перевернуть
        //числа неччетные, квадрат убывание
        //соединить коллекции строк, кбрать дубль
//        List1 = ["apple", "banana"],
//        List2 = ["orange", "apple", "grape"]
//        Output: ["orange", "grape", "banana", "apple"]

        //Есть массив целых чисел [1, 2, 3, 4, 5]. Необходимо определить, содержатся ли в массиве три последовательных числа,
//идущих по порядку. Последовательность может быть только возрастающей (например, 1, 2, 3). Если такие числа найдены,
//вернуть true, иначе вернуть false
        //1, 2, 3, 4, 5 - true
        //1, 3, 5 - false

        String st1r = "A man, a plan, a canal: Panama";
        String bracket = "kghkgh{]sljfl}}}{[]sldslfl[]";

        System.out.println(bracketCount(bracket));

        List<String> one = List.of("apple", "banana");
        List<String> two = List.of("orange", "apple", "grape");
        int[] num = {100, 100, 90, 454, 2, 75, 133, 11, 3};
        List<Integer> list = Arrays.stream(num).boxed().toList();

        List<String> three = new ArrayList<>();
        three.addAll(one);
        for (String str : two) {
            if (!three.contains(str)) {
                three.add(str);
            }
        }
        System.out.println(three);
    }

    private static long bracketCount(String bracket) {
        return bracket
//                .chars()
//                .filter(c -> c == '(' || c == ')' || c == '[' || c == ']' || c == '{' || c == '}').count();
                .replaceAll("[^(){}\\[\\]]", "").length();
    }

    /*
    [^()\\[\\]{}]: Регулярное выражение, которое означает "любой символ,
     кроме круглых (), квадратных [] и фигурных {} скобок".
  - ^ внутри квадратных скобок обозначает отрицание, то есть "не соответствует идущему после".
  - Квадратные и фигурные скобки [], {} требуют экранирования обратной косой чертой \\
   в Java-строках, так как они имеют специальное значение в регулярных выражениях.
   */
    private static Map<Character, Long> bracketCountMap(String bracket) {
        return bracket
//                .replaceAll("[^()\\[\\]{}]", "")
                .chars()
                .filter(c -> c == '(' || c == '[')
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()));
    }

    private static List<Integer> streamSort(List<Integer> num) {
        return num.stream().filter(i -> i % 2 != 0)
                .map(i -> i * i)
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    private static boolean reversStrOld(String str) {
        String data = "[ ,:]";
        String inWork = str.replaceAll(data, "").toLowerCase();

        String sb = new StringBuilder(inWork).reverse().toString();

        return inWork.equals(sb);
    }


    private static int secondNum(int[] num) {
        return Arrays.stream(num).distinct().boxed().sorted((a, b) -> Integer.compare(b, a))
                .skip(1).findFirst().orElseGet(() -> Integer.MIN_VALUE);
    }
}
