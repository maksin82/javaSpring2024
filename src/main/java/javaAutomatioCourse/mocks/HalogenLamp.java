package javaAutomatioCourse.mocks;

public class HalogenLamp implements Lamp {
    @Override
    public void turnOn() {
        System.out.println("HLamp is on");
    }

    @Override
    public void turnOff() {
        System.out.println("HLamp is off");
    }
}
