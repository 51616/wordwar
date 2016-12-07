package lib;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyEvent;
import main.*;

public class GameloopUtility {

	private static final int REFRESH_DELAY = 10;
	public static final int TICK_PER_SECONDS = 100;
	public static AnimationTimer animationTimer;

	public static void runGameloop() {
		// TODO Auto-generated constructor stub

		new AnimationTimer() {
			Long start = 0l;

			@Override
			public void handle(long now) {
				if (start == 0l)
					start = now;
				long diff = now - start;
				if (diff >= 100000000l) { // 100000000l = 100ms.
					Main.instance.drawGameScreen();
					InputUtility.postUpdate();
					start = now;
				}
			}
		}.start();

	}
}
