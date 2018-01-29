/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

/**
 *
 * @author Xavier
 */
public class Grid {
    final private int WIDTH, HEIGHT, BLOCK_SIZE;
    final private Background background ;
    private ArrayList<Integer> fixedBlocks, tempBlocks;
    final private Image tempBlockImage;
    
    public Grid(Image backgroundImage, String pathTempBlockImage){
        this.WIDTH = 15;
        this.HEIGHT = 11;
        this.BLOCK_SIZE = 50;
        
        this.background = this.getBackground(backgroundImage);
        this.setFixedBlocks();
        this.setTempBlocks();
        this.tempBlockImage = FileManager.getImage(pathTempBlockImage);
    }
    
    private Background getBackground(Image image){
        BackgroundImage backgroundImage = new BackgroundImage(image,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        return background;
    }
    
    private void setFixedBlocks(){
        
        this.fixedBlocks = new ArrayList();
        for(int i = 0; i<5; i++){
            int block = 15 + (i*30);
            for(int j = 0;j<7;j++){
                block += 2;
                this.fixedBlocks.add(block);
            }
        }
        
    }
    
    public void setTempBlocks(){
        this.tempBlocks = new ArrayList();
        
        this.tempBlocks.add(12);this.tempBlocks.add(20);this.tempBlocks.add(24);this.tempBlocks.add(26);
        this.tempBlocks.add(32);this.tempBlocks.add(36);this.tempBlocks.add(38);this.tempBlocks.add(40);
        this.tempBlocks.add(42);this.tempBlocks.add(50);this.tempBlocks.add(54);this.tempBlocks.add(62);
        this.tempBlocks.add(64);this.tempBlocks.add(66);this.tempBlocks.add(70);this.tempBlocks.add(90);
        this.tempBlocks.add(92);this.tempBlocks.add(96);this.tempBlocks.add(118);this.tempBlocks.add(122);
        this.tempBlocks.add(130);this.tempBlocks.add(132);this.tempBlocks.add(138);this.tempBlocks.add(144);
        this.tempBlocks.add(158);this.tempBlocks.add(160);
        
    }
    
    public ArrayList<Integer> getTempBlocks(){
        return this.tempBlocks;
    }
    
    public Image getTempBlockImage(){
        return this.tempBlockImage;
    }
    
    
    
    public boolean hasATempBlockIn(int position){
        return this.tempBlocks.contains(position);
    }
    
    public boolean hasATempBlockIn(double x, double y){
        int position = convertToBlockNumber(x/BLOCK_SIZE, y/BLOCK_SIZE);
        return this.tempBlocks.contains(position);
    }
    
    public boolean hasATFixedBlockIn(int position){
        return this.fixedBlocks.contains(position);
    }
    
    public boolean hasAFixedBlockIn(double x, double y){
        int position = convertToBlockNumber(x/BLOCK_SIZE, y/BLOCK_SIZE);
        return this.fixedBlocks.contains(position);
    }
    
    private int convertToBlockNumber(double x, double y){
        int blockNumber = ((int)y * WIDTH) + (int)x + 1;
        return blockNumber;
    }
    
    public double[] convertToBlockPosition(int blockNumber){
        
        
        double yPosition = (int)((blockNumber-1) / WIDTH);
        double xPosition = blockNumber - (WIDTH * yPosition) - 1;
        double[] blockPosition = {xPosition * BLOCK_SIZE, yPosition * BLOCK_SIZE};
        
        return blockPosition;
    }
    
    public Background getBackground(){
        return this.background;
    }
    
}
