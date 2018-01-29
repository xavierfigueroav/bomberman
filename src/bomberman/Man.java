/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Xavier
 */
public class Man {
    private ImageView token;
    final private int MOVE = 50; 
    
    // Constructors
    public Man(){}
    
    public Man(Image imageToken){
        this.token = new ImageView(imageToken);
    }
    
    public void moveOnKeyPressAround(Grid grid){
        
        token.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event){
            
                double actualX = token.getLayoutX();
                double actualY = token.getLayoutY();
                double newX, newY;

                switch(event.getCode()){
                    case LEFT:
                        newX = actualX-MOVE;
                        if(actualX!=0 && canMoveAround(grid, newX, actualY)){
                            token.setLayoutX(newX);
                        }
                        break;
                    case RIGHT:
                        newX = actualX+MOVE;
                        if(actualX!=700 && canMoveAround(grid, newX, actualY)){
                            token.setLayoutX(newX);
                        }
                        break;
                    case UP:
                        newY = actualY-MOVE;
                        if(actualY!=0 && canMoveAround(grid, actualX, newY)){
                            token.setLayoutY(newY);
                        }
                        break;
                    case DOWN:
                        newY = actualY+MOVE;
                        if(actualY!=500 && canMoveAround(grid, actualX, newY)){
                            token.setLayoutY(newY);
                        }
                        break;
                }
            
            }
        });
        
    }
    
    private boolean canMoveAround(Grid grid, double x, double y){
        return !grid.hasAFixedBlockIn(x, y) && !grid.hasATempBlockIn(x, y);
    }
    
    // Getters
    public ImageView getAsNode(){
        return this.token;
    }
    
    // Setters
    public void setToken(Image imageToken){
        this.token = new ImageView(imageToken);
    }
    
}
