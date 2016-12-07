package logic;

public class Warrior extends Character {

	public Warrior(int x, int warriorClass,int team) {
		// TODO Auto-generated constructor stub
		super(x,team);
		this.hp=140+15*warriorClass;
		this.damage=25+10*warriorClass;
		this.towerDamage=3;
		this.armor=10+3*warriorClass;
		this.attackRange=5;
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
