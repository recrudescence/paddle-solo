package paddle;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	// we use finals so that after they initialize, they don't need to be loaded again.
	public static final AudioClip BOOP = Applet.newAudioClip(Sound.class.getResource("sounds/boop.wav"));
	public static final AudioClip BEEP = Applet.newAudioClip(Sound.class.getResource("sounds/beep.wav"));
	public static final AudioClip DEATH = Applet.newAudioClip(Sound.class.getResource("sounds/gameover.wav"));
	public static final AudioClip BG1 = Applet.newAudioClip(Sound.class.getResource("sounds/dundundun.wav"));
}