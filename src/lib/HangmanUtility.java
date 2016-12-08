package lib;

import java.util.Scanner;

import javafx.scene.input.KeyCode;
import logic.PlayerStatus;

public class HangmanUtility {
	private static String[] wordList = { "computer", "java", "activity", "alaska", "appearance", "javatar",
			"automobile", "falafel", "birthday", "canada", "central", "character", "chicken", "chosen", "cutting",
			"daily", "darkness", "shawarma", "disappear", "driving", "effort", "establish", "exact", "establishment",
			"fifteen", "football", "foreign", "frequently", "frighten", "function", "gradually", "hurried", "identity",
			"importance", "impossible", "invented", "italian", "journey", "lincoln", "london", "massage", "minerals",
			"outer", "paint", "particles", "personal", "physical", "progress", "quarter", "recognise", "replace",
			"rhythm", "situation", "slightly", "steady", "stepped", "strike", "successful", "sudden", "terrible",
			"traffic", "unusual", "volume", "yesterday" };

	private static String CurrentWord;
	private static String line;
	private static String usedChars, wrongChars;
	private static int correct, wrong;
	private static boolean wait = false;

	public HangmanUtility() {

		// TODO Auto-generated constructor stub
		/*
		 * int m = 0; int level = (int) (Math.random() * 64); // creates line
		 * first then put into .setText while (m < wordList[level].length()) {
		 * line += "__ "; m++; } // end for
		 * 
		 * System.out.println(wordList[level]);
		 * 
		 * System.out.println(line);
		 * 
		 * Scanner kb = new Scanner(System.in); char[] charArray =
		 * line.toCharArray();
		 * 
		 * int right = 0;
		 * 
		 * while (right < wordList[level].length()) {
		 * 
		 * char guess = kb.next().trim().charAt(0); if
		 * (wordList[level].contains(guess + "")) { int i = 0; for (i = 0; i <
		 * wordList[level].length(); i++) { if (wordList[level].charAt(i) ==
		 * guess) { charArray[3 * i] = ' '; charArray[3 * i + 1] = guess;
		 * right++; } } } line=new String(charArray).trim();
		 * System.out.println(line);
		 * 
		 * }
		 * 
		 * System.out.println("WON!");
		 */

	}

	public static String randomWord() {
		correct = 0;
		wrong = 0;
		usedChars = new String();
		line = new String();
		wrongChars = new String();
		int i = (int) (Math.random() * 64);
		CurrentWord = wordList[i];
		int m = 0;
		while (m < CurrentWord.length()) {
			line += "__ ";
			m++;
		}
		System.out.println(CurrentWord);

		return wordList[i];

	}

	public static void update() {
		String s = new String();
		if (InputUtility.getKeyTriggered().size() > 0) {
			s = InputUtility.getKeyTriggered().get(0).toString();

			if (s.length() == 1 && !wait) {
				char c = s.charAt(0);
				if (!usedChars.contains(c + "")) {
					if (CurrentWord.toUpperCase().contains(c + "")) {
						char[] charArray = line.toCharArray();
						int i = 0;
						for (i = 0; i < CurrentWord.length(); i++) {
							if (CurrentWord.toUpperCase().charAt(i) == c) {
								charArray[3 * i] = ' ';
								charArray[3 * i + 1] = c;
								correct++;
								PlayerStatus.getInstance().increaseGold();
							}
						}
						line = new String(charArray);
						System.out.println(line);
					} else if (!wrongChars.contains(c + "")) {
						wrongChars += c;
						wrong++;
						PlayerStatus.instance.wrongGuess();
					}
					usedChars += c;
				}

				if (correct == CurrentWord.length()) {
					wait = true;
					Thread t = new Thread(new Runnable() {

						public void run() {
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							HangmanUtility.randomWord();
							wait = false;
						}
					});
					t.start();
				}
			} 
		}
	}

	public static String getWrongChars() {
		return wrongChars;
	}

	public static String getUsedChars() {
		return usedChars;
	}

	public static String getLine() {
		return line;
	}

	public static int getCorrect() {
		return correct;
	}

	public static int getWrong() {
		return wrong;
	}

}
