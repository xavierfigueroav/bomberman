/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import threads.GameEngine;
import threads.Clock;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */

public class Bomberman extends Application {
    
    private static Stage stage;
    public static GameEngine gameEngine;
    private static Scene welcomeScene, newGameScene, instructionsScene, rankingScene;
    
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setResizable(false);
        stage.setTitle("Bomberman");
        
        welcomeScene = new Scene(new Welcome().getRoot(),800,600);
        instructionsScene = new Scene(new Instructions().getRoot(),800,600);
        
        loadFonts();
        
        switchToWelcomeScene();
        
        stage.show();
    }
    
    private static void loadFonts(){
        Font.loadFont(Welcome.class.getResource("/fonts/8-bit-pusab.ttf").toExternalForm(),10);
        
        Font.loadFont(GameOver.class.getResource("/fonts/8-bit-pusab.ttf").toExternalForm(),10);
        Font.loadFont(GameOver.class.getResource("/fonts/pixChicago.ttf").toExternalForm(),10);
        
        Font.loadFont(Instructions.class.getResource("/fonts/8-bit-pusab.ttf").toExternalForm(),10);
        Font.loadFont(Instructions.class.getResource("/fonts/pixChicago.ttf").toExternalForm(),10);
        
        Font.loadFont(NewGame.class.getResource("/fonts/pixChicago.ttf").toExternalForm(),10);
        
        Font.loadFont(Ranking.class.getResource("/fonts/8-bit-pusab.ttf").toExternalForm(),10);
        Font.loadFont(Ranking.class.getResource("/fonts/pixChicago.ttf").toExternalForm(),10);
    }
    
    public static void switchToWelcomeScene(){
        
        stage.setScene(welcomeScene);
        welcomeScene.getStylesheets().add("/styles/welcome.css");
        
    }
    
    public static void setNewGameScene(){

        newGameScene = new Scene(new NewGame().getRoot(),800,600);
        gameEngine = new GameEngine();
        stage.setScene(newGameScene);
        NewGame.setInfo();
        newGameScene.getStylesheets().add("/styles/newGame.css");
        returnToWelcomeSceneOnKeyPress(newGameScene);
        
    }
    
    public static void setNewGameScene(int lives, int score, Clock clock){

        newGameScene = new Scene(new NewGame().getRoot(),800,600);
        gameEngine = new GameEngine(lives, score, clock);
        stage.setScene(newGameScene);
        NewGame.setInfo();
        newGameScene.getStylesheets().add("/styles/newGame.css");
        returnToWelcomeSceneOnKeyPress(newGameScene);
    }
    
    public static void switchToInstructionsScene(){
        
        stage.setScene(instructionsScene);
        instructionsScene.getStylesheets().add("/styles/instructions.css");
        returnToWelcomeSceneOnKeyPress(instructionsScene);
        
    }
    
    public static void switchToRankingScene(){
        
        rankingScene = new Scene(new Ranking().getRoot(),800,600);
        stage.setScene(rankingScene);
        rankingScene.getStylesheets().add("/styles/ranking.css");
        returnToWelcomeSceneOnKeyPress(rankingScene);
        
    }
    
    public static void switchToEndGameScene(boolean isWinner, int score){
        Scene endGameScene = new Scene(new GameOver(isWinner, score).getRoot(),800,600);
        stage.setScene(endGameScene);
        endGameScene.getStylesheets().add("/styles/gameover.css");
        
    }
    
    private static void returnToWelcomeSceneOnKeyPress(Scene scene){
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event){
                if(event.getCode().equals(KeyCode.BACK_SPACE)){
                    
                    if(newGameScene == null){
                        switchToWelcomeScene();
                    } else if(scene.getRoot().equals(newGameScene.getRoot())){
                        gameEngine.stopBalloons();
                        gameEngine.stopClock();
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
