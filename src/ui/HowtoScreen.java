package ui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import main.Main;

public class HowtoScreen extends VBox {
	public static StackPane header = new StackPane();
	public static StackPane middlePane = new StackPane();
	public static Canvas canvas = new Canvas(1280, 570);
	private Canvas homeCanvas = new Canvas(100, 100);
	private Image button1 = new Image("file:bin/res/HowTo-01.png");
	private Image button2 = new Image("file:bin/res/HowTo-02.png");
	private Image button3 = new Image("file:bin/res/HowTo-03.png");
	private Image button4 = new Image("file:bin/res/HowTo-04.png");
	private Image result1 = new Image("file:bin/res/Instruction-01.png");
	private Image result2 = new Image("file:bin/res/Instruction-02.png");
	private Image result3 = new Image("file:bin/res/Instruction-03.png");
	private Image result4 = new Image("file:bin/res/Instruction-04.png");
	private Image base = new Image("file:bin/res/base.png");
	private Image home = new Image("file:bin/res/homeClick-01.png");
	public static GraphicsContext gc = canvas.getGraphicsContext2D();

	public HowtoScreen() {
		super();
		this.setPrefSize(1280, 720);
		VBox vbox = new VBox();
		vbox.setPrefHeight(150);
		Text title = new Text("Word Wars");
		vbox.setAlignment(Pos.CENTER);
		title.setFont(MenuScreen.titleFont);
		title.setFill(Color.WHITE);

		Text pageName = new Text("How to play?");
		vbox.setAlignment(Pos.BOTTOM_CENTER);
		pageName.setFont(MenuScreen.mediumFont);
		pageName.setFill(Color.WHITE);
		vbox.setStyle("-fx-background-color: black;");
		vbox.getChildren().addAll(title, pageName);
		header.getChildren().addAll(vbox, createHomeButton());
		this.getChildren().add(header);

		drawCanvas();
		gc.drawImage(base, 300, 50);
	}

	private void drawCanvas() {
		gc.drawImage(MenuScreen.background, 0, 0, canvas.getWidth(), canvas.getHeight());
		middlePane.getChildren().add(canvas);
		middlePane.getChildren().add(buttonsPane());
		this.getChildren().add(middlePane);
	}

	private GridPane buttonsPane() {
		GridPane btnPane = new GridPane();
		btnPane.setPadding(new Insets(50, 0, 0, 100));
		btnPane.setVgap(10);
		btnPane.add(createButtons(button1, result1), 0, 0);
		btnPane.add(createButtons(button2, result2), 0, 1);
		btnPane.add(createButtons(button3, result3), 0, 2);
		btnPane.add(createButtons(button4, result4), 0, 3);
		return btnPane;
	}

	private StackPane createButtons(Image img, Image resultImg) {
		StackPane button = new StackPane();
		Canvas canvasNew = new Canvas(img.getWidth(), img.getHeight());
		GraphicsContext gc = canvasNew.getGraphicsContext2D();
		gc.drawImage(img, 0, 0);
		button.getChildren().add(canvasNew);
		button.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				HowtoScreen.gc.drawImage(resultImg, 300, 50);
			}
		});
		button.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				HowtoScreen.gc.drawImage(base, 300, 50);
			}
		});
		return button;
	}

	public StackPane createHomeButton() {
		StackPane stack = new StackPane();
		stack.setPrefSize(100, 100);
		GraphicsContext gc = homeCanvas.getGraphicsContext2D();
		gc.drawImage(home, 0, 0);
		stack.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Main.instance.changeScreenTo("MenuScreen", 0);
			}
		});
		stack.getChildren().add(homeCanvas);
		stack.setAlignment(Pos.TOP_RIGHT);
		return stack;
	}
}
