/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;
import java.util.Timer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

/**
 *
 * @author Xavier
 */
public class Clock{
    
    private Timeline timeline;
    private int seconds;
    private int minutes;
    private int hours;
    
    
    public Clock(){
        seconds = minutes = hours = 0;
        wind();
    }
    
    private void wind(){
        Duration period = Duration.millis(1000);

        KeyFrame keyFrame = new KeyFrame(period, new start());
        
        timeline = new Timeline();
        
        timeline.getKeyFrames().add(keyFrame);
        
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    private class start implements EventHandler<ActionEvent>{
        
        @Override
        public void handle(ActionEvent event) {
            
            if(seconds==59){
                seconds = 0;
                if(minutes==59){
                    minutes = 0;
                    hours++;
                } else{
                    minutes++;
                }
            } else {
                seconds++;
            }
                
            System.out.println(hours+" : "+minutes+" : "+seconds);
            //wind();
                
        }
        
    }
    
    public int getSeconds(){
        return this.seconds;
    }
    
    public int getMinutes(){
        return this.minutes;
    }
    
    public int getHours(){
        return this.hours;
    }
    
    public void stopClock(){
        this.timeline.stop();
    }
    
}
