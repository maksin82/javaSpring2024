package javaAutomatioCourse.ColectionYP;

import java.util.Collections;
import java.util.List;

public class ReverseList {
    public void reverseInPlace(List<Integer> list) {
        for (int i = 0; i < list.size()/2; i++) {
            Collections.swap(list, i, list.size()-1-i);
        }
    }
}