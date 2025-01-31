package lectures.lec29.solid.lickov.good;

public interface VideoPlayer {

	String playerName();

	default void playVideo(String filename) {
		System.out.printf("%s is playing %s \n", playerName(), filename);
	}

}
