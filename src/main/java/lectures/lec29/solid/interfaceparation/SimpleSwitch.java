package lectures.lec29.solid.interfaceparation;

public class SimpleSwitch implements Switchable {

	@Override
	public void turnOn() {
		System.out.println("On");
	}

	@Override
	public void turnOff() {
		System.out.println("Off");
	}

}
