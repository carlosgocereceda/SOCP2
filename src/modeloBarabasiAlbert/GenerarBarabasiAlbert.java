package modeloBarabasiAlbert;

public class GenerarBarabasiAlbert {
	private int M; // Num. de enlaces con los que entra un nodo nuevo a la red
	private int T; // numero de pasos del modelo
	private int m0; // Nodos iniciales (todos conectados con todos)
	private int N; // Numero de nodos

	public GenerarBarabasiAlbert(int m, int t) {
		this.M = m;
		this.T = t;
		this.m0 = m + 1;
		this.N = this.m0 + t;
	}

	private Red generaRedInicial() {
		Red red = new Red(this.m0);

		for (int i = 1; i <= this.m0; i++) {
			for (int j = 1; j <= this.m0; j++) {
				Arista a = new Arista(new Nodo(i, 0), new Nodo(j, 0));
				if (!red.contains(a) && i != j) {
					red.add(a);
				}
			}
		}
		System.out.println("Red inicial creada con " + red.numNodos() + " nodos y " + red.numAristas() + " aristas."
				+ System.getProperty("line.separator") + red);
		return red;
	}

	public Red simularRed() {
		Red red = this.generaRedInicial();
		
		for (int i = red.numNodos() + 1; i < this.T + red.numNodos() + 1; i++) {
			Nodo n1 = red.getExistingNodo(i); // Nuevo nodo

			while (n1.getDegree() <= this.M) { // Genera m <= m0 enlaces
				Ruleta ruleta = new Ruleta(red);
				
				// Método de selección por ruleta
				Integer in2 = ruleta.barabasi();
				Nodo n2 = in2 != null ? red.getNodo(in2.intValue()) : null;

				if (in2 == null || n2 == null) {
					System.err.println("ERROR: Ruleta ha generado un nodo: " + in2 + " no existente en la red.");
					return red; // Salir del error
				} else {
					Arista a = new Arista(n1, n2);
					if (!n1.equals(n2) && !red.contains(a)) {
						red.add(a);
					} else {
						System.out.println("Ya contenia la arista: " + a);
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