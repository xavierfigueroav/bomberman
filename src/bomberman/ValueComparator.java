/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.util.Comparator;
import java.util.Map;

/**
 *
 * @author Xavier
 */
public class ValueComparator implements Comparator<String> {
    
    Map<String, Integer> toBeOrdered;
    
    public ValueComparator(Map<String, Integer> toBeOrdered){
        
        this.toBeOrdered = toBeOrdered;
        
    }

    @Override
    public int compare(String key1, String key2) {
        return toBeOrdered.get(key2) - toBeOrdered.get(key1);
    }
    
}
