/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnaAssembler.algorithm;

import java.util.List;
import dnaAssembler.builder.SequenceBuilder;
import dnaAssembler.graph.DeBruijnGraph;

/**
 *
 * @author Jacek
 */
public class DeBruijnGraphAlgorithm {
    private DeBruijnGraph deBruijnGraph;
    
    public DeBruijnGraphAlgorithm(DeBruijnGraph deBruijnGraph) {
        this.deBruijnGraph = deBruijnGraph;
    }
    
    public String assembly() {
        List<String> eulerPath = this.deBruijnGraph.findEulerPath();
      
        
        System.out.println("EULER "+eulerPath);
        
        String result = SequenceBuilder.build(eulerPath);
        return result;
    }
}
