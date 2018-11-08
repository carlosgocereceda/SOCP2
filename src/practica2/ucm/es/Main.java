package practica2.ucm.es;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import arista.Arista;

public class Main {

	static long N, p;
	
	public static void main(String[] args) {	
		if(args.length < 2) {
			System.out.println("USO: " + args[0] + " <N> <p>");
		} else {
			N = Long.parseLong(args[0]);
			p = Long.parseLong(args[1]);
			
			System.out.println("N: " + N + "  p: " + p);
			
			generarAristas();
		}
		
	}
	
	public static void generarAristas() {
		List<Arista> pares = new ArrayList<>();
		List<Arista> aristas = new ArrayList<>();
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				Arista a = new Arista(i, j);
				if(!pares.contains(a) && i != j)
					pares.add(a);
			}
		}
		
		System.out.println("Numero de pares: " + pares.size() + " debian ser: " + ((N * (N - 1) ) / 2));
		
		System.out.println(pares);
	}
}
