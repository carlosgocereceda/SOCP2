package modeloBarabasiAlbert;

public class GenerarBarabasiAlbert {
	private int M; // Num. de enlaces con los que entra un nodo nuevo a la red
	private int T; // numero de pasos del modelo
	private int m0; // Nodos iniciales (todos conectados con todos)
	private int N; // Numero de nodos
	
	private EnlacePreferencial enlPref;
	
	public GenerarBarabasiAlbert (int m, int t) {
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
				if(!red.contains(a) && i != j) {
					red.add(a);
				}
			}
		}
		System.out.println("Red inicial creada con " + red.numNodos() + " nodos y " + red.numAristas() + " aristas.");
		
		return red;
	}
	
	public Red simularRed() {
		Red red = this.generaRedInicial();
		
		
		
		return red;
	}
}