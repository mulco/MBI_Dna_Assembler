/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnaAssembler.graph;

import java.util.Set;
import org.jgrapht.EdgeFactory;

/**
 *
 * @author Jacek
 */
public class DeBruijnEdgeFactory implements EdgeFactory<String, String>  {
    private DeBruijnGraph graph;
    
    public void setGraph(DeBruijnGraph graph){
                this.graph = graph;
    }

    
    @Override
    public String createEdge(String v1, String v2) {
        // Zweryfikowac
        //
        //
        //
         if(v1.substring(1).equals(v2.substring(0, v2.length()-1)) || v1.length() != v2.length()){
                        String suffix="";
                        if(graph.containsEdge(v1, v2)){
                                Set<String> fromV1 = graph.outgoingEdgesOf(v1);
                                Set<String> intoV2 = graph.incomingEdgesOf(v2);
                                int nextNo=1;
                                for(String fv1:fromV1){
                                        if(intoV2.contains(fv1)){
                                                ++nextNo;
                                        }
                                }
                                suffix="("+nextNo+")";
                        }
                        if(v1.length()>v2.length()){
                                return v1+v2.substring(v2.length()-1, v2.length())+suffix;
                        }else{
                                return v1.substring(0,1)+v2+suffix;
                        }
                }else{
                        return null;
                }

        
    }
    
}
