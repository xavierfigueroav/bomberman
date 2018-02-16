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
 * Contiene todos los elementos lógicos que se encargan de controlar el estado y funcionamiento del juego y actuar en consecuencia.
 * @author Xavier Figueroa, Isaac Solís, Luis Mariño.
 */
public class GameEngine{
    
    private int lives;
    private int score;
    private Clock clock;
    private Map<Integer,Balloon> balloons;
    
    /**
     * Se encarga de crear una instancia de la clase por primera vez.
     */
    public GameEngine(){
        
        lives = 3;
        score = 0;
        clock = new Clock();
        
        setBalloons();
    }
    
    /**
     * Se encarga de crear una instancia de la clase cuando el juego ya ha empezado, luego de que Bomberman muere.
     * @param lives Representa las vidas que le quedan a Bomberman luego de morir.
     * @param score Representa el puntaje que tenía el jugador antes de que Bomberman muriera.
     * @param clock Representa el temporizador y su estado en el momento de la muerte de Bomberman.
     */
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
    
    /**
     * Se encarga de eliminar un Balloon con el código dado del campo de juego y de la lista de balloons.
     * Además, por reglas del juego, incrementa el puntaje del jugador y luego invoca al método 'refresfScore' de la clase 'NewGame'.
     * @param code Es la posición vertical del balloon en el campo de juego. Ésta es una característica distintiva de cada uno, por lo cual es tratada como un código.
     */
    public void destroyABalloon(int code){
        
        balloons.get(code).destroyBalloon();
        balloons.remove(code);
        this.score+=100;
        NewGame.refreshScore(this.getScore());
        
    }
    /**
     * Se encarga de disminuir en 1 las vidas de Bomberman.
     * Cuando Bomberman posee vidas, éste método invoca al método 'setNewGameScene' de la clase Bomberman.
     * Cuando Bomberman no tiene vidas, éste método de encarga de invocar al método 'endGame'.
     */
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
    
    /**
     * Se encarga de detener el movimiento de todos los balloons sobre el campo del juego.
     */
    public void stopBalloons(){
        if(balloons!=null){
            for(Balloon balloon: balloons.values()){
                balloon.stopBalloon();
            }
        }
    }
    
    /**
     * Se encarga de terminar el juego invocando al método 'switchToEndGameScene' de la clase Bomberman.
     * Además, si el jugador ganó el juego, éste método invoca al método 'setBonus', el cual incrementa el puntaje del jugador dependiendo de la duración que haya tenido juego.
     * @param isWinner Es verdadero si el jugardor ganó el juego y falso si lo perdió.
     */
    public void endGame(boolean isWinner){
        
        if(isWinner){
            setBonus();
        }
        
        Bomberman.switchToEndGameScene(isWinner,this.score);
        
    }
    
    private void setBonus(){
        
        if(clock.getMinutes()<2){
            this.score +=1000;
            NewGame.refreshScore(this.getScore());
        } else if(clock.getMinutes()<4){
            this.score +=500;
            NewGame.refreshScore(this.getScore());
        }
        
    }
    
    /**
     * Analiza si Bomberman puede salir del campo de juego (y el jugador ganar el juego).
     * @return Es verdadero si ya no quedan balloons por destruir y es falso en caso contrario.
     */
    public boolean theGateIsOpen(){
        return balloons.isEmpty();
    }
    
    /**
     * Invoca al método 'stopClock' de la clase Clock, el cual se encarga de detener el temporizador del juego.
     */
    public void stopClock(){
        clock.stopClock();
    }
    
    /**
     * Retorna las vidas que tiene Bomberman.
     * @return Entero entre 0 y 3 que representa las vidas que tiene Bomberman.
     */
    public int getLives(){
        return this.lives;
    }
    
    /**
     * Retorna el puntaje que tiene el jugador.
     * @return String que representa el puntaje del juegador, en formato 0000;
     */
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
    
    /**
     * Invoca al método 'getTime' de la clase Clock
     * @return Tiempo transcurrido durante el juego en formato hh:mm:ss.
     */
    public String getTime(){
        return clock.getTime();
    }
    
}