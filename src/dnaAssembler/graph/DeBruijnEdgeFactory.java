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
public class DeBruijnEdgeFactory implements EdgeFactory<String, String> {
	private DeBruijnGraph graph;

	public void setGraph(final DeBruijnGraph graph) {
		this.graph = graph;
	}

	@Override
	public String createEdge(final String v1, final String v2) {

		if (v1.substring(1).equals(v2.substring(0, v2.length() - 1))) {
			String suffix = "";
			if (graph.containsEdge(v1, v2)) {
				final Set<String> fromV1 = graph.outgoingEdgesOf(v1);
				final Set<String> intoV2 = graph.incomingEdgesOf(v2);
				int nextNumber = 1;

				for (final String fv1 : fromV1) {
					if (intoV2.contains(fv1)) {
						++nextNumber;
					}
				}
				suffix = "(" + nextNumber + ")";
			}
			if (v1.length() > v2.length()) {
				return v1 + v2.substring(v2.length() - 1, v2.length()) + suffix;
			} else {
				return v1.substring(0, 1) + v2 + suffix;
			}
		} else {
			return null;
		}
	}

}
