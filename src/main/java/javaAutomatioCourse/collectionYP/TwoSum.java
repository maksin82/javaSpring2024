package javaAutomatioCourse.collectionYP;

import java.util.*;

/*
 * find a, b in [1, 3, 6, 7, 9] so that a + b = 10
 * */
public class TwoSum {
    public List<Integer> findPairsXZ(List<Integer> numbers, int sum) {
        Set<Integer> pairs = new TreeSet<>();

        for (int i = 0; i < numbers.size()-1; i++) {
            int bingo = sum - numbers.get(i);
            if(numbers.contains(bingo)) {
                pairs.add(numbers.get(i));
                pairs.add(numbers.get(numbers.indexOf(bingo)));
            }
        }

        return new ArrayList<>(pairs);
    }

    public List<Integer> findPairs(List<Integer> list, int sum) {
        Set<Integer> set = new HashSet<>();
        for (Integer current : list) {
            if (set.contains(sum - current)) {
                List<Integer> result = new ArrayList<>(Arrays.asList(sum - current, current));
                result.sort(Integer::compareTo);
                return result;
            }
            set.add(current);
        }
        return null;
    }

}
