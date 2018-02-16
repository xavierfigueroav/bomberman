/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import threads.GameEngine;
import threads.Clock;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Es la clase principal del proyecto y desde la cual empieza la ejecución del programa.
 * @author Xavier Figueroa, Isaac Solís, Luis Mariño.
 */

public class Bomberman extends Application {
    
    private static Stage stage;
    /**
     * Objeto que contiene los métodos y campos necesarios para realizar tareas autónomas en el juego.
     */
    public static GameEngine gameEngine;
    private static Scene welcomeScene, newGameScene, instructionsScene, rankingScene;
    
    /**
    * Crea la ventana del programa y establece su configuración inicial.
    * @param primaryStage Parámetro que representa la ventana del programa.
    */
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setResizable(false);
        stage.setTitle("Bomberman");
        
        welcomeScene = new Scene(new Welcome().getRoot(),800,600);
        instructionsScene = new Scene(new Instructions().getRoot(),800,600);
        
        loadFonts();
        
        switchToWelcomeScene();
        
        stage.show();
    }
    
    private static void loadFonts(){
        Font.loadFont(Welcome.class.getResource("/fonts/8-bit-pusab.ttf").toExternalForm(),10);
        
        Font.loadFont(GameOver.class.getResource("/fonts/8-bit-pusab.ttf").toExternalForm(),10);
        Font.loadFont(GameOver.class.getResource("/fonts/pixChicago.ttf").toExternalForm(),10);
        
        Font.loadFont(Instructions.class.getResource("/fonts/8-bit-pusab.ttf").toExternalForm(),10);
        Font.loadFont(Instructions.class.getResource("/fonts/pixChicago.ttf").toExternalForm(),10);
        
        Font.loadFont(NewGame.class.getResource("/fonts/pixChicago.ttf").toExternalForm(),10);
        
        Font.loadFont(Ranking.class.getResource("/fonts/8-bit-pusab.ttf").toExternalForm(),10);
        Font.loadFont(Ranking.class.getResource("/fonts/pixChicago.ttf").toExternalForm(),10);
    }
    
    /**
    * Se encarga de colocar en la ventana (Stage) el Scene que representa la pantalla de bienvenida del programa.
    * Además, carga el archivo CSS que configura los estilos del Scene mencionado.
    */
    public static void switchToWelcomeScene(){
        
        stage.setScene(welcomeScene);
        welcomeScene.getStylesheets().add("/styles/welcome.css");
        
    }
    
    /**
    * Se encarga de colocar en la ventana (Stage), por primera vez, el Scene que representa la pantalla de juego.
    * Inicializa el campo 'gameEngine'.
    * Carga el archivo CSS que configura los estilos del Scene mencionado.
    * Invoca al método listener que se encarga de regresar al jugador a la pantalla de bienvenida cuando la tecla backspace es presionada.
    */
    public static void setNewGameScene(){

        newGameScene = new Scene(new NewGame().getRoot(),800,600);
        gameEngine = new GameEngine();
        stage.setScene(newGameScene);
        NewGame.setInfo();
        newGameScene.getStylesheets().add("/styles/newGame.css");
        returnToWelcomeSceneOnKeyPress(newGameScene);
        
    }
    
    /**
    * Se encarga de colocar en la ventana (Stage) el Scene que representa la pantalla de juego, cuando Bomberman muere.
    * Inicializa el campo 'gameEngine' con parámetros 'lives', 'score' y 'clock'.
    * Carga el archivo CSS que configura los estilos del Scene mencionado.
    * Invoca al método listener que se encarga de regresar al jugador a la pantalla de bienvenida cuando la tecla backspace es presionada.
    * @param lives Representa las vidas que le quedan a Bomberman luego de morir.
    * @param score Representa el puntaje que tenía el jugador antes de que Bomberman muriera.
    * @param clock Representa el temporizador del juego y su estado en el momento de la muerte de Bomberman.
    */
    public static void setNewGameScene(int lives, int score, Clock clock){

        newGameScene = new Scene(new NewGame().getRoot(),800,600);
        gameEngine = new GameEngine(lives, score, clock);
        stage.setScene(newGameScene);
        NewGame.setInfo();
        newGameScene.getStylesheets().add("/styles/newGame.css");
        returnToWelcomeSceneOnKeyPress(newGameScene);
    }
    
    /**
    * Se encarga de colocar en la ventana (Stage), el Scene que representa la pantalla de instrucciones del juego.
    * Carga el archivo CSS que configura los estilos del Scene mencionado.
    * Invoca al método listener que se encarga de regresar al jugador a la pantalla de bienvenida cuando la tecla backspace es presionada.
    */
    public static void switchToInstructionsScene(){
        
        stage.setScene(instructionsScene);
        instructionsScene.getStylesheets().add("/styles/instructions.css");
        returnToWelcomeSceneOnKeyPress(instructionsScene);
        
    }
    
    /**
    * Se encarga de colocar en la ventana (Stage), el Scene que representa la pantalla de ranking de jugadores.
    * Carga el archivo CSS que configura los estilos del Scene mencionado.
    * Invoca al método listener que se encarga de regresar al jugador a la pantalla de bienvenida cuando la tecla backspace es presionada.
    */
    public static void switchToRankingScene(){
        
        rankingScene = new Scene(new Ranking().getRoot(),800,600);
        stage.setScene(rankingScene);
        rankingScene.getStylesheets().add("/styles/ranking.css");
        returnToWelcomeSceneOnKeyPress(rankingScene);
        
    }
    
    /**
    * Se encarga de colocar en la ventana (Stage), el Scene que representa la pantalla de juego terminado.
    * Carga el archivo CSS que configura los estilos del Scene mencionado.
    * Invoca al método listener que se encarga de regresar al jugador a la pantalla de bienvenida cuando la tecla backspace es presionada.
    * @param isWinner Es verdadero cuando jugador ganó el juego y es falso cuando lo perdió.
    * @param score Representa el puntaje del jugador al terminar el juego.
    */
    public static void switchToEndGameScene(boolean isWinner, int score){
        Scene endGameScene = new Scene(new GameOver(isWinner, score).getRoot(),800,600);
        stage.setScene(endGameScene);
        endGameScene.getStylesheets().add("/styles/gameover.css");
        
    }
    
    private static void returnToWelcomeSceneOnKeyPress(Scene scene){
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event){
                if(event.getCode().equals(KeyCode.BACK_SPACE)){
                    
                    if(newGameScene == null){
                        switchToWelcomeScene();
                    } else if(scene.getRoot().equals(newGameScene.getRoot())){
                        gameEngine.stopBalloons();
                        gameEngine.stopClock();
                        switchToWelcomeScene();
                    } else{
                        switchToWelcomeScene();
                    }
                    
                }
            }
        });
    }
    

    /**
     * Es el primer método que se ejecuta cuando se corre el programa.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}