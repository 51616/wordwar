package logic;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import lib.*;
import logic.Character;

public class Archer extends Character {
	private static List<Image> archerWalk = DrawingUtility.archerWalk;
	private static List<Image> archerAtk = DrawingUtility.archerAtk;
	private static List<Image> archerDying = DrawingUtility.archerDying;
	private static List<Image> archerWon = DrawingUtility.archerWon;

	public Archer(int x, int archerClass, int team) {
		// TODO Auto-generated constructor stub
		super(x, team);
		this.maxHp = 90 + 10 * archerClass;
		this.hp = maxHp;
		this.damage = 10 + 5 * archerClass;
		this.towerDamage = 1;
		this.armor = 4 + 2 * archerClass;
		this.attackRange = 100;
		// AnimateTick=10;
		// this.attackTime = 200;
	}

	public double getHpRatio() {
		return (double) hp / (double) maxHp;
	}

	@Override
	protected void calculateNextState() {
		// TODO Auto-generated method stub
		if (!isDying) {
			List<Attackable> allies = PlayerStatus.instance.getAllies();
			List<Attackable> enemies = PlayerStatus.instance.getEnemies();
			// System.out.println(allies.get(0).getX());

			if (this.getTeam() == Character.ALLIES) {

				try {
					for (int i = 0; i < enemies.size(); i++) {
						Attackable enemyUnit = enemies.get(i);

						if ((((Entity) enemyUnit).getX() + enemyUnit.getModelWidth() - enemyUnit.getHitBox()
								- (this.getX() + this.getModelWidth())) <= attackRange) {
							if (!isAttacking) {
								currentFrame = 0;
							}
							// System.out.println("ALLY INRANGE");
							isMoving = false;
							isAttacking = true;
							nextX = x;

							return;
						}
					}
				} catch (Exception e) {
					// System.out.println("No enemy found");
				}

				// System.out.println("ALLY Number : "+i+" ALLY X : " +this.x);

				try {
					int i = allies.indexOf(this);
					Entity nextAlly = ((Entity) allies.get(i + 1));
					if (nextAlly instanceof Character && !nextAlly.isMoving
							&& nextAlly.getX() < this.getX() + DrawingUtility.MODEL_WIDTH + speed) {
						isMoving = false;
						isAttacking = false;
						nextX = nextAlly.getX() - DrawingUtility.MODEL_WIDTH;
						return;
					}
				} catch (Exception e) {
					// System.out.println("FIRST ALLY UNIT");
				}

				isMoving = true;
				isAttacking = false;
				nextX = x + speed;

			} else {
				// System.out.println("ENEMY x : " + this.x);

				try {
					for (int i = allies.size() - 1; i >= 0; i--) {
						Attackable allyUnit = allies.get(i);

						if (this.getX() - (((Entity) allyUnit).getX() + allyUnit.getHitBox()) <= attackRange) {
							if (!isAttacking) {
								currentFrame = 0;
							}
							// System.out.println("ALLY INRANGE");
							isMoving = false;
							isAttacking = true;
							nextX = x;

							return;
						}
					}
				} catch (Exception e) {
					// System.out.println("No ally unit found");
				}
				try {
					int i = enemies.indexOf(this);
					Entity nextEnemy = (Entity) enemies.get(i - 1);

					if (nextEnemy instanceof Character && !nextEnemy.isMoving
							&& nextEnemy.getX() + DrawingUtility.MODEL_WIDTH > this.getX() - speed) {
						// System.out.println("stop next");
						isMoving = false;
						isAttacking = false;
						nextX = nextEnemy.getX() + DrawingUtility.MODEL_WIDTH;
						return;
					}
				} catch (Exception e) {
					// System.out.println("First Enemy Unit");
				}

				isMoving = true;
				isAttacking = false;
				nextX = x - speed;

			}
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.WHITE);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		if (this.team == Character.ALLIES) {
			if (isAttacking) {
				gc.drawImage(archerAtk.get(currentFrame), x, y);
			} else if (isDying) {
				gc.drawImage(archerDying.get(currentFrame), x, y);
			} else if (!PlayerStatus.getInstance().isPlaying()) {
				gc.drawImage(archerWon.get(currentFrame), x, y);
			} else {
				gc.drawImage(archerWalk.get(currentFrame), x, y);
			}

			gc.fillRoundRect(x, y + 20, (DrawingUtility.MODEL_WIDTH * 0.6) * getHpRatio(), 10, 10, 10);
			gc.strokeRoundRect(x, y + 20, (DrawingUtility.MODEL_WIDTH * 0.6) * getHpRatio(), 10, 10, 10);
		} else {
			if (isAttacking) {
				gc.drawImage(archerAtk.get(currentFrame), x + DrawingUtility.MODEL_WIDTH, y,
						-DrawingUtility.MODEL_WIDTH, DrawingUtility.MODEL_HEIGHT);
			} else if (isDying) {
				gc.drawImage(archerDying.get(currentFrame), x + DrawingUtility.MODEL_WIDTH, y,
						-DrawingUtility.MODEL_WIDTH, DrawingUtility.MODEL_HEIGHT);
			} else if (!PlayerStatus.getInstance().isPlaying()) {
				gc.drawImage(archerWon.get(currentFrame), x + DrawingUtility.MODEL_WIDTH, y,
						-DrawingUtility.MODEL_WIDTH, DrawingUtility.MODEL_HEIGHT);
			}

			else {
				gc.drawImage(archerWalk.get(currentFrame), x + DrawingUtility.MODEL_WIDTH, y,
						-DrawingUtility.MODEL_WIDTH, DrawingUtility.MODEL_HEIGHT);
			}

			gc.fillRoundRect(x + getModelWidth() - getHitBox(), y + 20, (DrawingUtility.MODEL_WIDTH * 0.6) * getHpRatio(), 10, 10, 10);
			gc.strokeRoundRect(x + getModelWidth() - getHitBox(), y + 20, (DrawingUtility.MODEL_WIDTH * 0.6) * getHpRatio(), 10, 10, 10);
		}

	}

}
