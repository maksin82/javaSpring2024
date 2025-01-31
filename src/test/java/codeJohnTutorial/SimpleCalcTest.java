package codeJohnTutorial;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleCalcTest {

    @Test
    public void testTwoPlusTwo() {
        var calc = new SimpleCalc();
        assertEquals(calc.intAdd(2, 2), 4);
    }

}