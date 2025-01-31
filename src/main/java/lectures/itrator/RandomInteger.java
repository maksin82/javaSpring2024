package lectures.itrator;

import java.util.Random;

public class RandomInteger {

	private final Random random = new Random();

	private final int limit;

	public RandomInteger(int limit) {
		this.limit = limit;
	}

	public int neXtInt() {
		return random.nextInt(limit);
	}

}
