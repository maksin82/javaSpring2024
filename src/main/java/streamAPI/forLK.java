package streamAPI;

public class forLK {

	public static void main(String[] args) {
//		System.out.println(Stream.of(2, 12, 85, 06).filter(x -> x % 2 == 0).reduce(0, (x, y) -> x + y));

		String data = "Hnskdfk Ysualsefo oevasd";
		System.out.println(data.indexOf(97));

		char[] ca = data.toCharArray();
		char[] ca1 = new char[data.length()];
		ca[1] = 'I';
		ca1[1] = 'I';
		System.out.println(ca[1]);
		System.out.println(ca1[1]);
		System.out.println(ca1[2]);

	}

}
