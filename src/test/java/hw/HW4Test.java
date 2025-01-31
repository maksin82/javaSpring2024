package hw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HW4Test {

	@Test
	void powFive() {
		// var resultForFive = new HW4();
		assertEquals(6, (HW4.powFive()).size());
	}

	@Test
	void multFour1() {
		assertEquals(6, HW4.multFour1().size());
	}

	@Test
	void lessThenFiveShoudReternExeption() {
		int[] arr = { 1, 2, 3 };
		assertThrows(IllegalArgumentException.class, () -> {
			HW4.moreThenFive2(arr);
		});
	}

}