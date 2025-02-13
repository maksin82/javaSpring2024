package liveCoding;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SampleCodingQuestions {
    public static void main(String[] args) {
        List<Integer> listOfIntegers = Arrays.asList(71, 18, 42, 21, 67, 32, 95, 14, 56, 87);
        Map<Boolean, List<Integer>> partitioned = odd(listOfIntegers);

        System.out.println(partitioned.get(true));
        System.out.println(odd(listOfIntegers));

        List<String> listOfStrings = Arrays.asList("Java", "Python", "C#", "Java", "Kotlin", "Python");
        System.out.println(removeDuplicateElm(listOfStrings));

        String inputString = "Java Concept Of The Day";
        System.out.println(findFrequency1(inputString));
        System.out.println(findFrequency2(inputString));
        System.out.println(findFrequency3(inputString));

        //How do you find frequency of each element in an array or a list?
        List<String> stationeryList = Arrays.asList("Pen", "Eraser", "Note Book", "Pen", "Pencil", "Stapler", "Note Book", "Pencil");
        System.out.println(countString1(stationeryList));
        System.out.println(countString2(stationeryList));

        //How do you sort the given list of decimals in reverse order?
        List<Double> decimalList = Arrays.asList(12.45, 23.58, 17.13, 42.89, 33.78, 71.85, 56.98, 21.12);
        System.out.println(decimalToReverseOrder(decimalList));

        List<String> listOfStringsJoin = Arrays.asList("Facebook", "Twitter", "YouTube", "WhatsApp", "LinkedIn");
        System.out.println(joinString1(listOfStringsJoin));
        System.out.println(joinString2(listOfStringsJoin));
        System.out.println(joinString3(listOfStringsJoin));

        int[] ints = {45, 12, 56, 15, 24, 75, 31, 89};
        List<Integer> listOfIntegersFive = new ArrayList<>(Arrays.stream(ints).boxed()
//                .collect(Collectors.toCollection(ArrayList::new)); //явно указать, что хотите собрать элементы в изменяемую коллекцию, например, ArrayList
                .toList()); //для создания мутабельного типа
        System.out.println(multiplesFive1(listOfIntegersFive));
        System.out.println(multiplesFive2(listOfIntegersFive));
        System.out.println(multiplesFive3(listOfIntegersFive));

        List<Integer> listOfIntegersMinMax = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);
        System.out.println(findMin1(listOfIntegersMinMax));
        System.out.println(findMin2(listOfIntegersMinMax));
        System.out.println(findMin3(listOfIntegersMinMax));
        System.out.println(findMin4(listOfIntegersMinMax));
        System.out.println(findMax1(listOfIntegersMinMax));
        System.out.println(findMax2(listOfIntegersMinMax));
        System.out.println(findMax3(listOfIntegersMinMax));
        System.out.println(findMax4(listOfIntegersMinMax));

        int[] a = new int[]{4, 2, 5, 1};
        int[] b = new int[]{8, 1, 9, 5};
        System.out.println(Arrays.toString(mergeArr1(a, b)));
        System.out.println(Arrays.toString(mergeArr2(a, b)));
        System.out.println(Arrays.toString(mergeArr3(a, b)));
    }


    // How do you merge two unsorted arrays into single sorted array using Java 8 streams?
    private static int[] mergeArr1(int[] a, int[] b) {
      return IntStream.concat(Arrays.stream(a), Arrays.stream(b)).sorted().distinct().toArray();
    }
    private static int[] mergeArr2(int[] a, int[] b) {
        int[] merge = new int[a.length + b.length];
        System.arraycopy(a, 0, merge, 0,a.length);
        System.arraycopy(b, 0, merge, a.length, b.length);
        Arrays.sort(merge);
        return merge;
    }
    private static int[] mergeArr3(int[] a, int[] b) {
        Set<Integer> set = new HashSet<>();
        for (int i: a) set.add(i);
        for (int i: b) set.add(i);

        return set.stream().mapToInt(Integer::intValue).sorted().toArray();
    }


    //Given a list of integers, find maximum and minimum of those numbers?
    private static int findMax1(List<Integer> listOfIntegersMinMax) {
        return listOfIntegersMinMax.stream().sorted(Comparator.reverseOrder()).findFirst().orElseThrow(() -> new IllegalArgumentException("empty"));
        //Неэффективность: Сортировка всего списка (O(n log n)) для поиска максимума (достаточно O(n)).
    }

    private static int findMax2(List<Integer> listOfIntegersMinMax) {
        return listOfIntegersMinMax.stream()
                .filter(Objects::nonNull) //Если в списке могут быть null, добавьте фильтр
                .max(Integer::compareTo)
//                .get(); //опсно без проверки на null
                .orElseThrow(() -> new NoSuchElementException("empty"));
    }

    private static int findMax3(List<Integer> listOfIntegersMinMax) {
        return Collections.max(listOfIntegersMinMax);   //выбросит NoSuchElementException, если список пуст
    }

    private static int findMax4(List<Integer> listOfIntegersMinMax) {
        int max = Integer.MIN_VALUE; // Лучшая производительность для больших данных.
        for (Integer i : listOfIntegersMinMax) {
            if (i != null && i > max) {
                max = i;
            }
        }
        return max;
    }

    private static int findMin1(List<Integer> listOfIntegersMinMax) {
        return Collections.min(listOfIntegersMinMax);
    }

    private static int findMin2(List<Integer> listOfIntegersMinMax) {
        return listOfIntegersMinMax.stream().min((i1, i2) -> i1.compareTo(i2)).orElseThrow();
    }

    private static int findMin3(List<Integer> listOfIntegersMinMax) {
        return listOfIntegersMinMax.stream().min(Comparator.naturalOrder()).orElseThrow();
    }

    private static int findMin4(List<Integer> listOfIntegersMinMax) {
        int num = Integer.MAX_VALUE;
        for (Integer i : listOfIntegersMinMax) {
            if (i != null && i < num) {
                num = i;
            }
        }
        return num;
    }


    //From the given list of integers, print the numbers which are multiples of 5?
    private static List<Integer> multiplesFive1(List<Integer> listOfIntegersFive) {
        return listOfIntegersFive.stream().filter(Objects::nonNull).filter(i -> i % 5 == 0).toList();
    }

    private static List<Integer> multiplesFive2(List<Integer> listOfIntegersFive) {
        List<Integer> fiveMulp = new ArrayList<>();
        for (Integer i : listOfIntegersFive) {
            if (i != null && i % 5 == 0) {
                fiveMulp.add(i);
            }
        }
        return fiveMulp;
    }

    private static List<Integer> multiplesFive3(List<Integer> listOfIntegersFive) {
        ListIterator<Integer> listIterator = listOfIntegersFive.listIterator();
        while (listIterator.hasNext()) {
            Integer num = listIterator.next();
            if (num == null || num % 5 != 0) {
                listIterator.remove();
            }
        }
        return listOfIntegersFive;
    }


    //Given a list of strings, join the strings with ‘[‘ as prefix, ‘]’ as suffix and ‘,’ as delimiter?
    private static String joinString1(List<String> listOfStringsJoin) {
        return "[" + String.join(",", listOfStringsJoin) + "]";
    }

    private static String joinString2(List<String> listOfStringsJoin) {
        return listOfStringsJoin.stream().collect(Collectors.joining(",", "[", "]"));
    }

    private static String joinString3(List<String> list) {
        StringJoiner sj = new StringJoiner(",", "[", "]");
        for (String s : list) {
            sj.add(s);
        }
        return sj.toString();
    }


    //How do you sort the given list of decimals in reverse order?
    private static List<Double> decimalToReverseOrder(List<Double> decimalList) {
        return decimalList.stream().sorted(Comparator.reverseOrder()).toList();
    }

    //How do you find frequency of each element in an array or a list?
    private static Map<String, Long> countString1(List<String> stationeryList) {
        Map<String, Integer> map = new HashMap<>();

        return stationeryList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private static Map<String, Integer> countString2(List<String> stationeryList) {
        Map<String, Integer> map = new HashMap<>();
        for (String str : stationeryList) {
            int count = map.getOrDefault(str, 0);
            map.put(str, count + 1);
        }
        return map;
    }


    //How do you find frequency of each character in a string using Java 8 streams?
    private static Map<Character, Integer> findFrequency1(String inputString) {
        Map<Character, Integer> map = new HashMap<>();

        for (char c : inputString.toCharArray()) {
            map.compute(c, (k, v) -> v == null ? 1 : v + 1);
        }
        return map;
    }

    private static Map<Character, Integer> findFrequency2(String inputString) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : inputString.toCharArray()) {
            int count = map.getOrDefault(c, 0);
            map.put(c, count + 1);
        }
        return map;
    }

    /*
       mapToObj(c -> (char) c):- Поток данных, полученный из chars(),
     содержит int значения. Поскольку мы хотим работать с символами Character,
     использован mapToObj(), чтобы преобразовать каждый int обратно в char.

       c -> (char) c — это лямбда-выражение, которое преобразует каждый int
      в char. Оно используется как функция преобразования, которая
      применяется к каждому элементу исходного потока.

      .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())):
    - collect() — это терминальная операция Stream, которая преобразует элементы
      потока в другой тип.
    - Collectors.groupingBy() — это коллектор, который группирует элементы потока
      по предоставленной функции классификации. В данном случае, элементы (символы)
      группируются сами по себе.
    - Function.identity() — это встроенная функция, возвращающая свой входной аргумент.
      В данном случае, она используется для ключей группировки, чтобы сгруппировать
      элементы по самим символам.
    - Collectors.counting() — это коллектор, который подсчитывает количество элементов
      в каждой группе и возвращает его в виде числа Long.
   */
    private static Map<Character, Long> findFrequency3(String inputString) {
        Map<Character, Long> map = new HashMap<>();
        return inputString.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }


    //How do you remove duplicate elements from a list using Java 8 streams?
    private static List<String> removeDuplicateElm(List<String> listOfStrings) {
        return listOfStrings.stream().distinct().collect(Collectors.toList());
    }


    //Given a list of integers, separate odd and even numbers?
    public static Map<String, List<Integer>> oddEven1(List<Integer> list) {
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("odd",
                list.stream().filter(i -> i % 2 == 0).collect(Collectors.toList())
        );
        map.put("even",
                list.stream().filter(i -> i % 2 != 0).collect(Collectors.toList())
        );
        return map;
    }

    public static Map<Boolean, List<Integer>> odd(List<Integer> list) {
        return
                list.stream()
                        .collect(Collectors.partitioningBy(i -> i % 2 == 0));
    }
}
