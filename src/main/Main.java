package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import lib.*;
import logic.MainLogic;
import logic.PlayerStatus;
import ui.GameScreen;

public class Main extends Application {
	public static Main instance;
	
	private Stage primaryStage;
	//private Scene configScene;
	private Scene gameScene;
	//private MainLogic gameLogic;
	private GameScreen gameScreen;
	private PlayerStatus player;
	
	private boolean isGameSceneShown = false;
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		instance = this;
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Hangman");
		this.primaryStage.setResizable(false);
		this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});
		
		//this.gameLogic = new MainLogic();
		player=new PlayerStatus(3);
		
		gameScreen = new GameScreen();
		this.gameScene = new Scene(gameScreen);
		gameScreen.requestFocus();
		GameloopUtility.runGameloop();


		this.primaryStage.setScene(gameScene);
		this.primaryStage.show();
		/*drawGameScreen();
		HangmanUtility.randomWord();
		player.update();
		drawGameScreen();*/
		
	}
	
	public void drawGameScreen(){
		this.gameScreen.paintComponent();
	}
}
