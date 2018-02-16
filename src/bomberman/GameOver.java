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
 * Contiene todos los elementos gráficos que constituyen a la pantalla que aparece cuando el juego termina.
 * @author Xavier Figueroa, Isaac Solís, Luis Mariño.
 */
public class GameOver {
    
    private BorderPane root;
    private TextField nameField;
    /**
    * Se encarga de crear una instancia de la clase
    * @param isWinner Es verdadero cuando jugador ganó y es falso cuando lo perdió.
    * @param score Representa el puntaje del jugador al terminar el juego.
    */
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
        
        nameField.setOnKeyPressed((KeyEvent event) -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                String name = nameField.getText();
                
                ArrayList<String> data = new ArrayList();
                data.add(name+","+score);
                
                FileManager.writeFile("src/data/scores.csv", data);
                
                Bomberman.switchToWelcomeScene();
            }
        });
        
    }
    /**
    * Se encarga de retornar el objeto contenedor que será usado como Node Root para ser colocado en un objeto Scene.
    * @return Instancia de la clase BorderPane.
    */
    public BorderPane getRoot(){
        return this.root;
    }
    
}
