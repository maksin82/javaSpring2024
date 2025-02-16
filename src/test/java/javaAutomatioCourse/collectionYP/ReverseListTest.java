package javaAutomatioCourse.collectionYP;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReverseListTest {

    private final ReverseList r = new ReverseList();

    @Test
    void quizArrayAsList() {
        List<Integer> input = Arrays.asList(15, 10);
        //  input.add(10); менять можно, добавить нельзя

        r.reverseInPlace(input);
        assertEquals(Integer.valueOf(10), input.get(0));
    }

    @Test
    void quizListOf() {
//        List<Integer> input = List.of(15, 10);
        //  input.add(10); нельзя менять, нельзя добавить - для констант
//input = Collections.unmodifiableList(input); - больше нельзя изменить
        List<Integer> input = new ArrayList<>(Arrays.asList(15, 10));

        r.reverseInPlace(input);
        assertEquals(Integer.valueOf(10), input.get(0));
    }

    @Test
    void singleElement() {
        List<Integer> input = new ArrayList<>();
        input.add(10);

        r.reverseInPlace(input);
        assertEquals(Integer.valueOf(10), input.get(0));
        assertEquals(10, input.get(0));
    }

    @Test
    void nElement() {
        List<Integer> input = new ArrayList<>();
        input.add(10);
        input.add(15);
        input.add(15);
        input.add(15);
        input.add(15);
        input.add(15);

        r.reverseInPlace(input);
        assertEquals(List.of(15, 15, 15, 15, 15, 10), input);
    }
}