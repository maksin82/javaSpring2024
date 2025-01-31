package javaAutomatioCourse;

public class StringInfo {

	private final String s;

	public StringInfo(String s) {
		this.s = s;
	}

	public boolean isPalindrome() {
		// StringBuilder rs = new StringBuilder(s).reverse();
		// return s.equals(rs.toString());

		if (s == null || s.length() == 0) {
			throw new IllegalArgumentException("Its null");
		}

		for (int i = 0; i < s.length() / 2; i++) {
			if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}

}
