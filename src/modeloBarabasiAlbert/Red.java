package modeloBarabasiAlbert;

import java.util.ArrayList;
import java.util.List;

public class Red {
	protected List<Arista> aristas;
	//protected HashMap<Integer, Nodo> nodos;
	protected ArrayList<Nodo> nodos;
	protected int nodosEnLaRed = 0;
	
	public Red(int initialCapacity, int nodos) {
		this.aristas = new ArrayList<>(initialCapacity);
		//this.nodos = new HashMap<>();
		this.nodos = new ArrayList<Nodo>();
		// Inicializamos a null la lista de nodos
		for(int i = 1; i <= nodos; i++) {
			this.nodos.add(null);
		}
		
	}
	
	public int numNodos() {
		return this.nodosEnLaRed;
	}
	
	public int numAristas() {
		return this.aristas.size();
	}
	
	public boolean contains(Arista a) {
		Nodo n1 = a.getNodo1(), n2 = a.getNodo2();
		compruebaExistenciaNodos(a);		
		return this.aristas.contains(new Arista(this.nodos.get(n1.getValue()), this.nodos.get(n2.getValue())));
	}
	
	private void compruebaExistenciaNodos(Arista a) {
		int nodo1 = a.getNodo1().getValue(), nodo2 = a.getNodo2().getValue();
		if(this.nodos.get(nodo1) == null) {
			this.nodos.set(nodo1,  new Nodo(nodo1, 0));
			nodosEnLaRed += 1;
		}
		if(this.nodos.get(nodo2) == null) {
			this.nodos.set(nodo2,  new Nodo(nodo2, 0));
			nodosEnLaRed += 1;
		}
		/*if(!this.nodos.containsKey(nodo1))
			this.nodos.put(nodo1, new Nodo(nodo1, 0));
		if(!this.nodos.containsKey(nodo2))
			this.nodos.put(nodo2, new Nodo(nodo2, 0));*/
	}
	
	public void add(Arista a) {
		compruebaExistenciaNodos(a);
		Nodo n1 = this.nodos.get(a.getNodo1().getValue());
		Nodo n2 = this.nodos.get(a.getNodo2().getValue());
		
		n1.incrementDegree();
		n2.incrementDegree();
		
		this.aristas.add(new Arista(n1, n2));
	}
	
	/**
	 * Devuelve el nodo de la red SI NO EXISTE lo creara
	 * @param nodo Nodo de la red
	 * @return Nodo parte de la red
	 */
	public Nodo getExistingNodo(int nodo) {
		if(this.nodos.get(nodo) == null) {
			this.nodos.set(nodo, new Nodo(nodo, 0));
			nodosEnLaRed += 1;
		}
		/*if(!this.nodos.containsKey(nodo))
			this.nodos.put(nodo, new Nodo(nodo, 0));*/
		
		return this.nodos.get(nodo);
	}
	
	public Nodo getNodo(int nodo) {
		return this.nodos.get(nodo);
	}
	
	public List<Arista> getAristas() {
		return aristas;
	}

	public void setAristas(List<Arista> aristas) {
		this.aristas = aristas;
	}
	public ArrayList<Nodo> getNodos(){
		return this.nodos;
	}
	
	public Nodo getLargestHubDegree() {
		int max = 0;
		Nodo nMax = null;
		
		for(Nodo n : this.nodos) {
			if(n != null) {
				if(n.getDegree() > max) {
					max = n.getDegree();
					nMax = n;
				}
			}
			
		}
		
		return nMax;
	}
	
	@Override
	public String toString() {
		String sOut = "###### NODOS ######" + System.getProperty("line.separator");
		
		for (Nodo n : this.nodos)
			if(n != null) {
				sOut += n + System.getProperty("line.separator");
			}
			
		
		sOut = "###### ARISTAS ######" + System.getProperty("line.separator");
		
		for (Arista a : this.aristas)
			sOut += a + System.getProperty("line.separator");
		
		return sOut;
	}
}
