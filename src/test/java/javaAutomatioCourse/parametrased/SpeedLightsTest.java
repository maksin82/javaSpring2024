package javaAutomatioCourse.parametrased;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SpeedLightsTest {
//    String expLight;  - JUnit 4 @RunWith(Parameterized.class)
//    int currentSpeed;
//
//    public SpeedLightsTest(String expLight, int currentSpeed) {
//        this.expLight = expLight;
//        this.currentSpeed = currentSpeed;
//    }


    static Stream<Object[]> checkLightObj() {               //myVersion
        return Stream.of(
                new Object[]{"green", 50},
                new Object[]{"yellow", 70},
                new Object[]{"red", 90}
        );
    }

    /*
     * - Arguments.of(...): Этот метод используется для создания экземпляра Arguments.
     *  Он принимает любое количество параметров (включая разные типы данных),
     *  что позволяет вам передавать именно столько аргументов, сколько нужно для вашего теста.*/

    static Stream<Arguments> checkLightProvider() {
        return Stream.of(
                Arguments.of("green", 50),
                Arguments.of("yellow", 70),
                Arguments.of("yellow", 80),
                Arguments.of("red", 90)
        );
    }


//========================================================

    static Stream<TestData> checkLightClass() {
        return Stream.of(
                new TestData("green", 50),
                new TestData("yellow", 70),
                new TestData("red", 90)
        );
    }

    @ParameterizedTest(name="{0} light , speed {1}")
    @MethodSource
    public void checkLightObj(String expLight, int currentSpeed) {
        var lights = new SpeedLights();
        assertEquals(expLight, lights.showLights(currentSpeed));
    }
//================================================
    /*Если ваши параметры являются простыми примитивами или строками
     * */

    @ParameterizedTest(name="{0} light , speed {1}")
    @MethodSource("checkLightProvider")
    void checkLightObjectArgument(String expLight, int currentSpeed) {
        var lights = new SpeedLights();
        assertEquals(expLight, lights.showLights(currentSpeed));
    }

    @ParameterizedTest
    @CsvSource({
            "green, 50",
            "yellow, 70",
            "red, 90"
    })
    void checkLightCSV(String expLight, int currentSpeed) {
        var lights = new SpeedLights();
        assertEquals(expLight, lights.showLights(currentSpeed));
    }

    //=================================================
    /*
    Использование отдельного класса для тестовых данных
    Если хотим отделить логику тестов от данных, можно создать класс для хранения данных
    * */

    @ParameterizedTest
    @MethodSource
    void checkLightClass(TestData data) {
        var lights = new SpeedLights();
        assertEquals(data.expLight, lights.showLights(data.currentSpeed));
    }

    static class TestData {
        String expLight;
        int currentSpeed;

        TestData(String expLight, int currentSpeed) {
            this.expLight = expLight;
            this.currentSpeed = currentSpeed;
        }
    }
}