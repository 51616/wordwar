package lib;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;

public class InputUtility {
	
	private static ArrayList<KeyCode> keyPressed = new ArrayList<>();
	private static ArrayList<KeyCode> keyTriggered = new ArrayList<>();


	public static boolean getKeyPressed(KeyCode keycode) {
		if (keyPressed.contains(keycode))
			return true;
		return false;
	}

	public static void setKeyPressed(KeyCode keycode, boolean pressed) {
		if (pressed) {
			if (!keyPressed.contains(keycode))
				keyPressed.add(keycode);
		} else {
			keyPressed.remove(keycode);
		}

	}

	public static boolean getKeyTriggered(KeyCode keycode) {
		if (keyTriggered.contains(keycode))
			return true;
		return false;
	}

	public static void setKeyTriggered(KeyCode keycode, boolean pressed) {
		if (pressed) {
			if (!keyPressed.contains(keycode))
				keyTriggered.add(keycode);
		} else {
			keyTriggered.remove(keycode);
		}
	}

	public static void postUpdate() {
		keyTriggered = new ArrayList<KeyCode>();

	}

	public static ArrayList<KeyCode> getKeyTriggered() {
		return keyTriggered;
	}
}
