package lib;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyEvent;
import logic.*;
import main.*;


public class GameloopUtility {

	private static final int REFRESH_DELAY = 10;
	public static final int TICK_PER_SECONDS = 100;
	public static AnimationTimer animationTimer;
	
	public static  void runGameloop() {
		// TODO Auto-generated constructor stub
		//gameLogic=Logic;
		new AnimationTimer() {
			Long start = 0l;

			@Override
			public void handle(long now) {
				if (start == 0l)
					start = now;
				long diff = now - start;
				if (diff >= 10000000) { // 1e7 ns = 10ms.
					PlayerStatus.getInstance().update();
					HangmanUtility.update();
					//gameLogic.logicUpdate();
					Main.instance.drawGameScreen();
					InputUtility.postUpdate();
					start = now;
				}
			}
		}.start();

	}
}
