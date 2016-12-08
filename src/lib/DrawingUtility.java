package lib;

import javafx.scene.image.Image;

public class DrawingUtility {
	public static final int MODEL_HEIGHT=100,MODEL_WIDTH=100,ITEM_WIDTH=50,ITEM_HEIGTH=50;
	public static final int ALLIES_CASTLE=300,ENEMIES_CASTLE=1500;
	public static final Image bg = getImage("bg-02.png");
	public static final Image castle = getImage("castle-01.png");
	public static final Image warrior = getImage("batches-01.png");
	public static final Image archer = getImage("batches-02.png");
	public static final Image mage = getImage("batches-03.png");
	private static Image getImage(String directory) {

		try {
			Image img = new Image(ClassLoader.getSystemResource(directory).toString());
			return img;
		} catch (Exception e) {
			System.out.println("image not found");
			return null;
		}
	}
	
	
	public void drawStatusBar() {
		
	}

}
