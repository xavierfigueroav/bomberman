/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import bomberman.Bomberman;
import bomberman.GameBoard;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Xavier
 */
public class GameEngine implements Runnable{
    
    private int lives;
    private int score;
    private Clock clock;
    private Map<Integer,Balloon> balloons;
    
    public GameEngine(){
        
        lives = 3;
        score = 0;
        clock = new Clock();
        
        setBalloons();
        
    }
    
    public GameEngine(int lives, int score, Clock clock){
        
        this.lives = lives;
        this.score = score;
        this.clock = clock;
        
        setBalloons();
    }
    
    private void setBalloons(){
        
        this.balloons = new TreeMap();
        balloons.put(0,new Balloon(10,0));
        balloons.put(6,new Balloon(6,6));
        balloons.put(8,new Balloon(2,8));
        
    }
    
    public void destroyABalloon(int posY){
        
        balloons.get(posY).destroyBalloon();
        this.score+=100;
        
    }
    
    public void decreaseALive(){
        lives--;
        System.out.println("Te quedan "+lives+" vidas.");
        if(lives>0){
            Bomberman.setNewGameScene(this.lives, this.score, this.clock);
        } else {
            stopBalloons();
            stopClock();
            endGame();
        }
        
    }
    
    public void stopBalloons(){
        if(balloons!=null){
            for(Balloon balloon: balloons.values()){
                balloon.stopBalloon();
            }
        }
    }
    
    private void endGame(){
        
        Bomberman.switchToEndGameScene(false,this.score);
        
    }
    
    public void stopClock(){
        clock.stopClock();
    }

    @Override
    public void run() {
        
    }
    
}
