/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author Xavier
 */
public class InstructionsScene {
    private GridPane root;
    
    public InstructionsScene(){
        
        Text testText = new Text("I Work!");
        
        this.root = new GridPane();
        this.root.add(testText, 0, 0);
    }
    
    public GridPane getRoot(){
        return this.root;
    }
    
}
