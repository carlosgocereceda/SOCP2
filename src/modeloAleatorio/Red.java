package modeloAleatorio;

import java.io.File;
import java.io.IOException;

import org.gephi.graph.api.Edge;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.UndirectedGraph;
import org.gephi.graph.impl.GraphModelImpl;
import org.gephi.io.exporter.api.ExportController;
import org.gephi.statistics.plugin.ClusteringCoefficient;
import org.gephi.statistics.plugin.ConnectedComponents;
import org.gephi.statistics.plugin.Degree;
import org.gephi.statistics.plugin.GraphDensity;
import org.gephi.statistics.plugin.GraphDistance;
import org.openide.util.Lookup;

public class Red {
	private GraphModel graphModel;
	private UndirectedGraph graph;
	//Para los calculos
	private Degree degree = null;
	private int numAristas = 0;
	
	public Red(int N) {
		this.graphModel = new GraphModelImpl();
		this.graph = this.graphModel.getUndirectedGraph();
		for(int i = 0; i < N; i++) {
			Node n = graphModel.factory().newNode(Integer.toString(i));
			this.graph.addNode(n);
		}
	}
	public void addArista(int n1, int n2) {
		Node nodo1 = this.graph.getNode(Integer.toString(n1));
		Node nodo2 = this.graph.getNode(Integer.toString(n2));
		Edge e = graph.getEdge(nodo1, nodo2);
		e = e == null ? graphModel.factory().newEdge(nodo1, nodo2, false) : e;
		if (!this.graph.contains(e)) {
			this.graph.addEdge(e);
			this.numAristas += 1;
		}
			
	}
	//Para los calculos 
	public GraphModel getGraphModel() {
		return this.graphModel;
	}

	public ClusteringCoefficient getClusteringCoefficient() {
		ClusteringCoefficient clusteringCoefficient = new ClusteringCoefficient();
		clusteringCoefficient.setDirected(false);
		clusteringCoefficient.execute(this.graph);

		return clusteringCoefficient;
	}

	public GraphDistance getGraphDistance() {
		GraphDistance distance = new GraphDistance();
		distance.setDirected(false);
		distance.execute(this.graph);
		return distance;
	}

	public Degree getDegree() {
		if (this.degree == null)
			this.degree = new Degree();

		
		this.degree.execute(this.graph);

		return this.degree;
	}

	public GraphDensity getDensity() {
		GraphDensity gdensity = new GraphDensity();
		gdensity.execute(this.graphModel);
		return gdensity;
	}

	public ConnectedComponents getConnectedComponents() {
		ConnectedComponents ccomp = new ConnectedComponents();
		ccomp.execute(this.graphModel);
		return ccomp;
	}

	public int getLargestHubDegree() {
		if (this.degree == null)
			this.getDegree();

		UndirectedGraph uGraph = this.graphModel.getUndirectedGraph();
		int maxDegree = 0;

		for (Node n : uGraph.getNodes()) {
			int d = uGraph.getDegree(n);

			if (d > maxDegree)
				maxDegree = d;
		}

		return maxDegree;
	}

	public int getShortestHubDegree() {
		if (this.degree == null)
			this.getDegree();

		UndirectedGraph uGraph = this.graphModel.getUndirectedGraph();
		int minDegree = 2147483647; // Max int

		for (Node n : uGraph.getNodes()) {
			int d = uGraph.getDegree(n);

			if (d < minDegree)
				minDegree = d;
		}

		return minDegree;
	}
	
	public int getNumAristas() {
		return numAristas;
	}

	public void setNumAristas(int numAristas) {
		this.numAristas = numAristas;
	}

	public void export(String pathname) {
		// Export full graph
		ExportController ec = Lookup.getDefault().lookup(ExportController.class);
		try {
			ec.exportFile(new File(pathname));
		} catch (IOException ex) {
			ex.printStackTrace();
			return;
		}
	}
	public void setGraphModel(GraphModel graphModel) {
		this.graphModel = graphModel;
	}
	public UndirectedGraph getGraph() {
		return graph;
	}
	public void setGraph(UndirectedGraph graph) {
		this.graph = graph;
	}

}
