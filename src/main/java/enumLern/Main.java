package enumLern;

public class Main {

	enum Seasons {

		SPRING, SUMMER, AUTUMN, WINTER

	}

	public static void main(String[] args) {
		Main.Seasons seasons = Seasons.AUTUMN;

		switch (seasons) {
			case AUTUMN -> System.out.println("Osen");
			case SUMMER -> System.out.println("Leto");
			case SPRING -> System.out.println("Vesna");
			case WINTER -> System.out.println("Zima");
			default -> System.out.println("Bam!");
		}
	}

}
