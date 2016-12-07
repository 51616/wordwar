package logic;
import ui.*;
import lib.*;

public abstract class Character extends Entity implements Moveable,Attackable {
	public static final int ALLIES=1,ENEMIES=-1;
	protected int hp;
	protected int damage,towerDamage,armor,attackRange;
	protected boolean isAttacking;
	protected int x;
	protected int team;
	
	public Character(int x,int team) {
		// TODO Auto-generated constructor stub
		super(x,GameScreen.UPPER_UI_HEIGHT+GameScreen.BACKGROUND_HEIGHT-DrawingUtility.MODEL_HEIGHT,Integer.MAX_VALUE-1);
		this.x=x;
		speed=10;
		isAttacking=false;
		this.team=team;
		nextY=y;
	}
	
	//public abstract boolean isCollide(Item i);
	
	public boolean isTeamWith(Character c) {
		if (c.getTeam()==this.getTeam())
			return true;
		return false;
	}
	
	public int getTeam() {
		return team;
	}



	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decreaseLife() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

}
