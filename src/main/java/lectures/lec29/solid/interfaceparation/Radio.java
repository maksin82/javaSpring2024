package lectures.lec29.solid.interfaceparation;

public class Radio implements Adjustable {

	@Override
	public void adjustTo(int value) {
		System.out.println("Volume to" + value);
	}

}
