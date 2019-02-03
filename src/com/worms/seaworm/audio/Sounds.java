package com.worms.seaworm.audio;

import com.worms.seaworm.audio.Audio.Sound;
import com.worms.seaworm.audio.Audio.SoundLength;

public class Sounds {
	public static Sound SPLASH;
	public static Sound MUSIC;
	
	public static void init() {
		SPLASH = Audio.loadSound("sounds/splash.ogg").setLength(SoundLength.SHORT);
		MUSIC = Audio.loadSound("music/music.ogg").setLength(SoundLength.LONG);
	}
}
