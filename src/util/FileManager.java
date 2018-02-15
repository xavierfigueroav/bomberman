/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import javafx.scene.image.Image;


public interface FileManager {
    static ArrayList<String> readFile(String filePath){
        Path path = Paths.get(filePath);
        ArrayList<String> info = new ArrayList();
        try{
            info = (ArrayList)Files.readAllLines(path);
        } catch(IOException io){
            io.printStackTrace();
        }
        return info;
    }
    
    static void writeFile(String filePath, ArrayList<String> data){
        Path path = Paths.get(filePath);
        try{
            Files.write(path,data,StandardCharsets.UTF_8,StandardOpenOption.APPEND);
        }catch(IOException io){
            io.printStackTrace();
        }
    }
    
    static Image getImage(String imagePath){
        FileInputStream img = null;
        Image image = null;
        try {
            img = new FileInputStream(imagePath);
            image = new Image(img);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }finally {
            try {
                img.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return image;
    }
}
