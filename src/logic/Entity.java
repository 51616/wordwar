package logic;

import lib.*;

public abstract class Entity implements IRenderable {
	
	protected int x,y,z;
	protected int nextX,nextY;
	protected int direction,speed;
	protected boolean isDestroyed,isAttacking,isMoving;
	

	public Entity(int x,int y) {
		// TODO Auto-generated constructor stub
		this.x=x;
		this.y=y;
		this.z=1;
		isDestroyed=false;
		isAttacking=false;
		isMoving=false;
		
		IRenderableHolder.getInstance().addAndSort(this);
	}
	
	protected abstract void calculateNextState();
	public abstract void update();
	
	
	

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public int getDirection() {
		return direction;
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}

	public boolean isAttacking() {
		return isAttacking;
	}

	public boolean isMoving() {
		return isMoving;
	}
}
