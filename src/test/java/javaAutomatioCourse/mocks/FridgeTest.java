package javaAutomatioCourse.mocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class FridgeTest {
    @Spy
    SmallDoor door = new SmallDoor();
    @Mock
    private Lamp lamp;
    private Fridge fridge;

    @BeforeEach
    public void setUp() {
        fridge = new Fridge(new SmallDoor(), lamp);
    }

    @ParameterizedTest// парам тесты надо в отдельный класс
    @ValueSource(booleans = {true, false})
    void testDoorOperations(boolean isOpen) {
        if (isOpen) {
            fridge.openDoor();
            verify(lamp).turnOn();
        } else {
            fridge.closeDoor();
            verify(lamp).turnOff();
        }
    }

    @Test
    void openDoor() {
        fridge.openDoor();

        verify(lamp).turnOn();
    }

    @Test
    void closeDoor() {
        fridge.closeDoor();

        verify(lamp).turnOff();
    }

    //    @Spy это как обертка для мока
//    если мы тестируем корзину с реальной платежной системой
//    и нам надо что-то проверить про нее
    @Test
    public void closeDoorHL() {
        var f = new Fridge(door, new HalogenLamp());
//        у объекта можно застабить метод, а остальные не изменять
//        when(door.close()).thenReturn(10);
//        и для @Mock / @Spy
//        doThrow(new RuntimeException("Hi")).when(door).close();
        f.closeDoor();

        verify(door).close();
    }
}