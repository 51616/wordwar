package logic;

public interface Attackable {
	
	public void attack(Attackable a);
	public void decreaseLife(int damage);
	public int getTeam();

}
