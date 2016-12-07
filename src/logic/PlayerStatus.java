package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;




public class PlayerStatus {
	Castle castle;
	public static final int EASY_LEVEL=1,MEDIUM_LEVEL=2,HARD_LEVEL=3;
	private int gold,goldBonus,goldPenalty;
	private int life;
	private List<Character> allies=new ArrayList<Character>();
	private List<Character> enemies=new ArrayList<Character>();
	private Comparator<Character> comparator;
	private int level;
	private int warriorClass,archerClass,mageClass;
	private int warriorExp,archerExp,mageExp;
	private boolean castleIsAvailable;

	public static PlayerStatus instance;


	public PlayerStatus(int level) {
		// TODO Auto-generated constructor stub
		this.level=level;
		castleIsAvailable=true;
		warriorClass=1;
		archerClass=1;
		mageClass=1;
		
		warriorExp=0;
		archerExp=0;
		mageExp=0;
		
		
		gold=50;
		life=100;
		
		
		goldBonus=9-level;
		goldPenalty=1+level;
		
		
		//Sort Character list in X-ascending order
		comparator = (Character o1, Character o2) -> {
			if ( o1.getX() >  o2.getX())
				return 1;
			return -1;
		};
		
		instance=this;
		
	}
	
	public void addAndSort(Character c) {
		if (c.getTeam()==Character.ALLIES)
			allies.add(c);
		else
			enemies.add(c);
		
		sort();
	}
	
	public void sort(){
		Collections.sort(allies, comparator);
		Collections.sort(enemies, comparator);
	}
	
	public void increaseGold() {
		gold+=goldBonus;
	}
	
	public void decreaseGold() {
		gold-=goldPenalty;
		
	}
	
	public void decreaseLife() {
		life-=3;
	}
	
	public void decreaseLife(int damage) {
		life-=damage;
	}
	
	public Castle getCastle() {
		return castle;
	}

	public int getGold() {
		return gold;
	}

	public int getGoldBonus() {
		return goldBonus;
	}

	public int getGoldPenalty() {
		return goldPenalty;
	}

	public int getLife() {
		return life;
	}

	public List<Character> getAllies() {
		return allies;
	}

	public List<Character> getEnemies() {
		return enemies;
	}

	public int getLevel() {
		return level;
	}

	public int getWarriorClass() {
		return warriorClass;
	}

	public int getArcherClass() {
		return archerClass;
	}

	public int getMageClass() {
		return mageClass;
	}

	public int getWarriorExp() {
		return warriorExp;
	}

	public int getArcherExp() {
		return archerExp;
	}

	public int getMageExp() {
		return mageExp;
	}

	public static PlayerStatus getInstance() {
		return instance;
	}
	
	public boolean isCastleIsAvailable() {
		return castleIsAvailable;
	}

}
