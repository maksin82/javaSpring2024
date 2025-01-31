package lectures.exception;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IO {

	private static String readFile(String name) throws IOException {
		return Files.readString(Path.of(name));
	}

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter file name: ");
		String filename = scanner.nextLine();

		writeIntoFile("abc.txt", Arrays.asList("🌹", "✨", "bam", "bom", "bum", "cat", "df", "вечность"));

		try {
			String content = readFile(filename);
			System.out.println("================================");
			System.out.println(content);
		}
		catch (NoSuchFileException e) {
			System.out.println("No such file " + filename);
			System.out.println("Try again");
		}

		System.out.println("\n --Finally--");
		try {
			System.out.println("Trying");
			System.out.println(tryMe(1));
			System.out.println("Success");
		}
		catch (RuntimeException e) {
			System.out.println("Error");
			throw e;
		}
		finally {
			System.out.println("Finally");
		}
	}

	static boolean writeIntoFile(String filename, List<String> lines) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter(filename));
			for (String line : lines) {
				out.println(line);
			}
			return true;
		}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			if (out != null) {
				out.close();
			}
		}
	}

	private static String tryMe(int i) {
		if (i > 10) {
			throw new RuntimeException();
		}
		else {
			return "OK";
		}
	}

}
