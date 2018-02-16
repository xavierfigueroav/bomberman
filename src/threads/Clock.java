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
 * Temporizador del juego.
 * @author Xavier Figueroa, Isaac Solís, Luis Mariño.
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
            NewGame.refreshTime(Clock.this.getTime());
                
        }
        
    }
    
    /**
     * Retorna los minutos transcurridos durante el juego.
     * @return Entero entre 0 y 59 que representa los minutos transcurridos en 1 hora.
     */
    public int getMinutes(){
        return this.minutes;
    }
    
    /**
     * Retorna el tiempo transcurrido durante el juego en formato hh:mm:ss.
     * @return Tiempo transcurrido durante el juego en formato hh:mm:ss.
     */
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
    
    /**
     * Se encarga de detener el temporizador.
     */
    public void stopClock(){
        this.timeline.stop();
    }
    
}
