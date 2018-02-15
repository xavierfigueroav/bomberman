/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;
import bomberman.NewGame;
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
            NewGame.refreshTime();
                
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
    
    public String getTime(){
        String sec = Integer.toString(this.seconds);
        String min = Integer.toString(this.minutes);
        String h = Integer.toString(this.hours);
        
        if(this.seconds<10){
            sec = "0"+this.seconds;
        }
        if(this.minutes<10){
            min = "0"+this.minutes;
        }
        if(this.hours<10){
            h = "0"+this.hours;
        }
        
        return h+":"+min+":"+sec;
    }
    
    public void stopClock(){
        this.timeline.stop();
    }
    
}
