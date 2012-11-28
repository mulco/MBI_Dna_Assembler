/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnaAssembler.builder;

import java.util.List;
import dnaAssembler.generator.SubsequencesGenerator;
import dnaAssembler.graph.DeBruijnEdgeFactory;
import dnaAssembler.graph.DeBruijnGraph;

/**
 *
 * @author Jacek
 */
public class DeBruijnGraphBuilder {
    public static DeBruijnGraph build(List<String> sequences) {
        DeBruijnGraph graph = new DeBruijnGraph(new DeBruijnEdgeFactory());
        
                for (String sequence : sequences) {
                        List<String> subsequences = SubsequencesGenerator.generate(sequence, graph.LEVEL-1);
                        System.out.println("TEST ("+sequence+"): "+subsequences);
                        
                        String v1 = null;
                        String v2 = null;
                        for(int i = 0; i < subsequences.size(); i++) {
                            v1 = v2;
                            v2 = subsequences.get(i);
                            
                            if(v1 == null || v2 == null) {
                                continue;
                            }
                            
                            if (!graph.containsVertex(v1)) {
                                graph.addVertex(v1);
                            }
                            if (!graph.containsVertex(v2)) {
                                    graph.addVertex(v2);
                            }
                            if (!graph.containsEdge(v1, v2)) {
                                    graph.addEdge(v1, v2);
                            }
                        }
                       
                        
                        
//                        String lo = sequence.substring(s, e - 1);
//                        String ld = sequence.substring(s + 1, e);
//                        
//                        if (!graph.containsVertex(lo)) {
//                                graph.addVertex(lo);
//                        }
//                        if (!graph.containsVertex(ld)) {
//                                graph.addVertex(ld);
//                        }
//                        if (!graph.containsEdge(lo, ld)) {
//                                graph.addEdge(lo, ld);
//                        }
                }        
        return graph;
    }
}
