package modeloBarabasiAlbert;

import java.util.ArrayList;
import java.util.Random;

public class Ruleta {

	private ArrayList<Integer> ruleta;

	public Ruleta(Red red) {
		ArrayList<Nodo> nodos = red.getNodos();
		ruleta = new ArrayList<Integer>();
		for (Nodo nodo : nodos) {
			if (nodo != null) {
				for (int i = 0; i < nodo.getDegree(); i++) {
					ruleta.add(nodo.getValue());
				}
			}
		}
	}

	public int barabasi() {
		Random r = new Random();
		return ruleta.get(r.nextInt(ruleta.size()));
	}

}
