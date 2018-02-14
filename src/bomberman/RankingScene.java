/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

/**
 *
 * @author Xavier
 */
public class RankingScene {
    //private ScrollPane root;
    private TreeMap<String, Integer> rankingSortedByName, rankingSortedByScore;
    private VBox root;
    private HBox head, buttons, rankingContainer;
    GridPane grid;
    Button sortByName, sortByScore;
    
    public RankingScene(){
        
        this.createRankings();
        
        head = new HBox();
        Text title = new Text("Ranking");
        title.setId("title");
        head.getChildren().add(title);
        head.setAlignment(Pos.CENTER);
        
        buttons = new HBox();
        sortByName = new Button("Sort by name");
        sortByScore = new Button("Sort by score");
        buttons.getChildren().addAll(sortByName, sortByScore);
        buttons.setSpacing(30);
        buttons.setAlignment(Pos.CENTER);
        
        rankingContainer = new HBox();
        rankingContainer.setAlignment(Pos.CENTER);
        
        grid = new GridPane();
        grid.setHgap(150);
        grid.setVgap(5);
        
        rankingContainer.getChildren().add(grid);
        
        
        this.root = new VBox();
        this.root.setSpacing(15);
        this.root.setPadding(new Insets(30,0,0,0));
        this.root.getChildren().addAll(head,buttons,rankingContainer);
        
        
        
        sortByName.setOnKeyPressed(new HandleButton());
        sortByScore.setOnKeyPressed(new HandleButton());
    }
    
    private void createRankings(){
        
        ArrayList<String> scores = FileManager.readFile("src/data/scores2.txt");
        
        rankingSortedByName = new TreeMap<>();
        
        for(int i = 0; i<scores.size();i++){
            
            String line = scores.get(i);
            String[] player_score = line.split(",");
            String player = player_score[0];
            String score = player_score[1];
            rankingSortedByName.put(player, Integer.parseInt(score));
            
        }
        
        ValueComparator comparator = new ValueComparator(rankingSortedByName);
        rankingSortedByScore = new TreeMap<>(comparator);
        rankingSortedByScore.putAll(rankingSortedByName);
        
    }
    
    
    private class HandleButton implements EventHandler<KeyEvent>{
        @Override
        public void handle(KeyEvent event){
                
            if(event.getCode().equals(KeyCode.ENTER) && event.getSource() == sortByName){
                
                grid.getChildren().clear();

                int i = 0;
                for(Map.Entry<String,Integer> entry : rankingSortedByName.entrySet()) {
                    String player = entry.getKey();
                    String score = Integer.toString(entry.getValue());
                    
                    Text playerText = new Text(player);
                    playerText.setId("playerText");
                    Text scoreText = new Text(score);
                    scoreText.setId("scoreText");

                    grid.add(playerText,0,i+1);
                    grid.add(scoreText,1,i+1);
                    i++;
                }

                rankingContainer.getChildren().clear();
                rankingContainer.getChildren().add(grid);


            } else if (event.getCode().equals(KeyCode.ENTER) && event.getSource() == sortByScore){

                grid.getChildren().clear();

                int i = 0;
                for(Map.Entry<String,Integer> entry : rankingSortedByScore.entrySet()) {
                    String player = entry.getKey();
                    String score = Integer.toString(entry.getValue());
                    
                    Text playerText = new Text(player);
                    playerText.setId("playerText");
                    Text scoreText = new Text(score);
                    scoreText.setId("scoreText");
                    
                    grid.add(playerText,0,i+1);
                    grid.add(scoreText,1,i+1);
                    i++;
                }

                rankingContainer.getChildren().clear();
                rankingContainer.getChildren().add(grid);

            }
        }
    
    }
    
    public VBox getRoot(){
        return this.root;
    }
    
}
