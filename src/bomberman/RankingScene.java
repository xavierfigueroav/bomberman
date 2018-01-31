/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Xavier
 */
public class RankingScene {
    private ScrollPane root;
    private TreeMap<String, Integer> rankingSortedByName, rankingSortedByScore;
    private VBox vbox;
    private HBox head, buttons, rankingContainer;
    GridPane grid;
    Button sortByName, sortByScore;
    
    public RankingScene(){
        
        this.createRankings();
        
        head = new HBox();
        Text title = new Text("Ranking");
        head.getChildren().add(title);
        
        buttons = new HBox();
        sortByName = new Button("Sort by name");
        sortByScore = new Button("Sort by score");
        buttons.getChildren().addAll(sortByName, sortByScore);
        
        rankingContainer = new HBox();
        
        grid = new GridPane();
        
        vbox = new VBox();
        vbox.getChildren().addAll(head,buttons,rankingContainer);
        
        
        sortByName.setOnAction(new HandleButton());
        sortByScore.setOnAction(new HandleButton());
        
        this.root = new ScrollPane(vbox);
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
    
    
    private class HandleButton implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
                
            if(event.getSource() == sortByName){
                
                grid.getChildren().clear();

                int i = 0;
                for(Map.Entry<String,Integer> entry : rankingSortedByName.entrySet()) {
                    String player = entry.getKey();
                    String score = Integer.toString(entry.getValue());

                    grid.add(new Text(player),0,i+1);
                    grid.add(new Text(score),1,i+1);
                    i++;
                }

                rankingContainer.getChildren().clear();
                rankingContainer.getChildren().add(grid);


            } else if (event.getSource() == sortByScore){

                grid.getChildren().clear();

                int i = 0;
                for(Map.Entry<String,Integer> entry : rankingSortedByScore.entrySet()) {
                    String player = entry.getKey();
                    String score = Integer.toString(entry.getValue());

                    grid.add(new Text(player),0,i+1);
                    grid.add(new Text(score),1,i+1);
                    i++;
                }

                rankingContainer.getChildren().clear();
                rankingContainer.getChildren().add(grid);

            }
        }
    
    }
    
    public ScrollPane getRoot(){
        return this.root;
    }
    
}
