/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnaAssembler.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.graph.DirectedMultigraph;

import dnaAssembler.test.JGraphAdapter;

/**
 * 
 * @author Jacek
 */
public class DeBruijnGraph extends DirectedMultigraph<String, String> {

	public static final int LEVEL = 4; // ?

	public DeBruijnGraph(final DeBruijnEdgeFactory deBruijnEdgeFactory) {
		super(deBruijnEdgeFactory);
		deBruijnEdgeFactory.setGraph(this);
	}

	// Algorytm Fleuryego
	public List<String> findEulerPath() {
		final List<String> eulerPath = new ArrayList<>();
		final List<String> stack = new ArrayList<>();
		final DeBruijnGraph graph = (DeBruijnGraph) this.clone();

		String v = (String) graph.vertexSet().toArray()[0];
		// eulerPath.add(v);

		while (true) {
			while (graph.outgoingEdgesOf(v).size() > 0) {
				final String edge_v_w = graph.outgoingEdgesOf(v).toArray()[0]
						.toString();
				final String w = graph.getEdgeTarget(edge_v_w);
				stack.add(w);
				graph.removeEdge(v, w);
				v = w;
			}
			if (stack.size() != 0) {
				v = stack.remove(stack.size() - 1);
				eulerPath.add(v);
				continue;
			}
			break;
		}
		System.out.println("eulerPath: " + eulerPath);

		// for(String vertex : this.vertexSet()) {
		// eulerPath.add(vertex);
		// }

		return eulerPath;
	}

	public void show() {
		final JGraphAdapter d = new JGraphAdapter();
		d.init();
	}

	public synchronized List<String> findEulerPath(final boolean verbose) {
		//
		// moze inny algorytm, fleury, hierholzer
		//
		//
		//
		//
		final List<String> path = new LinkedList<String>();
		final DeBruijnGraph g = (DeBruijnGraph) this.clone();

		while (g.vertexSet().size() != 0) {
			System.out.print("edges: " + g.edgeSet());
			String start = null, end = null;
			int startIndex = -1;
			if (path.size() == 0) {
				final Set<String> imbalanced = g.getImbalancedVertices();
				if (imbalanced.size() != 2) {
					if (verbose) {
						System.err.println("Imbalanced graph. Aborting...");
					}
					System.out.print("exception?");
					return null;
				}
				for (final String vert : imbalanced) {
					if (g.inDegreeOf(vert) < g.outDegreeOf(vert)) {
						start = vert;
					} else if (g.inDegreeOf(vert) > g.outDegreeOf(vert)) {
						end = vert;
					}
				}
				assert (start != null && end != null && !start.equals(end));
			} else { // that is path is not null
				for (final String vert : g.vertexSet()) {

					startIndex = path.lastIndexOf(vert);
					if (startIndex >= 0) {
						path.remove(startIndex);
						start = vert;
						break;
					}
				}
			}
			while (start != null) {
				if (startIndex == -1) {
					path.add(start);
				} else {
					path.add(startIndex++, start);
				}
				final Set<String> directions = g.outgoingEdgesOf(start);
				if (directions.size() > 0) {
					final String direction = directions
							.toArray(new String[directions.size()])[0];
					start = g.getEdgeTarget(direction);
					g.removeEdge(direction);
				} else {
					start = null;
				}
			}
			// remove stranded vertices - could have maintained a list of used
			// vertices...
			final Set<String> vertsToRemove = new HashSet<String>();
			for (final String vert : g.vertexSet()) {
				if (g.inDegreeOf(vert) == 0 && g.outDegreeOf(vert) == 0) {
					vertsToRemove.add(vert);
				}
			}
			for (final String vert : vertsToRemove) {
				g.removeVertex(vert);
			}
		}

		return path;
	}

	public Set<String> getImbalancedVertices() {
		final Set<String> vers = new HashSet<String>();
		for (final String vert : this.vertexSet()) {
			if (this.inDegreeOf(vert) != this.outDegreeOf(vert)) {
				vers.add(vert);
			}
		}
		return vers;
	}

}
