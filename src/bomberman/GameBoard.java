/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import Threads.Balloon;
import Threads.Bomb;
import javafx.scene.Node;
import javafx.scene.image.Image;
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
    public static final int PSEUDO_BALL = -2;
    public static final int PSEUDO_MAN = 3;
    
    private static GridPane grid;
    private static int[][] pseudoGrid;
    
    private final Image tempBlockImage;
    
    public GameBoard(String pathTempBlockImage){
        
        start();
        
        pseudoGrid = new int[GRID_HEIGHT][GRID_WIDTH];
        
        grid = new GridPane();
        grid.setId("grid");
        
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
        
        this.grid.setMinSize(750, 550);
        this.grid.setMaxSize(750, 550);
        this.grid.setGridLinesVisible(true);
        
        this.setFixedBlocks();
        this.setTempBlocks();
        this.tempBlockImage = FileManager.getImage(pathTempBlockImage);
        
        
        
        for(int yBlock = 0; yBlock<GRID_HEIGHT;yBlock++){
            for(int xBlock = 0; xBlock<GRID_WIDTH;xBlock++){
                
                if(this.pseudoGrid[yBlock][xBlock]==2){
                    
                    ImageView block = new ImageView(tempBlockImage);
                    grid.add(block, xBlock, yBlock);
                    
                }
                
            }
        }
    }
    
    private void start(){
        if(grid!=null){
            grid.getChildren().clear();
        }
    }
    
    
    public static void setInGrid(Object o, int posX, int posY){
        
        if(o instanceof Man){
            
            Man man = (Man)o;
            grid.add(man.getAsNode(), posX, posY);
            pseudoGrid[posY][posX] = PSEUDO_MAN;
            
        } else if (o instanceof Balloon){
            
            Balloon balloon = (Balloon)o;
            grid.add(balloon.getAsNode(), posX, posY);
            pseudoGrid[posY][posX] = PSEUDO_BALL;
            
        } else if (o instanceof Bomb){
            
            Bomb bomb = (Bomb)o;
            grid.add(bomb.getAsNode(), posX, posY);
            pseudoGrid[posY][posX] = PSEUDO_BOMB;
            
        }
        
    }
    
    public static void setInPseudoGrid(int element, int posX, int posY){
        
        pseudoGrid[posY][posX] = element;
        
    }
    
    public GridPane getGrid(){
        return grid;
    }
    
    private void setFixedBlocks(){
        
        for(int i = 0; i<GRID_HEIGHT; i++){
            for(int j = 0;j<GRID_WIDTH;j++){
                if((i%2!=0)&&(j%2!=0)){
                    pseudoGrid[i][j] = PSEUDO_FIXED_BLOCK;
                }
            }
        }
        
    }
    
    public static void removeFromTheGrid(Node node){
        
        int posX = GridPane.getColumnIndex(node);
        int posY = GridPane.getRowIndex(node);
        
        grid.getChildren().remove(node);
        pseudoGrid[posY][posX] = PSEUDO_EMPTY;
        
        
    }
    
    
    
    public int[][] getPseudoGrid(){
        return pseudoGrid;
    }
    
    public Image getTempBlockImage(){
        return this.tempBlockImage;
    }
    
    
    public static boolean hasATempBlockIn(int x, int y){
        
        if(isInside(x,y)){
            return (pseudoGrid[y][x] == PSEUDO_TEMP_BLOCK);
        }
        return false;
        
    }
    
    public static boolean hasAFixedBlockAt(int x, int y){
        if(isInside(x,y)){
            return (pseudoGrid[y][x] == PSEUDO_FIXED_BLOCK);
        }
        return false;
        
    }
    
    public static boolean hasManAt(int x, int y){
        if(isInside(x,y)){
            return (pseudoGrid[y][x] == PSEUDO_MAN);
        }
        return false;
    }
    
    public static boolean hasABalloonAt(int x, int y){
        
        if(isInside(x,y)){
            return (pseudoGrid[y][x] == PSEUDO_BALL);
        }
        return false;
        
    }
    
    public static boolean isEmptyAt(int x, int y){
        if(isInside(x,y)){
            return (pseudoGrid[y][x] == PSEUDO_EMPTY);
        }
        return false;
    }
    
    private static boolean isInside(int x, int y){
        
        return (x>=0 && x<=GRID_WIDTH-1 && y>=0  && y<=GRID_HEIGHT-1);
    }
    
    private void setTempBlocks(){
        
        pseudoGrid[0][11] = PSEUDO_TEMP_BLOCK;pseudoGrid[1][4] = PSEUDO_TEMP_BLOCK;
        pseudoGrid[1][8] = PSEUDO_TEMP_BLOCK;pseudoGrid[1][10] = PSEUDO_TEMP_BLOCK;
        pseudoGrid[2][1] = PSEUDO_TEMP_BLOCK;pseudoGrid[2][5] = PSEUDO_TEMP_BLOCK;
        pseudoGrid[2][7] = PSEUDO_TEMP_BLOCK;pseudoGrid[2][9] = PSEUDO_TEMP_BLOCK;
        pseudoGrid[2][11] = PSEUDO_TEMP_BLOCK;pseudoGrid[3][4] = PSEUDO_TEMP_BLOCK;
        pseudoGrid[3][8] = PSEUDO_TEMP_BLOCK;pseudoGrid[4][1] = PSEUDO_TEMP_BLOCK;
        pseudoGrid[4][3] = PSEUDO_TEMP_BLOCK;pseudoGrid[4][5] = PSEUDO_TEMP_BLOCK;
        pseudoGrid[4][9] = PSEUDO_TEMP_BLOCK;pseudoGrid[5][14] = PSEUDO_TEMP_BLOCK;
        pseudoGrid[6][1] = PSEUDO_TEMP_BLOCK;pseudoGrid[6][5] = PSEUDO_TEMP_BLOCK;
        pseudoGrid[7][12] = PSEUDO_TEMP_BLOCK;pseudoGrid[8][1] = PSEUDO_TEMP_BLOCK;
        pseudoGrid[8][9] = PSEUDO_TEMP_BLOCK;pseudoGrid[8][11] = PSEUDO_TEMP_BLOCK;
        pseudoGrid[9][2] = PSEUDO_TEMP_BLOCK;pseudoGrid[9][8] = PSEUDO_TEMP_BLOCK;
        pseudoGrid[10][7] = PSEUDO_TEMP_BLOCK;pseudoGrid[10][9] = PSEUDO_TEMP_BLOCK;
    }
    
}
