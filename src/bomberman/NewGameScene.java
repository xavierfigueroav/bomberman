/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import Threads.BombThread;
import Threads.ManThread;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author Xavier
 */
public class NewGameScene {
    
    public Background background;
    private Man man;
    private BorderPane root;
    private GameBoard gameBoard;
    
    public NewGameScene(){
        
        Image manImage = FileManager.getImage("src/images/man.png");
        
        this.man = new Man(manImage);
        
        this.gameBoard = new GameBoard("src/images/tempBlock.png");
        GridPane grid = gameBoard.getGrid();
        
        this.root = new BorderPane();
        this.root.setBackground(getBackground(new Image("/images/background.png")));
        this.root.setCenter(grid);
        
        grid.add(man.get(),0,0);
        
        this.man.handleKeyPressOn(gameBoard);
        this.man.get().setFocusTraversable(true);
        
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
