package logic;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import lib.AudioUtility;
import lib.CastleDoor;
import lib.DrawingUtility;
import lib.GameBackground;
import lib.IRenderable;
import lib.IRenderableHolder;
import ui.GameScreen;

public class Castle extends Entity implements Attackable {
	Image castleImage = null;
	private static List<Image> castleDying = DrawingUtility.castleDying;
	private int team;
	private boolean isDying;
	private double hp, maxHp;
	private int currentFrame, lastDyingFrame, currentTick, AnimateTick;

	public Castle(int x, int team) {
		// TODO Auto-generated constructor stub
		super(x, GameScreen.UPPER_UI_HEIGHT + GameScreen.BACKGROUND_HEIGHT - DrawingUtility.CASTLE_HEIGHT);
		this.team = team;
		new CastleDoor(this);
		isDying = false;
		maxHp = 100;
		hp = maxHp;
		castleImage = DrawingUtility.castle;
		currentFrame = 0;
		currentTick = 0;
		lastDyingFrame = 4;
		AnimateTick = 5;
		z = Integer.MAX_VALUE;

		PlayerStatus.getInstance().addAndSort(this);

	}

	/*
	 * public void deploy(int unitType) { System.out.println("Deploy"); if
	 * (unitType == 1) new Warrior(60,
	 * PlayerStatus.getInstance().getWarriorClass(), Character.ALLIES); else if
	 * (unitType == 2) new Archer(60,
	 * PlayerStatus.getInstance().getArcherClass(), Character.ALLIES); else new
	 * Mage(60, PlayerStatus.getInstance().getMageClass(), Character.ALLIES); }
	 */

	@Override
	public void attack(Attackable a) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void calculateNextState() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (currentTick == AnimateTick) {
			if (!isDestroyed && isDying) {

				if (currentFrame == lastDyingFrame) {
					isDestroyed = true;

				}

				currentFrame++;
				currentTick = 0;
				return;
			}
		} else {
			currentTick++;
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.WHITE);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		if (team == Character.ALLIES) {
			if (isDying) {
				gc.drawImage(castleDying.get(currentFrame), x, y, DrawingUtility.CASTLE_WIDTH,
						DrawingUtility.CASTLE_HEIGHT);
			} else {
				gc.drawImage(castleImage, x, y, DrawingUtility.CASTLE_WIDTH, DrawingUtility.CASTLE_HEIGHT);
			}
			gc.fillRoundRect(x , y + 20, (DrawingUtility.CASTLE_WIDTH * 0.6) * getHpRatio(),
					10, 10, 10);
			gc.strokeRoundRect(x , y + 20,
					(DrawingUtility.CASTLE_WIDTH * 0.6) * getHpRatio(), 10, 10, 10);
		} else {
			if (isDying) {
				gc.drawImage(castleDying.get(currentFrame), x + DrawingUtility.CASTLE_WIDTH, y,
						-DrawingUtility.CASTLE_WIDTH, DrawingUtility.CASTLE_HEIGHT);
			} else {
				gc.drawImage(castleImage, x + DrawingUtility.CASTLE_WIDTH, y, -DrawingUtility.CASTLE_WIDTH,
						DrawingUtility.CASTLE_HEIGHT);
			}
			gc.fillRoundRect(x + getModelWidth() - getHitBox(), y + 20, (DrawingUtility.CASTLE_WIDTH * 0.6) * getHpRatio(),
					10, 10, 10);
			gc.strokeRoundRect(x + getModelWidth() - getHitBox(), y + 20,
					(DrawingUtility.CASTLE_WIDTH * 0.6) * getHpRatio(), 10, 10, 10);
		}

		
	}

	@Override
	public void decreaseLife(double damage) {
		// TODO Auto-generated method stub
		hp -= damage;
		if (hp <= 0) {
			hp = 0;
			isDying = true;
			CastleDoor thisCastleDoor = null;
			;
			for (IRenderable i : IRenderableHolder.getInstance().getEntities()) {
				if (i instanceof CastleDoor && ((CastleDoor) i).getCastle() == this) {
					thisCastleDoor = (CastleDoor) i;
					break;
				}
			}
			IRenderableHolder.getInstance().getEntities().remove(thisCastleDoor);
			AudioUtility.playCastleDestruction();
		}
		


	}

	@Override
	public int getTeam() {
		// TODO Auto-generated method stub
		return team;
	}

	public double getHp() {
		// TODO Auto-generated method stub
		return hp;
	}

	public double getHpRatio() {
		return hp / maxHp;
	}

	public int getModelWidth() {
		return DrawingUtility.CASTLE_WIDTH;
	}

	public int getHitBox() {
		return DrawingUtility.CASTLE_HITBOX_WIDTH;
	}

}
