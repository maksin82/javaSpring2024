package liveCoding;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Sept17 {
    public static void main(String[] args) {
        int[] num = new int[20];
        Arrays.setAll(num, i -> (int) (Math.random() * 255));

        String str = "One Two Three"; //eerhT owT enO

        String ssport = "Ssssport"; //Sport

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "three");

//        System.out.println(str.substring(0, str.length() - 1));
        System.out.println(removeDuplicate(ssport));
    }

    private static String getSport(String ssport) {
//       return ssport.replaceAll("s", "");
        StringBuilder sb = new StringBuilder();
        for (char c : ssport.toLowerCase().toCharArray()) {
            if (sb.indexOf(String.valueOf(c)) == -1) {
                sb.append(c);
            }
        }
        return StringUtils.capitalize(sb.toString());
    }

    private static String removeDuplicate(String str) {
//        if (str.isEmpty() || str.length() == 0) {
//            return str;
//        }
//        StringBuilder sb = new StringBuilder();
//        char oldChar = str.toLowerCase().charAt(0);
//
//        for (int i = 0; i < str.length(); i++) {
//            char temp = str.charAt(i);
//            if (oldChar != temp) {
//                sb.append(temp);
//            }
//        }
//        return sb.toString();
        return str.toLowerCase().chars()
                .distinct()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining())
                .replaceFirst("^.", String.valueOf(Character.toUpperCase(str.charAt(0))));
    }

    private static Map<String, Integer> mapReverse(Map<Integer, String> map) {
        //поменять местами K,V -> HashMap()
        return map.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getValue, // Ключ новой мапы = значение исходной
                        Map.Entry::getKey,   // Значение новой мапы = ключ исходной
                        (oldKey, newKey) -> newKey // Стратегия разрешения конфликтов (оставляем последний)
                ));
    }

    private static int sumRec(int[] num, int i) {
        if (i == num.length) {
            return 0;
        } else {
            return num[i] + sumRec(num, i + 1);
        }
    }

    public static String reverseRecursion(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        // Базовый случай: если строка из одного символа
        if (str.length() == 1) {
            return str;
        }
        // Берём последний символ + рекурсия для подстроки без последнего символа
        return str.charAt(str.length() - 1) + reverseRecursion(str.substring(0, str.length() - 1));
    }

    public static String reverseWhileLoop(String str) {
        char[] c = str.toCharArray();
        int left = 0;
        int right = c.length - 1;

        while (left < right) {
            char temp = c[left];
            c[left] = c[right];
            c[right] = temp;
            left++;
            right--;
        }
        return new String(c);
    }

    private static String reversString(String str) {
//            return new StringBuilder(str).reverse().toString();

        String result = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            result += str.charAt(i);
        }
        return result;
    }

}
