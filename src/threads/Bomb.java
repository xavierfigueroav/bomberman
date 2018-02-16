/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import bomberman.Bomberman;
import util.FileManager;
import bomberman.GameBoard;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Contiene todos los elementos gráficos y lógicos que constituyen a una bomba y permiten su autonomía.
 * @author Xavier Figueroa, Isaac Solís, Luis Mariño.
 */
public class Bomb{
    
    private final ImageView bomb;
    private final int bombPosX, bombPosY;
    
    /**
     * Se encarga de crear una instancia de esta clase.
     * @param bombPosX Posición horizontal de la bomba en el campo de juego.
     * @param bombPosY Posición vertical de la bomba en el campo de juego.
     */
    public Bomb(int bombPosX, int bombPosY){
        
        this.bomb = new ImageView(FileManager.getImage("src/images/bomb.png"));
        this.bombPosX = bombPosX;
        this.bombPosY = bombPosY;
        setBomb();
        turnOnBomb();
    }
    
    private void setBomb(){
        GameBoard.setInGrid(this, bombPosX, bombPosY);
    }
    
    private void turnOnBomb(){
        Duration duration = Duration.millis(3000);

        KeyFrame keyFrame = new KeyFrame(duration, new Detonation());
        
        Timeline timeline = new Timeline();
        
        timeline.getKeyFrames().add(keyFrame);
        
        timeline.play();
    }
    
    private class Detonation implements EventHandler<ActionEvent>{
        
        @Override
        public void handle(ActionEvent event) {
            
            destroy();
            GameBoard.removeFromTheGrid(bomb);
            
        }
        
    }
    
    private void destroy(){
        
        destroyBlocks();
        killMan();
        destroyBalloons();
        
    }
    
    private void destroyBlocks(){
        
        if(GameBoard.hasATempBlockAt(bombPosX, bombPosY)){
            GameBoard.destroyBlockAt(bombPosX, bombPosY);
        }
        
        if(GameBoard.hasATempBlockAt(bombPosX, bombPosY+1)){
            GameBoard.destroyBlockAt(bombPosX, bombPosY+1);

        }

        if(GameBoard.hasATempBlockAt(bombPosX, bombPosY-1)){
            GameBoard.destroyBlockAt(bombPosX, bombPosY-1);
        }

        if(GameBoard.hasATempBlockAt(bombPosX+1, bombPosY)){
            GameBoard.destroyBlockAt(bombPosX+1, bombPosY);
        }

        if(GameBoard.hasATempBlockAt(bombPosX-1, bombPosY)){
            GameBoard.destroyBlockAt(bombPosX-1, bombPosY);
        }
        
    }
    
    private void killMan(){
        
        if(GameBoard.hasManAt(bombPosX, bombPosY)){
            Bomberman.gameEngine.stopBalloons();
            Bomberman.gameEngine.decreaseALive();
        }
        
        if(GameBoard.hasManAt(bombPosX, bombPosY+1)){
            Bomberman.gameEngine.stopBalloons();
            Bomberman.gameEngine.decreaseALive();
        }

        if(GameBoard.hasManAt(bombPosX, bombPosY-1)){
            Bomberman.gameEngine.stopBalloons();
            Bomberman.gameEngine.decreaseALive();
        }

        if(GameBoard.hasManAt(bombPosX+1, bombPosY)){
            Bomberman.gameEngine.stopBalloons();
            Bomberman.gameEngine.decreaseALive();
        }

        if(GameBoard.hasManAt(bombPosX-1, bombPosY)){
            Bomberman.gameEngine.stopBalloons();
            Bomberman.gameEngine.decreaseALive();
        }
        
        
    }
    
    private void destroyBalloons(){
        
        if(GameBoard.hasABalloonAt(bombPosX, bombPosY)){
            Bomberman.gameEngine.destroyABalloon(bombPosY);
        }
        
        if(GameBoard.hasABalloonAt(bombPosX, bombPosY+1)){
            Bomberman.gameEngine.destroyABalloon(bombPosY+1);
        }

        if(GameBoard.hasABalloonAt(bombPosX, bombPosY-1)){
            Bomberman.gameEngine.destroyABalloon(bombPosY-1);
        }

        if(GameBoard.hasABalloonAt(bombPosX+1, bombPosY)){
            Bomberman.gameEngine.destroyABalloon(bombPosY);
        }

        if(GameBoard.hasABalloonAt(bombPosX-1, bombPosY)){
            Bomberman.gameEngine.destroyABalloon(bombPosY);
        }
        
    }
    
    /**
     * Retorna el elemento gráfico del objeto Bomb, aquel que puede ser agregado al campo de juego.
     * @return Icono de la bomba.
     */
    public ImageView getAsNode(){
        return this.bomb;
    }
}
