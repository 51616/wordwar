package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import lib.DrawingUtility;

public class Balloon extends Entity implements Moveable {
	private static final int RIGHT = 1, LEFT = -1;
	private int direction;
	private int currentTick, actionTick;

	private static Image balloon = DrawingUtility.balloon;

	public Balloon(int x, int y) {
		// TODO Auto-generated constructor stub
		super(x, y);
		isMoving = true;
		direction = RIGHT;
		actionTick = 100;
		currentTick=0;
	}

	@Override
	public void move() {

		if (!this.isDestroyed) {
			this.calculateNextState();
			this.x = this.nextX;
			this.y = this.nextY;
		}

	}

	@Override
	protected void calculateNextState() {
		// TODO Auto-generated method stub
		try {
			Attackable firstAlly = PlayerStatus.getInstance().getAllies()
					.get(PlayerStatus.getInstance().getAllies().size() - 1);
			double firstAllyX = ((Entity) firstAlly).getX();
			if (this.getX() - firstAllyX < 20 && direction == LEFT) {
				direction *= -1;
			}
		} catch (Exception e) {
			//System.out.println("NO ALLY UNIT");
		}

		try {
			Attackable firstEnemy = PlayerStatus.getInstance().getEnemies().get(0);
			double firstEnemyX = ((Entity) firstEnemy).getX();
			if (this.getX() - firstEnemyX > 20 && direction == RIGHT) {
				System.out.println("TURN");
				direction *= -1;
			}
		} catch (Exception e) {
			//System.out.println("NO ENEMY UNIT");
		}
		
		nextX = x + speed * direction;
		
		if (currentTick == actionTick) {
			System.out.println("RANDOM");
			speed = Math.random();
			currentTick=0;
			return;
		}
		
		currentTick++;


	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		this.move();

	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(balloon, x, y);
	}

}
