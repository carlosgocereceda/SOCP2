package modeloAleatorio;

import java.util.ArrayList;
import java.util.Set;

public class Calculos {

	private Set<Arista> aristas;
	private ArrayList<Integer> gradoNodos;
	private double gradoMedio = 0.0;
	private int max = 0;
	private int min = 0;
	private int N;
	
	public Calculos(Set<Arista> aristas, int N) {
		this.N = N;
		gradoNodos = new ArrayList<Integer>();
		this.aristas = aristas;
		
		for(int i = 0; i < N; i++) {
			gradoNodos.add(0);
		}
		
		for(Arista a : aristas) {
			int gradoNodo1 = gradoNodos.get(a.getNodo1());
			int gradoNodo2 = gradoNodos.get(a.getNodo2());
			gradoNodos.set(a.getNodo1(), gradoNodo1 + 1);
			gradoNodos.set(a.getNodo2(), gradoNodo2 + 1);
		}
		
		for(int i = 0; i < gradoNodos.size(); i++) {
			gradoMedio += gradoNodos.get(i);
			if(gradoNodos.get(i) > max) {
				max = gradoNodos.get(i);
			}
			if(gradoNodos.get(i) < min) {
				min = gradoNodos.get(i);
			}
		}
		gradoMedio = gradoMedio / gradoNodos.size();
		
	}
	
	public double getGradoMedio() {
		return this.gradoMedio;
	}
	public int getMaxHub() {
		return this.max;
	}
	public int getMinHub() {
		return this.min;
	}
	public int getNumAristas() {
		return aristas.size();
	}
	public double getClustering() {
		return getGradoMedio()/N;
	}
	public double getDensity() {
		return aristas.size() / ((N*(N-1)) / 2);
	}
	
}
