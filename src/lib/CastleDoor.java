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

	public CastleDoor(Castle castle) {
		// TODO Auto-generated constructor stub
		x=castle.getX();
		y=castle.getY();
		team=castle.getTeam();
		
		IRenderableHolder.getInstance().addAndSort(this);
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(team==Character.ALLIES)
			gc.drawImage(castleDoor, x, GameScreen.UPPER_UI_HEIGHT + GameScreen.BACKGROUND_HEIGHT - DrawingUtility.CASTLE_HEIGHT,DrawingUtility.CASTLE_WIDTH,DrawingUtility.CASTLE_HEIGHT);
		else
			gc.drawImage(castleDoor, x+DrawingUtility.CASTLE_WIDTH, GameScreen.UPPER_UI_HEIGHT + GameScreen.BACKGROUND_HEIGHT - DrawingUtility.CASTLE_HEIGHT,-DrawingUtility.CASTLE_WIDTH,DrawingUtility.CASTLE_HEIGHT);
		
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

}
