/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnaAssembler.test;

import java.util.ArrayList;
import java.util.List;
import dnaAssembler.algorithm.DeBruijnGraphAlgorithm;
import dnaAssembler.builder.DeBruijnGraphBuilder;
import dnaAssembler.generator.SequenceGenerator;
import dnaAssembler.graph.DeBruijnGraph;

/**
 *
 * @author Jacek
 */
public class BuildGraphTest {
    public static void test() {
        
        String sequence = SequenceGenerator.generateSequence(10);
//        List<String> subsequences = SubsequencesGenerator.generate(sequence, 4);
        List<String> subsequences = new ArrayList<String>();
        
        subsequences.add("AAABA");
        subsequences.add("ABAAB");
        subsequences.add("BAABA");
        subsequences.add("AABAB");
        
        System.out.println("Sequence: "+sequence);
        System.out.println(subsequences);
        
        DeBruijnGraph graph = DeBruijnGraphBuilder.build(subsequences);
        DeBruijnGraphAlgorithm algorithm = new DeBruijnGraphAlgorithm(graph);
        String result = algorithm.assembly();
        
        System.out.println("Vertecies: "+graph.vertexSet());
        System.out.println("Edges: "+graph.edgeSet());
        
        System.out.println("---------------------");
        System.out.println("Result: "+result);
        
    }
}
