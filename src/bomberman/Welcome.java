/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Contiene todos los elementos gráficos que constituyen a la pantalla de bienvenida del juego, lo primero que ve el usuario.
 * @author Xavier Figueroa, Isaac Solís, Luis Mariño.
 */
public class Welcome{
    private final VBox root;
    private final Text gameName, groupText;
    private final Button newGameButton, instructionsButton, rankingButton;
    
    public Welcome(){
        
        gameName = new Text("Bomberman");
        gameName.setId("gameName");
        groupText = new Text("Group 7");
        groupText.setId("groupText");
        
        
        newGameButton = new Button("New game");
        instructionsButton = new Button("Instructions");
        rankingButton = new Button("Ranking");
        
        this.root = new VBox();
        this.root.getChildren().addAll(gameName,groupText,newGameButton,instructionsButton,rankingButton);
        this.root.setAlignment(Pos.CENTER);
        this.root.setMargin(groupText,new Insets(0,0,30,0));
        this.root.setMargin(newGameButton,new Insets(0,0,20,0));
        this.root.setMargin(instructionsButton,new Insets(0,0,20,0));
        
        newGameButton.setOnKeyPressed(new HandleButton());
        instructionsButton.setOnKeyPressed(new HandleButton());
        rankingButton.setOnKeyPressed(new HandleButton());
        
    }
    
    private class HandleButton implements EventHandler<KeyEvent>{
        @Override
        public void handle(KeyEvent event){
            if(event.getCode().equals(KeyCode.ENTER)){
                
                if(event.getSource() == newGameButton){
                    Bomberman.setNewGameScene();
                } else if (event.getSource() == instructionsButton){
                    Bomberman.switchToInstructionsScene();
                } else {
                    Bomberman.switchToRankingScene();
                }
                
            }
        }
    
    }
    
    /**
    * Se encarga de retornar el objeto contenedor que será usado como Node Root para ser colocado en un objeto Scene.
    * @return Instancia de la clase VBox.
    */
    public VBox getRoot(){
        return this.root;
    }
    
}
