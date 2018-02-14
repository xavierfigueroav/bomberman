/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author Xavier
 */
public class InstructionsScene {
    private VBox root;
    
    public InstructionsScene(){
        
        Font.loadFont(WelcomeScene.class.getResource("/fonts/8-bit-pusab.ttf").toExternalForm(),10);
        Font.loadFont(WelcomeScene.class.getResource("/fonts/pixChicago.ttf").toExternalForm(),10);
        
        TextFlow board = new TextFlow();
        board.setLineSpacing(5);
        board.setMinSize(600, 400);
        board.setMaxSize(600, 400);
        
        Text title = new Text("How to play?");
        title.setId("title");
        
        Text instructionsText = new Text();
        instructionsText.setId("instructionsText");
        instructionsText.setText("+ Use the direction pad to direct Bomberman in any one of the four cardinal directions.\n"
                + "+ Press the A key to drop a bomb on the tile that Bomberman is currently standing on.\n"
                + "+ Press the Backspace key to exit the game.\n"
                + "+ To win the game, you must kill all enemies and find the exit.\n"
                + "+ If an enemy touches you or an explosion hits you, you will die.\n"
                + "+ You start the game with three lives. If you die three times, you will lose the game.");
        
        board.getChildren().add(instructionsText);
        
        this.root = new VBox();
        this.root.setAlignment(Pos.CENTER);
        this.root.setSpacing(20);
        this.root.getChildren().addAll(title,board);
    }
    
    public VBox getRoot(){
        return this.root;
    }
    
}
