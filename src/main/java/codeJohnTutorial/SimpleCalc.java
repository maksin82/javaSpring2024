package codeJohnTutorial;

public class SimpleCalc implements Printable {

	public int intAdd(int a, int b) {
		return a + b;
	}

	@Override
	public String print(String s) {
		return "Simple Calculator" + s;
	}

}