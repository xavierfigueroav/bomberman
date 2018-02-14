/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import bomberman.GameBoard;
import java.util.ArrayList;

/**
 *
 * @author Xavier
 */
public class GameEngine implements Runnable{
    
    private int lives;
    private int score;
    private Clock clock;
    private ArrayList<Balloon> balloons;
    
    public GameEngine(){
        
        lives = 3;
        score = 0;
        clock = new Clock();
        
        setBalloons();
        
    }
    
    private void setBalloons(){
        
        this.balloons = new ArrayList();
        balloons.add(new Balloon(10,0));
        //balloons.add(new Balloon(6,6));
        //balloons.add(new Balloon(2,8));
        
    }
    
    public void destroyABalloon(int posY){
        
        if(posY == 0){
            balloons.get(0).destroyBalloon();
        } else if(posY == 6){
            balloons.get(1).destroyBalloon();
        } else {
            balloons.get(2).destroyBalloon();
        }
        
        score+=100;
        
    }
    
    public void decreaseALive(){
        
        if(lives!=0){
            lives--;
            System.out.println("Te quedan "+lives+" vidas.");
        }
        
    }
    
    
    
    public void stopBalloons(){
        if(balloons!=null){
            for(Balloon balloon: balloons){
                balloon.stopBalloon();
            }
        }
    }
    
    public void stopClock(){
        clock.stopClock();
    }

    @Override
    public void run() {
        
    }
    
}
