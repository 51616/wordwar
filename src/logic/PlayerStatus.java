package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lib.DrawingUtility;
import lib.GameBackground;
import lib.HangmanUtility;
import lib.IRenderable;
import lib.IRenderableHolder;
import lib.InputUtility;
import ui.GameScreen;

public class PlayerStatus implements IRenderable {
	Castle castle;
	public static final int EASY_LEVEL = 1, MEDIUM_LEVEL = 2, HARD_LEVEL = 3;
	private int gold, goldBonus, goldPenalty;
	private int life;
	private List<Attackable> allies = new ArrayList<Attackable>();
	private List<Attackable> enemies = new ArrayList<Attackable>();
	private Comparator<Attackable> comparator;
	private int level;
	private int warriorClass, archerClass, mageClass;
	private int warriorExp, archerExp, mageExp;
	private boolean castleAvailable;
	private GameBackground background;

	private String line;

	public static PlayerStatus instance;

	public PlayerStatus(int level) {
		// TODO Auto-generated constructor stub

		
		this.level = level;
		this.castle=new Castle();
		castleAvailable = true;
		warriorClass = 1;
		archerClass = 1;
		mageClass = 1;

		warriorExp = 0;
		archerExp = 0;
		mageExp = 0;

		gold = 10;
		life = 100;

		goldBonus = 9 - level;
		goldPenalty = 1 + level;

		HangmanUtility.randomWord();
		line = HangmanUtility.getLine();

		background = new GameBackground();

		// Sort Character list in X-ascending order
		comparator = (Attackable o1, Attackable o2) -> {
			if (((Entity)o1).getX() > ((Entity)o2).getX())
				return 1;
			return -1;
		};

		instance = this;
		IRenderableHolder.getInstance().addAndSort(this);
		//new Warrior(1300,1,Character.ENEMIES);
		//new Warrior(1600,1,Character.ENEMIES);
	}

	public void setLine(String line) {
		this.line = line;
	}

	public void addAndSort(Character c) {
		if (c.getTeam() == Character.ALLIES)
			allies.add(c);
		else
			enemies.add(c);

		sort();
	}

	public void sort() {
		Collections.sort(allies, comparator);
		Collections.sort(enemies, comparator);
	}

	public void increaseGold() {
		gold += goldBonus;
	}

	public void decreaseGold() {
		gold -= goldPenalty;

	}

	public void decreaseLife() {
		life -= 3;
	}

	public void decreaseLife(int damage) {
		life -= damage;
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

	public List<Attackable> getAllies() {
		return allies;
	}

	public List<Attackable> getEnemies() {
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

	public boolean isCastleAvailable() {
		return castleAvailable;
	}

	public void wrongGuess() {
		if (gold >= goldPenalty) {
			decreaseGold();
		} else if (gold == 0) {
			decreaseLife();
		} else {
			gold = 0;
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.WHITE);
		gc.setFont(Font.font("Tahoma", FontWeight.BOLD, 30));
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double fontWidth = fontLoader.computeStringWidth(line, gc.getFont());
		gc.fillText(line, 800 - fontWidth / 2, GameScreen.UPPER_UI_HEIGHT + GameScreen.BACKGROUND_HEIGHT + 100);
		gc.fillText(HangmanUtility.getWrongChars(), 800 - fontWidth / 2,
				GameScreen.UPPER_UI_HEIGHT + GameScreen.BACKGROUND_HEIGHT + 150);
		gc.fillText("LIFE : " + life, 50, 50);
		gc.fillText("GOLD : " + gold, 300, 50);

	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

	public void update() {
		setLine(HangmanUtility.getLine());
	}

}
