package lib;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.Castle;
import logic.Character;
import ui.GameScreen;

public class CastleDoor implements IRenderable {
	Image castleDoor = DrawingUtility.castleDoor;
	private double x,y;
	private int team;
	private Castle castle;

	public CastleDoor(Castle castle) {
		// TODO Auto-generated constructor stub
		this.castle=castle;
		x=castle.getX();
		y=castle.getY();
		team=castle.getTeam();
		
		IRenderableHolder.getInstance().addAndSort(this);
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(team==Character.ALLIES)
			gc.drawImage(castleDoor, x, y,DrawingUtility.CASTLE_WIDTH,DrawingUtility.CASTLE_HEIGHT);
		else
			gc.drawImage(castleDoor, x+DrawingUtility.CASTLE_WIDTH, y,-DrawingUtility.CASTLE_WIDTH,DrawingUtility.CASTLE_HEIGHT);
		
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Integer.MIN_VALUE+1;
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Castle getCastle() {
		return castle;
	}

}
