package lectures.lec29.solid.liskov.bad;

public abstract class MediaPlayer {

	public String playerName;

	public MediaPlayer(String playerName) {
		this.playerName = playerName;
	}

	public void playVideo(String filename) {
		System.out.println(playerName + " play video " + filename);
	}

	public void playAudio(String filename) {
		System.out.println(playerName + " play audio " + filename);
	}

}
