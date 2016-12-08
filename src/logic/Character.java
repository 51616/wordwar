package logic;

import ui.*;

import java.util.List;

import lib.*;
import logic.Character;

public abstract class Character extends Entity implements Moveable, Attackable {
	public static final int ALLIES = 1, ENEMIES = -1;
	public static final int WARRIOR = 1, ARCHER = 2, MAGE = 3;
	protected int hp;
	protected int damage, towerDamage, armor, attackRange;
	protected boolean isAttacking;
	protected int team;
	protected int lastFrame, currentFrame;

	public Character(int x, int team) {
		// TODO Auto-generated constructor stub
		super(x, GameScreen.UPPER_UI_HEIGHT + GameScreen.BACKGROUND_HEIGHT - DrawingUtility.MODEL_HEIGHT);
		this.x = x;
		speed = 5;
		isAttacking = false;
		this.team = team;
		nextY = y;

		PlayerStatus.getInstance().addAndSort(this);
	}

	// public abstract boolean isCollide(Item i);

	public boolean isTeamWith(Character c) {
		if (c.getTeam() == this.getTeam())
			return true;
		return false;
	}

	public int getTeam() {
		return team;
	}

	@Override
	public void attack(Attackable a) {
		// TODO Auto-generated method stub
		if (a instanceof Castle) {
			a.decreaseLife(towerDamage);
			return;
		}
		a.decreaseLife(damage);
		System.out.println("DEAL DAMAGE : " + this.damage);
	}

	@Override
	public void decreaseLife(int damage) {
		// TODO Auto-generated method stub
		hp -= damage-armor;
		if (hp <= 0) {
			hp = 0;
			isDestroyed = true;
			isMoving = false;
			isAttacking = false;
		}
		System.out.println(hp);

	}

	public void move() {
		/*
		 * This method returns “true” if an entity can be moved. o If it is not
		 * destroyed yet, this method moves it by updating the statuses to those
		 * of the pre-calculated next state
		 * (nextX,nextY,nextDirection,isDestroyedInNextState). o Do note that
		 * the entity has delay before it can move. o Finally, the next state
		 * must be computed using the method “calculateNextState()”.
		 */
		if (!this.isDestroyed) {
			this.calculateNextState();
			this.x = this.nextX;
			this.y = this.nextY;
		}

	}

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
					//System.out.println("ALLY NUMBER " + i + " REPORT ENEMY INRANGE");
					isMoving = false;
					isAttacking = true;
					this.attack((Attackable)firstEnemy);
					nextX = x;
					return;
				}
			} catch (Exception e) {
				//System.out.println("No enemy found");
			}

			// System.out.println("ALLY Number : "+i+" ALLY X : " +this.x);

			try {
				Entity nextAlly = ((Entity) allies.get(i + 1));
				if (!nextAlly.isMoving && nextAlly.getX() < this.getX() + DrawingUtility.MODEL_WIDTH + speed) {
					isMoving = false;
					isAttacking = false;
					nextX=nextAlly.getX()-DrawingUtility.MODEL_WIDTH;
					return;
				}
			} catch (Exception e) {
				//System.out.println("FIRST ALLY UNIT");
			}
			
			isMoving = true;
			isAttacking = false;
			nextX = x + speed;

		} else {
			// System.out.println("ENEMY x : " + this.x);
			int i = enemies.indexOf(this);
			try {
				Entity firstAlly=(Entity) allies.get(allies.size() - 1);
				if ( this.x - firstAlly.getX() <= this.attackRange) {
					//System.out.println("ALLY INRANGE");
					isMoving = false;
					isAttacking = true;
					this.attack((Attackable)firstAlly);
					nextX = x;
					return;
				}
			} catch (Exception e) {
				//System.out.println("No ally unit found");
			}
			try {
				
				Entity nextEnemy=(Entity) enemies.get(i - 1);

				if (!nextEnemy.isMoving 
						&& nextEnemy.getX() + DrawingUtility.MODEL_WIDTH > this.getX() - speed) {
					//System.out.println("stop next");
					isMoving = false;
					isAttacking = false;
					nextX = nextEnemy.getX() + DrawingUtility.MODEL_WIDTH;
					return;
				}
			} catch (Exception e) {
				//System.out.println("First Enemy Unit");
			}
			
			

			isMoving = true;
			isAttacking = false;
			nextX = x - speed;

		}

	}

}
