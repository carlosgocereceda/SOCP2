package modeloBarabasiAlbert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Red {
	List<Arista> aristas;
	HashMap<Integer, Nodo> nodos;
	
	public Red(int initialCapacity) {
		this.aristas = new ArrayList<>(initialCapacity);

	}
	
	public int numNodos() {
		return this.nodos.size();
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
		if(!this.nodos.containsKey(nodo1))
			this.nodos.put(nodo1, new Nodo(nodo1, 0));
		if(!this.nodos.containsKey(nodo2))
			this.nodos.put(nodo2, new Nodo(nodo2, 0));
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
	public Nodo getNodo(int nodo) {
		if(!this.nodos.containsKey(nodo))
			this.nodos.put(nodo, new Nodo(nodo, 0));
		
		return this.nodos.get(nodo);
	}
	
	public List<Arista> getAristas() {
		return aristas;
	}

	public void setAristas(List<Arista> aristas) {
		this.aristas = aristas;
	}
}
