package logic;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import lib.AudioUtility;
import lib.DrawingUtility;

import lib.GameBackground;

import lib.IRenderableHolder;
import lib.InputUtility;
import ui.GameScreen;

public class MainLogic {
	private GameBackground background;
	private PlayerStatus player;
	private int createTick = 100;
	private List<Entity> onScreenEntity = new ArrayList<Entity>();
	// private List<GameAnimation> onScreenAnimation = new
	// ArrayList<GameAnimation>();

	public synchronized void onStart(int level) {
		background = new GameBackground();
		// Castle castle=new Castle(DrawingUtility.ALLIES_CASTLE,
		// Character.ALLIES);
		player = new PlayerStatus(level);

		onScreenEntity.add(player.createAllyCastle());
		onScreenEntity.add(player.createEnemyCastle());
		onScreenEntity.add(new Archer(500, PlayerStatus.getInstance().getMageClass(), Character.ENEMIES));
		onScreenEntity.add(new Warrior(1200, PlayerStatus.getInstance().getMageClass(), Character.ENEMIES));
		onScreenEntity.add(new Warrior(1400, PlayerStatus.getInstance().getMageClass(), Character.ENEMIES));
		// onScreenEntity.add(new Balloon(DrawingUtility.SCREEN_WIDTH / 2,
		// GameScreen.UPPER_UI_HEIGHT + 20));
		onScreenEntity.add(new Warrior(1500, PlayerStatus.getInstance().getMageClass(), Character.ENEMIES));

	}

	public synchronized void onExit() {
		onScreenEntity.clear();
	}

	public void logicUpdate() {

		if (!AudioUtility.isSongPlaying()) {
			AudioUtility.playSong();
		}

		if (player.getEnemies().size() < player.getLevel()) {

		}

		if (player.isPlaying()) {
			if (player.isCastleAvailable() && InputUtility.getKeyTriggered().size() > 0) {
				if (InputUtility.getKeyTriggered().get(0) == KeyCode.NUMPAD1
						|| InputUtility.getKeyTriggered().get(0) == KeyCode.DIGIT1) {
					if (player.buy(player.WAR_PRICE)) {
						onScreenEntity.add(new Warrior(1, PlayerStatus.getInstance().getMageClass(), Character.ALLIES));
					}
				} else if (InputUtility.getKeyTriggered().get(0) == KeyCode.NUMPAD2
						|| InputUtility.getKeyTriggered().get(0) == KeyCode.DIGIT2) {
					if (player.buy(player.ARCH_PRICE)) {
						onScreenEntity
								.add(new Archer(1, PlayerStatus.getInstance().getArcherClass(), Character.ALLIES));
					}
				} else if (InputUtility.getKeyTriggered().get(0) == KeyCode.NUMPAD3
						|| InputUtility.getKeyTriggered().get(0) == KeyCode.DIGIT3) {
					if (player.buy(player.MAGE_PRICE)) {
						onScreenEntity.add(new Mage(1, PlayerStatus.getInstance().getMageClass(), Character.ALLIES));
					}
				}
			}
		}

		// Update moving background
		// background.updateBackground();

		// Time up

		// Create random target
		// createTarget();

		// Update target object
		List<Projectile> newProjectile = new ArrayList<Projectile>();

		for (Entity e : onScreenEntity) {
			try {
				Character attacker = (Character) e;
				if (attacker.isAttacking() && attacker.getCurrentFrame() == 3 && attacker.getCurrentTick() == 0) {
					if (e instanceof Archer || e instanceof Mage) {
						newProjectile.add(new Projectile(attacker));
						AudioUtility.playProjectileSFX(attacker);
					} else if (e instanceof Warrior) {
						((Warrior) attacker).attack(((Warrior)
						attacker).getAttackingTarget());
						AudioUtility.playWarAtk();
					}
				}
			} catch (Exception ex) {
				
			}


			e.update();
		}

		onScreenEntity.addAll(newProjectile);

		List<Entity> toRemove = new ArrayList<Entity>();
		for (int i = onScreenEntity.size() - 1; i >= 0; i--) {
			if (onScreenEntity.get(i).isDestroyed)
				toRemove.add(onScreenEntity.get(i));
		}

		/*
		 * for (Attackable c : player.getAllies()) { if (((Entity)
		 * c).isDestroyed) { toRemove.add(c); } }
		 * 
		 * for (Attackable c : player.getEnemies()) { if (((Entity)
		 * c).isDestroyed) { toRemove.add(c); } }
		 */
		onScreenEntity.removeAll(toRemove);
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
