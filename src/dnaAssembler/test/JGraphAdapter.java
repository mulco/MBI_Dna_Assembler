package dnaAssembler.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JApplet;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphModelAdapter;

import dnaAssembler.graph.DeBruijnGraph;

// applet wyswietlajacy graf, przyklad z manuala do biblioteki
public class JGraphAdapter extends JApplet {
	private static final Color DEFAULT_BG_COLOR = Color.decode("#CCFFFF");
	private static final Dimension DEFAULT_DIMENSION = new Dimension(1024, 720);

	private JGraphModelAdapter m_jgAdapter;

	private DeBruijnGraph graph;

	public DeBruijnGraph getGraph() {
		return graph;
	}

	public void setGraph(final DeBruijnGraph graph) {
		this.graph = graph;
	}

	/**
	 * @see java.applet.Applet#init().
	 */
	@Override
	public void init() {
		m_jgAdapter = new JGraphModelAdapter<String, String>(graph);
		final JGraph jGraph = new JGraph(m_jgAdapter);
		adjustDisplaySettings(jGraph);
		getContentPane().add(jGraph);
		setSize(DEFAULT_DIMENSION);

		resize(DEFAULT_DIMENSION);

		distributeVertices(m_jgAdapter, graph);
	}

	// dostosowanie ustawien wyswietlania
	private void adjustDisplaySettings(final JGraph jg) {
		jg.setPreferredSize(DEFAULT_DIMENSION);
		Color c = DEFAULT_BG_COLOR;
		String colorStr = null;

		try {
			colorStr = getParameter("bgcolor");
		} catch (final Exception e) {
		}

		if (colorStr != null) {
			c = Color.decode(colorStr);
		}

		jg.setBackground(c);
	}

	// rozlokowanie wierzcholkow grafu, na podstawie manuala do biblioteki
	private void distributeVertices(
			final JGraphModelAdapter<String, String> ga,
			final Graph<String, String> graph) {
		final int vertices = graph.vertexSet().size();
		final double step = 2 * Math.PI / vertices;
		double counter = 0;
		final double standardSpan = 200;

		for (final String vertex : graph.vertexSet()) {
			final int x = (DEFAULT_DIMENSION.width / 2 + (int) (standardSpan * Math
					.cos(counter)));
			final int y = (DEFAULT_DIMENSION.height / 2 + (int) (standardSpan * Math
					.sin(counter))) - 100;

			final DefaultGraphCell cell = ga.getVertexCell(vertex);
			final Map attr = cell.getAttributes();
			final Rectangle2D b = GraphConstants.getBounds(attr);

			GraphConstants
					.setBounds(attr, new Rectangle(x, y, (int) b.getWidth(),
							(int) b.getHeight()));

			final Map cellAttr = new HashMap();
			cellAttr.put(cell, attr);
			ga.edit(cellAttr, null, null, null);

			counter += step;
		}
	}
}
