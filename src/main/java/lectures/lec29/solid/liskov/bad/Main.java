package lectures.lec29.solid.liskov.bad;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<MediaPlayer> players = List.of(new VlcPlayer(), new WinAmp());

		for (MediaPlayer player : players) {
			player.playAudio("dsd.mp3");
			player.playVideo("dff.mp4");
		}
	}

}
