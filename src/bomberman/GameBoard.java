/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import threads.Balloon;
import threads.Man;
import threads.Bomb;
import util.FileManager;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * Contiene los atributos y métodos relacionados con la configuración de los elementos en el campo de juego.
 * @author Xavier Figueroa, Isaac Solís, Luis Mariño
 */
public class GameBoard {
    /**
    * Ancho de la cuadrícula, en cantidad de columnas.
    */
    public static final int GRID_WIDTH = 15;
    /**
    * Alto de la cuadrícula, en cantidad de filas.
    */
    public static final int GRID_HEIGHT = 11;
    /**
    * Representa un bloque fijo en la cuadrícula que simula al campo de juego (cuadrícula espejo).
    */
    public static final int PSEUDO_FIXED_BLOCK = 1;
    /**
    * Representa un bloque temporal (destruible por una bomba) en la cuadrícula que simula al campo de juego (cuadrícula espejo).
    */
    public static final int PSEUDO_TEMP_BLOCK = 2;
    /**
    * Representa al vacío en la cuadrícula que simula al campo de juego (cuadrícula espejo).
    */
    public static final int PSEUDO_EMPTY = 0;
    /**
    * Representa una bomba en la cuadrícula que simula al campo de juego (cuadrícula espejo).
    */
    public static final int PSEUDO_BOMB = -1;
    /**
    * Representa un balloon (enemigo) en la cuadrícula que simula al campo de juego (cuadrícula espejo).
    */
    public static final int PSEUDO_BALLOON = -2;
    /**
    * Representa a Bomberman en la cuadrícula que simula al campo de juego (cuadrícula espejo).
    */
    public static final int PSEUDO_MAN = 3;
    
    private static GridPane grid;
    private static int[][] mirrorGrid;
    private static ImageView[][] mirrorTempBlocks;
    private static int[] gatePosition;
    
    public GameBoard(){
        
        mirrorGrid = new int[GRID_HEIGHT][GRID_WIDTH];
        mirrorTempBlocks = new ImageView[GRID_HEIGHT][GRID_WIDTH];
        createGrid();
        gatePosition = new int[2]; gatePosition[0] = 0; gatePosition[1] = 11;
    }
    
    private void createGrid(){
        grid = new GridPane();
        
        for (int i = 0; i < GRID_WIDTH; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / GRID_WIDTH);
            grid.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < GRID_HEIGHT; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / GRID_HEIGHT);
            grid.getRowConstraints().add(rowConst);         
        }
        
        grid.setMinSize(750, 550);
        grid.setMaxSize(750, 550);
        
        setTempBlocks();
        setFixedBlocks();
    }
    
    private void setTempBlocks(){
        
        ArrayList<String> tempBlocksData = FileManager.readFile("src/data/tempBlocksLevel1.csv");
        
        for(String line: tempBlocksData){
            
            String[] position = line.split(",");
            int posY = Integer.parseInt(position[0]);
            int posX = Integer.parseInt(position[1]);
            
            ImageView block = new ImageView(FileManager.getImage("src/images/tempBlock.png"));
            grid.add(block, posX, posY);
            mirrorTempBlocks[posY][posX] = block;
            mirrorGrid[posY][posX] = PSEUDO_TEMP_BLOCK;
            
        }
    }
    
    private void setFixedBlocks(){
        
        for(int i = 0; i<GRID_HEIGHT; i++){
            for(int j = 0;j<GRID_WIDTH;j++){
                if((i%2!=0)&&(j%2!=0)){
                    mirrorGrid[i][j] = PSEUDO_FIXED_BLOCK;
                }
            }
        }
    }
    
    
    /**
    * Se encarga de colocar un elemento en el campo de juego y en la cuadrícula que lo simula.
    * @param object Elemento a ser agregado en el campo de juego.
    * @param posX Posición horizontal donde será agregado el elemento.
    * @param posY Posición vertical donde será agregado el elemento.
    */
    public static void setInGrid(Object object, int posX, int posY){
        
        if(object instanceof Man){
            
            Man man = (Man)object;
            grid.add(man.getAsNode(), posX, posY);
            mirrorGrid[posY][posX] = PSEUDO_MAN;
            
        } else if (object instanceof Balloon){
            
            Balloon balloon = (Balloon)object;
            grid.add(balloon.getAsNode(), posX, posY);
            mirrorGrid[posY][posX] = PSEUDO_BALLOON;
            
        } else if (object instanceof Bomb){
            
            Bomb bomb = (Bomb)object;
            grid.add(bomb.getAsNode(), posX, posY);
            mirrorGrid[posY][posX] = PSEUDO_BOMB;
            
        }
        
    }
    
    /**
    * Se encarga de eliminar un elemento del campo de juego y de la cuadrícula que lo simula.
    * @param node Elemento a ser eliminado del campo de juego.
    */
    public static void removeFromTheGrid(Node node){
        
        int posX = GridPane.getColumnIndex(node);
        int posY = GridPane.getRowIndex(node);
        
        grid.getChildren().remove(node);
        mirrorGrid[posY][posX] = PSEUDO_EMPTY;
        
        
    }
    
    /**
    * Se encarga de eliminar un bloque del campo de juego y en la cuadrícula que lo simula.
    * @param posX Posición horizontal en donde se encuentra el bloque a ser eliminado.
    * @param posY Posición vertical en donde se encuentra el bloque a ser eliminado.
    */
    public static void destroyBlockAt(int posX, int posY){
        
        ImageView block = mirrorTempBlocks[posY][posX];
        removeFromTheGrid(block);
        
    }
    
    private static boolean isInside(int x, int y){
        
        return (x>=0 && x<GRID_WIDTH && y>=0  && y<GRID_HEIGHT);
    }
    
    /**
    * Analiza si una posición en el campo de juego está vacía.
    * @param x Posición horizontal a analizar.
    * @param y Posición vertical analizar.
    * @return Retorna verdadero si no hay nada en dicha posición y retorna falso si no es así.
    */
    public static boolean isEmptyAt(int x, int y){
        if(isInside(x,y)){
            return (mirrorGrid[y][x] == PSEUDO_EMPTY);
        }
        return false;
    }
    
    /**
    * Analiza si existe un bloque temporal en una posición dada.
    * @param x Posición horizontal a analizar.
    * @param y Posición vertical analizar.
    * @return Retorna verdadero si hay un bloque temporal en dicha posición y retorna falso si no es así.
    */
    public static boolean hasATempBlockAt(int x, int y){
        
        if(isInside(x,y)){
            return (mirrorGrid[y][x] == PSEUDO_TEMP_BLOCK);
        }
        return false;
        
    }
    
    /**
    * Analiza si la puerta de salida se encuentra en una posición dada.
    * @param x Posición horizontal a analizar.
    * @param y Posición vertical analizar.
    * @return Retorna verdadero si la puerta se encuentra en dicha posición y retorna falso si no es así.
    */
    public static boolean hasTheGateAt(int x, int y){
        return ((x == gatePosition[1]) && (y == gatePosition[0]));
    }
    
    /**
    * Analiza si Bomberman está en una posición dada.
    * @param x Posición horizontal a analizar.
    * @param y Posición vertical analizar.
    * @return Retorna verdadero si Bomberman está en dicha posición y retorna falso si no es así.
    */
    public static boolean hasManAt(int x, int y){
        if(isInside(x,y)){
            return (mirrorGrid[y][x] == PSEUDO_MAN);
        }
        return false;
    }
    
    /**
    * Analiza si existe un balloon (enemigo) en una posición dada.
    * @param x Posición horizontal a analizar.
    * @param y Posición vertical analizar.
    * @return Retorna verdadero si un balloon se encuentra en dicha posición y retorna falso si no es así.
    */
    public static boolean hasABalloonAt(int x, int y){
        
        if(isInside(x,y)){
            return (mirrorGrid[y][x] == PSEUDO_BALLOON);
        }
        return false;
        
    }
    
    /**
    * Se encarga de retornar el campo grid.
    * @return Objeto de la clase GridPane.
    */
    public GridPane getGrid(){
        return grid;
    }
    
}
