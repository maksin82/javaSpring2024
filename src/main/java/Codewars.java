import java.util.*;
import java.util.function.BiFunction;

public class Codewars {

	public static void main(String[] args) {

		String time = "The greatest 2 victory is that 3 which requires no battle";
		String num = "*";
		String str = "(())((()())())";
		String upDown = str.toUpperCase().toLowerCase(new Locale("lt"));
		int length = str.length();
		int upDownLength = upDown.length();
		// System.out.println(upDown + " " + length + " " + upDownLength);

		int a = -86349;
		int b = 7; // 16
		int[] d = { 34, 15, 88, 2 };
		double c = 0.5;
		Block brik = new Block(new int[] { 2, 6, 7 });
//		System.out.println(order("hj2 hgfr1 khf3"));
		method(str);
		System.out.println(method(str));
	}

	public static String method(String str) {
		str = str + "!";
		return str;
	}

	public static String order(String words) {
		// if (words.length()==0) return "";
		//
		// String[] wordy = words.split(" ");
		// Map<Integer, String> ordered = new HashMap<>();
		//
		// for (String w : wordy) {
		// for (char c : w.toCharArray()) {
		// if (Character.isDigit(c)) {
		// int i = Integer.parseInt(String.valueOf(c));
		// ordered.put(i, w);
		// break;
		// }
		// }
		// }
		// String result = "";
		// for (int i = 1; i <= wordy.length; i++) {
		// result += ordered.get(i) + " ";
		// }
		//
		// return result.trim();

		return Arrays.stream(words.split(" "))
			.sorted(Comparator.comparing(s -> Integer.valueOf(s.replaceAll("\\D", ""))))
			.reduce((a, b) -> a + " " + b)
			.get();
	}

	public static String longToIP(long ip) {
		long four = ip % 256;
		long three = (ip / 256) % 256;
		long two = (ip / 256 / 256) % 256;
		long one = (ip / 256 / 256 / 256) % 256;

		return String.format("%d.%d.%d.%d", one, two, three, four);

		// return String.format("%d.%d.%d.%d", ip>>>24, (ip>>16)&0xff, (ip>>8)&0xff,
		// ip&0xff);

		// String[] e = new String[4];
		// int i = 4;
		// while (i-- != 0) {
		// e[i] = "" + (ip % 256);
		// ip /= 256;
		// }
		// return String.join(".",e);
	}

	public static boolean validParentheses(String parens) {
		// int countA = 0;
		// int countB = 0;
		// for (char c : parens.toCharArray()) {
		// if (c == '(') {
		// countA++;
		// }
		// if (c == ')') {
		// countB++;
		// if (countA < countB) return false;
		// }
		// }
		// return countA == countB ? true : false;

		// int open = 0;
		// for(int i=0; i<parens.length(); i++) {
		// open += parens.charAt(i) == '(' ? 1 : -1;
		// if(open < 0) {
		// return false;
		// }
		// }
		// return open == 0;
		while (parens.contains("()"))
			parens = parens.replace("()", "");
		return parens == "";
	}

	public static int countArgs(Object... args) {
		// your code here
		return args == null ? 0 : args.length;
	}

	public static int arithmetic(int a, int b, String operator) {
		Map<String, BiFunction<Integer, Integer, Integer>> operations = new HashMap<>();
		operations.put("add", (i, j) -> i + j);
		operations.put("subtract", (i, j) -> i - j);
		operations.put("multiply", (i, j) -> i * j);
		operations.put("divide", (i, j) -> i / j);

		return operations.get(operator).apply(a, b);
	}

	public static long breakChocolate(long n, long m) {
		return n * m - 1;
	}

	public static boolean isPlural(float f) {
		// return f==1 ? false : true;
		return f != 1;
	}

	public static String hoopCount(int n) {
		Map<Boolean, String> phrase = new HashMap<>();
		phrase.put(true, "Great, now move on to tricks");
		phrase.put(false, "Keep at it until you get it");

		return phrase.get(n >= 10);
	}

	public static String CalculateAge(int birth, int yearTo) {
		int count = yearTo - birth;
		// String year = (Math.abs(count) == 1) ? "year" : "years";
		//
		// if (count < 0) {
		// return String.format("You will be born in %d %s.", Math.abs(count), year);
		// }
		// if (count > 0) {
		// return String.format("You are %d %s old.", count, year);
		// }
		// else return "You were born this very year!";

		return count == 0 ? "You were born this very year!"
				: count > 0 ? String.format("You are %d year%s old.", count, count == 1 ? "" : "s")
						: String.format("You will be born in %d year%s.", -count, -count == 1 ? "" : "s");
	}

	public static double fuelPrice(int litres, double pricePerLitre) {
		// double fuelPrice = litres * pricePerLitre;
		//
		// if (litres >= 2) {
		// fuelPrice = pricePerLitre * litres - 0.05 * litres;
		// }
		// if (litres >= 4) {
		// fuelPrice = pricePerLitre * litres - 0.10 * litres;
		// }
		// if (litres >= 6) {
		// fuelPrice = pricePerLitre * litres - 0.15 * litres;
		// }
		// if (litres >= 8) {
		// fuelPrice = pricePerLitre * litres - 0.20 * litres;
		// }
		// if (litres >= 10) {
		// fuelPrice = pricePerLitre * litres - 0.25 * litres;
		// }
		//
		// return fuelPrice;
		return litres * (pricePerLitre - Math.min(litres / 2 * 0.05, 0.25));
	}

	public static char getGrade(int s1, int s2, int s3) {
		int score = (s1 + s2 + s3) / 3;

		return 90 <= score && score <= 100 ? 'A' : 80 <= score && score < 90 ? 'B'
				: 70 <= score && score < 80 ? 'C' : 60 <= score && score < 70 ? 'D' : 'F';

		// int mean = (s1 + s2 + s3) / 3;
		// if (mean >= 90) return 'A';
		// if (mean >= 80) return 'B';
		// if (mean >= 70) return 'C';
		// if (mean >= 60) return 'D';
		// return 'F';

		// s1=(s1+s2+s3)/3;
		// return s1 >= 90 ? 'A':s1 >= 80 ? 'B':s1 >= 70 ? 'C':s1 >= 60 ? 'D':'F';
	}

	public static String makeReadable(int seconds) {
		// Do something
		int sec = seconds % 60;
		seconds -= sec;
		int minut = (seconds / 60) % 60;
		int hour = seconds / 3600;

		StringBuilder builder = new StringBuilder();
		if (hour < 10) {
			builder.append(0).append(hour).append(":");
		}
		else
			builder.append(hour).append(":");
		if (minut < 10) {
			builder.append(0).append(minut).append(":");
		}
		else
			builder.append(minut).append(":");
		if (sec < 10) {
			builder.append(0).append(sec);
		}
		else
			builder.append(sec);

		return builder.toString();

		// return String.format("%02d:%02d:%02d", seconds / 3600, (seconds / 60) % 60,
		// seconds % 60);
	}

	public static String chromosomeCheck(String sperm) {
		return "Congratulations! You're going to have a " + (sperm.contains("Y") ? "son." : "daughter.");
		// return sperm.equals("XX")? "Congratulations! You're going to have a daughter."
		// : "Congratulations! You're going to have a son.";
	}

	public static String boolToWord(boolean b) {
		return b == true ? "Yes" : "No";
	}

	public static Double calculate(final double numberOne, final String operation, final double numberTwo) {
		Double Result = null;
		Result = switch (operation) {
			case "+" -> numberOne + numberTwo;
			case "-" -> numberOne - numberTwo;
			case "*" -> numberOne * numberTwo + 0.0;
			case "/" -> numberTwo == 0 ? null : numberOne / numberTwo;
			default -> Result;
		};
		return Result;
	}

	public static Integer basicMath(String op, int v1, int v2) {
		// Integer bM = switch (op) {
		// case "+" -> v1 + v2;
		// case "-" -> v1 - v2;
		// case "*" -> v1 * v2;
		// case "/" -> v1 / v2;
		// default -> v1;
		// };
		// return bM;
		return op.equals("+") ? (v1 + v2)
				: op.equals("-") ? (v1 - v2) : op.equals("*") ? (v1 * v2) : (v2 == 0) ? null : (v1 / v2);
	}

	public static String switchItUp(int number) {
		// String numbers = switch (number) {
		// case 0 -> "Zero";
		// case 1 -> "One";
		// case 2 -> "Two";
		// case 3 -> "Three";
		// case 4 -> "Four";
		// case 5 -> "Five";
		// case 6 -> "Six";
		// case 7 -> "Seven";
		// case 8 -> "Eight";
		// case 9 -> "Nine";
		// default -> "NaN";
		// };
		// return numbers;
		return new String[] { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine" }[number];
	}

	public static String factors(int n) {
		StringBuilder result = new StringBuilder();

		for (int i = 2; n > 1; i++) {
			int count = 0;
			while (n % i == 0) {
				n /= i;
				count++;
			}
			if (count > 0) {
				result.append("(").append(i);
				if (count > 1) {
					result.append("**").append(count);
				}
				result.append(")");
			}
		}
		return result.toString();
	}

	public static int digital_root(int n) {
		// while (n >= 10) {
		// n = n % 10 + n / 10;
		// }
		// return n;
		return n == 0 ? 0 : n % 9 == 0 ? 9 : n % 9;
		// return (n != 0 && n%9 == 0) ? 9 : n % 9;
	}

	public static boolean isPrime(int num) {
		if (num <= 1)
			return false;
		if (num > 1) {
			for (int i = 2; i < num; i++) {
				if (num % i == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public static String bmi(double weight, double height) {
		String[] str = { "Underweight", "Normal", "Overweight", "Obese" };
		double[] doubles = { 0, 18.5, 25.0, 30 };
		double bmi = weight / (height * height);

		return bmi <= 18.5 ? "Underweight" : bmi <= 25.0 ? "Normal" : bmi <= 30.0 ? "Overweight" : "Obese";
		// String answer = "";
		// for (int i = 0; i < doubles.length; i++) {
		// if (bmi >= doubles[i]) answer = str[i];
		// } return answer;

		// if ( bmi <= 18.5) return str[0];
		// if (bmi <= 25.0) return str[1];
		// if (bmi <= 30.0) return str[2];
		// return str[3];
	}

	public static int TwiceAsOld(int dadYears, int sonYears) {
		int years = dadYears - 2 * sonYears;
		return Math.abs(years);

	}

	public static String declareWinner(Fighter fighter1, Fighter fighter2, String firstAttacker) {
		String winner = "";
		Fighter firstF = (firstAttacker.equals(fighter1.name) ? fighter1 : fighter2);
		Fighter secondF = (firstAttacker.equals(fighter1.name) ? fighter2 : fighter1);

		do {
			secondF.health -= firstF.damagePerAttack;
			if (secondF.health <= 0) {
				winner = firstF.name;
				break;
			}
			firstF.health -= secondF.damagePerAttack;
			if (firstF.health <= 0) {
				winner = secondF.name;
				break;
			}
		}
		while (firstF.health >= 0 || secondF.health >= 0);

		return winner;
		// while (true) {
		// if ((b.health -= a.damagePerAttack) <= 0) return a.name;
		// if ((a.health -= b.damagePerAttack) <= 0) return b.name;
		// }
	}

	public static int findSmallestInt(int[] args) {
		// int[] arr = Arrays.stream(args).sorted().toArray();
		// return arr[0];
		Arrays.sort(args);
		return args[0];
	}

	public static boolean isLove(final int flower1, final int flower2) {
		return (flower1 + flower2) % 2 != 0;
		// return flower1 % 2 != flower2 % 2;
	}

	public static int makeNegative(final int x) {
		return Math.abs(x) * (-1);
	}

	public static boolean isEven(double n) {
		return n % 2 == 0;

		// return n % 2 ==0 ? true : false;
	}

	public static int simpleMultiplication(int n) {
		return n % 2 == 0 ? n * 8 : n * 9;
		// return n * (n % 2 + 8);
	}

	public static int paperWork(int n, int m) {
		if (n < 0 || m < 0) {
			return 0;
		}
		else
			return Math.multiplyExact(n, m);

		// return (n < 0) || (m < 0) ? 0 : n * m;

		// return max(m, 0) * max(n, 0);
	}

	public static String numberToString(int num) {
		// return Integer.toString(num);
		// return num+"";
		return String.valueOf(num);

	}

	public static int stringToNumber(String str) {
		// return Integer.parseInt(str);
		return Integer.valueOf(str);
	}

	public static String remove(String str) {
		return str.substring(1, str.length() - 1);
	}

	public static String[] stringToArray(String s) {
		String[] split = s.split("\\d"); // d- цифра s - пробел
		return split;
	}

	public static String reverseWords(String str) {

		String[] arr = str.split(" ");
		String[] newArr = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			newArr[i] = arr[arr.length - 1 - i];
		}
		String sr = "";
		for (int i = 0; i < newArr.length; i++) {
			sr += newArr[i];
			sr += " ";
		}
		return sr.trim();
		// String[] str2 = str.split(" ");
		// String fin = "";
		// for(int i=str2.length-1;i>=0;i--){
		// fin += str2[i];
		// if(i>0)fin+=" ";
		// }
		// return fin;

		// List Words = Arrays.asList(str.split(" "));
		// Collections.reverse(Words);
		// return String.join(" ", Words);

		// return Arrays.stream(str.split(" ")).reduce((x, y) -> y+" "+x).get();
	}

	public static int howOld(final String herOld) {
		String age = String.valueOf(herOld.charAt(0));
		return Integer.parseInt(age);
		// String[] s = herOld.split(" ");
		// return Integer.valueOf(s[0]);

		// String s = herOld.substring(0,1);
		// return Integer.parseInt(s);

		// return Character.getNumericValue(herOld.charAt(0));
	}

	public static String noSpace(final String x) {
		// List simbol = Arrays.asList(x.split(" "));
		return String.join("", x.split(" "));

		// return x.replace(" ", "");
		// return x.replaceAll("\\s+","");
	}

	public static String solution(String str) {
		String newstr = "";
		for (int i = 0; i < str.length(); i++) {
			newstr += str.charAt(str.length() - i - 1);
		}
		return newstr;

		// for(int i = str.length()-1; i >= 0; i--){
		// newstr += str.charAt(i);}

		// return Stream.of(str)
		// .map(s -> new StringBuilder(s).reverse())
		// .collect(Collectors.joining());

	}

	static String encode(String word) {
		word = word.toLowerCase();
		String encodeWord = "";

		for (int i = 0; i < word.length(); i++) {
			String newS = "";
			newS = word.substring(0, i);
			newS += word.substring(i + 1);

			char w = word.charAt(i);
			String count = String.valueOf(w);
			if (newS.contains(count)) {
				encodeWord += ")";
			}
			else
				encodeWord += "(";
		}
		return encodeWord;

		// word = word.toLowerCase();
		// String result = "";
		// for (int i = 0; i < word.length(); ++i) {
		// char c = word.charAt(i);
		// result += word.lastIndexOf(c) == word.indexOf(c) ? "(" : ")";
		// }
		// return result;

	}

	public static double twoDecimalPlaces(double number) {
		number = number * 100;
		number = (double) ((int) number);
		number = number / 100;
		return number;
	}

	public static int Liters(double time) {
		// (int) (time * 0.5);
		return (int) time / 2;
	}

	public static boolean getXO(String str) {
		// str = str.toLowerCase();
		// char charX = 'x';
		// char charO = 'o';
		// int countX = 0;
		// int countO = 0;
		//
		// for (int i = 0; i < str.length(); i++) {
		// if (str.charAt(i) == charX) {
		// countX++;
		// }
		// if (str.charAt(i) == charO) {
		// countO++;
		// }
		// }
		//
		// if (!str.contains("x") && !str.contains("o")) {
		// return true;
		// } else if (countX == countO) {
		// return true;
		// } else {
		// return false;
		// }
		str = str.toLowerCase();
		return str.replace("o", "").length() == str.replace("x", "").length();
	}

	public static String highAndLow(String numbers) {
		String[] str = numbers.split(" ", 0);

		int[] ints = new int[str.length];
		for (int i = 0; i < str.length; i++) {
			ints[i] = Integer.parseInt(str[i]);
		}

		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < ints.length; i++) {
			if (ints[i] < min) {
				min = ints[i];
			}
		}
		for (int i = 0; i < ints.length; i++) {
			if (ints[i] > max) {
				max = ints[i];
			}
		}

		return min + " " + max;
	}

}
