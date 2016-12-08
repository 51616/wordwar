package lib;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import ui.GameScreen;

public class GameBackground implements IRenderable {
	private Image bgImage = null;
	public GameBackground() {
		// TODO Auto-generated constructor stub
		bgImage=DrawingUtility.bg;
		IRenderableHolder.getInstance().addAndSort(this);
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(bgImage, 50, GameScreen.UPPER_UI_HEIGHT+20, 1500, GameScreen.BACKGROUND_HEIGHT);
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Integer.MIN_VALUE;
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

}
