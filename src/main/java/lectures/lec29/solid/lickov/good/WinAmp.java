package lectures.lec29.solid.lickov.good;

public class WinAmp implements AudioPlayer {

	@Override
	public String playerName() {
		return "WinAmp";
	}

	@Override
	public void playAudio(String filename) {
		AudioPlayer.super.playAudio(filename);
	}

}
