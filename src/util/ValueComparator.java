/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Comparator;
import java.util.Map;

/**
 * Implementa la interface Comparator con el fin de poder ordenar un objeto Map según sus valores.
 * @author Xavier Figueroa, Isaac Solís, Luis Mariño.
 */
public class ValueComparator implements Comparator<String> {
    
    Map<String, Integer> toBeOrdered;
    
    /**
     * Crea una instacia de esta clase.
     * @param toBeOrdered Objeto Map a ser ordenado por valores, con claves de tipo String y valores de tipo Integer.
     */
    public ValueComparator(Map<String, Integer> toBeOrdered){
        
        this.toBeOrdered = toBeOrdered;
        
    }
    
    /**
     * Compara dos elementos de un Map con String como claves e Integers como valores.
     * @param key1 Calve del primer elemento a ser comparado.
     * @param key2 Calve del segundo elemento a ser comparado.
     * @return 
     */
    @Override
    public int compare(String key1, String key2) {
        return toBeOrdered.get(key2) - toBeOrdered.get(key1);
    }
    
}
