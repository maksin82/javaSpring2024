package lectures.lec22;

import java.util.List;

public class WC {

	public static Counters wc(List<String> lines) {
		return lines.stream()
			// .parallel()
			.reduce(new Counters(), (counters, line) -> {
				counters.addLines(1);
				counters.addWords(line.split(" ").length);
				return counters;
			}, (c1, c2) -> {
				c1.addWords(c2.words);
				c1.addLines(c2.lines);
				return c1;
			});
	}

	public static void main(String[] args) {
		System.out.println(wc(List.of("Hello", "I love you", "Won't you tell me your name")).getString());
	}

	static class Counters {

		private int words = 0;

		private int lines = 0;

		public void addWords(int noOfWords) {
			words += noOfWords;
		}

		public void addLines(int noOfLines) {
			lines += noOfLines;
		}

		public String getString() {
			return String.format("Lines: %s, Words: %s", lines, words);
		}

	}

}
