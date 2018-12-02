package modeloAleatorio;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.UndirectedGraph;
import org.gephi.graph.impl.GraphModelImpl;
import org.gephi.io.exporter.api.ExportController;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.EdgeDirectionDefault;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.gephi.statistics.plugin.ClusteringCoefficient;
import org.gephi.statistics.plugin.ConnectedComponents;
import org.gephi.statistics.plugin.Degree;
import org.gephi.statistics.plugin.GraphDensity;
import org.gephi.statistics.plugin.GraphDistance;
import org.openide.util.Lookup;

public class ConversorGephiToolkit {
	public GraphModel graphModel;
	public Graph graph;
	private Degree degree = null;
	private int numAristas = 0;
	private int numPares = 0;

	public ConversorGephiToolkit() {
		this.graphModel = new GraphModelImpl();

		// Convert a Red to a GraphModel just adding the nodes and edges to the empty
		this.graph = this.graphModel.getUndirectedGraph();
		Set<Edge> edgesToRemove = new HashSet<>();

		for (int i = 1; i <= Main.N; i++) {
			Node n1 = this.graph.getNode(Integer.toString(i));
			if (n1 == null) {
				n1 = graphModel.factory().newNode(Integer.toString(i));
				this.graph.addNode(n1);
			}

			for (int j = 1; j <= Main.N; j++) {
				if (i != j) {
					Node n2 = this.graph.getNode(Integer.toString(j));
					if (n2 == null) {
						n2 = graphModel.factory().newNode(Integer.toString(j));
						this.graph.addNode(n2);
					}
					
					Edge e = this.graph.getEdge(n1, n2);
					Edge e2 = this.graph.getEdge(n2, n1);
					Edge eN = graphModel.factory().newEdge(n1, n2, false);
					
					if(( (e != null && !this.graph.contains(e)) || e == null)
							&& (e2 != null && !this.graph.contains(e2) || e2 == null)) {
						this.graph.addEdge(eN);
						
						if(!Main.generarRandom(Main.p))
							edgesToRemove.add(eN);
					}
				}
			}
		}
		
		this.numPares = this.graph.getEdgeCount();
		
		this.graph.removeAllEdges(edgesToRemove);
		
		this.numAristas = this.graph.getEdgeCount();
		
		if(this.numPares != ((Main.N * (Main.N - 1) ) / 2))
			System.out.println("Numero de pares: " + this.numPares + " debian ser: " + ((Main.N * (Main.N - 1) ) / 2));
		
		this.graphModel = this.graph.getModel();
	}

	public ConversorGephiToolkit(List<Arista> r) {
		this.graphModel = new GraphModelImpl();

		// Convert a Red to a GraphModel just adding the nodes and edges to the empty
		this.graph = this.graphModel.getUndirectedGraph();

		for (Arista a : r) {
			Node n1 = this.graph.getNode(Integer.toString(a.getNodo1()));
			if (n1 == null) {
				n1 = graphModel.factory().newNode(Integer.toString(a.getNodo1()));
				this.graph.addNode(n1);
			}

			Node n2 = this.graph.getNode(Integer.toString(a.getNodo2()));
			if (n2 == null) {
				n2 = graphModel.factory().newNode(Integer.toString(a.getNodo2()));
				this.graph.addNode(n2);
			}

			Edge e = graphModel.factory().newEdge(n1, n2, false);

			if (!this.graph.contains(e))
				this.graph.addEdge(e);

		}

		this.graphModel = this.graph.getModel();
	}

	public void importFile(String filePath) {
		ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
		pc.newProject();
		Workspace workspace = pc.getCurrentWorkspace();
		ImportController importController = Lookup.getDefault().lookup(ImportController.class); // Import Grapg File
		Container container;

		try {
			File file = new File(filePath);
			container = importController.importFile(file);
			container.getLoader().setEdgeDefault(EdgeDirectionDefault.UNDIRECTED); // Force Undirected
			System.out.println("Empty graph imported");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		// Append imported data to GraphAPI
		importController.process(container, new DefaultProcessor(), workspace);

		// Model it exists because we have a workspace
		this.graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
	}

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
}
