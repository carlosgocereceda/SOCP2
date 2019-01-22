package modeloBarabasiAlbert;

import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Node;

public class GenerarBarabasiAlbert {
	private int M; // Num. de enlaces con los que entra un nodo nuevo a la red
	private int T; // numero de pasos del modelo
	private int m0; // Nodos iniciales (todos conectados con todos)
	private int N; // Numero de nodos
	// Nuevo
	private Red redOriginal; // Red generada desde un principio para simplemente llamarla
	private Ruleta ruletaOriginal;// Ruleta de la red original para que luego haga sus correspondientes llamadas

	public GenerarBarabasiAlbert(int m, int t) {
		this.M = m;
		this.T = t;
		this.m0 = m + 1;
		this.N = this.m0 + t;
		this.ruletaOriginal = new Ruleta(this.redOriginal);
		this.redOriginal = this.generaRedInicial();
	}

	private Red generaRedInicial() {
		Red red = new Red(this.m0, this.N);
		for(int i = 0; i < m0+1; i++) {
			Node nodo1 = red.getGraphModel().factory().newNode(Integer.toString(i));
			red.getGraph().addNode(nodo1);
		}
		
		// Nos recorremos todos los nodos INICIALES, añadiendoles la rista entre ellos, si no existese
		for (int i = 1; i < this.m0 +1; i++) {
			for (int j = i+1; j < this.m0 +1; j++) {
				Node nodo1 = red.getGraph().getNode(Integer.toString(i));
				Node nodo2 = red.getGraph().getNode(Integer.toString(j));
				Edge e1 = red.getGraph().getEdge(nodo1, nodo2);
				e1 = e1 == null ? red.getGraphModel().factory().newEdge(nodo1, nodo2, false) : e1;
				
				if (!red.getGraph().contains(e1)) {
					red.addArista(i,j);
					this.ruletaOriginal.update(i);
					this.ruletaOriginal.update(j);
				}
			}
		}
		return red;
	}
	// Metodo llamado en el MAIN
	public Red simularRed() {
		// Creeamos la red
		Red red = this.redOriginal;
		// Nos guardamos en la ruleta las veces que salen los nodos, para luego elegir cual cogeremos
		Ruleta ruleta = this.ruletaOriginal;
		System.out.println(red.getNumAristas() + " aristas");
		for (int i = red.getGraph().getNodeCount(); i < this.T + this.m0; i++) {
			System.out.println(red.getGraph().getNodeCount());
			//Nodo n1 = red.getExistingNodo(i); // Nuevo nodo
			Node n1 = red.getGraphModel().factory().newNode(Integer.toString(i));
			red.getGraph().addNode(n1);
			int counter = 0;
			while (counter < this.M) { // Genera m <= m0 enlaces
				
				// Método de selección por ruleta
				Integer in2 = ruleta.barabasi();
				Node n2 = red.getGraph().getNode(Integer.toString(in2));

				if (in2 == null || n2 == null) {
					System.err.println("ERROR: Ruleta ha generado un nodo: " + in2 + " no existente en la red.");
					return red; // Salir del error
				} else {
					Edge e = red.getGraph().getEdge(n1, n2);
					e = e == null ? red.getGraphModel().factory().newEdge(n1, n2, false) : e;
					//Arista a = new Arista(n1, n2);
					if (!n1.equals(n2) && !red.getGraph().contains(e)) {
						/*
						 * Actualiza la ruleta, sumando uno al grado de n1 y n2
						 * para no generar la ruleta varias veces
						 */
						ruleta.update(in2);
						ruleta.update(Integer.parseInt((String) n1.getId()));
						red.addArista(Integer.parseInt((String) n1.getId()),in2);
						counter += 1;
					} else {
						// System.out.println("Ya contenia la arista: " + a);
					}
				}
			}
		}
		return red;
	}

	public int getN() {
		return N;
	}
}