/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnaAssembler.generator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jacek
 */
public class SubsequencesGenerator {
    public static List<String> generate(String longSequence, int n) {
        List<String> subsequences = new ArrayList<>();
        
        for(int i = 0; i+n < longSequence.length()+1; i++) {
            subsequences.add(longSequence.substring(i, i+n));
        }
        
        return subsequences;
    }
    
    
}
