package logic;

import ui.*;

import java.util.List;

import lib.*;
import logic.Character;

public abstract class Character extends Entity implements Moveable, Attackable {
	public static final int ALLIES = 1, ENEMIES = -1;
	public static final int WARRIOR = 1, ARCHER = 2, MAGE = 3;
	protected int hp, maxHp;
	protected int  armor, attackRange;
	protected double damage,towerDamage;
	protected int team;

	protected boolean isDying;
	protected int lastMovingFrame, currentFrame, lastAttackFrame, lastDyingFrame, lastCelebrationFrame;
	protected int currentTick, AnimateTick;
	protected int attackCounter, attackTime;

	public Character(int x, int team) {
		// TODO Auto-generated constructor stub
		super(x, GameScreen.UPPER_UI_HEIGHT + GameScreen.BACKGROUND_HEIGHT - DrawingUtility.MODEL_HEIGHT);
		this.x = x;
		this.team = team;
		this.attackCounter = 0;
		speed = 0.5;
		currentFrame = 0;
		currentTick = 0;
		lastCelebrationFrame=8;
		lastMovingFrame = 7;
		lastAttackFrame = 5;
		lastDyingFrame = 5;
		AnimateTick = 5;

		PlayerStatus.getInstance().addAndSort(this);
	}

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
	public void decreaseLife(double damage) {
		// TODO Auto-generated method stub
		hp -= damage - armor;
		if (hp <= 0) {
			hp = 0;
			isDying = true;
			isMoving = false;
			isAttacking = false;
			currentFrame = 0;
			PlayerStatus.getInstance().remove(this);
		}

	}

	public void move() {

		if (!this.isDestroyed && PlayerStatus.getInstance().isPlaying()) {

			this.calculateNextState();
			this.x = this.nextX;
			this.y = this.nextY;
		}

	}

	public void update() {
		// TODO Auto-generated method stub
		if (!this.isDestroyed) {
			this.move();
			if (currentTick == AnimateTick) {
				if (!PlayerStatus.getInstance().isPlaying()) {
					if (!isDying&& 
							(this.getTeam()==Character.ALLIES && PlayerStatus.getInstance().isDefeated()
							||this.getTeam()==Character.ENEMIES && PlayerStatus.getInstance().hasWon()) ) {
						this.isDying=true;
						isAttacking=false;
						isMoving=false;
						this.hp=0;
						return;
					}
					else if (isAttacking || isMoving) {
						isAttacking=false;
						isMoving=false;
						currentFrame=0;
					} else if (isDying) {
						if (currentFrame == lastDyingFrame) {
							isDestroyed = true;
						}
						currentFrame++;
						currentTick = 0;
						return;
					}
					else if (currentFrame == lastCelebrationFrame) {
						currentFrame = 0;
					}
					currentFrame++;
					currentTick = 0;
					return;
				}
				
				else if (isMoving) {
					if (currentFrame == lastMovingFrame) {
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
				} else if (isDying) {
					if (currentFrame == lastDyingFrame) {
						isDestroyed = true;
					}

					currentFrame++;
					currentTick = 0;
					return;
				}  else {
					currentFrame = 0;
				}
			} else {
				currentTick++;
			}
		}
	}

	protected abstract void calculateNextState();

	public int getCurrentFrame() {
		return currentFrame;
	}

	public int getCurrentTick() {
		return currentTick;
	}
	
	public int getModelWidth() {
		return DrawingUtility.MODEL_WIDTH;
	}
	
	public int getHitBox() {
		return DrawingUtility.CHARACTER_HITBOX_WIDTH;
	}

}
