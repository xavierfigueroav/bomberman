/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import Threads.Balloon;
import Threads.Clock;
import Threads.GameEngine;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class Bomberman extends Application {
    
    private static Stage stage;
    public static GameEngine game;
    private static Scene welcomeScene, newGameScene, instructionsScene, rankingScene;
    
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setResizable(false);
        stage.setTitle("Bomberman");
        
        welcomeScene = new Scene(new WelcomeScene().getRoot(),800,600);
        instructionsScene = new Scene(new InstructionsScene().getRoot(),800,600);
        
        switchToWelcomeScene();
        
        stage.show();
    }
    
    public static void switchToWelcomeScene(){
        
        stage.setScene(welcomeScene);
        welcomeScene.getStylesheets().add("/styles/welcome.css");
        
    }
    
    public static void setNewGameScene(){

        newGameScene = new Scene(new NewGameScene().getRoot(),800,600);
        game = new GameEngine();
        stage.setScene(newGameScene);
        returnToWelcomeSceneOnKeyPress(newGameScene);
        
    }
    
    public static void setNewGameScene(int lives, int score, Clock clock){

        newGameScene = new Scene(new NewGameScene().getRoot(),800,600);
        game = new GameEngine(lives, score, clock);
        stage.setScene(newGameScene);
        returnToWelcomeSceneOnKeyPress(newGameScene);
    }
    
    public static void switchToInstructionsScene(){
        
        stage.setScene(instructionsScene);
        instructionsScene.getStylesheets().add("/styles/instructionsScene.css");
        returnToWelcomeSceneOnKeyPress(instructionsScene);
        
    }
    
    public static void switchToRankingScene(){
        
        rankingScene = new Scene(new RankingScene().getRoot(),800,600);
        stage.setScene(rankingScene);
        rankingScene.getStylesheets().add("/styles/rankingScene.css");
        returnToWelcomeSceneOnKeyPress(rankingScene);
        
    }
    
    public static void switchToEndGameScene(boolean isWinner, int score){
        Scene endGameScene = new Scene(new GameOverScene(isWinner, score).getRoot(),800,600);
        stage.setScene(endGameScene);
        endGameScene.getStylesheets().add("/styles/GameOverScene.css");
        
    }
    
    private static void returnToWelcomeSceneOnKeyPress(Scene scene){
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event){
                if(event.getCode().equals(KeyCode.BACK_SPACE)){
                    
                    if(newGameScene == null){
                        switchToWelcomeScene();
                    } else if(scene.getRoot().equals(newGameScene.getRoot())){
                        game.stopBalloons();
                        game.stopClock();
                        switchToWelcomeScene();
                    } else{
                        switchToWelcomeScene();
                    }
                    
                }
            }
        });
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
