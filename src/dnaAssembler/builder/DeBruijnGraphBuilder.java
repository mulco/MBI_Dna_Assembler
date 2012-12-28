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
	public static DeBruijnGraph build(final List<String> sequences) {
		final DeBruijnGraph graph = new DeBruijnGraph(new DeBruijnEdgeFactory());

		for (final String sequence : sequences) {
			final List<String> subsequences = SubsequencesGenerator.generate(
					sequence, graph.LEVEL - 1);
			// System.out.println("TEST ("+sequence+"): "+subsequences);

			String v1 = null;
			String v2 = null;

			for (int i = 0; i < subsequences.size(); i++) {
				v1 = v2;
				v2 = subsequences.get(i);

				if (v1 == null || v2 == null) {
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
		}
		return graph;
	}
}
