/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import util.FileManager;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Xavier
 */
public class GameOver {
    
    private BorderPane root;
    private TextField nameField;
    
    public GameOver(boolean isWinner, int score){
        
        Text message, order;
        if(isWinner){
            message = new Text("You won!");
            order = new Text("Brag your name");
        } else {
            message = new Text("You lost!");
            order = new Text("Write your name on the wall of shame");
        }
        message.setId("message");
        order.setId("order");
        
        VBox text = new VBox();
        text.getChildren().addAll(message,order);
        text.setAlignment(Pos.CENTER);
        
        nameField = new TextField();
        nameField.setId("nameField");
        nameField.setMaxSize(250,60);
        nameField.setMinSize(250,60);
        
        VBox form = new VBox();
        form.getChildren().addAll(text,nameField);
        form.setAlignment(Pos.CENTER);
        form.setSpacing(40);
        form.setMaxSize(500, 300);
        form.setMinSize(500, 300);
        
        this.root = new BorderPane();
        this.root.setCenter(form);
        nameField.setFocusTraversable(true);
        handleEnterPress(score);
    }
    
    private void handleEnterPress(int score){
        
        nameField.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event){
            
                if(event.getCode().equals(KeyCode.ENTER)){
                    String name = nameField.getText();
                    
                    ArrayList<String> data = new ArrayList();
                    data.add(name+","+score);
                    
                    FileManager.writeFile("src/data/scores.csv", data);
                    
                    Bomberman.switchToWelcomeScene();
                }
            
            }
        });
        
    }
    
    public BorderPane getRoot(){
        return this.root;
    }
    
}
