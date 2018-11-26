package modeloBarabasiAlbert;

import java.io.File;
import java.io.IOException;

import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.io.exporter.api.ExportController;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.EdgeDirectionDefault;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.gephi.statistics.plugin.ClusteringCoefficient;
import org.gephi.statistics.plugin.GraphDistance;
import org.openide.util.Lookup;
import org.gephi.graph.api.Node;

public class Conversor {
	public GraphModel graphModel;
	public Graph graph;

	public Conversor(Red r) {
		ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
		pc.newProject();
		Workspace workspace = pc.getCurrentWorkspace();
		ImportController importController = Lookup.getDefault().lookup(ImportController.class); // Import Grapg File
		Container container;

		try {
			File file = new File("emptyGraph.gexf");
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

		// Convert a Red to a GraphModel just adding the nodes and edges to the empty

		this.graph = this.graphModel.getUndirectedGraph();
		
		for (Arista a : r.aristas) {
			/*
			Node n1 = graphModel.factory().newNode(Integer.toString(a.getNodo1().getValue()));
			Node n2 = graphModel.factory().newNode(Integer.toString(a.getNodo2().getValue()));
			
			if(!this.graph.contains(n1) && !this.graph.getNodes().toCollection().contains(n1))
				this.graph.addNode(n1);
			else 
				n1 = this.graph.getNode(Integer.toString(a.getNodo1().getValue()));
			
			if(!this.graph.contains(n2) && !this.graph.getNodes().toCollection().contains(n1))
				this.graph.addNode(n2);
			else
				n2 = this.graph.getNode(Integer.toString(a.getNodo2().getValue()));
				
				*/
			Node n1 = this.graph.getNode(Integer.toString(a.getNodo1().getValue()));
			if(n1 == null) {
				n1 = graphModel.factory().newNode(Integer.toString(a.getNodo1().getValue()));
				this.graph.addNode(n1);
			}
			
			Node n2 = this.graph.getNode(Integer.toString(a.getNodo2().getValue()));
			if(n2 == null) {
				n2 = graphModel.factory().newNode(Integer.toString(a.getNodo2().getValue()));
				this.graph.addNode(n2);
			}
			
			Edge e = graphModel.factory().newEdge(n1, n2, false);
			
			if(!this.graph.contains(e))
				this.graph.addEdge(e);
			
			this.graphModel = this.graph.getModel();
		}

		System.out.println("Modelo cargado, red no dirigida construida!");	
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

	public void export(String pathname) {
		// Export full graph
		ExportController ec = Lookup.getDefault().lookup(ExportController.class);
		try {
			ec.exportFile(new File(pathname));
		} catch (IOException ex) {
			ex.printStackTrace();
			return;
		}
		
		/* 
		// Export only visible graph
		GraphExporter exporter = (GraphExporter) ec.getExporter("gexf"); // Get GEXF exporter
		exporter.setExportVisible(true); // Only exports the visible (filtered) graph
		try {
			ec.exportFile(new File("test-visible.gexf"), exporter);
		} catch (IOException ex) {
			ex.printStackTrace();
			return;
		}
		
		*/
	}

	public void exampleProcesorToolkit() {
		ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
		pc.newProject();
		Workspace workspace = pc.getCurrentWorkspace();

		// Import Grapg File
		ImportController importController = Lookup.getDefault().lookup(ImportController.class);
		Container container;

		try {
			File file = new File(getClass().getResource("/org/gephi/toolkit/demos/resources/test.gexf").toURI());
			container = importController.importFile(file);
			container.getLoader().setEdgeDefault(EdgeDirectionDefault.DIRECTED); // Force Directed
			// container.setAllowAutoNode(false); // Don´t create missing nodes

		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		// Append imported data to GraphAPI
		importController.process(container, new DefaultProcessor(), workspace);

		// Model it exists because we have a workspace
		GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();

		// Create two nodes
		Node n0 = graphModel.factory().newNode("n0");
		n0.setLabel("Node 0");

		Node n1 = graphModel.factory().newNode("n1");
		n1.setLabel("Node 1");

		Edge e1 = graphModel.factory().newEdge(n0, n1, 10, true);

		// Apprend as a Directed Graph
		DirectedGraph directedGraph = graphModel.getDirectedGraph();
		directedGraph.addNode(n0);
		directedGraph.addNode(n1);
		directedGraph.addEdge(e1);

		// Count nodes and edges
		System.out.println("Nodes: " + directedGraph.getNodeCount());

		// Find node by id
		Node node2 = directedGraph.getNode("n2");

		// Modify the graph while reading
		// Due to locking, you need to use toArray() on iterable to be able to modify
		// the graph in a read loop
		for (Node n : directedGraph.getNodes().toArray()) {
			directedGraph.removeNode(n);
		}

	}

	public void statisticsMetrics() {
		GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();

		// Get centrality
		GraphDistance distance = new GraphDistance();
		distance.setDirected(true);
		distance.execute(graphModel);

		ClusteringCoefficient clusteringCoefficient = new ClusteringCoefficient();
		clusteringCoefficient.setDirected(true);
		clusteringCoefficient.execute(graphModel);
	}

}
