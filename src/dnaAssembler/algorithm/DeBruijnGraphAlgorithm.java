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
	private final DeBruijnGraph deBruijnGraph;

	public DeBruijnGraphAlgorithm(final DeBruijnGraph deBruijnGraph) {
		this.deBruijnGraph = deBruijnGraph;
	}

	public String assembly() {
		final List<String> eulerPath = this.deBruijnGraph.findEulerPath();

		System.out.println("EULER " + eulerPath);

		final String result = SequenceBuilder.build(eulerPath);
		return result;
	}
}
