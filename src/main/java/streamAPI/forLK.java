package streamAPI;

import java.util.stream.Stream;

public class forLK {

	public static void main(String[] args) {
		Stream.of(2, 12, 85, 06).filter(x -> x % 2 == 0).reduce(0, (x, y) -> x + y);

	}

}
