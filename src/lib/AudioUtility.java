package lib;

import javafx.scene.media.AudioClip;
import logic.*;
import logic.Character;


public class AudioUtility {
	public static final AudioClip BG_SONG = getSound("SFX/bgsong.mp3");
	public static final AudioClip warAtk = getSound("SFX/1attack.wav");
	public static final AudioClip archerAtk = getSound("SFX/2attack.wav");
	public static final AudioClip mageAtk = getSound("SFX/3attack.wav");
	public static final AudioClip castleDestruction = getSound("SFX/castle_destruction.wav");
	public static final AudioClip celebrationSound = getSound("SFX/won.wav");

	
	public static AudioClip getSound(String directory) {
		String audio_path= ClassLoader.getSystemResource(directory).toString();
		return new AudioClip(audio_path);
	}
	
	public static void playSong() {
		BG_SONG.play(20);
	}
	
	public static boolean isSongPlaying() {
		return BG_SONG.isPlaying();
	}
	
	public static void playCelebration() {
		celebrationSound.play(30);
	}
	
	public static void playCastleDestruction() {
		castleDestruction.play(30);
	}
	
	public static void playWarAtk() {
		warAtk.play(30);
	}
	
	public static void playArcherAtk() {
		archerAtk.play(30);
	}
	
	public static void playMageAtk() {
		mageAtk.play(30);
	}
	
	public static void playProjectileSFX(Character c) {
		if (c instanceof Archer) {
			playArcherAtk();
		} else if (c instanceof Mage) {
			playMageAtk();
		}
	}
	
	
	
}
