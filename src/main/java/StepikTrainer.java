import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StepikTrainer {

	public static void main(String[] args) {

		List<String> stringList = Arrays.stream(readInput()).map(s -> {
			try {
				int num = Integer.parseInt(s);
				return " " + num * 2;
			}
			catch (NumberFormatException e) {
				return s;
			}
		}).toList();

		stringList.forEach(System.out::print);
	}

	public static String[] readInput() {
		Scanner scanner = new Scanner(System.in);
		String inputLine = scanner.nextLine();
		scanner.close();

		return inputLine.split(" \\| ");
	}

}
