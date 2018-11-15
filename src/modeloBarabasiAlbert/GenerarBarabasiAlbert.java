package modeloBarabasiAlbert;

public class GenerarBarabasiAlbert {
	private int M; // Num. de enlaces con los que entra un nodo nuevo a la red
	private int T; // numero de pasos del modelo
	private int m0; // Nodos iniciales (todos conectados con todos)
	private int N; // Numero de nodos

	private EnlacePreferencial enlPref;

	public GenerarBarabasiAlbert(int m, int t) {
		this.M = m;
		this.T = t;
		this.m0 = m + 1;
		this.N = this.m0 + t;

		this.enlPref = new EnlacePreferencial();
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
		System.out.println("Red inicial creada con " + red.numNodos() + " nodos y " + red.numAristas() + " aristas.");

		return red;
	}

	public Red simularRed() {
		Red red = this.generaRedInicial();
		Ruleta ruleta = new Ruleta(red);
		
		for (int i = 0; i < this.T; i++) {
			int numEnlacesActual = 0;
			while(numEnlacesActual <= this.m0) { // Genera m <= m0
				Nodo n1 = new Nodo(i, 0); // Nodo inicial
				Nodo n2 = ruleta.barabasi(); // Nodo con el que se enlazara
			
				// COMPROBAR SI PUEDO FORMAR EL ENLACE
				
				red.add(new Arista(n1, n2));
				numEnlacesActual++;
			}
		}
		
		return red;
	}
}