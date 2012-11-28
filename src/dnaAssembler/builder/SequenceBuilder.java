/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnaAssembler.builder;

import java.util.List;

/**
 *
 * @author Jacek
 */
public class SequenceBuilder {
    public static String build(List<String> eulerPath) {
//        System.out.println("BUILDER no: "+eulerPath);
                String base = "";
                for (int i = 0; i < eulerPath.size(); ++i) {
                        if(i == 0) {
                            base = eulerPath.get(i);
                        } else {
                            base = concatSequences(base, eulerPath.get(i));
                        }
                        
//                        System.out.println("BUILDER: "+base);
                }
//                System.out.println("BUILDER res: "+base);
                return base;
        }
    
    private static String concatSequences(String base, String next) {
//        System.out.println("CCT: "+base+", "+next);
        for(int i = next.length(); i >0; i--) {
//            System.out.println("concatSequences: "+base.substring(base.length()-i, base.length())+" + "+next.substring(0, i));
            if(base.substring(base.length()-i, base.length()).equals(next.substring(0, i))) {
                return base.substring(0, base.length()-i)+next;
            }
        }
        return base+next;
    }
}
