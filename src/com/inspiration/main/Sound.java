package com.inspiration.main;

import java.applet.Applet;
import java.applet.AudioClip;

@SuppressWarnings("deprecation")
public class Sound {

	private AudioClip clip;

	public static final Sound musicBackground = new Sound("/fundo.wav");
	public static final Sound hurtEffect = new Sound("/hurt.wav");
	public static final Sound arrowDemageEffect = new Sound("/arrow.wav");
	public static final Sound gameoverEffect = new Sound("/gameover2.wav");
	public static final Sound levelupEffect = new Sound("/levelup.wav");
	public static final Sound bowEffect = new Sound("/arco.wav");
	public static final Sound arrowEffect = new Sound("/flecha.wav");
	public static final Sound pigEffect = new Sound("/pig.wav");
	public static final Sound demageEffect = new Sound("/demage.wav");
	public static final Sound eatingEffect = new Sound("/eating.wav");
	
	private Sound(String name) {
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(name));
		} catch (Throwable e) {

		}
	}

	public void play() {
		try {
			new Thread() {
				public void run() {
					clip.play();
				}
			}.start();
		} catch (Throwable e) {

		}
	}
	
	public void stop() {
		try {
			new Thread() {
				public void run() {
					clip.stop();
				}
			}.stop();
		} catch (Throwable e) {
			// TODO: handle exception
		}
	}

	public void loop() {
		try {
			new Thread() {
				public void run() {
					clip.loop();
				}
			}.start();
		} catch (Throwable e) {

		}
	}
}
