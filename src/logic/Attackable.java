package logic;

public interface Attackable {
	
	public void attack(Attackable a);
	public void decreaseLife(double damage);
	public int getTeam();
	public int getModelWidth();
	public int getHitBox();

}
