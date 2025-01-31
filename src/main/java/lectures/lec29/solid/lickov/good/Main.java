package lectures.lec29.solid.lickov.good;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		WinAmp winAmp = new WinAmp();
		Vlc vlc = new Vlc();

		List<AudioPlayer> audioPlayers = List.of(winAmp, vlc);

		for (AudioPlayer audioPlayer : audioPlayers) {
			audioPlayer.playAudio("dsd.mp3");
		}

		List<VideoPlayer> videoPlayers = List.of(vlc);
		for (VideoPlayer videoPlayer : videoPlayers) {
			videoPlayer.playVideo("cff.mp4");
		}
	}

}
