/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnaAssembler.test;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import dnaAssembler.algorithm.DeBruijnGraphAlgorithm;
import dnaAssembler.builder.DeBruijnGraphBuilder;
import dnaAssembler.generator.SequenceGenerator;
import dnaAssembler.generator.SubsequencesGenerator;
import dnaAssembler.graph.DeBruijnGraph;

/**
 * 
 * @author Jacek
 */
public class BuildGraphTest {
	public static void test() {

		final String sequence = SequenceGenerator.generateSequence(10);
		// List<String> subsequences = SubsequencesGenerator.generate(sequence,
		// 4);
		final List<String> subsequences = new ArrayList<String>();

		subsequences.add("AAABA");
		subsequences.add("ABAAB");
		subsequences.add("BAABA");
		subsequences.add("AABAB");

		System.out.println("Sequence: " + sequence);
		System.out.println(subsequences);

		final DeBruijnGraph graph = DeBruijnGraphBuilder.build(subsequences);
		final DeBruijnGraphAlgorithm algorithm = new DeBruijnGraphAlgorithm(
				graph);
		final String result = algorithm.assembly();

		System.out.println("Vertecies: " + graph.vertexSet());
		System.out.println("Edges: " + graph.edgeSet());

		System.out.println("---------------------");
		System.out.println("Result: " + result);

		final JGraphAdapter jGraphAdapter = new JGraphAdapter();
		jGraphAdapter.setGraph(graph);

		final JFrame mainFrame = new JFrame("Graph");
		jGraphAdapter.init();
		mainFrame.add(jGraphAdapter, BorderLayout.CENTER);
		mainFrame.pack();
		mainFrame.setVisible(true);
		// mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
