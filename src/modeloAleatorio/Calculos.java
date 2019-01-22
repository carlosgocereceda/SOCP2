package modeloAleatorio;

import java.util.ArrayList;
import java.util.Set;
// En esta clase se haran los correspondientes calculos para las metricas a rellenar en la clase Estadistica
public class Calculos {

	private Set<Arista> aristas;
	private ArrayList<Integer> gradoNodos;
	private double gradoMedio = 0.0;
	private int max = 0;
	private int min = 0;
	private int N;
	
	public Calculos(Set<Arista> aristas, int N) {
		// Inicializo los atributos de la clase
		this.N = N;
		gradoNodos = new ArrayList<Integer>();
		this.aristas = aristas;
		
		// Inicializo el array que contendra los grados de cada nodo de la red
		for(int i = 0; i < N; i++) {
			gradoNodos.add(0);
		}
		// Me guardo el grado de cada NODO recorriendome las aristas de la red
		for(Arista a : aristas) {
			int gradoNodo1 = gradoNodos.get(a.getNodo1());
			int gradoNodo2 = gradoNodos.get(a.getNodo2());
			gradoNodos.set(a.getNodo1(), gradoNodo1 + 1);
			gradoNodos.set(a.getNodo2(), gradoNodo2 + 1);
		}
		// Busco el grado mas alto y el mas pequeño
		for(int i = 0; i < gradoNodos.size(); i++) {
			gradoMedio += gradoNodos.get(i);
			// Aqui el grado mas alto
			if(gradoNodos.get(i) > max) {
				max = gradoNodos.get(i);
			}
			// Aqui el mas pequeño
			if(gradoNodos.get(i) < min) {
				min = gradoNodos.get(i);
			}
		}
		// Calculo el grado medio
		gradoMedio = gradoMedio / gradoNodos.size();
		
	}
	// Grado medio de la red
	public double getGradoMedio() {
		return this.gradoMedio;
	}
	// El MaxHub de la red
	public int getMaxHub() {
		return this.max;
	}
	// El MinHub de la red
	public int getMinHub() {
		return this.min;
	}
	// Numero de aristas de la red
	public int getNumAristas() {
		return aristas.size();
	}
	// Calculo el coeficiente de Clustering
	public double getClustering() {
		return getGradoMedio()/N;
	}
	// Calculos la densidad
	public double getDensity() {
		return aristas.size() / ((N*(N-1)) / 2);
	}
	// Calculo el Average Distance
	public double getAvarageDistance() {
		return Math.log(N) / Math.log(this.gradoMedio);
	}
	
	
}
