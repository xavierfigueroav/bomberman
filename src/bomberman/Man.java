/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 *
 * @author Xavier
 */
public class Man {
    private ImageView man, bomb;
    private static int vidas;
    
    // Constructors
    public Man(){}
    
    public Man(Image imageToken){
        this.vidas = 3;
        this.man = new ImageView(imageToken);
        this.bomb = new ImageView(FileManager.getImage("src/images/bomb.png"));
    }
    
    
    public void handleKeyPressOn(GameBoard gameBoard){
        
        man.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event){
            
                int actualX = GridPane.getColumnIndex(man);
                int actualY = GridPane.getRowIndex(man);
                int newX, newY;
                GridPane grid = gameBoard.getGrid();

                switch(event.getCode()){
                    case LEFT:
                        newX = actualX - 1;
                        if(actualX!=0 && gameBoard.noBlockAt(newX, actualY)){
                            grid.getChildren().remove(man);
                            grid.add(man, newX, actualY);
                        }
                        break;
                    case RIGHT:
                        newX = actualX+1;
                        if(actualX!=14 && gameBoard.noBlockAt(newX, actualY)){
                            grid.getChildren().remove(man);
                            grid.add(man, newX, actualY);
                        }
                        break;
                    case UP:
                        newY = actualY-1;
                        if(actualY!=0 && gameBoard.noBlockAt(actualX, newY)){
                            grid.getChildren().remove(man);
                            grid.add(man, actualX, newY);
                        }
                        break;
                    case DOWN:
                        newY = actualY+1;
                        if(actualY!=10 && gameBoard.noBlockAt(actualX, newY)){
                            grid.getChildren().remove(man);
                            grid.add(man, actualX, newY);
                        }
                        break; 
                    case A:
                        grid.add(bomb, actualX, actualY);
                        gameBoard.getPseudoGrid()[actualY][actualX] = gameBoard.PSEUDO_BOMB;
                        turnOn(gameBoard);
                }
            
            }
        });
        
    }
    
    private void turnOn(GameBoard gameBoard){
        
        Duration duration = Duration.millis(3000);

        KeyFrame keyFrame = new KeyFrame(duration, new detonate(gameBoard));
        
        Timeline timeline = new Timeline();
        
        timeline.getKeyFrames().add(keyFrame);
        
        timeline.play();
    }   
    
    private class detonate implements EventHandler<ActionEvent>{
        
        private GameBoard gameBoard;
        private int bombPosX, bombPosY;
        public detonate(GameBoard gameBoard){
            this.gameBoard = gameBoard;
            this.bombPosY = GridPane.getRowIndex(bomb);
            this.bombPosX = GridPane.getColumnIndex(bomb);
        }
        public void handle(ActionEvent event) {
            
            gameBoard.getGrid().getChildren().remove(bomb);
           
            gameBoard.getPseudoGrid()[bombPosY][bombPosX] = 0;
            
            destroyBlocks();
        }
        
        private void destroyBlocks(){
            
            for(int f = -1;f<2;f++){
                
                for(int c = -1; c<2;c++){
                    try{
                        if(gameBoard.getPseudoGrid()[bombPosY+f][bombPosX+c] == gameBoard.PSEUDO_TEMP_BLOCK){

                            gameBoard.getPseudoGrid()[bombPosY+f][bombPosX+c] = 0;

                        }
                    } catch(Exception e){
                        System.out.println("Fuera del borde");
                    }
                    
                }
                
            }
            
        }
        
    }
    
    
    
    // Getters
    public ImageView get(){
        return this.man;
    }
    
    // Setters
    public void setToken(Image imageToken){
        this.man = new ImageView(imageToken);
    }
    
}
