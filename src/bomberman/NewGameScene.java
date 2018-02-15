/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import Threads.Balloon;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Xavier
 */
public class NewGameScene {
    
    private Man man;
    private BorderPane root;
    private GameBoard gameBoard;
    
    
    public NewGameScene(){
        this.gameBoard = new GameBoard();
        this.man = new Man();
        
        
        GridPane grid = gameBoard.getGrid();
        
        this.root = new BorderPane();
        this.root.setBackground(getBackground(new Image("/images/background.png")));
        this.root.setCenter(grid);
        
        this.man.handleKeyPress();
        this.man.getAsNode().setFocusTraversable(true);
        
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