package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lib.GameloopUtility;
import logic.MainLogic;
import ui.GameScreen;
import ui.HowtoScreen;
import ui.MenuScreen;

public class Main extends Application {
	public static Main instance;
	
	private Stage primaryStage;
	private Scene menuScene;
	private Scene gameScene;
	private Scene howToScene;
	private MainLogic gameLogic;
	private GameScreen gameScreen;
	private MenuScreen menuScreen;
	private HowtoScreen howtoScreen;
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		instance = this;
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Word Wars");
		this.primaryStage.setWidth(1280);
		this.primaryStage.setHeight(720);
		this.primaryStage.setResizable(false);
		this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});
		
		menuScreen = new MenuScreen();
		this.menuScene = new Scene(menuScreen);
		
		howtoScreen = new HowtoScreen();
		howToScene = new Scene(howtoScreen);
		this.primaryStage.setScene(menuScene);
		this.primaryStage.show();
		
		
	}
	public void changeScreenTo(String screen,int level){
		if (screen.equals("MenuScreen")){
			this.primaryStage.setScene(menuScene);
		} else if (screen.equals("HowToScreen")){
			this.primaryStage.setScene(howToScene);
		} else if (screen.equals("GameScreen")){
			this.gameLogic = new MainLogic();
			this.gameLogic.onStart(level);
			gameScreen = new GameScreen();
			this.gameScene = new Scene(gameScreen);
			gameScreen.requestFocus();
			GameloopUtility.runGameloop(gameLogic);
			this.primaryStage.setScene(gameScene);
		}
	}
	public void drawGameScreen(){
		this.gameScreen.paintComponent();
	}
}
