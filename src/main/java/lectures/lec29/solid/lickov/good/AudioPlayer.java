package lectures.lec29.solid.lickov.good;

public interface AudioPlayer {

	String playerName();

	default void playAudio(String filename) {
		System.out.printf("%s is playing %s \n", playerName(), filename);
	}

}
