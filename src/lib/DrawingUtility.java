package lib;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class DrawingUtility {
	public static final int SCREEN_HEIGHT=900,SCREEN_WIDTH=1600;
	public static final int MODEL_HEIGHT=100,MODEL_WIDTH=60,ITEM_WIDTH=50,ITEM_HEIGTH=50;
	public static final int ALLIES_CASTLE=50,ENEMIES_CASTLE=1400;
	public static final int CASTLE_WIDTH=150,CASTLE_HEIGHT=200;
	public static final Image bg = getImage("bg-02.png");
	public static final Image castle = getImage("still-castle-1.png");
	public static final Image castleDoor = getImage("still-castle-2.png");
	public static final List<Image> warriorWalk = getWarriorWalk();
	public static final List<Image> archerWalk = getArcherWalk();
	public static final List<Image> mageWalk = getMageWalk();
	
	public static final List<Image> warriorAtk = getWarriorAtk();
	public static final List<Image> archerAtk = getArcherAtk();
	public static final List<Image> mageAtk = getMageAtk();
	
	public static final Image arrow = getImage("bullet-02.png");
	public static final Image magic = getImage("bullet-01.png");
	
	public static final Image balloon = getImage("balloon.png");

	public static final Image trash=getImage("res/trash-01.png");
	public static final Image warLogo = getImage("batches-01.png");
	public static final Image archLogo = getImage("batches-02.png");
	public static final Image mageLogo = getImage("batches-03.png");
	private static Image getImage(String directory) {

		try {
			Image img = new Image(ClassLoader.getSystemResource(directory).toString());
			return img;
		} catch (Exception e) {
			System.out.println("image not found");
			return null;
		}
	}
	
	private static List<Image> getWarriorWalk() {
		List<Image> images=new ArrayList<Image>();
		images.add(getImage("res/1walking-01.png"));
		images.add(getImage("res/1walking-02.png"));
		images.add(getImage("res/1walking-03.png"));
		images.add(getImage("res/1walking-04.png"));
		images.add(getImage("res/1walking-05.png"));
		images.add(getImage("res/1walking-06.png"));
		images.add(getImage("res/1walking-07.png"));
		images.add(getImage("res/1walking-08.png"));
		return images;
	}
	
	private static List<Image> getWarriorAtk() {
		List<Image> images=new ArrayList<Image>();
		images.add(getImage("res/Attack1-01.png"));
		images.add(getImage("res/Attack1-02.png"));
		images.add(getImage("res/Attack1-03.png"));
		images.add(getImage("res/Attack1-04.png"));
		images.add(getImage("res/Attack1-05.png"));
		images.add(getImage("res/Attack1-06.png"));
		return images;
	}
	
	private static List<Image> getArcherWalk() {
		List<Image> images=new ArrayList<Image>();
		images.add(getImage("res/2walking-01.png"));
		images.add(getImage("res/2walking-02.png"));
		images.add(getImage("res/2walking-03.png"));
		images.add(getImage("res/2walking-04.png"));
		images.add(getImage("res/2walking-05.png"));
		images.add(getImage("res/2walking-06.png"));
		images.add(getImage("res/2walking-07.png"));
		images.add(getImage("res/2walking-08.png"));
		return images;
	}
	
	private static List<Image> getArcherAtk() {
		List<Image> images=new ArrayList<Image>();
		images.add(getImage("res/Attack2-01.png"));
		images.add(getImage("res/Attack2-02.png"));
		images.add(getImage("res/Attack2-03.png"));
		images.add(getImage("res/Attack2-04.png"));
		images.add(getImage("res/Attack2-05.png"));
		images.add(getImage("res/Attack2-06.png"));
		return images;
	}
	
	private static List<Image> getMageWalk() {
		List<Image> images=new ArrayList<Image>();
		images.add(getImage("res/3walking-01.png"));
		images.add(getImage("res/3walking-02.png"));
		images.add(getImage("res/3walking-03.png"));
		images.add(getImage("res/3walking-04.png"));
		images.add(getImage("res/3walking-05.png"));
		images.add(getImage("res/3walking-06.png"));
		images.add(getImage("res/3walking-07.png"));
		images.add(getImage("res/3walking-08.png"));
		return images;
	}
	
	private static List<Image> getMageAtk() {
		List<Image> images=new ArrayList<Image>();
		images.add(getImage("res/Attack3-01.png"));
		images.add(getImage("res/Attack3-02.png"));
		images.add(getImage("res/Attack3-03.png"));
		images.add(getImage("res/Attack3-04.png"));
		images.add(getImage("res/Attack3-05.png"));
		images.add(getImage("res/Attack3-06.png"));
		return images;
	}
	
	
	public void drawStatusBar() {
		
	}

}
