package paddle;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	public static final AudioClip BOOP = Applet.newAudioClip(Sound.class.getResource("sounds/boop.wav"));
	public static final AudioClip BEEP = Applet.newAudioClip(Sound.class.getResource("sounds/beep.wav"));
	public static final AudioClip DEATH = Applet.newAudioClip(Sound.class.getResource("sounds/gameover.wav"));
}