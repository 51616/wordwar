package logic;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import lib.AudioUtility;
import lib.DrawingUtility;

public class Warrior extends Character {
	private static List<Image> warriorWalk = DrawingUtility.warriorWalk;
	private static List<Image> warriorAtk = DrawingUtility.warriorAtk;
	private static List<Image> warriorDying = DrawingUtility.warriorDying;
	private static List<Image> warriorWon = DrawingUtility.warriorWon;
	private Attackable attackingTarget; 

	public Warrior(int x, int warriorClass, int team) {
		// TODO Auto-generated constructor stub
		super(x, team);

		this.maxHp = 140 + 15 * warriorClass;
		this.hp = maxHp;
		this.damage = 10 + 10 * warriorClass;
		this.towerDamage = 2;
		this.armor = 5 + 3 * warriorClass;
		this.attackRange = 0;
		this.attackTime = 25;

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
					for (int j=0;j<enemies.size();j++) {
						Attackable enemyUnit=enemies.get(j);
						
						if ((((Entity) enemyUnit).getX() + enemyUnit.getModelWidth() - enemyUnit.getHitBox() - (this.getX() + this.getModelWidth()))<=attackRange) {
							if (!isAttacking) {
								currentFrame = 0;
							}
							// System.out.println("ALLY INRANGE");
							attackingTarget=enemyUnit;
							isMoving = false;
							isAttacking = true;
							nextX = x;
							if (attackTime == attackCounter) {
								//this.attack(enemyUnit);
								//AudioUtility.playWarAtk();
								attackCounter = 0;
								return;
							}
							attackCounter++;
							return;
						}
						//System.out.println("NO ENEMY INRANGE");
					}
				} catch (Exception e) {
					 System.out.println("No enemy found");
				}

				try {
					
					int i = allies.indexOf(this);
					Entity nextAlly = ((Entity) allies.get(i + 1));
					if (nextAlly instanceof Character &&!nextAlly.isMoving
							&& nextAlly.getX() < this.getX() + DrawingUtility.MODEL_WIDTH + speed) {
						isMoving = false;
						isAttacking = false;
						nextX = nextAlly.getX() - DrawingUtility.MODEL_WIDTH;
						return;
					}
				} catch (Exception e) {
					
					// System.out.println(this + "FIRST ALLY UNIT");
				}

				isMoving = true;
				isAttacking = false;
				nextX = x + speed;

			} else if (this.getTeam() == Character.ENEMIES) {
				// System.out.println("ENEMY x : " + this.x);

				try {
					for (int i=allies.size()-1;i>=0;i--) {
						Attackable allyUnit=allies.get(i);
						
						if (this.getX() - (((Entity)allyUnit).getX() + allyUnit.getHitBox())<=attackRange) {
							if (!isAttacking) {
								currentFrame = 0;
							}
							// System.out.println("ALLY INRANGE");
							attackingTarget=allyUnit;
							isMoving = false;
							isAttacking = true;
							nextX = x;
							if (attackTime == attackCounter) {
								//this.attack(allyUnit);
								//AudioUtility.playWarAtk();
								attackCounter = 0;
								return;
							}
							attackCounter++;
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
				gc.drawImage(warriorAtk.get(currentFrame), x, y);
			} else if (isDying) {
				gc.drawImage(warriorDying.get(currentFrame), x, y);
			} else if (!PlayerStatus.getInstance().isPlaying()) {
				gc.drawImage(warriorWon.get(currentFrame), x, y);
			} else {
				gc.drawImage(warriorWalk.get(currentFrame), x, y);
			}
			gc.fillRoundRect(x, y + 20, (DrawingUtility.MODEL_WIDTH * 0.6) * getHpRatio(), 10, 10, 10);
			gc.strokeRoundRect(x, y + 20, (DrawingUtility.MODEL_WIDTH * 0.6) * getHpRatio(), 10, 10, 10);
		} else {
			if (isAttacking) {
				gc.drawImage(warriorAtk.get(currentFrame), x + DrawingUtility.MODEL_WIDTH, y,
						-DrawingUtility.MODEL_WIDTH, DrawingUtility.MODEL_HEIGHT);
			} else if (isDying) {
				gc.drawImage(warriorDying.get(currentFrame), x + DrawingUtility.MODEL_WIDTH, y,
						-DrawingUtility.MODEL_WIDTH, DrawingUtility.MODEL_HEIGHT);
			} else if (!PlayerStatus.getInstance().isPlaying()) {
				gc.drawImage(warriorWon.get(currentFrame), x + DrawingUtility.MODEL_WIDTH, y,
						-DrawingUtility.MODEL_WIDTH, DrawingUtility.MODEL_HEIGHT);
			} else {
				gc.drawImage(warriorWalk.get(currentFrame), x + DrawingUtility.MODEL_WIDTH, y,
						-DrawingUtility.MODEL_WIDTH, DrawingUtility.MODEL_HEIGHT);
			}

			gc.fillRoundRect(x + getModelWidth() - getHitBox(), y + 20, (DrawingUtility.MODEL_WIDTH * 0.6) * getHpRatio(), 10, 10, 10);
			gc.strokeRoundRect(x + getModelWidth() - getHitBox(), y + 20, (DrawingUtility.MODEL_WIDTH * 0.6) * getHpRatio(), 10, 10, 10);
		}

	}

	public double getHpRatio() {
		return (double) hp / (double) maxHp;
	}
	
	public Attackable getAttackingTarget() {
		return attackingTarget;
	}

}
