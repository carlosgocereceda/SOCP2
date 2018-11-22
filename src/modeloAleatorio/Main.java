package modeloAleatorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

	public static long N;
	public static double p;
	
	public static void main(String[] args) {	
		if(args.length < 2) {
			System.out.println("USO: " + args[0] + " <N> <p>");
		} else {
			N = Long.parseLong(args[0]);
			p = Double.parseDouble(args[1]);
			
			System.out.println("N: " + N + "  p: " + p);
			new GenerarGephi(generarAristas()).generaFicheros();
		}
		
	}
	
	public static List<Arista> generarAristas() {
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
		
		
		
		for (int i = 0; i < pares.size(); i++) {
			if(generarRandom(p))
				aristas.add(pares.get(i));
		}
		return aristas;
	}

	public static boolean generarRandom(double p){
		Double result;
		Random r= new Random();
		result = r.nextDouble();
		
		return result <= p;
	}
}
