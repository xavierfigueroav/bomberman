/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

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
    public final int GRID_WIDTH, GRID_HEIGHT;
    private GridPane grid;
    private int[][] pseudoGrid;
    public final int PSEUDO_FIXED_BLOCK, PSEUDO_TEMP_BLOCK, PSEUDO_NO_BLOCK, PSEUDO_BOMB;
    final private Image tempBlockImage;
    
    public GameBoard(String pathTempBlockImage){
        
        PSEUDO_FIXED_BLOCK = 1;
        PSEUDO_TEMP_BLOCK = 2;
        PSEUDO_NO_BLOCK = 0;
        PSEUDO_BOMB = -1;
        
        
        GRID_WIDTH = 15;
        GRID_HEIGHT = 11;
        
        this.pseudoGrid = new int[GRID_HEIGHT][GRID_WIDTH];
        
        this.grid = new GridPane();
        this.grid.setId("grid");
        
        
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
        
        
        //this.grid.getStylesheets().add("/styles/malla.css");
    }
    
    
    public GridPane getGrid(){
        return this.grid;
    }
    
    private void setFixedBlocks(){
        
        for(int i = 0; i<GRID_HEIGHT; i++){
            for(int j = 0;j<GRID_WIDTH;j++){
                if((i%2!=0)&&(j%2!=0)){
                    this.pseudoGrid[i][j] = PSEUDO_FIXED_BLOCK;
                }
            }
        }
        
    }
    
    private void setTempBlocks(){
        
        this.pseudoGrid[0][11] = PSEUDO_TEMP_BLOCK;this.pseudoGrid[1][4] = PSEUDO_TEMP_BLOCK;
        this.pseudoGrid[1][8] = PSEUDO_TEMP_BLOCK;this.pseudoGrid[1][10] = PSEUDO_TEMP_BLOCK;
        this.pseudoGrid[2][1] = PSEUDO_TEMP_BLOCK;this.pseudoGrid[2][5] = PSEUDO_TEMP_BLOCK;
        this.pseudoGrid[2][7] = PSEUDO_TEMP_BLOCK;this.pseudoGrid[2][9] = PSEUDO_TEMP_BLOCK;
        this.pseudoGrid[2][11] = PSEUDO_TEMP_BLOCK;this.pseudoGrid[3][4] = PSEUDO_TEMP_BLOCK;
        this.pseudoGrid[3][8] = PSEUDO_TEMP_BLOCK;this.pseudoGrid[4][1] = PSEUDO_TEMP_BLOCK;
        this.pseudoGrid[4][3] = PSEUDO_TEMP_BLOCK;this.pseudoGrid[4][5] = PSEUDO_TEMP_BLOCK;
        this.pseudoGrid[4][9] = PSEUDO_TEMP_BLOCK;this.pseudoGrid[5][14] = PSEUDO_TEMP_BLOCK;
        this.pseudoGrid[6][1] = PSEUDO_TEMP_BLOCK;this.pseudoGrid[6][5] = PSEUDO_TEMP_BLOCK;
        this.pseudoGrid[7][12] = PSEUDO_TEMP_BLOCK;this.pseudoGrid[8][1] = PSEUDO_TEMP_BLOCK;
        this.pseudoGrid[8][9] = PSEUDO_TEMP_BLOCK;this.pseudoGrid[8][11] = PSEUDO_TEMP_BLOCK;
        this.pseudoGrid[9][2] = PSEUDO_TEMP_BLOCK;this.pseudoGrid[9][8] = PSEUDO_TEMP_BLOCK;
        this.pseudoGrid[10][7] = PSEUDO_TEMP_BLOCK;this.pseudoGrid[10][9] = PSEUDO_TEMP_BLOCK;
    }
    
    public int[][] getPseudoGrid(){
        return this.pseudoGrid;
    }
    
    public Image getTempBlockImage(){
        return this.tempBlockImage;
    }
    
    
    public boolean hasATempBlockIn(int x, int y){
        
        return (this.pseudoGrid[y][x]==2);
        
    }
    
    public boolean hasAFixedBlockIn(int x, int y){
        
        return (this.pseudoGrid[y][x]==1);
        
    }
    
    public boolean noBlockAt(int x, int y){
        
        return (this.pseudoGrid[y][x]==0);
    
    }
    
}
