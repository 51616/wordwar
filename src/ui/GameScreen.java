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
import lib.IRenderable;
import lib.IRenderableHolder;
import lib.InputUtility;

public class GameScreen extends StackPane {

	public static final int UNDER_UI_HEIGHT = 300, BACKGROUND_HEIGHT = 500, UPPER_UI_HEIGHT = 100;
	private Canvas canvas;

	public GameScreen() {
		// TODO Auto-generated constructor stub
		super();

		this.canvas = new Canvas(1600, 900);
		this.getChildren().add(this.canvas);
		

		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				System.out.println("KeyPressed : " + event.getCode().toString());
				if (!InputUtility.getKeyPressed(event.getCode())) {
					InputUtility.setKeyTriggered(event.getCode(), true);
					//HangmanUtility.update(event.getCode().toString());
					System.out.println("KeyTriggered : " + event.getCode().toString().charAt(0));
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
		/*gc.setFill(Color.BLACK);
		gc.fillRect(0, 500, 1600, 400);
		gc.fillRect(0, 0, 1600, UPPER_UI_HEIGHT);
		
		gc.setFill(Color.WHITE);
		gc.setFont(Font.font("Tahoma", FontWeight.BOLD, 30));
		String line = HangmanUtility.getLine();
		gc.fillText(line, 300, 600);
		String used=HangmanUtility.getUsedChars();
		gc.fillText(used, 300, 700);
		String wrongs=HangmanUtility.getWrongChars();
		gc.fillText(wrongs, 300, 800);*/
		
		gc.setFill(Color.BLACK);
		gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
		gc.fillRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
		for(IRenderable renderable : IRenderableHolder.getInstance().getEntities()){
			renderable.render(gc);
		}
		
	}

}
