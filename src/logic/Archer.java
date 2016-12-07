package logic;

import java.util.List;

import lib.*;

public class Archer extends Character {

	public Archer(int x, int archerClass, int team) {
		// TODO Auto-generated constructor stub
		super(x, team);
		this.hp = 100 + 10 * archerClass;
		this.damage = 20 + 5 * archerClass;
		this.towerDamage = 2;
		this.armor = 5 + 2 * archerClass;
		this.attackRange = 100;
	}

	@Override
	protected void calculateNextState() {
		// TODO Auto-generated method stub
		List<Character> allies = PlayerStatus.instance.getAllies();
		List<Character> enemies = PlayerStatus.instance.getEnemies();

		if (this.getTeam() == Character.ALLIES) {
			int i = allies.indexOf(this);

			if (enemies.get(0).getX() - this.x <= this.attackRange) {
				isMoving = false;
				isAttacking = true;
				nextX = x;
				return;
			} else if (i < allies.size() - 1) {
				if (!allies.get(i + 1).isMoving && allies.get(i + 1).getX() < this.getX()
						+ DrawingUtility.MODEL_WIDTH + speed) {
					isMoving = true;
					isAttacking = false;
					nextX = allies.get(i + 1).getX() - this.getX() - DrawingUtility.MODEL_WIDTH;
					return;
				}

			} else /* First ally to fight (Most right ally) */ {
				if (enemies.size() == 0) {
					if (this.getX() + speed >= DrawingUtility.ENEMIES_CASTLE) {

						isMoving = false;
						isAttacking = true;
						nextX = DrawingUtility.ENEMIES_CASTLE;
						return;
					}
				}
			}
			isMoving = true;
			isAttacking = false;
			nextX = x + speed;

		} else {
			int i = enemies.indexOf(this);
			if (this.x - allies.get(allies.size() - 1).getX() <= this.attackRange) {
				isMoving = false;
				isAttacking = true;
				nextX = x;
				return;
			}

			else if (i == 0) /* First enemy to fight (Most left enemy) */ {
				if (allies.size() == 0) {
					if (this.getX() - speed >= DrawingUtility.ALLIES_CASTLE) {
						isMoving = false;
						isAttacking = true;
						nextX = DrawingUtility.ALLIES_CASTLE;
						return;
					}
				}
			}else if (i > 0) {
				if (!enemies.get(i - 1).isMoving && enemies.get(i - 1).getX()+DrawingUtility.MODEL_WIDTH 
						< this.getX()- speed) {
					isMoving = true;
					isAttacking = false;
					nextX = enemies.get(i - 1).getX()+DrawingUtility.MODEL_WIDTH;
					return;
				}

			}
			
			isMoving = true;
			isAttacking = false;
			nextX = x - speed;

		}

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (!this.isDestroyed) {
			if (isAttacking) {
				
			}
		}

	}
	
	public void decreaseHp(int damage) {
		hp-=damage;
		if (hp<=0)
			hp=0;
			isDestroyed=true;
			isMoving=false;
			isAttacking=false;
	}

	/*
	 * @Override public boolean isCollide(Item i) { // TODO Auto-generated
	 * method stub return this.getX() + DrawingUtility.MODEL_WIDTH >= ((Item)
	 * i).getX() && this.getX() < ((Item) i).getX() && this.getY() +
	 * DrawingUtility.MODEL_HEIGHT >= ((Item) i).getY() && this.getY() < ((Item)
	 * i).getY() || ((Item) i).getX() + DrawingUtility.ITEM_WIDTH >= this.getX()
	 * && ((Item) i).getX() < this.getX() && ((Item) i).getY() +
	 * DrawingUtility.ITEM_HEIGTH >= this.getY() && ((Item) i).getY() <
	 * this.getY(); }
	 */

}
