package javaAutomatioCourse.mocks;

public class IncandescentLamp implements Lamp {
    @Override
    public void turnOn() {
        System.out.println("ILamp is on");
    }

    @Override
    public void turnOff() {
        System.out.println("ILamp is off");
    }
}
