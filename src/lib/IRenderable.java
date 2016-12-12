package lib;

import javafx.scene.canvas.GraphicsContext;

public interface IRenderable {
	public void render(GraphicsContext gc);
	public int getZ();
	public boolean isDestroyed();
}
