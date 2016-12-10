package logic;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import lib.DrawingUtility;
import lib.IRenderable;
import lib.IRenderableHolder;
import ui.GameScreen;

public class Projectile extends Entity implements Moveable, IRenderable {
	private static Image arrow = DrawingUtility.arrow;
	private static Image magic = DrawingUtility.magic;

	public static final int ARROW = 1, MAGIC = 2;
	private int direction;
	private Character shooter;

	public Projectile(Character shooter) {
		// TODO Auto-generated constructor stub
		super(shooter.getX(), shooter.getY());

		this.shooter = shooter;
		if (shooter.getTeam() == Character.ALLIES) {
			direction = 1;
		} else if (shooter.getTeam() == Character.ENEMIES) {
			direction = -1;
		}
		speed = 5;

		IRenderableHolder.getInstance().addAndSort(this);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		if (!this.isDestroyed) {
			this.calculateNextState();
			this.x = this.nextX;
			this.y = this.nextY;
		}
	}

	@Override
	protected void calculateNextState() {
		// TODO Auto-generated method stub
		List<Attackable> allies = PlayerStatus.instance.getAllies();
		List<Attackable> enemies = PlayerStatus.instance.getEnemies();

		if (shooter.getTeam() == Character.ALLIES) {
			try {

				for (int i=0;i<enemies.size();i++) {
					Attackable enemyUnit=enemies.get(i);
					
					if ((((Entity)enemyUnit).getX()+enemyUnit.getModelWidth()-enemyUnit.getHitBox() - (this.getX() + DrawingUtility.PROJECTILE_WIDTH))<=0) {
						// System.out.println("ALLY INRANGE");

						shooter.attack(enemyUnit);
						this.isDestroyed=true;
						return;
					}
				}
			} catch (Exception e) {

			}
		} else if (shooter.getTeam() == Character.ENEMIES) {
			try {

				for (int i = allies.size() - 1; i >= 0; i--) {
					Attackable allyUnit = allies.get(i);
					if (this.getX() - (((Entity) allyUnit).getX() + allyUnit.getHitBox()) <= 0) {
						// System.out.println("ALLY INRANGE");
						shooter.attack(allyUnit);
						this.isDestroyed=true;
						return;
					}
				}
				/*
				Entity firstAlly = ((Entity) allies.get(0));
				if (this.getX() - 20 < firstAlly.getX()) {
					shooter.attack((Attackable) firstAlly);
					this.isDestroyed = true;
					return;
				}*/
			} catch (Exception e) {

			}
		}

		isMoving = true;
		nextX = x + speed * direction;

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		this.move();
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (shooter instanceof Archer) {
			if (shooter.getTeam() == Character.ALLIES) {
				gc.drawImage(arrow, x, y);
			} else if (shooter.getTeam() == Character.ENEMIES) {
				gc.drawImage(arrow, x + 60, y, -60, 100);
			}

		} else if (shooter instanceof Mage) {
			if (shooter.getTeam() == Character.ALLIES) {
				gc.drawImage(magic, x, y);
			} else if (shooter.getTeam() == Character.ENEMIES) {
				gc.drawImage(magic, x + 60, y, -60, 100);
			}

		}

	}

}
