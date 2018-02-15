/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import util.FileManager;
import bomberman.Bomberman;
import bomberman.GameBoard;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Xavier
 */
public class Man{
    private final ImageView man;
    private int manPosX, manPosY;
    
    public Man(){
        this.man = new ImageView(FileManager.getImage("src/images/man.png"));
        manPosX = manPosY = 0;
        setMan();

    }
    
    private void setMan(){
        GameBoard.setInGrid(this, manPosX, manPosY);
    }
    
    
    public void handleKeyPress(){
        
        man.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event){
            
                manPosX = GridPane.getColumnIndex(man);
                manPosY = GridPane.getRowIndex(man);
                int newX, newY;

                switch(event.getCode()){
                    case LEFT:
                        newX = manPosX - 1;
                        if(GameBoard.isEmptyAt(newX, manPosY)){
                            GameBoard.removeFromTheGrid(man);
                            GameBoard.setInGrid(Man.this, newX, manPosY);
                            
                            if(GameBoard.hasTheGateAt(newX, manPosY) && Bomberman.gameEngine.theGateIsOpen()){
                                Bomberman.gameEngine.stopClock();
                                Bomberman.gameEngine.endGame(true);
                            }
                            
                        }
                        break;
                    case RIGHT:
                        newX = manPosX+1;
                        if(GameBoard.isEmptyAt(newX, manPosY)){
                            GameBoard.removeFromTheGrid(man);
                            GameBoard.setInGrid(Man.this, newX, manPosY);
                            
                            if(GameBoard.hasTheGateAt(newX, manPosY) && Bomberman.gameEngine.theGateIsOpen()){
                                Bomberman.gameEngine.stopClock();
                                Bomberman.gameEngine.endGame(true);
                            }
                            
                        }
                        break;
                    case UP:
                        newY = manPosY-1;
                        if(GameBoard.isEmptyAt(manPosX, newY)){
                            GameBoard.removeFromTheGrid(man);
                            GameBoard.setInGrid(Man.this, manPosX, newY);
                            
                            if(GameBoard.hasTheGateAt(manPosX, newY) && Bomberman.gameEngine.theGateIsOpen()){
                                Bomberman.gameEngine.stopClock();
                                Bomberman.gameEngine.endGame(true);
                            }
                            
                        }
                        break;
                    case DOWN:
                        newY = manPosY+1;
                        if(GameBoard.isEmptyAt(manPosX, newY)){
                            GameBoard.removeFromTheGrid(man);
                            GameBoard.setInGrid(Man.this, manPosX, newY);
                            
                            if(GameBoard.hasTheGateAt(manPosX, newY) && Bomberman.gameEngine.theGateIsOpen()){
                                Bomberman.gameEngine.stopClock();
                                Bomberman.gameEngine.endGame(true);
                            }
                            
                        }
                        break; 
                    case A:
                        new Bomb(manPosX, manPosY);
                        break;
                        
                }
            
            }
        });
        
    }
    
    // Getters
    public ImageView getAsNode(){
        return this.man;
    }
    
}
