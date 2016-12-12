package ui;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.Main;

public class MenuScreen extends VBox {
	public static final Font titleFont = Font.loadFont("file:bin/res/tyepaloon.ttf", 96);
	public static final Font mediumFont = Font.loadFont("file:bin/res/tyepaloon.ttf", 40);
	public static final Font smallFont = Font.loadFont("file:bin/res/tyepaloon.ttf", 32);
	private static VBox gameTitle = new VBox();
	private static StackPane middlePane = new StackPane();
	private static ChoiceBox<String> difficulties;
	private Canvas canvas;

	public static ChoiceBox<String> getDifficulties() {
		return difficulties;
	}

	public static Image background = new Image("file:bin/res/front_bg.png");

	public MenuScreen() {
		// TODO Auto-generated constructor stub
		super();
		this.setPrefSize(1280, 720);
		gameTitle.setPrefHeight(250);
		Label upperTitle = new Label("Rujikorn & Vorawee");
		Label middleTitle = new Label("present");
		setSmallStyle(upperTitle);
		setSmallStyle(middleTitle);
		Text title = new Text("Word Wars");
		gameTitle.setAlignment(Pos.BOTTOM_CENTER);
		title.setFont(titleFont);
		title.setFill(Color.WHITE);

		gameTitle.getChildren().addAll(upperTitle, middleTitle, title);
		gameTitle.setStyle("-fx-background-color: black;");
		this.getChildren().add(gameTitle);

		GridPane choosingPane = new GridPane();
		drawBackground();
		GridPane difficultyPane = new GridPane();
		difficultyPane.setHgap(20);
		difficultyPane.setPadding(new Insets(25));

		Text difficultyText = new Text("Difficulty : ");
		setMediumStyle(difficultyText);
		difficulties = new ChoiceBox<>(FXCollections.observableArrayList("EASY", "MEDIUM", "HARD"));
		difficulties.setPrefSize(80, 40);
		difficulties.getSelectionModel().selectFirst();
		difficulties.setTooltip(new Tooltip("Select the difficulty you want"));
		difficulties.setStyle("-fx-background-color: whitesmoke ;-fx-mark-color: blue ;");
		difficultyPane.add(difficultyText, 0, 0);
		difficultyPane.add(difficulties, 1, 0);
		difficultyPane.setAlignment(Pos.CENTER);
		choosingPane.add(difficultyPane, 0, 1);

		Text newGameText = new Text("New Game");
		setMediumStyle(newGameText);
		setMouseOnText(newGameText);

		newGameText.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				String a = MenuScreen.getDifficulties().getValue();
				if (a.equals("EASY")) {
					Main.instance.changeScreenTo("GameScreen", 1);
				} else if (a.equals("MEDIUM")) {
					Main.instance.changeScreenTo("GameScreen", 2);
				} else {
					Main.instance.changeScreenTo("GameScreen", 3);
				}
			}
		});
		addTextToGrid(newGameText, choosingPane, 0);

		Text howToPlayText = new Text("How to Play ?");
		setMouseOnText(howToPlayText);
		setMediumStyle(howToPlayText);
		howToPlayText.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Main.instance.changeScreenTo("HowToScreen", 0);
			}
		});
		addTextToGrid(howToPlayText, choosingPane, 2);

		Text scoreText = new Text("Leader Board");
		setMouseOnText(scoreText);
		setMediumStyle(scoreText);
		addTextToGrid(scoreText, choosingPane, 3);

		choosingPane.setVgap(5);
		choosingPane.setPadding(new Insets(50, 0, 0, 400));
		middlePane.getChildren().add(choosingPane);
		middlePane.setAlignment(Pos.CENTER);
		this.getChildren().add(middlePane);
		

	}

	public void setSmallStyle(Label text) {
		text.setFont(smallFont);
		text.setTextFill(Color.WHITE);
		text.setAlignment(Pos.CENTER);
	}

	public void setMediumStyle(Text text) {
		text.setFont(mediumFont);
		text.setFill(Color.WHITE);
	}

	public void addTextToGrid(Text text, GridPane grid, int y) {
		FlowPane flow = new FlowPane();
		flow.setPadding(new Insets(25));
		flow.setAlignment(Pos.CENTER);
		flow.getChildren().add(text);
		grid.add(flow, 0, y);
	}

	public void drawBackground() {
		this.canvas = new Canvas(1280,470);
		GraphicsContext gc = this.canvas.getGraphicsContext2D();
		gc.drawImage(background, 0, 0, this.canvas.getWidth(), this.canvas.getHeight());
		middlePane.getChildren().add(canvas);
	}

	public void setMouseOnText(Text text) {
		text.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {

				text.setFill(Color.BLACK);
			}
		});
		text.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				text.setFill(Color.WHITE);
			}
		});
	}
}
