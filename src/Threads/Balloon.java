/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import bomberman.*;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author Xavier
 */
public class Balloon{
    
    private ImageView balloon;
    private int balloonPosX, balloonPosY, direction;
    private Timeline timeline;
    
    public Balloon(int balloonPosX, int balloonPosY){
        
        this.balloon = new ImageView(FileManager.getImage("src/images/balloon.png"));
        this.balloonPosX = balloonPosX;
        this.balloonPosY = balloonPosY;
        this.direction = 1;
        
        setBalloon();
        releaseBalloon();
        
    }
    
    private void setBalloon(){
        GameBoard.setInGrid(this, balloonPosX, balloonPosY);
    }
    
    private void releaseBalloon(){
        Duration period = Duration.millis(1000);

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
                System.out.println("Está vacío");
                GameBoard.removeFromTheGrid(balloon);
                GameBoard.setInGrid(Balloon.this, balloonPosX+direction, balloonPosY);
                balloonPosX += direction;
                
                
            } else if(GameBoard.hasManAt(balloonPosX+direction, balloonPosY)){
                System.out.println("Hay un man");
                Bomberman.game.decreaseALive();
                direction *= -1;
                
                
            } else {
                System.out.println("NO está vacío");
                direction *= -1;
                GameBoard.removeFromTheGrid(balloon);
                GameBoard.setInGrid(Balloon.this, balloonPosX+direction, balloonPosY);
                balloonPosX += direction;
                
                
            }
            
        }
        
    }
    
    public void stopBalloon(){
        
        if (timeline!=null){
            timeline.stop();
        }
        
    }
    
    public void destroyBalloon(){
        
        this.stopBalloon();
        GameBoard.removeFromTheGrid(this.balloon);
        
    }
    
    public ImageView getAsNode(){
        return this.balloon;
    }
    
}