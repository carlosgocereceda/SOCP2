package modeloBarabasiAlbert;

import java.util.ArrayList;
import java.util.Random;

public class Ruleta {

	private ArrayList<Integer> ruleta;

	public Ruleta(Red red) {
		this.ruleta = new ArrayList<Integer>();
		// Recorremos los nodos de la red
	}

	public int barabasi() {
		Random r = new Random();
		return this.ruleta.get(r.nextInt(this.ruleta.size()));
	}

	public void update(Integer nodo) {
		this.ruleta.add(nodo);		
	}

}
