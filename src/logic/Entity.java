package logic;

import lib.*;

public abstract class Entity implements IRenderable {
	
	protected double x,y;
	protected int z;
	protected double nextX,nextY;
	protected int direction;
	protected double speed;
	protected boolean isDestroyed,isAttacking,isMoving;
	

	public Entity(double x,double y) {
		// TODO Auto-generated constructor stub
		this.x=x;
		this.y=y;
		this.z=1;
		nextX=x;
		nextY=y;
		isDestroyed=false;
		isAttacking=false;
		isMoving=false;
		
		IRenderableHolder.getInstance().addAndSort(this);
		
	}
	
	protected abstract void calculateNextState();
	public abstract void update();
	
	
	

	public double getX() {
		return x;
	}

	public double getY() {
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
