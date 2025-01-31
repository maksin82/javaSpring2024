package javaAutomatioCourse.mocks;

public class Fridge {
    private SmallDoor door;
    private Lamp lamp;

    public Fridge(SmallDoor door, Lamp lamp) {
        this.door = door;
        this.lamp = lamp;
    }

    public void openDoor() {
        System.out.println("Fridge open the door");
        door.open();
        lamp.turnOn();
    }

    public void closeDoor() {
        System.out.println("Fridge close the door");
        door.close();
        lamp.turnOff();
    }
}
