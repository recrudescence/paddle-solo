package paddle;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	public static final AudioClip BOOP = Applet.newAudioClip(Sound.class.getResource("sounds/boop.wav"));
	public static final AudioClip BEEP = Applet.newAudioClip(Sound.class.getResource("sounds/beep.wav"));
	public static final AudioClip DEATH1 = Applet.newAudioClip(Sound.class.getResource("sounds/eh.wav"));
	public static final AudioClip DEATH2 = Applet.newAudioClip(Sound.class.getResource("sounds/gameover.wav"));
	public static final AudioClip BG1 = Applet.newAudioClip(Sound.class.getResource("sounds/dundundun.wav"));
	//public static final AudioClip BG2 = Applet.newAudioClip(Sound.class.getResource("sounds/bopbopbop.wav"));
}