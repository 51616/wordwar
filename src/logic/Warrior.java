package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import lib.DrawingUtility;

public class Warrior extends Character {
	Image warriorImage=DrawingUtility.warrior;

	public Warrior(int x, int warriorClass,int team) {
		// TODO Auto-generated constructor stub
		super(x,team);
		this.hp=140+15*warriorClass;
		this.damage=25+10*warriorClass;
		this.towerDamage=3;
		this.armor=10+3*warriorClass;
		this.attackRange=100;
		
		lastFrame=10;
		currentFrame=0;
	}



	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (!this.isDestroyed) {
			this.move();
			if (isMoving) {
				
			} else if (isAttacking) {
				
			}

		}
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(warriorImage, x, y,100,100);
		
	}

}
