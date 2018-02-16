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

/**
 * Contiene métodos que se encangan de la manipulación de archivos fundamentales para el funcionamiento del juego.
 * @author Xavier Figueroa, Isaac Solís, Luis Mariño.
 */
public interface FileManager {
    
    /**
     * Se encarga de leer un archivo solicitado y retornar su contenido como una estructura de datos.
     * @param filePath Ruta relativa del archivo a ser leído.
     * @return Colección de Strings, donde cada String es una línea del archivo leído.
     */
    public static ArrayList<String> readFile(String filePath){
        Path path = Paths.get(filePath);
        ArrayList<String> info = new ArrayList();
        try{
            info = (ArrayList)Files.readAllLines(path);
        } catch(IOException io){
            System.out.println("Error al intentar leer archivo. Asegúrese de que exista.");
        }
        return info;
    }
    
    /**
     * Se encarga de escribir o sobreescribir un archivo determinado, con la información otorgada en forma de una estructura de datos.
     * @param filePath Ruta relativa del archivo a ser escrito o sobreescrito.
     * @param data Colección de Strings, donde cada String se convertirá en una línea del archivo a escribir o sobreescribir.
     */
    public static void writeFile(String filePath, ArrayList<String> data){
        Path path = Paths.get(filePath);
        try{
            Files.write(path,data,StandardCharsets.UTF_8,StandardOpenOption.APPEND);
        }catch(IOException io){
            System.out.println("Error al intentar escribir archivo.");
        }
    }
    
    /**
     * Retorna un objeto Image a partir de una imagen ubicada en la ruta relativa especificada.
     * @param imagePath Rutra relativa de la imagen a ser usada.
     * @return Objeto de tipo Image, cuyo contenido representa la imagen ubicada en la ruta relativa especificada.
     */
    public static Image getImage(String imagePath){
        FileInputStream img = null;
        Image image = null;
        try {
            img = new FileInputStream(imagePath);
            image = new Image(img);
        } catch (FileNotFoundException ex) {
            System.out.println("Error al intentar leer archivo. Asegúrese de que exista.");
        }finally {
            try {
                img.close();
            } catch (IOException ex) {
                System.out.println("Error al intentar cerrar stream. Nunca fue abierto.");
            }
        }
        return image;
    }
}
