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

	public Archer(int x, int archerClass, int team) {
		// TODO Auto-generated constructor stub
		super(x, team);
		this.maxHp = 100 + 10 * archerClass;
		this.hp = maxHp;
		this.damage = 20 + 5 * archerClass;
		this.towerDamage = 2;
		this.armor = 5 + 2 * archerClass;
		this.attackRange = 150;
		this.attackTime = 200;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (!this.isDestroyed) {
			this.move();
			if (currentTick == AnimateTick) {
				if (isMoving) {
					if (currentFrame == lastFrame) {
						currentFrame = 0;
					}
					currentFrame++;
					currentTick = 0;
					return;

				} else if (isAttacking) {
					if (currentFrame == lastAttackFrame) {
						
						currentFrame = 0;
					}

					currentFrame++;
					currentTick = 0;
					return;
				} else {
					currentFrame=0;
				}
			} else {
				currentTick++;
			}
		}
	}



	public double getHpRatio() {
		return (double) hp / (double) maxHp;
	}
	
	@Override
	protected void calculateNextState() {
		// TODO Auto-generated method stub
		List<Attackable> allies = PlayerStatus.instance.getAllies();
		List<Attackable> enemies = PlayerStatus.instance.getEnemies();
		// System.out.println(allies.get(0).getX());

		if (this.getTeam() == Character.ALLIES) {
			int i = allies.indexOf(this);
			try {
				Entity firstEnemy = ((Entity) enemies.get(0));
				if (firstEnemy.getX() - this.x <= this.attackRange) {
					if (!isAttacking) {
						currentFrame=0;
					}
					// System.out.println("ALLY NUMBER " + i + " REPORT ENEMY
					// INRANGE");
					isMoving = false;
					isAttacking = true;
					nextX = x;
					/*if (attackTime == attackCounter) {
						this.attack((Attackable) firstEnemy);
						attackCounter = 0;
						return;
					}*/
					attackCounter++;

					return;
				}
			} catch (Exception e) {
				// System.out.println("No enemy found");
			}

			// System.out.println("ALLY Number : "+i+" ALLY X : " +this.x);

			try {
				Entity nextAlly = ((Entity) allies.get(i + 1));
				if (nextAlly instanceof Character && 
						!nextAlly.isMoving && nextAlly.getX() < this.getX() + DrawingUtility.MODEL_WIDTH + speed) {
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
			int i = enemies.indexOf(this);
			try {
				Entity firstAlly = (Entity) allies.get(allies.size() - 1);
				double firstAllyX=firstAlly.getX();
				if (firstAlly instanceof Castle)
					firstAllyX+=DrawingUtility.CASTLE_WIDTH-DrawingUtility.MODEL_WIDTH;
				if (this.x - firstAllyX <= this.attackRange) {
					if (!isAttacking) {
						currentFrame=0;
					}
					//System.out.println("ALLY INRANGE");
					isMoving = false;
					isAttacking = true;
					nextX = x;
					/*if (attackTime == attackCounter) {
						this.attack((Attackable) firstAlly);
						attackCounter=0;
						return;
					}*/
					attackCounter++;
					return;
				}
			} catch (Exception e) {
				// System.out.println("No ally unit found");
			}
			try {

				Entity nextEnemy = (Entity) enemies.get(i - 1);

				if (nextEnemy instanceof Character && !nextEnemy.isMoving && nextEnemy.getX() + DrawingUtility.MODEL_WIDTH > this.getX() - speed) {
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

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.WHITE);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		if (this.team == Character.ALLIES) {
			if (isAttacking) {
				gc.drawImage(archerAtk.get(currentFrame), x, y);
			}else {
				gc.drawImage(archerWalk.get(currentFrame), x, y);
			}
			
			gc.fillRoundRect(x, y + 20, (DrawingUtility.MODEL_WIDTH * 0.6) * getHpRatio(), 10, 10, 10);
			gc.strokeRoundRect(x, y + 20, (DrawingUtility.MODEL_WIDTH * 0.6) * getHpRatio(), 10, 10, 10);
		} else {
			gc.drawImage(archerWalk.get(currentFrame), x + DrawingUtility.MODEL_WIDTH, y, -DrawingUtility.MODEL_WIDTH,
					DrawingUtility.MODEL_HEIGHT);
			gc.fillRoundRect(x + 20, y + 20, (DrawingUtility.MODEL_WIDTH * 0.6) * getHpRatio(), 10, 10, 10);
			gc.strokeRoundRect(x + 20, y + 20, (DrawingUtility.MODEL_WIDTH * 0.6) * getHpRatio(), 10, 10, 10);
		}

	}

}
