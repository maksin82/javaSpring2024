package codeJohnTutorial;

public class LambdaTutorial {

	public static void main(String[] args) {
		SimpleCalc calc = new SimpleCalc();
		// printThings(calc);
		/**
		 * printThings( public void print() { System.out.println("Simple Calc"); } );
		 */

		// printThings(() -> System.out.println("Simple Calc"));
		printThings((t) -> "Simple Calc" + t);

		Printable lambdaPrintable = (t) -> "Simple Calc" + t;
		printThings(lambdaPrintable);
	}

	static String printThings(Printable thing) {
		return thing.print("ulator");
	}

}
