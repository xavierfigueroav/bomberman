/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import bomberman.Bomberman;
import bomberman.NewGame;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Xavier
 */
public class GameEngine{
    
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
    
    public void destroyABalloon(int code){
        
        balloons.get(code).destroyBalloon();
        balloons.remove(code);
        this.score+=100;
        NewGame.refreshScore();
        
    }
    
    public void decreaseALive(){
        lives--;
        if(lives>0){
            Bomberman.setNewGameScene(this.lives, this.score, this.clock);
        } else {
            stopBalloons();
            stopClock();
            endGame(false);
        }
        
    }
    
    public void stopBalloons(){
        if(balloons!=null){
            for(Balloon balloon: balloons.values()){
                balloon.stopBalloon();
            }
        }
    }
    
    public void endGame(boolean isWinner){
        
        if(isWinner){
            setBonus();
        }
        
        Bomberman.switchToEndGameScene(isWinner,this.score);
        
    }
    
    private void setBonus(){
        
        if(clock.getMinutes()<2){
            this.score +=1000;
            NewGame.refreshScore();
        } else if(clock.getMinutes()<4){
            this.score +=500;
            NewGame.refreshScore();
        }
        
    }
    
    public boolean theGateIsOpen(){
        return balloons.isEmpty();
    }
    
    public void stopClock(){
        clock.stopClock();
    }
    
    public int getLives(){
        return this.lives;
    }
    
    public String getScore(){
        String sc = Integer.toString(this.score);
        
        if(this.score<10){
            sc = "000"+this.score;
        } else if(this.score<100){
            sc = "00"+this.score;
        }else if(this.score<1000){
            sc = "0"+this.score;
        }
        
        return sc;
    }
    
    public String getTime(){
        return clock.getTime();
    }
    
}
