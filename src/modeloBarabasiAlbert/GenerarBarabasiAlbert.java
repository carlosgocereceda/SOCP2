package modeloBarabasiAlbert;

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
		// Creeamos la red
		this.redOriginal = this.generaRedInicial();
		// Nos guardamos en la ruleta las veces que salen los nodos, para luego
		// elegir cual cogeremos
		this.ruletaOriginal = new Ruleta(this.redOriginal);
	}

	private Red generaRedInicial() {
		Red red = new Red(this.m0, this.N);

		// Nos recorremos todos los nodos, añadiendoles la rista entre ellos, si no existese
		for (int i = 1; i <= this.m0; i++) {
			for (int j = i+1; j <= this.m0; j++) {
				Arista a = new Arista(new Nodo(i, 0), new Nodo(j, 0));
				// Si no existe en la red lo añado
				if (!red.contains(a) && i != j) {// Esta comrpobacion sobra (i != j)
					red.add(a);
				}
			}
		}
		/*for(Nodo n : red.getNodos()){
			if(n != null)
				System.out.println("Id del nodo: " + n.getValue()+ " Grado: " + n.getDegree());
		}*/
		// System.out.println("Red inicial creada con " + red.numNodos() + " nodos y " + red.numAristas() + " aristas."
		//		+ System.getProperty("line.separator") + red);
		return red;
	}
	// Metodo llamado en el MAIN
	public Red simularRed() {
		// Creeamos la red
		Red red = this.redOriginal;
		// Nos guardamos en la ruleta las veces que salen los nodos, para luego elegir cual cogeremos
		Ruleta ruleta = this.ruletaOriginal;
		
		for (int i = red.numNodos() + 1; i < this.T + this.m0; i++) {
			Nodo n1 = red.getExistingNodo(i); // Nuevo nodo

			while (n1.getDegree() < this.M) { // Genera m <= m0 enlaces
				
				// Método de selección por ruleta
				Integer in2 = ruleta.barabasi();
				Nodo n2 = in2 != null ? red.getNodo(in2.intValue()) : null;

				if (in2 == null || n2 == null) {
					System.err.println("ERROR: Ruleta ha generado un nodo: " + in2 + " no existente en la red.");
					return red; // Salir del error
				} else {
					Arista a = new Arista(n1, n2);
					if (!n1.equals(n2) && !red.contains(a)) {
						/*
						 * Actualiza la ruleta, sumando uno al grado de n1 y n2
						 * para no generar la ruleta varias veces
						 */
						ruleta.update(in2);
						ruleta.update(n1.getValue());
						red.add(a);
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