package lectures.lec29.solid.liskov.bad;

public class WinAmp extends MediaPlayer {

	public final static String playerName = "WinAmp";

	public WinAmp() {
		super(playerName);
	}

	@Override
	public void playVideo(String filename) {
		throw new RuntimeException("Can`t play video");
	}

}
