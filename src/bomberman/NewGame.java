/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import threads.Man;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 *
 * @author Xavier
 */
public class NewGame {
    
    private final Man man;
    private final BorderPane root;
    private final GameBoard gameBoard;
    private static HBox infoBar;
    
    
    public NewGame(){
        this.gameBoard = new GameBoard();
        this.man = new Man();
        
        GridPane grid = gameBoard.getGrid();
        infoBar = new HBox();
        infoBar.setId("infoBar");
        infoBar.setSpacing(150);
        infoBar.setMaxHeight(25); infoBar.setMinHeight(25); infoBar.setPrefHeight(25);
        infoBar.setAlignment(Pos.CENTER);
        
        HBox bottom = new HBox();
        bottom.setMaxHeight(25); bottom.setMinHeight(25); bottom.setPrefHeight(25);
        
        this.root = new BorderPane();
        this.root.setBackground(getBackground(new Image("/images/background.png")));
        this.root.setTop(infoBar);
        this.root.setCenter(grid);
        this.root.setBottom(bottom);
        
        this.man.handleKeyPress();
        this.man.getAsNode().setFocusTraversable(true);
        
    }
    
    public static void setInfo(){
        String lives = Integer.toString(Bomberman.gameEngine.getLives());
        String score = Bomberman.gameEngine.getScore();
        String time = Bomberman.gameEngine.getTime();
        
        Text livesText = new Text("LIVES: "+lives);livesText.setId("livesText");
        livesText.setId("info");
        Text scoreText = new Text("SCORE: "+score);
        scoreText.setId("info");
        Text timeText = new Text("TIME: "+time);
        timeText.setId("info");
        
        infoBar.getChildren().addAll(livesText, scoreText, timeText);
    }
    
    public static void refreshScore(){
        infoBar.getChildren().remove(1);
        String score = Bomberman.gameEngine.getScore();
        Text scoreText = new Text("SCORE: "+score);
        scoreText.setId("info");
        infoBar.getChildren().add(1, scoreText);
    }
    
    public static void refreshTime(){
        infoBar.getChildren().remove(2);
        String time = Bomberman.gameEngine.getTime();
        Text timeText = new Text("TIME: "+time);
        timeText.setId("info");
        infoBar.getChildren().add(2, timeText);
    }
    
    
    private Background getBackground(Image image){
        BackgroundImage backgroundImage = new BackgroundImage(image,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        return background;
    }
    
    public BorderPane getRoot(){
        return this.root;
    }
    
}