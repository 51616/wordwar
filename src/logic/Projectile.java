package logic;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import lib.DrawingUtility;
import lib.IRenderable;
import lib.IRenderableHolder;
import ui.GameScreen;

public class Projectile extends Entity implements Moveable,IRenderable {
	private static Image arrow = DrawingUtility.arrow;
	private static Image magic = DrawingUtility.magic;

	public static final int ARROW = 1, MAGIC = 2;
	private int direction;
	private Character shooter;

	public Projectile(Character shooter) {
		// TODO Auto-generated constructor stub
		super(shooter.getX()+20, GameScreen.UPPER_UI_HEIGHT + GameScreen.BACKGROUND_HEIGHT - 30);

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
				Entity firstEnemy = ((Entity) enemies.get(0));
				if (this.getX() + 20 > firstEnemy.getX()) {
					shooter.attack((Attackable) firstEnemy);
					this.isDestroyed = true;
					return;
				}
			} catch (Exception e) {

			}
		} 	else if (shooter.getTeam() == Character.ENEMIES) {
			try {
				Entity firstAlly = ((Entity) allies.get(0));
				if (this.getX() + 20 > firstAlly.getX()) {
					shooter.attack((Attackable) firstAlly);
					this.isDestroyed = true;
					return;
				}
			} catch (Exception e) {

			}
		}
		
		isMoving = true;
		nextX = x + speed*direction;	

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
			gc.drawImage(arrow, x, y,40,10);
			
		}else if (shooter instanceof Mage) {
			gc.drawImage(magic, x, y,40,10);
			
		}

	}

}
