package javaAutomatioCourse.parametrased;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NegativeLightTest {
    @Test
        //TestNG(expectedExceptions = IllegalArgumentException.class)
    void shouldFailOnDevilSpeed() {
        SpeedLights lights = new SpeedLights();

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> lights.showLights(150));

        assertEquals("devilSpeed", exception.getMessage());
    }

    @Test
    public void testShowLightsWithInvalidValue() {
        SpeedLights lights = new SpeedLights();

        try {
            lights.showLights(150);
            fail("При пожаре воруй, убивай");
        } catch (IllegalArgumentException e) {
            // Проверяем, что исключение выбросилось как ожидается
            assertEquals("devilSpeed", e.getMessage());
        }
    }
}
