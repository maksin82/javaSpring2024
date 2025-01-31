package lectures.lec29.solid.lickov.good;

public class Vlc implements VideoPlayer, AudioPlayer {

	@Override
	public String playerName() {
		return "VLC";
	}

	@Override
	public void playAudio(String filename) {
		AudioPlayer.super.playAudio(filename);
	}

	@Override
	public void playVideo(String filename) {
		VideoPlayer.super.playVideo(filename);
	}

}
