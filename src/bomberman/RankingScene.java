/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author Xavier
 */
public class RankingScene {
    private ScrollPane root;
    
    public RankingScene(){
        
        GridPane grid = new GridPane();
        
        Text head = new Text("Ranking");
        ArrayList<String> scores = FileManager.readFile("src/data/scores.csv");
        
        grid.add(head,0,0);
        for(int i = 0; i<scores.size();i++){
            
            String line = scores.get(i);
            String[] player_score = line.split(",");
            String player = player_score[0];
            String score = player_score[1];
            grid.add(new Text(player),0,i+1);
            grid.add(new Text(score),1,i+1);
            
        }
        grid.setAlignment(Pos.CENTER);
        grid.setVisible(true);
        this.root = new ScrollPane(grid);
    }
    
    public ScrollPane getRoot(){
        return this.root;
    }
    
}
