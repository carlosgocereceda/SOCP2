package modeloBarabasiAlbert;

import java.util.ArrayList;
import java.util.Random;

public class Ruleta {

	private ArrayList<Integer> ruleta;

	public Ruleta(Red red) {
		ArrayList<Nodo> nodos = red.getNodos();
		this.ruleta = new ArrayList<Integer>();
		// Recorremos los nodos de la red
		for (Nodo nodo : nodos) {
			// Mientras el nodo no sea null
			if (nodo != null) {
				// Cogemos y añadimos "n" veces a la lista de la RULETA el ID del nodo, n = numero del grado del nodo
				for (int i = 0; i < nodo.getDegree(); i++) {
					this.ruleta.add(nodo.getValue());
				}
			}
		}
	}

	public int barabasi() {
		Random r = new Random();
		return this.ruleta.get(r.nextInt(this.ruleta.size()));
	}

	public void update(Integer nodo) {
		this.ruleta.add(nodo);		
	}

}
