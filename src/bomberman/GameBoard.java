/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import Threads.Balloon;
import Threads.Bomb;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author Xavier
 */
public class GameBoard {
    public static final int GRID_WIDTH = 15;
    public static final int GRID_HEIGHT = 11;
    public static final int PSEUDO_FIXED_BLOCK = 1;
    public static final int PSEUDO_TEMP_BLOCK = 2;
    public static final int PSEUDO_EMPTY = 0;
    public static final int PSEUDO_BOMB = -1;
    public static final int PSEUDO_BALLOON = -2;
    public static final int PSEUDO_MAN = 3;
    
    private static GridPane grid;
    private static int[][] mirrorGrid;
    private static ImageView[][] mirrorTempBlocks;
    
    public GameBoard(){
        
        mirrorGrid = new int[GRID_HEIGHT][GRID_WIDTH];
        mirrorTempBlocks = new ImageView[GRID_HEIGHT][GRID_WIDTH];
        createGrid();
        
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
        grid.setGridLinesVisible(true);
        
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
            this.grid.add(block, posX, posY);
            this.mirrorTempBlocks[posY][posX] = block;
            this.mirrorGrid[posY][posX] = PSEUDO_TEMP_BLOCK;
            
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
    
    public static void setInGrid(Object o, int posX, int posY){
        
        if(o instanceof Man){
            
            Man man = (Man)o;
            grid.add(man.getAsNode(), posX, posY);
            mirrorGrid[posY][posX] = PSEUDO_MAN;
            
        } else if (o instanceof Balloon){
            
            Balloon balloon = (Balloon)o;
            grid.add(balloon.getAsNode(), posX, posY);
            mirrorGrid[posY][posX] = PSEUDO_BALLOON;
            
        } else if (o instanceof Bomb){
            
            Bomb bomb = (Bomb)o;
            grid.add(bomb.getAsNode(), posX, posY);
            mirrorGrid[posY][posX] = PSEUDO_BOMB;
            
        }
        
    }
    
    public static void removeFromTheGrid(Node node){
        
        int posX = GridPane.getColumnIndex(node);
        int posY = GridPane.getRowIndex(node);
        
        grid.getChildren().remove(node);
        mirrorGrid[posY][posX] = PSEUDO_EMPTY;
        
        
    }
    
    public static void destroyBlockAt(int posX, int posY){
        
        ImageView block = mirrorTempBlocks[posY][posX];
        removeFromTheGrid(block);
        
    }
    
    private static boolean isInside(int x, int y){
        
        return (x>=0 && x<GRID_WIDTH && y>=0  && y<GRID_HEIGHT);
    }
    
    public static boolean isEmptyAt(int x, int y){
        if(isInside(x,y)){
            return (mirrorGrid[y][x] == PSEUDO_EMPTY);
        }
        return false;
    }
    
    public static boolean hasATempBlockAt(int x, int y){
        
        if(isInside(x,y)){
            return (mirrorGrid[y][x] == PSEUDO_TEMP_BLOCK);
        }
        return false;
        
    }
    
    public static boolean hasManAt(int x, int y){
        if(isInside(x,y)){
            return (mirrorGrid[y][x] == PSEUDO_MAN);
        }
        return false;
    }
    
    public static boolean hasABalloonAt(int x, int y){
        
        if(isInside(x,y)){
            return (mirrorGrid[y][x] == PSEUDO_BALLOON);
        }
        return false;
        
    }
    
    public GridPane getGrid(){
        return grid;
    }
    
}
