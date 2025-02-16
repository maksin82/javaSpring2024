package javaAutomatioCourse.collectionYP;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TwoSumTest {
    private final TwoSum twoSum = new TwoSum();

    @Test
    void findPairs() {
        List<Integer> data = twoSum.findPairs(List.of(1, 12, 2, 3, 6, 7, 8, 2, 9), 10);

        List<Integer> expData = new ArrayList<>(Arrays.asList(3, 7));
        expData.sort((s1, s2) -> s1.compareTo(s2));

        assertEquals(expData, data);
    }
}