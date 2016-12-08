package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import lib.DrawingUtility;
import lib.GameBackground;
import lib.IRenderable;
import ui.GameScreen;

public class Castle extends Entity implements Attackable{
	Image castleImage=null;
	private int team;

	public Castle(int x,int team) {
		// TODO Auto-generated constructor stub
		super(x,GameScreen.BACKGROUND_HEIGHT+GameScreen.UPPER_UI_HEIGHT);
		this.team=team;
		castleImage=DrawingUtility.castle;
		
		PlayerStatus.getInstance().addAndSort(this);
		
		
	}
	
	public void deploy(int unitType) {
		System.out.println("Deploy");
		if (unitType==1)
			new Warrior(60,PlayerStatus.getInstance().getWarriorClass(),Character.ALLIES);
		else if (unitType==2)
			new Archer(60,PlayerStatus.getInstance().getArcherClass(),Character.ALLIES);
		else
			new Mage(60,PlayerStatus.getInstance().getMageClass(),Character.ALLIES);
	}

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
		
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(castleImage, x, GameScreen.UPPER_UI_HEIGHT+GameScreen.BACKGROUND_HEIGHT-400);
		
	}

	@Override
	public void decreaseLife(int damage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTeam() {
		// TODO Auto-generated method stub
		return team;
	}

}
