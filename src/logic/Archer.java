package logic;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import lib.*;

public class Archer extends Character {

	public Archer(int x, int archerClass, int team) {
		// TODO Auto-generated constructor stub
		super(x, team);
		this.hp = 100 + 10 * archerClass;
		this.damage = 20 + 5 * archerClass;
		this.towerDamage = 2;
		this.armor = 5 + 2 * archerClass;
		this.attackRange = 300;
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (!this.isDestroyed) {
			this.move();
			if (isAttacking) {
				
			} else if (isMoving) {
				
			}
		}

	}


	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
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

