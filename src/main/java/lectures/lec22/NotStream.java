package lectures.lec22;

import java.util.List;

public class NotStream {
    public static void main(String[] args) {
        List<String> str = List.of("aaa", "bbbbbbbbb", "cccc");
        System.out.println(findBest(str, (first, second) -> first.length() - second.length()));
        System.out.println(findBest(str,
                new Comparator<String>() {
                    @Override
                    public int compare(String first, String second) {
                        return second.length() - first.length();
                    }
                }));
    }

    static interface Comparator<T> {
        int compare(T first, T second);
    }

    static <T> T findBest(List<T> elements, Comparator<T> comparator) {
        if (elements.isEmpty()) return null;
        T champion = elements.get(0);
        for (T a : elements) {
            if (comparator.compare(champion, a) < 0) {
                champion = a;
            }
        }
        return champion;
    }
}
