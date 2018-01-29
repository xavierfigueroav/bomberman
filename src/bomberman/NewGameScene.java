/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

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
import javafx.scene.layout.Pane;
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
    private Pane root;
    private Grid grid;
    //public Text gameInfo;
    
    public NewGameScene(){
        
        Image backgroundImage = FileManager.getImage("src/images/background.png");
        Image manImage = FileManager.getImage("src/images/man.png");
        
        this.man = new Man(manImage);
        
        grid = new Grid(backgroundImage, "src/images/tempBlock.png");
        
        
        this.root = new Pane();
        this.root.setBackground(grid.getBackground());
        
        
        
        for(int numberBlock: grid.getTempBlocks()){
            ImageView block = new ImageView(grid.getTempBlockImage());
            double[] position = grid.convertToBlockPosition(numberBlock);
            
            block.setLayoutX(position[0]);
            block.setLayoutY(position[1]);
            
            this.root.getChildren().add(block);
        }
        
        this.root.getChildren().add(man.getAsNode());
        
        this.man.moveOnKeyPressAround(grid);
        this.man.getAsNode().setFocusTraversable(true);
    }
    
    
    
    
    public Pane getRoot(){
        return this.root;
    }
    
}
