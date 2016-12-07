package logic;

public class Mage extends Character {

	public Mage(int x, int mageClass,int team) {
		// TODO Auto-generated constructor stub
		super(x,team);
		this.hp=70+10*mageClass;
		this.damage=30+5*mageClass;
		this.towerDamage=1;
		this.armor=2+2*mageClass;
		this.attackRange=80;
	}

	@Override
	protected void calculateNextState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
