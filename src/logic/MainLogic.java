package logic;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.input.KeyCode;
import lib.DrawingUtility;

import lib.GameBackground;

import lib.IRenderableHolder;
import lib.InputUtility;

public class MainLogic {
	private GameBackground background;
	private PlayerStatus player;
	private List<Entity> onScreenEntity = new ArrayList<Entity>();
	// private List<GameAnimation> onScreenAnimation = new
	// ArrayList<GameAnimation>();

	public synchronized void onStart(int level) {
		background = new GameBackground();
		player = new PlayerStatus(level);
		onScreenEntity.add(new Castle(50, Character.ALLIES));
		onScreenEntity.add(new Castle(1500, Character.ENEMIES));
		onScreenEntity.add(new Warrior(1000, PlayerStatus.getInstance().getMageClass(), Character.ENEMIES));
		onScreenEntity.add(new Warrior(1300, PlayerStatus.getInstance().getMageClass(), Character.ENEMIES));
	}

	public synchronized void onExit() {
		onScreenEntity.clear();
	}

	public void logicUpdate() {

		if (InputUtility.getKeyTriggered().size() > 0) {
			if (InputUtility.getKeyTriggered().get(0) == KeyCode.NUMPAD1
					|| InputUtility.getKeyTriggered().get(0) == KeyCode.DIGIT1) {
				if (player.buy(player.WAR_PRICE)) {
					onScreenEntity.add(new Warrior(60, PlayerStatus.getInstance().getMageClass(), Character.ALLIES));
				}
			} else if (InputUtility.getKeyTriggered().get(0) == KeyCode.NUMPAD2
					|| InputUtility.getKeyTriggered().get(0) == KeyCode.DIGIT2) {
				if (player.buy(player.ARCH_PRICE)) {
					onScreenEntity.add(new Archer(60, PlayerStatus.getInstance().getArcherClass(), Character.ALLIES));
				}
			} else if (InputUtility.getKeyTriggered().get(0) == KeyCode.NUMPAD3
					|| InputUtility.getKeyTriggered().get(0) == KeyCode.DIGIT3) {
				if (player.buy(player.MAGE_PRICE)) {
					onScreenEntity.add(new Mage(60, PlayerStatus.getInstance().getMageClass(), Character.ALLIES));
				}
			}
		}

		// Update moving background
		// background.updateBackground();

		// Time up
		if (player.getLife() == 0) {
			// GameManager.goToTitle();
			return;
		}

		// Create random target
		// createTarget();

		// Update target object
		for (Entity e : onScreenEntity) {
			e.update();
		}

		// Update animation
		// for(GameAnimation obj : onScreenAnimation){
		// obj.updateAnimation();
		// }

		// Remove unused image
		for (int i = onScreenEntity.size() - 1; i >= 0; i--) {
			if (onScreenEntity.get(i).isDestroyed)
				onScreenEntity.remove(i);
		}

		List<Attackable> toRemove = new ArrayList<Attackable>();
		for (Attackable c : player.getAllies()) {
			if (((Entity) c).isDestroyed) {
				toRemove.add(c);
			}
		}

		for (Attackable c : player.getEnemies()) {
			if (((Entity) c).isDestroyed) {
				toRemove.add(c);
			}
		}

		player.getAllies().removeAll(toRemove);
		player.getEnemies().removeAll(toRemove);
		IRenderableHolder.getInstance().getEntities().removeAll(toRemove);

		/*
		 * for(int i=onScreenAnimation.size()-1; i>=0; i--){
		 * if(!onScreenAnimation.get(i).isVisible())
		 * onScreenAnimation.remove(i); }
		 */
	}

}
