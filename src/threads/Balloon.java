/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import bomberman.Bomberman;
import bomberman.GameBoard;
import util.FileManager;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Contiene todos los elementos gráficos y lógicos que constituyen a un Balloon (enemigo) y permiten su autonomía.
 * @author Xavier Figueroa, Isaac Solís, Luis Mariño.
 */
public class Balloon{
    
    private final ImageView balloon;
    private int balloonPosX, balloonPosY, direction;
    private Timeline timeline;
    
    /**
     * Se encarga de crear una instancia de esta clase.
     * @param balloonPosX Posición horizontal inicial del balloon en el campo de juego.
     * @param balloonPosY Posición vertical (fija) del balloon en el campo de juego.
     */
    public Balloon(int balloonPosX, int balloonPosY){
        this.balloon = new ImageView(FileManager.getImage("src/images/balloon.gif"));
        this.balloonPosX = balloonPosX;
        this.balloonPosY = balloonPosY;
        this.direction = 1;
        
        setBalloon();
        releaseBalloon();
        
    }
    
    private void setBalloon(){
        GameBoard.setInGrid(this, this.balloonPosX, this.balloonPosY);
    }
    
    private void releaseBalloon(){
        Duration period = Duration.millis(250);

        KeyFrame keyFrame = new KeyFrame(period, new Motion());
        
        timeline = new Timeline();
        
        timeline.getKeyFrames().add(keyFrame);
        
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    private class Motion implements EventHandler<ActionEvent>{
        
        @Override
        public void handle(ActionEvent event) {
            
            if(GameBoard.isEmptyAt(balloonPosX+direction, balloonPosY)){
                
                GameBoard.removeFromTheGrid(balloon);
                GameBoard.setInGrid(Balloon.this, balloonPosX+direction, balloonPosY);
                balloonPosX += direction;
                
                
            } else if(GameBoard.hasManAt(balloonPosX+direction, balloonPosY)){
                
                Bomberman.gameEngine.stopBalloons();
                Bomberman.gameEngine.decreaseALive();
                
                
            } else {
                
                direction *= -1;
                GameBoard.removeFromTheGrid(balloon);
                GameBoard.setInGrid(Balloon.this, balloonPosX+direction, balloonPosY);
                balloonPosX += direction;
            }
        }
    }
    
    /**
     * Se encarga de detener el movimiento del balloon.
     */
    public void stopBalloon(){
        
        if (timeline!=null){
            timeline.stop();
        }
        
    }
    
    /**
     * Se encarga de eliminar al balloon del campo de juego y de la cuadrícula que lo simula.
     */
    public void destroyBalloon(){
        
        this.stopBalloon();
        GameBoard.removeFromTheGrid(this.balloon);
        
    }
    
    /**
     * Retorna el elemento gráfico del objeto Balloon, aquel que puede ser agregado al campo de juego.
     * @return Icono del balloon.
     */
    public ImageView getAsNode(){
        return this.balloon;
    }
}
