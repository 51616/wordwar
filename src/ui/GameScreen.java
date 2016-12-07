package ui;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lib.HangmanUtility;
import lib.InputUtility;

public class GameScreen extends StackPane {

	public static final int UNDER_UI_HEIGHT = 300, BACKGROUND_HEIGHT = 250, UPPER_UI_HEIGHT = 70;
	private Canvas canvas;

	public GameScreen() {
		// TODO Auto-generated constructor stub
		super();
		this.canvas = new Canvas(800, 600);
		HangmanUtility.randomWord();

		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				System.out.println("KeyPressed : " + event.getCode().toString());
				if (!InputUtility.getKeyPressed(event.getCode())) {
					InputUtility.setKeyTriggered(event.getCode(), true);
					System.out.println("KeyTriggered : " + event.getCode().toString());
				}
				InputUtility.setKeyPressed(event.getCode(), true);
			}
		});

		this.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				System.out.println("KeyReleased : " + event.getCode().toString());
				InputUtility.setKeyPressed(event.getCode(), false);
			}
		});

	}

	public void paintComponent() {
		GraphicsContext gc = this.canvas.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, 800, 400);
		gc.setFill(Color.WHITE);
		gc.setFont(Font.font("Tahoma", FontWeight.BOLD, 30));
		String line = HangmanUtility.getLine();
		gc.fillText(line, 400, 300);
	}

}
