package modeloBarabasiAlbert;

import java.io.File;
import java.io.IOException;

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
import org.gephi.statistics.plugin.ConnectedComponents;
import org.gephi.statistics.plugin.Degree;
import org.gephi.statistics.plugin.GraphDensity;
import org.gephi.statistics.plugin.GraphDistance;
import org.openide.util.Lookup;
import org.gephi.graph.api.Node;

public class ConversorGephiToolkit {
	public GraphModel graphModel;
	public Graph graph;

	public ConversorGephiToolkit(Red r) {
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
			Node n1 = this.graph.getNode(Integer.toString(a.getNodo1().getValue()));
			if (n1 == null) {
				n1 = graphModel.factory().newNode(Integer.toString(a.getNodo1().getValue()));
				this.graph.addNode(n1);
			}

			Node n2 = this.graph.getNode(Integer.toString(a.getNodo2().getValue()));
			if (n2 == null) {
				n2 = graphModel.factory().newNode(Integer.toString(a.getNodo2().getValue()));
				this.graph.addNode(n2);
			}

			Edge e = graphModel.factory().newEdge(n1, n2, false);

			if (!this.graph.contains(e))
				this.graph.addEdge(e);

		}
		
		this.graphModel = this.graph.getModel();
		System.out.println("Modelo cargado, red no-dirigida construida!");
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
		Degree degree = new Degree();
		degree.execute(this.graph);

		return degree;
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
