package modeloAleatorio;

import org.gephi.graph.api.Edge;
import org.gephi.graph.impl.EdgeImpl;
import org.gephi.graph.impl.NodeImpl;

public class EdgeExtension extends EdgeImpl implements Edge {
	public EdgeExtension(Object id, NodeImpl source, NodeImpl target, int type, double weight, boolean directed) {
		super(id, source, target, type, weight, directed);
	}

	
}
