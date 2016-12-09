package logic;

import ui.*;

import java.util.List;

import lib.*;
import logic.Character;

public abstract class Character extends Entity implements Moveable, Attackable {
	public static final int ALLIES = 1, ENEMIES = -1;
	public static final int WARRIOR = 1, ARCHER = 2, MAGE = 3;
	protected int hp,maxHp;
	protected int damage, towerDamage, armor, attackRange;
	protected int team;
	public int getCurrentTick() {
		return currentTick;
	}

	protected int lastFrame, currentFrame,lastAttackFrame;
	protected int currentTick,AnimateTick;
	protected int attackCounter, attackTime;

	public Character(int x, int team) {
		// TODO Auto-generated constructor stub
		super(x, GameScreen.UPPER_UI_HEIGHT + GameScreen.BACKGROUND_HEIGHT - DrawingUtility.MODEL_HEIGHT);
		this.x = x;
		speed = 0.5;
		isAttacking = false;
		this.team = team;
		nextY = y;
		this.attackCounter = 0;
		currentFrame=0;
		currentTick=0;
		lastFrame=7;
		lastAttackFrame=5;
		AnimateTick=5;

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
		hp -= damage - armor;
		if (hp <= 0) {
			hp = 0;
			isDestroyed = true;
			isMoving = false;
			isAttacking = false;
		}
		System.out.println(hp);

	}

	public void move() {

		if (!this.isDestroyed) {
			this.calculateNextState();
			this.x = this.nextX;
			this.y = this.nextY;
		}

	}

	protected abstract void calculateNextState();

	public int getCurrentFrame() {
		return currentFrame;
	}

}
